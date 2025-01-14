package software.ulpgc.imageviewer.mvc.apps.windows;
import software.ulpgc.imageviewer.mvc.architecture.io.ImageDeserializer;
import software.ulpgc.imageviewer.mvc.architecture.model.Image;
import software.ulpgc.imageviewer.mvc.architecture.view.ImageDisplay;
import software.ulpgc.imageviewer.mvc.architecture.view.ViewPort;
import javax.swing.*;
import java.awt.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image currentImage;
    private final ImageDeserializer deserializer;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    private static final Color BACKGROUND_COLOR = getBackgroundColor();

    private static Color getBackgroundColor() {
        return Color.decode("#2C3E50");
    }

    public Image getImage() {
        return currentImage;
    }

    @Override
    public void display(Image image) {
        this.currentImage = image;
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawImage(graphics);
    }

    private java.awt.Image deserialize() {
        return (java.awt.Image) deserializer.deserialize(currentImage.content());
    }

    private void drawImage(Graphics graphics) {
        java.awt.Image image = deserialize();
        ViewPort viewPort = ViewPort.createWithSize(this.getWidth(), this.getHeight())
                .adjustToFit(image.getWidth(null), image.getHeight(null));
        graphics.drawImage(image,viewPort.x(), viewPort.y(),viewPort.width(), viewPort.height(), null);
    }


}
