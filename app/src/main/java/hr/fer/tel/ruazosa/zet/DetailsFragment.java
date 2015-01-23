package hr.fer.tel.ruazosa.zet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;

public class DetailsFragment extends OrmLiteFragment {

    private static final String DEPARTURE_ID = "departureID";

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.details_text);

        if (getArguments() != null) {
            int departureID = getArguments().getInt(DEPARTURE_ID);

            RuntimeExceptionDao<Arrival, Integer> arrivalDao = getHelper().getRuntimeArrivalDao();
            RuntimeExceptionDao<Station, Integer> stopDao = getHelper().getRuntimeStationDao();

            List<Arrival> arrivalList = arrivalDao.queryForEq("ride_id", departureID);

            List<DetailView> finalList = null;
            QueryBuilder<Station, Integer> queryBuilderStop = stopDao.queryBuilder();
            try {
                for (Arrival a : arrivalList) {
                    String stopName = queryBuilderStop.where().eq("idStation", a.getStation().getIdStation()).queryForFirst().getName();
                    finalList.add(new DetailView(a.getTime(), stopName));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            StringBuilder departuresSB = new StringBuilder();
            for (DetailView v : finalList) {
                departuresSB.append(v.toString()).append("\n");
            }
            textView.setText(departuresSB.toString());
        }

        return rootView;
    }
}
