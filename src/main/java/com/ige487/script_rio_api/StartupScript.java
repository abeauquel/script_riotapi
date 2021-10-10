package com.ige487.script_rio_api;

import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Season;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeagueEntry;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistories;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.match.Matches;
import com.merakianalytics.orianna.types.core.staticdata.*;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import com.merakianalytics.orianna.types.core.summoner.Summoners;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class StartupScript implements CommandLineRunner {
    public StartupScript() {
        Configuration.createOriannaConfig();
    }

    public League listChallenger(){
        return League.challengerInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
       // League master = League.masterInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
    }

    public void createScriptChampion(){
        Champions champions = Champions.withRegion(Region.NORTH_AMERICA).get();
        MyFileWriter myFileWriter = new MyFileWriter("champion"+System.currentTimeMillis()+".sql");
        List<String> lines = new ArrayList<>();
        for (Champion champion:champions) {
            String line = "INSERT INTO public.champion (nom, difficulte, defense, puissance, resistance, vie, endurance, portee, vitesse, regionid) " +
                    "VALUES ('"+champion.getName().replace("'", "") +"', "+champion.getDifficultyRating()+
                    ", "+champion.getStats().getArmor()+", "+champion.getStats().getAttackDamage()+", "+champion.getStats().getMagicResist()+", " +
                    champion.getStats().getHealth()+ ", "+ champion.getStats().getMana() +", "+champion.getStats().getAttackRange()+", "+ champion.getStats().getMovespeed() +", 1);";
            lines.add(line);
        }
        myFileWriter.writeListStringInFile(lines);
    }

    public void createScriptJoueurs(){
        League challengers =  League.challengerInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
        League masters =  League.masterInQueue(Queue.RANKED_SOLO).withRegion(Region.NORTH_AMERICA).get();
        MyFileWriter myFileWriter = new MyFileWriter("joueur"+System.currentTimeMillis()+".sql");
        List<String> lines = new ArrayList<>();

        for (LeagueEntry joueur : challengers) {
            String line = "INSERT INTO public.joueur (nom, serveur_id, rang_id)" +
                    " VALUES ('"+joueur.getSummoner().getName().replace("'", "") +"', 1, 1);";
            lines.add(line);
        }

        for (LeagueEntry joueur : masters) {
            String line = "INSERT INTO public.joueur (nom, serveur_id, rang_id)" +
                    " VALUES ('"+joueur.getSummoner().getName().replace("'", "") +"', 1, 2);";
            lines.add(line);
        }

        myFileWriter.writeListStringInFile(lines);
    }

    public void createScriptItem(){
        // Items
        Items items = Items.withRegion(Region.NORTH_AMERICA).get();
        MyFileWriter myFileWriter = new MyFileWriter("equipement"+System.currentTimeMillis()+".sql");
        List<String> lines = new ArrayList<>();

        for (Item equipement : items) {
            String line = "INSERT INTO public.equipement (nom, prix, description, vie, defense, puissance, resistance, regionid)" +
                    " VALUES ('"+equipement.getName().replace("'", "") +"', " +
                    equipement.getBasePrice()+ ", '"+ equipement.getPlaintext().replace("'", "") + "', " +
                    equipement.getStats().getHealth() +", "+ equipement.getStats().getArmor() + ", "+
                    equipement.getStats().getAttackDamage() + ", "+ equipement.getStats().getMagicResist() +  ", 1);";
            lines.add(line);
        }
        myFileWriter.writeListStringInFile(lines);
    }
    public void createScriptRune(){
        // Reforged Runes
        ReforgedRunes reforgedRunes = ReforgedRunes.withRegion(Region.NORTH_AMERICA).get();
        MyFileWriter myFileWriter = new MyFileWriter("atout"+System.currentTimeMillis()+".sql");
        List<String> lines = new ArrayList<>();

        for (ReforgedRune rune : reforgedRunes) {
            String shortDesc = rune.getShortDescription().replace("'", "");
            shortDesc = shortDesc.replace("<b>", "");
            shortDesc = shortDesc.replace("</b>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_AdaptiveDmg>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Takedown>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Takedown>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_MS>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_MS>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_ImpairMov>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_ImpairMov>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_MS>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_MS>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Adaptive>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Adaptive>", "");
            shortDesc = shortDesc.replace("<i>", "");
            shortDesc = shortDesc.replace("</i>", "");
            shortDesc = shortDesc.replace("</br>", "");
            shortDesc = shortDesc.replace("<br>", "");
            shortDesc = shortDesc.replace("+<attention>", "");
            shortDesc = shortDesc.replace("</attention>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Immobilize>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Immobilize>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_ImpairAct>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_ImpairAct>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_CDR>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_CDR>", "");
            shortDesc = shortDesc.replace("<lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Stasis>", "");
            shortDesc = shortDesc.replace("</lol-uikit-tooltipped-keyword key=LinkTooltip_Description_Stasis>", "");
            if(shortDesc.length() > 255)
                shortDesc = shortDesc.substring(0, 254);
            String line = "INSERT INTO public.atout (nom, description)" +
                    " VALUES ('"+rune.getName().replace("'", "") +"'," +
                    " '"+ shortDesc+ "' );";
            lines.add(line);
        }
        myFileWriter.writeListStringInFile(lines);
    }



    @Override
    public void run(String...args) {
        //createScriptChampion();
        createScriptJoueurs();
        //createScriptItem();
        //createScriptRune();
        //League challenger =  listChallenger();
      //  Champions champions = listChampion();


        //MatchHistory recentHistory = MatchHistory.forSummoner(challenger.get(0).getSummoner()).get();


//        List<Match> matches = Matches.withIds(4052884132L, 4052911647L).withRegion(Region.NORTH_AMERICA).get();
//       // List<MatchHistory> recentHistories = MatchHistories.forSummoners(challenger).fromRecentMatches().get();
//
//        List<Summoner> summoners = Summoners.named("Alexinsecte2", "Kalturi").withRegion(Region.NORTH_AMERICA).get();
//        List<MatchHistory> histories = MatchHistories.forSummoners(summoners).get();
    }
}
