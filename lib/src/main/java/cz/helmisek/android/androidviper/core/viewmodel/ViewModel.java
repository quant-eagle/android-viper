package cz.helmisek.android.androidviper.core.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;

import cz.helmisek.android.androidviper.core.contract.ViewModelDefaultContract;
import cz.helmisek.android.androidviper.core.interactor.Interactor;


public abstract class ViewModel<I extends Interactor, VB extends ViewDataBinding> extends BaseObservable implements ViewModelDefaultContract<I>
{

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private VB mBinding;
	private I mInteractor;


	public ViewModel(Context context)
	{
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.mInteractor = initInteractor(context);
	}


	public void setBinding(VB binding)
	{
		this.mBinding = binding;
	}


	public VB getBinding()
	{
		if(this.mBinding == null) throw new RuntimeException("Binding is not assigned");
		return this.mBinding;
	}


	public I getInteractor()
	{
		return this.mInteractor;
	}


	public Context getContext()
	{
		return mContext;
	}


	public LayoutInflater getLayoutInflater()
	{
		return mLayoutInflater;
	}


	@Override
	public void subscribe()
	{
	}


	@Override
	public void unsubscribe()
	{
	}
}
