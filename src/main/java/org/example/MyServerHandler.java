package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

public class MyServerHandler implements HttpHandler {
    private final ArrayList<Wine> vino;

    public MyServerHandler(ArrayList<Wine> vino) {
        this.vino = vino;
    }

    @Override
    public void handle(HttpExchange http_exchange) throws IOException {
        URI url_request = http_exchange.getRequestURI();
        String query_request = url_request.getQuery();
        String output = "";
        String[] parametro_comando = query_request.split("[=&]");

        if(parametro_comando[0].equals("cmd")){
            switch(parametro_comando[1]){
                case "red":
                    output += parametro_comando[1]+ "<br>";
                    System.out.println(output);
                    break;
                case "white":
                    output += parametro_comando[1]+ "<br>";
                    System.out.println(output);
                    break;
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
}
