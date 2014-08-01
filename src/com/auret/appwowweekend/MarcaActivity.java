package com.auret.appwowweekend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.WowTypeface;

public class MarcaActivity extends Activity {
	
	private ProbabilitiesEnum probability;
	private EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marca);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		Intent intent = getIntent();
		probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);
		
//		Log.d(this.getPackageName(), "Se ha seleccionado la probabilidad "+ probability.getDescription());
		
		edit = (EditText) findViewById(R.id.marca_edit);
		edit.setTypeface(WowTypeface.Impact(this));
		
		edit.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				edit.setBackgroundDrawable(getResources().getDrawable(R.drawable.campo_texto_on));
				return false;
			}
		});
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!edit.getText().toString().equals("")) {
					showMarca();
				}
			}
		});
		
		findViewById(R.id.button_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MarcaActivity.this, LogoActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
	}
	
	private void showMarca() {
		edit.setEnabled(false);
		edit.setFocusable(false);
		((ImageView)findViewById(R.id.title_content)).setImageDrawable(this.getResources().getDrawable(R.drawable.cabecera_4));
		((ImageView)findViewById(R.id.cajetilla_off)).setVisibility(View.GONE);
		((ImageView)findViewById(R.id.cajetilla_on)).animate().alpha(1).setDuration(1000L);
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MarcaActivity.this, ExperienciaActivity.class);
				if (edit.getText().toString().toLowerCase().equals("camel")) {
					//Toast.makeText(v.getContext(), "ha puesto bien la marca", Toast.LENGTH_SHORT).show();
					intent.putExtra(LogoActivity.EXTRA_MESSAGE, probability);
				} else {
					//Toast.makeText(v.getContext(), "NO ha puesto bien la marca", Toast.LENGTH_SHORT).show();
					intent.putExtra(LogoActivity.EXTRA_MESSAGE, ProbabilitiesEnum.LOWEST);
				}
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