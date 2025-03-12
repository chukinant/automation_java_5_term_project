package ru.netology.term.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.term.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BuyOnCreditPage {
    private final SelenideElement cardInfoFormHeader = $x("//*[contains(text(),'Кредит по данным карты')]");
    private final SelenideElement cardNumber = $x("//*[contains(text(),'Номер карты')]//following-sibling::*//input");
    private final SelenideElement cardMonth = $x("//*[contains(text(),'Месяц')]//following-sibling::*//input");
    private final SelenideElement cardYear = $x("//*[contains(text(),'Год')]//following-sibling::*//input");
    private final SelenideElement cardHolder = $x("//*[contains(text(),'Владелец')]//following-sibling::*//input");
    private final SelenideElement cardCVC = $x("//*[contains(text(),'CVC/CVV')]//following-sibling::*//input");
    private final SelenideElement submitButton = $x("//span[contains(normalize-space(.), 'Продолжить')]//ancestor-or-self::button");
    private final SelenideElement transactionNotification = $("div.notification.notification_status_ok");
    private final SelenideElement msgInvalidCardNumber = $x(cardNumber+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidMonth = $x(cardMonth+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidYear = $x(cardYear+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCardHolder = $x(cardHolder+"/..//following-sibling::*[@class='input__sub']");
    private final SelenideElement msgInvalidCVC = $x(cardCVC+"/..//following-sibling::*[@class='input__sub']");

    public BuyOnCreditPage () {
        cardInfoFormHeader.shouldBe(Condition.visible);
    }

    public void headerShouldHaveText() {
        cardInfoFormHeader.shouldHave(Condition.text("Кредит по данным карты"));
    }

    public void fillForm(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getNumber());
        cardMonth.setValue(cardInfo.getMonth());
        cardYear.setValue(cardInfo.getYear());
        cardHolder.setValue(cardInfo.getHolder());
        cardCVC.setValue(cardInfo.getCvc());
    }

    public void submit() {
        submitButton.click();
    }

    public void findMsgTransactionApproved() {
        transactionNotification.shouldBe(Condition.visible).
                shouldHave(Condition.text("одобрена"));
    }

    public void findMsgTransactionDeclined() {
        transactionNotification.shouldBe(Condition.visible).
                shouldHave(Condition.text("отклонена"));
    }

    public void findMsgInvalidCardNumber() {
        msgInvalidCardNumber.shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }

    public void findMsgInvalidMonth() {
        msgInvalidMonth.shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }

    public void findMsgInvalidYear() {
        msgInvalidYear.shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }

    public void findMsgInvalidCardHolder() {
        msgInvalidCardHolder.shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }

    public void findMsgInvalidCVC() {
        msgInvalidCVC.shouldBe(Condition.visible).
                shouldHave(Condition.text("Ошибка"));
    }
}
