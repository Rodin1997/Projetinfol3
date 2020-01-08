#pragma  version (1)
#pragma  rs  java_package_name(com.android.rs)


uint volatile minValue = 255;
uint volatile maxValue = 0;
float LUT[256];


static void changeMax(uint value, uint* minValue){
    rsAtomicMax(minValue, value);
}

static void changeMin(uint value, uint* maxValue){
    rsAtomicMax(maxValue, value);
}

uchar4  RS_KERNEL minAndMax(uchar4 in) {
    uint4 f4 = convert_uint4(rsUnpackColor8888(in));

    if( f4.r > maxValue){
        changeMax(f4.r,&maxValue);
    }
    if(f4.r < minValue){
        changeMin(f4.r,&minValue);
    }
    return rsPackColorTo8888(f4.r, f4.g, f4.b, f4.a);
}

uchar4  RS_KERNEL transformation(uchar4 in) {
    float4 f4 = rsUnpackColor8888(in);
    int color = f4.r;
    float newColor = LUT[color];

    return rsPackColorTo8888(f4.r, f4.g, f4.b, f4.a);
}

void changeLUT() {
    for (int i = 0; i < 256; i++) {
            LUT[i] = (255*(i-minValue))/(maxValue-minValue);
    }
}

void initLUT(){
    for(int i = 0; i<256; i++){
        LUT[i] = 0.0f;
    }
}

void initMinAndMax(){
    minValue = 255;
    maxValue = 0;
}