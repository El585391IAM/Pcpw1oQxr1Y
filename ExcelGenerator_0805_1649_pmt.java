// 代码生成时间: 2025-08-05 16:49:00
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.OutputStream;
import java.io.IOException;

/**
 * RESTful service to generate an Excel file.
 */
@Path("/excel")
public class ExcelGenerator {

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateExcel() throws IOException {
        try {
            Workbook workbook = new XSSFWorkbook(); // Create a new Excel workbook
            Sheet sheet = workbook.createSheet("Sheet1"); // Create a new sheet
            Row row = sheet.createRow(0); // Create a new row
            row.createCell(0).setCellValue("Header1"); // Add a cell with header
            row.createCell(1).setCellValue("Header2"); // Add a cell with header

            // Create a sample data row
            row = sheet.createRow(1);
            row.createCell(0).setCellValue("Data1");
            row.createCell(1).setCellValue("Data2");

            // Prepare the response
            return Response.ok(workbook)
                    .header("Content-Disposition", "attachment; filename=sample.xlsx")
                    .build();
        } catch (Exception e) {
            // Log the exception and return a server error response
            e.printStackTrace();
            return Response.serverError().entity("Error generating Excel file: " + e.getMessage()).build();
        }
    }
}