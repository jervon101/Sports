package nyc.c4q.sportsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.sportsapp.FullGameModel.Fullgameschedule;
import nyc.c4q.sportsapp.FullGameModel.Game;
import nyc.c4q.sportsapp.SportsCycleView.SportsAdaptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView sportView;
    List<Fullgameschedule.GameEntry> mygame = new ArrayList<>();
    SportsAdaptor adaptor;
    RecyclerView reminderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecycleView();
        CreateRetrofit();


    }


    public void RecycleView(){
        sportView = (RecyclerView) findViewById(R.id.cycleView);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        sportView.setLayoutManager(manager);
        sportView.setAdapter(adaptor);







    }
    public void CreateRetrofit() {
        final String authHeader = Base64.encodeToString(INFO.getUserCredentials().getBytes(), Base64.NO_WRAP);

        String encoding = Base64.encodeToString(INFO.getUserCredentials().getBytes(), Base64.NO_WRAP);

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Api.FULL_GAME_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api user = retrofit1.create(Api.class);

        Call<Game> call = user.getGameSchedule("Basic " + encoding,"tomorrow");

        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                Game game = response.body();

                String myDate = game.getFullgameschedule().getGameentry()[1].getDate();
                String re = myDate.replace("-", "");
                String date = re;
                String hello = date+"-"+game.getFullgameschedule().getGameentry()[1].getAwayTeam().getAbbreviation() +"-"+game.getFullgameschedule().getGameentry()[1].getHomeTeam().getAbbreviation();

                for (int i = 0; i < game.getFullgameschedule().getGameentry().length; i++) {

                    mygame.add(game.getFullgameschedule().getGameentry()[i]);
                }

                adaptor = new SportsAdaptor(mygame);
                sportView.setAdapter(adaptor);
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e("Failure", t.getMessage());
                Log.e("Failure", t.getLocalizedMessage());

            }
        });


    }
}
