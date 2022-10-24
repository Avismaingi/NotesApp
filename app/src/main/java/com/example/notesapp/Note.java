package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;

import static com.example.notesapp.MainActivity.arrayAdapter;
import static com.example.notesapp.MainActivity.noteTitle;
import static com.example.notesapp.MainActivity.noteMaterial;
import static com.example.notesapp.MainActivity.sharedPreferences;

public class Note extends AppCompatActivity {

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        EditText title = (EditText) findViewById(R.id.noteTitle);
        EditText content = (EditText) findViewById(R.id.noteMaterial);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -2);

        if (id >= 0) {
            title.setText(noteTitle.get(id));
            content.setText(noteMaterial.get(id));
        } else if (id == -1) {
            noteTitle.add("");
            noteMaterial.add("");
            id = noteTitle.size() - 1;
        }


        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (id >= 0) {
                    noteTitle.set(id, String.valueOf(charSequence));
                    try {
                        sharedPreferences.edit().putString("noteTitle", ObjectSerializer.serialize(noteTitle)).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (id >= 0) {
                    noteMaterial.set(id, String.valueOf(charSequence));
                    try {
                        sharedPreferences.edit().putString("noteMaterial", ObjectSerializer.serialize(noteMaterial)).apply();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}