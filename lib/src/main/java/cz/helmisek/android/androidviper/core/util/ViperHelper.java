package cz.helmisek.android.androidviper.core.util;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.UUID;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;


/**
 * ViperHelper utility class should handle all the connections in the VIPER framework
 * utilize the background work for all the views and decrease boilerplate code amount in them.
 *
 * @param <VB> the type parameter
 * @param <PR> the type parameter
 */
public class ViperHelper<VB extends ViewDataBinding, PR extends Presenter>
{

	/**
	 * Constant TAG for Logcat logging
	 */
	private static final String LOG_TAG = ViperHelper.class.getSimpleName();
	/**
	 * Internal presenter ID for all operations tied to specific type of {@link Presenter}
	 */
	private String mPresenterId;
	/**
	 * View specific type of {@link ViewDataBinding}
	 */
	private VB mBinding;
	/**
	 * View specific type of {@link Presenter}
	 */
	private PR mPresenter;
	/**
	 * An instance of {@link ViperConfig} to control DataBinding variables tied to layouts.
	 */
	private ViperConfig mViperConfig;

	/* ViperHelper specific variables for flow controlling */
	private boolean mInstanceRemoved;
	private boolean mOnSaveInstanceCalled;
	private boolean mAlreadyCreated;


	/**
	 * OnCreate implementation for both {@link Fragment} and {@link Activity}.
	 * Should be called in {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)} or
	 * {@link Activity#onCreate(Bundle)} to prepare the {@link Presenter} and provide all the necessary object through
	 * implementations.
	 *
	 * @param view                     {@link ViewWrapper} initialized instance to provide object to lower levels
	 * @param presenterContract 	   {@link ViewPresenterContract} initialized contract to allow us init specific
	 *                                                                 presenter through abstraction
	 * @param savedInstanceState       Saved instance state provided by {@link Fragment} or {@link Activity}
	 */
	public void onCreate(@NonNull final ViewWrapper<VB, PR> view, ViewPresenterContract<PR> presenterContract, @Nullable Bundle savedInstanceState)
	{
		// skip if already created
		if(this.mAlreadyCreated) return;
		initializeView(view);
		obtainPresenterId(savedInstanceState);
		setupPresenter(view, presenterContract);
		rebind(view);
		this.mPresenter.onPresenterAttached(this.mPresenter.isFirstAttachment());
	}


	/**
	 * Setup presenter for the specific View.
	 *
	 * @param view {@link ViewWrapper} initialized instance to provide object to lower levels
	 * @param presenterDefaultContract {@link ViewPresenterContract} initialized contract to allow us init specific
	 *                                                                 presenter through abstraction
	 */
	private void setupPresenter(@NonNull ViewWrapper<VB, PR> view, ViewPresenterContract<PR> presenterDefaultContract)
	{
		// get presenter instance for this screen
		this.mPresenter = (PR) ViperProvider.getInstance().getPresenter(this.mPresenterId, presenterDefaultContract);
		this.mOnSaveInstanceCalled = false;

		bindPresenter(view);
	}


	/**
	 * Bind {@link ViewWrapper} to {@link Presenter} to ensure we have our View implementation provided in Presenter.
	 *
	 * @param view {@link ViewWrapper} initialized instance to provide object to lower levels
	 */
	private void bindPresenter(@NonNull ViewWrapper<VB, PR> view)
	{
		// bind all together
		this.mPresenter.bind(view);

		// call Presenter callback
		if(this.mPresenter.isFirstAttachment())
			this.mPresenter.onPresenterCreated();
	}


	/**
	 * Obtain PresenterId based on our @param savedInstanceState.
	 * If null we, therefore we dont have any Presenter saved and we create a new special ID for currently used
	 * Presenter, else we obtain the PresenterId from the Bundle and assign it to our {@link ViperHelper#mPresenterId}
	 * for later use.
	 *
	 * @param savedInstanceState Saved instance state provided by {@link Fragment} or {@link Activity}
	 */
	private void obtainPresenterId(final @Nullable Bundle savedInstanceState)
	{
		// obtain unique PresenterId
		if(this.mPresenterId == null)
		{ // screen (activity/fragment) created for first time, attach unique ID
			if(savedInstanceState == null)
				this.mPresenterId = UUID.randomUUID().toString();
			else
				this.mPresenterId = savedInstanceState.getString(getPresenterIdFieldName());
		}
	}


	/**
	 * Initialize our implementor view using DataBinding.
	 *
	 *  @param view {@link ViewWrapper} initialized instance to provide object to lower levels
	 */
	private void initializeView(final @NonNull ViewWrapper<VB, PR> view)
	{
		// perform Data Binding initialization
		this.mAlreadyCreated = true;

		if(view instanceof Activity)
			this.mBinding = DataBindingUtil.setContentView(((Activity) view), view.getLayoutId());
		else if(view instanceof Fragment)
			this.mBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), view.getLayoutId(), null, false);
		else
			throw new IllegalArgumentException("View must be an instance of Activity or Fragment (support-v4).");
	}


	/**
	 * Rebind objects to their respective DataBinding variables such as Presenter or ViewModel.
	 *
	 * We do recognize 2 types right now. Standard and Non-Standard modes. {@see ViperConfig}
	 *
	 * @param view {@link ViewWrapper} initialized instance to provide object to lower levels
	 */
	private void rebind(@NonNull ViewWrapper<VB, PR> view)
	{
		if(this.mViperConfig.isStandard())
		{
			rebindStandard(view);
		}
		else
		{
			rebindNonStandard();
		}
	}


	/**
	 * Rebind objects to DataBinding variables as defined in ViperConfig with use of CustomBindingVariable array.
	 */
	private void rebindNonStandard()
	{
		for(ViperConfig.CustomBindingVariable bindingVariable : this.mViperConfig.getCustomBindingVariablesArray())
		{
			if(!this.mBinding.setVariable(bindingVariable.getVariableId(), bindingVariable.getObject()))
			{
				throw new IllegalArgumentException("Binding variable " + bindingVariable.toString() + " you have defined is not present or does not match any variable in your layout");
			}
		}
	}


	/**
	 * Rebind objects do DataBinding variables in standard way, therefore assigning {@link Presenter} instance
	 * and if present then {@link cz.helmisek.android.androidviper.core.viewmodel.ViewModel} instance as well.
	 *
	 * All of that is done with help of {@link ViperConfig}.
	 *
	 * @param view {@link ViewWrapper} initialized instance to provide object to lower levels
	 */
	private void rebindStandard(@NonNull ViewWrapper<VB, PR> view)
	{
		if(!this.mBinding.setVariable(this.mViperConfig.getPresenterVariableId(), this.mPresenter))
		{
			throw new IllegalArgumentException("Binding variable wasn't set successfully. Probably presenterVariableName of your " +
					"ViperConfig of " + view.getClass().getSimpleName() + " doesn't match any variable in "
					+ this.mBinding.getClass().getSimpleName());
		}
		else
		{
			if(this.mViperConfig.hasViewModel())
				this.mBinding.setVariable(this.mViperConfig.getViewModelVariableId(), this.mPresenter.getViewModel());
		}
	}


	/**
	 * Call from {@link Activity#onResume()} or {@link Fragment#onResume()}
	 */
	public void onResume()
	{
		if(this.mPresenter != null) this.mPresenter.onResume();
	}


	/**
	 * Call from {@link Activity#onPause()} or {@link Fragment#onPause()}
	 */
	public void onPause()
	{
		if(this.mPresenter != null) this.mPresenter.onPause();
	}


	/**
	 * Use in case this Presenter is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroyView()}.
	 * Use in case Presenter is associated with Fragment.
	 *
	 * @param fragment {@link Fragment} instance
	 */
	public void onDestroyView(@NonNull Fragment fragment)
	{
		if(this.mPresenter == null) return;

		if(fragment.getActivity() != null && fragment.getActivity().isFinishing())
		{
			this.mPresenter.onPresenterDetached(true);
			removePresenter();
		}
		else
		{
			this.mPresenter.onPresenterDetached(false);
			this.mAlreadyCreated = false;
		}
	}


	/**
	 * Use in case this Presenter is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroy()}
	 *
	 * @param fragment Any instance of type {@link Fragment}
	 */
	public void onDestroy(@NonNull Fragment fragment)
	{
		if(this.mPresenter == null) return;

		if(fragment.getActivity().isFinishing())
		{
			removePresenter();
		}
		else if(fragment.isRemoving() && !this.mOnSaveInstanceCalled)
		{
			// The fragment can be still in backstack even if isRemoving() is true.
			// We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
			Log.d(LOG_TAG, "Removing presenter - fragment replaced");
			removePresenter();
		}
		this.mAlreadyCreated = false;
	}


	/**
	 * Use in case this Presenter is associated with an {@link Activity}
	 * Call from {@link Activity#onDestroy()}
	 *
	 * @param activity the activity
	 */
	public void onDestroy(@NonNull Activity activity)
	{
		if(this.mPresenter == null) return;

		if(activity.isFinishing())
		{
			this.mPresenter.onPresenterDetached(true);
			removePresenter();
		}
		else
			this.mPresenter.onPresenterDetached(false);
		this.mAlreadyCreated = false;
	}


	/**
	 * Getter for the Presenter
	 *
	 * @return {@link Presenter} instance
	 */
	@Nullable
	public PR getPresenter()
	{
		return this.mPresenter;
	}


	/**
	 * Call from {@link Activity#onSaveInstanceState(Bundle)}
	 * or {@link Fragment#onSaveInstanceState(Bundle)}.
	 * This allows the presenter to save its state.
	 *
	 * @param bundle InstanceState bundle
	 */
	public void onSaveInstanceState(@NonNull Bundle bundle)
	{
		bundle.putString(getPresenterIdFieldName(), this.mPresenterId);
		if(this.mPresenter != null)
		{
			ViperProvider.getInstance().addPresenter(this.mPresenterId, this.mPresenter);
			this.mOnSaveInstanceCalled = true;
		}
	}


	/**
	 * Getter for the Data Binding instance
	 *
	 * @return Data Binding instance
	 */
	public VB getBinding()
	{
		return this.mBinding;
	}


	/**
	 * Sets config.
	 *
	 * @param viperConfig the viper config
	 */
	public void setConfig(ViperConfig viperConfig)
	{
		if(this.mViperConfig == null)
			this.mViperConfig = viperConfig;
	}


	/**
	 * This method defines a key under which the Presenter ID will be stored inside SavedInstanceState of the Activity/Fragment.
	 * <p>
	 * The key should be unique enough to avoid collision with other user-defined keys
	 *
	 * @return key
	 */
	@NonNull
	private String getPresenterIdFieldName()
	{
		return "__internal_presenter_id_" + this.mPresenterId;
	}


	/**
	 * Remove Presenter instance from memory and cleanup
	 */
	private void removePresenter()
	{
		if(!this.mInstanceRemoved)
		{
			ViperProvider.getInstance().removePresenter(mPresenterId);
			this.mPresenter.onPresenterRemoved();
			this.mInstanceRemoved = true;
			this.mAlreadyCreated = false;
		}
	}

}
