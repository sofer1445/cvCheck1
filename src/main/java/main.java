import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.*;
public class main {

    public static void main(String[] args) {
    readingFile readingFile = new readingFile();

    }
    //import javax.imageio.ImageIO;
    //import javax.swing.*;
    //import java.awt.*;
    //import java.awt.image.BufferedImage;
    //import java.io.File;
    //import java.io.IOException;
    //
    //public class Main {
    //
    //    public static void main(String[] args) {
    //        try {
    //            File imageFile = new File("C:\\Users\\noymi\\OneDrive\\שולחן העבודה\\image\\catboy.jpg");
    //            if (imageFile.exists()) {
    //                System.out.println("File found");
    //            } else {
    //                System.out.println("Worng path!");
    //            }
    //
    //            BufferedImage catBoy1 = ImageIO.read(imageFile); // קוראת מתוך הקובץ את התוכן והופכת את זה לתמונה
    //            // אובייקט שמייצג את התכונות של התמונה
    //            System.out.println("Width: " + catBoy1.getWidth());
    //            System.out.println("Height:" + catBoy1.getHeight());
    //            int pixelColor = catBoy1.getRGB(300,50);
    //            Color color = new Color(pixelColor);
    //            System.out.println("RED : " + color.getRed());
    //            System.out.println("GREEN : " + color.getGreen());
    //            System.out.println("BLUE : " + color.getBlue());
    //        //    Color redColor = Color.RED;
    //          //  catBoy.setRGB(10,10, redColor.getRGB()); // שינוי צבע
    ////            File file = new File("C:\\Users\\noymi\\OneDrive\\שולחן העבודה\\image\\catboy.jfif");
    ////            ImageIO.write(catBoy , "png", file);
    //            File file = new File("C:\\Users\\noymi\\OneDrive\\שולחן העבודה\\image\\catboy2.jpg");
    //            ImageIO.write(catBoy1, "jpg", file);
    //            Color backgroundColor = new Color(catBoy1.getRGB(0,0));
    //
    //
    //
    //            for (int x = 0 ; x < catBoy1.getWidth(); x++) {
    //                for (int y = 0; y < catBoy1.getWidth(); y += 2) {
    //                    Color currentColor = new Color(catBoy1.getRGB(x , y));
    //                    if (currentColor.equals(backgroundColor)){
    //                        catBoy1.setRGB(x , y ,Color.GREEN.getRGB());
    //                    }
    //
    //             //       catBoy.setRGB(x, 10, redColor.getRGB());
    //                }
    //
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
    //
    //    public static boolean isSimilarColor (Color color1 , Color color2) {
    //        boolean similar = false ;
    //
    //        int redDiff = color1.getRed() - color2.getRed();
    //        int greenDiff = color1.getGreen() - color2.getGreen();
    //        int blueDiff = color1.getBlue() - color2.getBlue();
    //        if (redDiff + greenDiff+ blueDiff < 40 ){
    //            similar= true;
    //
    //        } return similar;
    //    }
    //}
    //            System.out.println("Width:" + imageFile.getWidth()); //1350
//            System.out.println("Height:" + imageFile.getHeight()); //700
//            int pixelColor =  imageFile.getRGB(750,350);
//            Color color = new Color(pixelColor);
//            System.out.println("red:" + color.getRed());
//            System.out.println("blue:" + color.getBlue());
//            System.out.println("green:" + color.getGreen());
//            Color redRGB = Color.RED;
//            imageFile.setRGB(0,0,redRGB.getRGB());
}
