package ru.netology.term.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final SelenideElement purchaseOnCreditButton = $x("//*[contains(text(),'Купить в кредит')]/ancestor-or-self::button");
    private final SelenideElement purchaseButton = $x("//*[contains(text(),'Купить')]/ancestor-or-self::button");
    private final SelenideElement tripOfTheDayHeader = $x("//*[contains(text(),'Путешествие дня')]");

    public LandingPage() {
        tripOfTheDayHeader.shouldBe(Condition.visible);
        purchaseButton.shouldBe(Condition.visible);
        purchaseOnCreditButton.shouldBe(Condition.visible);
    }

    public BuyOnCreditPage chooseCredit() {
        purchaseOnCreditButton.click();
        return new BuyOnCreditPage();
    }
}