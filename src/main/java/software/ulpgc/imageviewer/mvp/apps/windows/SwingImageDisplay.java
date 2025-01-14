package software.ulpgc.imageviewer.mvp.apps.windows;

import software.ulpgc.imageviewer.mvp.architecture.io.ImageDeserializer;
import software.ulpgc.imageviewer.mvp.architecture.view.ImageDisplay;
import software.ulpgc.imageviewer.mvp.architecture.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private final List<PaintOrder> paintOrderList;
    private Shift shift = Shift.NULL;
    private Released released = Released.NULL;
    private int initialX;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.paintOrderList = new ArrayList<>();
        this.deserializer = deserializer;
        this.addMouseListener(createMouseAdapter());
        this.addMouseMotionListener(createMouseMotionAdapter());
    }

    private MouseMotionAdapter createMouseMotionAdapter() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initialX);
            }
        };
    }

    private MouseAdapter createMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initialX);
                shift.offset(0);
            }
        };
    }


    private static final Color BACKGROUND_COLOR = getBackgroundColor();

    private static Color getBackgroundColor() {
        return Color.decode("#2C3E50");
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        paintOrderList.forEach(o -> paintByOrder(graphics, o));
    }


    private Image deserialize(byte[] content) {
        return (Image) deserializer.deserialize(content);
    }

    private void paintByOrder(Graphics graphics, PaintOrder paintOrder) {
        Image image = deserialize(paintOrder.content());
        ViewPort viewPort = ViewPort.createWithSize(this.getWidth(), this.getHeight())
                .adjustToFit(image.getWidth(null), image.getHeight(null));
        graphics.drawImage(image,viewPort.x() + paintOrder.offset(), viewPort.y(),viewPort.width(), viewPort.height(), null);
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public void paint(PaintOrder... orders) {
        this.paintOrderList.clear();
        Collections.addAll(this.paintOrderList, orders);
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    @Override
    public void on(Released released) {
        this.released  =released;
    }
}
