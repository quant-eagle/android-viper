package cz.helmisek.android.androidrxviper.core.contract;

import cz.helmisek.android.androidrxviper.core.viewmodel.ViewModel;


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
