// 代码生成时间: 2025-10-08 00:00:20
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// 使用JERSEY框架创建RESTful服务
@Path("/analyze")
public class TextFileAnalyzer {

    // 分析文本文件内容的RESTful方法
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response analyzeTextFile(@QueryParam("filename") String filename) {
        try {
            // 检查文件名是否为空
            if (filename == null || filename.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Filename cannot be empty.").build();
            }

            // 读取文件内容
            String content = new String(Files.readAllBytes(Paths.get(filename)));

            // 分析文件内容（这里只是一个简单的示例）
            int letterCount = content.length();
            int wordCount = countWords(content);
            int sentenceCount = countSentences(content);

            // 创建响应对象
            AnalyzerResponse response = new AnalyzerResponse(letterCount, wordCount, sentenceCount);
            return Response.ok(response).build();

        } catch (IOException e) {
            // 处理文件读取异常
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading file: " + e.getMessage()).build();
        }
    }

    // 计算单词数量
    private int countWords(String content) {
        return content.replaceAll("[^a-zA-Z ]", "").split(" ").length - 1;
    }

    // 计算句子数量
    private int countSentences(String content) {
        return content.length() - content.replaceAll("[^.!?]", "").length();
    }

    // 定义分析响应对象
    public static class AnalyzerResponse {
        private int letters;
        private int words;
        private int sentences;

        public AnalyzerResponse(int letters, int words, int sentences) {
            this.letters = letters;
            this.words = words;
            this.sentences = sentences;
        }

        public int getLetters() {
            return letters;
        }

        public int getWords() {
            return words;
        }

        public int getSentences() {
            return sentences;
        }
    }
}