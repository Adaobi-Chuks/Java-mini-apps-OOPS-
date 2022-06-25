package src.main.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {

    private static final double TAX_RATE = 0.13;
    ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<Item>();
    }

    public Item getItems(int index) {
        return new Item(this.items.get(index));
    }

    public void setItem(int index, Item item) {
        this.items.set(index, new Item(item));
    }

    public boolean add(Item item) {
        if(items.contains(item)) {
            return false;
        } else {
            items.add(new Item(item));
            return true;
        }
    }

    public void remove(String name) {
        if(items.isEmpty()) {
            throw new IllegalStateException("You can not remove item because the cqart is empty");
        }
        for (int i = 0; i < this.items.size(); i++) {
            if(this.items.get(i).getName().equals(name)) {
                this.items.remove(i);
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < this.items.size(); i++) {
            temp += this.items.get(i).toString();
            temp += "\n";
        }
        return temp;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public void clear() {
        this.items.clear();
    }

    public double getSubtotal() {
        return this.items.stream().mapToDouble((item) -> item.getPrice()).sum();
    }

    public double getTax(double subtotal) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return Double.parseDouble(formatter.format(subtotal * TAX_RATE));
    }

    public double getTotal(double subtotal, double tax) {
        return subtotal + tax;
    }

    public String checkout() {
        if (this.items.isEmpty()) {
            throw new IllegalStateException("INVALID STATE");
        }
        return "\tRECEIPT\n\n" +
            "\tSubtotal: $" + getSubtotal() + "\n" +
            "\tTax: $" + getTax(getSubtotal()) + "\n" +
            "\tTotal: $" + getTotal(getSubtotal(), getTax(getSubtotal())) + "\n";
    }

}