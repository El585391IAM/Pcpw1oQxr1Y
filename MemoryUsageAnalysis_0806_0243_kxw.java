// 代码生成时间: 2025-08-06 02:43:27
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

public class MemoryUsageAnalysis {

    private MemoryMXBean memoryMXBean;

    /**
     * Constructor for MemoryUsageAnalysis class.
     * Initializes the MemoryMXBean to use for retrieving memory usage.
     */
    public MemoryUsageAnalysis() {
        memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    /**
     * Retrieves the current memory usage.
     * 
     * @return A map containing memory usage details.
     */
    public Map<String, String> getCurrentMemoryUsage() {
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        Map<String, String> memoryUsageMap = new HashMap<>();
        memoryUsageMap.put("heapUsed", String.valueOf(heapMemoryUsage.getUsed()));
        memoryUsageMap.put("heapMax", String.valueOf(heapMemoryUsage.getMax()));
        memoryUsageMap.put("heapCommitted", String.valueOf(heapMemoryUsage.getCommitted()));
        memoryUsageMap.put("nonHeapUsed", String.valueOf(nonHeapMemoryUsage.getUsed()));
        memoryUsageMap.put("nonHeapMax", String.valueOf(nonHeapMemoryUsage.getMax()));
        memoryUsageMap.put("nonHeapCommitted", String.valueOf(nonHeapMemoryUsage.getCommitted()));

        return memoryUsageMap;
    }

    /**
     * Main method to test memory usage analysis.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            MemoryUsageAnalysis analysis = new MemoryUsageAnalysis();
            Map<String, String> memoryUsage = analysis.getCurrentMemoryUsage();

            for (Map.Entry<String, String> entry : memoryUsage.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " bytes");
            }
        } catch (Exception e) {
            System.err.println("Error occurred while analyzing memory usage: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
