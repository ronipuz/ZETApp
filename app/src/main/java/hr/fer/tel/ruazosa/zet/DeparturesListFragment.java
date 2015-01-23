package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;

public class DeparturesListFragment extends OrmLiteListFragment {

    private static final String LINE_ID = "lineID";
    private static final String DEPARTURE_RETURN = "departure_return";
    private static final String DEPARTURE_ID = "departureID";

    public DeparturesListFragment() {}

    public static DeparturesListFragment newInstance(int lineID, boolean departure_return) {
        DeparturesListFragment fragment = new DeparturesListFragment();
        Bundle args = new Bundle();
        args.putInt(LINE_ID, lineID);
        args.putBoolean(DEPARTURE_RETURN, departure_return);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<DepartureView> finalList = null;

        if (getArguments() != null) {
            Boolean departure_return = getArguments().getBoolean(DEPARTURE_RETURN);
            int lineID = getArguments().getInt(LINE_ID);

            RuntimeExceptionDao<Ride, Integer> departureDao = getHelper().getRuntimeRideDao();
            RuntimeExceptionDao<Arrival, Integer> arrivalDao = getHelper().getRuntimeArrivalDao();
            RuntimeExceptionDao<Station, Integer> stopDao = getHelper().getRuntimeStationDao();

            QueryBuilder<Ride, Integer> queryBuilderRide = departureDao.queryBuilder();
            QueryBuilder<Arrival, Integer> queryBuilderArrival = arrivalDao.queryBuilder();
            QueryBuilder<Station, Integer> queryBuilderStop = stopDao.queryBuilder();

            try {
                queryBuilderRide.where().eq("tram_id", lineID)
                        .and().eq("direction", departure_return);
                List<Ride> departuresList = departureDao.query(queryBuilderRide.prepare());

                for (Ride r : departuresList) {
                    queryBuilderArrival.where().eq("ride_id", r.getIdRide());
                    queryBuilderArrival.orderBy("DATE", true);
                    Arrival firstArrival = queryBuilderArrival.queryForFirst();
                    queryBuilderArrival.orderBy("DATE",false);
                    Arrival lastArrival = queryBuilderArrival.queryForFirst();

                    String firstName = queryBuilderStop.where()
                            .eq("idStation", firstArrival.getStation().getIdStation()).queryForFirst().getName();
                    String lastName = queryBuilderStop.where()
                            .eq("idStation", lastArrival.getStation().getIdStation()).queryForFirst().getName();

                    finalList.add(new DepartureView(firstArrival.getTime(), firstName, lastName));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, finalList));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int departureID = ((Ride) l.getAdapter().getItem(position)).getIdRide();
        showDetails(departureID);
    }

    private void showDetails(int id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsActivity.class);
        intent.putExtra(DEPARTURE_ID, id);
        startActivity(intent);
    }
}
