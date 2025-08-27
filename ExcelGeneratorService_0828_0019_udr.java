// 代码生成时间: 2025-08-28 00:19:00
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

@Path("/excel")
public class ExcelGeneratorService {

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateExcelFile() {
        try {
            Workbook workbook = new XSSFWorkbook(); // Create a new Excel workbook
            Sheet sheet = workbook.createSheet("Sheet1"); // Create a new sheet

            // Create a header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Column 1");
            headerRow.createCell(1).setCellValue("Column 2");
            headerRow.createCell(2).setCellValue("Column 3");

            // Set header alignment
            headerRow.createCell(0).setCellStyle(horizontalCenterStyle(workbook));
            headerRow.createCell(1).setCellStyle(horizontalCenterStyle(workbook));
            headerRow.createCell(2).setCellStyle(horizontalCenterStyle(workbook));

            // Create a data row
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue("Value 1");
            dataRow.createCell(1).setCellValue("Value 2");
            dataRow.createCell(2).setCellValue("Value 3");

            // Write the workbook to a byte array output stream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);

            // Close the workbook
            workbook.close();

            // Get the byte array from the output stream
            byte[] excelBytes = bos.toByteArray();

            // Set the response header for the file download
            return Response.ok(excelBytes, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=generatedExcel.xlsx").build();
        } catch (IOException e) {
            // Handle exceptions and return an error message
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generating Excel file: " + e.getMessage()).build();
        }
    }

    private org.apache.poi.ss.usermodel.CellStyle horizontalCenterStyle(Workbook workbook) {
        org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
}