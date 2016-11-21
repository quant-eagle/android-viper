package cz.helmisek.android.androidvipersample.core.interactor.repository;

import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import rx.Observable;


public interface WeatherRepository
{
	Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(final String location);
}
