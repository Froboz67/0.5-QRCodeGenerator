package org.example;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class QRwithImage {


    String webpage = "https://regional-weather-v2.netlify.app";
    String path = "C://users/kenge/Desktop/QR/weather.png";
    String logoPath = "C://Users/kenge/Pictures/logo-pic.png";
    // qr with image
    // creates the new qr code and buffers it into the BufferedImage variable qrCodeImage


    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;


        try {
            bitMatrix = qrCodeWriter.encode(webpage, BarcodeFormat.QR_CODE, 500, 500);
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

            g.drawImage(logoImage, deltaWidth / 2, deltaHeight / 2, null);
            g.dispose();

            ImageIO.write(qrCodeImage, "png", new File(path));

            System.out.println("QR with Image created successfully");
        } catch (IOException ex) {
            //throw new RuntimeException(ex);
            System.out.println("Error generating the QR with Image " + ex.getMessage());
        }

    }



}
