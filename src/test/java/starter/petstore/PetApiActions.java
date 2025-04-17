package starter.petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.core.steps.UIInteractions;
import org.hamcrest.Matchers;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;

public class PetApiActions extends UIInteractions {

    private static final String BASE_URI = "https://petstore.swagger.io";
    private static final String BASE_PATH = "/v2/pet";

    /**
     * Adds a new pet named "Puff" to the pet store and returns its ID.
     */
    @Given("Puff is available in the pet store")
    public Long givenKittyIsAvailableInPetStore() {
        Pet pet = new Pet("Puff", "available");

        return given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet, ObjectMapperType.GSON)
                .post()
                .then()
                .statusCode(200) // Ensure the request was successful
                .extract()
                .body()
                .as(Pet.class, ObjectMapperType.GSON)
                .getId();
    }

    /**
     * Fetches a pet by its ID.
     */
    @When("I ask for a pet using Kitty's ID: {0}")
    public void whenIAskForAPetWithId(Long id) {
        given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(200); // Ensure the request was successful
    }

    /**
     * Verifies that the fetched pet has the name "Puff".
     */
    @Then("I get Puff as result")
    public void thenISeeKittyAsResult() {
        then().body("name", Matchers.equalTo("Puff"));
    }
}