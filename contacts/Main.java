import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import models.Contact;
import models.ContactManager;

public class Main {

    static ContactManager contactManager;
    public static void main(String[] args) {
        
        try {
            loadContacts("contacts.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Process Complete");
            System.out.println(contactManager);
            manageContacts();
        }
        
    }

    public static void manageContacts() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Would you like to \n\ta) add another contact\n\tb) remove a contact \n\tc) exit");
            String response = scan.nextLine();
            if (response.equals("a")) {
               System.out.print("\tName: ");
               String name = scan.nextLine();
               System.out.print("\tPhone Number: ");
               String phoneNumber = scan.nextLine();
               System.out.print("\tBirth Date: ");
               String birthDate = scan.nextLine();
               if (name.isBlank() || phoneNumber.isBlank() || phoneNumber.length() < 5) {
                   System.out.println("\nThe input you provided is not valid. Registration failed.");
               } else {
                   try {
                       contactManager.addContact(new Contact(name, phoneNumber, birthDate));
                   } catch (ParseException e) {
                       System.out.println(e.getMessage());
                   } finally {
                       System.out.println("\n\nUPDATED CONTACTS\n\n" + contactManager);
                   }
               }
            } else if (response.equals("b")) {
                System.out.println("\nWho would you like to remove?");
                contactManager.removeContact(scan.nextLine());
                System.out.println("\n\nUPDATED CONTACTS\n\n" + contactManager);
            } else {
                break;
            }
        }
        scan.close();
    }

    public static void loadContacts(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);
        while (scanFile.hasNextLine()) {
            try {
               Contact contact = new Contact(scanFile.next(), scanFile.next(), scanFile.next());
               contactManager.addContact(contact);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        scanFile.close();
    }

}
