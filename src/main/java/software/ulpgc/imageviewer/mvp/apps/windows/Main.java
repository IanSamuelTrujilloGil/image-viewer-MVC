package software.ulpgc.imageviewer.mvp.apps.windows;

import software.ulpgc.imageviewer.mvp.architecture.control.ImagePresenter;
import software.ulpgc.imageviewer.mvp.architecture.control.NextImageCommand;
import software.ulpgc.imageviewer.mvp.architecture.control.PreviousImageCommand;
import software.ulpgc.imageviewer.mvp.architecture.io.FileImageLoader;
import software.ulpgc.imageviewer.mvp.architecture.model.Image;

import java.io.File;
import java.util.Objects;

public class Main {
    private static Image getInitImage() {
        return new FileImageLoader(new File(Objects.requireNonNull(Main.class.getResource("/images")).getFile())).load();
    }

    public static void main(String[] args) {
        MainFrame mainFrame =new MainFrame();
        ImagePresenter imagePresenter = new ImagePresenter(mainFrame.getImageDisplay());
        imagePresenter.display(getInitImage());
        mainFrame
                .add("next", new NextImageCommand(imagePresenter))
                .add("previous", new PreviousImageCommand(imagePresenter))
                .setVisible(true);
    }
}
