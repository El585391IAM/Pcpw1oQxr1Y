// 代码生成时间: 2025-09-13 23:40:39
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
# TODO: 优化性能
import javax.ws.rs.core.MediaType;
# 添加错误处理
import javax.ws.rs.core.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;

@Path("/resize")
public class ImageResizerService {
# NOTE: 重要实现细节

    // Define the size to which all images will be resized
    private static final int NEW_WIDTH = 800;
    private static final int NEW_HEIGHT = 600;
# 增强安全性

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resizeImages(List<File> imageFiles) {
        try {
            List<File> resizedImages = new ArrayList<>();
            for (File originalFile : imageFiles) {
                BufferedImage originalImage = ImageIO.read(originalFile);
                if (originalImage == null) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Failed to read image.").build();
# 增强安全性
                }
# FIXME: 处理边界情况

                BufferedImage resizedImage = resizeImage(originalImage);
                File resizedFile = new File("resized_" + originalFile.getName());
# NOTE: 重要实现细节
                ImageIO.write(resizedImage, "jpg", resizedFile);
                resizedImages.add(resizedFile);
            }

            return Response.ok(resizedImages).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error resizing images.").build();
        }
    }

    // Resize image to new width and height
    private BufferedImage resizeImage(BufferedImage originalImage) {
        Image tmp = originalImage.getScaledInstance(NEW_WIDTH, NEW_HEIGHT, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(NEW_WIDTH, NEW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        resized.getGraphics().drawImage(tmp, 0, 0, null);
        return resized;
    }
# FIXME: 处理边界情况
}
