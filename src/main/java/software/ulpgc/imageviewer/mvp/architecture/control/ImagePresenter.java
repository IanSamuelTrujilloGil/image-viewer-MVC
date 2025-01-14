package software.ulpgc.imageviewer.mvp.architecture.control;

import software.ulpgc.imageviewer.mvp.architecture.model.Image;
import software.ulpgc.imageviewer.mvp.architecture.view.ImageDisplay;

public class ImagePresenter {
    private Image currentImage;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.imageDisplay.on(createShiftHandler());
        this.imageDisplay.on(createReleaseHandler());
    }

    private ImageDisplay.Released createReleaseHandler() {
        return this::updateCurrentImageBasedOnOffset;
    }

    private static boolean isOffsetForPreviousImage(int offset) {
        return offset > 0;
    }

    private boolean shouldKeepCurrentImage(int offset) {
        return Math.abs(offset) < imageDisplay.width() / 2;
    }

    private ImageDisplay.Shift createShiftHandler() {
        return o -> imageDisplay.paint(
                createPaintOrderForCurrentImage(o),
                isOffsetForPreviousImage(o) ?
                        createPaintOrderForPreviousImage(o - imageDisplay.width() ) :
                        createPaintOrderForNextImage(imageDisplay.width() + o)
        );
    }

    private void updateCurrentImageBasedOnOffset(int offset) {
        if (shouldKeepCurrentImage(offset)) return;
        currentImage = isOffsetForPreviousImage(offset) ? currentImage.previous() : currentImage.next();
    }


    private ImageDisplay.PaintOrder createPaintOrderForNextImage(int offset) {
        return new ImageDisplay.PaintOrder(currentImage.next().content(), offset);
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    private ImageDisplay.PaintOrder createPaintOrderForPreviousImage(int offset) {
        return new ImageDisplay.PaintOrder(currentImage.previous().content(), offset);
    }

    public void display(Image image) {
        this.currentImage = image;
        imageDisplay.paint(createPaintOrderForCurrentImage(0));
    }

    private ImageDisplay.PaintOrder createPaintOrderForCurrentImage(int offset) {
        return new ImageDisplay.PaintOrder(currentImage.content(), offset);
    }
}
