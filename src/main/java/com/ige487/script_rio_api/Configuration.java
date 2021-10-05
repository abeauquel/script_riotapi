package com.ige487.script_rio_api;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;


public abstract class Configuration {

    public static void createOriannaConfig(){
        Orianna.Configuration config = new Orianna.Configuration();
        Orianna.loadConfiguration(config);
        Orianna.setRiotAPIKey("RGAPI-00767119-a3a9-4ce9-b77e-cc5adde2170d\n");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);
    }
}
