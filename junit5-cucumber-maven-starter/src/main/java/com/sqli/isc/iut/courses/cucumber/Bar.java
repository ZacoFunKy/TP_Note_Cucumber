package com.sqli.isc.iut.courses.cucumber;

public class Bar {

    private String name;
    private int totalSeats;
    private int occupiedSeats;
    private double pignonBill;
    private double leblancBill;

    private int hasPignonAlreadyHadADrink = 0;

    public Bar(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.occupiedSeats = 0;
        this.leblancBill = 0;
        this.pignonBill = 0;
    }

    public boolean tryToEnter(int people) {
        if (occupiedSeats + people > totalSeats) {
            return false;
        }
        occupiedSeats += people;
        return true;
    }

    public void setOccupiedSeats(int numberOfPeople) {
        this.occupiedSeats = numberOfPeople;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public double orderCocktails(int quantity, int price) {
        return quantity * price;
    }

    public double payBill(double bill, String person) {
        if (person.equals("Pignon")) {
            pignonBill = bill;
            if (pignonBill == 10) {
                hasPignonAlreadyHadADrink = 1;
            } else {
                hasPignonAlreadyHadADrink = -1;
            }
        } else {
            leblancBill = bill;
        }
        return 0;
    }

    public boolean isPignonHappy() {
        if (hasPignonAlreadyHadADrink == -1) {
            return false;
        }
        return true;
    }


    public void setIsPignonHappy(int isPignonHappy) {
        this.hasPignonAlreadyHadADrink = isPignonHappy;
    }
}
