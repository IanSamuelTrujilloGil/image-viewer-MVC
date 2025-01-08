package software.ulpgc.imageviewer.architecture.view;

import software.ulpgc.imageviewer.architecture.model.Image;

public interface ImageDisplay {
    Image getImage();
    void display(Image image);
}