package no.bouvet.techone.camel.routes;

import no.bouvet.techone.MySerializedPoJo;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sondre.engell on 16.09.2014.
 */

public class AMQPRouteTest extends CamelTestSupport {
    protected ApplicationContext applicationContext;

    @Test
    public void testAMQPRouteWithPoJo() throws Exception {
        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
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
        applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/amqp-context.xml");
        return SpringCamelContext.springCamelContext(applicationContext);
    }

}
