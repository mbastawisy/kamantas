package com.kamantas.mob.util.http;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;

public abstract class Connector extends
		AsyncTask<BasicNameValuePair, Integer, String> {
	
	protected static final String TAG = "com.kamantas.mob.util.http.Connector";
	protected String mUrl;
	protected Activity mActivity;
	protected int requestId;
	protected IDataCallback mCallback;

	public Connector(String url, Activity mActivity, int requestId, IDataCallback callback) {
		this.mUrl = url;
		this.mActivity = mActivity;
		this.requestId = requestId;
		this.mCallback = callback;
	}
	
	protected void onPreExecute() {
		mCallback.onPreExecute(requestId, mActivity);
	}

	protected abstract String doInBackground(BasicNameValuePair... params);

	protected void onPostExecute(String result) {
		mCallback.onPostExecute(requestId, mActivity, result.trim());
	}
}
