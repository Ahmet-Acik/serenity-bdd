package starter.acceptancetests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import starter.petstore.PetApiActions;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenFetchingAlreadyAvailablePet {

    Long newPetId = null;
    PetApiActions petApi;

    @Test
    public void fetchAlreadyAvailablePet() {
        newPetId = petApi.givenKittyIsAvailableInPetStore();
        petApi.whenIAskForAPetWithId(newPetId);
        petApi.thenISeeKittyAsResult();
    }
}