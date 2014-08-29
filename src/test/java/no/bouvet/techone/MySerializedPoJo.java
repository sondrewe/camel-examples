package no.bouvet.techone;

import java.io.Serializable;

/**
 * Created by sondre.engell on 19.08.2014.
 */
public class MySerializedPoJo implements Serializable {
    private String propertyOne;

    public MySerializedPoJo() {
        this.propertyOne = "DefaultValue";
    }

    public String getPropertyOne() {
        return propertyOne;
    }

    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    @Override
    public String toString() {
        return "MySerializedPoJo{" +
                "propertyOne='" + propertyOne + '\'' +
                '}';
    }
}
