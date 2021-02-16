package x.snowroller;

import x.snowroller.fileutils.FileReader;
import x.snowroller.models.Todo;
import x.snowroller.models.Todos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {

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
                //localhost:5050/?anna&antonsson

                //fullName tar ut anna&antonsson
                String fullName = requestedUrl.split("\\?")[1];

                //urlFirstName tar ut anna
                //urlLastName tar ut antonsson
                String urlFirstName = fullName.split("\\&")[0];
                String urlLastName = fullName.split("\\&")[1];

                //använder createUser metoden i klassen UserHandler. Skickar in värden till databasen.
                UserHandler.createUser("", urlFirstName, urlLastName);

                System.out.println(urlFirstName + " " + urlLastName + "has been added to database.");
                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                //output.print(page);
                output.flush();
                var dataOut = new BufferedOutputStream(socket.getOutputStream());
                dataOut.flush();
                socket.close();

//            } else if (requestedUrl.equals("/getAll")){
//                var output = new PrintWriter(socket.getOutputStream());
//                byte[] page = FileReader.readFromFile(file);
//
//                var todos = new Todos();
//                todos.todos = new ArrayList<>();
//                todos.todos.add(new Todo("1", "Todo 1", false));
//                todos.todos.add(new Todo("2", "Todo 2", false));
//
//                JsonConverter converter = new JsonConverter();
//
//                var json = converter.convertToJson(todos);
//                System.out.println(json);
//
//                output.println("HTTP/1.1 200 OK");
//                output.println("Content-Length:" + page.length);
//                output.println("Content-Type: application/json");
//                output.println("");
//                output.flush();
//
//                var dataOut = new BufferedOutputStream(socket.getOutputStream());
//                if (requestType.equals("GET")) {
//                    dataOut.write(page);
//                    dataOut.flush();
//                    socket.close();
//                } else if (requestType.equals("HEAD")) {
//                    dataOut.flush();
//                    socket.close();
//                }
            } else if (requestedUrl.equals("/getAll")){
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
            else if (requestedUrl.equals("/index.html")) {
                var output = new PrintWriter(socket.getOutputStream());

                byte[] page = FileReader.readFromFile(file);

                String contentType = Files.probeContentType(file.toPath());

                output.println("HTTP/1.1 200 OK");
                output.println("Content-Length:" + page.length);
                output.println("Content-Type:" + contentType);
                output.println("");
                //output.print(page);
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
                output.println("Content-Type:" + contentType);  //application/json
                output.println("");
                //output.print(page);
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
                output.println("Content-Type:" + contentType);  //application/json
                output.println("");
                //output.print(page);
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
                    output.println("HTTP/1.1 404");
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

//    nedanstående metod används ej. probeContent tror jag gör jobbet istället?
//    private static String contentType(String requestedUrl) {
////        if (requestedUrl.endsWith(".html")) {
////            return "text/html";
////        } else if (requestedUrl.endsWith(".png")) {
////            return "image/png";
////        } else if (requestedUrl.endsWith(".pdf")) {
////            return "application/pdf";
////        } else if (requestedUrl.endsWith(".js")) {
////            return "text/javascript";
////        } else if (requestedUrl.endsWith(".css")) {
////            return "text/css";
////        } else return "text/plain";
////    }

//    private static void createJsonResponse() {
//        var todos = new Todos();
//        todos.todos = new ArrayList<>();
//        todos.todos.add(new Todo("1", "Todo 1", false));
//        todos.todos.add(new Todo("2", "Todo 2", false));
//
//        JsonConverter converter = new JsonConverter();
//
//        var json = converter.convertToJson(todos);
//        System.out.println(json);
//    }
}
