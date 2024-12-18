package local.javi.app.domain.util.handler;

public interface FileHandler {
    void checkIfFileOrFolderExists(String zipFileName, String outputFolder);
    void extractZip(String zipFileName, String outputFolder);
    void deleteFile(String zipFileName);

    void deleteDirectory(String directoryPath);
}
