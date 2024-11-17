package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws WriterException, IOException {

        // start the QR generator here:
//          This is the successful original code block
//        String website = "https://regional-weather-v2.netlify.app";
//        String path = "C://users/kenge/Desktop/QR/weather.png";
//        BitMatrix matrix = new MultiFormatWriter().encode(website, BarcodeFormat.QR_CODE, 500, 500);
//        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(path));
//        System.out.println("QR Code successfully created!");

        String webpage = "https://regional-weather-v2.netlify.app";
        String path = "C://users/kenge/Desktop/QR/weather1.png";
        String logoPath = "C://Users/kenge/Pictures/logo-initials.png";
        // qr with image
        // creates the new qr code and buffers it into the BufferedImage variable qrCodeImage


        // This makes a far more detailed QR code quality
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = null;


            try {
                bitMatrix = qrCodeWriter.encode(webpage, BarcodeFormat.QR_CODE, 1000, 1000, hints);
            } catch (WriterException ex) {
                //throw new RuntimeException(ex);
                System.out.println("Error encoding the new QR code" + ex.getMessage());
            }
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // load the logo for the qr
            try {
                BufferedImage logoImage = ImageIO.read(new File(logoPath));
                int deltaHeight = qrCodeImage.getHeight() - logoImage.getHeight();
                int deltaWidth = qrCodeImage.getWidth() - logoImage.getWidth();

                Graphics2D g = qrCodeImage.createGraphics();
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

                g.drawImage(logoImage, deltaWidth / 2, deltaHeight / 2, logoImage.getWidth(), logoImage.getHeight(), null);
                g.dispose();

                ImageIO.write(qrCodeImage, "png", new File(path));

                System.out.println("QR with Image created successfully");
            } catch (IOException ex) {
                //throw new RuntimeException(ex);
                System.out.println("Error generating the QR with Image " + ex.getMessage());
            }

        }


    }
}