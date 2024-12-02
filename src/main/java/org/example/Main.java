package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
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
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("");
        LocalDate today = LocalDate.now();
        Month month = today.getMonth();
        int day = today.getDayOfMonth();
        int year = today.getYear();
        System.out.println("Today is " + month + " " + day + ", " + year);
        System.out.println("-----------------------------------------");
        System.out.println();


        System.out.println("QR Code Generator - v1.1");
        System.out.print("Please paste the URL for the QR Code: ");
        String webpage = input.nextLine();
        System.out.println("entered");
        System.out.print("Please enter the QR image file name: ");
        String filename = input.nextLine();


        String path = "D:/Kevin_Docs/Engel_Docs/Tech_Elevator/workspace/GitHub/3.5-QRCodeGenerator/qr-codes/" + filename + ".png";
        String logoPath = "D:/Kevin_Docs/Engel_Docs/Tech_Elevator/workspace/GitHub/3.5-QRCodeGenerator/qr-codes/logo-initials.png";

        // qr with image
        // creates the new qr code and buffers it into the BufferedImage variable qrCodeImage

        /*
         The Map,EncodeHintType> makes a far more detailed QR code quality
         ErrorCorrectionLevel. (H)igh (M)edium (L)ow useful for QR's with added graphics provides
         different levels of correction
         */

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = null;


            try {
                bitMatrix = qrCodeWriter.encode(webpage, BarcodeFormat.QR_CODE, 1000, 1000, hints);
            } catch (WriterException e) {

                System.out.println("Error encoding the new QR code" + e.getMessage());
            }
            assert bitMatrix != null;
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            
            /*
            try block inserts a small logo into the center of the QR logoImage
             */
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

            } catch (IOException e) {
                System.out.println("Error generating the QR with Image " + e.getMessage());
            }

        }


    }
}