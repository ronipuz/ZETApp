package hr.fer.tel.ruazosa.util.retrofit;

import java.util.List;

import hr.fer.tel.ruazosa.model.Tram;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ZETService {

	@GET("/default.aspx?id=291")
    List<Tram> trams();
    
    //@GET("/default.aspx?id=330&route_id={id}")
    //List<Schedule> arrivals(@Path("id") int lineId);
}
