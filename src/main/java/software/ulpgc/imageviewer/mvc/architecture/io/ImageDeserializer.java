package software.ulpgc.imageviewer.mvc.architecture.io;

public interface ImageDeserializer {
    Object deserialize(byte[] bytes);
}