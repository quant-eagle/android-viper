package cz.helmisek.android.androidviper.core.presenter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import java.io.Serializable;

import cz.helmisek.android.androidviper.core.contract.PresenterContract;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;
import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;


/**
 * Presenter class implementation, which is the most important layer between the View and our structure.
 * Presenter class should handle View interactions and respond to them by defined actions in our layer structure.
 *
 * @param <VM> an instance of {@link ViewModel} dependant
 * @param <VB> an instance of {@link ViewDataBinding} dependant
 */
public abstract class Presenter<VM extends ViewModel, VB extends ViewDataBinding> implements PresenterContract<VM>, Serializable
{
	/**
	 * Bounded {@link ViewModel} descendant instance.
	 * Used to control View states, behavior and data on View interactions.
	 */
	private VM mViewModel;

	/**
	 * ViewWrapper instance to provide all necessary View related objects to all of the descendants.
	 */
	private ViewWrapper<VB, ? extends Presenter> mWrapper;

	/**
	 * Describing whether this attachment went trough its initial attachment or not.
	 */
	private boolean firstAttachment = true;


	/**
	 * Should be called only once when this instance is created and initialized.
	 */
	public void onPresenterCreated()
	{
		this.mViewModel = initViewModel();
		this.mViewModel.bind(mWrapper);
		this.mViewModel.onViewModelCreated();
	}


	/**
	 * Called when presenter is attached to the View.
	 *
	 * @param firstAttachment First attachment of this instance to the View
	 */
	public void onPresenterAttached(boolean firstAttachment)
	{
		this.mViewModel.subscribe(firstAttachment);
	}


	/**
	 * Called when presenter is detached from the View.
	 *
	 * @param wasDestroyed Describes whether this instance of presenter has been totally destroyed or just finished.
	 */
	public void onPresenterDetached(boolean wasDestroyed)
	{
		if(firstAttachment)
		{
			firstAttachment = false;
		}

		this.mViewModel.unsubscribe(wasDestroyed);
	}


	/**
	 * Called when presenter is removed from memory.
	 */
	public void onPresenterRemoved()
	{
	}


	/**
	 * System life cycle impl method - On resume.
	 */
	public void onResume()
	{
	}


	/**
	 * System life cycle impl method - On pause.
	 */
	public void onPause()
	{
	}


	/**
	 * Is first attachment boolean.
	 *
	 * @return the boolean
	 */
	public boolean isFirstAttachment()
	{
		return firstAttachment;
	}


	/**
	 * Gets context.
	 *
	 * @return the context
	 */
	public Context getContext()
	{
		return mWrapper.getContext();
	}


	/**
	 * Gets binding.
	 *
	 * @return the binding
	 */
	public VB getBinding()
	{
		return mWrapper.getBinding();
	}


	@Override
	public VM getViewModel()
	{
		return this.mViewModel;
	}


	/**
	 * Bind already defined and initialized instance of {@link ViewWrapper} to this instance.
	 *
	 * @param viewWrapper NonNull instance of {@link ViewWrapper}
	 */
	public void bind(@NonNull ViewWrapper<VB, ? extends Presenter> viewWrapper)
	{
		this.mWrapper = viewWrapper;

		if(this.mViewModel != null)
			this.mViewModel.bind(viewWrapper);
	}

}
