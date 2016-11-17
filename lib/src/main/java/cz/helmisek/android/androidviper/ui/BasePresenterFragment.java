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


//TODO: make working as well in the same way like BasePresenterActivity
public abstract class BasePresenterFragment<P extends Presenter, VB extends ViewDataBinding> extends Fragment implements ViewPresenterDefaultContract<P>
{

	private P mPresenter;
	private View mRootView;


	public abstract int getLayoutId();


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		VB binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
		this.mPresenter = initPresenter();
		this.mRootView = binding.getRoot();
		return this.mRootView;
	}


	@Override
	public void onPause()
	{
		super.onPause();
	}


	@Override
	public void onResume()
	{
		super.onResume();
	}



	public View getRootView()
	{
		return this.mRootView;
	}
}
