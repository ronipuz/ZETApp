package hr.fer.tel.ruazosa.zet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import hr.fer.tel.ruazosa.data.DatabaseHelper;
import hr.fer.tel.ruazosa.model.Tram;


public class DatabaseTestActivity extends OrmLiteBaseActivity<DatabaseHelper> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        TextView tb1 = (TextView) findViewById(R.id.text1);
        TextView  tb2 = (TextView) findViewById(R.id.text2);

        Tram tram = new Tram(5, "Petica", false);
        Tram tram2 = new Tram(6, "Sestica", false);

        RuntimeExceptionDao<Tram, Integer> tramDao = getHelper().getRuntimeTramDao();
        List<Tram> trams;
        tramDao.create(tram);
        tramDao.create(tram2);

        trams = tramDao.queryForAll();

        tb1.setText(trams.get(0).getIdTram().toString());
        tb2.setText(trams.get(0).getTramNumber().toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_database_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
