import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Movie;
import models.Store;

public class Main {

    private static Store movieStore = new Store();
    public static void main(String[] args) {
        System.out.println("\n********************JAVA VIDEO STORE********************\n");
        try {
            loadMovies("movies.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("MOVIES LOADED\n\n");
            System.out.println(movieStore);
            manageMovies();
        }
    }

    public static void manageMovies() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWould you like to \n\ta) purchase\n\tb) rent \n\tc) return.");
            String response = scan.nextLine();
        while (true) {
            if (!(response.equals("a") || response.equals("b") || response.equals("c"))) {
                scan.close();
                break;
            }
            System.out.print("Enter the name of the movie: ");
            String name = scan.nextLine();
            if (movieStore.getMovie(name) == null) {
                System.out.println("\n\nThe input you provided is not valid. Please try again\n");
                continue;
            }
            switch (response) {
                case "a":
                    if (!(movieStore.getMovie(name).isAvailable())) {
                    System.out.println("\n\n\n\nThe movie is not available for purchase. Please try again\n");
                    continue;
                    }
                    movieStore.action(name, "sell"); break;
                case "b": movieStore.action(name, "rent"); break;
                case "c": movieStore.action(name, "return"); break;
            }
            System.out.println("\n\nUPDATED MOVIES\n\n" + movieStore);
        }
    }

    public static void loadMovies(String fileName) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            String[] words = line.split("--");
            movieStore.addMovie(new Movie(words[0], words[1], Double.parseDouble(words[2])));
        }
        scanFile.close();
    }

}
