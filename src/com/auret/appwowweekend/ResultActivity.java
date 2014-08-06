package com.auret.appwowweekend;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.WowHelper;

public class ResultActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		ProbabilitiesEnum probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);
		Log.d(this.getPackageName(), "Se ha llegado con una probabilidad "+ probability.getDescription());
		
		SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		int x = 0;
		double random = 0;
		if (!probability.equals(ProbabilitiesEnum.LOWEST)) {
			random = Math.random()*100;
			x=(random<probability.getValue())? 1:0;
		}
		
		Integer premiosHoy = Integer.parseInt(userPrefs.getString(Integer.toString(WowHelper.getDate(getApplicationContext()).get(Calendar.DAY_OF_YEAR)), "0"));
		Integer premiosPorDia = Integer.parseInt(userPrefs.getString("premiosDia", "0"));
		
		if (x == 1 && premiosHoy < premiosPorDia) {
			premiosHoy++;
			userPrefs.edit().putString(Integer.toString(WowHelper.getDate(getBaseContext()).get(Calendar.DAY_OF_YEAR)), premiosHoy.toString()).commit();
			Log.d(this.getPackageName(), "El usuario ha ganado.");
			setContentView(R.layout.result_winner);
			findViewById(R.id.button_camara).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
						startActivityForResult(takePictureIntent, 1);
					}
				}
			});
			
		} else {
			Log.d(this.getPackageName(), "El usuario ha perdido.");
			setContentView(R.layout.result_looser);
		}
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ResultActivity.this, LogoActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
