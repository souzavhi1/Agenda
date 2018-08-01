package com.example.victor.agenda;

import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario );

        //Botão Salvar que não precisa mais

 //       Button botaoSalvar = findViewById( R.id.formulario_salvar);
 //       botaoSalvar.setOnClickListener( new View.OnClickListener() {
 //           @Override
 //           public void onClick(View v) {
 //               Toast.makeText( FormularioActivity.this, "Botão Clicado!!", Toast.LENGTH_SHORT).show();
 //               finish();
//            }
//        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);


        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Toast.makeText( FormularioActivity.this, "Aluno Salvo!!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected( item );
    }
}
