package org.systemDesign.structuralPattern.proxy;

public class LoggingProxyDemo {
    public static void main(String[] args) {
        FileService fileService = new FileServiceProxy("john.doe@company.com");
        fileService.upload("report.pdf");
        System.out.println();
        fileService.download("presentation.pptx");
        System.out.println();
        fileService.delete("old_data.csv");
    }
}
// Subject interface
interface FileService {
    void upload(String fileName);
    void download(String fileName);
    void delete(String fileName);
}
// Real Subject
class RealFileService implements FileService {
    public void upload(String filename) {
        System.out.println("Uploading file: " + filename);
    }
    public void download(String filename) {
        System.out.println("Downloading file: " + filename);
    }
    public void delete(String filename) {
        System.out.println("Deleting file: " + filename);
    }
}
//Logging Proxy
class FileServiceProxy implements FileService{
    private final RealFileService realFileService;
    private final String userName;
    public FileServiceProxy(String userName){
        this.realFileService = new RealFileService();
        this.userName = userName;
    }
    @Override
    public void upload(String fileName) {
        logAction("UPLOAD", fileName);
        realFileService.upload(fileName);
        logSuccess("UPLOAD", fileName);
    }
    @Override
    public void download(String fileName) {
        logAction("DOWNLOAD", fileName);
        realFileService.download(fileName);
        logSuccess("DOWNLOAD", fileName);
    }
    @Override
    public void delete(String fileName) {
        logAction("DELETE", fileName);
        realFileService.delete(fileName);
        logSuccess("DELETE", fileName);
    }
    private void logAction(String action, String filename) {
        System.out.println("[LOG] User: " + userName +
                " | Action: " + action +
                " | File: " + filename +
                " | Time: " + System.currentTimeMillis());
    }
    private void logSuccess(String action, String filename) {
        System.out.println("[LOG] " + action + " completed successfully for " + filename);
    }
}