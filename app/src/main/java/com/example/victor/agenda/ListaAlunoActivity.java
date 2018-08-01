package com.example.victor.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_aluno );

        String[] alunos ={"Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe"};
        final ListView listaAlunos = (ListView) findViewById( R.id.lista_alunos );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1,alunos );
        listaAlunos.setAdapter( adapter );

        Button novoAluno = (Button) findViewById( R.id.novo_aluno );
        novoAluno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent( ListaAlunoActivity.this, FormularioActivity.class );
                startActivity( intentVaiProFormulario );
            }
        } );

    }
}
