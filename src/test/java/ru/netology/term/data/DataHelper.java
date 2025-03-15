package ru.netology.term.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String status;
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String generateRandomCardNumberMonthYearOrCvc(int length) {
        Faker faker = new Faker();
        String pattern = "#".repeat(length);
        return faker.numerify(pattern);
    }

    public static String generateRandomSequenceWithLettersForNumbers(String locale, int length) {
        Faker faker = new Faker(new Locale(locale));
        String sequence;
        do {
            sequence = faker.regexify("[A-Za-z0-9]{" + length + "}");
        } while (!sequence.matches(".*[a-zA-Z].*"));  // Check if the sequence contains at least one letter
        return sequence;
    }

    public static String generateRandomSequenceWithSpCharsForNumbers(int length) {
        Faker faker = new Faker();
        String sequence;
        do {
            sequence = faker.regexify("[0-9 !@#$%^&*()_+=<>?'\"-/|]{" + length + "}");
        } while (!sequence.matches(".*[a-zA-Z].*"));  // Check if the sequence contains at least one letter
        return sequence;
    }

    public static int generateMonthsToAdd(int min, int max) {
        Faker faker = new Faker();
        return faker.number().numberBetween(min, max);
    }

    public static String generateDate(int monthsToAdd) {
        return LocalDate.now().plusMonths(monthsToAdd).format(DateTimeFormatter.ofPattern("MM.yyyy"));
    }

    public static String getMonth(String date) {
        return date.substring(0, 2);
    }

    public static String getYear(String date) {
        return date.substring(5);
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generateRandomFirstName(String locale, int length) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName().substring(0,length);
    }

    public static String generateRandomSequenceWithDigitsForName(String locale, int length) {
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9]{" + length + "}");
    }

    public static String generateRandomSequenceWithSpCharsForName(String locale, int length) {
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9 !@#$%^&*()_+=|<>?\"/|]{" + length + "}");
    }

    public static String generateValidCVC() {
        Faker faker = new Faker();
        String pattern = "#".repeat(3);
        String cvc = faker.numerify(pattern);
        String[] arr = {"000", "111", "222", "333", "444", "555", "666", "777", "888", "999"};
        for (String item : arr) {
            if (cvc.equals(item)) {
                return generateValidCVC();
            }
        }
        return cvc;
    }

    public static String generateCvcOfThreeSameDigits() {
        String[] cvcs = new String[]{"000", "111", "222", "333", "444", "555", "666", "777", "888", "999"};
        return cvcs[new Random().nextInt(cvcs.length)];
    }

    public static CardInfo getApprovedCardInfo() {
        String date = generateDate(generateMonthsToAdd(0, 60));
        String month = getMonth(date);
        String year = getYear(date);
        String holder = generateName("en");
        String cvc = generateValidCVC();
        return new CardInfo("APPROVED", "1111222233334444", month, year, holder, cvc);
    }

    public static CardInfo getDeclinedCardInfo() {
        String date = generateDate(generateMonthsToAdd(0, 60));
        String month = getMonth(date);
        String year = getYear(date);
        String holder = generateName("en");
        String cvc = generateValidCVC();
        return new CardInfo("DECLINED", "5555666677778888", month, year, holder, cvc);
    }

    public static CardInfo getCardInfoInvalidCardNumber(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String number = generateRandomCardNumberMonthYearOrCvc(length);
        return new CardInfo(cardInfo.status, number, cardInfo.month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardNumberWithLetters(String locale, int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String number = generateRandomSequenceWithLettersForNumbers(locale, length);
        return new CardInfo(cardInfo.status, number, cardInfo.month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardNumberWithSpChars(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String number = generateRandomSequenceWithSpCharsForNumbers(length);
        return new CardInfo(cardInfo.status, number, cardInfo.month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoInvalidHardcodedMonth(String month) {
        CardInfo cardInfo = getApprovedCardInfo();
        return new CardInfo(cardInfo.status, cardInfo.number, month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoInvalidMonth(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String month = generateRandomCardNumberMonthYearOrCvc(length);
        return new CardInfo(cardInfo.status, cardInfo.number, month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardMonthWithLetters(String locale, int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String month = generateRandomSequenceWithLettersForNumbers(locale, length);
        return new CardInfo(cardInfo.status, cardInfo.number, month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardMonthWithSpChars(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String month = generateRandomSequenceWithSpCharsForNumbers(length);
        return new CardInfo(cardInfo.status, cardInfo.number, month, cardInfo.year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoInvalidYear(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String year = generateRandomCardNumberMonthYearOrCvc(length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardYearWithLetters(String locale, int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String year = generateRandomSequenceWithLettersForNumbers(locale, length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCardYearWithSpChars(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String year = generateRandomSequenceWithSpCharsForNumbers(length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoSpecifiedDate(int monthToAddMin, int monthToAddMax) {
        CardInfo cardInfo = getApprovedCardInfo();
        String date = generateDate(generateMonthsToAdd(monthToAddMin, monthToAddMax));
        String month = getMonth(date);
        String year = getYear(date);
        return new CardInfo(cardInfo.status, cardInfo.number, month, year, cardInfo.holder, cardInfo.cvc);
    }

    public static CardInfo getCardInfoInvalidName(String locale, int lengthFirstName, int lengthSecondName) {
        CardInfo cardInfo = getApprovedCardInfo();
        String firstName = generateRandomFirstName(locale, lengthFirstName);
        String secondName = generateRandomSequenceWithDigitsForName(locale, lengthSecondName);
        String name = firstName + " " + secondName;
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, name, cardInfo.cvc);
    }

    public static CardInfo getCardInfoInvalidNameWithSpChars(String locale, int lengthFirstName, int lengthSecondName) {
        CardInfo cardInfo = getApprovedCardInfo();
        String firstName = generateRandomFirstName(locale, lengthFirstName);
        String secondName = generateRandomSequenceWithSpCharsForName(locale, lengthSecondName);
        String name = firstName + " " + secondName;
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, name, cardInfo.cvc);
    }

    public static CardInfo getCardInfoCvcOfThreeSameDigits() {
        CardInfo cardInfo = getApprovedCardInfo();
        String cvc = generateCvcOfThreeSameDigits();
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, cardInfo.holder, cvc);
    }

    public static CardInfo getCardInfoInvalidCVC(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String cvc = generateRandomCardNumberMonthYearOrCvc(length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, cardInfo.holder, cvc);
    }

    public static CardInfo getCardInfoCardCvcWithLetters(String locale, int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String cvc = generateRandomSequenceWithLettersForNumbers(locale, length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, cardInfo.holder, cvc);
    }

    public static CardInfo getCardInfoCardCvcWithSpChars(int length) {
        CardInfo cardInfo = getApprovedCardInfo();
        String cvc = generateRandomSequenceWithSpCharsForNumbers(length);
        return new CardInfo(cardInfo.status, cardInfo.number, cardInfo.month, cardInfo.year, cardInfo.holder, cvc);
    }
}
