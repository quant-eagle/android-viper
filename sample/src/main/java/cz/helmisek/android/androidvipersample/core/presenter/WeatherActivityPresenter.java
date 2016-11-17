package cz.helmisek.android.androidvipersample.core.presenter;

import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidvipersample.core.viewinteractor.WeatherActivityViewInteractor;
import cz.helmisek.android.androidvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherActivityPresenter extends Presenter<WeatherActivityViewModel, ActivityWeatherBinding> implements WeatherActivityViewInteractor
{

	@Override
	public WeatherActivityViewModel initViewModel()
	{
		return new WeatherActivityViewModel();
	}


	@Override
	public void onPresenterCreated()
	{
		super.onPresenterCreated();

		getBinding().setPresenter(this);
		getBinding().setViewmodel(getViewModel());
	}


	@Override
	public void onLocationSubmit()
	{
		getViewModel().setupWeatherInformation(getBinding().weatherLocation.getText().toString());
	}
}
