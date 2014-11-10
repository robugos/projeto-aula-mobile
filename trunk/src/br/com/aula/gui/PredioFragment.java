package br.com.aula.gui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.aula.R;
import br.com.aula.dominio.Predio;
import br.com.aula.negocio.FavoritoBS;
import br.com.aula.negocio.PredioBS;

@SuppressLint("InflateParams")
public class PredioFragment extends Fragment {

	ListAdapter dataAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View principal = inflater.inflate(R.layout.fragment_predio, container,
				false);

		// View de todos os predios
		displayListView(principal);

		return principal;
	}

	private void displayListView(View view) {

		ArrayList<Predio> predioList = new ArrayList<Predio>();
		PredioBS predioBS = new PredioBS();
		Log.i("resposta: ", "antes de strings[]");
		String[] parts = predioBS.pegarPredios().split(",");
		Log.i("resposta: ", "depois de strings[]");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split("#");
			String id = c[0];
			Log.i("resposta: ", id);
			String nome = c[1];
			Log.i("resposta: ", nome);
			String quantAndar = c[2];
			Log.i("resposta: ", quantAndar);
			Predio predio = new Predio(id, nome, quantAndar, false);
			predioList.add(predio);
		}

		dataAdapter = new ListAdapter(getActivity(), R.layout.listview_predio,
				predioList);
		ListView listView = (ListView) view.findViewById(R.id.listviewPredio);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Predio predio = (Predio) parent.getItemAtPosition(position);
				chamaPredio(predio);
			}
		});

	}

	private class ListAdapter extends ArrayAdapter<Predio> {

		private ArrayList<Predio> predioList;

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Predio> predioList) {
			super(context, textViewResourceId, predioList);
			this.predioList = new ArrayList<Predio>();
			this.predioList.addAll(predioList);
		}

		private class ViewHolder {
			TextView nome;
			TextView quantAndar;
			CheckBox favorito;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_predio, null);

				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.quantAndar = (TextView) convertView.findViewById(R.id.quantAndar);
				holder.favorito = (CheckBox) convertView
						.findViewById(R.id.favoritar);
				holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Predio predio = (Predio) fav.getTag();
						FavoritoBS favorito = new FavoritoBS();
						if (fav.isChecked() == true) {
							favorito.favoritar("predio", predio.getId());
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " agora é um favorito.",
									Toast.LENGTH_LONG).show();
						} else {
							favorito.favoritar("predio", predio.getId());
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " não é mais um favorito.",
									Toast.LENGTH_LONG).show();
						}
						predio.setSelected(fav.isChecked());
					}
				});

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Predio predio = predioList.get(position);
			holder.nome.setText(predio.getNome());
			holder.quantAndar.setText(predio.getQuantAndar());
			holder.favorito.setId(position);
			holder.favorito.setChecked(predio.isSelected());
			holder.favorito.setTag(predio);

			return convertView;

		}

	}

	public void chamaPredio(Predio predio) {

		Intent i = new Intent(getActivity(), SalaActivity.class);
		Bundle bundlePredio = new Bundle();
		bundlePredio.putString("id", predio.getId());
		Log.i("id", predio.getId());
		bundlePredio.putString("nome", predio.getNome());
		Log.i("nome", predio.getNome());
		bundlePredio.putString("quantAndar", predio.getQuantAndar());
		Log.i("turno", predio.getQuantAndar());
		i.putExtras(bundlePredio);
		startActivity(i);
	}

}
