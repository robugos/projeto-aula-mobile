package br.com.aula;

import br.com.aula.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
/*import android.view.View;
import android.widget.ImageButton;*/

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter adapt;
	private ActionBar actionBar;
	// T�tulo das Abas
	private String[] abas = { "Curso", "Pr�dio" };
	private static Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainActivity.context = getApplicationContext();

		// Inicializa��o
		viewPager = (ViewPager) findViewById(R.id.rolagem);
		actionBar = getActionBar();
		adapt = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(adapt);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adiciona abas
		for (String tab_name : abas) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * sele��o de aba do swiping na viewpager
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// seleciona aba na troca
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// mostra view certa na aba
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	/*public void onToggleStar(View v){
		ImageButton star = (ImageButton)findViewById(R.id.favorite);
		star.setImageResource(R.drawable.button_on);
	}*/
	
	public static Context getAppContext() {
        return MainActivity.context;
    }

}