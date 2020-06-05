import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Server {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socet = server.accept();
            if(socet.isConnected()){
                
            }


//            InputStream inputStream = socet.getInputStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//            final int bufferSize = 1024;
//            final char[] buffer = new char[bufferSize];
//            final StringBuilder out = new StringBuilder();
//            Reader in = new InputStreamReader(inputStream, "UTF-8");
//            for (; ; ) {
//                int rsz = in.read(buffer, 0, buffer.length);
//                if (rsz < 0)
//                    break;
//                out.append(buffer, 0, rsz);
//            }
//            System.out.println(out.toString());
//        HttpRequest requestLoalHost = HttpRequest.newBuilder()
//                .GET()
//                .uri(URI.create("http://localhost:8080"))
//                .build();
//        HttpResponse<String> response = httpClient.send(requestLoalHost, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
        }
    }
}