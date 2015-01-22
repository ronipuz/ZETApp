package hr.fer.tel.ruazosa.zet;

import android.os.Bundle;

public class ArrivalsListFragment extends OrmLiteListFragment {

    private static final String STOP_ID = "stopID";
    private static final String DIRECTION = "direction";

    public ArrivalsListFragment() {}

    public static ArrivalsListFragment newInstance(int stopID, boolean direction) {
        ArrivalsListFragment fragment = new ArrivalsListFragment();
        Bundle args = new Bundle();
        args.putInt(STOP_ID, stopID);
        args.putBoolean(DIRECTION, direction);
        fragment.setArguments(args);
        return fragment;
    }

    //TODO
}
