package hr.fer.tel.ruazosa.util.retrofit;

import java.util.List;

import hr.fer.tel.ruazosa.model.Tram;
import hr.fer.tel.ruazosa.util.parser.Detalji;
import hr.fer.tel.ruazosa.util.parser.Raspored;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ZETService {

	@GET("/default.aspx?id=291")
    List<Tram> trams();
    
    @GET("/default.aspx?id=330&")
    Raspored raspored(@Query("route_id") int route_id);
    
    @GET("/default.aspx?id=331&")
    Detalji detalji(@Query("route_id") int route_id,
                    @Query("trip_id") String trip_id,
                    @Query("direction_id") int direction_id);
}
