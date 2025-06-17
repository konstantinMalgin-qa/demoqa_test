package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080"; //открывает браузер в высоком разрешении
        Configuration.baseUrl = "https://demoqa.com"; // хост выведен в конфиг
        Configuration.pageLoadStrategy = "eager"; // не дожидаемся лоудера страницы

    }


    @Test
    void practiceForm() {
        open("/automation-practice-form"); //открывает страницу
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form")); // проверка зотображения текста
        executeJavaScript("$('#fixedban').remove()"); // удаляем баннеры
        executeJavaScript("$('footer').remove()");// удаляем футер

        //Блок Name

        $("#firstName").setValue("Kostya");
        $("#lastName").setValue("Ivanov");

        //Блок e-mail

        $("#userEmail").setValue("kostya@ivanov.com");

        //Блок Gender

        $(byText("Male")).click(); // not good слов может быть больше , чем одно

//$("#genterWrapper").$(byText("Male")).click(); //best указываем поля и в нем ищем по тексту
//$("#gender-radio-1").parent().click(); // good добавляем родительский атрибут
// $("[for=gender-radio-1]").click(); //wrong

        //Блок Mobile

        $("#userNumber").setValue("1234567893");

        //Блок Date of Birth

        $("#dateOfBirthInput").click();

        $(".react-datepicker__month-select").selectOption("March");

        $(".react-datepicker__year-select").selectOption("2005");

        $(".react-datepicker__day--025").click();

// $(".react-datepicker__day--025:not(.react-datepicker__day--outside-month").click();
                                                            // если в календаре 2 числа,
                                                            // от предыдущего и текущего месяца

        //Блок Subjects

        $("#subjectsInput").setValue("Eng");

        $$(".subjects-auto-complete__option").findBy(text("English")).click();

// $("#subjectsInput").setValue("English").pressEnter();

        //Блок Hobbies

        $("#hobbiesWrapper").$(byText("Music")).click();


        // Блок загрузки фото
        $("#uploadPicture").uploadFromClasspath("Photo.jpeg");
                                    // метод работает только если у элемента есть type="file"

//$("#uploadPicture").uploadFile(new File("src/test/resources/Photo.jpeg"));

        // Блок Address
        $("#currentAddress").setValue("123 Main St.");

        // Блок State and City

        // Штат и Город
        $("#state").click();

        $("#stateCity-wrapper").$(byText("Haryana")).click();

        $("#city").click();

        $("#stateCity-wrapper").$(byText("Karnal")).click();




        $("#submit").click();


        // Проверка текста в форме после заполнения


        $(".modal-dialog").should(appear);

        $(".table-responsive").shouldHave(text("Kostya Ivanov"));
        $(".table-responsive").shouldHave(text("kostya@ivanov.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567893"));
        $(".table-responsive").shouldHave(text("25 March,2005"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("Photo.jpeg"));
        $(".table-responsive").shouldHave(text("123 Main St."));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

        // Дополнительно проверяем общий успех отправки формы
        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
    }
}
