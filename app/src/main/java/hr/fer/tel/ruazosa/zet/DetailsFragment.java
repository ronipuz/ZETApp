package hr.fer.tel.ruazosa.zet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import hr.fer.tel.ruazosa.model.Ride;

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

            List departuresList = null;
            RuntimeExceptionDao<Ride, Integer> departureDao = getHelper().getRuntimeRideDao();
            //TODO departuresList = departureDao....

            StringBuilder departuresSB = new StringBuilder();
            for (Object o : departuresList) { //TODO
                departuresSB.append(o.toString()).append("\n");
            }
            textView.setText(departuresSB.toString());
        }

        return rootView;
    }
}
