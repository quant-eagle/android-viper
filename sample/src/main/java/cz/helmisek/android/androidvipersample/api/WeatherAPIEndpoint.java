package cz.helmisek.android.androidvipersample.api;

import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface WeatherAPIEndpoint
{

	@GET("data/2.5/weather")
	Observable<Response<CurrentWeatherEntity>> getCurrentWeather(@Query("q") String location, @Query("units") String units);

}
