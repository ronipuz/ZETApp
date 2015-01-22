package hr.fer.tel.ruazosa.zet;

import android.app.Fragment;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import hr.fer.tel.ruazosa.data.DatabaseHelper;

public class OrmLiteAppFragment extends Fragment{
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
