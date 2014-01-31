package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mongodb.MongoDbConstants;

/**
 * Created by felles on 30.01.14.
 */
public class RestletRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("restlet:http://localhost:8181/weatherService/windDirection/{direction}")
                .log(LoggingLevel.INFO, "Mottok forespørsel om vindretning ${header.direction}")
                .setBody().simple("{\"location.windDirection.@name\" : \"${header.direction}\"}")
                .setHeader(MongoDbConstants.FIELDS_FILTER, constant("{\"@from\":1, \"@to\":1, \"location.windSpeed\":1}"))
                .to("mongodb:dbConnection?database=weather&collection=hourly&operation=findAll")
                ;

        from("restlet:http://localhost:8181/weatherService/tempBelow/{temp}")
                .log(LoggingLevel.INFO, "Mottok forespørsel om temperatur under ${header.temp}")
                .setBody().simple("{\"location.temperature.@value\" : {$lt : \"${header.temp}\"}}")
                .setHeader(MongoDbConstants.FIELDS_FILTER, constant("{\"@from\":1, \"@to\":1, \"location.temperature\":1}"))
                .to("mongodb:dbConnection?database=weather&collection=hourly&operation=findAll")
        ;

    }
}
