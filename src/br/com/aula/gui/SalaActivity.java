package br.com.aula.gui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import br.com.aula.dominio.Sala;
import br.com.aula.negocio.FavoritoBS;
import br.com.aula.negocio.SalaBS;

@SuppressLint("InflateParams")
public class SalaActivity extends Activity {

	public Predio predio;
	public ListAdapter dataAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sala);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getValues();
		setTitle(this.getTitle() + " - " + predio.getNome());

		displayListView();

	}

	public void getValues() {
		String id;
		String nome;
		String quantAndar;
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			Log.i("Extras", "Null");
			id = "1";
			nome = "";
			quantAndar = "";
		} else {
			Log.i("Extras", "não Null");
			id = extras.getString("id");
			Log.i("Extras", id);
			nome = extras.getString("nome");
			Log.i("Extras", nome);
			quantAndar = extras.getString("quantAndar");
			Log.i("Extras", quantAndar);
		}
		predio = new Predio(id, nome, quantAndar, false);
	}

	private void displayListView() {

		ArrayList<Sala> salaList = new ArrayList<Sala>();
		SalaBS salaBS = new SalaBS();
		Log.i("resposta: ", "antes de strings[]");
		String[] parts = salaBS.pegarSalas(predio).split(",");
		Log.i("resposta: ", "depois de strings[]");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split("#");
			String id = c[0];
			String numero = c[1];
			String tipo = c[2];
			String andar = c[2];
			String observacao = c[2];

			Sala sala = new Sala(id, numero, tipo, andar, predio, observacao, false);
			salaList.add(sala);
		}

		dataAdapter = new ListAdapter(this, R.layout.listview_sala,
				salaList);
		ListView listView = (ListView) findViewById(R.id.listviewSalas);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Quando clica, mostra uma toast com o texto da TextView
				Sala sala = (Sala) parent
						.getItemAtPosition(position);
				Toast.makeText(SalaActivity.this,
						"Clicou em " + sala.getNumero() + sala.getId(),
						Toast.LENGTH_LONG).show();
				// chamaCurso(curso);
			}
		});

	}

	private class ListAdapter extends ArrayAdapter<Sala> {

		private ArrayList<Sala> salaList;

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Sala> salaList) {
			super(context, textViewResourceId, salaList);
			this.salaList = new ArrayList<Sala>();
			this.salaList.addAll(salaList);
		}

		private class ViewHolder {
			TextView nome;
			TextView andar;
			CheckBox favorito;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_sala, null);

				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.andar = (TextView) convertView
						.findViewById(R.id.professor);
				holder.favorito = (CheckBox) convertView
						.findViewById(R.id.favoritar);
				holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Sala sala = (Sala) fav.getTag();
						FavoritoBS favorito = new FavoritoBS();
						if (fav.isChecked() == true) {
							favorito.favoritar("sala", sala.getId());
							Toast.makeText(getApplicationContext(),
									fav.getId() + " agora é um favorito.",
									Toast.LENGTH_LONG).show();
						} else {
							favorito.favoritar("sala", sala.getId());
							Toast.makeText(getApplicationContext(),
									fav.getId() + " não é mais um favorito.",
									Toast.LENGTH_LONG).show();
						}
						sala.setSelected(fav.isChecked());
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Sala sala = salaList.get(position);
			holder.nome.setText(sala.getNumero());
			holder.andar.setText(sala.getAndar());
			holder.favorito.setChecked(sala.isSelected());
			holder.favorito.setTag(sala);

			return convertView;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
