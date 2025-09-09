// 代码生成时间: 2025-09-09 19:23:42
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rename")
public class BulkFileRenamer {

    // POST endpoint to rename files
    @POST
    @Path("/renameFiles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renameFiles(List<FileData> filesData) {
        try {
            for (FileData fileData : filesData) {
                File originalFile = new File(fileData.getOldName());
                File newFile = new File(fileData.getNewName());

                // Check if the original file exists
                if (!originalFile.exists()) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("File not found: " + fileData.getOldName())
                            .build();
                }

                // Check if the new file name already exists
                if (newFile.exists()) {
                    return Response.status(Response.Status.CONFLICT)
                            .entity("File already exists: " + fileData.getNewName())
                            .build();
                }

                // Rename the file
                if (originalFile.renameTo(newFile)) {
                    System.out.println("File renamed from " + fileData.getOldName() + " to " + fileData.getNewName());
                } else {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Failed to rename file: " + fileData.getOldName())
                            .build();
                }
            }
            return Response.ok("All files have been renamed successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error during file renaming: " + e.getMessage())
                    .build();
        }
    }

    // Helper method to list files in a directory
    @Path("/listFiles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFiles(@QueryParam("directory") String directory) {
        try {
            Path path = Paths.get(directory);
            List<String> fileNames = Files.list(path)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            return Response.ok(fileNames).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error listing files: " + e.getMessage())
                    .build();
        }
    }

    // Class to represent file data
    public static class FileData {
        private String oldName;
        private String newName;

        public FileData() {
        }

        public String getOldName() {
            return oldName;
        }

        public void setOldName(String oldName) {
            this.oldName = oldName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }
    }
}
