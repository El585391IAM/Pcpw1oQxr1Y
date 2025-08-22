// 代码生成时间: 2025-08-22 12:52:46
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;

/**
 * RESTful service for batch file renaming.
 */
@Path("/rename")
public class BatchFileRenamer {

    /**
     * GET request to get the current state of the service.
     * @return a message indicating the service is running.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getServiceStatus() {
        return Response.ok("Service is running.").build();
    }

    /**
     * POST request to rename files in a specified directory.
     * The request body should contain a list of file paths and new names.
     * @param formData the multi-part form data containing files and new names.
     * @return a response with the result of the renaming operation.
     */
    @POST
    @Path("/renameFiles")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response renameFiles(MultiPart formData) {
        try {
            List<FormDataBodyPart> bodyParts = formData.getBodyParts();
            List<String> filePaths = new ArrayList<>();
            List<String> newNames = new ArrayList<>();
            for (FormDataBodyPart bodyPart : bodyParts) {
                if ("file".equals(bodyPart.getFormDataContentDisposition().getFileName())) {
                    filePaths.add(bodyPart.getValue());
                } else if ("newName".equals(bodyPart.getFormDataContentDisposition().getFileName())) {
                    newNames.add(bodyPart.getValue());
                }
            }
            if (filePaths.size() != newNames.size()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("The number of files and new names must match.").build();
            }

            for (int i = 0; i < filePaths.size(); i++) {
                File file = new File(filePaths.get(i));
                File newFile = new File(file.getParent(), newNames.get(i));
                if (!file.renameTo(newFile)) {
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to rename file: " + file.getName()).build();
                }
            }
            return Response.ok("Files renamed successfully.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred: " + e.getMessage()).build();
        }
    }
}
