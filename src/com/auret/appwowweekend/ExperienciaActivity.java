package com.auret.appwowweekend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.WowTypeface;

public class ExperienciaActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.experiencia);
		
		Intent intent = getIntent();
		final ProbabilitiesEnum probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);
		
		Log.d(this.getPackageName(), "Se ha llegado a la experiencia con una probabilidad "+ probability.getDescription());
		
		((TextView)findViewById(R.id.experience_subtitle)).setTypeface(WowTypeface.Impact(this));
		
		TextView titulo = (TextView)findViewById(R.id.experience_title);
		ImageView image = (ImageView)findViewById(R.id.experience);
		titulo.setTypeface(WowTypeface.Impact(this));
		
		SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (userPrefs.getBoolean("granCanaria", false)) {
			titulo.setText("31 DE AGOSTO / AMADORES BEACH CLUB");
			image.setImageDrawable(getResources().getDrawable(R.drawable.fotos_exp_lp));
		} else {
			titulo.setText("30 DE AGOSTO / MONKEY BEACH CLUB");
			image.setImageDrawable(getResources().getDrawable(R.drawable.fotos_exp_tfn));
		}
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExperienciaActivity.this, QrActivity.class);
				intent.putExtra(LogoActivity.EXTRA_MESSAGE, probability);            	
				startActivity(intent);
				finish();
			}
		});
		
		findViewById(R.id.button_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ExperienciaActivity.this, LogoActivity.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.back_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
