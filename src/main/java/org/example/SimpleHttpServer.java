package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String uri = t.getRequestURI().toString();
            String response;
            int statusCode = 200;

            try {
                if (isStaticFile(uri)) {
                    File file = new File("src/main/resources" + uri);
                    if (file.exists()) {
                        byte[] fileBytes = Files.readAllBytes(file.toPath());
                        t.getResponseHeaders().set("Content-Type", getContentType(uri));
                        t.sendResponseHeaders(statusCode, fileBytes.length);
                        OutputStream os = t.getResponseBody();
                        os.write(fileBytes);
                        os.close();
                        return;
                    } else {
                        statusCode = 404;
                        response = "404 Not Found";
                    }
                } else {
                    if ("GET".equals(t.getRequestMethod())) {
                        response = MicroSpringBoot.handleGetRequest(uri);
                    } else {
                        statusCode = 405;
                        response = "405 Method Not Allowed";
                    }
                }
            } catch (Exception e) {
                statusCode = 500;
                response = "500 Internal Server Error";
                e.printStackTrace();
            }

            t.sendResponseHeaders(statusCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private boolean isStaticFile(String uri) {
            return uri.endsWith(".html") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".js") || uri.endsWith(".txt");
        }

        private String getContentType(String uri) {
            if (uri.endsWith(".html")) {
                return "text/html";
            } else if (uri.endsWith(".jpg")) {
                return "image/jpeg";
            } else if (uri.endsWith(".js")) {
                return "application/javascript";
            } else if (uri.endsWith(".png")) {
                return "image/png";
            } else if (uri.endsWith(".txt")) {
                return "text/plain";
            }
            return "application/octet-stream";
        }
    }
}