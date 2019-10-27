package search;

import base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import java.util.List;

public class ResultsTests extends BaseTest {

    @Test
    public void testPostcodeResults(){
        String searchTerm = "BT6";
        var resultsPage = homePage.inputSearchTerm(searchTerm);
        if(!resultsPage.checkIfNoResults()){
            List<String> results = resultsPage.getPostcodeResults();
            for (String result: results){
                assertEquals(result,searchTerm, "Unexpected Postcodes Found");
            }
        } else{
            System.out.println("No results returned currently for valid search");
        }

    }

    @Test
    public void testMyLocationResults(){
        String searchTerm = "My Location";
        String regex = "\\b(Approx.*miles away)\\b";
        var resultsPage = homePage.inputSearchTerm(searchTerm);
        if(!resultsPage.checkIfNoResults()){
            List<String> results = resultsPage.getMyLocationResults();
            for (String result: results){
                assertTrue(result.matches(regex));
            }
        } else{
            System.out.println("No results returned currently for My Location");
        }
    }

    @Test
    public void testZeroResultsReturned(){
        String searchTerm = "SW19"; // search term not valid for island of Ireland
        var resultsPage = homePage.inputSearchTerm(searchTerm);
        assertTrue(resultsPage.checkIfNoResults(),"Results were returned");
    }
}
