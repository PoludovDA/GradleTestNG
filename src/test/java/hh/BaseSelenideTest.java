package hh;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.io.IOException;

abstract public class BaseSelenideTest {

    public void setUp() {
        //Установка драйвера + путь к нему + запуск
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        //Верить вебдрайверменеджер
        Configuration.driverManagerEnabled = true;
        //Размер окна браузера
        Configuration.browserSize = "2200x1180";
        //Показывать ли вирт браузер
        Configuration.headless = false;
    }

    @BeforeSuite
    public void init() {
        setUp();
    }

    @AfterSuite
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}