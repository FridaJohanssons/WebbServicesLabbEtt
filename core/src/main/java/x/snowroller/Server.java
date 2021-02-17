package x.snowroller;

import x.snowroller.fileutils.FileReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            //TCP/IP
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println(Thread.currentThread());

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> handleConnection(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleConnection(Socket socket) {
        System.out.println(Thread.currentThread());
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String url = input.readLine();
            String requestedUrl = url.split(" ")[1];
            String requestType = url.split(" ")[0];

            File file = new File("web" + File.separator + requestedUrl);


            if (requestType.equals("POST")) {
                var output = new PrintWriter(socket.getOutputStream());
                //exempel adress är http://localhost:5050/?anna&antonsson

                //fullName tar ut anna&antonsson
                String fullName = requestedUrl.split("\\?")[1];

                //urlFirstName tar ut anna
                //urlLastName tar ut antonsson
                String urlFirstName = fullName.split("\\&")[0];
                String urlLastName = fullName.split("\\&")[1];

                //använder createUser metoden i klassen UserHandler. Skickar in värden till databasen.
                UserHandler.createUser(urlFirstName, urlLastName);

                System.out.println(urlFirstName + " " + urlLastName + "has been added to database.");
                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.flush();
                socket.close();

            } else if (requestedUrl.equals("/getAll")){
                //hämtar alla Users från databasen
                var output = new PrintWriter(socket.getOutputStream());
                JsonConverter converter = new JsonConverter();

                byte[] page = FileReader.readFromFile(file);

                var json = converter.convertToJson(UserHandler.getAll());
                System.out.println(json);

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type: application/json");
                output.println("");
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                if (requestType.equals("GET")) {
                    dataOut.write(page);
                    dataOut.flush();
                    socket.close();
                } else if (requestType.equals("HEAD")) {
                    dataOut.flush();
                    socket.close();
                }
            }
            if (requestedUrl.equals("/index.html")) {
                var output = new PrintWriter(socket.getOutputStream());

                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                if (requestType.equals("GET")) {
                    dataOut.write(page);
                    dataOut.flush();
                    socket.close();
                } else if (requestType.equals("HEAD")) {
                    dataOut.flush();
                    socket.close();
                }

            } else if (requestedUrl.equals("/cat.png")) {
                var output = new PrintWriter(socket.getOutputStream());

                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                if (requestType.equals("GET")) {
                    dataOut.write(page);
                    dataOut.flush();
                    socket.close();
                } else if (requestType.equals("HEAD")) {
                    dataOut.flush();
                    socket.close();
                }

            } else if (requestedUrl.equals("/stylesheet.css")) {
                var output = new PrintWriter(socket.getOutputStream());

                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                if (requestType.equals("GET")) {
                    dataOut.write(page);
                    dataOut.flush();
                    socket.close();
                } else if (requestType.equals("HEAD")) {
                    dataOut.flush();
                    socket.close();
                }

            } else if (requestedUrl.equals("/javascriptfile.js")) {
                var output = new PrintWriter(socket.getOutputStream());

                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                if (requestType.equals("GET")) {
                    dataOut.write(page);
                    dataOut.flush();
                    socket.close();
                } else if (requestType.equals("HEAD")) {
                    dataOut.flush();
                    socket.close();
                }
            } else {
                    var output = new PrintWriter(socket.getOutputStream());
                    output.println("HTTP/1.1 404 File not found");
                    output.println("Content-Length: 0");
                    output.flush();
                    var dataOut = new BufferedOutputStream(socket.getOutputStream());
                    dataOut.flush();
                    socket.close();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
