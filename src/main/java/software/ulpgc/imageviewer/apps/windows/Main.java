package software.ulpgc.imageviewer.apps.windows;
import software.ulpgc.imageviewer.control.NextImageCommand;
import software.ulpgc.imageviewer.control.PreviousImageCommand;
import software.ulpgc.imageviewer.io.FileImageLoader;
import software.ulpgc.imageviewer.model.Image;
import java.io.File;

public class Main {
    private static Image getInitImage() {
        return new FileImageLoader(new File("src/main/resources/images")).load();
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