package com.avaj.simulator;

import com.avaj.utils.InputFileException;
import com.avaj.utils.ScenarioReader;

public class Simulator {

    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new InputFileException("Wrong number of arguments.");
            WeatherTower wt = new WeatherTower();
            ScenarioReader sr = new ScenarioReader(args[0], wt);
            int cycles = sr.getWeatherChangesNumber();

            while (cycles-- > 0)
                wt.changeWeather();
        } catch (InputFileException exception){
            System.out.println(exception.getMessage());
        }
    }
}
