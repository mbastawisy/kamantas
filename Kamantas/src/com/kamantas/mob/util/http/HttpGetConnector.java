package com.kamantas.mob.util.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.util.Log;

import com.kamantas.mob.global.Globals;

public class HttpGetConnector extends Connector {

	public HttpGetConnector(String url, Activity mActivity, int requestId,
			IDataCallback callback) {
		super(url, mActivity, requestId, callback);
	}

	protected String doInBackground(BasicNameValuePair... params) {
		try {
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();

			for (int i = 0; i < params.length; i++) {
				parameters.add(params[i]);
			}

			HttpParams p = new BasicHttpParams();
			p.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
					HttpVersion.HTTP_1_1);
			p.setParameter(CoreProtocolPNames.USER_AGENT, Globals.getInstance()
					.getUserAgent());

			DefaultHttpClient httpClient = new DefaultHttpClient(p);
			HttpResponse httpResponse;

			HttpGet httpGet = new HttpGet(mUrl);

			httpResponse = httpClient.execute(httpGet);
			String response = EntityUtils.toString(httpResponse.getEntity());

			httpResponse.getEntity().consumeContent();
			httpClient.getConnectionManager().shutdown();

			return response;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}

		return "-500";
	}
}