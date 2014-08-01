package com.auret.appwowweekend;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.auret.appwowweekend.fragments.PreferencesFragment;

public class Preferences extends Activity {
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
		setContentView(R.layout.preferences);

		SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		userPrefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener () {

			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				if (key.equals("tenerife") && sharedPreferences.getBoolean("tenerife", true)) {
					sharedPreferences.edit().putBoolean("tenerife", true).commit();
					sharedPreferences.edit().putBoolean("granCanaria", false).commit();
					refresh();
				} else if (key.equals("granCanaria") && sharedPreferences.getBoolean("granCanaria", true)) {
					sharedPreferences.edit().putBoolean("granCanaria", true).commit();
					sharedPreferences.edit().putBoolean("tenerife", false).commit();
					refresh();
				}
			}
		});
	}
	
	private void refresh() {
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
	}
	
	protected void onResume() {
    	super.onResume();
    	refresh();
    }
	
	@Override
	public void onStart() {
		super.onStart();
	}
	 
	@Override
	public void onStop() {
		super.onStop();
	}
}
