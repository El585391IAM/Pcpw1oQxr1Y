// 代码生成时间: 2025-08-25 14:24:00
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rename")
public class BatchFileRenamer {
    
    // POST endpoint to handle the batch rename operation
    @POST
    @Path("/batchRename")
    @Produces(MediaType.APPLICATION_JSON)
    public Response batchRename(@QueryParam("sourceDir") String sourceDir,
                             @QueryParam("newPrefix") String newPrefix) {
        try {
            // Check if source directory exists
            File directory = new File(sourceDir);
            if (!directory.exists() || !directory.isDirectory()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Source directory does not exist.").build();
            }

            // Get all files in the directory
            File[] files = directory.listFiles();
            if (files == null || files.length == 0) {
                return Response.status(Response.Status.NOT_FOUND).entity("No files found in the directory.").build();
            }

            // Rename files with the new prefix
            int renamedCount = 0;
            for (File file : files) {
                if (file.isFile()) {
                    Path path = Paths.get(file.getAbsolutePath());
                    String newName = newPrefix + file.getName();
                    Path targetPath = Paths.get(file.getParent(), newName);
                    Files.move(path, targetPath);
                    renamedCount++;
                }
            }

            // Return success response with the number of renamed files
            return Response.ok().entity("Renamed " + renamedCount + " files successfully.").build();
        } catch (IOException e) {
            // Handle exceptions and return a server error response
            return Response.serverError().entity("Error occurred: " + e.getMessage()).build();
        }
    }

    // GET endpoint for testing purposes
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "BatchFileRenamer is up and running!";
    }
}