package src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import src.main.models.Cart;
import src.main.models.Item;
import src.main.models.Store;

public class Main {

    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {

        try{
            loadItems("products.txt");
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            manageItems();
            System.out.println();
        }

    }
   
    public static void  manageItems() {

        Scanner scan = new Scanner(System.in);

        while(true) {
            
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);
            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart \n\tc) Checkout");

            String reply = scan.next();
            scan.nextLine();

            switch(reply) {

                case "a": System.out.print("\nChoose an aisle number between: 1 - 7: ");
                int row = scan.hasNextInt() ? scan.nextInt() - 1 : 404;
                scan.nextLine();

                System.out.print("Choose an item number between: 1 - 3: ");
                int column = scan.hasNextInt() ? scan.nextInt() - 1 : 404;
                scan.nextLine();

                if(row == 404 || column == 404) {
                    continue;
                }
                if(row < 0 || column < 0 || row > 6 || column > 2) {
                    continue;
                }
                Item item = store.getItem(row, column);
                if(cart.add(item) == true) {
                    System.out.println(item.getName() + " was added to your shopping cart.");
                } else {
                    System.out.println(item.getName() + " is already in your shopping cart.");
                } break;

                case "b": if(cart.isEmpty()) {
                    continue;
                };
                System.out.print("Enter the item you'd like to remove: ");
                String name = scan.next();
                scan.nextLine();
                cart.remove(name); break;

                case "c": if(cart.isEmpty()) {
                    continue;
                };
                System.out.println(cart.checkout());
                scan.close();
                return;
                default: continue;
            }
            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.print("Enter anything to continue: ");
            scan.nextLine();
        }
    }

    public static void loadItems(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

            for (int i = 0; scanFile.hasNextLine(); i++) {
                String line = scanFile.nextLine();
                String[] items = line.split(";");
                for (int j = 0; j < items.length; j++) {
                    String[] field = items[j].split("=");
                    store.setItem(i, j, new Item(field[0], Double.parseDouble(field[1])));
                }
            }
            scanFile.close();
    }
}
