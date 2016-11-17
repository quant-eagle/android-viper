package cz.helmisek.android.androidviper.ui;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterDefaultContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;
import cz.helmisek.android.androidviper.core.util.ViperConfig;
import cz.helmisek.android.androidviper.core.util.ViperHelper;


public abstract class BasePresenterActivity<P extends Presenter, VB extends ViewDataBinding> extends AppCompatActivity
		implements ViewPresenterDefaultContract<P>, ViewWrapper<VB, P>
{

	private final ViperHelper<VB, P> mViperHelper = new ViperHelper<>();


	protected abstract ViperConfig setupViperConfig();


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.mViperHelper.setConfig(setupViperConfig());
		this.mViperHelper.onCreate(this, this, savedInstanceState);
	}


	@Override
	public P getPresenter()
	{
		return this.mViperHelper.getPresenter();
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		mViperHelper.onResume();
	}


	@Override
	protected void onPause()
	{
		super.onPause();
		mViperHelper.onPause();
	}


	@Override
	public Context getContext()
	{
		return getActivity();
	}


	@Override
	public Activity getActivity()
	{
		return this;
	}


	@Override
	public VB getBinding()
	{
		return this.mViperHelper.getBinding();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		this.mViperHelper.onSaveInstanceState(outState);
	}


	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mViperHelper.onDestroy(this);
	}
}
