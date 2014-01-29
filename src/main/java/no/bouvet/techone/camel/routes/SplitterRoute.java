package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by felles on 29.01.14.
 */
public class SplitterRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:testFiles/input?include=.*.xml")
            .log(LoggingLevel.INFO, "Mottok xml fil!")
            .split(xpath("//ansatt"))
                .log(LoggingLevel.INFO, "\n${body}")
                .to("direct:processEmployee")
            .end()
            .log(LoggingLevel.INFO, this.getClass().getName(), "Ferdig behandlet\n ${body}");

        from("direct:processEmployee")
            .marshal().xmljson()
            .log(LoggingLevel.INFO, "Konvertert XML til json:\n ${body}")
        ;
    }
}
