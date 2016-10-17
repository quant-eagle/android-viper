package cz.helmisek.android.androidvipersample.core.router;

import android.content.Context;
import android.content.Intent;

import cz.helmisek.android.androidviper.core.router.Router;
import cz.helmisek.android.androidvipersample.activity.WeatherActivity;


/**
 * Created by Jirka Helmich on 11.10.16.
 */

public class WeatherRouter extends Router
{

	public static void startActivity(Context context)
	{
		Intent intent = getActivityIntent(context);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}


	public static Intent getActivityIntent(Context context)
	{
		return new Intent(context, WeatherActivity.class);
	}

}
