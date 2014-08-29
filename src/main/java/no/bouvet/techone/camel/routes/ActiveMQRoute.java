package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by sondre.engell on 19.08.2014.
 */
public class ActiveMQRoute extends RouteBuilder {


    public static final String ACTIVEMQ_ROUTE_NAME = "activeMQRoute";

    @Override
    public void configure() throws Exception {
        from("file:testFiles/mq-input?move=../processedFiles").routeId(ACTIVEMQ_ROUTE_NAME)
                .log(LoggingLevel.INFO, "Fil lest.")
                .convertBodyTo(String.class)
                .to("activemq:queue:myTestQueue");

        from("activemq:queue:myTestQueue")
                .log(LoggingLevel.INFO, "Mottok melding på kø: ${body}");
    }


}
