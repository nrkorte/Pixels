package PixLab;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

    /*
     * Instance variables: image - a 2D Array of Colors
     */

    private Color[][] image;

    /**
     * Creates a new Image based on an existing 2D array of colors
     *
     * @param image the array of Colors
     */
    public Image(Color[][] image) {
        this.image = image;
    }

    /**
     * Creates a new Image from an image stored in a file
     *
     * @param file the name of the file to create the Image from
     */
    public Image(String file) {
        // read image and load into array of Colors
        try {
            BufferedImage img = ImageIO.read(new File(file));
            image = new Color[img.getHeight()][img.getWidth()];
            for (int r = 0; r < image.length; r++) {
                for (int c = 0; c < image[r].length; c++) {
                    image[r][c] = new Color(img.getRGB(c, r));
                }
            }
        } catch (IOException e) { // couldn't open file
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Display the image into a Java GUI Window
     *
     * @param title The title to be displayed in the window's title bar
     */
    public void display(String title) {
        new PixLab.ImageGUI(image, title);
    }

    /**
     * noBlue - returns a new Image of this with all the Blue components removed
     *
     * @return Image without any blue
     */
    public Image removeBlue() {

        Color[][] newImg = new Color[image.length][image[0].length];

        // loop through all pixels
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {

                // get component parts of pixel's color
                int red = image[r][c].getRed();
                int green = image[r][c].getGreen();
                int blue = image[r][c].getBlue();

                // construct a new pixel with the same red and green but no blue
                newImg[r][c] = new Color(red, green, 0);
            }
        }

        return new Image(newImg);
    }

    public Image removeRed() {
        Color[][] newImg = new Color[image.length][image[0].length];

        // loop through all pixels
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {

                // get component parts of pixel's color
                int red = image[r][c].getRed();
                int green = image[r][c].getGreen();
                int blue = image[r][c].getBlue();

                // construct a new pixel with the same red and green but no blue
                newImg[r][c] = new Color(0, green, blue);
            }
        }

        return new Image(newImg);
    }

    public Image removeGreen() {
        Color[][]newImg = new Color[image.length][image[0].length];
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                int red = image[r][c].getRed();
                int blue = image[r][c].getBlue();
                int green = image[r][c].getGreen();

                newImg[r][c] = new Color(red, 0, blue);
            }
        }
        return new Image(newImg);
    }

    public Image blackWhite() {
        Color[][] newImg = new Color[image.length][image[0].length];

        // loop through all pixels
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {

                // get component parts of pixel's color
                int total = (image[r][c].getBlue() + image[r][c].getGreen() + image[r][c].getRed()) / 3;
                // construct a new pixel with the same red and green but no blue
                newImg[r][c] = new Color(total, total, total);
            }
        }

        return new Image(newImg);
    }

    public Image invertColor() {
        Color[][] newImg = new Color[image.length][image[0].length];

        // loop through all pixels
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {

                // get component parts of pixel's color
                int red = 255 - image[r][c].getRed();
                int green = 255 - image[r][c].getGreen();
                int blue = 255 - image[r][c].getBlue();

                // construct a new pixel with the same red and green but no blue
                newImg[r][c] = new Color(red, green, blue);
            }
        }

        return new Image(newImg);
    }

    public Image flipHorizontal() {
        Color[][] newImg = new Color[image.length][image[0].length];
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                int red = image[r][c].getRed();
                int blue = image[r][c].getBlue();
                int green = image[r][c].getGreen();

                newImg[r][image[r].length - 1 - c] = new Color(red, green, blue);
            }
        }
        return new Image(newImg);
    }

    public Image mirrorVertical() {
        Color[][] newImg = new Color[image.length][image[0].length];
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                int red = image[r][c].getRed();
                int blue = image[r][c].getBlue();
                int green = image[r][c].getGreen();
                int re = image[r][image[r].length - 1 - c].getRed();
                int g = image[r][image[r].length - 1 - c].getGreen();
                int b = image[r][image[r].length - 1 - c].getBlue();

                if (c <= image[r].length / 2) {
                    newImg[r][c] = new Color(red, green, blue);
                } else {

                    newImg[r][c] = new Color(re, g, b);
                }
            }
        }
        return new Image(newImg);
    }
    public Image mirrorHorizontal() {
        Color[][] newImg = new Color[image.length][image[0].length];
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                int red = image[r][c].getRed();
                int blue = image[r][c].getBlue();
                int green = image[r][c].getGreen();
                int re = image[image.length - 1 - r][c].getRed();
                int g = image[image.length - 1 - r][c].getGreen();
                int b = image[image.length - 1 - r][c].getBlue();

                if (r <= image.length / 2) {
                    newImg[r][c] = new Color(red, green, blue);
                } else {

                    newImg[r][c] = new Color(re, g, b);
                }
            }
        }
        return new Image(newImg);
    }

    public Image blurImage() {
        Color[][] newImg = new Color[image.length][image[0].length]; // image object
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                if (r != 0 && c != 0 && r != image.length - 1 && c != image[r].length - 1) {
                    int red = image[r][c].getRed() + image[r + 1][c].getRed() + image[r - 1][c].getRed()
                            + image[r][c - 1].getRed() + image[r][c + 1].getRed() + image[r + 1][c + 1].getRed()
                            + image[r - 1][c - 1].getRed() + image[r - 1][c + 1].getRed()
                            + image[r + 1][c - 1].getRed();
                    int blue = image[r][c].getBlue() + image[r + 1][c].getBlue() + image[r - 1][c].getBlue()
                            + image[r][c - 1].getBlue() + image[r][c + 1].getBlue() + image[r + 1][c + 1].getBlue()
                            + image[r - 1][c - 1].getBlue() + image[r - 1][c + 1].getBlue()
                            + image[r + 1][c - 1].getBlue();
                    int green = image[r][c].getGreen() + image[r + 1][c].getGreen() + image[r - 1][c].getGreen()
                            + image[r][c - 1].getGreen() + image[r][c + 1].getGreen() + image[r + 1][c + 1].getGreen()
                            + image[r - 1][c - 1].getGreen() + image[r - 1][c + 1].getGreen()
                            + image[r + 1][c - 1].getGreen();
                    int x = 9;
                    newImg[r][c] = new Color(red / x, green / x, blue / x);
                } else {
                    newImg[r][c] = image[r][c];
                }
            }
        }
        return new Image(newImg);
    }

    public Image edgeDetection() {
        Color[][] newImg = new Color[image.length][image[0].length];
        int x = 2;//higher = less sensitive; lower = more sensitive
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[r].length; c++) {
                int red = image[r][c].getRed();
                int blue = image[r][c].getBlue();
                int green = image[r][c].getGreen();
                if(r < image.length - 1 && c < image[r].length) {
                    if(image[r][c].getRed() - image[r + 1][c].getRed() > x || image[r][c].getRed() - image[r +  1][c].getRed() < -x ) {//
                        if(image[r][c].getBlue() - image[r +  1][c].getBlue() > x || image[r][c].getRed() - image[r +  1][c].getRed() < -x ) {
                            if(image[r][c].getGreen() - image[r +  1][c].getGreen() > x || image[r][c].getRed() - image[r +  1][c].getRed() < -x ) {
                                newImg[r][c] = new Color(0, 0, 0);
                            }
                            else {
                                newImg[r][c] = new Color(255, 255, 255);
                            }
                        }
                        else {
                            newImg[r][c] = new Color(255, 255, 255);
                        }
                    }
                    else {
                        newImg[r][c] = new Color(255, 255, 255);
                    }
                }
                else {
                    newImg[r][c] = new Color(red, green, blue);
                }
            }
        }
        return new Image (newImg);
    }























}