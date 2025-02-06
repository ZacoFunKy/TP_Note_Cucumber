package com.sqli.isc.iut.courses.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BarExperienceSteps {

    private Bar bar;
    private boolean entryAllowed;
    private double pignonTotalBill;
    private double leblancTotalBill;
    private double paidBillAmount;

    @Given("the bar {string} is a cocktail bar")
    public void setupBar(String barName) {
        bar = new Bar(barName, 10); // La capacité est définie dans le background (10 sièges)
    }

    @Given("Mr Pignon and Mr Leblanc are at the bar {string}")
    public void mrPignonAndMrLeblancAreAtTheBar(String barName) {
        assertEquals("le Juste", barName, "Bar name should be 'le Juste'");
    }

    @Given("there are only {int} seats in the bar")
    public void setBarCapacity(int seats) {
        assertEquals(10, seats, "The bar should have 10 seats");
    }

    @Given("there are already {int} people in the bar")
    public void setPeopleInBar(int numberOfPeople) {
        bar.setOccupiedSeats(numberOfPeople);
    }

    @When("they try to enter the bar")
    public void attemptToEnterBar() {
        entryAllowed = bar.tryToEnter(2);
    }

    @Then("the bar should be full, and they are refused entry")
    public void assertEntryDenied() {
        assertFalse(entryAllowed, "Entry should be denied when the bar is full");
    }

    @Then("the bar should be full and the person behind them is refused entry")
    public void assertNextPersonDenied() {
        boolean nextPersonEntry = bar.tryToEnter(1);
        assertFalse(nextPersonEntry, "The next person should be denied entry");
    }

    @When("they each order a cocktail of the month priced at {int}€")
    public void orderCocktails(int price) {
        assertEquals(10, price, "The cocktail should cost 10€");
        pignonTotalBill += bar.orderCocktails(1, price);
        leblancTotalBill += bar.orderCocktails(1, price);
    }


    @When("Mr Leblanc pays for both")
    public void leblancPaysForBoth() {
        paidBillAmount = leblancTotalBill + pignonTotalBill;
        bar.payBill(paidBillAmount, "Both");
    }


    @Then("the bill is checked, and Mr Leblanc pays")
    public void checkBillAndLeblancPays() {
        assertEquals(20, paidBillAmount, "Total bill should be 20€");
        assertEquals(0, bar.payBill(paidBillAmount, "Leblanc"), "The bill should be fully paid");
    }

    @Then("Mr Pignon is happy because they only consumed one drink")
    public void verifyPignonHappiness() {
        assertTrue(bar.isPignonHappy(), "Pignon should be happy with one drink");
    }

    @When("they finish their drinks")
    public void finishDrinks() {
        // Pas d'action directe, les boissons sont finies
    }

    @When("no one pays for the other's drink")
    public void separatePayments() {
    }

    @Then("they check their individual bills")
    public void checkIndividualBills() {
        assertEquals(10, pignonTotalBill, "Pignon's bill should be 10€");
        assertEquals(10, leblancTotalBill, "Leblanc's bill should be 10€");
    }

    @Then("Mr Pignon pays his bill")
    public void pignonPaysHisBill() {
        double previousBill = pignonTotalBill;
        bar.payBill(pignonTotalBill, "Pignon");
    }

    @Then("Mr Leblanc insists on ordering more drinks")
    public void leblancOrdersMoreDrinks() {
        double previousBill = leblancTotalBill;
        leblancTotalBill += bar.orderCocktails(2, 10);
        assertTrue(leblancTotalBill > previousBill, "Leblanc's bill should increase after ordering more drinks");
    }

    @When("Mr Leblanc orders {int} more cocktails of the month")
    public void leblancOrdersAdditionalCocktails(int numberOfCocktails) {
        if(bar.isPignonHappy() == true) {
            bar.setIsPignonHappy(-1);
        }
        leblancTotalBill += bar.orderCocktails(numberOfCocktails, 10);
    }

    @Then("Mr Leblanc checks the bill and pays")
    public void leblancPaysHisBill() {
        assertEquals(0, bar.payBill(leblancTotalBill, "Leblanc"), "Leblanc should have fully paid his bill");
    }

    @Then("Mr Pignon is sad because he knows that after one cocktail, he will have a very bad night")
    public void pignonFeelsSad() {
        assertFalse(bar.isPignonHappy(), "Pignon should be sad after one drink");
    }
}
