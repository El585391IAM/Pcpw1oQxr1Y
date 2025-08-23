// 代码生成时间: 2025-08-24 06:08:04
package com.example.api;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseFormatter {

    // Format a success response with a specific message and data
    private static Response successResponse(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("data", data);
        return Response.ok(response).build();
    }

    // Format an error response with a specific message and error code
    private static Response errorResponse(String message, String errorCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", errorCode);
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }

    // Public method to create a success response
    public static Response createSuccessResponse(String message, Object data) {
        try {
            return successResponse(message, data);
        } catch (Exception e) {
            // Log the exception and return a generic error response
            System.err.println("Error creating success response: " + e.getMessage());
            return errorResponse("Internal Server Error", "INTERNAL_SERVER_ERROR");
        }
    }

    // Public method to create an error response
    public static Response createErrorResponse(String message, String errorCode) {
        try {
            return errorResponse(message, errorCode);
        } catch (Exception e) {
            // Log the exception and return a generic error response
            System.err.println("Error creating error response: " + e.getMessage());
            return errorResponse("Internal Server Error", "INTERNAL_SERVER_ERROR");
        }
    }
}
