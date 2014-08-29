package no.bouvet.techone.camel.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by swe on 28.01.14.
 */
public class ChoiceRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:xmlNode")
    protected MockEndpoint xmlNode;

    @EndpointInject(uri = "mock:txtNode")
    protected MockEndpoint txtNode;

    @EndpointInject(uri = "mock:csvNode")
    protected MockEndpoint csvNode;

    @EndpointInject(uri = "mock:otherwiseNode")
    protected MockEndpoint otherwiseNode;

    @EndpointInject(uri = "direct:start")
    protected ProducerTemplate template;


    @Test
    public void testChoiceRoute() throws Exception {
        context.addRoutes(new ChoiceRoute());
        context.start();
        Thread.sleep(10000);
        context.stop();
    }

    @Test
    public void testWithAdvise() throws Exception {
        context.addRoutes(new ChoiceRoute());

        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:start");
                weaveById("xmlNode").replace().to("mock:xmlNode");
                weaveById("txtNode").replace().to("mock:txtNode");
                weaveById("csvNode").replace().to("mock:csvNode");
                weaveById("otherwiseNode").replace().to("mock:otherwiseNode");
            }
        });

        context.start();
        xmlNode.expectedMessageCount(1);
        txtNode.expectedMessageCount(0);
        csvNode.expectedMessageCount(0);
        otherwiseNode.expectedMessageCount(0);
        template.sendBodyAndHeader("", Exchange.FILE_NAME, "test.xml");
        assertMockEndpointsSatisfied();
    }
}
