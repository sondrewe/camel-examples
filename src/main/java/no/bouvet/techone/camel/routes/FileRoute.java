package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by swe on 28.01.14.
 */
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:testFiles/input?move=../processedFiles")
            .log(LoggingLevel.INFO, "Mottok fil med navn ${file:name}");
    }
}
