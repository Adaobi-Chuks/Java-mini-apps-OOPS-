package models;

public abstract class Product implements Comparable <Product>{
    private double price;
    private String color;
    private String brand;

    public Product(double price, String color, String brand) {
        this.price = price;
        this.color = color;
        this.brand = brand;
    }

    public Product(Product source) {
        this.price = source.price;
        this.color = source.color;
        this.brand = source.brand;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public abstract void fold();

    @Override
    public int compareTo(Product specifiedObject) {
        String className = this.getClass().getSimpleName();
        String sClassName = specifiedObject.getClass().getSimpleName();
        if (!(className.equals(sClassName))) {
            return className.compareTo(sClassName);
        }
        return (int) (Math.round(this.getPrice() - specifiedObject.getPrice()));
    }

}
