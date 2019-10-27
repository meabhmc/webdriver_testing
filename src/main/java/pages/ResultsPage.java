package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {
    private WebDriver driver;
    private By noResultsHeader = By.className("noresults-heading");
    private By postcodeDisplay = By.xpath("//a//h2//span[@class='propbox-town']/span");
    private By distanceAwayDisplay = By.className("propbox-distance");

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public List<String> getMyLocationResults(){
        List<String> listOfMyLocationResults = new ArrayList<>();
        List<WebElement> myLocationResults = driver.findElements(distanceAwayDisplay);
        for(WebElement result: myLocationResults){
            System.out.println(result.getText());
            listOfMyLocationResults.add(result.getText());
        }
        return listOfMyLocationResults;
    }

    /**
     * Get list of strings indicating the postcode for each returned result on first page of results
     * @return
     */
    public List<String> getPostcodeResults(){
        List<WebElement> postCodeResults = driver.findElements(postcodeDisplay);
        return removeFeaturedDevelopmentsFromResults(postCodeResults);
    }

    /**
     * Clean up list of web elements to only include those that have text result.
     * Handles cases where business knowingly returns a sponsored Featured Development with no postcode
     * @param elements
     * @return
     */
    private List<String> removeFeaturedDevelopmentsFromResults(List<WebElement> elements){
        List<String> listOfPostCodeResults = new ArrayList<>();
        for(WebElement element: elements){
            var text = element.getText();
            System.out.println("postcode: " + text);
            if(text !=null && !text.isEmpty()){
                listOfPostCodeResults.add(text);
            }
        }
        return listOfPostCodeResults;
    }

    /**
     * When no search results are present, the noResultsHeader WebElement should be visible
     * If that WebElement is not visible, indicating search results are present, the noResultsHeader Webelement will
     * not be visible (size of WebElement list = 0).
     * @return
     */
    public Boolean checkIfNoResults(){
        return driver.findElements(noResultsHeader).size()>0;
    }

}
