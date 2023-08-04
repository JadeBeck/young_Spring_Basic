package hello.core;

import java.io.File;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;


public class Program {

    public static void main(String[] args) {
        String str = "123412341234";
        try {
            Barcode barcode = BarcodeFactory.createCode128(str);
            File file = new File("barcode1.png");

            BarcodeImageHandler.savePNG(barcode, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
