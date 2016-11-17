package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.presenter.Presenter;


/**
 * The interface View presenter default contract.
 *
 * @param <P> any descendant of {@link Presenter} to use.
 */
public interface ViewPresenterContract<P extends Presenter>
{
	/**
	 * Initialize new instance of {@link Presenter} to use with any view, which should use {@link Presenter}.
	 *
	 * @return freshly initialized instance of {@link Presenter}
	 */
	P initPresenter();
}
