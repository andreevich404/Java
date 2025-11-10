package srp;

import java.util.List;

public class ReportManager {
    private final ReportData reportData;
    private final ReportPrinter printer;
    private final ReportSaver saver;

    public ReportManager(List<Integer> data) {
        this.reportData = new ReportData(data);
        this.printer = new ReportPrinter();
        this.saver = new ReportSaver();
    }

    public void generateReport() {
        int sum = reportData.calculateSum();
        double avg = reportData.calculateAverage();

        printer.print(sum, avg);
        saver.saveToFile(sum, avg, "report.txt");
    }
}