// 代码生成时间: 2025-08-23 03:27:48
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
# 扩展功能模块
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
# TODO: 优化性能
import java.io.File;
# 增强安全性
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
# TODO: 优化性能
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Path("/unzip")
public class UnzipTool {

    // POST endpoint to receive a zip file and unzip it
    @POST
    @Path("/unzipFile")
# TODO: 优化性能
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public Response unzipFile(byte[] zipBytes) {
        try {
            // Create a temporary zip file from the received bytes
            File zipFile = File.createTempFile("tempZip", ".zip");
            try (FileOutputStream fos = new FileOutputStream(zipFile)) {
                fos.write(zipBytes);
# 扩展功能模块
            }
# NOTE: 重要实现细节
            // Create the output directory
            File outputDir = new File("./unzippedFiles");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            // Unzip the file
            unzip(zipFile, outputDir);
            // Return a success message
# FIXME: 处理边界情况
            return Response.ok("File has been successfully unzipped to: " + outputDir.getAbsolutePath()).build();
        } catch (IOException e) {
            // Return an error message in case of any exception
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
# TODO: 优化性能
                    .entity("Error unzipping file: " + e.getMessage()).build();
        }
    }

    // Method to unzip the file
    private void unzip(File zipFile, File outputDir) throws IOException {
        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry zipEntry = zis.getNextEntry();
            // Iterate through the ZIP entries
            while (zipEntry != null) {
                File newFile = newFile(outputDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // Write file content
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
# 改进用户体验
                }
                zipEntry = zis.getNextEntry();
# NOTE: 重要实现细节
            }
        }
    }

    // Method to create a file from the ZIP entry
    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
# 改进用户体验
        }
        
        return destFile;
    }
# TODO: 优化性能

    // GET endpoint to retrieve a list of unzipped files
    @GET
    @Path("/listUnzippedFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUnzippedFiles() {
        File outputDir = new File("./unzippedFiles");
        File[] files = outputDir.listFiles();
        // If there are no files, return an empty list
        if (files == null || files.length == 0) {
            return Response.ok("[]").build();
        }
        // List the files as a JSON array
        StringBuilder fileList = new StringBuilder();
        fileList.append('[');
        for (int i = 0; i < files.length; i++) {
            fileList.append("\"").append(files[i].getName()).append(
# 添加错误处理