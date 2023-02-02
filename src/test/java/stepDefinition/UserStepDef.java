package stepDefinition;

import endpoints.UserEndpoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import model.User;
import org.junit.Assert;
import utils.ValidatorOperation;

import java.util.List;
import java.util.Map;

public class UserStepDef extends UserEndpoint {

    @When("I created the user in pet store")
    public void iCreatedTheUser() {
        createUser(new User());
    }

    @When("I read the user in pet store")
    public void iReadTheUserByUsername() {
        readUser(new User());
    }

    @When("I delete the user in pet store")
    public void iDeleteTheUserByUsername() {
        deleteUser(new User());
    }
    @When("I updated the user in pet store")
    public void iUpdatedTheUserByUsername(DataTable dt) {
        List<Map<String, String>> data =  dt.asMaps(String.class, String.class);
        updateUser(new User(),
                data.get(0).get("username"),
                data.get(0).get("first name"),
                data.get(0).get("last name"),
                data.get(0).get("email"),
                data.get(0).get("password"),
                data.get(0).get("phone"));
    }
    @When("I validated the status code should be {int}")
    public void iCreatedTheUser(int statusCode) {
        validateStatusCode(statusCode);
    }

    @And("I validated the values in response body")
    public void iValidateTheValuesInResponse(DataTable dt) {

        List<Map<String, String>> data =  dt.asMaps(String.class, String.class);
        Assert.assertEquals(data.get(0).get("username"),extractString("username"));
        if(extractString("firstName")!= null) {
            Assert.assertEquals(data.get(0).get("first name"),extractString("firstName"));
        }
        if(extractString("lastName") != null) {
            Assert.assertEquals(data.get(0).get("last name"),extractString("lastName"));
        }
        Assert.assertEquals(data.get(0).get("email"),extractString("email"));
        Assert.assertEquals(data.get(0).get("password"),extractString("password"));
        Assert.assertEquals(data.get(0).get("phone"),extractString("phone"));
    }
}
