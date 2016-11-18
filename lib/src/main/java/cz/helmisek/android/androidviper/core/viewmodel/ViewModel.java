package cz.helmisek.android.androidviper.core.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import cz.helmisek.android.androidviper.core.contract.InteractorContract;
import cz.helmisek.android.androidviper.core.contract.ViewModelContract;
import cz.helmisek.android.androidviper.core.interactor.Interactor;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;


public abstract class ViewModel<I extends Interactor, VB extends ViewDataBinding> extends BaseObservable implements ViewModelContract<I>, InteractorContract
{

	private ViewWrapper<VB, ? extends Presenter> mWrapper;
	private I mInteractor;


	public void bind(ViewWrapper<VB, ? extends Presenter> viewWrapper)
	{
		this.mWrapper = viewWrapper;
	}


	public VB getBinding()
	{
		return mWrapper.getBinding();
	}


	public I getInteractor()
	{
		return this.mInteractor;
	}


	@Override
	public void subscribe(boolean firstAttachment)
	{
	}


	@Override
	public void unsubscribe(boolean wasDestroyed)
	{
	}


	@Override
	public void onViewModelCreated()
	{
		this.mInteractor = initInteractor();
		this.mInteractor.bind(this);
		this.mInteractor.init();
	}


	@Override
	public Resources getResources()
	{
		return mWrapper.getContext().getResources();
	}


	@Override
	public Context getContext()
	{
		return mWrapper.getContext();
	}
}
