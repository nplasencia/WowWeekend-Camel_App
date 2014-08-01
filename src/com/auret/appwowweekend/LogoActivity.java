package com.auret.appwowweekend;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.WowHelper;

public class LogoActivity extends Activity {
	
	public static final String EXTRA_MESSAGE = "com.auret.appwowweekend.probability";
	private static final int AUTO_HIDE_DELAY_MILLIS = 10000;
	private boolean visible = false;
	private View buttonsView;
	private View configButton;
	private View nextButtons;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
		nextButtons = findViewById(R.id.next_buttons);
		
		configButton = findViewById(R.id.config_button);
		configButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
				final ArrayAdapter<String> itemsMenu = new ArrayAdapter<String>(v.getContext(), android.R.layout.select_dialog_item);
				itemsMenu.add("Ajustes");
				itemsMenu.add("Salir");
				builder.setTitle("Opciones").setAdapter(itemsMenu, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {
		            	   	if (which == 0) {
		            	   		final EditText input = new EditText(v.getContext());
		            	   		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		            	   		new AlertDialog.Builder(v.getContext()).setTitle("Contraseña").setMessage("Introduce tu contraseña para acceder a los ajustes")
		            	   	    	.setView(input).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            	   	        public void onClick(DialogInterface dialog, int whichButton) {
			            	   	            if (input.getText().toString().equals("wowAdmin_99")) {
			            	   	            	Intent intent = new Intent(LogoActivity.this, Preferences.class);
			            	   	            	startActivity(intent);
			            	   	            } else {
			            	   	            	Toast.makeText(v.getContext(), "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
			            	   	            }
			            	   	        }})
			            	   	        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			            	   	        	public void onClick(DialogInterface dialog, int whichButton) {
			            	   	        		// Do nothing.
			            	   	        	}
			            	   	        }).show();
		           			} else {
		           				finish();
		           			}
		               }
		        });
				builder.show();
			}
		});
		
		findViewById(R.id.first_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.putExtra(EXTRA_MESSAGE, ProbabilitiesEnum.MIDDLE);
				//nextButtons.setVisibility(View.VISIBLE);
				buttonsView.setVisibility(View.GONE);
				configButton.setVisibility(View.GONE);
				startActivity(intent);
				/*findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(intent);
					}
				});*/
			}
		});
		
		findViewById(R.id.second_button).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.putExtra(EXTRA_MESSAGE, ProbabilitiesEnum.HIGHEST);
				//nextButtons.setVisibility(View.VISIBLE);
				buttonsView.setVisibility(View.GONE);
				configButton.setVisibility(View.GONE);
				startActivity(intent);
				/*findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(intent);
					}
				});*/
			}
		});

		findViewById(R.id.third_button).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				intent.putExtra(EXTRA_MESSAGE, ProbabilitiesEnum.LOWEST);
				//nextButtons.setVisibility(View.VISIBLE);
				buttonsView.setVisibility(View.GONE);
				configButton.setVisibility(View.GONE);
				startActivity(intent);
				/*findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(intent);
					}
				});*/
			}
		});
		buttonsView = (View) findViewById(R.id.logo_buttons);
		delayedHide(1000);
		findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!visible) {
					visible=true;
					configButton.animate().translationY(0).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
					buttonsView.animate().translationY(0).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
					delayedHide(AUTO_HIDE_DELAY_MILLIS);
				}
			}
		});
		
		findViewById(R.id.back_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nextButtons.setVisibility(View.GONE);
				buttonsView.setVisibility(View.VISIBLE);
				configButton.setVisibility(View.VISIBLE);
				
				findViewById(R.id.fullscreen_content).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!visible) {
							visible=true;
							configButton.animate().translationY(0).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
							buttonsView.animate().translationY(0).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});
			}
		});
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        intent = new Intent(LogoActivity.this, MarcaActivity.class);
        delayedHide(1000);
    }
	
	Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
        	visible = false;
            buttonsView.animate().translationY(buttonsView.getHeight()).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
            configButton.animate().translationY(-configButton.getHeight()).setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
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
		WowHelper.resetNumeroDePremiosPorDia(getApplicationContext());
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	public void onStart() {
		super.onStart();
	}
}
