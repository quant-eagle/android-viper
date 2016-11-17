package cz.helmisek.android.androidviper.core.interactor;

import android.content.Context;
import android.content.res.Resources;

import cz.helmisek.android.androidviper.core.contract.InteractorContract;


public abstract class Interactor implements InteractorContract
{
	private InteractorContract mInteractorContract;


	public void bind(InteractorContract contract)
	{
		this.mInteractorContract = contract;
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
