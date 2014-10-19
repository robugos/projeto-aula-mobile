package br.com.aula.adapter;

import br.com.aula.Curso;
import br.com.aula.Predio;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Curso fragment activity
			return new Curso();
		case 1:
			// Predio fragment activity
			return new Predio();
		}

		return null;
	}

	@Override
	public int getCount() {
		// igual numero de abas
		return 2;
	}

}
