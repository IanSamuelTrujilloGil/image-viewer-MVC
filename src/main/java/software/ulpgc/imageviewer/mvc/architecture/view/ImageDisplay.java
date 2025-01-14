package software.ulpgc.imageviewer.mvc.architecture.view;

import software.ulpgc.imageviewer.mvc.architecture.model.Image;

public interface ImageDisplay {
    Image getImage();
    void display(Image image);
}