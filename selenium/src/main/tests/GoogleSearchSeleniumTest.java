package main.tests;

import java.time.Duration;
import main.tests.page.StartPage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchSeleniumTest {

  private static final String START_PAGE_URL = "https://www.google.com"; // todo брать из properties
  private static final long IMPLICITLY_WAIT_TIMEOUT_SECONDS = 5; // todo брать из properties

  private static WebDriver webDriver;
  private static StartPage startPage;

  @BeforeAll
  public static void setup() {
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_TIMEOUT_SECONDS));
    webDriver.get(START_PAGE_URL);

    startPage = new StartPage(webDriver);
  }

  @AfterAll
  public static void exit() {
    webDriver.quit();
  }

  @DisplayName("Проверка функциональности поиска")
  @Test
  public void checkFunctionSearch() {
    String text = "Selenide vs Selenium";
    String expected = "Selenium — это ";

    startPage.clickButtonRejectAll();
    startPage.inputSearch(text);
    startPage.clickButtonSearch();
    // todo здесь может появится "Подтвердить, что я не робот"
    WebElement resultSearch = startPage.getResultSearch();

    Assertions.assertThat(resultSearch.getText()).contains(expected);
  }

  @DisplayName("Проверка кнопки «Мне повезёт»")
  @Test
  public void checkButtonFeelingLucky() {
    startPage.clickButtonRejectAll();
    try {
      WebElement submitFeelingLucky = startPage.getButtonFeelingLuckyAsInput();

      Assertions.assertThat(submitFeelingLucky.isDisplayed())
          .as("button feeling lucky is not displayed")
          .isFalse();
      Assertions.assertThat(submitFeelingLucky.isEnabled())
          .as("button feeling lucky is enabled")
          .isTrue();

      startPage.clickButton(submitFeelingLucky);

      WebElement submitFeelingLuckyAfter = startPage.getButtonFeelingLuckyAsInput();

      Assertions.assertThat(submitFeelingLuckyAfter).isNull();
    } catch (NoSuchElementException exc) {
      WebElement buttonFeelingLucky = startPage.getButtonFeelingLuckyAsButton();

      Assertions.assertThat(buttonFeelingLucky.isDisplayed())
          .as("button feeling lucky is displayed")
          .isTrue();
      Assertions.assertThat(buttonFeelingLucky.isEnabled())
          .as("button feeling lucky is enabled")
          .isTrue();

      startPage.clickButton(buttonFeelingLucky);

      WebElement inputSearch = startPage.getInputSearchUntilTextIsNotEmpty();
      // todo здесь может появится "Подтвердить, что я не робот"
      Assertions.assertThat(inputSearch.getText()).isNotBlank();
    }
  }

  @DisplayName("Проверка ссылок в футере (Настройки и язык)")
  @Test
  public void checkUrlsInSettings() {
    WebElement urlSettingsElement = startPage.getUrlSettings();

    Assertions.assertThat(urlSettingsElement.isDisplayed())
        .as("url settings is displayed")
        .isTrue();
    Assertions.assertThat(urlSettingsElement.getText())
        .as("url settings has 'href' attribute")
        .containsAnyOf("Настройки", "Settings");

    WebElement labelLangueElement = startPage.getLabelLangue();

    Assertions.assertThat(labelLangueElement.isDisplayed())
        .as("label langue is displayed")
        .isTrue();
  }

  @DisplayName("Проверка логотипа и ссылок «О нас» / «Реклама»")
  @Test
  public void checkLogoAndUrlsAboutGoogleAndAdvertising() {
    WebElement logoGoogleElement = startPage.getLogoGoogle();

    Assertions.assertThat(logoGoogleElement.isDisplayed())
        .as("logo google is displayed")
        .isTrue();

    WebElement urlAboutGoogleElement = startPage.getUrlAboutGoogle();

    Assertions.assertThat(urlAboutGoogleElement.isDisplayed())
        .as("url about google is displayed")
        .isTrue();
    Assertions.assertThat(urlAboutGoogleElement.getText())
        .as("url about google has text")
        .isNotBlank();

    WebElement urlAdvertisingElement = startPage.getUrlAdvertising();

    Assertions.assertThat(urlAdvertisingElement.isDisplayed())
        .as("url advertising is displayed")
        .isTrue();
    Assertions.assertThat(urlAdvertisingElement.getAttribute("href"))
        .as("url about google has text")
        .startsWith("https://www.google.com");
  }
}
