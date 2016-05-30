package br.com.roquebuarquejr.sqliteexemplo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.roquebuarquejr.sqliteexemplo.data.DataBaseHelper;
import br.com.roquebuarquejr.sqliteexemplo.data.model.ClientModel;

public class MainActivity extends AppCompatActivity {

    private ListView ls;
    private ClientModel model;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ls = (ListView) findViewById(R.id.listView);
        model = new ClientModel(getApplicationContext());

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idUser;
                cursor.moveToPosition(position);
                idUser = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.ID));
                Intent i = new Intent(MainActivity.this, UpdateDeleteActivity.class);
                i.putExtra("id", idUser);
                startActivity(i);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }


    private void listClients() {
        cursor = model.listAllClient();
        String[] campos = new String[]{DataBaseHelper.ID, DataBaseHelper.NAME, DataBaseHelper.EMAIL};
        int[] idViews = new int[]{R.id.lb_id, R.id.lb_name, R.id.lb_email};
        adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_list_view, cursor, campos, idViews, 0);

        ls.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listClients();
    }
}
