package software.ulpgc.imageviewer.architecture.io;

import software.ulpgc.imageviewer.architecture.model.Image;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;


public class FileImageLoader implements ImageLoader{
    private final File[] imageFiles;

    public FileImageLoader(File folder) {
        this.imageFiles = loadValidImageFiles(folder);
    }

    private static File[] loadValidImageFiles(File folder) {
        if (folder == null || !folder.isDirectory())
            throw new IllegalArgumentException("The provided folder is not a valid directory.");
        return folder.listFiles(imageExtensionFilter()) != null ?
                folder.listFiles(imageExtensionFilter()) :
                new File[0];
    }

    private static FileFilter imageExtensionFilter() {
        return f -> Arrays.stream(Image.Format.values())
                .map(s-> s.name().toLowerCase())
                .anyMatch(e-> isValidExtension(f, e));
    }

    private static boolean isValidExtension(File file, String extension) {
        return file.getName().toLowerCase().endsWith(extension);
    }

    @Override
    public Image load() {
        return getImageAt(0);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Image getImageAt(int index) {
        return new Image() {
            @Override
            public String name() {
                return currentFile().getName();
            }

            @Override
            public byte[] content() {
                try {
                    return Files.readAllBytes(currentFile().toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Image next() {
                return getImageAt(previousIndex());
            }

            private int previousIndex() {
                return (index + 1) % imageFiles.length;
            }

            @Override
            public Image previous() {
                return getImageAt(nextIndex());
            }

            private int nextIndex() {
                return index > 0 ? index - 1 : imageFiles.length - 1;
            }

            @Override
            public Format format() {
                return Format.valueOf(getExtension(currentFile().getName()));
            }

            private File currentFile(){
                return imageFiles[index];
            }
        };
    }
}