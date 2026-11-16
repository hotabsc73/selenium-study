package main.tests.page;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {

  private static final long WAIT_TIMEOUT_SECONDS = 15; // todo брать из properties

  private static final String XPATH_SUBMIT_FEELING_LUCKY = "//*[@name=\"btnI\"]";
  private static final String XPATH_TEXT_SUBMIT_FEELING_LUCKY = "//span[contains(text(),'Мне повезёт!')]";
  private static final String XPATH_URL_SETTINGS = "//g-popup/div[1]";
  private static final String CSS_SELECTOR_LABEL_LANGUE = "body > div.plsC5e.RqyYHe > div:nth-child(9) > div > div.O3yKUb";

  private final WebDriver webDriver;
  private final WebDriverWait webDriverWait;

  @FindBy(id = "W0wltc")
  private WebElement buttonRejectAll;
  @FindBy(id = "ti6dpd")
  private WebElement inputSearch;
  @FindBy(css = "input[name='btnK']")
  private WebElement buttonSearch;
  @FindBy(id = "sI1XGe")
  private WebElement logoGoogle;
  @FindBy(css = "a:nth-child(1)")
  private WebElement urlAboutGoogle;
  @FindBy(css = "a:nth-child(2)")
  private WebElement urlAdvertising;
  @FindBy(xpath = "//*[@id=\"rcnt\"]/div[1]/div[1]")
  private WebElement resultSearch;

  public StartPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    PageFactory.initElements(webDriver, this);
  }

  public WebElement getInputSearch() {
    return this.inputSearch;
  }

  // Получить строку поиска когда текст не будет пустым
  public WebElement getInputSearchUntilTextIsNotEmpty() {
    webDriverWait.until(driver ->
        !inputSearch.getAttribute("value").isBlank()
    );
    return inputSearch;
  }

  // Ввести данные в поисковую строку Google
  public void inputSearch(String value) {
    inputSearch.sendKeys(value);
  }

  // Нажать на кнопку "Поиск в Google"
  public void clickButtonSearch() {
    buttonSearch.click();
  }

  // Нажать на кнопку "Отклонить все куки"
  public void clickButtonRejectAll() {
    buttonRejectAll.click();
  }

  // Найти кнопку "Мне повезет!", когда он input
  public WebElement getButtonFeelingLuckyAsInput() {
    return webDriver.findElement(By.xpath(XPATH_SUBMIT_FEELING_LUCKY));
  }

  public void clickButton(WebElement input) {
    input.click();
  }

  // Найти кнопку "Мне повезет!", когда он button
  public WebElement getButtonFeelingLuckyAsButton() {
    return webDriver.findElement(By.xpath(XPATH_TEXT_SUBMIT_FEELING_LUCKY));
  }

  // Получить элемент результата поиска
  public WebElement getResultSearch() {
    return this.resultSearch;
  }

  // Получить элемент ссылки "Настройки"
  public WebElement getUrlSettings() {
    return webDriverWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath(XPATH_URL_SETTINGS)
        )
    );
  }

  // Получить элемент label "Россия"
  public WebElement getLabelLangue() {
    return webDriverWait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(CSS_SELECTOR_LABEL_LANGUE)
        )
    );
  }

  // Получить логотип Google
  public WebElement getLogoGoogle() {
    return this.logoGoogle;
  }

  // Получить элемент ссылки "Всё о Google"
  public WebElement getUrlAboutGoogle() {
    return this.urlAboutGoogle;
  }

  // Получить элемент ссылки "Реклама"
  public WebElement getUrlAdvertising() {
    return this.urlAdvertising;
  }

}
