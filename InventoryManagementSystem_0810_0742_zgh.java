// 代码生成时间: 2025-08-10 07:42:59
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// 定义库存管理系统资源
@Path("/inventory")
public class InventoryManagementSystem {
    // 使用HashMap模拟数据库存储商品和库存数量
    private Map<String, Integer> inventory = new HashMap<>();

    // POST方法添加商品
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(String product, int quantity) {
        try {
            if (quantity < 0) {
                return Response.status(400).entity("Quantity cannot be negative.").build();
            }
            inventory.put(product, quantity);
            return Response.ok("Product added successfully.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Error adding product: " + e.getMessage()).build();
        }
    }

    // GET方法获取商品库存
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getProducts() {
        return inventory;
    }

    // GET方法通过商品名称获取库存
    @Path("{product}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("product") String product) {
        try {
            if (!inventory.containsKey(product)) {
                return Response.status(404).entity("Product not found.").build();
            }
            return Response.ok(inventory.get(product)).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error getting product: " + e.getMessage()).build();
        }
    }

    // PUT方法更新商品库存
    @Path("{product}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("product") String product, int quantity) {
        try {
            if (quantity < 0 || !inventory.containsKey(product)) {
                return Response.status(400).entity("Quantity cannot be negative or product not found.").build();
            }
            inventory.put(product, quantity);
            return Response.ok("Product quantity updated successfully.").build();
        } catch (Exception e) {
            return Response.serverError().entity("Error updating product: " + e.getMessage()).build();
        }
    }
}
