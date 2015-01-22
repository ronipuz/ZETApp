package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import hr.fer.tel.ruazosa.model.Tram;

public class LinesListFragment extends OrmLiteListFragment {

    private static final String TRAM_BUS = "tram_bus";
    private static final String LINE_ID = "lineID";

    private boolean dualPane;

    public LinesListFragment() {}

    public static LinesListFragment newInstance(boolean tram_bus) {
        LinesListFragment fragment = new LinesListFragment();
        Bundle args = new Bundle();
        args.putBoolean(TRAM_BUS, tram_bus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Tram> linesList = null;

        if (getArguments() != null) {
            boolean tram_bus = getArguments().getBoolean(TRAM_BUS);
            RuntimeExceptionDao<Tram, Integer> lineDao = getHelper().getRuntimeTramDao();
            linesList = lineDao.queryForEq("tramBus", tram_bus);
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, linesList));

        View departuresFrame = getActivity().findViewById(R.id.departures_frame);
        dualPane = departuresFrame != null && departuresFrame.getVisibility() == View.VISIBLE;

        /*TODO
        if (dualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        int lineID = ((Tram) l.getAdapter().getItem(position)).getIdTram();
        showDepartures(position, lineID);
    }

    private void showDepartures(int index, int id) {
        if (dualPane) {
            //TODO getListView().setItemChecked(index, true);
            DeparturesFragment departuresFragment = (DeparturesFragment)
                    getFragmentManager().findFragmentById(R.id.departures_frame);

            if (departuresFragment == null || departuresFragment.getShownID() != id) {
                departuresFragment = DeparturesFragment.newInstance(id);
                getFragmentManager().beginTransaction()
                        .replace(R.id.departures_frame, departuresFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DeparturesActivity.class);
            intent.putExtra(LINE_ID, id);
            startActivity(intent);
        }
    }
}
