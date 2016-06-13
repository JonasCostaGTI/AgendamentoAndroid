package ServiceTempo;

import javax.annotation.Generated;

/**
 * Created by jonascosta on 12/06/16.
 */
@Generated("org.jsonschema2pojo")
public class Wind {

    private String speed;
    private String deg;

    /**
     *
     * @return
     * The speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public String getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(String deg) {
        this.deg = deg;
    }
}
