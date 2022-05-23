package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;

public class MyServerHandler implements HttpHandler {
    private final ArrayList<Wine> lista_vino;

    public MyServerHandler(ArrayList<Wine> lista_vini) {
        this.lista_vino = lista_vini;
    }

    @Override
    public void handle(HttpExchange http_exchange) throws IOException {
        URI url_request = http_exchange.getRequestURI();
        String query_request = url_request.getQuery();
        String output = "";
        String[] parametro_comando = query_request.split("[=&]");

        if (parametro_comando[0].equals("cmd")) {
            switch (parametro_comando[1]) {
                case "red":
                    for (int i = 0; i < lista_vino.size(); i++) {
                        if (lista_vino.get(i).getType().equals("red")) {
                            output += lista_vino.get(i) + "<br>";
                        }
                    }
                    break;
            }

            String response = "<!doctype html>\n" +
                    "<html lang=en>\n" +
                    "<head>\n" +
                    "<meta charset=utf-8>\n" +
                    "<title>ESAME UFS05</title>\n" +
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