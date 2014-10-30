package br.com.aula;

import br.com.aula.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PredioFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_predio, container, false);
		
		Button button = (Button) view.findViewById(R.id.ceagri);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				chamaPredio();
			} 
		});
		
		return view;
	}
	
	public void chamaPredio() {
		Intent entra = new Intent(getActivity(), PredioActivity.class);
		entra.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(entra);
	}

}
