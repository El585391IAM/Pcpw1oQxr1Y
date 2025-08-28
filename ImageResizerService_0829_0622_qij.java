// 代码生成时间: 2025-08-29 06:22:57
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

@Path("/imageResizer")
public class ImageResizerService {

    // The base directory where images are stored
    private static final String BASE_DIRECTORY = "./images/";

    @POST
    @Path("/resize")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response resizeImages(String resizeRequest) {
        try {
            ResizeRequest request = new ResizeRequest(resizeRequest);
            resizeRequest(request);
            return Response.ok("Images resized successfully").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error resizing images: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid request: " + e.getMessage()).build();
        }
    }

    private void resizeRequest(ResizeRequest request) throws IOException {
        if (request == null || request.getDirectoryPath() == null || request.getTargetWidth() == 0 || request.getTargetHeight() == 0) {
            throw new IllegalArgumentException("Invalid resize request");
        }

        Path directoryPath = Paths.get(BASE_DIRECTORY + request.getDirectoryPath());
        if (!Files.isDirectory(directoryPath)) {
            throw new IOException("Directory not found");
        }

        Files.walk(directoryPath).forEach(path -> {
            File file = path.toFile();
            if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png"))) {
                try {
                    resizeImage(file.getAbsolutePath(), request.getTargetWidth(), request.getTargetHeight());
                } catch (IOException e) {
                    System.err.println("Error resizing image: " + file.getName() + " - " + e.getMessage());
                }
            }
        });
    }

    private void resizeImage(String imagePath, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File(imagePath));
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        String newFileName = imagePath + "_resized." + imagePath.substring(imagePath.lastIndexOf("."));
        ImageIO.write(resizedImage, imagePath.substring(imagePath.lastIndexOf(".") + 1), new File(newFileName));
    }

    // Inner class to represent resize request
    public static class ResizeRequest {
        private String directoryPath;
        private int targetWidth;
        private int targetHeight;

        public ResizeRequest(String directoryPath, int targetWidth, int targetHeight) {
            this.directoryPath = directoryPath;
            this.targetWidth = targetWidth;
            this.targetHeight = targetHeight;
        }

        public ResizeRequest(String json) {
            // JSON parsing logic here
        }

        public String getDirectoryPath() {
            return directoryPath;
        }

        public int getTargetWidth() {
            return targetWidth;
        }

        public int getTargetHeight() {
            return targetHeight;
        }
    }
}
