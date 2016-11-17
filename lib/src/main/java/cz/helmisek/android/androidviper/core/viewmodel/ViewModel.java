package cz.helmisek.android.androidviper.core.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;

import cz.helmisek.android.androidviper.core.contract.ViewModelDefaultContract;
import cz.helmisek.android.androidviper.core.interactor.Interactor;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;


public abstract class ViewModel<I extends Interactor, VB extends ViewDataBinding> extends BaseObservable implements ViewModelDefaultContract<I>
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


	public Context getContext()
	{
		return mWrapper.getContext();
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
	}
}
