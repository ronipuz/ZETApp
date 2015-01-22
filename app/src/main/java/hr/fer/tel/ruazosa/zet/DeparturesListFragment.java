package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DeparturesListFragment extends ListFragment {

    private static final String LINE_ID = "lineID";
    private static final String DEPARTURE_RETURN = "departure_return";
    private static final String DEPARTURE_ID = "departureID";

    public DeparturesListFragment() {}

    public static DeparturesListFragment newInstance(int lineID, String departure_return) {
        DeparturesListFragment fragment = new DeparturesListFragment();
        Bundle args = new Bundle();
        args.putInt(LINE_ID, lineID);
        args.putString(DEPARTURE_RETURN, departure_return);
        fragment.setArguments(args);
        return fragment;
    }

    @Override //TODO onActivityCreated ?
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> departuresList = new ArrayList<>();

        if (getArguments() != null) {
            String departure_return = getArguments().getString(DEPARTURE_RETURN);
            int lineID = getArguments().getInt(LINE_ID);

            //TODO dohvat liste polazaka iz baze
            for (int i = 0; i < 10; i++) {
                departuresList.add("linija " + (lineID+1) + " " + departure_return + " " + (i+1));
            }
        }

        setListAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, departuresList));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //TODO dohvat id-a polaska
        int departureID = position;

        showDetails(departureID);
    }

    private void showDetails(int id) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsActivity.class);
        intent.putExtra(DEPARTURE_ID, id);
        startActivity(intent);
    }
}
