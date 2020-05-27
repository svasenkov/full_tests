package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Epic("QA.GURU automation course")
@Story("Google tests")
@Tag("google")
class GoogleTests {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
        Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub/";
    }

    @Test
    @Description("Google test, we look for lepra")
    @DisplayName("Google test")
    void successfulSearch() {
        open("http://google.com");

        $(byName("q")).val("lepra").pressEnter();

        $("html").shouldHave(text("Lepra - Wikipedia"));
    }

    @Test
    @Description("Google test, we look for 2ch, but cant find")
    @DisplayName("Google test")
    void unSuccessfulSearch() {
        open("http://google.com");

        $(byName("q")).val("2ch").pressEnter();

        $("html").shouldHave(text("Лепрозорий: вход"));
    }

}