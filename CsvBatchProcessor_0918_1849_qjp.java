// 代码生成时间: 2025-09-18 18:49:05
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Path("/csv")
public class CsvBatchProcessor {

    // Base path for the CSV files
    private static final String BASE_PATH = "./csv_files/";

    // Endpoint to process CSV files
    @GET
    @Path("/process")
    @Produces(MediaType.TEXT_PLAIN)
    public Response processCsvFiles() {
        try {
            // Read all CSV files in the directory
            List<String> csvFiles = Files.list(Paths.get(BASE_PATH))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .map(path -> path.toString())
                    .collect(Collectors.toList());

            // Process each CSV file
            for (String filePath : csvFiles) {
                processCsvFile(filePath);
            }

            // Return a success message
            return Response.ok("All CSV files processed successfully.").build();
        } catch (IOException e) {
            // Return an internal server error with the exception message
            return Response.serverError().entity("Error processing CSV files: " + e.getMessage()).build();
        }
    }

    // Method to process a single CSV file
    private void processCsvFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the CSV file
                // For now, we just print it out
                System.out.println(line);
            }
        } catch (IOException e) {
            // Log or handle the error for this specific file
            System.err.println("Error processing file: " + filePath + " - " + e.getMessage());
        }
    }

    // Main method to run the CSV batch processor
    public static void main(String[] args) {
        // Start the Jersey server with the CsvBatchProcessor resource
        // Assuming the server is configured to start on port 8080
        System.out.println("Starting CSV Batch Processor...");
    }
}
