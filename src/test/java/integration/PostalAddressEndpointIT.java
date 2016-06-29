package integration;

import integration.shared.IntegrationServer;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class PostalAddressEndpointIT extends IntegrationServer {

    @Test
    public void should_return_postal_address_from_correct_cep() {
        get("http://127.0.0.1:" + jettyPort + "/postal_address?cep=13300055")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("address", equalTo("Rua Floriano Peixoto - de 911/912 ao fim"))
                .and()
                .body("district", equalTo("Centro"))
                .and()
                .body("city", equalTo("Itu"))
                .and()
                .body("uf", equalTo("SP"));
    }

    @Test
    public void should_return_postal_address_from_new_ceo() {
        get("http://127.0.0.1:" + jettyPort + "/postal_address?cep=05426111")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("address", equalTo("Avenida Brigadeiro Faria Lima - até 1025 - lado ímpar"))
                .and()
                .body("district", equalTo("Pinheiros"))
                .and()
                .body("city", equalTo("Sao Paulo"))
                .and()
                .body("uf", equalTo("SP"));
    }

    @Test
    public void should_return_NOT_FOUND_when_cep_does_not_exist() {
        get("http://127.0.0.1:" + jettyPort + "/postal_address?cep=99999999")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void should_return_BAD_REQUEST_when_cep_invalid() {
        get("http://127.0.0.1:" + jettyPort + "/postal_address?cep=XPTO9123")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("CEP invalido"))
                .and()
                .body("code", equalTo("cep_invalid"));

    }
}
