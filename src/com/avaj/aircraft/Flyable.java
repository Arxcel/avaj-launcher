package com.avaj.aircraft;

import com.avaj.simulator.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower WeatherTower);
}
