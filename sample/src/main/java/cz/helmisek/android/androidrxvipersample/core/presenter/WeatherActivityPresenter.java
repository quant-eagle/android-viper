package cz.helmisek.android.androidrxvipersample.core.presenter;

import android.content.Context;

import cz.helmisek.android.androidrxviper.core.presenter.Presenter;
import cz.helmisek.android.androidrxvipersample.core.behavior.WeatherActivityUI;
import cz.helmisek.android.androidrxvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidrxvipersample.databinding.ActivityWeatherBinding;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherActivityPresenter extends Presenter<WeatherActivityViewModel, ActivityWeatherBinding> implements WeatherActivityUI
{

	public WeatherActivityPresenter(Context context, ActivityWeatherBinding binding)
	{
		super(context, binding);
	}


	@Override
	public WeatherActivityViewModel initViewModel()
	{
		return new WeatherActivityViewModel(getContext());
	}


	@Override
	public void init()
	{
		super.init();

		getBinding().setPresenter(this);
		getBinding().setViewmodel(getViewModel());
	}


	@Override
	public void onLocationSubmit()
	{
		getViewModel().setupWeatherInformation(getBinding().weatherLocation.getText().toString());
	}
}
