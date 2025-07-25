package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideRepositorySearchTest {
    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void findSelenideJunitTest() {

        //открыть главную страницу

        open("https://github.com/"); //открывает страницу

        //ввести в поле поиска selenide и нажать enter


        $("[placeholder='Search or jump to...']").click(); // клик на поиск

        $("#query-builder-test").setValue("Selenide").pressEnter(); // ввод "Selenide" и enter

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
