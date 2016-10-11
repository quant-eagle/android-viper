package cz.helmisek.android.androidrxviper.core.interactor;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public abstract class RetrofitApiInteractor extends Interactor
{

	public abstract String getBaseUrl();


	private Retrofit mRetrofit;


	public RetrofitApiInteractor(Context context, Retrofit retrofit)
	{
		super(context);
		this.mRetrofit = retrofit;
	}


	public RetrofitApiInteractor(Context context)
	{
		super(context);
		this.mRetrofit = getDefaultRetrofitBuilder().build();
	}


	private Retrofit.Builder getDefaultRetrofitBuilder()
	{
		return new Retrofit.Builder()
				.client(new OkHttpClient())
				.baseUrl(getBaseUrl())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
				.addConverterFactory(GsonConverterFactory.create());
	}


	protected <T> T getEndpoint(final Class<T> service)
	{
		return this.mRetrofit.create(service);
	}


	public void setRetrofit(Retrofit retrofit)
	{
		this.mRetrofit = retrofit;
	}


	public Retrofit getRetrofit()
	{
		return this.mRetrofit;
	}
}
