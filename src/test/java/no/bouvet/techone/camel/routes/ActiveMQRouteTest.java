package no.bouvet.techone.camel.routes;

import no.bouvet.techone.MySerializedPoJo;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sondre.engell on 19.08.2014.
 */

@ContextConfiguration({"classpath:META-INF/spring/broker.xml", "classpath:META-INF/spring/activemq-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveMQRouteTest extends CamelTestSupport {
    protected ApplicationContext applicationContext;
    @Test
    public void testMQRoute() throws Exception {
        context.start();
        Thread.sleep(10000);
    }

    @Test
    public void testMQRouteWithPoJo() throws Exception {
        context.getRouteDefinition(ActiveMQRoute.ACTIVEMQ_ROUTE_NAME).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:start");
            }
        });
        context.start();
        template.sendBody("direct:start", new MySerializedPoJo());
        Thread.sleep(10000);
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/activemq-context.xml");
        return SpringCamelContext.springCamelContext(applicationContext);
    }
}
