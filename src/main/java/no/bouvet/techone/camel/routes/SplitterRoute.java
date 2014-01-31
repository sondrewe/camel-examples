package no.bouvet.techone.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Sondre Wabakken Engell on 29.01.14.
 *
 * Route to demonstrate the use of the splitter processor.
 * Also uses the file and direct components as well as the splitter processor.
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
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    String s = exchange.getIn().getBody(String.class);
                }
            })
                .marshal().xmljson()
                .log(LoggingLevel.INFO, "Konvertert XML til json:\n ${body}")
        ;
    }
}
