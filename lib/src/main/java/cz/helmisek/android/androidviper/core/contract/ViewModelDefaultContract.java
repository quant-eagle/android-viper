package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.interactor.Interactor;


/**
 * Behavior description for {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel}
 * which should always implement it.
 *
 * @param <I> any descendant of {@link Interactor} class to use with
 *           this {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel} instance.
 */
public interface ViewModelDefaultContract<I extends Interactor>
{
	/**
	 * Should be called only once on fresh
	 * {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel} instance creation.
	 */
	void onViewModelCreated();

	/**
	 * Called every time {@link cz.helmisek.android.androidviper.core.presenter.Presenter} is reattached to a window or
	 * when it has been created.
	 *
	 * @param firstAttachment - result of expression (if {@link cz.helmisek.android.androidviper.core.presenter.Presenter}
	 *                           was attached to the window for the first time)
	 * */
	void subscribe(boolean firstAttachment);

	/**
	 * Unsubscribe method is called when {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel}
	 * is being detached, finishing or destroyed. (See @param wasDestroyed)
	 *
	 * @param wasDestroyed - if true, then this
	 *                     instance of {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel} is not going to live anymore.
	 *                     Else it is going to be revived later (on config change etc).
	 */
	void unsubscribe(boolean wasDestroyed);

	/**
	 * Initialization of freshly new {@link Interactor} descendant instance for
	 * this {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel} instance.
	 *
	 * @return new instance of {@link Interactor} descendant.
	 */
	I initInteractor();
}
