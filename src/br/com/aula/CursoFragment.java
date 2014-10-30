package br.com.aula;

import br.com.aula.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CursoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View principal = inflater.inflate(R.layout.fragment_curso, container, false);
		
		return principal;
	}
}
