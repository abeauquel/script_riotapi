package com.ige487.script_rio_api;

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class StartupScript implements CommandLineRunner {

    public StartupScript() {
        Configuration.createOriannaConfig();
    }

    public void test(){
        League challenger = League.challengerInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
        League master = League.masterInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
    }

    @Override
    public void run(String...args) {
        test();
    }
}
