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

public class ArrivalsListFragment extends OrmLiteListFragment {

    private static final String STOP_ID = "stopID";
    private static final String DIRECTION = "direction";
    private static final String DEPARTURE_ID = "departureID";

    public ArrivalsListFragment() {}

    public static ArrivalsListFragment newInstance(int stopID, boolean direction) {
        ArrivalsListFragment fragment = new ArrivalsListFragment();
        Bundle args = new Bundle();
        args.putInt(STOP_ID, stopID);
        args.putBoolean(DIRECTION, direction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Arrival> arrivalsList = null;

        if (getArguments() != null) {
            Boolean direction = getArguments().getBoolean(DIRECTION);
            int stopID = getArguments().getInt(STOP_ID);
            RuntimeExceptionDao<Arrival, Integer> arrivalDao = getHelper().getRuntimeArrivalDao();

            //TODO arrivalsList = arrivalDao....
            /*
            QueryBuilder<Arrival, Integer> queryBuilderArrival = arrivalDao.queryBuilder();
            try {
                queryBuilderArrival.where().eq("station", stopID).and().gt("DATE", System.currentTimeMillis() % 1000);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedQuery<Arrival> preparedQuery = null;
            */
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, arrivalsList));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int departureID = ((Arrival) l.getAdapter().getItem(position)).getRide().getIdRide(); //TODO ?
        showDetails(departureID);
    }

    private void showDetails(int id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsActivity.class);
        intent.putExtra(DEPARTURE_ID, id);
        startActivity(intent);
    }
}
