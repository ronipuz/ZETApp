package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LinesListFragment extends ListFragment {

    private static final String TRAM_BUS = "tram_bus";
    private static final String LINE_ID = "lineID";

    private boolean dualPane;

    public LinesListFragment() {}

    public static LinesListFragment newInstance(String tram_bus) {
        LinesListFragment fragment = new LinesListFragment();
        Bundle args = new Bundle();
        args.putString(TRAM_BUS, tram_bus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> linesList = new ArrayList<>();

        if (getArguments() != null) {
            String tram_bus = getArguments().getString(TRAM_BUS);

            //TODO dohvat liste linija iz baze
            for (int i = 0; i < 5; i++) {
                linesList.add(tram_bus + " " + (i + 1));
            }
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

        //TODO dohvati id-a odabrane linije
        int lineID = position;

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
