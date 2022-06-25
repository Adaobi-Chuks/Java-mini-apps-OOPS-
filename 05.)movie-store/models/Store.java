package models;

import java.util.ArrayList;

public class Store {
    
    ArrayList<Movie> movieStore;

    public Store() {
        this.movieStore = new ArrayList<Movie>();
    }

    public Movie getMovie(int index) {
        return new Movie(this.movieStore.get(index));
    }

    public Movie getMovie(String movieName) {
        for (int i = 0; i < movieStore.size(); i++) {
            if(movieStore.get(i).getName().equals(movieName)) {
                return movieStore.get(i);
            }
        }
        return null;
    }

    public void setMovie(Movie movie, int index) {
        this.movieStore.set(index, new Movie(movie));
    }

    public void addMovie(Movie movie) {
        this.movieStore.add(new Movie(movie));
    }

    public void action(String movieName, String action) {
        if(movieStore.isEmpty()) {
            throw new IllegalStateException("Can not perform an action from an empty store");
        }
        if(!(action.equalsIgnoreCase("sell") || action.equalsIgnoreCase("rent") || action.equalsIgnoreCase("return")) || movieName.isBlank() || movieName == null) {
            throw new IllegalArgumentException("Invalid Parameter");
        }

        for (int i = 0; i < movieStore.size(); i++) {
            if(movieStore.get(i).getName().equals(movieName)) {
                switch(action) {
                    case "sell" : movieStore.remove(i); break;
                    case "rent" : movieStore.get(i).setIsAvailable(false); break;
                    case "return" : movieStore.get(i).setIsAvailable(true);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < movieStore.size(); i++) {
            temp += movieStore.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

}
