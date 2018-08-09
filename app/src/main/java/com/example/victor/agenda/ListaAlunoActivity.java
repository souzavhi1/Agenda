package com.example.victor.agenda;

import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victor.agenda.dao.AlunoDAO;
import com.example.victor.agenda.modelo.Aluno;

import java.util.List;

public class ListaAlunoActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_aluno );

        listaAlunos = (ListView) findViewById( R.id.lista_alunos );

        listaAlunos.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition( position );

                Intent intentVaiProFormulario = new Intent( ListaAlunoActivity.this, FormularioActivity.class );
                intentVaiProFormulario.putExtra( "aluno", aluno );
                startActivity( intentVaiProFormulario );


            }
        } );

        listaAlunos.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        } );


        Button novoAluno = (Button) findViewById( R.id.novo_aluno );
        novoAluno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent( ListaAlunoActivity.this, FormularioActivity.class );
                startActivity( intentVaiProFormulario );
            }
        } );

        registerForContextMenu( listaAlunos );
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO( this );
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

//        String[] alunos ={"Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe","Daniel","Ronaldo","Jeferson","Felipe"};

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>( this,android.R.layout.simple_list_item_1,alunos );
        listaAlunos.setAdapter( adapter );
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition( info.position );

        MenuItem itemSite = menu.add( "Visitar site" );
            Intent intentSite = new Intent( Intent.ACTION_VIEW);

            String site = aluno.getSite();
            if (!site.startsWith( "http://" )){
                site = "http://" + site;
            }
            intentSite.setData( Uri.parse(site));
            itemSite.setIntent( intentSite );

        MenuItem deletar = menu.add( "Deletar" );
        deletar.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlunoDAO dao = new AlunoDAO( ListaAlunoActivity.this );

                dao.deleta(aluno);
                dao.close();
                Toast.makeText( ListaAlunoActivity.this, "Aluno " + aluno.getNome() + " deletado!", Toast.LENGTH_SHORT).show();
                carregaLista();

                return false;
            }
        } );


    }

}
