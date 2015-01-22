package hr.fer.tel.ruazosa.zet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeparturesFragment extends Fragment {

    private static final String LINE_ID = "lineID";

    private FragmentActivity context;

    public DeparturesFragment() {}

    public static DeparturesFragment newInstance(int id) {
        DeparturesFragment fragment = new DeparturesFragment();
        Bundle args = new Bundle();
        args.putInt(LINE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_departures, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.departures_pager);
        viewPager.setAdapter(new CustomPagerAdapter());
    }

    public int  getShownID() {
        int id = 0;
        if (getArguments() != null) {
            id = getArguments().getInt(LINE_ID);
        }
        return id;
    }

    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        private String pageTitles[] = new String[]{"Odlasci", "Dolasci"};
        private String pageID[] = new String[]{"odlazak", "dolazak"};

        public CustomPagerAdapter() {
            super(context.getSupportFragmentManager());
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return pageID.length;
        }

        @Override
        public Fragment getItem(int position) {
            int lineID = 0;
            if (getArguments() != null) {
                lineID = getArguments().getInt(LINE_ID);
            }
            return DeparturesListFragment.newInstance(lineID, pageID[position]);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < pageTitles.length) {
                return pageTitles[position];
            } else {
                return null;
            }
        }
    }
}
