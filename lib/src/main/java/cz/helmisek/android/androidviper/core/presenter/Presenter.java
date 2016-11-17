package cz.helmisek.android.androidviper.core.presenter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import java.io.Serializable;

import cz.helmisek.android.androidviper.core.contract.PresenterContract;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;
import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;


public abstract class Presenter<VM extends ViewModel, VB extends ViewDataBinding> implements PresenterContract<VM>, Serializable
{
	private VM mViewModel;
	private ViewWrapper<VB, ? extends Presenter> mWrapper;
	private boolean firstAttachment = true;


	public void onPresenterCreated()
	{
		this.mViewModel = initViewModel();
		this.mViewModel.bind(mWrapper);
		this.mViewModel.onViewModelCreated();
	}


	public void onPresenterAttached(boolean firstAttachment)
	{
		this.mViewModel.subscribe(firstAttachment);
	}


	public void onPresenterDetached(boolean wasDestroyed)
	{
		this.mViewModel.unsubscribe(wasDestroyed);
	}


	public void onPresenterDestroyed()
	{
	}


	@Override
	public void onResume()
	{
		this.mViewModel.subscribe(this.firstAttachment);
	}


	@Override
	public void onPause()
	{
		unsubscribe();
	}


	private void unsubscribe()
	{
		if(firstAttachment)
		{
			firstAttachment = false;
		}
		this.mViewModel.unsubscribe(false);
	}


	public boolean isFirstAttachment()
	{
		return firstAttachment;
	}


	public Context getContext()
	{
		return mWrapper.getContext();
	}


	public VB getBinding()
	{
		return mWrapper.getBinding();
	}


	@Override
	public VM getViewModel()
	{
		return this.mViewModel;
	}


	public void bind(ViewWrapper<VB, ? extends Presenter> viewWrapper)
	{
		this.mWrapper = viewWrapper;

		if(this.mViewModel != null)
			this.mViewModel.bind(viewWrapper);
	}

}
