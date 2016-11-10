package cz.helmisek.android.androidviper.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterDefaultContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;


public abstract class BasePresenterFragment<P extends Presenter, VB extends ViewDataBinding> extends Fragment implements ViewPresenterDefaultContract<P, VB>
{

	private P mPresenter;
	private View mRootView;


	public abstract int getLayoutId();


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		VB binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
		this.mPresenter = initPresenter(binding);
		this.mRootView = binding.getRoot();
		return this.mRootView;
	}


	@Override
	public void onPause()
	{
		super.onPause();
		getPresenter().onPause();
	}


	@Override
	public void onResume()
	{
		super.onResume();
		getPresenter().onResume();
	}


	@Override
	public P getPresenter()
	{
		return this.mPresenter;
	}


	public View getRootView()
	{
		return this.mRootView;
	}
}
