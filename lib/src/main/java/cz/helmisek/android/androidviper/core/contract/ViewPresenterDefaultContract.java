package cz.helmisek.android.androidviper.core.contract;

import android.databinding.ViewDataBinding;

import cz.helmisek.android.androidviper.core.presenter.Presenter;


/**
 * Created by Jirka Helmich on 29.09.16.
 */

public interface ViewPresenterDefaultContract<P extends Presenter, VB extends ViewDataBinding>
{
	P getPresenter();
	P initPresenter(VB binding);
}
