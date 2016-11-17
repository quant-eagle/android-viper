package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.viewmodel.ViewModel;

/**
 * Default behavior description for every {@link cz.helmisek.android.androidviper.core.presenter.Presenter}.
 *
 * @param <VM> Any descendant of {@link ViewModel} to use with this instance of {@link cz.helmisek.android.androidviper.core.presenter.Presenter}
 * */
public interface PresenterContract<VM extends ViewModel>
{

	/**
	 * Initialize an instace of ViewModel and return it
	 *
	 * @return VM - freshly created instance of {@link ViewModel}
	 */
	VM initViewModel();

	/**
	 * Return an instance of specific ViewModel
	 *
	 * @return VM - instance of {@link ViewModel}
	 */
	VM getViewModel();

	/**
	 * OnPause impl for Presenter - should be called in a lifecycle of presenter held by a view
	 */
	void onPause();

	/**
	 * OnResume impl for Presenter - should be called in a lifecycle of presenter held by a view
	 */
	void onResume();

}
