package htppServ;

import com.sun.net.httpserver.*;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);

            HttpContext context = server.createContext("/", new EchoHandler());
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
//        server.setExecutor(threadPoolExecutor);
            server.start();

    }

    static class EchoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder builder = new StringBuilder();

            builder.append("<h1>URI: ").append(exchange.getRequestURI()).append("</h1>");

            Headers headers = exchange.getRequestHeaders();
            for (String header : headers.keySet()) {
                builder.append("<p>").append(header).append("=")
                        .append(headers.getFirst(header)).append("</p>");
            }


            byte[] bytes = builder.toString().getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            InputStream inputStream = exchange.getRequestBody();
//            Files.copy(inputStream, new File("C:\\Users\\Di\\Desktop\\testmy.txt").toPath());

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }



}