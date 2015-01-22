package hr.fer.tel.ruazosa.zet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    private static final String DEPARTURE_ID = "departureID";

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.details_text);

        StringBuilder departuresSB = new StringBuilder();
        if (getArguments() != null) {
            int departureID = getArguments().getInt(DEPARTURE_ID);

            //TODO dohvat liste polazaka iz baze
            for (int i = 0; i < 10; i++) {
                departuresSB.append("Polazak ").append(departureID+1).append(" ").append(" stanica ").append(i+1).append("\n");
            }
            textView.setText(departuresSB.toString());
        }

        return rootView;
    }
}
