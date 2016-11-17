package cz.helmisek.android.androidvipersample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.helmisek.android.androidvipersample.BR;
import cz.helmisek.android.androidviper.core.util.ViperConfig;
import cz.helmisek.android.androidviper.ui.BasePresenterActivity;
import cz.helmisek.android.androidvipersample.R;
import cz.helmisek.android.androidvipersample.core.presenter.WeatherActivityPresenter;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;


public class WeatherActivity extends BasePresenterActivity<WeatherActivityPresenter, ActivityWeatherBinding>
{

	@Override
	public int getLayoutId()
	{
		return R.layout.activity_weather;
	}


	@Override
	protected ViperConfig setupViperConfig()
	{
		return new ViperConfig(BR.presenter, BR.viewmodel);
	}


	@Override
	public WeatherActivityPresenter initPresenter()
	{
		return new WeatherActivityPresenter();
	}


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setSupportActionBar(getBinding().toolbar);
	}
}
