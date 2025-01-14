package software.ulpgc.imageviewer.mvp.architecture.view;


public interface ImageDisplay {
    int width();
    void paint(PaintOrder... orders);
    void on(Shift shift);
    void on(Released released);

    record PaintOrder(byte[] content, int offset) {}

    interface Shift {
        Shift NULL = offset -> {};
        void offset(int offset);
    }

    interface Released {
        Released NULL = offset -> {};
        void offset(int offset);
    }
}
