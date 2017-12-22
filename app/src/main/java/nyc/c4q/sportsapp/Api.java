package nyc.c4q.sportsapp;


import nyc.c4q.sportsapp.FullGameModel.Game;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by jervon.arnoldd on 12/13/17.
 */

public interface Api {

    String FULL_GAME_API = "https://api.mysportsfeeds.com/";
    @GET("v1.1/pull/nba/2017-2018-regular/full_game_schedule.json")
    Call<Game> getGameSchedule(@Header("Authorization") String user, @Query("date") String date);



}
