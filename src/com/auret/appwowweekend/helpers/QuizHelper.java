package com.auret.appwowweekend.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.auret.appwowweekend.LogoActivity;
import com.auret.appwowweekend.QrActivity;
import com.auret.appwowweekend.R;
import com.auret.appwowweekend.ResultActivity;
import com.auret.appwowweekend.enums.ProbabilitiesEnum;

public class QuizHelper extends AsyncTask<String, Integer, String>{
	private QrActivity activity;
	private Context context;
	private ProbabilitiesEnum probability;
	private ProgressDialog dialog;
	
	public QuizHelper(Context context, ProbabilitiesEnum probability, QrActivity activity) {
		this.context = context;
		this.probability = probability;
		this.activity = activity;
		dialog = new ProgressDialog(context, R.style.AppWowWeekendDialog);
        dialog.setMessage(context.getResources().getString(R.string.getting_gift));
        dialog.setCancelable(false);
	}
	
	protected void onPreExecute() {
		super.onPreExecute();
        dialog.show();
	}
	
	@Override
	protected String doInBackground(String... params) {
		 try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        return "";
	}
	
	protected void onPostExecute (String result){
		dialog.dismiss();
		Intent intent = new Intent (context, ResultActivity.class);
		intent.putExtra(LogoActivity.EXTRA_MESSAGE, probability);
		context.startActivity(intent);
		activity.finish();
	}
	
}
