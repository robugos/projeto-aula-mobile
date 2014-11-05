package br.com.aula;

import org.w3c.dom.Text;

import br.com.aula.dominio.Curso;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class PredioActivity extends Activity {
	
	public int idCurso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_predio);
		
		TextView curso = (TextView) findViewById(R.id.idCurso);
		curso.setText(idCurso);
		
	}
	
	
	public void setIdCurso(int idCurso){
		this.idCurso = idCurso;
	}
	
	public int getIdCurso(){
		return idCurso;
	}
}
