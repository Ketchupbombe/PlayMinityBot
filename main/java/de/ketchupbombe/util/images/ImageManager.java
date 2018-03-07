package de.ketchupbombe.util.images;

import de.ketchupbombe.util.FileManager;
import de.ketchupbombe.util.KetchupLogger;

import java.io.*;
import java.net.URL;

/**
 * @author Ketchupbombe
 * @version 1.0
 */
public class ImageManager {

    public static void init() {
        for (ImageCategory category : ImageCategory.values()) {
            File path = new File("images" + System.getProperty("file.separator"));
            File file = new File("images" + System.getProperty("file.separator") + category.path + ".txt");
            if (!path.exists()) {
                path.mkdirs();
                KetchupLogger.log(KetchupLogger.LogType.INFO, "Created directory \'" + path.getName() + "\'");
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    KetchupLogger.log(KetchupLogger.LogType.INFO, "Created file \'" + file.getName() + "\'");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Deprecated
    public static void downloadImage(String url, String direction) throws IOException {
        URL imageUrl = new URL(url);
        InputStream in = imageUrl.openStream();
        OutputStream os = new FileOutputStream(direction);
        byte[] b = new byte[2048];
        int length;

        while ((length = in.read(b)) != -1) {
            os.write(b, 0, length);
        }
        in.close();
        os.close();
    }

    public static void registerNewImage(ImageCategory category, String url) {
        File file = new File("images" + System.getProperty("file.separator") + category.path + ".txt");
        new FileManager(file).writeLine(url);
    }

}
