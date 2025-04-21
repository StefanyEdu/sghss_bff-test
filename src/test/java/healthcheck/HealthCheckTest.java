package healthcheck;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import enums.Servico;
import env.AmbienteAtualLoader;
import io.sicredi.tm4j.testng.TM4JTestNGListener;

import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import property.PropertyLoader;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.is;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class HealthCheckTest {

    @Test(groups = {"healthCheck"})
    public void shouldReturn200_HealthCheck() {
        given()
                .when()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .get(PropertyLoader.get().getUrl(AmbienteAtualLoader.get(), Servico.PUFV_CONTRATO) + "actuator/health")
                .then()
                .spec(responseSpecification())
                .statusCode(HttpStatus.SC_OK)
                .body("status", is("UP"));
    }
}
