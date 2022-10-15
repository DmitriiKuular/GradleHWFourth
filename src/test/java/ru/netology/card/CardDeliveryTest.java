package ru.netology.card;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldFillForm() {
        String date = generateDate(4);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Петр Василий");
        $("[data-test-id=phone] input").setValue("+78883331313");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! " + "Встреча успешно забронирована на "
        + date), Duration.ofSeconds(15)).shouldBe(visible);
    }

    @Test
    void shouldFillFormUsingHelpButtons() throws InterruptedException {
        String date = generateDate(3);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Са");
        $$(".menu-item__control").find(exactText("Санкт-Петербург")).click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Петр Василий");
        $("[data-test-id=phone] input").setValue("+78883331313");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $("[data-test-id=notification]").shouldHave(exactText("Успешно! " + "Встреча успешно забронирована на "
                + date), Duration.ofSeconds(15)).shouldBe(visible);
        Thread.sleep(8000);
    }
}
