package com.avaj.aircraft;

import com.avaj.utils.SimulationWriter;
import com.avaj.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    };

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather){
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "All goes ok.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "We have problems. Zero vision!");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "OMG! Winter is coming!");
                break;
        }

        if (this.coordinates.getHeight() == 0)
        {
            SimulationWriter.log("JetPlane#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            SimulationWriter.log("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        this.weatherTower.register(this);
        SimulationWriter.log("Tower says: JetPlane#" + super.name + "(" + super.id + ")" + " registered to weather tower.");
    }


}
