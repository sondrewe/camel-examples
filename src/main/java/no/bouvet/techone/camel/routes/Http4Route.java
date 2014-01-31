package no.bouvet.techone.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by Sondre Wabakken Engell on 30.01.14.
 */
public class Http4Route extends RouteBuilder {

    public static final String ROUTE_NAME = "WeatherRoute";

    @Override
    public void configure() throws Exception {
        from("quartz://weatherTrigger?cron=0+0+0/1+*+*+?").routeId(ROUTE_NAME)
                .log(LoggingLevel.INFO, "Started!")
                .setHeader(Exchange.HTTP_QUERY, constant("lat=59.57;lon=10.45"))
                .to("http4://api.met.no/weatherapi/locationforecast/1.8/")
                .split().xpath("//time")
                    .filter().xpath("/time[@to=@from]")
                    .marshal().xmljson().convertBodyTo(String.class)
                    .to("mongodb:dbConnection?database=weather&collection=hourly&operation=save")
                .end()
        ;
    }
}
