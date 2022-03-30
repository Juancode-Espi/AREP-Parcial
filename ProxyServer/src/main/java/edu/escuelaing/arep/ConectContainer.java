package edu.escuelaing.arep;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class ConectContainer {

    private Gson gson;
    private final List<String[]> ports = new LinkedList<>();
    private int port;

    public ConectContainer(int port) {
        this.gson = new Gson();
        this.port= port;
        this.ports.add(new String[] {"loadbalancer1", "8090"});
        this.ports.add(new String[] {"loadbalancer2", "8091"});
        this.ports.add(new String[] {"loadbalancer3", "8092"});
    }

    public String mensajeContenedor(String mensaje) throws UnsupportedEncodingException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("mensaje", mensaje));
        System.out.println(mensaje + "puerto 8090");
        String[] port1 = ports.get(port);
        String url = "http://"+port1[0]+":"+port1[1]+"/publicar/mensaje";
        System.out.println(port1[0]+" "+port1[1]);

        try {
            System.out.println("Conection established!");
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.getOutputStream().write(mensaje.getBytes(StandardCharsets.UTF_8));
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            String outPut = "";
            while ((input = in.readLine()) != null){
                outPut += input;
            }
            System.out.println(outPut);
            return gson.toJson(outPut);

        }catch (Exception e){
            e.printStackTrace();
        }



        return gson.toJson("error al pedir información");
    }



}
