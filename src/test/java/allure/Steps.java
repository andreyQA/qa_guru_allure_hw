package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Steps {
    @Step("Открытие главной")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchRepo(String repo){
        $(".header-search-input").setValue(repo).submit();
    }

    @Step("Ищем {repo} на странице поисковой выдачи")
    public void searchResults(String repo) {
        $(By.linkText(repo)).click();
        takeScreenShot();
    }

    @Step("Открываем вкладку Issues")
    public void clickIssue() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие issue {issue_name}")
    public void checkIssueName(String issue_name) {
        $(withText(issue_name)).should(Condition.exist);
        takeScreenShot();
    }
    @Attachment(value = "Видео", type = "image/png")
    public byte[] takeScreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
