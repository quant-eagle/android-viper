package cz.helmisek.android.androidviper.core.contract;

import android.content.Context;
import android.content.res.Resources;


/**
 * The interface contract for {@link cz.helmisek.android.androidviper.core.interactor.Interactor} which should provide
 * basic needs for every instance of this type and it's descendants.
 */
public interface InteractorContract
{
	/**
	 * Get provided {@link Context} to work with.
	 *
	 * @return Provided {@link Context} instance
	 */
	Context getContext();

	/**
	 * Get provided {@link Resources} to work with.
	 *
	 * @return Provided {@link Resources} instance
	 */
	Resources getResources();
}
