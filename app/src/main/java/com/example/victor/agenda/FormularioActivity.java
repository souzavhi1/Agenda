package com.example.victor.agenda;

import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipSession;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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

import java.io.File;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario );
        helper = new FormularioHelper( this );

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra( "aluno" );
        if (aluno != null){
            helper.preencheFormulario(aluno);
        }



        //Botão Salvar que não precisa mais

 //       Button botaoSalvar = findViewById( R.id.formulario_salvar);
 //       botaoSalvar.setOnClickListener( new View.OnClickListener() {
 //           @Override
 //           public void onClick(View v) {
 //               Toast.makeText( FormularioActivity.this, "Botão Clicado!!", Toast.LENGTH_SHORT).show();
 //               finish();
//            }
//        } );

        Button botaoFoto = (Button) findViewById( R.id.formulario_botao_foto );
        botaoFoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                String caminhoFoto = getExternalFilesDir( null ) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile( FormularioActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",arquivoFoto));

                startActivityForResult( intentCamera,123 );
            }

        } );
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
                if (aluno.getId() != null){
                    dao.altera(aluno);
                }
                else {
                    dao.insere( aluno );
                }
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
