package cz.helmisek.android.androidviper.ui;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.util.ViewWrapper;
import cz.helmisek.android.androidviper.core.util.ViperHelper;


public abstract class BasePresenterFragment<P extends Presenter, VB extends ViewDataBinding> extends Fragment implements ViewPresenterContract<P>, ViewWrapper<VB, P>
{
	private final ViperHelper<VB, P> mViperHelper = new ViperHelper<>();


	public abstract int getLayoutId();


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		mViperHelper.onCreate(this, this, savedInstanceState);
		return mViperHelper.getBinding().getRoot();
	}


	@Override
	public void onResume()
	{
		super.onResume();
		mViperHelper.onResume();
	}


	@Override
	public void onPause()
	{
		super.onPause();
		mViperHelper.onPause();
	}


	@Override
	public void onDestroyView()
	{
		mViperHelper.onDestroyView(this);
		super.onDestroyView();
	}


	@Override
	public void onDestroy()
	{
		mViperHelper.onDestroy(this);
		super.onDestroy();
	}


	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		mViperHelper.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}


	@Override
	public Context getContext()
	{
		return getActivity().getApplicationContext();
	}


	@Override
	public Activity getActivityContext()
	{
		return getActivity();
	}


	public P getPresenter()
	{
		return mViperHelper.getPresenter();
	}


	public VB getBinding()
	{
		return mViperHelper.getBinding();
	}

}

