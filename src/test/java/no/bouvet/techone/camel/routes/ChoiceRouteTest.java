package no.bouvet.techone.camel.routes;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by swe on 28.01.14.
 */
public class ChoiceRouteTest extends CamelTestSupport {

    @Test
    public void testSplitterRoute() throws Exception {
        context.addRoutes(new ChoiceRoute());
        context.start();
        Thread.sleep(10000);
    }
}
