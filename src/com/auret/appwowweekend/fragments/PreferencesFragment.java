package com.auret.appwowweekend.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.auret.appwowweekend.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PreferencesFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
	}
}