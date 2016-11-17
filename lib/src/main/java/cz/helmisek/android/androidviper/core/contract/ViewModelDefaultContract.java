package cz.helmisek.android.androidviper.core.contract;

import cz.helmisek.android.androidviper.core.interactor.Interactor;


/**
 * Created by Jirka Helmich on 29.09.16.
 */

public interface ViewModelDefaultContract<I extends Interactor>
{
	void onViewModelCreated();
	void subscribe(boolean firstAttachment);
	void unsubscribe(boolean wasDestroyed);

	I initInteractor();
}
