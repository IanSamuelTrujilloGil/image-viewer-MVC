package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.view.ImageDisplay;

public class NextImageCommand implements Command{
    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.getImage().next());
    }
}
