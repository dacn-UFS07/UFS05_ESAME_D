package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/", new MyServerHandler(costruireListaVini()));
        server.setExecutor(null);
        server.start();

    }

    private static ArrayList<Wine> costruireListaVini() {
        ArrayList<Wine> vino = new ArrayList<Wine>();
        vino.add(new Wine(13,"dom perignon",225.94,"white"));
        vino.add(new Wine(14,"Pignoli",133.0,"red"));
        vino.add(new Wine(124,"PInot nero",43.0,"red"));
        return vino;
    }
}
