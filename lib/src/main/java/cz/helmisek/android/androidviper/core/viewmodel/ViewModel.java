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


/**
 * ViewModel definition described below has got to be used as an ancestor to every ViewModel used in VIPER
 * framework. ViewModel should handle View states and prepare information for the View.
 *
 * @param <I>  Any {@link Interactor} type
 */
public abstract class ViewModel<I extends Interactor> extends BaseObservable implements ViewModelContract<I>, InteractorContract
{

	/**
	 * ViewWrapper instance to provide all necessary View related objects to all of the descendants.
	 */
	private ViewWrapper<?, ? extends Presenter> mWrapper;

	/**
	 * An instance of {@link Interactor} type specific for this ViewModel instance.
	 */
	private I mInteractor;


	/**
	 * Bind an already defined ViewWrapper instance with provided objects to our ViewWrapper instance.
	 *
	 * @param viewWrapper {@link ViewWrapper} initialized instance to provide object to lower levels
	 */
	public void bind(ViewWrapper<?, ? extends Presenter> viewWrapper)
	{
		this.mWrapper = viewWrapper;
	}


	/**
	 * Get our specific {@link Interactor} type instance.
	 *
	 * @return {@link Interactor} type instance
	 */
	public I getInteractor()
	{
		return this.mInteractor;
	}


	@Override
	public void onViewModelAttached(boolean firstAttachment)
	{
	}


	@Override
	public void onViewModelDetached(boolean wasDestroyed)
	{
	}


	@Override
	public void onViewModelRemoved()
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
