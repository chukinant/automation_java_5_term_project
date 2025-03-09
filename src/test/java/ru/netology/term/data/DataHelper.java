package ru.netology.term.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    public static String getApprovedCardNumber() {
        return "1111222233334444";
    }

    public static String getDeclinedCardNumber() {
        return "5555666677778888";
    }

    public static String generateRandomCardNumber(String locale, int length) {
        Faker faker = new Faker(new Locale(locale));
        String pattern = "#".repeat(length);
        return faker.numerify(pattern);
    }

    public static String generateRandomSequenceWithLetters(String locale, int length) {
        Faker faker = new Faker(new Locale(locale));
        return faker.regexify("[A-Za-z0-9]{" + length + "}");
    }

    public static String generateRandomSequenceWithSpecialChars(String locale, int length) {
        Faker faker = new Faker(new Locale(locale));
        return faker.regexify("[0-9!@#$%^&*()_+=<>?]{" + length + "}");
    }

    public static int generateMonthsToAdd(int min, int max) {
        Faker faker = new Faker();
        return faker.number().numberBetween(min,max);
    }

    public static String generateDate(int monthsToAdd) {
        return LocalDate.now().plusMonths(monthsToAdd).format(DateTimeFormatter.ofPattern("MM.yyyy"));
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
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

    public static String generateInvalidCVC() {
        String[] cvcs = new String[] {"000", "111", "222", "333", "444", "555", "666", "777", "888", "999"};
        return cvcs[new Random().nextInt(cvcs.length)];
    }
}
