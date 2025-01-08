package software.ulpgc.imageviewer.apps.windows;
import software.ulpgc.imageviewer.architecture.control.NextImageCommand;
import software.ulpgc.imageviewer.architecture.control.PreviousImageCommand;
import software.ulpgc.imageviewer.architecture.io.FileImageLoader;
import software.ulpgc.imageviewer.architecture.model.Image;
import java.io.File;
import java.util.Objects;

public class Main {
    private static Image getInitImage() {
        return new FileImageLoader(new File(Objects.requireNonNull(Main.class.getResource("/images")).getFile())).load();
    }

    public static void main(String[] args) {
        MainFrame mainFrame =new MainFrame();
        mainFrame
                .displayImage(getInitImage())
                .add("next", new NextImageCommand(mainFrame.getImageDisplay()))
                .add("previous", new PreviousImageCommand(mainFrame.getImageDisplay()))
                .setVisible(true);
    }
}