package br.com.roquebuarquejr.sqliteexemplo;

import android.app.backup.BackupHelper;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.roquebuarquejr.sqliteexemplo.data.DataBaseHelper;
import br.com.roquebuarquejr.sqliteexemplo.data.model.ClientModel;

/**
 * Created by roquebuarque on 29/05/16.
 */
public class UpdateDeleteActivity extends AppCompatActivity {

    private String id;
    private Cursor cursor;
    private ClientModel model;

    private EditText txName, txEmail;
    private Button btnDelete, btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);

        txName = (EditText) findViewById(R.id.tx_name);
        txEmail = (EditText) findViewById(R.id.tx_email);

        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(Integer.parseInt(id));
            }
        });
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(Integer.parseInt(id));
            }
        });

        id = getIntent().getStringExtra("id");
        model = new ClientModel(getApplicationContext());
        cursor = model.loadClientById(Integer.parseInt(id));

        txName.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.NAME)));
        txEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.EMAIL)));

    }

    private void delete(int id) {
        String result = model.delete(id);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        finish();
    }

    private void update(int id) {
        String name = txName.getText().toString();
        String email = txEmail.getText().toString();
        String result = model.udate(id, name, email);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        finish();
    }
}
