package com.avaj.utils;

import com.avaj.aircraft.AircraftFactory;
import com.avaj.simulator.WeatherTower;

import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
public class ScenarioReader {

    private AircraftFactory factory = new AircraftFactory();
    private int mWeatherChanges;
    private WeatherTower mWeatherTower;

    public ScenarioReader(String filePath, WeatherTower wt) throws InputFileException {
        try {
            mWeatherTower = wt;
            FileReader fr = new FileReader(filePath);
            Scanner sc = new Scanner(fr);
            File file = new File("scenario.txt");
            file.delete();
            if (sc.hasNextInt())
            {
                mWeatherChanges = sc.nextInt();
                if (mWeatherChanges <= 0)
                    throw new InputFileException("Wrong number of weather changes.");
                if (sc.hasNextLine()) sc.nextLine();
            }
            else
            {
                throw new InputFileException("Wrong number of weather changes.");
            }
            while (sc.hasNextLine())
                parseLine(sc.nextLine());
        } catch (Exception e)
        {
            throw new InputFileException(e.getMessage());
        }
    }

    private void parseLine(String line) throws Exception{
        String[] parts = line.split(" ");
        if (parts.length != 5)
            throw new Exception("Wrong line format.");
        else
        {
            int longitude, latitude, height;
            longitude = Integer.parseInt(parts[2]);
            latitude = Integer.parseInt(parts[3]);
            height = Integer.parseInt(parts[4]);
            if (longitude <= 0 || latitude <= 0 || height <= 0)
               throw new Exception("Wrong coordinate values.");
            factory.newAircraft(parts[0], parts[1], longitude, latitude, height).registerTower(mWeatherTower);
        }
    }

    public int getWeatherChangesNumber()
    {
        return mWeatherChanges;
    }

}
