package app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Converts the data from an url to JSON objects
 */
public class DataParser {
    private BufferedReader reader;
    private final JSONParser jsonParser;

    public DataParser(BufferedReader reader) {
        this.reader = reader;
        this.jsonParser = new JSONParser();
    }

    /**
     * Parse the information in the reader to a JSONObject.
     * @return JSONObject that contains the information of the JSON from the internet
     * @throws IOException if there is an error with the reader.
     * @throws ParseException if there is an error with the format of the json.
     */
    public JSONObject parseDataToJSON() throws IOException, ParseException {
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return (JSONObject) jsonParser.parse(builder.toString());
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public JSONParser getJsonParser() {
        return this.jsonParser;
    }
}
