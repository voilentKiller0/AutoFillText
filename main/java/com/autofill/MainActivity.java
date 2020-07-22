package com.chandan.autofill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public void hideKeyboard(View v){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Faker faker = new Faker(new Locale("en-IND"));
        final String[]name = new String[100];
        for (int i = 0 ; i < 100 ; i++){
            name[i]=faker.name().firstName();
        }
        final ArrayList<String> arrayList = new ArrayList<>();
        final EditText editText = findViewById(R.id.editText);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList.clear();
                for (String i : name){
                    if((i.toLowerCase()).contains(s.toString().toLowerCase()))
                        arrayList.add(i);
                }
                if(TextUtils.isEmpty(editText.getText())){
                    arrayList.clear();
                }
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(arrayList.get(position));
                arrayList.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText() != null)
                    Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Enter Some Name", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
        findViewById(R.id.constraintLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });
    }
}