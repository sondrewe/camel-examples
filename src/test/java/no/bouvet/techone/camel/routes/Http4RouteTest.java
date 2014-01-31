package no.bouvet.techone.camel.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Sondre Wabakken Engell on 31.01.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/camel-context.xml")
public class Http4RouteTest extends CamelTestSupport{
    protected ApplicationContext applicationContext;

    @Test
    public void testHttpRoute() throws Exception {
        context.addRoutes(new Http4Route());
        context.getRouteDefinition(Http4Route.ROUTE_NAME).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:startWeatherRoute");
            }
        });
        context.start();
        template.sendBody("direct:startWeatherRoute", null);
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
        return SpringCamelContext.springCamelContext(applicationContext);
    }
}
