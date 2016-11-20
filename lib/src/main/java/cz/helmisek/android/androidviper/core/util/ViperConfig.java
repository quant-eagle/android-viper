package cz.helmisek.android.androidviper.core.util;

/**
 * The type Viper config.
 */
public class ViperConfig
{
	private int mPresenterVariableId;
	private int mViewModelVariableId;
	private boolean mStandardMode = true;
	private CustomBindingVariable[] customBindingVariablesArray;


	/**
	 * Instantiates a new Viper config.
	 *
	 * @param presenterVariableId the presenter variable id
	 */
	public ViperConfig(int presenterVariableId)
	{
		this(presenterVariableId, -1);
	}


	/**
	 * Instantiates a new Viper config.
	 *
	 * @param presenterVariableId the presenter variable id
	 * @param viewModelVariableId the view model variable id
	 */
	public ViperConfig(int presenterVariableId, int viewModelVariableId)
	{
		this.mPresenterVariableId = presenterVariableId;
		this.mViewModelVariableId = viewModelVariableId;
	}


	/**
	 * Instantiates a new Viper config.
	 *
	 * @param customBindingVariablesArray the custom binding variables array
	 */
	public ViperConfig(CustomBindingVariable[] customBindingVariablesArray)
	{
		this(-1, -1);
		this.customBindingVariablesArray = customBindingVariablesArray;
		this.mStandardMode = false;
	}


	/**
	 * Gets presenter variable id.
	 *
	 * @return the presenter variable id
	 */
	int getPresenterVariableId()
	{
		return this.mPresenterVariableId;
	}


	/**
	 * Gets view model variable id.
	 *
	 * @return the view model variable id
	 */
	int getViewModelVariableId()
	{
		return this.mViewModelVariableId;
	}


	/**
	 * Get custom binding variables array custom binding variable [ ].
	 *
	 * @return the custom binding variable [ ]
	 */
	CustomBindingVariable[] getCustomBindingVariablesArray()
	{
		return this.customBindingVariablesArray;
	}


	/**
	 * Has view model boolean.
	 *
	 * @return the boolean
	 */
	boolean hasViewModel()
	{
		return mViewModelVariableId != -1;
	}


	/**
	 * Is standard boolean.
	 *
	 * @return the boolean
	 */
	boolean isStandard()
	{
		return this.mStandardMode;
	}


	/**
	 * The type Custom binding variable.
	 */
	static class CustomBindingVariable
	{
		private int variableId;
		private Object mObject;


		/**
		 * Instantiates a new Custom binding variable.
		 *
		 * @param object     the object
		 * @param variableId the variable id
		 */
		public CustomBindingVariable(Object object, int variableId)
		{
			this.mObject = object;
			this.variableId = variableId;
		}


		/**
		 * Gets variable id.
		 *
		 * @return the variable id
		 */
		int getVariableId()
		{
			return this.variableId;
		}


		/**
		 * Gets object.
		 *
		 * @return the object
		 */
		Object getObject()
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
