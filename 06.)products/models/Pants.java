package models;

import java.util.Objects;

public class Pants extends Product implements Discountable{
    private int waist;
    
    public Pants(int waist, double price, String color, String brand) {
        super(price, color, brand);
        this.waist = waist;
    }

    public Pants(Pants source) {
        super(source);
        this.waist = source.waist;
    }

    public int getWaist() {
        return this.waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pants)) {
            return false;
        }
        Pants pants = (Pants) o;
        return waist == pants.waist
            && super.getPrice() == pants.getPrice()
            && super.getColor().equals(pants.getColor())
            && super.getBrand().equals(pants.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(waist, super.getPrice(), super.getColor(),super.getPrice());
    }

    @Override
    public String toString() {
        return "PANTS" +
            "\t" + getWaist() +
            "\t" + super.getPrice() +
            "\t" + super.getColor() +
            "\t" + super.getBrand();
    }

    @Override
    public void fold() {
        System.out.println("Fold");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    @Override
    public void discount() {
        super.setPrice(super.getPrice() / 2);
    }

    
}
