package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by swe on 28.01.14.
 */
public class SplitterRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:/home/swe/camel/input/?delete=true")
            .log(LoggingLevel.INFO, "File ${file:name} received")
            .choice()
                .when(simple("${file:ext} == 'xml'"))
                    .to("direct:xmlFileProcessor")
                .when(simple("${file:ext} == 'txt'"))
                    .to("direct:textFileProcessor")
                .when(simple("${file:ext} == 'csv'"))
                    .to("direct:csvFileProcessor")
                .otherwise()
                .log(LoggingLevel.ERROR, "received file with illegal extension: ${file:ext}")
            .endChoice()
        ;

        from("direct:xmlFileProcessor")
                .split(xpath("//ansatt"))
                .log(LoggingLevel.INFO, "${body}");
    }
}
