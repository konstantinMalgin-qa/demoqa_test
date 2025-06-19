package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080"; //открывает браузер в высоком разрешении
        Configuration.pageLoadStrategy = "eager"; // не дожидаемся лоудера страницы
    }

    @Test
    void findSelenideJunit() {

        //открыть главную страницу

        open("https://github.com/"); //открывает страницу

        //ввести в поле поиска selenide и нажать enter

        //$(".practice-form-wrapper").shouldHave(text("Student Registration Form")); // проверка зотображения текста

        $("[placeholder='Search or jump to...']").click(); // клик на поиск

        $("#query-builder-test").setValue("Selenide").pressEnter(); // ввод "Selenide" и enter

        //sleep(5000); // временно для отображения страницы


        // кликнуть на репозиторий Selenide

        $("a[href='/selenide/selenide']").click();

        // проверка: заголовок selenide/selenide

        $("#repository-container-header").shouldHave(text("selenide / selenide"));

        // переход в раздел Wiki

        $("#wiki-tab").click();

        // проверка что в списке страниц есть страница SoftAssertions

        $("#wiki-body").shouldHave(text("Soft assertions"));

        // переход на страницу Soft assertions

        $("a[href='/selenide/selenide/wiki/SoftAssertions']").click();

        // проверка что внутри есть заголовок JUnit5 и пример кода

       // $(".markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));

        //$(withText("Using JUnit5")).scrollTo();

        $("#wiki-wrapper").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})" +
                "\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }
}
