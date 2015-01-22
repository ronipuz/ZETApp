package hr.fer.tel.ruazosa.zet;

import android.support.v4.app.ListFragment;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import hr.fer.tel.ruazosa.data.DatabaseHelper;

public class OrmLiteListFragment extends ListFragment{
    private DatabaseHelper databaseHelper = null;

    protected DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
