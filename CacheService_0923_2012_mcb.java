// 代码生成时间: 2025-09-23 20:12:23
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Path("/cache")
public class CacheService {

    private final ConcurrentHashMap<String, CacheItem> cacheMap;

    // Initialize the cache map with a default capacity
    public CacheService() {
        cacheMap = new ConcurrentHashMap<>();
    }

    // Method to cache a value with an expiration time
    @GET
    @Path("/put/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public String putCache(@PathParam("key") String key, String value, int expirationMinutes) {
        try {
            CacheItem cacheItem = new CacheItem(value);
            cacheItem.setExpirationTime(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(expirationMinutes));
            cacheMap.put(key, cacheItem);
            return "Value cached for key: " + key + " with expiration in " + expirationMinutes + " minutes";
        } catch (Exception e) {
            // Handle any exceptions that might occur during caching
            return "Error: " + e.getMessage();
        }
    }

    // Method to retrieve a cached value
    @GET
    @Path("/get/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCache(@PathParam("key") String key) {
        CacheItem cacheItem = cacheMap.get(key);
        if (cacheItem != null && !cacheItem.isExpired()) {
            return cacheItem.getValue();
        } else {
            return "Cache miss for key: " + key;
        }
    }

    // Inner class to represent a cache item with a value and expiration time
    private class CacheItem {
        private String value;
        private long expirationTime;

        public CacheItem(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public long getExpirationTime() {
            return expirationTime;
        }

        public void setExpirationTime(long expirationTime) {
            this.expirationTime = expirationTime;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }
}
