package com.example.jsonobject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class QueryUtils {

    // JSON string from which data is being fetched
    private static final String jsonString = "[{\"match_id\":\"902316\",\"country_id\":\"1\",\"country_name\":\"Eurocups\",\"league_id\":\"1\",\"league_name\":\"European Championship - Final\",\"match_date\":\"2021-07-11\",\"match_status\":\"After Pen.\",\"match_time\":\"21:00\",\"match_hometeam_id\":\"3\",\"match_hometeam_name\":\"Italy\",\"match_hometeam_score\":\"2\",\"match_awayteam_name\":\"England\",\"match_awayteam_id\":\"16\",\"match_awayteam_score\":\"1\",\"match_hometeam_halftime_score\":\"0\",\"match_awayteam_halftime_score\":\"1\",\"match_hometeam_extra_score\":\"0\",\"match_awayteam_extra_score\":\"0\",\"match_hometeam_penalty_score\":\"3\",\"match_awayteam_penalty_score\":\"2\",\"match_hometeam_ft_score\":\"1\",\"match_awayteam_ft_score\":\"1\",\"match_hometeam_system\":\"4-3-3\",\"match_awayteam_system\":\"3-4-2-1\",\"match_live\":\"0\",\"match_round\":\"Final\",\"match_stadium\":\"Wembley Stadium (London)\",\"match_referee\":\"B. Kuipers\",\"team_home_badge\":\"https://apiv3.apifootball.com/badges/3_italy.jpg\",\"team_away_badge\":\"https://apiv3.apifootball.com/badges/16_england.jpg\",\"league_logo\":\"\",\"country_logo\":\"\",\"fk_stage_key\":\"4\",\"stage_name\":\"Final\",\"goalscorer\":[{\"time\":\"2\",\"home_scorer\":\"\",\"home_scorer_id\":\"\",\"home_assist\":\"\",\"home_assist_id\":\"\",\"score\":\"0 - 1\",\"away_scorer\":\"L. Shaw\",\"away_scorer_id\":\"2013220432\",\"away_assist\":\"K. Trippier\",\"away_assist_id\":\"2722594872\",\"info\":\"\"}],\"cards\":[{\"time\":\"47\",\"home_fault\":\"N. Barella\",\"card\":\"yellow card\",\"away_fault\":\"\",\"info\":\"\"}],\"substitutions\":{\"home\":[{\"time\":\"54\",\"substitution\":\"N. Barfgella | B. Cristfgfante\"},{\"time\":\"60\",\"substitution\":\"N. Barehgflla | B. Crisfgdtante\"},{\"time\":\"70\",\"substitution\":\"N. dff| B. dsfsf\"},{\"time\":\"80\",\"substitution\":\"N. dfdf| B. sdfsd\"}],\"away\":[{\"time\":\"30\",\"substitution\":\"K. asa| B. Sartka\"},{\"time\":\"40\",\"substitution\":\"K. ytr| B. Sartyka\"},{\"time\":\"58\",\"substitution\":\"K. sdfsd| B. nrhfghf\"},{\"time\":\"47\",\"substitution\":\"K. gfdg| B. dfger\"},{\"time\":\"75\",\"substitution\":\"K. sdfs| B. kjhkghjk\"}]},\"lineup\":{\"home\":{\"starting_lineups\":[{\"lineup_player\":\"Ciro Immobile\",\"lineup_number\":\"17\",\"lineup_position\":\"10\",\"player_key\":\"2681696639\"}],\"substitutes\":[{\"lineup_player\":\"Alessandro Bastoni\",\"lineup_number\":\"23\",\"lineup_position\":\"0\",\"player_key\":\"2283533776\"}],\"coach\":[{\"lineup_player\":\"R. Mancini\",\"lineup_number\":\"\",\"lineup_position\":\"\",\"player_key\":\"3244928587\"}],\"missing_players\":[]},\"away\":{\"starting_lineups\":[{\"lineup_player\":\"Declan Rice\",\"lineup_number\":\"4\",\"lineup_position\":\"7\",\"player_key\":\"353204575\"}],\"substitutes\":[{\"lineup_player\":\"Aaron Ramsdale\",\"lineup_number\":\"13\",\"lineup_position\":\"0\",\"player_key\":\"2971117080\"}],\"coach\":[{\"lineup_player\":\"G. Southgate\",\"lineup_number\":\"\",\"lineup_position\":\"\",\"player_key\":\"2968444313\"}],\"missing_players\":[]}},\"statistics\":[{\"type\":\"Shots Total\",\"home\":\"19\",\"away\":\"6\"}],\"statistics_1half\":[{\"type\":\"Shots Total\",\"home\":\"17\",\"away\":\"5\"}]}]";

    /**
     * @return Home Team Name
     */
    public static String getHomeTeamName() {
        String homeTeamName = "";
        try {
            JSONArray rootJsonArray = new JSONArray(jsonString);
            JSONObject rootJson = rootJsonArray.getJSONObject(0);
             homeTeamName = rootJson.getString("match_hometeam_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return homeTeamName;
    }

    /**
     * @return Away Team Name
     */
    public static String getAwayTeamName() {
        String awayTeamName = "";
        try {
            JSONArray rootJsonArray = new JSONArray(jsonString);
            JSONObject rootJson = rootJsonArray.getJSONObject(0);
            awayTeamName = rootJson.getString("match_awayteam_name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return awayTeamName;
    }

    public static ArrayList<Substitution> getFirstTeamSubstitution() {
        ArrayList<Substitution> substitutionArrayList = new ArrayList<>();
        JSONArray rootJsonArray;
        try {
            rootJsonArray = new JSONArray(jsonString);
            JSONObject rootJson = rootJsonArray.getJSONObject(0);

            JSONObject substitution = rootJson.getJSONObject("substitutions");
            JSONArray home = substitution.getJSONArray("home");
            JSONArray away = substitution.getJSONArray("away");

            // adding home substitution into collection
            for (int i = 0; i < home.length(); i++) {
                JSONObject subsObj = home.getJSONObject(i);
                int time = subsObj.getInt("time");
                String subs = subsObj.getString("substitution");
                substitutionArrayList.add(new Substitution(time, subs, "home"));
            }

            // adding away substitution into collection
            for (int i = 0; i < away.length(); i++) {
                JSONObject subsObj = away.getJSONObject(i);
                int time = subsObj.getInt("time");
                String subs = subsObj.getString("substitution");
                substitutionArrayList.add(new Substitution(time, subs, "away"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // sorting of collection order by times
        Collections.sort(substitutionArrayList, (o1, o2) -> String.valueOf(o1.getTime()).compareTo(String.valueOf(o2.getTime())));
        return substitutionArrayList;
    }
}
