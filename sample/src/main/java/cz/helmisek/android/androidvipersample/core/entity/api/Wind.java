package cz.helmisek.android.androidvipersample.core.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Jirka Helmich on 11.10.16.
 */
public class Wind
{

	@SerializedName("speed")
	@Expose
	private Double speed;
	@SerializedName("deg")
	@Expose
	private Double deg;


	/**
	 * @return The speed
	 */
	public Double getSpeed()
	{
		return speed;
	}


	/**
	 * @param speed The speed
	 */
	public void setSpeed(Double speed)
	{
		this.speed = speed;
	}


	/**
	 * @return The deg
	 */
	public Double getDeg()
	{
		return deg;
	}


	/**
	 * @param deg The deg
	 */
	public void setDeg(Double deg)
	{
		this.deg = deg;
	}

}
