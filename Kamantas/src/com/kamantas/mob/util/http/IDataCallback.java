package com.kamantas.mob.util.http;

import android.app.Activity;

/**
 * 
 * @author Mohamed Shalabi
 * 
 */
public interface IDataCallback {
	/**
	 * This method is called before the connection is made. Runs on UI thread
	 * 
	 * @param requestId
	 *            identifier for the request. Can be used to differentiate
	 *            between different request initiated from the same implementor
	 * @param ui
	 *            the caller activity
	 */
	void onPreExecute(int requestId, final Activity ui);

	/**
	 * Called after response is received. Runs on UI thread.
	 * 
	 * @param requestId
	 *            identifier for the request. Can be used to differentiate
	 *            between different request initiated from the same implementor
	 *            identifier for the request
	 * @param ui
	 *            the caller activity
	 * @param response
	 *            the received response
	 */
	void onPostExecute(int requestId, final Activity ui, final String response);

}
