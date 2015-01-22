package hr.fer.tel.ruazosa.util.retrofit;

import java.util.List;

import hr.fer.tel.ruazosa.model.Tram;
import retrofit.RestAdapter;

public class ZETClient {

	private static final String API_URL = "http://zet.hr";
    
    public static void main(String[] args) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new HTMLConverter())
                .build();

        ZETService zet = restAdapter.create(ZETService.class);
        
        List<Tram> trams = zet.trams();
        for (Tram tram : trams) {
            System.out.println(tram.toString());
        }
        

    }

}
