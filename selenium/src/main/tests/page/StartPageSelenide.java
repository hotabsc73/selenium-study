package main.tests.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

public class StartPageSelenide {

  private static final String XPATH_SUBMIT_FEELING_LUCKY = "//input[@name='btnI']";
  private static final String XPATH_TEXT_SUBMIT_FEELING_LUCKY = "//span[contains(text(),'Мне повезёт!')]";

  private final SelenideElement buttonRejectAll = $("#W0wltc");
  private final SelenideElement inputSearch = $("[name='q']");
  private final SelenideElement buttonSearch = $("input[name='btnK']");
  private final SelenideElement logoGoogle = $("[aria-label='Google']");
  private final SelenideElement urlAboutGoogle = $x("//*[text()='Всё о Google' or text()='All About Google']");
  private final SelenideElement urlAdvertising = $x("//*[text()='Реклама' or text()='Advertising']");
  private final SelenideElement resultSearch = $("//*[@id=\"rcnt\"]/div[1]/div[1]");
  private final SelenideElement urlSettings = $x("//*[text()='Настройки' or text()='Settings']");
  private final SelenideElement urlLangue = $(Selectors.byText("Россия"));

  // Нажать на кнопку "Отклонить все куки"
  public void clickButtonRejectAll() {
    this.buttonRejectAll.pressEnter();
  }

  public SelenideElement getInputSearch() {
    return this.inputSearch;
  }

  public void inputSearch(String value) {
    this.inputSearch.setValue(value);
  }

  // Нажать на кнопку "Поиск в Google"
  public void clickButtonSearch() {
    buttonSearch.pressEnter();
  }

  public SelenideElement getResultSearch() {
    return this.resultSearch;
  }

  // Найти кнопку "Мне повезет!", когда он input
  public SelenideElement getButtonFeelingLuckyAsInput() {
    return $x(XPATH_SUBMIT_FEELING_LUCKY);
  }

  public void clickButton(SelenideElement input) {
    input.click();
  }

  // Найти кнопку "Мне повезет!", когда он button
  public SelenideElement getButtonFeelingLuckyAsButton() {
    return $x(XPATH_TEXT_SUBMIT_FEELING_LUCKY);
  }


  // Получить элемент ссылки "Настройки"
  public SelenideElement getUrlSettings() {
    return this.urlSettings;
  }

  // Получить элемент label "Россия"
  public SelenideElement getLabelLangue() {
    return this.urlLangue;
  }

  // Получить логотип Google
  public SelenideElement getLogoGoogle() {
    return this.logoGoogle;
  }

  // Получить элемент ссылки "Всё о Google"
  public SelenideElement getUrlAboutGoogle() {
    return this.urlAboutGoogle;
  }

  // Получить элемент ссылки "Реклама"
  public SelenideElement getUrlAdvertising() {
    return this.urlAdvertising;
  }

}
