package cz.helmisek.android.androidrxvipersample.api;

import cz.helmisek.android.androidrxvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public interface WeatherAPIEndpoint
{

	@GET("data/2.5/weather")
	Observable<Response<CurrentWeatherEntity>> getCurrentWeather(@Query("q") String location, @Query("units") String units);

}
