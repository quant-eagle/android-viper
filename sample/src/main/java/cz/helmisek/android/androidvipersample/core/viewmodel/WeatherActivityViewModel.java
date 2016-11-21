package cz.helmisek.android.androidvipersample.core.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.android.databinding.library.baseAdapters.BR;

import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;
import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import cz.helmisek.android.androidvipersample.core.interactor.WeatherInteractor;
import cz.helmisek.android.androidvipersample.core.viewinteractor.WeatherPresenterViewModelContract;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;
import cz.kinst.jakub.view.StatefulLayout;
import retrofit2.Response;
import rx.functions.Action1;


public class WeatherActivityViewModel extends ViewModel<WeatherInteractor, ActivityWeatherBinding>
{

	public final ObservableField<StatefulLayout.State> state = new ObservableField<>(StatefulLayout.State.CONTENT);
	private final WeatherPresenterViewModelContract mPresenterViewModelContract;

	private CurrentWeatherEntity mCurrentWeatherEntity;


	public WeatherActivityViewModel(WeatherPresenterViewModelContract contract)
	{
		this.mPresenterViewModelContract = contract;
	}


	@Override
	public WeatherInteractor initInteractor()
	{
		return new WeatherInteractor();
	}


	public void setupWeatherInformation(final String location)
	{
		getInteractor().loadCurrentWeather(location).subscribe(new Action1<Response<CurrentWeatherEntity>>()
		{
			@Override
			public void call(Response<CurrentWeatherEntity> currentWeatherEntityResponse)
			{
				if(currentWeatherEntityResponse.isSuccessful())
				{
					setCurrentWeatherEntity(currentWeatherEntityResponse.body());
					getInteractor().setLastLocation(location);
				}

				WeatherActivityViewModel.this.mPresenterViewModelContract.onWeatherInformationReady(currentWeatherEntityResponse.isSuccessful());
			}
		});
	}


	public String getLastLocation()
	{
		return this.getInteractor().getLastLocation();
	}

	/* ---- Binding related ---- */


	@Bindable
	public CurrentWeatherEntity getCurrentWeatherEntity()
	{
		return this.mCurrentWeatherEntity;
	}


	private void setCurrentWeatherEntity(final CurrentWeatherEntity currentWeatherEntity)
	{
		this.mCurrentWeatherEntity = currentWeatherEntity;
		notifyPropertyChanged(BR.currentWeatherEntity);
	}


}
