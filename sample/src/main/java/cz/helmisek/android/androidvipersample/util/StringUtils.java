package cz.helmisek.android.androidvipersample.util;

import android.support.annotation.NonNull;

import java.util.Locale;


public class StringUtils
{

	public static String parseTemperature(@NonNull final Double value)
	{
		return String.format(Locale.getDefault(), "%.1f", value).replace(",", ".").concat("°C");
	}
}
