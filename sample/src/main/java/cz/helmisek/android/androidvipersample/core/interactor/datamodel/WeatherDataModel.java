package cz.helmisek.android.androidvipersample.core.interactor.datamodel;

import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import rx.Observable;


public interface WeatherDataModel
{
	Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(final String location);
}
