package cz.helmisek.android.androidvipersample.core.presenter;

import android.content.Context;

import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidvipersample.core.behavior.WeatherActivityViewInteractor;
import cz.helmisek.android.androidvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherActivityPresenterHandler extends Presenter<WeatherActivityViewModel, ActivityWeatherBinding> implements WeatherActivityViewInteractor
{

	public WeatherActivityPresenterHandler(Context context, ActivityWeatherBinding binding)
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
