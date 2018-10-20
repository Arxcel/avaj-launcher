package com.avaj.aircraft;

import com.avaj.utils.SimulationWriter;
import com.avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter (String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    ;

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather){
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "This is hot.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "Rain. What an awful weather.");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "The fog is everywhere!");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                SimulationWriter.log("JetPlane#" + super.name +"(" + super.id +"): " + "My rotor is going to freeze!");
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
        SimulationWriter.log("Tower says: Helicopter#" + super.name + "(" + super.id + ")" + " registered to weather tower.");
    }
}
