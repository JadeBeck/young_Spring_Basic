package hello.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import hello.core.Barcode39Image;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
@AllArgsConstructor
public class ProgramService {

    public String getBarcode(String patientId) {
        String str = patientId;
        String imgBase64 ="";
        try {
//            Barcode barcode = BarcodeFactory.createCode128(str);
//            File file = new File("barcode1.png");
//
//            BarcodeImageHandler.savePNG(barcode, file);

            Barcode39Image barcode = new Barcode39Image();
            imgBase64 = barcode.getBarcodeBase64(str, 2, 60);
            System.out.println("<img src=\"" + imgBase64 + "\" />"  );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgBase64;
    }
}
