package cz.helmisek.android.androidviper.core.interactor;


import android.content.Context;


public abstract class Interactor
{
	private final Context mContext;


	public Interactor(Context context)
	{
		this.mContext = context;
		init();
	}


	public Context getContext()
	{
		return this.mContext;
	}


	public void init()
	{
	}

}
