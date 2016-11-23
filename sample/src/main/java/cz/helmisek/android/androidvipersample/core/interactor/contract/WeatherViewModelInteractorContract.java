package cz.helmisek.android.androidvipersample.core.interactor.contract;

import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import rx.Observable;


public interface WeatherViewModelInteractorContract
{
	String PREF_KEY_LAST_WEATHER_LOCATION = "last_weather_location";
	String DEFAULT_LAST_WEATHER_LOCATION_VALUE = "London";

	String getLastLocation();
	void setLastLocation(final String location);

	Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(final String location);
}
