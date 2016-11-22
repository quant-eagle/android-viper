package cz.helmisek.android.androidvipersample.core.presenter;

import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidvipersample.R;
import cz.helmisek.android.androidvipersample.core.viewinteractor.WeatherActivityViewInteractor;
import cz.helmisek.android.androidvipersample.core.viewinteractor.WeatherPresenterViewModelContract;
import cz.helmisek.android.androidvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;


public class WeatherActivityPresenter extends Presenter<WeatherActivityViewModel, ActivityWeatherBinding> implements WeatherActivityViewInteractor, WeatherPresenterViewModelContract
{

	@Override
	public WeatherActivityViewModel initViewModel()
	{
		return new WeatherActivityViewModel(this);
	}


	@Override
	public void onPresenterCreated()
	{
		super.onPresenterCreated();

		getBinding().weatherLocation.setText(getViewModel().getLastLocation());

		refreshWeatherData();
	}


	@Override
	public void onLocationSubmit()
	{
		refreshWeatherData();
	}


	@Override
	public void onWeatherInformationReady(boolean successfully)
	{
		getBinding().requestState.setText(successfully ?
				getContext().getString(R.string.success) : getContext().getString(R.string.errr));
	}


	private void refreshWeatherData()
	{
		getViewModel().setupWeatherInformation(getBinding().weatherLocation.getText().toString());
	}

}
