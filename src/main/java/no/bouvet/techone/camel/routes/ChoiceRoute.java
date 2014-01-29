package no.bouvet.techone.camel.routes;

import static org.apache.camel.LoggingLevel.INFO;
import static org.apache.camel.LoggingLevel.ERROR;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by swe on 28.01.14.
 */
public class ChoiceRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("ftp://user@myFtpServer/input/?password=secret&move=processedFiles")
            .log(INFO, "File ${file:name} received")
            .choice()
                .when(simple("${file:ext} == 'xml'"))
                    .to("file:testFiles/xmlOut")
                .when(simple("${file:ext} == 'txt'"))
                    .to("file:testFiles/txtOut")
                .when(simple("${file:ext} == 'csv'"))
                    .to("file:testFiles/csvOut")
                .otherwise()
                    .log(ERROR, "received file with illegal extension: ${file:ext}")
            .endChoice()
        ;
    }
}
