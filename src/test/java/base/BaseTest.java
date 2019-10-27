package base;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;


public class BaseTest {
        protected WebDriver driver;
        protected HomePage homePage;

        @BeforeClass
        public void setUp(){
            System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.propertypal.com/");
            homePage = new HomePage(driver);
            homePage.acceptCookies();

        }

        @BeforeMethod
        public void goHome(){
            driver.get("https://www.propertypal.com/");
            homePage = new HomePage(driver);

        }

       @AfterClass
       public void tearDown(){
           driver.close();
      }



}


