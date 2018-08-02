package com.example.victor.agenda;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.victor.agenda.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoFone;
    private final EditText campoSite;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){

        campoNome = activity.findViewById( R.id.formulario_nome );
        campoEndereco = activity.findViewById( R.id.formulario_endereco );
        campoFone = activity.findViewById( R.id.formulario_fone );
        campoSite = activity.findViewById( R.id.formulario_site );
        campoNota= activity.findViewById( R.id.formulario_nota );
    }

    public Aluno pegaAluno() {

        Aluno aluno = new Aluno();
        aluno.setNome( campoNome.getText().toString());
        aluno.setEndereco( campoEndereco.getText().toString());
        aluno.setFone( campoFone.getText().toString());
        aluno.setSite( campoSite.getText().toString());
        aluno.setNota( (double) campoNota.getProgress() );
        return aluno;
    }
}
