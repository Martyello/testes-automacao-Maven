package api.tests;

import api.base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Listagem de Raças")
@Tag("breeds")
@DisplayName("Testes do endpoint GET /breeds/list/all")
public class BreedsListTest extends BaseTest {

    private static final String BREEDS_LIST_ALL = "/breeds/list/all";

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Listar todas as raças")
    @DisplayName("Deve retornar status 200 e status 'success'")
    @Description("Verifica se o endpoint /breeds/list/all retorna HTTP 200 e status success")
    void deveRetornarSucessoAoListarTodasRacas() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Listar todas as raças")
    @DisplayName("Deve retornar uma lista não vazia de raças")
    @Description("Verifica se o endpoint retorna pelo menos uma raça na lista")
    void deveRetornarListaNaoVaziaDeRacas() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .body("message", not(anEmptyMap()))
                .body("message.size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Listar todas as raças")
    @DisplayName("Deve conter raças conhecidas na lista")
    @Description("Verifica se raças populares como 'bulldog', 'labrador' e 'poodle' estão presentes")
    void deveConterRacasConhecidas() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .body("message", hasKey("bulldog"))
                .body("message", hasKey("labrador"))
                .body("message", hasKey("poodle"))
                .body("message", hasKey("husky"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Listar todas as raças")
    @DisplayName("Deve retornar sub-raças para bulldog")
    @Description("Verifica se a raça 'bulldog' possui sub-raças como 'english' e 'french'")
    void deveRetornarSubRacasParaBulldog() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .body("message.bulldog", hasItems("english", "french"));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Listar todas as raças")
    @DisplayName("Deve retornar Content-Type JSON")
    @Description("Verifica se o header Content-Type é application/json")
    void deveRetornarContentTypeJson() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Listar todas as raças")
    @DisplayName("Deve retornar resposta em tempo aceitável")
    @Description("Verifica se a resposta é retornada em menos de 5 segundos")
    void deveRetornarRespostaEmTempoAceitavel() {
        given()
                .spec(requestSpec)
                .when()
                .get(BREEDS_LIST_ALL)
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }
}