package cz.helmisek.android.androidviper.core.util;

public class ViperConfig
{
	private int mPresenterVariableId;
	private int mViewModelVariableId;
	private boolean mStandardMode = true;
	private CustomBindingVariable[] customBindingVariablesArray;


	public ViperConfig(int presenterVariableId)
	{
		this(presenterVariableId, -1);
	}


	public ViperConfig(int presenterVariableId, int viewModelVariableId)
	{
		this.mPresenterVariableId = presenterVariableId;
		this.mViewModelVariableId = viewModelVariableId;
	}


	public ViperConfig(CustomBindingVariable[] customBindingVariablesArray)
	{
		this(-1, -1);
		this.customBindingVariablesArray = customBindingVariablesArray;
		this.mStandardMode = false;
	}


	public int getPresenterVariableId()
	{
		return this.mPresenterVariableId;
	}


	public int getViewModelVariableId()
	{
		return this.mViewModelVariableId;
	}


	public CustomBindingVariable[] getCustomBindingVariablesArray()
	{
		return this.customBindingVariablesArray;
	}


	public boolean hasViewModel()
	{
		return mViewModelVariableId != -1;
	}


	public boolean isStandard()
	{
		return this.mStandardMode;
	}


	public static class CustomBindingVariable
	{
		private int variableId;
		private Object mObject;


		public CustomBindingVariable(Object object, int variableId)
		{
			this.mObject = object;
			this.variableId = variableId;
		}


		public int getVariableId()
		{
			return this.variableId;
		}


		public Object getObject()
		{
			return this.mObject;
		}


		@Override
		public String toString()
		{
			return "CustomBindingVariable{" +
					"variableId=" + variableId +
					", mObject=" + mObject +
					'}';
		}
	}
}
