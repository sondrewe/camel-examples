package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by felles on 30.01.14.
 */
public class RestletRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("restlet:http://localhost:8181/myWebservice/{id}")
                .log(LoggingLevel.INFO, "Mottok melding med id ${header.id}")
                .setBody().simple("${header.id} er mottatt!")
                ;

    }
}
