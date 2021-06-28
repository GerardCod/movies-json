package app;

/**
 * Defines the methods to search a movie in an API and show the results
 * @author Gerardo Aguilar
 * @version 1.0.0 27/06/2021
 */
public interface MovieReader {

    /**
     * Search a movie by title and shows the movies' titles related.
     * @param title the title of the movie.
     */
    void showMoviesByTitle(String title);
}
