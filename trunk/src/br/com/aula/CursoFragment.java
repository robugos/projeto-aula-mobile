package br.com.aula;

import java.util.ArrayList;

import br.com.aula.R;
import br.com.aula.dominio.Curso;
import br.com.aula.dominio.Predio;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CursoFragment extends Fragment {

	ListAdapter dataAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View principal = inflater.inflate(R.layout.fragment_curso, container, false);

		// View de todos os cursos
		displayListView(principal);

		// Mostrar todos os favoritos
		// checkButtonClick(principal);

		return principal;
	}

	private void displayListView(View view) {

		// Array de todos os cursos
		ArrayList<Curso> cursoList = new ArrayList<Curso>(); 
		Curso curso = new Curso(1, "Bach. Sistemas de Informação", "Manhã", false);
		cursoList.add(curso);
		curso = new Curso(2, "Lic. Computação", "Noite", true);
		cursoList.add(curso);
		curso = new Curso(3, "Bach. Ciências da Computação", "Tarde", false);
		cursoList.add(curso);
		curso = new Curso(4, "Administração", "Manhã", true);
		cursoList.add(curso);

		//
		dataAdapter = new ListAdapter(getActivity(), R.layout.listview_curso, cursoList);
		ListView listView = (ListView) view.findViewById(R.id.listviewCurso);
		//
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Quando clica, mostra uma toast com o texto da TextView
				Curso curso = (Curso) parent.getItemAtPosition(position);
				Toast.makeText(getActivity().getApplicationContext(), "Clicou em " + curso.getNome() + curso.getIdCurso(), Toast.LENGTH_LONG).show();
				//chamaCurso(curso.getIdCurso());
			}
		});

	}

	private class ListAdapter extends ArrayAdapter<Curso> {

		private ArrayList<Curso> cursoList;

		public ListAdapter(Context context, int textViewResourceId, ArrayList<Curso> cursoList) {
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
				LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.listview_curso, null);
				
				holder = new ViewHolder();
				holder.nome = (TextView) convertView.findViewById(R.id.nome);
				holder.turno = (TextView) convertView.findViewById(R.id.turno);
				holder.favorito = (CheckBox) convertView.findViewById(R.id.favoritar);
				holder.favorito.setButtonDrawable(R.drawable.star);
				convertView.setTag(holder);

				holder.favorito.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox fav = (CheckBox) v;
						Curso curso = (Curso) fav.getTag();
						if (fav.isChecked() == true) {
							Toast.makeText(getContext().getApplicationContext(), fav.getId() + " agora é um favorito.", Toast.LENGTH_LONG).show();	
						}else{
							Toast.makeText(getContext().getApplicationContext(), fav.getId() + " não é mais um favorito.", Toast.LENGTH_LONG).show();
						}
						curso.setSelected(fav.isChecked());
					}
				});
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Curso curso = cursoList.get(position);
			//holder.nome.setText(" (" + curso.getCodCurso() + ")");
			holder.nome.setText(curso.getNome());
			holder.turno.setText(curso.getTurno());
			holder.favorito.setId(curso.getIdCurso());
			holder.favorito.setChecked(curso.isSelected());
			holder.favorito.setTag(curso);

			return convertView;

		}

	}
	
	public void chamaCurso(int idCurso) {
		startActivity(new Intent(getActivity(), PredioActivity.class));
		PredioActivity predio = new PredioActivity();
		predio.setIdCurso(idCurso);
	}

	// Mostrar todos os favoritos
	/*
	 * private void checkButtonClick(View view) {
	 * 
	 * Button selecionados = (Button) view.findViewById(R.id.findSelected);
	 * selecionados.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) {
	 * 
	 * StringBuffer responseText = new StringBuffer();
	 * responseText.append("Os cursos abaixo estão selecionados\n");
	 * 
	 * ArrayList<Curso> cursoList = dataAdapter.cursoList; for (int i = 0; i <
	 * cursoList.size(); i++) { Curso curso = cursoList.get(i); if
	 * (curso.isSelected()) { responseText.append("\n" + curso.getNome()); } }
	 * 
	 * Toast.makeText(getActivity().getApplicationContext(), responseText,
	 * Toast.LENGTH_LONG).show();
	 * 
	 * } });
	 * 
	 * }
	 */

}
