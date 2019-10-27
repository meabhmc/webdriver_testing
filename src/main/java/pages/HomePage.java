package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    private By searchInput = By.xpath("//article//form//section/input");
    private By saleButton = By.xpath("//button[text()='For Sale']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Finds the search bar, inputs search term, and clicks "For Sale" to open Results Page
     * @param searchTerm string to be entered into search bar
     * @return ResultsPage object
     */
    public ResultsPage inputSearchTerm(String searchTerm){
        waitForElementToLoad(searchInput);
        driver.findElement(searchInput).sendKeys(searchTerm);
        driver.findElement(saleButton).click();
        waitForPageToLoad(searchTerm);
        return new ResultsPage(driver);
    }

    public void acceptCookies(){
        By cookieConsentButton = By.xpath("//button[text()=' I Accept ']");
        waitForElementToLoad(cookieConsentButton);
        driver.findElement(cookieConsentButton).click();
    }

    private void waitForElementToLoad (By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void waitForPageToLoad(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains(searchTerm));
    }

}
