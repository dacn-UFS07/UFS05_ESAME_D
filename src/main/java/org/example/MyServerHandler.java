package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyServerHandler implements HttpHandler {
    private final ArrayList<Wine> vino;

    public MyServerHandler(ArrayList<Wine> vino) {
        this.vino = vino;
    }

    @Override
    public void handle(HttpExchange http_exchange) throws IOException {
        String output = "";
        for(int i = 0; i < vino.size(); i++) {
            output += vino.get(i) + "<br>";
        }
        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<meta charset=utf-8>\n" +
                "<title>MyJava Sample</title>\n" +
                "</head>\n" +
                "<body>\n" +
                output +
                "</body>\n" +
                "</html>\n";
        http_exchange.sendResponseHeaders(200, response.length());
        OutputStream os = http_exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
