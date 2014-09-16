package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by sondre.engell on 16.09.2014.
 */
public class AMQPRoute extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("file:testFiles/mq-input?move=../processedFiles")
                .log(LoggingLevel.INFO, "Fil lest.")
                .convertBodyTo(String.class)
                .to("amqp:queue:amqpTestQueue");


        from("amqp:queue:amqpTestQueue")
                .log(LoggingLevel.INFO, "Fikk melding!!! ${body}");
    }
}
