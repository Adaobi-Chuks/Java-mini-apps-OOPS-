package models;

public class Movie {

    private static final double spD = 2.25;
    private static final double spB = 4.25;
    private static final double rpD = 0.99;
    private static final double rpB = 1.99;
    
    private String name;
    private String format;
    private double rating;
    private double sellingPrice;
    private double rentalPrice;
    private boolean isAvailable;


    public Movie(String name, String format, double rating) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must contain a value");
        }
        if (!(format.equals("DVD") || format.equals("Blue-Ray"))) {
            throw new IllegalArgumentException("format must be DVD or Blue-Ray");
        }
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Invalid rating");
        }
        this.name = name;
        this.format = format;
        this.rating = rating;
        this.sellingPrice = this.format.equals("DVD") ? spD : spB;
        this.rentalPrice = this.format.equals("DVD") ? rpD : rpB;
        this.isAvailable = true;
    }

    public Movie(Movie source) {
        this.name = source.name;
        this.format = source.format;
        this.rating = source.rating;
        this.sellingPrice = source.sellingPrice;
        this.rentalPrice = source.rentalPrice;
        this.isAvailable = source.isAvailable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException ("Invalid Parameters");
        }
        this.name = name;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        if(!format.equalsIgnoreCase("DVD") || !format.equalsIgnoreCase("Blue-Ray")) {
            throw new IllegalArgumentException ("Invalid Parameters");
        }
        this.format = format;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        if(rating < 0 || rating > 10) {
            throw new IllegalArgumentException ("Invalid Parameters");
        }
        this.rating = rating;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    private void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getRentalPrice() {
        return this.rentalPrice;
    }

    private void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "\t Name: " + name + "\n" +
        "\t Format: " + format + "\n" +
        "\t Rating: " + rating + "\n" +
        "\t Selling Price: " + sellingPrice + "\n" +
        "\t Rental Price: " + rentalPrice + "\n" +
        "\t Availability: " + (isAvailable ? "in-stock" : "rented") + "\n";
    }

}
