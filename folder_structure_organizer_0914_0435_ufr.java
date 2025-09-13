// 代码生成时间: 2025-09-14 04:35:04
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/organize")
public class FolderStructureOrganizer {

    private static final String DIRECTORY_SEPARATOR = File.separator;
    private static final String REPORT_FILE_NAME = "report.txt";
    private static final String REPORT_FILE_PATH = System.getProperty("user.dir") + DIRECTORY_SEPARATOR + REPORT_FILE_NAME;

    /**
     * Organizes the file structure of a given directory.
     * @param directoryPath The path to the directory to be organized.
     * @return A report of the organization process.
     */
    @GET
    @Path("/organize/{directoryPath}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response organizeDirectory(@PathParam("directoryPath") String directoryPath) {
        try {
            List<String> report = new ArrayList<>();

            // Validate directory path
            Path directory = Paths.get(directoryPath);
            if (!Files.isDirectory(directory)) {
                report.add("The specified path is not a directory or does not exist.");
                return Response.status(Response.Status.NOT_FOUND).entity(generateReport(report)).build();
            }

            // Organize directory
            report.add("Starting to organize the directory: " + directoryPath);
            organizeDirectoryRecursively(directory, report);
            report.add("Directory organization completed.");

            // Save report to a file
            Files.write(Paths.get(REPORT_FILE_PATH), report);
            report.add("Report saved to: " + REPORT_FILE_PATH);

            return Response.ok(generateReport(report)).build();
        } catch (IOException e) {
            return Response.serverError().entity("An error occurred: " + e.getMessage()).build();
        }
    }

    private void organizeDirectoryRecursively(Path directory, List<String> report) throws IOException {
        Files.list(directory).forEach(file -> {
            try {
                if (Files.isDirectory(file)) {
                    report.add("Entering directory: " + file);
                    organizeDirectoryRecursively(file, report);
                } else {
                    report.add("File found: " + file);
                }
            } catch (IOException e) {
                report.add("An error occurred while processing file: " + file + ", error: " + e.getMessage());
            }
        });
    }

    private String generateReport(List<String> report) {
        return report.stream().collect(Collectors.joining("\
"));
    }
}
