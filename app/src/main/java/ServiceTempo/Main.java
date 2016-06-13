package ServiceTempo;

import javax.annotation.Generated;

/**
 * Created by jonascosta on 12/06/16.
 */
@Generated("org.jsonschema2pojo")
public class Main {


    private String temp;
    private String pressure;
    private String humidity;
    private String tempMin;
    private String tempMax;
    private String seaLevel;
    private String grndLevel;

    /**
     *
     * @return
     * The temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The pressure
     */
    public String getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The tempMin
     */
    public String getTempMin() {
        return tempMin;
    }

    /**
     *
     * @param tempMin
     * The temp_min
     */
    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    /**
     *
     * @return
     * The tempMax
     */
    public String getTempMax() {
        return tempMax;
    }

    /**
     *
     * @param tempMax
     * The temp_max
     */
    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    /**
     *
     * @return
     * The seaLevel
     */
    public String getSeaLevel() {
        return seaLevel;
    }

    /**
     *
     * @param seaLevel
     * The sea_level
     */
    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    /**
     *
     * @return
     * The grndLevel
     */
    public String getGrndLevel() {
        return grndLevel;
    }

    /**
     *
     * @param grndLevel
     * The grnd_level
     */
    public void setGrndLevel(String grndLevel) {
        this.grndLevel = grndLevel;
    }
}
