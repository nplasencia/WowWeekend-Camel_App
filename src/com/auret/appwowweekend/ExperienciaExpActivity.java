package com.auret.appwowweekend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;

public class ExperienciaExpActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.experiencia_exp);
		
		Intent intent = getIntent();
		final ProbabilitiesEnum probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);
		
		Log.d(this.getPackageName(), "Se ha llegado a la experiencia explanation con una probabilidad "+ probability.getDescription());
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExperienciaExpActivity.this, ExperienciaActivity.class);
				intent.putExtra(LogoActivity.EXTRA_MESSAGE, probability);            	
				startActivity(intent);
			}
		});
		
		findViewById(R.id.button_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExperienciaExpActivity.this, LogoActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.logo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
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
