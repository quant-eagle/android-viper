package cz.helmisek.android.androidvipersample.core.interactor.base;

import cz.helmisek.android.androidviper.core.interactor.Interactor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


public abstract class RetrofitApiInteractor extends Interactor
{

	public abstract Retrofit.Builder getRetrofitBuilder();
	public abstract String getBaseUrl();


	private Retrofit mRetrofit;


	public RetrofitApiInteractor(Retrofit retrofit)
	{
		this.mRetrofit = retrofit;
	}


	public RetrofitApiInteractor()
	{
		this.mRetrofit = getRetrofitBuilder().build();
	}


	public Retrofit.Builder getDefaultRetrofitBuilder()
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
