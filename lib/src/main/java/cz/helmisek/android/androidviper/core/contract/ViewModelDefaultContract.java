package cz.helmisek.android.androidviper.core.contract;

import android.content.Context;

import cz.helmisek.android.androidviper.core.interactor.Interactor;


/**
 * Created by Jirka Helmich on 29.09.16.
 */

public interface ViewModelDefaultContract<I extends Interactor>
{
	void subscribe();
	void unsubscribe();

	I initInteractor(Context context);
}
