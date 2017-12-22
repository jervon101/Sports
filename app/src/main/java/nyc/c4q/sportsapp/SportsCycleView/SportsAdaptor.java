package nyc.c4q.sportsapp.SportsCycleView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.sportsapp.FullGameModel.Fullgameschedule;
import nyc.c4q.sportsapp.R;


/**
 * Created by jervon.arnoldd on 12/17/17.
 */
public class SportsAdaptor extends RecyclerView.Adapter<SportsAdaptor.SportsHolder> {


    List<Fullgameschedule.GameEntry> gameEntries;


    public SportsAdaptor(List<Fullgameschedule.GameEntry> gameEntries) {
        this.gameEntries = gameEntries;
    }

    public SportsAdaptor() {
    }

    @Override
    public SportsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_item, parent, false);

        return new SportsHolder(view);
    }

    @Override
    public void onBindViewHolder(SportsHolder holder, int position) {
        Fullgameschedule.GameEntry entry = gameEntries.get(position);


        holder.home.setText(entry.getHomeTeam().getAbbreviation());
//        holder.home.setText(entry.getHomeTeam().getName());

        holder.away.setText(entry.getAwayTeam().getAbbreviation());

//        holder.away.setText(entry.getAwayTeam().getName());

      holder.time.setText(entry.getTime());
      holder.date.setText(entry.getDate());
      holder.location.setText(entry.getLocation());



    }

    @Override
    public int getItemCount() {
        return gameEntries.size();
    }

    class SportsHolder extends RecyclerView.ViewHolder {
        TextView home, homescore, away, location, awayscore,date,time;

        public SportsHolder(View itemView) {

            super(itemView);
            location = (TextView) itemView.findViewById(R.id.location);
            date=(TextView) itemView.findViewById(R.id.date);
            time=(TextView) itemView.findViewById(R.id.time);
            home=(TextView) itemView.findViewById(R.id.home);
            away=(TextView) itemView.findViewById(R.id.away);
//            homescore=(TextView) itemView.findViewById(R.id.home_score);
//            awayscore=(TextView) itemView.findViewById(R.id.away_score);


        }
    }
}
