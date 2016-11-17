package cz.helmisek.android.androidviper.core.util;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;

import cz.helmisek.android.androidviper.core.presenter.Presenter;


public interface ViewWrapper<VB extends ViewDataBinding, PR extends Presenter>
{
	int getLayoutId();
	Context getContext();
	PR getPresenter();
	VB getBinding();
	Activity getActivity();
}
