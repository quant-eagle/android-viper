package cz.helmisek.android.androidrxvipersample.activity;

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

}
