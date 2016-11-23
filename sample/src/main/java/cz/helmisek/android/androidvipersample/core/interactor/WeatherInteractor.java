package cz.helmisek.android.androidvipersample.core.interactor;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cz.helmisek.android.androidvipersample.api.WeatherAPIEndpoint;
import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import cz.helmisek.android.androidvipersample.core.interactor.contract.WeatherViewModelInteractorContract;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class WeatherInteractor extends RetrofitApiInteractor<WeatherViewModelInteractorContract>
{
	private static final String UNITS_METRIC = "metric";


	@Override
	public WeatherViewModelInteractorContract initDataContract()
	{
		return new WeatherViewModelInteractorContract()
		{
			@Override
			public String getLastLocation()
			{
				SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
				return mSharedPreferences.getString(PREF_KEY_LAST_WEATHER_LOCATION, DEFAULT_LAST_WEATHER_LOCATION_VALUE);
			}


			@Override
			public void setLastLocation(String location)
			{
				SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
				mSharedPreferences.edit().putString(PREF_KEY_LAST_WEATHER_LOCATION, location).apply();
			}


			@Override
			public Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(String location)
			{
				return getRetrofit().create(WeatherAPIEndpoint.class)
						.getCurrentWeather(location, UNITS_METRIC)
						.observeOn(AndroidSchedulers.mainThread());
			}
		};
	}
}
