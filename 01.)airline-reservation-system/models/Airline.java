package models;

public class Airline {

    private Person[] seats;

    public Airline() {
        this.seats = new Person[11];
    }

    public Person getPerson(int index){
        return new Person(this.seats[index]);
    }

    public void setPerson(Person person) {
        this.seats[person.getSeatNumber() - 1] = new Person(person);
    }

    public void createReservation(Person person) {
        while (seats[person.getSeatNumber() - 1] != null) {
            System.out.println(person.getName() + ", seat: "  + person.getSeatNumber() 
            + " is already taken. Please choose another one.\n");
            person.chooseSeat();
            person.getSeatNumber();
        }
        seats[(person.getSeatNumber() - 1)] = new Person(person);
        System.out.println("Thank you " + person.getName() 
        + " for flying with Java airlines. Your seat number is " + person.getSeatNumber() + ".\n");
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < seats.length; i++) {
            if (this.seats[i] == null) {
                temp += "Seat " + (i+1) + " is empty.\n\n";
            }else {
                temp += this.seats[i].toString() + "\n\n";
            }
        }
        return temp;
    }
}