package no.bouvet.techone.camel.routes;

import junit.framework.TestCase;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by felles on 30.01.14.
 */
public class RestletRouteTest extends CamelTestSupport {

    @Test
    public void testRestletRoute() throws Exception {
        context.addRoutes(new RestletRoute());
        context.start();
        Thread.sleep(10000);
    }
}
