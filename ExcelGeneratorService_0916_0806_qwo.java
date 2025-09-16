// 代码生成时间: 2025-09-16 08:06:27
package com.example.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
# 扩展功能模块
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
# 添加错误处理
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Path("/excel")
public class ExcelGeneratorService {

    private static final String EXCEL_FILE = "example.xlsx";

    private Workbook createExcelFile() {
        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();
        // Add a new sheet to the workbook
        workbook.createSheet("Example Sheet");
        // Return the workbook
        return workbook;
    }

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateExcel() {
        try {
            // Create an Excel workbook
            Workbook workbook = createExcelFile();
            // Create a ByteArrayOutputStream to store the Excel file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // Write the workbook to the output stream
            workbook.write(outputStream);
# 扩展功能模块
            // Close the workbook
            workbook.close();
            // Close the output stream
            outputStream.close();
            // Get the Excel file as a byte array
            byte[] excelBytes = outputStream.toByteArray();
            // Create a response with the Excel file and set the content type to 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
            return Response.ok(excelBytes)
                    .header("Content-Disposition", "attachment; filename=" + EXCEL_FILE)
                    .build();
        } catch (IOException e) {
            // Log the exception and return a 500 error response
            e.printStackTrace();
# 优化算法效率
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating Excel file: " + e.getMessage()).build();
        }
    }
}
# 增强安全性
