package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

    }


    @Test
    void fullFormTest() {
        open("/text-box");


        $("#userName").setValue("Kostya");
        $("#userEmail").setValue("kostya@ivanov.com");
        $("#currentAddress").setValue("Some street 1");
        $("#permanentAddress").setValue("Another street 2");
        $("#submit").click();


        $("#output").shouldHave(text("Kostya"));
        $("#output").shouldHave(text("kostya@ivanov.com"));
        $("#output").shouldHave(text("Some street 1"));
        $("#output").shouldHave(text("Another street 2"));


    }
}
