package cz.helmisek.android.androidviper.core.util;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;

import cz.helmisek.android.androidviper.core.presenter.Presenter;


/**
 * The interface describing View holding our {@link Presenter} ({@link android.support.v4.app.Fragment} or {@link Activity}.
 * Methods described here are propagated to our {@link Presenter} instance to be accessed there.
 *
 * @param <VB> any {@link ViewDataBinding} type
 * @param <PR> any type of {@link Presenter}
 */
public interface ViewWrapper<VB extends ViewDataBinding, PR extends Presenter>
{
	/**
	 * Get layout id for layout inflation to the View.
	 *
	 * @return LayoutId from R.java
	 */
	int getLayoutId();

	/**
	 * Gets context.
	 *
	 * @return provided instance of {@link Context}
	 */
	Context getContext();

	/**
	 * Get specific instance of any {@link Presenter} descendant.
	 *
	 * @return Defined instance of {@link Presenter}
	 */
	PR getPresenter();

	/**
	 * Get provided descendant of {@link ViewDataBinding} instance.
	 *
	 * @return Provided instance of {@link ViewDataBinding} instance
	 */
	VB getBinding();

	/**
	 * Get an instance of {@link Activity} holding our {@link Presenter} instance.
	 *
	 * @return Provided instance of {@link Activity}
	 */
	Activity getActivityContext();
}
