package ru.netology.term.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final SelenideElement purchaseInCreditButton = $x("//*[contains(text(),'Купить в кредит')]/ancestor-or-self::button");
    private final SelenideElement purchaseButton = $x("//*[contains(text(),'Купить')]/ancestor-or-self::button");
    private final SelenideElement tripOfTheDayHeader = $x("//*[contains(text(),'Путешествие дня']");
    private final SelenideElement cardInfoFormHeader = $x("//*[contains(text(),'Кредит по данным карты']");
    private final SelenideElement cardNumber = $x("//*[contains(text(),'Номер карты')]//following-sibling::*//input");
    private final SelenideElement cardMonth = $x("//*[contains(text(),'Месяц')]//following-sibling::*//input");
    private final SelenideElement cardYear = $x("//*[contains(text(),'Год')]//following-sibling::*//input");
    private final SelenideElement cardHolder = $x("//*[contains(text(),'Владелец')]//following-sibling::*//input");
    private final SelenideElement cardCVC = $x("//*[contains(text(),'CVC/CVV')]//following-sibling::*//input");
    private final SelenideElement submitButton = $x("//span[contains(normalize-space(.), 'Продолжить')]//ancestor-or-self::button");
    private final SelenideElement TransactionNotification = $("div.notification.notification_status_ok");
    private final SelenideElement msgInvalidNumber = $x(cardNumber+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidMonth = $x(cardMonth+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidYear = $x(cardYear+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCardHolder = $x(cardHolder+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCVC = $x(cardCVC+"/..//following-sibling::*[@class='input__sub']");

//    public VerificationPage validLogin(DataHelper.AuthInfo info) {
//        usernameField.setValue(info.getUsername());
//        passwordField.setValue(info.getPassword());
//        submitButton.click();
//        return new VerificationPage();
//    }
}