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
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.agenda.dao.AlunoDAO;
import com.example.victor.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario );

        helper = new FormularioHelper( this );

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
                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO( this );
                dao.Insere(aluno);
                dao.close();

                Toast.makeText( FormularioActivity.this, "Aluno " + aluno.getNome() + " Salvo!!", Toast.LENGTH_SHORT).show();


//                Antes de criar o FormularioHelper
//                EditText campoNome = findViewById( R.id.formulario_nome );
//                String nome = campoNome.getText().toString();
//
//                EditText campoEndereco = findViewById( R.id.formulario_endereco );
//                String endereco = campoEndereco.getText().toString();
//
//                EditText campoFone = findViewById( R.id.formulario_fone );
//                String fone = campoFone.getText().toString();
//
//                EditText campoSite = findViewById( R.id.formulario_site );
//                String site = campoSite.getText().toString();

                finish();
                break;
        }

        return super.onOptionsItemSelected( item );
    }
}
