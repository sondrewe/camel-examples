package no.bouvet.techone.camel.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Sondre Wabakken Engell on 28.01.14.
 *
 * Simple route to demonstrate the file component.
 */
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:testFiles/input?move=../processedFiles")
            .log(LoggingLevel.INFO, "Mottok fil med navn ${file:name}");
    }
}
