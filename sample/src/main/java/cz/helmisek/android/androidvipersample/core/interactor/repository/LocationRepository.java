package cz.helmisek.android.androidvipersample.core.interactor.repository;

public interface LocationRepository
{
	String getLastLocation();
	void setLastLocation(final String location);
}
