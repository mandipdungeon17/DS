package org.systemDesign.structuralPattern.decorator;

public class DataSourceDecoratorDemo {
    public static void main(String[] args) {
        String data = "Highly Classified Data";
        // Plain file
        DataSource source = new FileDataSource("data.txt");
        source.writeData(data);
        System.out.println(source.readData());

        System.out.println("\n--- With Encryption ---\n");
        // File with encryption
        source = new EncryptDecorator(new FileDataSource("encrypted.txt"));
        source.writeData(data);
        System.out.println("Read: " + source.readData());

        System.out.println("\n--- With Compression and Encryption ---\n");

        // File with compression and encryption
        source = new CompressionDecorator(new EncryptDecorator(new FileDataSource("secure.txt")));
        source.writeData(data);
        System.out.println("Read: " + source.readData());


    }
}
interface DataSource{
    void writeData(String data);
    String readData();
}
// Concrete Component (Base)
class FileDataSource implements DataSource{
    private final String fileName;
    private String data;
    public FileDataSource(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void writeData(String data) {
        this.data = data;
        System.out.println("Writing data to file: " + this.fileName);
        System.out.println("Data: " + data);
    }
    @Override
    public String readData() {
        System.out.println("Reading data from file: " + fileName);
        return this.data;
    }
}
// Abstract Decorator
abstract class DataSourceDecorator implements DataSource{
    protected DataSource dataSource;
    public DataSourceDecorator(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void writeData(String data) {
        dataSource.writeData(data);
    }
    @Override
    public String readData() {
        return dataSource.readData();
    }
}
// Encryption Decorator
class EncryptDecorator extends DataSourceDecorator{
    public EncryptDecorator(DataSource dataSource){
        super(dataSource);
    }
    @Override
    public void writeData(String data) {
        System.out.println("Encrypting data...");
        String encrypted = encode(data);
        dataSource.writeData(encrypted);
    }
    @Override
    public String readData() {
        String data = dataSource.readData();
        System.out.println("Decrypting data...");
        return decode(data);
    }
    public String encode(String data){
        // Simple encoding for demo
        return "ENCRYPTED[" + data + "]";
    }
    private String decode(String data) {
        return data.replace("ENCRYPTED[", "").replace("]", "");
    }
}
// Compression Decorator
class CompressionDecorator extends DataSourceDecorator{
    public CompressionDecorator(DataSource dataSource){
        super(dataSource);
    }
    @Override
    public void writeData(String data) {
        System.out.println("Compressing data...");
        String compressed = compress(data);
        dataSource.writeData(compressed);
    }
    @Override
    public String readData() {
        String data = dataSource.readData();
        System.out.println("Decompressing data...");
        return decompress(data);
    }
    private String compress(String data) {
        return "COMPRESSED[" + data + "]";
    }

    private String decompress(String data) {
        return data.replace("COMPRESSED[", "").replace("]", "");
    }
}