package cz.helmisek.android.androidviper.core.interactor;

import android.content.Context;
import android.content.res.Resources;

import cz.helmisek.android.androidviper.core.contract.InteractorContract;


/**
 * Base interactor implementation. Implements contract to use provided {@link Context} and {@link Resources}.
 * Interactor class dependants should only take care of raw data gathering and preparation for specific bounded
 * instance of {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel}.
 */
public abstract class Interactor<IDC> implements InteractorContract
{
	/**
	 * Contract which provides all descendants with all
	 * the basic needs such as {@link Context} or {@link Resources}
	 * to work with system to gather data
	 * without any further necessary implementation out of the box.
	 */
	private InteractorContract mInteractorContract;


	public abstract IDC initDataContract();


	private IDC mInteractorDataContract;


	/**
	 * Bind an already implemented contract.
	 *
	 * @param contract An already implemented contract.
	 */
	public void bind(final InteractorContract contract)
	{
		this.mInteractorContract = contract;
	}


	/**
	 * Get an already implemented data contract.
	 */
	public IDC getInteractorDataContract()
	{
		return this.mInteractorDataContract;
	}


	/**
	 * Initialization method, being run right after {@link #bind(InteractorContract)}.
	 * If you need to set some more additional things to your Interactor instance you should do it in here.
	 * At this point unlike by using constructor, you have access to {@link InteractorContract} methods.
	 */
	public void init()
	{
		this.mInteractorDataContract = initDataContract();
	}


	@Override
	public Context getContext()
	{
		return this.mInteractorContract.getContext();
	}


	@Override
	public Resources getResources()
	{
		return this.mInteractorContract.getResources();
	}
}
