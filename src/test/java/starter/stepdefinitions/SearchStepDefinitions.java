package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.MatcherAssert;
import java.util.List;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;

public class SearchStepDefinitions {

    @When("user with method {string} calls product {string}")
    public void sendRequest(String method, String product) {
        String reqURL = "https://waarkoop-server.herokuapp.com/api/v1/search/demo/"+product;
        if(method.equals("Get")) {
            SerenityRest.get(reqURL);
        }
        else if(method.equals("Post")){
            SerenityRest.post(reqURL);
        }
        else{
            System.out.println("Unsupported request type");
        }
    }

    @Then("response status code should be {int}")
    public void validateStatusCode(Integer responseCode) {
       restAssuredThat(response -> response.statusCode(responseCode));
    }

    @Then("response status line should be {string}")
    public void validateStatusLine(String statusLineValue) {
        restAssuredThat(response -> response.statusLine(statusLineValue));
    }

    @Then("user verifies the results displayed for {string}")
    public void validateResponseContainsProduct(String product) {
        restAssuredThat(response -> response.body("title[1]", containsStringIgnoringCase(product)));
    }

    @Then("user verifies requested item")
    public void verifyRequestedItem(DataTable dataTable) {
        List<String> boxList = dataTable.asList();
        String[] boxData = boxList.stream().toArray(String[]::new);
        for(int i=3; i<boxData.length; i=i+3){
            if (boxData[i].equals("true")) {
                String keyName = boxData[i+1];
                Boolean booleanItemValue = Boolean.parseBoolean(boxData[i]);
                restAssuredThat(response -> response.body("detail.'" + keyName + "'", equalTo(booleanItemValue) ));
            }
            else{
                String keyName = boxData[i+1];
                String keyValue = boxData[i+2];
                restAssuredThat(response -> response.body("detail.'" + keyName + "'", containsString(keyValue)));
            }
        }
    }
    @Then("user with method {string} verifies {string} products all keys")
    public void heVerifiesAllProductsAllKeys( String method, String products, DataTable dataTable) {
        String[] productArray = products.split(", ");
        List<String> keyList = dataTable.asList();
        String[] keyName = keyList.stream().toArray(String[]::new);
        for(int k=0; k<productArray.length; k++){
            sendRequest(method, productArray[k]);

            for(int i=0; i<keyName.length; i++){
                String key = keyName[i];
                String[] a= lastResponse().body().asString().split("}'");
                for(int j=0; j<a.length; j++) {
                    MatcherAssert.assertThat("In '"+j+"' Company credentials Not containing'" + key + "'", a[j], containsString(key)
                    );
                }
            }
        }
    }

    @Then("providers count is {int}")
    public void userVerifiesProviderCount(Integer providerCount){
        int actualProviderCount = lastResponse().body().path("provider.size()");
        MatcherAssert.assertThat("Provider count does not match", actualProviderCount, equalTo(providerCount));
    }

}
