package com.example.imageappli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
    private Button buttonToRedOnly;
    private Button buttonDynamiqueExtension;
    private Button buttonDynamiqueExtensionColor;
    private Button buttonHistogramEqualization;
    private Button buttonHistogramEqualizationColor;
    private Button buttonReset;
    private Button buttonNextPicture;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of the different variable + display of the first picture
            counter = 0;
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
                colorize(picture);
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



                    }

                }
            });
    }
        public void toGrey(Bitmap bmp) {
            int R, G, B;
            int colorPixel;
            int width = bmp.getWidth();
            int height = bmp.getHeight();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    colorPixel = bmp.getPixel(x, y); // We get the value of a pixel at the position x,y
                    R = Color.red(colorPixel); // We get the differents value of RGB space
                    G = Color.green(colorPixel);
                    B = Color.blue(colorPixel);
                    int colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B); // We calcul the grey
                    bmp.setPixel(x, y, Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel)); // We transform the pixel with a grey color

                }
            }


        }
        /**
         * Transform a color picture to a grey picture. Same fonction as toGrey but faster.
         * @param bmp Picture to  be changed
         *  TD01
         *
         */
        public void toGreyDifferent(Bitmap bmp) {
            int R, G, B, colorNewPixel;
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int tab[] = new int[width * height];
            bmp.getPixels(tab, 0, width, 0, 0, width, height);
            for (int i = 0; i < tab.length; i++) {
                R = Color.red(tab[i]);
                G = Color.green(tab[i]);
                B = Color.blue(tab[i]);
                colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B);
                tab[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
            }
            bmp.setPixels(tab, 0, width, 0, 0, width, height);
        }
        /**
         * change at random the hue of a picture
         * @param bmp Picture to be changed
         * TD02:
         * Question 2.1
         */
        public void colorize(Bitmap bmp) {
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
         * Keep only the color red on the picture
         * @param bmp Picture to be  changed
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
                    int R = Color.red(tab_pixel[i]);
                    int G = Color.green(tab_pixel[i]);
                    int B = Color.blue(tab_pixel[i]);
                    int colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B);
                    tab_pixel[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
                }

            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
        }
        /* Question 1.1 TD 3 */

        /**
         * Increase the contrast of the picture
         * @param bmp Picture to be changed
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

        /* Question 1.2 TD 3 */
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
         * Increase the contrast of the picture.
         * @param bmp Picture to be changed
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

            for(int i = 0; i < pixels.length; i++){ // We determine the max and min for each value of RGB space
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


            for(int i = 0; i < pixels.length; i++){ // Set the new value of each pixel of the picture
                int red = Color.red(pixels[i]);
                int green = Color.green(pixels[i]);
                int blue = Color.blue(pixels[i]);
                int new_red = LUT_Red[red];
                int new_green =  LUT_Green[green];
                int new_blue = LUT_Blue[blue];
                pixels[i] = Color.rgb(new_red,new_green,new_blue);
            }

            bmp.setPixels(pixels,0,w,0,0,w,h);
        }

        /* Question 2.1 TD 3 */

        /**
         * Increase constrat by histogram equalization for a grey picture
         * @param bmp Picture to be changed
         */
        public void histogramEqualization(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] histogram = new int[256];
            int[] tab_pixel = new int[width * height];
            bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);


            for(int i = 0; i < tab_pixel.length ; i++){
                histogram[Color.red(tab_pixel[i])]++; //histogram Calcul
            }
            for(int i = 1 ; i < 256 ; i++){
                histogram[i]=histogram[i]+histogram[i-1]; // Cumulate Histogram calcul
            }


            for(int i = 0 ; i < tab_pixel.length ; i++){
                int cumulateHistoValue = histogram[Color.red(tab_pixel[i])]; // The value of the cumulative histogram is recovered as a function of the grey value of the pixel
                int grey = (cumulateHistoValue*255)/(width*height); // The new grey value is determined.
                tab_pixel[i]= Color.rgb(grey,grey,grey); // then affected

            }
            bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
        }

        /* Question 2.2 TD 3*/

        /**
         * Increase constrat by histogram equalization for a color picture
         * @param bmp Picture to be changed
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
}
