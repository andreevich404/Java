package srp;

import java.util.List;

public class ReportData {
    private final List<Integer> data;

    public ReportData(List<Integer> data) {
        this.data = data;
    }

    public int calculateSum() {
        int sum = 0;
        for (int n : data) sum += n;
        return sum;
    }

    public double calculateAverage() {
        if (data.isEmpty()) return 0;
        return (double) calculateSum() / data.size();
    }
}