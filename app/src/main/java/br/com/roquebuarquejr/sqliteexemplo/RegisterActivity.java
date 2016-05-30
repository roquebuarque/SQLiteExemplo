package br.com.roquebuarquejr.sqliteexemplo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.roquebuarquejr.sqliteexemplo.data.model.ClientModel;

/**
 * Created by roquebuarque on 29/05/16.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText txName, txEmail;
    private String name, email;
    private ClientModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txName = (EditText) findViewById(R.id.tx_name);
        txEmail = (EditText) findViewById(R.id.tx_email);

        model = new ClientModel(getApplicationContext());
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });
    }

    private void insert() {
        email = txEmail.getText().toString();
        name = txName.getText().toString();
        String retorno = model.insertClient(name, email);
        Toast.makeText(getApplicationContext(), retorno, Toast.LENGTH_LONG).show();
        finish();

    }
}
