package com.ige487.script_rio_api;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;


public abstract class Configuration {

    public static void createOriannaConfig(){
        Orianna.Configuration config = new Orianna.Configuration();
        Orianna.loadConfiguration(config);
        Orianna.setRiotAPIKey("RGAPI-873ed9b5-0513-43fd-8d3f-079b3c43a621");
        Orianna.setDefaultRegion(Region.NORTH_AMERICA);
    }
}
