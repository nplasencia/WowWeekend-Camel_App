package com.auret.appwowweekend.helpers;

import android.content.Context;
import android.graphics.Typeface;

public final class WowTypeface {
	public static final Typeface Impact(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), "Impact.ttf");
        return typeface;
    }
}
