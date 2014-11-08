package br.com.aula.gui;

import java.util.ArrayList;

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
import br.com.aula.dao.ConexaoHttpClient;
import br.com.aula.dominio.Curso;

public class CursoFragment extends Fragment {

	ListAdapter dataAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View principal = inflater.inflate(R.layout.fragment_curso, container,
				false);

		// View de todos os cursos
		displayListView(principal);

		return principal;
	}

	private void displayListView(View view) {

		ArrayList<Curso> cursoList = new ArrayList<Curso>();
		//CursoBS cursoBS = new CursoBS();
		Log.i("resposta: ", "antes de strings[]");
		String[] parts = pegarCursos().split(",");
		Log.i("resposta: ", "depois de strings[]");
		for (int i = 0; i < parts.length; i++) {
			String[] c = parts[i].split("#");
			String idCurso = c[0];
			Log.i("resposta: ", idCurso);
			String nome = c[1];
			Log.i("resposta: ", nome);
			String turno = c[2];
			Log.i("resposta: ", turno);
			Curso curso = new Curso(idCurso, nome, turno, false);
			cursoList.add(curso);
		}

		// Array de todos os cursos
		/*
		 * Curso curso = new Curso(1, "Bach. Sistemas de InformaÁ„o", "Manh„",
		 * false); cursoList.add(curso); curso = new Curso(2, "Lic. ComputaÁ„o",
		 * "Noite", true); cursoList.add(curso); curso = new Curso(3,
		 * "Bach. CiÍncias da ComputaÁ„o", "Tarde", false);
		 * cursoList.add(curso); curso = new Curso(4, "AdministraÁ„o", "Manh„",
		 * true); cursoList.add(curso);
		 */

		//

		dataAdapter = new ListAdapter(getActivity(), R.layout.listview_curso,
				cursoList);
		ListView listView = (ListView) view.findViewById(R.id.listviewCurso);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Quando clica, mostra uma toast com o texto da TextView
				Curso curso = (Curso) parent.getItemAtPosition(position);
				//Toast.makeText(getActivity().getApplicationContext(),
					//	"Clicou em " + curso.getNome() + curso.getIdCurso(),
						//Toast.LENGTH_LONG).show();
				chamaCurso(curso);
			}
		});

	}

	public String pegarCursos() {

		String urlGet = "http://150.161.16.233:8080/Aulaweb/listarCursos.jsp";

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

	private class ListAdapter extends ArrayAdapter<Curso> {

		private ArrayList<Curso> cursoList;

		public ListAdapter(Context context, int textViewResourceId,
				ArrayList<Curso> cursoList) {
			super(context, textViewResourceId, cursoList);
			this.cursoList = new ArrayList<Curso>();
			this.cursoList.addAll(cursoList);
		}

		private class ViewHolder {
			TextView nome;
			TextView turno;
			CheckBox favorito;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_curso, null);

				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.turno = (TextView) convertView.findViewById(R.id.turno);
				holder.favorito = (CheckBox) convertView
						.findViewById(R.id.favoritar);
				holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Curso curso = (Curso) fav.getTag();
						if (fav.isChecked() == true) {
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " agora È um favorito.",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(
									getContext().getApplicationContext(),
									fav.getId() + " n„o È mais um favorito.",
									Toast.LENGTH_LONG).show();
						}
						curso.setSelected(fav.isChecked());
					}
				});

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Curso curso = cursoList.get(position);
			// holder.nome.setText(" (" + curso.getCodCurso() + ")");
			holder.nome.setText(curso.getNome());
			holder.turno.setText(curso.getTurno());
			//holder.favorito.setId(Integer.parseInt(curso.getIdCurso()));
			holder.favorito.setChecked(curso.isSelected());
			holder.favorito.setTag(curso);

			return convertView;

		}

	}

	public void chamaCurso(Curso curso) {

		Intent i = new Intent(getActivity().getApplicationContext(), DisciplinaActivity.class);
		i.putExtra("idCurso", curso.getIdCurso());
		i.putExtra("nome", curso.getNome());
		i.putExtra("turno", curso.getTurno());
		startActivity(i);
	}

}
