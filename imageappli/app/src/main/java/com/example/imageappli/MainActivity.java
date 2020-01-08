package com.example.imageappli;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import androidx.renderscript.Allocation;
import androidx.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.rs.ScriptC_Gris;
import com.android.rs.ScriptC_coloriseRS;
import com.android.rs.ScriptC_redOnlyRS;
import com.android.rs.ScriptC_extDynRS;
import com.android.rs.ScriptC_histogramEqualizationRS;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    // Display zone
    private ImageView imageViewIndex;
    // Picture on the screen
    private Bitmap picture;

    // Save for the picture actually on the screen
    private Bitmap save;
    private BitmapFactory.Options option;
    int counter;
    int counterSave;
    // Differents button to choose the treatment.
    private Button buttonTocolorise;
    private Button buttonToGrey;
    private Button buttonToGrey1;
    private Button buttonGrisRS;
    private Button buttonToRedOnly;
    private Button buttonDynamiqueExtension;
    private Button buttonDynamiqueExtensionColor;
    private Button buttonHistogramEqualization;
    private Button buttonHistogramEqualizationColor;
    private Button buttonReset;
    private Button buttonNextPicture;
    private Button buttonFlou;
    private Button buttonConvolutionSobel;
    private Button buttonConvolutionPrewitt;
    private Button buttonRedOnlyRS;
    private Button buttonColoriseRS;
    private Button buttonHistoEqualRS;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of the different variable + display of the first picture
            counter = 1;
            counterSave = 0;
        option = new BitmapFactory.Options();
        option.inMutable = true;
        option.inScaled = false;
        imageViewIndex = findViewById(R.id.picture);
        picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo, option); // Utiliser ces 2 lignes pour changer les images.
        save = picture;
        imageViewIndex.setImageBitmap(picture);

        // Link between the button and the function to do.
        buttonTocolorise = findViewById(R.id.buttoncolorise);
        buttonTocolorise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorise(picture);
            }
        });

        buttonToGrey = findViewById(R.id.buttonGrey);
        buttonToGrey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGreyDifferent(picture);
            }
        });

        buttonToGrey1 = findViewById(R.id.buttonGrey1);
        buttonToGrey1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGrey(picture);
            }
        });


        buttonToRedOnly = findViewById(R.id.buttonRedonly);
        buttonToRedOnly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redOnly(picture);

            }
        });

        buttonDynamiqueExtension = findViewById(R.id.buttonDynamiqueExtension);
        buttonDynamiqueExtension.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dynamiqueExtension(picture);
            }
        });

        buttonDynamiqueExtensionColor = findViewById(R.id.buttonDynamiqueExtColor);
        buttonDynamiqueExtensionColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dynamiqueExtensionColor(picture);
            }
        });

        buttonHistogramEqualization = findViewById(R.id.buttonHistoEqualization);
        buttonHistogramEqualization.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                histogramEqualization(picture);
            }
        });

        buttonHistogramEqualizationColor = findViewById(R.id.buttonHistoEqualizationColor);
        buttonHistogramEqualizationColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                histogramEqualizationColorV1(picture);
            }
        });
            buttonFlou = findViewById(R.id.buttonFlou);
            buttonFlou.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Convolution convolution = new Convolution(3);
                    convolution.setAll(1);
                    convolution.Flou(picture);

                }
            });
            buttonConvolutionPrewitt = findViewById(R.id.buttonConvolutionPrewitt);
            buttonConvolutionPrewitt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Convolution convolution = new Convolution(3);
                    convolution.setAll(1);
                    convolution.filtrePrewitt(picture);

                }
            });
            buttonConvolutionSobel = findViewById(R.id.buttonConvolutionSobel);
            buttonConvolutionSobel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Convolution convolution = new Convolution(3);
                    convolution.setAll(1);
                    convolution.filtreSobel(picture);

                }
            });
            buttonGrisRS= findViewById(R.id.buttonGrisRS);
            buttonGrisRS.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GrisRS(picture);
                }
            });
            buttonRedOnlyRS = findViewById(R.id.buttonRedOnlyRS);
            buttonRedOnlyRS.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    redOnlyRS(picture);

                }
            });
            buttonColoriseRS= findViewById(R.id.buttonColoriseRS);
            buttonColoriseRS.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    coloriseRS(picture);
                }
            });

            buttonHistoEqualRS = findViewById(R.id.buttonEqualHistoRS);
            buttonHistoEqualRS.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    histogramEqualizationRS(picture);
                }
            });
            buttonReset= findViewById(R.id.buttonReset);
            buttonReset.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switch (counterSave){
                        case 0:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo, option); // Utiliser ces 2 lignes pour changer les images.
                            imageViewIndex.setImageBitmap(picture);
                            break;
                        case 1:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo1, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;

                        case 2:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo2, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;

                        case 3:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo3, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;

                        case 4:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo4, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;
                        case 5:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo5, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;

                        case 6:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo6, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;

                        case 7:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo7, option);
                            imageViewIndex.setImageBitmap(picture);
                            break;
                }

                }
            });

            buttonNextPicture= findViewById(R.id.buttonNextPicture);
            buttonNextPicture.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switch (counter){
                        case 0:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo, option); // Utiliser ces 2 lignes pour changer les images.
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave = 0;
                            break;

                        case 1:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo1, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;

                        case 2:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo2, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;

                        case 3:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo3, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;
                        case 4:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo4, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;
                        case 5:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo5, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;
                        case 6:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo6, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter++;
                            counterSave++;
                            break;
                        case 7:
                            picture = BitmapFactory.decodeResource(getResources(), R.drawable.photo7, option);
                            imageViewIndex.setImageBitmap(picture);
                            counter=0;
                            counterSave++;
                            break;

                    }

                }
            });
    }
    /**
     * Transforme une image en couleur en une image en gris version 1
     * @param bmp image à changer
     *  TD01
     *
     */
        public void toGrey(Bitmap bmp) {
            int r, g, b;
            int colorPixel;
            int width = bmp.getWidth();
            int height = bmp.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    colorPixel = bmp.getPixel(x, y); // recupère la valeur au pixel x et y
                    r = Color.red(colorPixel); // recupère les valeurs de l'espace rgb
                    g = Color.green(colorPixel);
                    b = Color.blue(colorPixel);
                    int colorNewPixel = (int) (0.3 * r + 0.59 * g + 0.11 * b); // Calcul de la couleur grise
                    bmp.setPixel(x, y, Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel)); // Transforme la couleur du pixel en gris

                }
            }


        }
        /**
         * Transforme une image en couleur en une image en gris version plus rapide
         * @param bmp image à mettre en gris
         *  TD01
         *
         */
        public void toGreyDifferent(Bitmap bmp) {
            int r, g, b, colorNewPixel;
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int tab[] = new int[width * height];
            bmp.getPixels(tab, 0, width, 0, 0, width, height);
            for (int i = 0; i < tab.length; i++) {
                r= Color.red(tab[i]);
                g = Color.green(tab[i]);
                b = Color.blue(tab[i]);
                colorNewPixel = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                tab[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
            }
            bmp.setPixels(tab, 0, width, 0, 0, width, height);
        }
        /**
         * Change la couleur de l'image
         * @param bmp image à coloriser
         * TD02:
         * Question 2.1
         */
        public void colorise(Bitmap bmp) {
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int tab_pixel[] = new int[width * height];
            float tab_HSV[] = new float[3];
            Random aleatoire = new Random();
            int mystere = aleatoire.nextInt(360);


            bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);
            for (int i = 0; i < tab_pixel.length; i++) {
                Color.RGBToHSV(Color.red(tab_pixel[i]), Color.green(tab_pixel[i]), Color.blue(tab_pixel[i]), tab_HSV);
                tab_HSV[0] = mystere;
                tab_pixel[i] = Color.HSVToColor(tab_HSV);
            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
        }

        /**
         * Garde uniquement la couleur rouge de l'image
         * @param bmp image à changer
         */
        public void redOnly(Bitmap bmp) {
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int tab_pixel[] = new int[width * height];
            float tab_HSV[] = new float[3];

            bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);
            for (int i = 0; i < tab_pixel.length; i++) {
                Color.RGBToHSV(Color.red(tab_pixel[i]), Color.green(tab_pixel[i]), Color.blue(tab_pixel[i]), tab_HSV);
                if (tab_HSV[0] >= 15 && tab_HSV[0] <= 345) {
                    int r = Color.red(tab_pixel[i]);
                    int g = Color.green(tab_pixel[i]);
                    int b = Color.blue(tab_pixel[i]);
                    int colorNewPixel = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                    tab_pixel[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
                }

            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
        }
        /* Question 1.1 TD 3 */

        /**
         * Augmente le contraste de l'image
         * @param bmp image à changer
         */
        public void dynamiqueExtension(Bitmap bmp){
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] pixels = new int[w*h];
            int[] LUT = new int[256];
            bmp.getPixels(pixels,0,w,0,0,w,h);
            int min = 255;
            int max = 0;
            int grey;

            for(int i = 1; i < w*h; ++i){
                grey = Color.red(pixels[i]);
                if(min > grey){
                    min = grey;
                }
                if(max < grey){
                    max = grey;
                }
            }
            //Initialization of the lut
            for(int i = 0; i< 256 ; i++){
                LUT[i]=(255*(i-min))/(max-min);
            }
            //Transformation calcul
            for(int i = 0; i < w*h; ++i){
                grey = Color.red(pixels[i]);
                pixels[i] = Color.rgb(LUT[grey],LUT[grey],LUT[grey]);
            }

            bmp.setPixels(pixels,0,w,0,0,w,h);
        }

        /* Question 1.2 TD 3
        * marche mal donc pas de bouton ni de test pour ce code
        * */
        public void dynamiqueExtensionV2(Bitmap bmp){
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] tab_pixels = new int[w*h];
            bmp.getPixels(tab_pixels,0,w,0,0,w,h);
            int min = 0;
            int max = 255;
            int[] histogram = new int[256];
            int color;

            //mise en place de l'histogramme
            for(int i = 0; i < w*h; ++i){
                color = Color.red(tab_pixels[i]);
                histogram[color]++;

            }

            int pourcentagePixelmin= (1*w*h)/100;
            int pourcentagePixelmax= (w*h)-((1*w*h)/100);
            int compteur = 0;
            for(int i = 0;i < 256;i++){
                compteur = compteur + histogram[i];
                if(compteur == pourcentagePixelmin){
                    min = i;
                    System.out.println(i);
                }
                if(compteur == pourcentagePixelmax){
                    max = i;
                    System.out.println("LE I EST ICI !!!!!" + "" + i);
                }

            }

            for(int i = 0; i < w*h; ++i){
                color = Color.red(tab_pixels[i]);
                color = (255 / (max - min)) * (color - min);
                tab_pixels[i] = Color.rgb(color,color,color);
            }

            bmp.setPixels(tab_pixels,0,w,0,0,w,h);
        }

        /* Question 1.3 TD 3 */

        /**
         * Augmente le contraste de l'image
         * @param bmp image à changer
         */
        public void dynamiqueExtensionColor(Bitmap bmp){
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] pixels = new int[w*h];
            bmp.getPixels(pixels,0,w,0,0,w,h);
            int[] LUT_Red= new int[256]; // Initialize the differents LUT.
            int[] LUT_Green= new int[256];
            int[] LUT_Blue = new int[256];

            int minRed = 255;
            int maxRed = 0;
            int minGreen = 255;
            int maxGreen = 0;
            int minBlue = 255;
            int maxBlue  = 0;

            int colorRed;
            int colorBlue;
            int colorGreen;

            for(int i = 0; i < pixels.length; i++){
                colorRed = Color.red(pixels[i]);
                if(minRed > colorRed){
                    minRed = colorRed;
                }
                if(maxRed < colorRed){
                    maxRed = colorRed;
                }

                colorGreen = Color.green(pixels[i]);
                if(minGreen > colorGreen){
                    minGreen = colorGreen;
                }
                if(maxGreen < colorGreen){
                    maxGreen  = colorGreen;
                }

                colorBlue = Color.blue(pixels[i]);
                if(minBlue > colorBlue){
                    minBlue = colorBlue;
                }
                if(maxBlue < colorBlue){
                    maxBlue = colorBlue;
                }

            }

            for(int i = 0; i < 256; i++){
                LUT_Red[i] = (255 *(i - minRed))/(maxRed-minRed);
                LUT_Blue[i] = (255 *(i - minBlue))/(maxBlue-minBlue);
                LUT_Green[i] =  (255 *(i - minGreen))/(maxGreen-minGreen);
            }


            for(int i = 0; i < pixels.length; i++){
                int red = Color.red(pixels[i]);
                int green = Color.green(pixels[i]);
                int blue = Color.blue(pixels[i]);
                int newred = LUT_Red[red];
                int newgreen =  LUT_Green[green];
                int newblue = LUT_Blue[blue];
                pixels[i] = Color.rgb(newred,newgreen,newblue);
            }

            bmp.setPixels(pixels,0,w,0,0,w,h);
        }

        /* Question 2.1 TD 3 */

        /**
         * Augmente le contraste grace à une égalisation d'histogramme pour une image grise
         * @param bmp image à changer
         */
        public void histogramEqualization(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] histogram = new int[256];
            int[] tab_pixel = new int[width * height];
            bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);


            for(int i = 0; i < tab_pixel.length ; i++){
                histogram[Color.red(tab_pixel[i])]++; //Calcul Histogramme
            }
            for(int i = 1 ; i < 256 ; i++){
                histogram[i]=histogram[i]+histogram[i-1]; // Cumul calcul histogramme
            }


            for(int i = 0 ; i < tab_pixel.length ; i++){
                int cumulateHistoValue = histogram[Color.red(tab_pixel[i])]; // La valeur de l'histogramme cumulé est récupérée en fonction de la valeur de gris du pixel
                int grey = (cumulateHistoValue*255)/(width*height); // le nouveau gris est déterminé
                tab_pixel[i]= Color.rgb(grey,grey,grey); // puis affecté

            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
        }

        /* Question 2.2 TD 3*/

        /**
         * Augmente le contraste grace à une égalisation d'histogramme pour une image couleur
         * @param bmp image à changer
         */
        public void histogramEqualizationColorV1(Bitmap bmp){

            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] histogram = new int[256];
            int[] tab_pixel = new int[width * height];
            bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);

            //Histogram calcul
            for(int i = 0; i < tab_pixel.length ; i++){
                int indice = (Color.red(tab_pixel[i])+Color.blue(tab_pixel[i])+Color.green(tab_pixel[i]))/3;
                histogram[indice]++;
            }

            for(int i = 1 ; i < 256 ; i++){
                histogram[i] = histogram[i] + histogram[i-1];
            }

            for(int i = 0 ; i < tab_pixel.length ; i++){
                int red = (histogram[Color.red(tab_pixel[i])]*255)/(width*height);
                int green = (histogram[Color.green(tab_pixel[i])]*255)/(width*height);
                int blue = (histogram[Color.blue(tab_pixel[i])]*255)/(width*height);
                tab_pixel[i] = Color.rgb(red,green,blue);
            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);

        }
    /**
     * Classe Convolution pour utiliser plusieurs filtres implémentant la même classe
     * (notamment l'utilisation d'un filtre sobel et d'un filtre prewitt sur une matrice 3X3)
     */
    public class Convolution {

        public int SIZE = 3;
        public int[][] Matrix;
        public int Factor = SIZE*SIZE;
        private int[][] sobelX;
        private int[][] sobelY;
        private int [][] prewittX;
        private int [][] prewittY;
        public Convolution(int size){
            this.SIZE = size; // taille de la matrice
            Matrix = new int[size][size];
            // Sobel initialisation
            sobelX = new int[3][3];
            sobelX[0][0] = -1;
            sobelX[0][1] = -2;
            sobelX[0][2] = -1;
            sobelX[1][0] = 0;
            sobelX[1][1] = 0;
            sobelX[1][2] = 0;
            sobelX[2][0] = 1;
            sobelX[2][1] = 2;
            sobelX[2][2] = 1;

            sobelY = new int[3][3];
            sobelY[0][0] = -1;
            sobelY[1][0] = -2;
            sobelY[2][0] = -1;
            sobelY[0][1] = 0;
            sobelY[1][1] = 0;
            sobelY[2][1] = 0;
            sobelY[0][2] = 1;
            sobelY[1][2] = 2;
            sobelY[2][2] = 1;

            prewittX = new int [3][3];
            prewittY = new int[3][3];
            //Prewit initialisation
            for(int x = 0; x < 3 ; x++){
                for(int y = 0; y < 3; y++){
                    if(x== 0){
                        prewittX[x][y] = -1;
                    }else if(x == 1){
                        prewittX[x][y] = 0;
                    }else{
                        prewittX[x][y] = 1;
                    }
                }
            }

            for(int x = 0; x < 3 ; x++){
                for(int y = 0; y < 3; y++){
                    if(y== 0){
                        prewittY[x][y] = -1;
                    }else if(y == 1){
                        prewittY[x][y] = 0;
                    }else{
                        prewittY[x][y] = 1;
                    }
                }
            }
        }
        /**
         * Fonction pour créer du flou sur une image, plusieurs utilisation possible
         * @param bmp image à flouter
         */
        public void Flou(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] tabPixels = new int [width*height];
            int[] tabPixelsSave = new int [width*height];

            bmp.getPixels(tabPixels, 0, width, 0, 0, width, height);
            bmp.getPixels(tabPixelsSave, 0,width,0,0,width,height);


            for(int x = 0; x < width-SIZE; x++){
                for(int y = 0; y < height-SIZE ; y++){

                    int position = ((x+ (SIZE/2)) + ((y+(SIZE/2))*width)); // pour commencer à l'indice 1.1 de l'image et mettre la position au centre de la matrice
                    int sumRed = 0;
                    int sumGreen = 0;
                    int sumBlue = 0;

                    for(int matriceX = 0; matriceX < SIZE ; matriceX++){ //
                        for(int matriceY = 0; matriceY < SIZE ; matriceY++){

                            int pixel = tabPixelsSave[x+matriceX +(y+matriceY)*width];

                            int mValue = Matrix[matriceX][matriceY];

                            sumRed = sumRed + (Color.red(pixel)*(mValue));
                            sumGreen = sumGreen + (Color.green(pixel)*(mValue));
                            sumBlue = sumBlue + (Color.blue(pixel)*(mValue));

                        }
                    }
                    int newRed = (sumRed / this.Factor);
                    int newGreen = (sumGreen / this.Factor);
                    int newBlue = (sumBlue / this.Factor);

                    tabPixels[position] = Color.rgb(newRed,newGreen,newBlue);
                }
            }

            bmp.setPixels(tabPixels ,0, width, 0, 0, width, height);

        }
        /**
         * On l'utilise pour une matrice de même valeur (exemple: 3x3)
         * @param valeur valeur de chaque case de la matrice
         */
        public void setAll(int  valeur){
            for(int x = 0 ; x < SIZE; x++){
                for(int y = 0 ; y < SIZE; y++){
                    Matrix[x][y] = valeur;
                }
            }

        }
        /**
         * Donne les contours des différents éléments de l'image
         * @param bmp image sur laquelle on applique le Filtre sobel
         */
        public void filtreSobel(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] tabPixels = new int [width*height];
            int[] tabPixelsSave = new int [width*height];

            bmp.getPixels(tabPixels, 0, width, 0, 0, width, height);
            bmp.getPixels(tabPixelsSave, 0,width,0,0,width,height);


            for(int x = 0; x < width-SIZE; x++){
                for(int y = 0; y < height-SIZE ; y++){

                    int position = ((x+ (SIZE/2)) + ((y+(SIZE/2))*width)); // pour commencer à l'indice 1.1 de l'image et mettre la position au centre de la matrice
                    int gX = 0;
                    int gY = 0;
                    for(int matriceX = 0; matriceX < SIZE ; matriceX++) {
                        for (int matriceY = 0; matriceY < SIZE; matriceY++) {
                            int pixel = tabPixelsSave[x + matriceX + (y + matriceY) * width];
                            gX = gX +(Color.red(pixel)*sobelX[matriceX][matriceY]);//appel matrice sobel
                            gY = gY +(Color.red(pixel)*sobelY[matriceX][matriceY]);
                        }
                    }
                        gX = gX/4;//formule sobel trouvé sur internet, elle est différente de prewitt car prewitt est une division par 3
                        gY = gY/4;
                    int newG = (int)Math.sqrt(Math.pow(gX,2.0) + Math.pow(gY, 2.0));
                    if(newG > 255){
                        newG = 255;
                    }


                    tabPixels[position] = Color.rgb(newG,newG,newG);

                }
            }
            bmp.setPixels(tabPixels ,0, width, 0, 0, width, height);

        }
        /**
         * Donne les contours des différents éléments de l'image
         * @param bmp image sur laquelle on applique le Filtre Prewitt
         */
        public void filtrePrewitt(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] tabPixels = new int [width*height];
            int[] tabPixelsSave = new int [width*height];

            bmp.getPixels(tabPixels, 0, width, 0, 0, width, height);
            bmp.getPixels(tabPixelsSave, 0,width,0,0,width,height);


            for(int x = 0; x < width-SIZE; x++){
                for(int y = 0; y < height-SIZE ; y++){

                    int position = ((x+ (SIZE/2)) + ((y+(SIZE/2))*width)); // pour commencer à l'indice 1.1 de l'image et mettre la position au centre de la matrice
                    int gX = 0;
                    int gY = 0;
                    for(int matriceX = 0; matriceX < SIZE ; matriceX++) {
                        for (int matriceY = 0; matriceY < SIZE; matriceY++) {
                            int pixel = tabPixelsSave[x + matriceX + (y + matriceY) * width];
                            gX = gX +(Color.red(pixel)*prewittX[matriceX][matriceY]); //appel de la matrice prewitt
                            gY = gY +(Color.red(pixel)*prewittY[matriceX][matriceY]);
                        }
                    }
                    gX = gX/3;//formule prewitt trouvé sur internet, elle est différente de sobel car sobel est une division par 4
                    gY = gY/3;
                    int newG = (int)Math.sqrt(Math.pow(gX,2.0) + Math.pow(gY, 2.0));
                    if(newG > 255){
                        newG = 255;
                    }


                    tabPixels[position] = Color.rgb(newG,newG,newG);

                }
            }
            bmp.setPixels(tabPixels ,0, width, 0, 0, width, height);

        }
    }


    private void GrisRS(Bitmap bmp) {
        //1)  Creer un  contexte  RenderScript
        androidx.renderscript.RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        androidx.renderscript.Allocation input = androidx.renderscript.Allocation.createFromBitmap(rs, bmp);
        androidx.renderscript.Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_Gris greyScript = new ScriptC_Gris(rs);
        //4)  Lancer  le noyau
        greyScript.forEach_Gris(input, output);
        //5)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //6)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        greyScript.destroy();
        rs.destroy();
    }
    private void coloriseRS(Bitmap bmp) {
        Random aleatoire = new Random();
        float mystere = (float) (aleatoire.nextInt(360));
        //1)  Creer un  contexte  RenderScript
        RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_coloriseRS colorizeScript = new ScriptC_coloriseRS(rs);
        //4)  Initialiser  les  variables  globales  potentielles
        colorizeScript.set_t(mystere);
        //5)  Lancer  le noyau
        colorizeScript.forEach_coloriseRS(input, output);
        //6)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //7)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        colorizeScript.destroy();
        rs.destroy();
    }

    private void redOnlyRS(Bitmap bmp){
        //1)  Creer un  contexte  RenderScript
        RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_redOnlyRS redOnlyScript = new ScriptC_redOnlyRS(rs);
        //4)  Lancer  le noyau
        redOnlyScript.forEach_redOnlyRS(input, output);
        //5)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //6)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        redOnlyScript.destroy();
        rs.destroy();
    }

    private void histogramEqualizationRS(Bitmap bmp){
        //Prend les dimensions de l'image
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        //crée un nouveau bitmap
        Bitmap res = bmp.copy(bmp.getConfig(), true);

        //crée un renderscript
        RenderScript rs = RenderScript.create(this);

        //crée une allocation à partir de bitmap
        Allocation allocationA = Allocation.createFromBitmap(rs, res);

        //crée une allocation de même type
        Allocation allocationB = Allocation.createTyped(rs, allocationA.getType());

        //crée le script à partir du fichier rs
        ScriptC_histogramEqualizationRS histEqScript = new ScriptC_histogramEqualizationRS(rs);

        //donne la taille dans le script
        histEqScript.set_size(width*height);

        //Appel le noyau pour créer l'histogramme
        histEqScript.forEach_root(allocationA, allocationB);

        //Appel la méthode rs et crée un histogramme cumulé
        histEqScript.invoke_createRemapArray();

        //Appel un second noyau et change en RGB
        histEqScript.forEach_remaptoRGB(allocationB, allocationA);

        //le script copie en bitmap
        allocationA.copyTo(bmp);

        //libère la mémoire
        allocationA.destroy();
        allocationB.destroy();
        histEqScript.destroy();
        rs.destroy();

    }
//pas de bouton pour la fonction dynExtensionRS car elle ne marche pas pour le moment
    private void dynExtensionRS(Bitmap bmp){

        Bitmap res = bmp.copy(bmp.getConfig(), true);
        RenderScript rs = RenderScript.create(this);
        Allocation allocationA = Allocation.createFromBitmap(rs, res);
        Allocation allocationB = Allocation.createTyped(rs, allocationA.getType());
        ScriptC_extDynRS test= new ScriptC_extDynRS(rs);
        test.invoke_initMinAndMax();
        test.forEach_minAndMax(allocationA, allocationB);
        test.invoke_initLUT();
        test.invoke_changeLUT();
        test.forEach_transformation(allocationB, allocationA);
        allocationA.copyTo(bmp);
        allocationA.destroy();
        allocationB.destroy();
        test.destroy();
        rs.destroy();

    }



}
