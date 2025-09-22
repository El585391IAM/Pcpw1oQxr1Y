// 代码生成时间: 2025-09-22 15:25:37
 * InteractiveChartGenerator.java
 * 
 * This class provides functionality to generate interactive charts based on user input.
 * It uses the JERSEY framework to create a RESTful API.
 * 
 * @author Your Name
 * @version 1.0
 */
package com.example.chartgenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/chart")
public class InteractiveChartGenerator {
    
    // Endpoint to generate a chart based on the provided data
    @POST
    @Path("/generate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> generateChart(Map<String, Object> requestData) {
        try {
            // Extract chart type and data from the request
            String chartType = (String) requestData.get("chartType");
            Map<String, Object> chartData = (Map<String, Object>) requestData.get("data");
            
            // Validate chart type and data
            if (chartType == null || chartData == null) {
                throw new BadRequestException("Chart type and data are required.");
            }
            
            // Generate the chart based on the chart type and data
            Map<String, Object> chart = new HashMap<>();
            chart.put("chartType", chartType);
            chart.put("data", chartData);
            
            // Return the generated chart
            return chart;
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return createErrorResponse(e.getMessage());
        }
    }
    
    // Helper method to create error responses
    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", true);
        errorResponse.put("message", message);
        return errorResponse;
    }
}
