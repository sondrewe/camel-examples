package no.bouvet.techone.camel.routes;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by swe on 28.01.14.
 */
public class SplitterRouteTest extends CamelTestSupport {

    @Test
    public void testSplitterRoute() throws Exception {
        context.addRoutes(new SplitterRoute());
        context.start();
        Thread.sleep(10000);
    }
}
