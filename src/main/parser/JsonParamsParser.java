package main.parser;

import main.config.Params;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JsonParamsParser {
    private static final String configurationLocation = "C:\\Users\\andrz\\IdeaProjects\\AnimalLand\\src\\main\\config\\configuration.json";

    public static Params parse(){
        Params params = null;
        try {
            final byte[] config = Files.readAllBytes(Paths.get(configurationLocation));
            final String json = new String(config);
            final Gson gson = new Gson();
            params = gson.fromJson(json, Params.class);
            return params;
        } catch (JsonSyntaxException | IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
