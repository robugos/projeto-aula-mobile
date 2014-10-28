package br.com.aula.adapter;

import br.com.aula.CursoFragment;
import br.com.aula.PredioFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	public static final CursoFragment CURSO = new CursoFragment();
	public static final PredioFragment PREDIO = new PredioFragment();
	public static final int NUM_ABAS = 2;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// fragment_curso
			return CURSO;
		case 1:
			// fragment_predio
			return PREDIO;
		}

		return null;
	}

	@Override
	public int getCount() {
		// igual numero de abas
		return NUM_ABAS;
	}

}
