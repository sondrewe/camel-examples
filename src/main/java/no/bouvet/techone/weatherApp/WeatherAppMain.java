package no.bouvet.techone.weatherApp;

import no.bouvet.techone.camel.routes.Http4Route;
import no.bouvet.techone.camel.routes.RestletRoute;
import org.apache.camel.spring.Main;

/**
 * Created by Sondre Wabakken Engell on 31.01.14.
 *
 * A main method to be able to run the Http4 and Restlet routes.
 */
public class WeatherAppMain {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.run(args);
    }
}
