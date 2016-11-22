package cz.helmisek.android.androidvipersample.core.interactor;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;

import cz.helmisek.android.androidvipersample.api.ApiConfig;
import cz.helmisek.android.androidvipersample.api.WeatherAPIEndpoint;
import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import cz.helmisek.android.androidvipersample.core.interactor.base.RetrofitApiInteractor;
import cz.helmisek.android.androidvipersample.core.interactor.datamodel.LocationDataModel;
import cz.helmisek.android.androidvipersample.core.interactor.datamodel.WeatherDataModel;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class WeatherInteractor extends RetrofitApiInteractor implements WeatherDataModel, LocationDataModel
{

	private static final String UNITS_METRIC = "metric";
	private static final String QUERY_PARAM_APP_ID = "appid";


	@Override
	public Retrofit.Builder getRetrofitBuilder()
	{
		final Retrofit.Builder retrofitBuilder = getDefaultRetrofitBuilder();
		final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(new Interceptor()
		{
			@Override
			public okhttp3.Response intercept(final Chain chain) throws IOException
			{
				final Request original = chain.request();
				final HttpUrl httpUrl = original.url().newBuilder()
						.addQueryParameter(QUERY_PARAM_APP_ID, ApiConfig.OPENWEATHER_API_KEY)
						.build();
				final Request request = original.newBuilder().url(httpUrl).build();
				return chain.proceed(request);
			}
		});
		retrofitBuilder.client(httpClient.build());
		return retrofitBuilder;
	}


	@Override
	public String getBaseUrl()
	{
		return ApiConfig.OPENWEATHER_API_BASE;
	}


	@Override
	public Observable<Response<CurrentWeatherEntity>> loadCurrentWeather(String location)
	{
		return getEndpoint(WeatherAPIEndpoint.class)
				.getCurrentWeather(location, UNITS_METRIC)
				.observeOn(AndroidSchedulers.mainThread());
	}


	@Override
	public String getLastLocation()
	{
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
		return mSharedPreferences.getString(PREF_KEY_LAST_WEATHER_LOCATION, DEFAULT_LAST_WEATHER_LOCATION_VALUE);
	}


	@Override
	public void setLastLocation(final String location)
	{
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
		mSharedPreferences.edit().putString(PREF_KEY_LAST_WEATHER_LOCATION, location).apply();
	}


}
