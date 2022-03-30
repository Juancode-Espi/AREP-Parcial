package edu.escuelaing.arep;

import edu.escuelaing.arep.Calculator;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;


public class App{

    public static void main( String[] args ){
        port(getPort());
        get("/sqrt", (req, res) -> {
            JSONObject json = new JSONObject();
            Double value = Double.parseDouble(req.queryParams("value"));
            Calculator calculator = new Calculator();
            json.put("output",calculator.sqrt(value));
            json.put("input",value);
            json.put("operation","sqrt");
            return json;
        });

        get("/sin", (req,res) -> {
            JSONObject json = new JSONObject();
            Double value = Double.parseDouble(req.queryParams("value"));
            Calculator calculator = new Calculator();
            json.put("output",calculator.sin(value));
            json.put("input",value);
            json.put("operation","sin");
            return json;
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}

