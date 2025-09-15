// 代码生成时间: 2025-09-15 09:49:15
import java.lang.management.ManagementFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// MemoryUsageAnalysisService class that provides memory usage analysis
@Path("/memory")
public class MemoryUsageAnalysisService {

    // Method to get memory usage information
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMemoryUsage() {
        try {
            // Get the memory MXBean
            com.sun.management.OperatingSystemMXBean osBean =
                (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

            // Get the total physical memory size
            long totalMemory = osBean.getTotalPhysicalMemorySize();

            // Get the free physical memory size
            long freeMemory = osBean.getFreePhysicalMemorySize();

            // Calculate the used memory size
            long usedMemory = totalMemory - freeMemory;

            // Create a JSON response with memory usage details
            String response = "{\"totalMemory\": \" + totalMemory + \