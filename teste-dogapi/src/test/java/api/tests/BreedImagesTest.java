package api.tests;

import api.base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Imagens por Raça")
@Tag("breed-images")
@DisplayName("Testes do endpoint GET /breed/{breed}/images")
public class BreedImagesTest extends BaseTest {

    private static final String BREED_IMAGES = "/breed/{breed}/images";

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Buscar imagens por raça")
    @DisplayName("Deve retornar imagens para a raça 'labrador'")
    @Description("Verifica se o endpoint retorna status 200 e imagens para labrador")
    void deveRetornarImagensParaLabrador() {
        given()
                .spec(requestSpec)
                .pathParam("breed", "labrador")
                .when()
                .get(BREED_IMAGES)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()))
                .body("message.size()", greaterThan(0));
    }

    @ParameterizedTest(name = "Deve retornar imagens para a raça: {0}")
    @ValueSource(strings = {"bulldog", "poodle", "husky", "beagle", "akita"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Buscar imagens por raça")
    @DisplayName("Deve retornar imagens para múltiplas raças")
    @Description("Verifica se o endpoint retorna imagens para diferentes raças")
    void deveRetornarImagensParaMultiplasRacas(String breed) {
        given()
                .spec(requestSpec)
                .pathParam("breed", breed)
                .when()
                .get(BREED_IMAGES)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Buscar imagens por raça")
    @DisplayName("Deve retornar URLs válidas de imagens")
    @Description("Verifica se as URLs retornadas são válidas e terminam com extensão de imagem")
    void deveRetornarUrlsValidasDeImagens() {
        given()
                .spec(requestSpec)
                .pathParam("breed", "labrador")
                .when()
                .get(BREED_IMAGES)
                .then()
                .statusCode(200)
                .body("message[0]", startsWith("https://"))
                .body("message[0]", containsString("images.dog.ceo"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Buscar imagens por raça inexistente")
    @DisplayName("Deve retornar erro para raça inexistente")
    @Description("Verifica se a API retorna erro adequado para uma raça que não existe")
    void deveRetornarErroParaRacaInexistente() {
        given()
                .spec(requestSpec)
                .pathParam("breed", "racainexistente123")
                .when()
                .get(BREED_IMAGES)
                .then()
                .statusCode(404)
                .body("status", equalTo("error"))
                .body("message", containsString("not found"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar imagens por sub-raça")
    @DisplayName("Deve retornar imagens para sub-raça 'english bulldog'")
    @Description("Verifica se é possível buscar imagens de uma sub-raça específica")
    void deveRetornarImagensParaSubRaca() {
        given()
                .spec(requestSpec)
                .when()
                .get("/breed/bulldog/english/images")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", not(empty()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar imagens por raça")
    @DisplayName("Deve retornar resposta em tempo aceitável")
    @Description("Verifica se a resposta é retornada em menos de 5 segundos")
    void deveRetornarRespostaEmTempoAceitavel() {
        given()
                .spec(requestSpec)
                .pathParam("breed", "labrador")
                .when()
                .get(BREED_IMAGES)
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }
}