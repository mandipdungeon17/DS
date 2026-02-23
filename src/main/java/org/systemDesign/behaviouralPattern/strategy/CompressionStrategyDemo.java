package org.systemDesign.behaviouralPattern.strategy;

import java.util.Objects;

public class CompressionStrategyDemo {
    public static void main(String[] args) {
        FileCompressor compressor = new FileCompressor();
        compressor.setCompressionStrategy(new ZipCompression());
        compressor.compressFile("document.pdf");
        System.out.println();
        compressor.setCompressionStrategy(new RarCompression());
        compressor.compressFile("photos.jpg");
        System.out.println();
        compressor.setCompressionStrategy(new SevenZipCompression());
        compressor.compressFile("videos.mp4");

    }
}
// Strategy interface
interface CompressionStrategy {
    void compress(String filename);
}
// Concrete strategy for ZIP compression
class ZipCompression implements CompressionStrategy{
    @Override
    public void compress(String filename) {
        System.out.println("Compressing " + filename + " using ZIP format");
        System.out.println("ZIP: High compression, widely supported");
    }
}
// Concrete strategy for RAR compression
class RarCompression implements CompressionStrategy{
    @Override
    public void compress(String filename) {
        System.out.println("Compressing " + filename + " using RAR format");
        System.out.println("RAR: Better compression ratio");
    }
}
class SevenZipCompression implements CompressionStrategy {
    public void compress(String filename) {
        System.out.println("Compressing " + filename + " using 7Z format");
        System.out.println("7Z: Highest compression, open source");
    }
}
// Context class
class FileCompressor {
    private CompressionStrategy strategy;
    public void setCompressionStrategy(CompressionStrategy strategy){
        this.strategy = strategy;
    }
    public void compressFile(String fileName){
        if(Objects.isNull(strategy)){
            System.out.println("Please set compression strategy");
        }
        else{
            strategy.compress(fileName);
        }
    }
}