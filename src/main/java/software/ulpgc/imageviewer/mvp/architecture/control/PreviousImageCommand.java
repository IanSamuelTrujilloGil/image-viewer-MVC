package software.ulpgc.imageviewer.mvp.architecture.control;

public class PreviousImageCommand implements Command{
    private final ImagePresenter presenter;

    public PreviousImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.display(presenter.getCurrentImage().previous());
    }
}
