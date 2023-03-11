package PixLab;

public class  PixRunner {

    public static void main(String[] args) {
        // create image from file and display it
        PixLab.Image i = new PixLab.Image("ssbw.png");
        i.display("Black");
        i.removeBlue().display("No Blue");
        i.removeRed().display("No red");
        i.removeGreen().display("No green");
        i.blackWhite().display("Black and White");
        i.invertColor().display("Invert Color");
        i.flipHorizontal().display("Flipped Horizontally");
        i.mirrorVertical().display("Mirrored Vertically");
        i.mirrorHorizontal().display("Mirrored Horizontally");
        i.blurImage().display("Blurred");
        i.edgeDetection().display("Edges");
    }
}
