package edu.escuelaing.arep;

import org.json.JSONObject;
import java.lang.Math;
import static spark.Spark.*;


public class App{

    public static void main( String[] args ){
        port(getPort());
        get("/sqrt", (req, res) -> {
            JSONObject json = new JSONObject();
            Double value = Double.parseDouble(req.queryParams("value"));
            json.put("output",Math.sqrt(value));
            json.put("input",value);
            json.put("operation","sqrt");
            return json;
        });

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4569;
    }
}

