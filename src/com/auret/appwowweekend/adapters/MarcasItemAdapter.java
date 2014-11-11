package com.auret.appwowweekend.adapters;

import java.util.List;
import java.util.Locale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.auret.appwowweekend.R;
import com.auret.appwowweekend.classes.Marca;
import com.auret.appwowweekend.helpers.WowTypeface;

public class MarcasItemAdapter extends BaseAdapter {
	protected List<Marca> items;

	public MarcasItemAdapter(List<Marca> items) {
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getMarca().getValue();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;

		if(convertView == null) {
			vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_logo, parent, false);
		}

		Marca item = (Marca) getItem(position); 

		TextView nombre = (TextView) vi.findViewById(R.id.text_itemName);
		nombre.setTypeface(WowTypeface.Impact(parent.getContext()));
		nombre.setText(item.getMarca().getDescription().toUpperCase(Locale.getDefault()));
		if (item.getChecked()) {
			((ImageView) vi.findViewById(R.id.btn_itemOn)).setVisibility(View.VISIBLE);
			((ImageView) vi.findViewById(R.id.btn_itemOff)).setVisibility(View.GONE);
		} else {
			((ImageView) vi.findViewById(R.id.btn_itemOn)).setVisibility(View.GONE);
			((ImageView) vi.findViewById(R.id.btn_itemOff)).setVisibility(View.VISIBLE);
		}

		return vi;
	}
}