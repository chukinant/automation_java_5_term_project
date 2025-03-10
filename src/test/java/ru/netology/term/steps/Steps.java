package ru.netology.term.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static AccountPage accountPage;
    private static AddToCardPage addToCardPage;

    @Given("открыта страница с формой авторизации {string}")
    public void openLoginPage(String url) {
        Selenide.open(url);
        loginPage = new LoginPage();
    }

    @Given("на картах по {int} рублей")
    public void resetBalances(int balance) {
        var api_helper = new API_Helper();
        api_helper.resetCardsBalances();
    }

    @When("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void login(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @And("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        accountPage = verificationPage.validVerification(verificationCode);
    }

    @Then("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyAccountPage() {
        accountPage.verifyIsAccountPage();
    }

    @Then("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.verifyCodeIsInvalid();
    }

    @Given("пользователь залогинен с именем {string} и паролем {string}")
    public void userIsLoggedIn(String username, String password) {
        openLoginPage("http://localhost:9999");
        verificationPage = loginPage.validLogin(username,password);
        accountPage = verificationPage.validVerification("12345");
    }

    @When("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void moneyTransfer(int amount, String cardNumber, int cardToIndex) {
        addToCardPage = accountPage.initiateTransferToCard(cardToIndex - 1);
        addToCardPage.moneyTransfer(cardNumber, amount);
    }

    @Then("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void moneyAreTransferred(int cardToIndex, int balance) {
        accountPage.checkCardBalance(cardToIndex - 1, balance);
    }
}
