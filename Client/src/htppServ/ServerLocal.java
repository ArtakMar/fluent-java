package htppServ;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerLocal {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            ExecutorService es = Executors.newFixedThreadPool(5);

            for (int i = 0; i < 5; i++) {

                es.submit(new Thread(() -> {
                    try {

                        while (true) {
                            Socket clientSocket = serverSocket.accept();

                            // Request
                            InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
                            Thread.sleep(10000);
                            StringBuilder s = new StringBuilder();
                            while (input.ready()) s.append((char) input.read());

                            System.out.println(s);

                            // Response
                            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
                            String answer = "HTTP/1.1 200 OK\n" +
                                    "Cache-Control: no-cache\n" +
                                    "Connection: close:\n" +
                                    "Content-Type: application/json\n\n" +
                                    "{\"ok\": \"" + s.toString() + "\"}";

                            out.write(answer);
                            out.flush();


                            input.close();
                            out.close();
                            clientSocket.close();
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }

            Thread.sleep(10000000);
            serverSocket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    }

