package software.ulpgc.imageviewer.mvp.architecture.io;

public interface ImageDeserializer {
    Object deserialize(byte[] bytes);
}
