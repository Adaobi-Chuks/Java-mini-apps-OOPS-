package models;

import java.util.Arrays;

public class Person {
    private String name;
    private String nationality;
    private String dateOfBirth;
    private String[] passport;
    private int seatNumber;

    public Person(String name, String nationality, String dateOfBirth, int seatNumber) {
        this.name = name;
        this.nationality = nationality;
        this. dateOfBirth = dateOfBirth;
        this.seatNumber = seatNumber;
        this.passport = new String[3];
    }

    public Person(Person source) {
        this.name = source.name;
        this.nationality = source.nationality;
        this.dateOfBirth = source.dateOfBirth;
        this.seatNumber = source.seatNumber;
        this.passport = Arrays.copyOf(source.passport, source.passport.length);
    }

    public String getName() {
        return this.name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public String[] getPassport() {
        return Arrays.copyOf(this.passport, this.passport.length);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPassport() {
        this.passport = new String[] {this.name, this.nationality, this.dateOfBirth};
    }

    public boolean applyPassport() {
        int randomNumber = (int)(Math.random() * 2);
        if (randomNumber == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void chooseSeat() {
        int randomNumber = (int)(Math.random() * 11);
        this.seatNumber = randomNumber += 1;
    }

    public String toString() {
        return  "Name: " + this.getName() 
        + "\nNationality: " + this.getNationality() 
        + "\nDate of Birth: " + this.getDateOfBirth() 
        + "\nSeat Number: " + this.getSeatNumber() 
        + "\nPassport: " + Arrays.toString(this.passport);
    }
}
