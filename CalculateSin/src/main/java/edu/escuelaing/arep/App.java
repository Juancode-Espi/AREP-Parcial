package edu.escuelaing.arep;

import org.json.JSONObject;
import java.lang.Math;
import static spark.Spark.*;

public class App 
{
    public static void main( String[] args )
    {
        get("/sin", (req,res) -> {
            JSONObject json = new JSONObject();
            Double value = Double.parseDouble(req.queryParams("value"));
            json.put("output",Math.sin(value));
            json.put("input",value);
            json.put("operation","sin");
            return json;
        });
    }
}
