package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MovieReader reader = new MovieFinder();
        Scanner scan = new Scanner(System.in);
        String continueProgram = "yes";

        while (continueProgram.equalsIgnoreCase("yes")) {
            System.out.println("Type the name of a movie");
            String title = scan.nextLine();
            reader.showMoviesByTitle(title.toLowerCase());
            System.out.println("Do you want to continue? Yes/No");
            continueProgram = scan.nextLine();
        }

        scan.close();
    }
}
