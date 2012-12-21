package com.kamantas.mob.startup;

import com.kamantas.mob.global.Globals;

import android.app.Application;

public class AppLoader extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		
		Globals.init(this);
	}
}
