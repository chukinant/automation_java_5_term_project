package ru.netology.term.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import ru.netology.term.data.DataHelper;
import ru.netology.term.data.DbDataHelper;
import ru.netology.term.pages.BuyOnCreditPage;
import ru.netology.term.pages.LandingPage;

public class Steps {
    public static LandingPage landingPage;
    public static BuyOnCreditPage buyOnCreditPage;

    @Given("открыта страница выбора способа оплаты тура {string}")
    public void openLandingPage(String url) {
        Selenide.open(url);
        landingPage = new LandingPage();
    }

    @After
    public void cleanDB() {
        DbDataHelper.cleanDB();
    }

    @And("пользователь нажимает на кнопку \"Купить в кредит\"")
    public void chooseBuyOnCredit() {
        buyOnCreditPage = landingPage.chooseCredit();
    }

    @Then("отображается заголовок \"Кредит по данным карты\" и форма ввода данных карты")
    public void buyOnCreditHeaderIsVisible() {
        buyOnCreditPage.headerShouldHaveText();

    }

    @When("пользователь указывает валидные значения в полях: \"Номер карты\", \"Месяц\", \"Год\", \"Владелец\", \"CVC\"")
    public void fillFormWithValidCardInfo() {
        DataHelper.CardInfo cardInfo = DataHelper.getApprovedCardInfo();
        buyOnCreditPage.fillForm(cardInfo);
    }

    @And("нажимает кнопку \"Продолжить\"")
    public void submit() {
        buyOnCreditPage.submit();
    }

    @Then("появляется сообщение об одобрении кредита банком")
    public void findApprovedMsg() {
        buyOnCreditPage.findMsgTransactionApproved();
    }

    @When("пользователь не указывает ничего в полях карты")
    public void doNothing() {
    }

    @Then("появляется сообщение об обязательности поля для заполнения")
    public void fieldIsRequiredDisplayed() {
        buyOnCreditPage.findMsgFieldIsRequired();
    }

    @Then("появляется сообщение о неверном формате номера карты")
    public void wrongFormatDisplayed() {
        buyOnCreditPage.findMsgInvalidFormatCardNumber();
    }

    @When("пользователь заполняет форму, указав номер DECLINED карты")
    public void fillFormWithDeclinedCardInfo() {
        DataHelper.CardInfo cardInfo = DataHelper.getDeclinedCardInfo();
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("появляется сообщение об отклонении операции банком")
    public void transactionDeclinedDisplayed() {
        buyOnCreditPage.findMsgTransactionDeclined();
    }

    @When("пользователь заполняет форму, указав в поле \"Номер карты\" {int} цифр")
    public void fillFormNotCompleteCardNumber(int x) {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidCardNumber(x);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Номер карты\" комбинацию из цифр и спецсимволов")
    public void fillCardNumberWithSpChars() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardNumberWithSpChars(16);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Номер карты\" комбинацию из цифр и букв")
    public void fillCardNumberWithSpLetters() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardNumberWithLetters("en", 16);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("значение не введено в поле \"Номер карты\"")
    public void cardNumberIsNotEntered() {
        Assertions.assertTrue(buyOnCreditPage.getValueInCardNumber().length() < 16);
    }

    @When("пользователь заполняет форму, оставив поле \"Месяц\" пустым")
    public void fillFormWithoutMonth() {
        DataHelper.CardInfo cardInfo = DataHelper.getApprovedCardInfo();
        buyOnCreditPage.fillFormWithOneFieldEmpty(cardInfo, "cardMonth");
    }

    @When("пользователь заполняет форму, оставив поле \"Год\" пустым")
    public void fillFormWithoutYear() {
        DataHelper.CardInfo cardInfo = DataHelper.getApprovedCardInfo();
        buyOnCreditPage.fillFormWithOneFieldEmpty(cardInfo, "cardYear");
    }

    @When("пользователь заполняет форму, оставив поле \"Владелец\" пустым")
    public void fillFormWithoutHolder() {
        DataHelper.CardInfo cardInfo = DataHelper.getApprovedCardInfo();
        buyOnCreditPage.fillFormWithOneFieldEmpty(cardInfo, "cardHolder");
    }

    @When("пользователь заполняет форму, оставив поле \"CVC\" пустым")
    public void fillFormWithoutCVC() {
        DataHelper.CardInfo cardInfo = DataHelper.getApprovedCardInfo();
        buyOnCreditPage.fillFormWithOneFieldEmpty(cardInfo, "cardCVC");
    }

    @When("пользователь заполняет форму, указав \"00\" поле \"Месяц\"")
    public void fillFormZeroMonth() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidHardcodedMonth("00");
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав \"13\" поле \"Месяц\"")
    public void fillFormThirteenthMonth() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidHardcodedMonth("13");
        buyOnCreditPage.fillForm(cardInfo);
    }
    @When("пользователь заполняет форму, указав одну цифру в поле \"Месяц\"")
    public void fillFormOneDigitMonth() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidMonth(1);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Месяц\" комбинацию из цифры и спецсимвола")
    public void fillMonthWithSpChars() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardMonthWithSpChars(2);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Месяц\" комбинацию из цифры и буквы")
    public void fillCardMonthWithLetters() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardMonthWithLetters("en",2);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав одну цифру в поле \"Год\"")
    public void fillFormOneDigitYear() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidYear(1);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Год\" комбинацию из цифры и спецсимвола")
    public void fillYearWithSpChars() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardYearWithSpChars(2);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Год\" комбинацию из цифры и буквы")
    public void fillCardYearWithLetters() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardYearWithLetters("en",2);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"CVC/CVV\" <x> цифр")
    public void fillFormOneOrTwoDigitCvc(int x) {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidYear(x);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"CVC\" комбинацию из цифр и спецсимволов")
    public void fillCvcWithSpChars() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardCvcWithSpChars(3);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"CVC\" комбинацию из цифр и букв")
    public void fillCardCvcWithLetters() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCardCvcWithLetters("en",3);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("значение не введено в поле \"Месяц\"")
    public void monthIsNotEntered() {
        Assertions.assertTrue(buyOnCreditPage.getValueInCardMonth().length() < 2);
    }

    @Then("значение не введено в поле \"Год\"")
    public void yearIsNotEntered() {
        Assertions.assertTrue(buyOnCreditPage.getValueInCardYear().length() < 2);
    }

    @Then("значение не введено в поле \"CVC\"")
    public void cvcIsNotEntered() {
        Assertions.assertTrue(buyOnCreditPage.getValueInCardCVC().length() < 3);
    }

    @Then("появляется сообщение о неверном формате данных месяца")
    public void invalidFormatMonthDisplayed () {
        buyOnCreditPage.findMsgInvalidFormatMonth();
    }

    @Then("появляется сообщение о неверном формате данных года")
    public void invalidFormatYearDisplayed () {
        buyOnCreditPage.findMsgInvalidFormatYear();
    }


    @Then("появляется сообщение о неверном формате данных кода")
    public void invalidFormatCvcDisplayed () {
        buyOnCreditPage.findMsgInvalidFormatCVC();
    }

    @When("пользователь заполняет форму, указав текущие месяц и год в соответствующих полях")
    public void fillFormCurrentMonthAndYear() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(0,0);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав прошлый месяц в поле \"Месяц\" и текущий год в поле \"Год\"")
    public void fillFormLastMonth() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(-1,-1);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("появляется сообщение о неверном сроке действия карты")
    public void invalidExpirationDateDisplayed () {
        buyOnCreditPage.findMsgCardExpirationInvalidDateMonth();
    }

    @When("пользователь заполняет форму, указав прошлый период в полях \"Месяц\" и \"Год\"")
    public void fillFormPastDate() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(-1,-300);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("появляется сообщение о неверном сроке действия карты или истекшем сроке действия карты")
    public void cardExpiredDisplayed () {
        try {
            buyOnCreditPage.findMsgCardExpiredDateYear();
        } catch (AssertionError e) {
            buyOnCreditPage.findMsgCardExpirationInvalidDateMonth();
        }
    }

    @When("пользователь заполняет форму, указав следующий месяц в поле \"Месяц\" и текущий год + 6 в поле \"Год\"")
    public void fillFormSeventyThreeMonthDate() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(73,73);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("появляется сообщение о неверно указанном сроке действия карты")
    public void cardInvalidExpirationDateDisplayed () {
        buyOnCreditPage.findMsgCardExpirationInvalidDateYear();
    }

    @When("пользователь заполняет форму, указав текущий месяц в поле \"Месяц\" и текущий год + 6 в поле \"Год\"")
    public void fillFormSeventyTwoMonthDate() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(72,72);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав полях \"Месяц\" и \"Год\" период, отстоящий более чем на 6 лет от текущего")
    public void fillFormMoreThanSeventyThreeMonthsDate() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoSpecifiedDate(73,300);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Владелец\" имя и фамилию, состоящие из 1 буквы")
    public void fillFormOneLetterCardHolderName() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoOneLetterName("en");
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Владелец\" только имя")
    public void fillFormOnlyFirstName() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidName("en",  0);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"Владелец\" комбинацию из букв с невалидным спецсимволом")
    public void fillFormCardHolderNameWithSpChars() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidNameWithSpChars("en", 15);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("появляется сообщение о неверном формате данных владельца")
    public void cardInvalidCardHolderDisplayed () {
        buyOnCreditPage.findMsgInvalidFormatCardHolder();
    }

    @When("пользователь заполняет форму, указав в поле \"Владелец\" комбинацию из букв с цифрами")
    public void fillFormCardHolderNameWithNumbers() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidNameWithDigits(10);
        buyOnCreditPage.fillForm(cardInfo);
    }

//    @When("пользователь заполняет форму, применяя нелатинские буквы {String} в поле \"Владелец\"")
//    public void fillFormWithNonLatinLettersInField(String locale) {
//        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidName(locale, 10, 15);
//        buyOnCreditPage.fillForm(cardInfo);
//    }

    @When("пользователь заполняет форму, указав три одинаковых цифры в поле \"CVC\"")
    public void fillFormCvcOfSameThreeDigits() {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoCvcOfThreeSameDigits();
        buyOnCreditPage.fillForm(cardInfo);
    }

    @When("пользователь заполняет форму, указав в поле \"CVC\" {int} цифр")
    public void fillFormCvcOfLessThanThreeDigits(int x) {
        DataHelper.CardInfo cardInfo = DataHelper.getCardInfoInvalidCVC(x);
        buyOnCreditPage.fillForm(cardInfo);
    }

    @Then("пользователь обновляет страницу")
    public void refreshPage() {
        Selenide.refresh();
    }

    @And("повторно указывает данные карты")
    public void fillFormAgain() {
        buyOnCreditPage.fillFormAgain();
    }

    @Then("появляется сообщение, что заказ уже совершен")
    public void orderIsAlreadyMade() {
        buyOnCreditPage.findMsgTransactionIsAlreadyMade();
    }

    @And("в таблице credit_request_entity БД появляется запись со статусом APPROVED")
    public void shouldAppearRecordWithStatusApproved() {
        Assertions.assertEquals("APPROVED", DbDataHelper.getCreditStatus());
    }

    @And("в таблице order_entity БД появляется новая запись")
    public void shouldAppearNewRecord() {
        Assertions.assertEquals(1, DbDataHelper.countOrders());
    }
}