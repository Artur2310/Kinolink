package ru.kinolink.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public abstract class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    protected String category;

    public String getPath(Integer id, String uploadDir) {

        Integer two = (id % 1000000) / 1000;
        Integer one = (id % 1000000000) / 1000000;

        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        uploadDir = uploadDir + "/" + category;

        dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        uploadDir = uploadDir + "/" + String.format("%03d", one);

        dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        uploadDir = uploadDir + "/" + String.format("%03d", two);

        dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return uploadDir;
    }

    public static byte[] formatImage(byte[] file) {


        try {

            ByteArrayInputStream bais = new ByteArrayInputStream(file);
            BufferedImage input = ImageIO.read(bais);

            double width = input.getWidth();
            double height = input.getHeight();
            double param = width / height;

            if (param > 0.715) {

                double nWidth = height * 0.715;
                int x = (int) (width / 2 - nWidth / 2);
                input = input.getSubimage(x, 0, (int) nWidth, (int) height);

            }
            if (param < 0.715) {
                double nHeight = width * 0.715;
                int y = (int) (height / 2 - nHeight / 2);
                input.getSubimage(0, y, (int) width, (int) nHeight);
            }


            int nWidth = 261;
            int nHeight = 365;
            Image nImage = input.getScaledInstance(nWidth, nHeight, Image.SCALE_AREA_AVERAGING);
            input = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = input.createGraphics();
            g2d.drawImage(nImage, 0, 0, null);
            g2d.dispose();

            Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
            if (iter.hasNext()) {
                ImageWriter writer = (ImageWriter) iter.next();
                ImageWriteParam iwp = writer.getDefaultWriteParam();
                iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                float value = 0.75f;
                // Write one for each compression values

                iwp.setCompressionQuality(value);

                BufferedImage buf = new BufferedImage(261, 365, BufferedImage.TYPE_INT_RGB);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();

                ImageOutputStream ios;

                ios = ImageIO.createImageOutputStream(bytes);

                writer.setOutput(ios);
                IIOImage image = new IIOImage(input, null, null);

                writer.write(null, image, iwp);

                return bytes.toByteArray();
            }

            return null;

        } catch (IOException e) {

            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return null;

    }

    // Save the image to the server
    public String saveImage(Integer id, byte[] file, String uploadDir) throws IOException {

        byte[] bytes = ImageUtil.formatImage(file);
        String dir = this.getPath(id, uploadDir);

        String name = id.toString() + ".jpg";

        String path = dir + "/" + name;
        File uploadedFile = new File(path);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return path;
    }

    public static void deleteImage(String path) {
        File image = new File(path);
        if (image.exists()) {
            image.delete();
        } else {
            logger.info("Image " + path + " not exist!");
        }
    }

    public void deleteAllImages() {
        String path = "image/";

        File dir = new File(path);

        if (!dir.exists()) return;

        path = path + category;

        dir = new File(path);

        deleteDir(dir);


    }

    private void deleteDir(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                deleteDir(f);
            file.delete();
        } else {
            file.delete();
        }
    }
}
