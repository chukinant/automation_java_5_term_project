package ru.netology.term.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.term.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BuyOnCreditPage {
    private final SelenideElement cardInfoFormHeader = $x("//*[contains(text(),'Кредит по данным карты')]");
    private final SelenideElement cardInfoForm = $x("//form");
    private final SelenideElement submitButton = $x("//span[contains(normalize-space(.), 'Продолжить')]//ancestor-or-self::button");
    private final SelenideElement cardNumber = $x("//*[contains(text(),'Номер карты')]//following-sibling::*//input");
    private final SelenideElement cardMonth = $x("//*[contains(text(),'Месяц')]//following-sibling::*//input");
    private final SelenideElement cardYear = $x("//*[contains(text(),'Год')]//following-sibling::*//input");
    private final SelenideElement cardHolder = $x("//*[contains(text(),'Владелец')]//following-sibling::*//input");
    private final SelenideElement cardCVC = $x("//*[contains(text(),'CVC/CVV')]//following-sibling::*//input");
    private final SelenideElement transactionNotification = $("div.notification.notification_status_ok");
    private final SelenideElement msgFieldIsRequired = $x("//*[contains(text(),'Владелец')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCardNumber = $x("//*[contains(text(),'Номер карты')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidMonth = $x("//*[contains(text(),'Месяц')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidYear = $x("//*[contains(text(),'Год')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCardHolder = $x("//*[contains(text(),'Владелец')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCVC = $x("//*[contains(text(),'CVC/CVV')]//following-sibling::*//input/..//following-sibling::*[@class='input__sub']");
    private DataHelper.CardInfo currentCardInfo;

    public BuyOnCreditPage () {
        cardInfoFormHeader.shouldBe(Condition.visible);
    }

    public String getValueInCardNumber () {
        return cardNumber.getValue();
    }

    public String getValueInCardMonth () {
        return cardMonth.getValue();
    }

    public String getValueInCardYear () {
        return cardYear.getValue();
    }

    public String getValueInCardHolder () {
        return cardHolder.getValue();
    }

    public String getValueInCardCVC() {
        return cardCVC.getValue();
    }

    public void headerShouldHaveText() {
        cardInfoFormHeader.shouldHave(Condition.text("Кредит по данным карты"));
        cardInfoForm.shouldBe(Condition.visible);
    }

    public void fillForm(DataHelper.CardInfo cardInfo) {
        currentCardInfo = cardInfo;
        cardNumber.setValue(cardInfo.getNumber());
        cardMonth.setValue(cardInfo.getMonth());
        cardYear.setValue(cardInfo.getYear());
        cardHolder.setValue(cardInfo.getHolder());
        cardCVC.setValue(cardInfo.getCvc());
    }

    public void fillFormAgain() {
        fillForm(currentCardInfo);
    }

    public void submit() {
        submitButton.click();
    }

    public void fillFormWithOneFieldEmpty(DataHelper.CardInfo cardInfo, String fieldToLeaveEmpty) {
        if (!"cardNumber".equals(fieldToLeaveEmpty)) {
            cardNumber.setValue(cardInfo.getNumber());
        }
        if (!"cardMonth".equals(fieldToLeaveEmpty)) {
            cardMonth.setValue(cardInfo.getMonth());
        }
        if (!"cardYear".equals(fieldToLeaveEmpty)) {
            cardYear.setValue(cardInfo.getYear());
        }
        if (!"cardHolder".equals(fieldToLeaveEmpty)) {
            cardHolder.setValue(cardInfo.getHolder());
        }
        if (!"cardCVC".equals(fieldToLeaveEmpty)) {
            cardCVC.setValue(cardInfo.getCvc());
        }
    }

    public void findMsgTransactionApproved() {
        transactionNotification.shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text("Успешно")).
                shouldHave(Condition.text("одобрена"));
    }

    public void findMsgTransactionDeclined() {
        transactionNotification.shouldBe(Condition.visible, Duration.ofSeconds(15)).
                shouldHave(Condition.text("отклонена"));
    }

    public void findMsgInvalidFormatCardNumber() {
        msgInvalidCardNumber.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверный формат"));
    }

    public void findMsgInvalidFormatMonth() {
        msgInvalidMonth.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверный формат"));
    }

    public void findMsgCardExpirationInvalidDateMonth() {
        msgInvalidMonth.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void findMsgInvalidFormatYear() {
        msgInvalidYear.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверный формат"));
    }

    public void findMsgCardExpiredDateYear() {
        msgInvalidYear.shouldBe(Condition.visible).
                shouldHave(Condition.text("Истёк срок действия карты"));
    }

    public void findMsgCardExpirationInvalidDateYear() {
        msgInvalidMonth.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void findMsgInvalidFormatCardHolder() {
        msgInvalidCardHolder.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверный формат"));
    }

    public void findMsgInvalidFormatCVC() {
        msgInvalidCVC.shouldBe(Condition.visible).
                shouldHave(Condition.text("Неверный формат"));
    }

    public void findMsgFieldIsRequired() {
        msgFieldIsRequired.shouldBe(Condition.visible).
                shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void findMsgTransactionIsAlreadyMade() {
        transactionNotification.shouldBe(Condition.visible, Duration.ofSeconds(15)).
                shouldHave(Condition.text("Операция уже одобрена Банком"));
    }
}
