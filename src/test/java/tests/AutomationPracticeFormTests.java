package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }


    @Test
    void practiceForm() {
        open("/automation-practice-form");

        //Блок Name

        $("#firstName").setValue("Kostya");
        $("#lastName").setValue("Ivanov");

        //Блок e-mail

        $("#userEmail").setValue("kostya@ivanov.com");

        //Блок Gender

        $(byText("Male")).click();

        //Блок Mobile

        $("#userNumber").setValue("1234567893");

        //Блок Date of Birth

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--025").click();

        //Блок Subjects

        $("#subjectsInput").setValue("Eng");
        $$(".subjects-auto-complete__option").findBy(text("English")).click();

        //Блок Hobbies

        $("#hobbiesWrapper").$(byText("Music")).click();


        // Блок загрузки фото
        $("#uploadPicture").uploadFromClasspath("Photo.jpeg");

        // Блок Address
        $("#currentAddress").setValue("123 Main St.");

        // Блок State and City

        // Штат и Город
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();




        $("#submit").click();


        // Проверка текста в форме после заполнения


        $(".table-responsive").shouldHave(text("Kostya Ivanov"));
        $(".table-responsive").shouldHave(text("kostya@ivanov.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567893"));
        $(".table-responsive").shouldHave(text("25 March,2005"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Music"));
        //$(".table-responsive").shouldHave(text("Photo.jpeg"));
        $(".table-responsive").shouldHave(text("123 Main St."));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

        // Дополнительно проверяем общий успех отправки формы
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
    }
}
