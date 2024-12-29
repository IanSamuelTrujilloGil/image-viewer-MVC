package software.ulpgc.imageviewer.model;

public interface Image {
    String name();
    byte[] content();
    Image next();
    Image previous();
    Format format();

     enum Format {
        jpg, jpeg, png, gif, bmp, tiff, svg, heif, raw
    }
}