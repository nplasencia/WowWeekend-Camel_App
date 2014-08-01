package com.auret.appwowweekend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.auret.appwowweekend.enums.ProbabilitiesEnum;
import com.auret.appwowweekend.helpers.QuizHelper;
import com.auret.appwowweekend.helpers.WowTypeface;
import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;

public class QrActivity extends CaptureActivity {

	private ProbabilitiesEnum probability;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.capture);
		
		Intent intent = getIntent();
		probability = (ProbabilitiesEnum) intent.getSerializableExtra(LogoActivity.EXTRA_MESSAGE);

		//Log.d(this.getPackageName(), "Se ha seleccionado la probabilidad "+ probability.getDescription());
		
		((TextView)findViewById(R.id.qr_subtitle)).setTypeface(WowTypeface.Impact(this));
		findViewById(R.id.button_home).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				QuizHelper quiz = new QuizHelper(v.getContext(), probability);
				quiz.execute("");
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
	public void handleDecode(Result rawResult, Bitmap barcode) {
		QuizHelper quiz = new QuizHelper(this, probability);
		quiz.execute("");
	}

}