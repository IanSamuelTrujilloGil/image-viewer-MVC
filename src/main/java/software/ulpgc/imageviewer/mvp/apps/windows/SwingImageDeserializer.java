package software.ulpgc.imageviewer.mvp.apps.windows;

import software.ulpgc.imageviewer.mvp.architecture.io.ImageDeserializer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class SwingImageDeserializer implements ImageDeserializer {
    private final Map<Integer, BufferedImage> memoize;

    public SwingImageDeserializer() {
        this.memoize = initializeImageCache();
    }

    @Override
    public BufferedImage deserialize(byte[] bytes) {
        return memoize.computeIfAbsent(Arrays.hashCode(bytes), key -> convertBytesToImage(bytes));
    }

    public BufferedImage convertBytesToImage(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Integer, BufferedImage> initializeImageCache() {
        return new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, BufferedImage> eldest) {
                return size() > 100;
            }
        };
    }


}
