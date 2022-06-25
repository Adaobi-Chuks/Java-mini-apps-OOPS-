package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contact {

    private String name;
    private int age;
    private String birthDate;
    private String phoneNumber;


    public Contact(String name, String birthDate, String phoneNumber) throws ParseException {
        if (name.isBlank() || name == null || phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() < 5) {
            throw new IllegalArgumentException("Invalid Paramemeters");
        }
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.age = toAge(birthDate);
    }

    public Contact(Contact source) {
        this.name = source.name;
        this.age = source.age;
        this.birthDate = source.birthDate;
        this.phoneNumber = source.phoneNumber;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.isBlank() || name == null) {
            throw new IllegalArgumentException("Invalid Paramemeters");
        }
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = birthDate;
        setAge(toAge(birthDate));
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() < 5) {
            throw new IllegalArgumentException("Invalid Paramemeters");
        }
        this.phoneNumber = phoneNumber;
    }

    public int toAge(String birthDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyy");
        formatter.setLenient(false);
        return (int)(TimeUnit.MILLISECONDS.toDays(new Date().getTime() - formatter.parse(birthDate).getTime()) / 365);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
        "Phone number: " + this.phoneNumber + "\n" +
        "Birth Date: " + this.birthDate + "\n" +
        "Age: " + this.age + " year old\n";
    }


}
