package cz.helmisek.android.androidviper.core.provider;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;


/**
 * Created by Jirka Helmich on 15.11.16.
 */

public class PresenterProvider
{
	private static PresenterProvider sInstance;

	private final HashMap<String, Presenter<? extends ViewModel, ? extends ViewDataBinding>> mPresentersMap;


	private PresenterProvider()
	{
		this.mPresentersMap = new HashMap<>();
	}


	public static PresenterProvider getInstance()
	{
		if(sInstance == null) sInstance = new PresenterProvider();
		return sInstance;
	}


	public synchronized void addPresenter(@NonNull String presenterId, @NonNull Presenter<? extends ViewModel, ? extends ViewDataBinding> presenter)
	{
		this.mPresentersMap.put(presenterId, presenter);
	}


	public synchronized void removePresenter(@NonNull String presenterId)
	{
		this.mPresentersMap.remove(presenterId);
	}


	@Nullable
	public synchronized <VM extends ViewModel, VB extends ViewDataBinding> Presenter<VM, VB> getPresenter(@NonNull String presenterId)
	{
		Presenter<VM, VB> instance = (Presenter<VM, VB>) this.mPresentersMap.get(presenterId);
		if(instance != null)
		{
			return instance;
		}
		return null;
	}
}