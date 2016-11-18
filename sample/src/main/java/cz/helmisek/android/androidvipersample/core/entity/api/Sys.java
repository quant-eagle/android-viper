package cz.helmisek.android.androidvipersample.core.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sys
{

	@SerializedName("message")
	@Expose
	private Double message;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("sunrise")
	@Expose
	private Integer sunrise;
	@SerializedName("sunset")
	@Expose
	private Integer sunset;


	/**
	 * @return The message
	 */
	public Double getMessage()
	{
		return message;
	}


	/**
	 * @param message The message
	 */
	public void setMessage(Double message)
	{
		this.message = message;
	}


	/**
	 * @return The country
	 */
	public String getCountry()
	{
		return country;
	}


	/**
	 * @param country The country
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}


	/**
	 * @return The sunrise
	 */
	public Integer getSunrise()
	{
		return sunrise;
	}


	/**
	 * @param sunrise The sunrise
	 */
	public void setSunrise(Integer sunrise)
	{
		this.sunrise = sunrise;
	}


	/**
	 * @return The sunset
	 */
	public Integer getSunset()
	{
		return sunset;
	}


	/**
	 * @param sunset The sunset
	 */
	public void setSunset(Integer sunset)
	{
		this.sunset = sunset;
	}

}