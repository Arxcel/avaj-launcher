package com.avaj.utils;

import java.io.FileWriter;

public class SimulationWriter {

    static public void log(String logMessage)
    {
        try {
            FileWriter writer = new FileWriter("simulation", true);
            writer.write(logMessage + "\n");
            writer.close();
        } catch (Exception e)
        {
            System.out.println("Error writing the simulation.");
            System.out.println(e.getMessage());
        }
    }
}
