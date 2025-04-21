package requestspecification;

import enums.Servico;
import env.AmbienteAtualLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import property.PropertyLoader;
import utils.PrintStreamExtent;

public class RequestSpecificationFactory {

    private static final Logger LOGGER = LogManager.getLogger();

    private static RequestSpecBuilder getRequestBuilder(Servico service) {
        return new RequestSpecBuilder()
                .setConfig(
                        RestAssuredConfig.config()
                                .logConfig(LogConfig
                                        .logConfig()
                                        .enablePrettyPrinting(true)
                                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                                        .defaultStream(new PrintStreamExtent(IoBuilder.forLogger(LOGGER).buildOutputStream()))
                                )
                )
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(PropertyLoader.get().getUrl(AmbienteAtualLoader.get(), service))
                .log(LogDetail.ALL);
    }

    public static RequestSpecification requestSpecification(Servico service) {
        return getRequestBuilder(service).build();
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }
}