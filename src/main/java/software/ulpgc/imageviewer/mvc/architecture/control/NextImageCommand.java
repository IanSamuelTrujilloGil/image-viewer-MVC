package software.ulpgc.imageviewer.mvc.architecture.control;

import software.ulpgc.imageviewer.mvc.architecture.view.ImageDisplay;

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
