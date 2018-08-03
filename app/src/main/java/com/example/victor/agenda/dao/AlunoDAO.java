package com.example.victor.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.victor.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends SQLiteOpenHelper {


    public AlunoDAO(Context context) {
        super( context, "Ageda",null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos(id INTEGER PRIMAREY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL( sql );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL( sql );
        onCreate( db );


    }

    public void Insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome",aluno.getNome());
        dados.put( "endereco",aluno.getEndereco() );
        dados.put( "fone",aluno.getFone() );
        dados.put( "site", aluno.getSite() );
        dados.put( "note",aluno.getNota() );

        db.insert( "Alunos", null, dados );


    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery( sql, null );

        List<Aluno> alunos = new ArrayList<Aluno>( );

        while (c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId( c.getLong( c.getColumnIndex( "id" ) ));
            aluno.setNome( c.getString( c.getColumnIndex( "nome" ) ));
            aluno.setEndereco( c.getString( c.getColumnIndex( "endereco" ) ));
            aluno.setFone( c.getString( c.getColumnIndex( "fone" ) ));
            aluno.setSite( c.getString( c.getColumnIndex( "site" ) ));
            aluno.setNota( c.getDouble( c.getColumnIndex( "nota" ) ));

            alunos.add( aluno );
        }
        c.close();


        return alunos;
    }
}