package ru.netology.term.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.netology.term.data.DataHelper;
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

    @And("пользователь нажимает на кнопку \"Купить в кредит\"")
    public void chooseBuyOnCredit() {
        buyOnCreditPage = landingPage.chooseCredit();
    }

    @Then("отображается заголовок \"Кредит по данным карты\"")
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

//    @When("пользователь пытается авторизоваться с именем {string} и паролем {string}")
//    public void login(String login, String password) {
//        verificationPage = loginPage.validLogin(login, password);
//    }
//
//    @And("пользователь вводит проверочный код 'из смс' {string}")
//    public void setValidCode(String verificationCode) {
//        accountPage = verificationPage.validVerification(verificationCode);
//    }
//
//    @Then("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
//    public void verifyAccountPage() {
//        accountPage.verifyIsAccountPage();
//    }
//
//    @Then("появляется ошибка о неверном коде проверки")
//    public void verifyCodeIsInvalid() {
//        verificationPage.verifyCodeIsInvalid();
//    }
//
//    @Given("пользователь залогинен с именем {string} и паролем {string}")
//    public void userIsLoggedIn(String username, String password) {
//        openLoginPage("http://localhost:9999");
//        verificationPage = loginPage.validLogin(username,password);
//        accountPage = verificationPage.validVerification("12345");
//    }
//
//    @When("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
//    public void moneyTransfer(int amount, String cardNumber, int cardToIndex) {
//        addToCardPage = accountPage.initiateTransferToCard(cardToIndex - 1);
//        addToCardPage.moneyTransfer(cardNumber, amount);
//    }
//
//    @Then("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
//    public void moneyAreTransferred(int cardToIndex, int balance) {
//        accountPage.checkCardBalance(cardToIndex - 1, balance);
//    }
}
