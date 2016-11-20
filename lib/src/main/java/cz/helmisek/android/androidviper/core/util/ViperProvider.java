package cz.helmisek.android.androidviper.core.util;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;


class ViperProvider
{
	private static ViperProvider sInstance;

	private final HashMap<String, Presenter<? extends ViewModel, ? extends ViewDataBinding>> mPresentersMap;


	private ViperProvider()
	{
		this.mPresentersMap = new HashMap<>();
	}


	static ViperProvider getInstance()
	{
		if(sInstance == null) sInstance = new ViperProvider();
		return sInstance;
	}


	synchronized void addPresenter(@NonNull String presenterId, @NonNull Presenter<? extends ViewModel, ? extends ViewDataBinding> presenter)
	{
		this.mPresentersMap.put(presenterId, presenter);
	}


	synchronized void removePresenter(@NonNull String presenterId)
	{
		this.mPresentersMap.remove(presenterId);
	}


	@Nullable
	synchronized <VM extends ViewModel, VB extends ViewDataBinding> Presenter<VM, VB> getPresenter(@NonNull String presenterId, ViewPresenterContract contract)
	{
		Presenter<VM, VB> instance = (Presenter<VM, VB>) this.mPresentersMap.get(presenterId);
		if(instance != null)
		{
			return instance;
		}
		return contract.initPresenter();
	}
}