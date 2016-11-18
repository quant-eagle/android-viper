package cz.helmisek.android.androidvipersample.core.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;
import cz.helmisek.android.androidvipersample.core.entity.api.CurrentWeatherEntity;
import cz.helmisek.android.androidvipersample.core.interactor.WeatherInteractor;
import cz.helmisek.android.androidvipersample.databinding.ActivityWeatherBinding;
import cz.kinst.jakub.view.StatefulLayout;
import retrofit2.Response;
import rx.functions.Action1;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherActivityViewModel extends ViewModel<WeatherInteractor, ActivityWeatherBinding>
{

	public final ObservableField<StatefulLayout.State> state = new ObservableField<>(StatefulLayout.State.CONTENT);

	private CurrentWeatherEntity mCurrentWeatherEntity;


	@Override
	public WeatherInteractor initInteractor()
	{
		return new WeatherInteractor();
	}


	@Override
	public void onViewModelCreated()
	{
		super.onViewModelCreated();

		setupWeatherInformation(getInteractor().getLastLocation());
	}


	public void setupWeatherInformation(final String location)
	{
		Log.v("Weather download", "In progress");

		getBinding().weatherLocation.setText(location);

		if(state.get() != StatefulLayout.State.PROGRESS)
			state.set(StatefulLayout.State.PROGRESS);

		getInteractor().loadCurrentWeather(location).subscribe(new Action1<Response<CurrentWeatherEntity>>()
		{
			@Override
			public void call(Response<CurrentWeatherEntity> currentWeatherEntityResponse)
			{
				if(currentWeatherEntityResponse.isSuccessful())
				{
					setCurrentWeatherEntity(currentWeatherEntityResponse.body());
					getInteractor().setLastLocation(location);
					state.set(StatefulLayout.State.CONTENT);
				}
				else
				{
					state.set(StatefulLayout.State.EMPTY);
				}
			}
		});
	}


	/* ---- Binding related ---- */


	@Bindable
	public CurrentWeatherEntity getCurrentWeatherEntity()
	{
		return this.mCurrentWeatherEntity;
	}


	public void setCurrentWeatherEntity(final CurrentWeatherEntity currentWeatherEntity)
	{
		this.mCurrentWeatherEntity = currentWeatherEntity;
		notifyPropertyChanged(BR.currentWeatherEntity);
	}


}
