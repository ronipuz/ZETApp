package hr.fer.tel.ruazosa.zet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LinesFragment extends Fragment {

    private FragmentActivity context;

    public LinesFragment() {}

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lines, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.lines_pager);
        viewPager.setAdapter(new CustomPagerAdapter());
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        private String pageTitles[] = new String[] {"Tram Lines", "Bus Lines"};
        private String pageID[] = new String[] {"tramlines", "buslines"};

        public CustomPagerAdapter() {
            super(context.getSupportFragmentManager());
        }

        @Override
        public int getCount() {
            return pageID.length;
        }

        @Override
        public Fragment getItem(int position) {
            return LinesListFragment.newInstance(pageID[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position < pageTitles.length) {
                return pageTitles[position];
            } else {
                return null;
            }
        }
    }
}
