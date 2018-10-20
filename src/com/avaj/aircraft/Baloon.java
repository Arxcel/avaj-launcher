package com.avaj.aircraft;

import com.avaj.utils.SimulationWriter;
import com.avaj.simulator.WeatherTower;


public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    };

    public void updateConditions()
    {
        String weather = weatherTower.getWeather(coordinates);
        switch (weather){
            case "SUN":
                coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                SimulationWriter.log("Baloon#" + super.name +"(" + super.id +"): " + "Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                SimulationWriter.log("Baloon#" + super.name +"(" + super.id +"): " + "Damn you rain! You messed up my baloon");
                break;
            case "FOG":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                SimulationWriter.log("Baloon#" + super.name +"(" + super.id +"): " + "Argh can't see anything!");
                break;
            case "SNOW":
                coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                SimulationWriter.log("Baloon#" + super.name +"(" + super.id +"): " + "Brr..., its getting cold up here.");
                break;
        }

        if (this.coordinates.getHeight() == 0)
        {
            SimulationWriter.log("Baloon#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            SimulationWriter.log("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower. Last seen at (" + this.coordinates.getLongitude() + "; " + this.coordinates.getLatitude() + "; "+ this.coordinates.getHeight() + ")");
        }
    }

    public void registerTower(WeatherTower WeatherTower) {
        this.weatherTower = WeatherTower;
        this.weatherTower.register(this);
        SimulationWriter.log("Tower says: Baloon#" + super.name + "(" + super.id + ")" + " registered to weather tower.");
    }


}
