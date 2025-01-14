package software.ulpgc.imageviewer.mvp.architecture.view;

public record ViewPort(int x, int y, int width, int height) {
    public static ViewPort createWithSize(int width, int height){
        return new ViewPort(0,0,width,height);
    }

    public ViewPort adjustToFit(int width, int height) {
        if(isWithinBounds(width, height)) return centeredViewPort(width, height);
        return shouldAdjustWidth(width, height) ? resizeWidth(width, height): resizeHeight(width, height);
    }

    private ViewPort resizeHeight(int width, int height) {
        return new ViewPort(centerHorizontal(calculateWidth(width, height)), 0, calculateWidth(width, height), this.height);
    }

    private int centerHorizontal(int width) {
        return (this.width - width) / 2;
    }

    private int calculateWidth(int width, int height) {
        return this.height*width/height;
    }

    private ViewPort resizeWidth(int width, int height) {
        return new ViewPort(0, centerVertical(calculateHeight(width, height)), this.width, calculateHeight(width, height));
    }

    private int centerVertical(int height) {
        return (this.height - height) / 2;
    }

    private int calculateHeight(int width, int height) {
        return this.width*height/width;
    }

    private boolean shouldAdjustWidth(int width, int height) {
        return aspectRatio(width, height) > viewPortAspectRatio();
    }

    private double viewPortAspectRatio() {
        return aspectRatio(this.width, this.height);
    }

    private double aspectRatio(int width, int height) {
        return (double) width /height;
    }

    private ViewPort centeredViewPort(int width, int height) {
        return new ViewPort(centerHorizontal(width), centerVertical(height), width, height);
    }

    private boolean isWithinBounds(int width, int height) {
        return width <= this.width && height <= this.height;
    }

    @Override
    public String toString() {
        return "ViewPort[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "width=" + width + ", " +
                "height=" + height + ']';
    }
}
