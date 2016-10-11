package cz.helmisek.android.androidrxvipersample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.helmisek.android.androidrxviper.ui.BasePresenterActivity;
import cz.helmisek.android.androidrxvipersample.R;
import cz.helmisek.android.androidrxvipersample.core.presenter.WeatherActivityPresenter;
import cz.helmisek.android.androidrxvipersample.databinding.ActivityWeatherBinding;


public class WeatherActivity extends BasePresenterActivity<WeatherActivityPresenter, ActivityWeatherBinding>
{

	@Override
	public int getLayoutId()
	{
		return R.layout.activity_weather;
	}


	@Override
	public WeatherActivityPresenter initPresenter(ActivityWeatherBinding binding)
	{
		return new WeatherActivityPresenter(getApplicationContext(), binding);
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setSupportActionBar(getPresenter().getBinding().toolbar);
	}
}
