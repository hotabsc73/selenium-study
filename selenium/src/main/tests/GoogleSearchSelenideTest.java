package main.tests;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import main.tests.page.StartPageSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;

public class GoogleSearchSelenideTest {

  private static final String START_PAGE_URL = "https://www.google.com"; // todo брать из properties

  private final StartPageSelenide startPage = new StartPageSelenide();

  @DisplayName("Проверка функциональности поиска")
  @Test
  public void checkFunctionSearch() {
    open(START_PAGE_URL);
    String text = "Selenide vs Selenium";
    String expected = "Selenium — это ";

    startPage.inputSearch(text);
    startPage.clickButtonSearch();
    // todo здесь может появится "Подтвердить, что я не робот"
    SelenideElement resultSearch = startPage.getResultSearch();

    resultSearch.shouldHave(value(expected));
  }

  @DisplayName("Проверка кнопки «Мне повезёт»")
  @Test
  public void checkButtonFeelingLucky() {
    open(START_PAGE_URL);
    try {
      startPage.getButtonFeelingLuckyAsInput().shouldBe(hidden, enabled);
    } catch (NoSuchElementException exc) {
      SelenideElement buttonFeelingLucky = startPage.getButtonFeelingLuckyAsButton().shouldBe(visible, enabled);

      startPage.clickButton(buttonFeelingLucky);

      // todo здесь может появится "Подтвердить, что я не робот"
      startPage.getInputSearch().shouldNotHave(value(""));
    }
  }

  @DisplayName("Проверка ссылок в футере (Настройки и язык)")
  @Test
  public void checkUrlsInSettings() {
    open(START_PAGE_URL);
    startPage.getUrlSettings().shouldBe(visible);
    startPage.getLabelLangue().shouldBe(visible);
  }

  @DisplayName("Проверка логотипа и ссылок «О нас» / «Реклама»")
  @Test
  public void checkLogoAndUrlsAboutGoogleAndAdvertising() {
    open(START_PAGE_URL);
    startPage.getLogoGoogle().shouldBe(visible);
    startPage.getUrlAboutGoogle().shouldBe(visible);
    startPage.getUrlAdvertising().shouldBe(visible)
        .shouldHave(attributeMatching("href", "^https://www.google.com.*"));
  }

}
