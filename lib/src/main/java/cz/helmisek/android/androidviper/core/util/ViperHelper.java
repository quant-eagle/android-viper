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

import java.util.UUID;

import cz.helmisek.android.androidviper.core.contract.ViewPresenterDefaultContract;
import cz.helmisek.android.androidviper.core.presenter.Presenter;


public class ViperHelper<VB extends ViewDataBinding, PR extends Presenter>
{

	private static final String LOG_TAG = ViperHelper.class.getSimpleName();
	private String mPresenterId;
	private boolean mInstanceRemoved;
	private boolean mOnSaveInstanceCalled;
	private boolean mAlreadyCreated;
	private VB mBinding;
	private PR mPresenter;
	private ViperConfig mViperConfig;


	public void onCreate(@NonNull ViewWrapper<VB, PR> view, ViewPresenterDefaultContract<PR> presenterDefaultContract, @Nullable Bundle savedInstanceState)
	{
		// skip if already created
		if(mAlreadyCreated) return;

		// perform Data Binding initialization
		mAlreadyCreated = true;

		if(view instanceof Activity)
			mBinding = DataBindingUtil.setContentView(((Activity) view), view.getLayoutId());
		else if(view instanceof Fragment)
			mBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), view.getLayoutId(), null, false);
		else
			throw new IllegalArgumentException("View must be an instance of Activity or Fragment (support-v4).");


		// obtain unique PresenterId
		if(mPresenterId == null)
		{ // screen (activity/fragment) created for first time, attach unique ID
			if(savedInstanceState == null)
				mPresenterId = UUID.randomUUID().toString();
			else
				mPresenterId = savedInstanceState.getString(getPresenterIdFieldName());
		}

		// get presenter instance for this screen
		mPresenter = (PR) ViperProvider.getInstance().getPresenter(mPresenterId, presenterDefaultContract, mBinding);
		mOnSaveInstanceCalled = false;

		// bind all together
		mPresenter.bind(view);

		// call ViewModel callback
		if(mPresenter.isFirstAttachment())
			mPresenter.onPresenterCreated();

		rebind(view);

		mPresenter.onPresenterAttached(mPresenter.isFirstAttachment());
	}


	private void rebind(@NonNull ViewWrapper<VB, PR> view)
	{
		if(mViperConfig.isStandard())
		{
			if(!mBinding.setVariable(mViperConfig.getPresenterVariableId(), mPresenter))
			{
				throw new IllegalArgumentException("Binding variable wasn't set successfully. Probably viewModelVariableName of your " +
						"ViewModelBindingConfig of " + view.getClass().getSimpleName() + " doesn't match any variable in "
						+ mBinding.getClass().getSimpleName());
			}
			else
			{
				if(mViperConfig.hasViewModel())
					mBinding.setVariable(mViperConfig.getViewModelVariableId(), mPresenter.getViewModel());
			}
		}
		else
		{
			for(ViperConfig.CustomBindingVariable bindingVariable : mViperConfig.getCustomBindingVariablesArray())
			{
				if(!mBinding.setVariable(bindingVariable.getVariableId(), bindingVariable.getObject()))
				{
					throw new IllegalArgumentException("Binding variable "+ bindingVariable.toString() +" you have defined is not present or does not match any variable in your layout");
				}
			}
		}
	}


	/**
	 * Call from {@link Activity#onResume()} or {@link Fragment#onResume()}
	 */
	public void onResume()
	{
		if(mPresenter != null) mPresenter.onResume();
	}


	/**
	 * Call from {@link Activity#onPause()} or {@link Fragment#onPause()}
	 */
	public void onPause()
	{
		if(mPresenter != null) mPresenter.onPause();
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroyView()}. Use in case model is associated
	 * with Fragment
	 *
	 * @param fragment Fragment instance
	 */
	public void onDestroyView(@NonNull Fragment fragment)
	{
		if(mPresenter == null) return;

		if(fragment.getActivity() != null && fragment.getActivity().isFinishing())
		{
			mPresenter.onPresenterDetached(true);
			removePresenter();
		}
		else
		{
			mPresenter.onPresenterDetached(false);
			mAlreadyCreated = false;
		}
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroy()}
	 *
	 * @param fragment
	 */
	public void onDestroy(@NonNull Fragment fragment)
	{
		if(mPresenter == null) return;

		if(fragment.getActivity().isFinishing())
		{
			removePresenter();
		}
		else if(fragment.isRemoving() && !mOnSaveInstanceCalled)
		{
			// The fragment can be still in backstack even if isRemoving() is true.
			// We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
			Log.d(LOG_TAG, "Removing presenter - fragment replaced");
			removePresenter();
		}
		mAlreadyCreated = false;
	}


	/**
	 * Use in case this model is associated with an {@link Activity}
	 * Call from {@link Activity#onDestroy()}
	 *
	 * @param activity
	 */
	public void onDestroy(@NonNull Activity activity)
	{
		if(mPresenter == null) return;

		if(activity.isFinishing())
		{
			mPresenter.onPresenterDetached(true);
			removePresenter();
		}
		else
			mPresenter.onPresenterDetached(false);
		mAlreadyCreated = false;
	}


	/**
	 * Getter for the ViewModel
	 *
	 * @return ViewModel instance
	 */
	@Nullable
	public PR getPresenter()
	{
		return this.mPresenter;
	}


	/**
	 * Call from {@link Activity#onSaveInstanceState(Bundle)}
	 * or {@link Fragment#onSaveInstanceState(Bundle)}.
	 * This allows the model to save its state.
	 *
	 * @param bundle InstanceState bundle
	 */
	public void onSaveInstanceState(@NonNull Bundle bundle)
	{
		bundle.putString(getPresenterIdFieldName(), mPresenterId);
		if(mPresenter != null)
		{
			ViperProvider.getInstance().addPresenter(mPresenterId, mPresenter);
			mOnSaveInstanceCalled = true;
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


	public void setConfig(ViperConfig viperConfig)
	{
		if(this.mViperConfig == null)
			this.mViperConfig = viperConfig;
	}


	/**
	 * This method defines a key under which the ViewModel ID will be stored inside SavedInstanceState of the Activity/Fragment.
	 * <p>
	 * The key should be unique enough to avoid collision with other user-defined keys
	 *
	 * @return key
	 */
	@NonNull
	private String getPresenterIdFieldName()
	{
		return "__internal_presenter_id";
	}


	/**
	 * Remove Presenter instance from memory and cleanup
	 */
	private void removePresenter()
	{
		if(!mInstanceRemoved)
		{
			ViperProvider.getInstance().removePresenter(mPresenterId);
			this.mPresenter.onPresenterDestroyed();
			this.mInstanceRemoved = true;
			this.mAlreadyCreated = false;
		}
	}

}
