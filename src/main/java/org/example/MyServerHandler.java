package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;

public class MyServerHandler implements HttpHandler {
    private final ArrayList<Wine> lista_vino;

    public MyServerHandler(ArrayList<Wine> lista_vini) {
        this.lista_vino = lista_vini;
    }

    @Override
    public void handle(HttpExchange http_exchange) throws IOException {
        URI url_request = http_exchange.getRequestURI();
        String query_request = url_request.getQuery();
        StringBuilder output = new StringBuilder();
        String[] parametro_comando = query_request.split("[=&]");

        if (parametro_comando[0].equals("cmd")) {
            switch (parametro_comando[1]) {
                case "red":
                    for (Wine wine : lista_vino) {
                        if (wine.getType().equals("red")) {
                            output.append(wine).append("<br>");
                        }
                    }
                    break;
                case "white":
                    for (Wine wine : lista_vino) {
                        if (wine.getType().equals("white")) {
                            output.append(wine).append("<br>");
                        }
                    }
                    break;
                case "sorted_by_name":
                    lista_vino.sort(Comparator.comparing(Wine::getName));
                    for (Wine wine : lista_vino) {
                        output.append(wine).append("<br>");
                    }
                    break;
                case "sorted_by_price":
                    lista_vino.sort((o1, o2) -> {
                        if(o1.getPrice() > o2.getPrice()) return 1;
                        if(o1.getPrice() < o2.getPrice()) return -1;
                        return 0;
                    });
                    for (Wine wine : lista_vino) {
                        output.append(wine).append("<br>");
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