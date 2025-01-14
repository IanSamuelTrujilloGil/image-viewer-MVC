package software.ulpgc.imageviewer.mvc.apps.windows;

import software.ulpgc.imageviewer.mvc.architecture.io.ImageDeserializer;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SwingImageDeserializer implements ImageDeserializer {
    @Override
    public Object deserialize(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
