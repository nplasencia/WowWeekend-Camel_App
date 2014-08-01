package com.auret.appwowweekend.helpers;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WowHelper {
	
	public static Calendar getDate(Context context) {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.HOUR_OF_DAY)>=19) {
//			Toast.makeText(context, "Se le suma 1 al dia porque son mas de las 7", Toast.LENGTH_LONG).show();
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		return cal;
	}
	
	public static void resetNumeroDePremiosPorDia(Context context) {
		Calendar cal = getDate(context);
		SharedPreferences userPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		
		Integer ultimoDia = Integer.parseInt(userPrefs.getString("ultimoDia", "0"));
		if (ultimoDia<cal.get(Calendar.DAY_OF_YEAR)) {
			userPrefs.edit().putString("ultimoDia", Integer.toString(cal.get(Calendar.DAY_OF_YEAR))).commit();
			userPrefs.edit().putString("premiosDia", "0").commit();
		}
	}
	
}