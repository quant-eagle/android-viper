package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;


/**
 * Created by Jirka Helmich on 29.09.16.
 */

public interface PresenterDefaultContract<VM extends ViewModel>
{
	VM initViewModel();

	VM getViewModel();

	void onPause();
	void onResume();

}
