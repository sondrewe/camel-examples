package no.bouvet.techone.camel.routes;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by swe on 28.01.14.
 */
public class FileRouteTest extends CamelTestSupport {

    @Test
    public void fileRouteTest() throws Exception {
        context.addRoutes(new FileRoute());
        context.start();
        Thread.sleep(10000);
    }

}
