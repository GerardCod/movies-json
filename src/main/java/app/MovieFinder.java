package app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

/**
 * Search movies in a third-party API and shows the movies' titles related to the search argument,
 */
public class MovieFinder implements MovieReader {

    /**
     * Search a movie in an API of hackerrank and shows the results.
     * @param title the title of the movie.
     */
    @Override
    public void showMoviesByTitle(String title) {
        String url = buildURL(title, 1);
        HttpsURLConnection connection = getConnection(url);
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())) ) {
            DataParser parser = new DataParser(reader);
            JSONObject object = parser.parseDataToJSON();
            JSONArray movies = (JSONArray) object.get("data");
            Number totalPages = (Number) object.get("total_pages");
            showTitles(movies);

            if (totalPages.intValue() > 1) {
                int i = 2;
                while (i <= totalPages.intValue()) {
                    url = buildURL(title, i);
                    connection = getConnection(url);
                    parser.setReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
                    object = parser.parseDataToJSON();
                    movies = (JSONArray) object.get("data");
                    showTitles(movies);
                    i++;
                }
            }
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints in console the list of movies' titles from the API.
     * @param movies the list of movies.
     */
    private void showTitles(JSONArray movies) {
        if (movies.size() > 0) {
            for (Object movie : movies) {
                JSONObject movieObject = (JSONObject) movie;
                String movieTitle = movieObject.get("Title").toString();
                System.out.println(movieTitle);
            }
        } else {
            System.out.println("There are not results for this movie");
        }
    }

    /**
     * Returns a secure connection with a resource from internet
     * @param url the direction of the resource.
     * @return HttpsURLConnection
     */
    private HttpsURLConnection getConnection(String url) {
        HttpsURLConnection connection = null;
        try {
            URL jsonUrl = new URL(url);
            connection = (HttpsURLConnection) jsonUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Builds an URL for the search of the movie.
     * @param title the name of the movie.
     * @param page the page number of the results.
     * @return an URL
     */
    private String buildURL(String title, Number page) {
        return "https://jsonmock.hackerrank.com/api/movies/search/?Title="+ title +"&page=" + page;
    }
}
