package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import hr.fer.tel.ruazosa.model.Station;

public class StopsListFragment extends OrmLiteListFragment {

    private static final String TRAM_BUS = "tram_bus";
    private static final String SORT = "sort";
    private static final String STOP_ID = "stopID";


    private boolean dualPane;

    public StopsListFragment() {}

    public static LinesListFragment newInstance(boolean tram_bus, boolean sort) {
        LinesListFragment fragment = new LinesListFragment();
        Bundle args = new Bundle();
        args.putBoolean(TRAM_BUS, tram_bus);
        args.putBoolean(SORT, sort);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Station> stopsList = null;

        if (getArguments() != null) {
            boolean tram_bus = getArguments().getBoolean(TRAM_BUS);
            boolean sort = getArguments().getBoolean(SORT);
            RuntimeExceptionDao<Station, Integer> stopDao = getHelper().getRuntimeStationDao();
            stopsList = stopDao.queryForEq("tramBus", tram_bus);
            if (sort) {
                //TODO sortirati listu, najblize stanice prve
            }
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, stopsList));

        View arrivalsFrame = getActivity().findViewById(R.id.arrivals_frame);
        dualPane = arrivalsFrame != null && arrivalsFrame.getVisibility() == View.VISIBLE;

        /*TODO
        if (dualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int lineID = ((Station) l.getAdapter().getItem(position)).getIdStation();
        showArrivals(position, lineID);
    }

    private void showArrivals(int index, int id) {
        if (dualPane) {
            //TODO getListView().setItemChecked(index, true);
            ArrivalsFragment arrivalsFragment = (ArrivalsFragment)
                    getFragmentManager().findFragmentById(R.id.arrivals_frame);

            if (arrivalsFragment == null || arrivalsFragment.getShownID() != id) {
                arrivalsFragment = ArrivalsFragment.newInstance(id);
                getFragmentManager().beginTransaction()
                        .replace(R.id.arrivals_frame, arrivalsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), ArrivalsActivity.class);
            intent.putExtra(STOP_ID, id);
            startActivity(intent);
        }
    }
}
