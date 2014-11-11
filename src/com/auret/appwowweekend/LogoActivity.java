package com.auret.appwowweekend;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.auret.appwowweekend.adapters.EdadItemAdapter;
import com.auret.appwowweekend.adapters.MarcasItemAdapter;
import com.auret.appwowweekend.classes.Edad;
import com.auret.appwowweekend.classes.Marca;
import com.auret.appwowweekend.enums.EdadesEnum;
import com.auret.appwowweekend.enums.MarcasEnum;
import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.WowHelper;

public class LogoActivity extends Activity {
	
	public static final String EXTRA_MESSAGE = "com.auret.appwowweekend.probability";
	private static final int AUTO_HIDE_DELAY_MILLIS = 10000;
	private boolean visible = false;
	private View buttonsView;
	private View configButton;
	private Marca marcaHabitual;
	private Marca marcaOcasional;
	private Edad edad;
	private ListView listHabitual;
	private ListView listOcasional;
	private ListView listEdades;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
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
		
		final List<Marca> marcasHabituales = getAllMarcas();
		final MarcasItemAdapter adapter1 = new MarcasItemAdapter(marcasHabituales);
		listHabitual = ((ListView) findViewById(R.id.list_habitual));
		listHabitual.setAdapter(adapter1);
		
		final List<Marca> marcasOcasionales = getAllMarcas();
		final MarcasItemAdapter adapterOcasionales = new MarcasItemAdapter(marcasOcasionales);
		listOcasional = ((ListView) findViewById(R.id.list_ocasional));
		listOcasional.setAdapter(adapterOcasionales);
		
		final List<Edad> edades = getAllEdades();
		final EdadItemAdapter adapterEdades = new EdadItemAdapter(edades);
		listEdades = ((ListView) findViewById(R.id.list_edad));
		listEdades.setAdapter(adapterEdades);
		
		listHabitual.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for (Marca marca : marcasHabituales) {
					marca.setChecked(false);
				}
				marcaHabitual = (Marca) parent.getItemAtPosition(position);
				marcaHabitual.setChecked(true);
				listHabitual.invalidateViews();
				
				//Toast.makeText(parent.getContext(), "Marca habitual: " + marcaHabitual.getMarca().getDescription(), Toast.LENGTH_LONG).show();
				pasarPantalla();
			}
		});
		
		findViewById(R.id.first_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
				listOcasional.setVisibility(View.GONE);
				listEdades.setVisibility(View.GONE);
				if (listHabitual.getVisibility()==View.GONE) {
					listHabitual.setVisibility(View.VISIBLE);
				} else {
					listHabitual.setVisibility(View.GONE);
				}
			}
		});
		
		listOcasional.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for (Marca marca : marcasOcasionales) {
					marca.setChecked(false);
				}
				marcaOcasional = (Marca) parent.getItemAtPosition(position);
				marcaOcasional.setChecked(true);
				listOcasional.invalidateViews();

				//Toast.makeText(parent.getContext(), "Marca ocasional: " + marcaOcasional.getMarca().getDescription(), Toast.LENGTH_LONG).show();
				pasarPantalla();
			}
		});
		
		findViewById(R.id.second_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
				listHabitual.setVisibility(View.GONE);
				listEdades.setVisibility(View.GONE);
				if (listOcasional.getVisibility()==View.GONE) {
					listOcasional.setVisibility(View.VISIBLE);
				} else {
					listOcasional.setVisibility(View.GONE);
				}
			}
		});
		
		listEdades.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for (Edad edad : edades) {
					edad.setChecked(false);
				}
				edad = (Edad) parent.getItemAtPosition(position);
				edad.setChecked(true);
				listEdades.invalidateViews();
				
				//Toast.makeText(parent.getContext(), "Edad: " + edad.getEdad().getDescription(), Toast.LENGTH_LONG).show();
				pasarPantalla();
			}
		});
		
		findViewById(R.id.third_button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
				listHabitual.setVisibility(View.GONE);
				listOcasional.setVisibility(View.GONE);
				
				if (listEdades.getVisibility()==View.GONE) {
					listEdades.setVisibility(View.VISIBLE);
				} else {
					listEdades.setVisibility(View.GONE);
				}
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
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(1000);
    }
	
	Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
        	listHabitual.setVisibility(View.GONE);
			listOcasional.setVisibility(View.GONE);
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
	
	private void pasarPantalla(){
		delayedHide(AUTO_HIDE_DELAY_MILLIS);
		listEdades.setVisibility(View.GONE);
		listOcasional.setVisibility(View.GONE);
		listHabitual.setVisibility(View.GONE);
		
		if (marcaHabitual != null) {
			if (marcaOcasional != null) {
				if (edad != null) {
					ProbabilitiesEnum probabilidad = ProbabilitiesEnum.LOWEST;
					if (!edad.getEdad().equals(EdadesEnum.MAYOR35) && !marcaHabitual.getMarca().equals(MarcasEnum.WINSTON) && !marcaHabitual.getMarca().equals(MarcasEnum.OTROS) &&
							!marcaOcasional.getMarca().equals(MarcasEnum.WINSTON) && !marcaOcasional.getMarca().equals(MarcasEnum.OTROS)) {
						if (marcaHabitual.getMarca().equals(MarcasEnum.CAMEL)) {
							probabilidad = ProbabilitiesEnum.MIDDLE;
						} else {
							probabilidad = ProbabilitiesEnum.HIGHEST;
						}
					}
					Intent intent = new Intent(LogoActivity.this, MarcaActivity.class);
					intent.putExtra(EXTRA_MESSAGE, probabilidad);
   	            	startActivity(intent);
   	            	finish();
				} else {
					listEdades.setVisibility(View.VISIBLE);
				}
			} else {
				listOcasional.setVisibility(View.VISIBLE);
			}
		} else {
			listHabitual.setVisibility(View.VISIBLE);
		}
	}
	
	private List<Marca> getAllMarcas() {
		MarcasEnum marcasEnum[] = MarcasEnum.values();
		List<Marca> marcas = new ArrayList<Marca>(marcasEnum.length);
		for (int i = 0; i < marcasEnum.length; i++) {
			marcas.add(new Marca(marcasEnum[i], false));
		}
		return marcas;
	}
	
	private List<Edad> getAllEdades() {
		EdadesEnum edadesEnum[] = EdadesEnum.values();
		List<Edad> edades = new ArrayList<Edad>(edadesEnum.length);
		for (int i = 0; i < edadesEnum.length; i++) {
			edades.add(new Edad(edadesEnum[i], false));
		}
		return edades;
	}
}
