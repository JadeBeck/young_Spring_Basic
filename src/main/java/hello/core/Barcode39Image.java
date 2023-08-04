package hello.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class Barcode39Image{

    // FIX �대�吏� 湲곕낯 �볦씠(諛붿퐫�쒓린以� �ъ씠利덈줈 蹂��뺣릺湲곕븣臾몄뿉 1�댁긽�대㈃��)
    private static int IMAGE_WIDTH = 230;
    // FIX �대�吏� 湲곕낯 �믪씠(諛붿퐫�쒓린以� �ъ씠利덈줈 蹂��뺣릺湲곕븣臾몄뿉 1�댁긽�대㈃��)
    private static int IMAGE_HEIGHT = 70;

    // 諛붿퐫�� 湲곕낯 �볦씠
    private static int BARCODE_WIDTH = 2;
    // 諛붿퐫�� 湲곕낯 �믪씠
    private static int BARCODE_HEIGHT = 50;
    // 諛곌꼍�� RGB 以� R 媛�
    private static int COLOR_R = 255;
    // 諛곌꼍�� RGB 以� G 媛�
    private static int COLOR_G = 255;
    // 諛곌꼍�� RGB 以� B 媛�
    private static int COLOR_B = 255;
    // 諛곌꼍 �щ챸 泥섎━(遺덊닾紐� 0, �щ챸 1)
    private static Boolean TYPE_TRANS = false;
    // �대�吏� ����[BASE64 - png(�щ챸泥섎━媛���), jpeg & SAVE - png, jpeg , gif]
    private static String TYPE_IMAGE = "png";
    // ���� �꾩껜 寃쎈줈 + �뚯씪紐�
    private static String SAVE_FULL_PATH = "";


    /**
     諛붿퐫�� 媛꾨떒�� �⑥닔

     1.  �쇰꺼 吏��뺥븯湲�           - barcode.setLabel("cjbox.tistory.com");
     2.  �띿뒪�� �덈낫�닿쾶 �섍린 - barcode.setDrawingText(false);
     3.  �볦씠 議곗젅�섍린           - barcode.setBarWidth(2);  // 1~2 �곷떦
     4.  �믪씠 議곗젅�섍린           - barcode.setBarHeight(50); // 50 �곷떦 
     5.  諛곌꼍�� 蹂�寃쏀븯湲�        - barcode.setBackground(Color.GREEN);
     6.  諛붿퐫�쒖깋 蹂�寃쏀븯湲�     - barcode.setForeground(Color.RED);

     */


    /**
     * <pre>
     * 諛붿퐫�� �대�吏� ����
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param savePath    �대�吏� ���� �꾩튂
     * @throws BarcodeException
     * @throws OutputException
     */
    public static void saveBarcodeImage(String barcodeText, String savePath) throws BarcodeException, OutputException{
        TYPE_TRANS = false;
        SAVE_FULL_PATH = savePath + "/" + barcodeText + "."+ TYPE_IMAGE;

        createBarcodeImage(barcodeText);
    }

    /**
     * <pre>
     * 諛붿퐫�� �대�吏� ����
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param savePath    �대�吏� ���� �꾩튂
     * @param typeImage   �대�吏� ����(png, jpeg , gif)
     * @throws BarcodeException
     * @throws OutputException
     */
    public static void saveBarcodeImage(String barcodeText, String savePath, String typeImage) throws BarcodeException, OutputException{
        TYPE_IMAGE = typeImage;
        TYPE_TRANS = false;
        SAVE_FULL_PATH = savePath + "/" + barcodeText + "."+ TYPE_IMAGE;

        createBarcodeImage(barcodeText);
    }

    /**
     * <pre>
     * 諛붿퐫�� �대�吏� ����
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param savePath    �대�吏� ���� �꾩튂
     * @param transType   諛곌꼍 �щ챸(0-遺덊닾紐�,1-�щ챸)
     * @throws BarcodeException
     * @throws OutputException
     */
    public static void saveBarcodeImage(String barcodeText, String savePath, Boolean transType ) throws BarcodeException, OutputException{
        TYPE_TRANS = transType;

        SAVE_FULL_PATH = savePath + "/" + barcodeText + "."+ TYPE_IMAGE;

        createBarcodeImage(barcodeText);
    }


    /**
     * <pre>
     * 諛붿퐫�� �대�吏� ����
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param savePath    �대�吏� ���� �꾩튂
     * @param boacodeWidth  諛붿퐫�� �볦씠
     * @param boacodeHeight 諛붿퐫�� �믪씠
     * @throws BarcodeException
     * @throws OutputException
     */
    public static void saveBarcodeImage(String barcodeText, String savePath, int boacodeWidth, int boacodeHeight ) throws BarcodeException, OutputException{
        TYPE_TRANS = false;
        BARCODE_WIDTH = boacodeWidth;
        BARCODE_HEIGHT = boacodeHeight;

        SAVE_FULL_PATH = savePath + "/" + barcodeText + "."+ TYPE_IMAGE;

        createBarcodeImage(barcodeText);
    }



    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦
     * </pre>
     *
     * @param barcodeText   諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @throws BarcodeException
     * @throws OutputException
     */
    public static void createBarcodeImage(String barcodeText) throws BarcodeException, OutputException{

        Barcode barcode = null;
        BufferedImage image = null;
        Graphics2D g = null;

        barcode = BarcodeFactory.createCode39(barcodeText, false);
        barcode.setBarWidth(BARCODE_WIDTH);
        barcode.setBarHeight(BARCODE_HEIGHT);
        image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        g = (Graphics2D) image.getGraphics();
        barcode.draw(g, 10, 56);
        g.dispose();

        try {
            if(TYPE_TRANS){//諛붿퐫�� �щ챸 泥섎━
                BufferedImage source = BarcodeImageHandler.getImage(barcode);
                Image imageTrans = makeColorTransparent(source, new Color(COLOR_R,COLOR_G,COLOR_B));
                image = imageToBufferedImage(imageTrans);
                ImageIO.write(image, TYPE_IMAGE, new File(SAVE_FULL_PATH));
            }else{
                if(TYPE_IMAGE.toLowerCase().equals("png")){
                    BarcodeImageHandler.savePNG(barcode, new File(SAVE_FULL_PATH));
                }else if(TYPE_IMAGE.toLowerCase().equals("gif")){
                    BarcodeImageHandler.saveGIF(barcode, new File(SAVE_FULL_PATH));
                }else if(TYPE_IMAGE.toLowerCase().equals("jpeg") || TYPE_IMAGE.toLowerCase().equals("jpg")){
                    BarcodeImageHandler.saveJPEG(barcode, new File(SAVE_FULL_PATH));
                }else{
                    BarcodeImageHandler.savePNG(barcode, new File(SAVE_FULL_PATH));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦 Base64 臾몄옄��
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @throws Exception
     */
    public static String getBarcodeBase64(String barcodeText) throws Exception{
        TYPE_TRANS = false;
        return createBarcodeImageBase64(barcodeText);
    }

    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦 Base64 臾몄옄��
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param transType   諛곌꼍 �щ챸(0-遺덊닾紐�,1-�щ챸)
     * @throws Exception
     */
    public static String getBarcodeBase64(String barcodeText, Boolean transType) throws Exception{

        TYPE_TRANS = transType;

        return createBarcodeImageBase64(barcodeText);
    }


    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦 Base64 臾몄옄��
     * </pre>
     *
     * @param barcodeText 諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param boacodeWidth  諛붿퐫�� �볦씠
     * @param boacodeHeight 諛붿퐫�� �믪씠
     * @throws Exception
     */
    public static String getBarcodeBase64(String barcodeText, int boacodeWidth, int boacodeHeight ) throws Exception{
        TYPE_TRANS = false;
        BARCODE_WIDTH = boacodeWidth;
        BARCODE_HEIGHT = boacodeHeight;

        return createBarcodeImageBase64(barcodeText);
    }


    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦 Base64 臾몄옄��
     * </pre>
     *
     * @param barcodeText   諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @param transType     諛곌꼍 �щ챸(0-遺덊닾紐�,1-�щ챸)
     * @param boacodeWidth  諛붿퐫�� �볦씠
     * @param boacodeHeight 諛붿퐫�� �믪씠
     * @throws Exception
     */
    public static String getBarcodeBase64(String barcodeText, Boolean transType, int boacodeWidth, int boacodeHeight ) throws Exception{

        TYPE_TRANS = transType;
        BARCODE_WIDTH = boacodeWidth;
        BARCODE_HEIGHT = boacodeHeight;

        return createBarcodeImageBase64(barcodeText);
    }

    /**
     * <pre>
     * 諛붿퐫�� �대�吏� �앹꽦(Base64 Encode)
     * </pre>
     *
     * @param barcodeText   諛붿퐫�� 臾몄옄��
     * @throws Exception
     */
    public static String createBarcodeImageBase64(String barcodeText) throws Exception{

        Barcode barcode = null;
        BufferedImage image = null;
        Graphics2D g = null;

        barcode = BarcodeFactory.createCode39(barcodeText, false);
        barcode.setBarWidth(BARCODE_WIDTH);
        barcode.setBarHeight(BARCODE_HEIGHT);
        image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, BARCODE_WIDTH, BARCODE_HEIGHT);
        barcode.setBackground(new Color(COLOR_R, COLOR_G, COLOR_B));
        barcode.draw(g, 10, 56);

        g.dispose();
        return encodeToString(BarcodeImageHandler.getImage(barcode));
    }

    /**
     * <pre>
     * �대�吏� Base64 �몄퐫�� 蹂���
     * </pre>
     *
     * @param barcodeText   諛붿퐫�� 蹂��섑븷 臾몄옄��
     * @throws Exception
     */
    public static String encodeToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            if(TYPE_TRANS){
                BufferedImage source = image;
                //諛붿퐫�� �щ챸 泥섎━
                Image imageTrans = makeColorTransparent(source, new Color(COLOR_R,COLOR_G,COLOR_B));
                image = imageToBufferedImage(imageTrans);
            }

            ImageIO.write(image, TYPE_IMAGE, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = new String( Base64.encode( imageBytes ) );
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/" + TYPE_IMAGE + ";base64," + imageString;
    }


    /**
     * <pre>
     * �대�吏� 諛곌꼍 �щ챸泥섎━�� �대�吏� ���� 蹂�寃�
     * </pre>
     *
     * @param image      �대�吏�
     */
    private static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    /**
     * <pre>
     * �대�吏� 諛곌꼍 �щ챸 泥섎━
     * </pre>
     *
     * @param im      �대�吏�
     * @param color   而щ윭 肄붾뱶
     */
    public static Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

}