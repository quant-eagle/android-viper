package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.presenter.Presenter;


/**
 * Created by Jirka Helmich on 29.09.16.
 */

public interface ViewPresenterDefaultContract<P extends Presenter>
{
	P initPresenter();
}
