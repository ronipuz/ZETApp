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

public class ArrivalsFragment extends Fragment {

    private static final String STOP_ID = "stopID";

    FragmentActivity context;

    public ArrivalsFragment() {}

    public static ArrivalsFragment newInstance(int id) {
        ArrivalsFragment fragment = new ArrivalsFragment();
        Bundle args = new Bundle();
        args.putInt(STOP_ID, id);
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
        return inflater.inflate(R.layout.fragment_arrivals, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.arrivals_pager);
        viewPager.setAdapter(new CustomPagerAdapter());
    }

    public int  getShownID() {
        int id = 0;
        if (getArguments() != null) {
            id = getArguments().getInt(STOP_ID);
        }
        return id;
    }

    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        private String pageTitles[] = new String[]{"1.Smjer", "2.Smjer"};

        public CustomPagerAdapter() {
            super(context.getSupportFragmentManager());
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return pageTitles.length;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            int stopID = 0;
            if (getArguments() != null) {
                stopID = getArguments().getInt(STOP_ID);
            }
            if (position == 0) {
                return ArrivalsListFragment.newInstance(stopID, false);
            } else {
                return ArrivalsListFragment.newInstance(stopID, true);
            }
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
