// 代码生成时间: 2025-09-17 10:51:52
package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;

/**
 * Service class to prevent SQL injection using prepared statements.
 */
public class SqlInjectionPreventionService {

    private DataSource dataSource;

    /**
     * Constructor to initialize the data source.
     * @param dataSource The data source to use for database operations.
     */
    public SqlInjectionPreventionService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Fetches user data from the database based on the user ID.
     * @param userId The ID of the user to fetch.
     * @return User data or null if not found.
     * @throws SQLException If a database access error occurs.
     */
    public String getUserData(int userId) throws SQLException {
        final String SQL_QUERY = "SELECT username FROM users WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("username");
            } else {
                return null;
            }
        }
    }

    /**
     * Resource configuration class to register the service.
     */
    public static class ResourceConfigurator extends ResourceConfig {

        public ResourceConfigurator() {
            register(new SqlInjectionPreventionService(dataSource));
        }
    }
}

/**
 * JAX-RS resource class to handle HTTP requests.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/users")
public class UserResource {

    private SqlInjectionPreventionService userService;

    public UserResource(SqlInjectionPreventionService userService) {
        this.userService = userService;
    }

    /**
     * GET endpoint to retrieve user data by ID.
     * @param userId The ID of the user to retrieve.
     * @return The user data in JSON format.
     */
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("userId") int userId) {
        try {
            return userService.getUserData(userId);
        } catch (SQLException e) {
            // Handle the error appropriately, e.g., log the error and return a suitable response.
            return "Error: Unable to retrieve user data.";
        }
    }
}