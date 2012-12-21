package com.kamantas.mob.global;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.kamantas.mob.R;

/**
 * This class holds all global and configuration variables in the application.
 * This class follows singleton design pattern and should be initialized at the
 * startup of the application.
 * 
 * @author Mohamed Shalabi
 * 
 */
public class Globals {
	private static Globals instance;

	private String userAgent;
	private String baseUrl;

	public static void init(Application app) {
		instance = new Globals();

		PackageInfo pInfo;
		int versionCode = 0;

		try {
			pInfo = app.getPackageManager().getPackageInfo(
					app.getPackageName(), 0);
			versionCode = pInfo.versionCode;
		} catch (NameNotFoundException e) {

		}

		// initialize variables
		instance.baseUrl = app.getString(R.string.base_url);

		String appName = app.getString(R.string.app_name);
		instance.userAgent = appName + "(Android "
				+ android.os.Build.VERSION.SDK_INT + "; Ver " + versionCode
				+ ")";
	}

	public static Globals getInstance() {
		if (instance == null) {
			instance = new Globals();
		}

		return instance;
	}

	public String getBaseUrl() {
		return baseUrl;
	}
	
	public String getUserAgent() {
		return userAgent;
	}
}
