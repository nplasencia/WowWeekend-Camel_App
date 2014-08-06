package com.auret.appwowweekend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;

public class MarcaActivity extends Activity {
	
	private ProbabilitiesEnum probability;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marca);
		
		Intent intent = getIntent();
		probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);
		
		Log.d(this.getPackageName(), "Se ha seleccionado la probabilidad "+ probability.getDescription());
		
		findViewById(R.id.button_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MarcaActivity.this, LogoActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		findViewById(R.id.btn_correct).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showMarca();
			}
		});
		
		findViewById(R.id.btn_incorrect).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				probability = ProbabilitiesEnum.LOWEST;
				showMarca();
			}
		});
		
	}
	
	private void showMarca() {
		((Button)findViewById(R.id.btn_correct)).setVisibility(View.GONE);
		((Button)findViewById(R.id.btn_incorrect)).setVisibility(View.GONE);
		((ImageView)findViewById(R.id.title_content)).setImageDrawable(this.getResources().getDrawable(R.drawable.cabecera_4));
		((ImageView)findViewById(R.id.cajetilla_off)).setVisibility(View.GONE);
		((ImageView)findViewById(R.id.cajetilla_on)).animate().alpha(1).setDuration(1000L);
		
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MarcaActivity.this, ExperienciaActivity.class);
				intent.putExtra(LogoActivity.EXTRA_MESSAGE, probability);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		finish();
	}
}