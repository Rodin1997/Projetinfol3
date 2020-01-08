#pragma  version (1)
#pragma  rs  java_package_name(com.android.rs)


uchar4  RS_KERNEL  redOnlyRS(uchar4  in) {
    float4  pixelf = rsUnpackColor8888(in); // On récupère la couleur qu'on décompose en 4 valeurs : R, G, B, A
    float maxRGB= max(pixelf.r,(max(pixelf.g,pixelf.b)));
    float minRGB = min(pixelf.r,(min(pixelf.b,pixelf.g)));
    float t;

    //Convertion de RGV vers HSV

    if(maxRGB == minRGB){
        t = 0;
    }else if(maxRGB == pixelf.r){
        t = (int)(60*((pixelf.g - pixelf.b)/(maxRGB-minRGB))+360)%360;
    }else if(maxRGB == pixelf.g){
        t = (int)(60*((pixelf.b - pixelf.r)/(maxRGB-minRGB))+120);
    }else if(maxRGB == pixelf.b){
        t = (int)(60*((pixelf.r - pixelf.g)/(maxRGB-minRGB))+240);
    }

    // Convertion de HSV vers RGB

    //Si la valeur de t est en dehors du rouge, je mets le pixel en gris
    if(t >= 15 && t <= 345){
        float  grey = (0.30* pixelf.r + 0.59* pixelf.g + 0.11* pixelf.b);
        return  rsPackColorTo8888(grey , grey , grey , pixelf.a);
    }else{
        return in;
    }
}