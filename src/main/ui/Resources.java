package main.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Resources {
    private static File RESOURCES_DIRECTORY;
    private static HashMap<String, BufferedImage> IMAGES;

    static {
        RESOURCES_DIRECTORY = new File("resources/");
        IMAGES = new HashMap<>();
    }

    public static void initImages() {
        for(Images i : Images.values()) {
            loadImage(i);
        }
    }

    public static void loadImage(String identifier, String path) {
        File file = new File(RESOURCES_DIRECTORY + "/images/" + path);

//        if(!file.exists() || file.isDirectory()) {
//            System.err.println("Image " + identifier + " could not be found at " + file.getAbsolutePath());
//            return;
//        }

        try {
            IMAGES.put(identifier, ImageIO.read(file));
        } catch (IOException e) {
            System.err.println("Failed to load image " + identifier + "." + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public static void loadImage(Images image) {
        String name = image.name().toLowerCase();
        loadImage(image.name().toLowerCase(), image.getDir() + name + "." + image.getExtension());
    }

    public static void loadImages(String dirPath) {
        File dir = new File(dirPath);

        if(!dir.exists() || !dir.isDirectory()) {
            System.err.println("Failed to load images at " + dir.getAbsolutePath() + ": does not exists or not a directory");
            return;
        }

        for(File file : dir.listFiles()) {
            loadImage(file.getName(), file.getAbsolutePath().replaceAll(RESOURCES_DIRECTORY.getAbsolutePath(), ""));
        }
    }

    public BufferedImage getImage(String identifier) {
        return IMAGES.get(identifier);
    }

    public enum Images {
        ATHLETIC_TRAIT("traits/"),
        PREGNANT_TRAIT("traits/"),
        ;

        private String dir;
        private String extension;

        Images(String dir) {
            this(dir, "png");
        }

        Images(String dir, String extension) {
            this.dir = dir;
            this.extension = extension;
        }

        public String getDir() {
            return dir;
        }

        public String getExtension() {
            return extension;
        }

        public BufferedImage get() {
            return IMAGES.get(name().toLowerCase());
        }
    }
}
