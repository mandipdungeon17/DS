package org.systemDesign.structuralPattern.facade;

public class ReportFacadeDemo {
    public static void main(String[] args) {
        ReportFacade reportFacade = new ReportFacade();
        // Simple one-liner instead of complex workflow
        reportFacade.generateAndSendReport(
                "SELECT * FROM sales WHERE month = 'January'",
                "manager@company.com"
        );
    }
}
//Facade
class ReportFacade{
    private final DataFetcher dataFetcher;
    private final DataProcessor dataProcessor;
    private final ChartGenerator chartGenerator;
    private final PDFGenerator pdfGenerator;
    private final EmailService emailService;

    ReportFacade() {
        this.dataFetcher = new DataFetcher();
        this.dataProcessor = new DataProcessor();
        this.chartGenerator = new ChartGenerator();
        this.pdfGenerator = new PDFGenerator();
        this.emailService = new EmailService();
    }
    // Simple method to generate and send report
    public void generateAndSendReport(String query, String recipient) {
        System.out.println("\n=== Generating Report ===\n");
        // Fetch data
        String rawData = dataFetcher.fetchData(query);
        // Process data
        String processedData = dataProcessor.processData(rawData);
        // Generate Chart
        String chart = chartGenerator.generateChart(processedData, "Bar Chart");
        // Create PDF
        String reportName = "Sales_Report.pdf";
        pdfGenerator.generatePDF(processedData + chart, reportName);
        // Send email
        emailService.sendEmail(recipient, "Monthly Report", reportName);
        System.out.println("\n=== Report Sent ===\n");
    }
}
// Subsystem: Data Fetcher
class DataFetcher{
    public String fetchData(String query) {
        System.out.println("DataFetcher: Executing query - " + query);
        return "Sample Data";
    }
}
// Subsystem: Data Processor
class DataProcessor {
    public String processData(String rawData) {
        System.out.println("DataProcessor: Processing data");
        return "Processed Data";
    }
}
// Subsystem: Chart Generator
class ChartGenerator {
    public String generateChart(String data, String chartType) {
        System.out.println("ChartGenerator: Creating " + chartType + " chart");
        return "Chart Image";
    }
}
// Subsystem: PDF Generator
class PDFGenerator {
    public void generatePDF(String content, String filename) {
        System.out.println("PDFGenerator: Creating PDF - " + filename);
    }
}
// Subsystem: Email Service
class EmailService {
    public void sendEmail(String recipient, String subject, String attachment) {
        System.out.println("EmailService: Sending to " + recipient);
    }
}