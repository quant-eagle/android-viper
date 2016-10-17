package cz.helmisek.android.androidvipersample.core.presenter;

import android.content.Context;

import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidvipersample.core.behavior.WeatherActivityUI;
import cz.helmisek.android.androidvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;


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
