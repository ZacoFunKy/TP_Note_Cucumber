Feature: Experience at the Juste bar

  Background:
    Given the bar "le Juste" is a cocktail bar
    And there are only 10 seats in the bar
    And Mr Pignon and Mr Leblanc are at the bar "le Juste"

  Scenario: Arrival at the bar with 9 people inside
    Given there are already 9 people in the bar
    When they try to enter the bar
    Then the bar should be full, and they are refused entry

  Scenario: Arrival at the bar with 8 people inside
    Given there are already 8 people in the bar
    When they try to enter the bar
    Then the bar should be full and the person behind them is refused entry

    When they each order a cocktail of the month priced at 10€
    And Mr Leblanc pays for both
    And they finish their drinks
    Then the bill is checked, and Mr Leblanc pays
    And Mr Pignon is happy because they only consumed one drink

  Scenario: Arrival at the bar with 3 people inside
    Given there are already 3 people in the bar
    When they each order a cocktail of the month priced at 10€
    And they finish their drinks
    Then they check their individual bills
    And Mr Pignon pays his bill
    And Mr Leblanc insists on ordering more drinks

    When Mr Leblanc orders 2 more cocktails of the month
    And they finish their drinks
    Then Mr Leblanc checks the bill and pays
    And Mr Pignon is sad because he knows that after one cocktail, he will have a very bad night
