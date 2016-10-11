package cz.helmisek.android.androidrxvipersample.core.interactor;

import android.content.Context;

import cz.helmisek.android.androidrxviper.core.interactor.RetrofitApiInteractor;
import cz.helmisek.android.androidrxvipersample.api.ApiConfig;
import cz.helmisek.android.androidrxvipersample.api.WeatherAPIEndpoint;
import cz.helmisek.android.androidrxvipersample.core.entity.api.CurrentWeatherEntity;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherInteractor extends RetrofitApiInteractor
{
	@Override
	public String getBaseUrl()
	{
		return ApiConfig.OPENWEATHER_API_BASE;
	}


	public WeatherInteractor(Context context)
	{
		super(context);
	}


	public Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(String location)
	{
		return getEndpoint(WeatherAPIEndpoint.class)
				.getCurrentWeather(location, ApiConfig.OPENWEATHER_API_KEY)
				.observeOn(AndroidSchedulers.mainThread());
	}


}
