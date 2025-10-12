// 代码生成时间: 2025-10-12 13:07:33
 * This Jersey resource class represents the TeacherStudentInteractionService,
 * which provides endpoints for teacher-student interactions.
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/interaction")
public class TeacherStudentInteractionService {

    // Database or repository for storing interactions
    private InteractionRepository interactionRepository;

    public TeacherStudentInteractionService() {
        // Initialize the repository
        interactionRepository = new InteractionRepository();
    }

    /**
     * Retrieves a list of all interactions stored in the database.
     *
     * @return A list of interactions in JSON format.
     */
    @GET
    @Path("/interactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInteractions() {
        try {
            // Fetch all interactions from the repository
            return Response.ok(interactionRepository.getAllInteractions()).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            return Response.serverError().entity("Error fetching interactions: " + e.getMessage()).build();
        }
    }

    /**
     * Creates a new interaction between a teacher and a student.
     *
     * @param interaction The interaction data.
     * @return A response with the created interaction or an error message.
     */
    @POST
    @Path("/interactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInteraction(Interaction interaction) {
        try {
            // Validate the interaction data
            if (interaction == null || interaction.getTeacherId() == null || interaction.getStudentId() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid interaction data").build();
            }

            // Save the interaction to the repository
            interactionRepository.addInteraction(interaction);
            return Response.ok(interaction).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur during creation
            return Response.serverError().entity("Error creating interaction: " + e.getMessage()).build();
        }
    }

    /**
     * Retrieves a specific interaction by its ID.
     *
     * @param interactionId The ID of the interaction to retrieve.
     * @return The interaction data or an error message.
     */
    @GET
    @Path("/interactions/{interactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInteractionById(@PathParam("interactionId") String interactionId) {
        try {
            // Fetch the interaction by its ID from the repository
            Interaction interaction = interactionRepository.getInteractionById(interactionId);
            if (interaction == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Interaction not found").build();
            }
            return Response.ok(interaction).build();
        } catch (Exception e) {
            // Handle any exceptions that may occur
            return Response.serverError().entity("Error fetching interaction: " + e.getMessage()).build();
        }
    }
}

/**
 * Represents an interaction between a teacher and a student.
 */
class Interaction {
    private String teacherId;
    private String studentId;
    private String message;

    // Getters and setters for the fields
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

/**
 * Provides a simple in-memory repository for interactions.
 */
class InteractionRepository {
    private List<Interaction> interactions;

    public InteractionRepository() {
        interactions = new ArrayList<>();
    }

    public List<Interaction> getAllInteractions() {
        return interactions;
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    public Interaction getInteractionById(String interactionId) {
        // In a real-world scenario, you would use a more efficient method to retrieve by ID
        for (Interaction interaction : interactions) {
            if (interaction.getMessage().equals(interactionId)) {
                return interaction;
            }
        }
        return null;
    }
}