package allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;



public class AllureHomework {

    private static final String REPO = "eroshenkoam/allure-example";
    private static final String ISSUENAME = "issue_to_test_allure_report";

    @Test
    public void checkIssueWithClearSelenideAndListener() {
        addListener("allure", new AllureSelenide());
        open("https://github.com/");
        $(".header-search-input").setValue(REPO).submit();
        $(By.linkText(REPO)).click();
        $("#issues-tab").click();
        $(withText(ISSUENAME)).should(Condition.exist);
    }
    @Test
    public void checkIssueWithLambda() {
        step("Открытие главной", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPO, () -> {
            $(".header-search-input").setValue(REPO).submit();
        });
        step("Ищем " + REPO + " на странице поисковой выдачи", () -> {
            $(By.linkText(REPO)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие issue " +ISSUENAME, () -> {
            $(withText(ISSUENAME)).should(Condition.exist);
        });
    }
    @Test
    @DisplayName("Проверка с аннотациями")
    public void checkIssueWithAnnotation() {
        Steps step = new Steps();
        step.openMainPage();
        step.searchRepo(REPO);
        step.searchResults(REPO);
        step.clickIssue();
        step.checkIssueName(ISSUENAME);
    }
}
