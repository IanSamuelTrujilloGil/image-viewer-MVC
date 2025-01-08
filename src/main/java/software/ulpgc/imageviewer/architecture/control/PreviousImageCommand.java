package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.view.ImageDisplay;

public class PreviousImageCommand implements Command{
    private final ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.getImage().previous());
    }
}
