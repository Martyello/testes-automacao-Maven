package api.tests;

import api.base.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Dog API")
@Feature("Imagem Aleatória")
@Tag("random-image")
@DisplayName("Testes do endpoint GET /breeds/image/random")
public class RandomImageTest extends BaseTest {

    private static final String RANDOM_IMAGE = "/breeds/image/random";
    private static final String RANDOM_IMAGES_MULTIPLE = "/breeds/image/random/{count}";

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar status 200 e status 'success'")
    @Description("Verifica se o endpoint /breeds/image/random retorna HTTP 200 e status success")
    void deveRetornarSucessoAoObterImagemAleatoria() {
        given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar uma URL válida de imagem")
    @Description("Verifica se a URL retornada é válida e aponta para o domínio correto")
    void deveRetornarUrlValidaDeImagem() {
        given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .body("message", startsWith("https://"))
                .body("message", containsString("images.dog.ceo"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar imagem com extensão válida")
    @Description("Verifica se a URL da imagem termina com uma extensão válida (jpg, png, gif)")
    void deveRetornarImagemComExtensaoValida() {
        String imageUrl = given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .extract()
                .path("message");

        assert imageUrl.matches(".*\\.(jpg|jpeg|png|gif)$") :
                "URL da imagem não possui extensão válida: " + imageUrl;
    }

    @RepeatedTest(value = 3, name = "Execução {currentRepetition} de {totalRepetitions}")
    @Severity(SeverityLevel.NORMAL)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar imagens diferentes em chamadas consecutivas")
    @Description("Verifica a aleatoriedade ao fazer múltiplas chamadas")
    void deveRetornarImagensAleatorias() {
        given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Obter múltiplas imagens aleatórias")
    @DisplayName("Deve retornar 3 imagens aleatórias")
    @Description("Verifica se o endpoint retorna a quantidade correta de imagens quando solicitado")
    void deveRetornarMultiplasImagensAleatorias() {
        given()
                .spec(requestSpec)
                .pathParam("count", 3)
                .when()
                .get(RANDOM_IMAGES_MULTIPLE)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", hasSize(3));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Obter múltiplas imagens aleatórias")
    @DisplayName("Deve retornar no máximo 50 imagens")
    @Description("Verifica o limite máximo de imagens retornadas pela API")
    void deveRespeitarLimiteMaximoDeImagens() {
        given()
                .spec(requestSpec)
                .pathParam("count", 50)
                .when()
                .get(RANDOM_IMAGES_MULTIPLE)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", hasSize(50));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar Content-Type JSON")
    @Description("Verifica se o header Content-Type é application/json")
    void deveRetornarContentTypeJson() {
        given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .contentType(containsString("application/json"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Obter imagem aleatória")
    @DisplayName("Deve retornar resposta em tempo aceitável")
    @Description("Verifica se a resposta é retornada em menos de 5 segundos")
    void deveRetornarRespostaEmTempoAceitavel() {
        given()
                .spec(requestSpec)
                .when()
                .get(RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }
}