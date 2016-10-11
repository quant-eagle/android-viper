package cz.helmisek.android.androidrxvipersample.core.presenter;

import android.content.Context;
import android.util.Log;

import cz.helmisek.android.androidrxviper.core.presenter.Presenter;
import cz.helmisek.android.androidrxvipersample.core.entity.api.CurrentWeatherEntity;
import cz.helmisek.android.androidrxvipersample.core.interactor.WeatherInteractor;
import cz.helmisek.android.androidrxvipersample.core.viewmodel.WeatherActivityViewModel;
import cz.helmisek.android.androidrxvipersample.databinding.ActivityWeatherBinding;
import cz.kinst.jakub.view.StatefulLayout;
import retrofit2.Response;
import rx.functions.Action1;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherActivityPresenter extends Presenter<WeatherActivityViewModel, ActivityWeatherBinding>
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
}
