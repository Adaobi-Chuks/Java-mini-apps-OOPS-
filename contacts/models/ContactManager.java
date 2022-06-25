package models;

import java.util.ArrayList;

public class ContactManager {

    ArrayList<Contact> contacts;

    public ContactManager(ArrayList<Contact> contact) {
        this.contacts = new ArrayList<Contact>();
    }

    public Contact getContact(int index) {
        return new Contact(contacts.get(index));
    }

    public void setContact(Contact contact, int index) {
        this.contacts.set(index, new Contact(contact));
    }

    public void addContact(Contact contact) {
        this.contacts.add(new Contact(contact));
    }

    public void removeContact(String contactName) {
        if(contacts.isEmpty()) {
            throw new IllegalStateException ("Can't remove from an empty list");
        }
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(contactName)) {
                contacts.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < contacts.size(); i++) {
            temp += contacts.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

}
