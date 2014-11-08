package br.com.aula.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Curso;
import br.com.aula.dominio.Disciplina;

public class DisciplinaActivity extends Activity {

	public Curso curso;
	public String idCurso, nome, turno;
	public ListAdapter dataAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disciplina);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getValues();
		setTitle(this.getTitle() + " - " + nome);

		try {
			displayListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getValues() {
		Intent i = this.getIntent();
		idCurso = i.getStringExtra("idCurso");
		nome = i.getStringExtra("nome");
		turno = i.getStringExtra("turno");
	}

	private void displayListView() {

		ArrayList<Disciplina> discList = new ArrayList<Disciplina>();
		//CursoBS cursoBS = new CursoBS();
		Log.i("resposta: ", "antes de strings[]");
		String[] parts = pegarDisciplinas().split(",");
		Log.i("resposta: ", "depois de strings[]");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split("#");
			String id = c[0];
			Log.i("resposta: ", idCurso);
			String nome = c[1];
			Log.i("resposta: ", nome);
			String professor = c[2];
			Log.i("resposta: ", professor);
			Disciplina disc = new Disciplina(id, nome, curso, professor, false);
			discList.add(disc);
		}
		
		dataAdapter = new ListAdapter(this, R.layout.listview_disciplina,
				discList);
		ListView listView = (ListView) findViewById(R.id.listviewDisciplinas);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Quando clica, mostra uma toast com o texto da TextView
				Disciplina disc = (Disciplina) parent
						.getItemAtPosition(position);
				Toast.makeText(DisciplinaActivity.this,
						"Clicou em " + disc.getNome() + disc.getId(),
						Toast.LENGTH_LONG).show();
				// chamaCurso(curso);
			}
		});

	}
	
	public String pegarDisciplinas() {

		String urlGet = "http://150.161.16.233:8080/Aulaweb/listarDisciplinas.jsp?=idCurso="+curso.getIdCurso();

		String resposta = null;

		try {
			resposta = ConexaoHttpClient.executaHttpGet(urlGet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("erro", " " + e);
		}

		// mensagemExibir("Login", respostaConvertida);

		// mensagemExibir("Login", "Usu√°rio v√°lido");
		return resposta;

	}

	private class ListAdapter extends ArrayAdapter<Disciplina> {

		private ArrayList<Disciplina> discList;

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Disciplina> discList) {
			super(context, textViewResourceId, discList);
			this.discList = new ArrayList<Disciplina>();
			this.discList.addAll(discList);
		}

		private class ViewHolder {
			TextView nome;
			TextView professor;
			CheckBox favorito;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) DisciplinaActivity.this
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_disciplina, null);

				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.professor = (TextView) convertView
						.findViewById(R.id.professor);
				holder.favorito = (CheckBox) convertView
						.findViewById(R.id.favoritar);
				holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Disciplina disc = (Disciplina) fav.getTag();
						if (fav.isChecked() == true) {
							Toast.makeText(DisciplinaActivity.this,
									fav.getId() + " agora È um favorito.",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(DisciplinaActivity.this,
									fav.getId() + " n„o È mais um favorito.",
									Toast.LENGTH_LONG).show();
						}
						disc.setSelected(fav.isChecked());
					}
				});

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Disciplina disc = discList.get(position);
			// holder.nome.setText(" (" + curso.getCodCurso() + ")");
			holder.nome.setText(disc.getNome());
			holder.professor.setText(disc.getProfessor());
			//holder.favorito.setId(disc.getId());
			holder.favorito.setChecked(disc.isSelected());
			holder.favorito.setTag(disc);

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
