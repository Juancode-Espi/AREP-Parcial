package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

import static spark.Spark.*;
public class App
{
    private static final AtomicInteger position = new AtomicInteger(-1);

    public static void main( String[] args )
    {
        Gson gson = new Gson();
        port(getPort());
        staticFiles.location("public");
        get("/publicar/:mensaje", (req, res) -> roundRobin(req.params(":mensaje")));
    }

    public static String roundRobin(String mensaje) throws UnsupportedEncodingException {
        ConectContainer conectContainer = new ConectContainer(getListenport());
        return conectContainer.mensajeContenedor(mensaje);
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

    public static int getListenport(){
        if(position.get() < 2){
            position.incrementAndGet();
            System.out.println(position.get());
        }else {
            position.set(0);
        }
        return position.get();
    }
}
