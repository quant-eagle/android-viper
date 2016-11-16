package cz.helmisek.android.androidviper.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterDefaultContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;
import cz.helmisek.android.androidviper.core.provider.PresenterProvider;


public abstract class BasePresenterActivity<P extends Presenter, VB extends ViewDataBinding> extends AppCompatActivity implements ViewPresenterDefaultContract<P, VB>
{

	private P mPresenter;


	public abstract int getLayoutId();


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		VB binding = DataBindingUtil.setContentView(this, getLayoutId());
		if(savedInstanceState == null)
		{
			this.mPresenter = initPresenter(binding);
		}
		else
		{
			this.mPresenter = (P) PresenterProvider.getInstance().getPresenter("JIRKA");
			this.mPresenter.revive(binding, getApplicationContext());
		}
	}


	@Override
	public P getPresenter()
	{
		return mPresenter;
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		getPresenter().onResume();
	}


	@Override
	protected void onPause()
	{
		super.onPause();
		getPresenter().onPause();
	}


	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		PresenterProvider.getInstance().addPresenter("JIRKA", this.mPresenter);
	}


	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
}
