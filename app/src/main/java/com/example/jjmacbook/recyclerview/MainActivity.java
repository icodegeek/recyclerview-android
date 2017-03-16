package com.example.jjmacbook.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Para utilizar el layout como tipo Linear List
        mLayoutManager = new LinearLayoutManager(this);

        //Para utilizar el layout como tipo Grid
        mLayoutManager = new GridLayoutManager(this, 2);

        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                deleteName(position);
            }
        });

        //Método para establecer que el layout va a tener un tamaño fijo y mejorar así el rendimiento
        mRecyclerView.setHasFixedSize(true);

        //Método para mostrar animación en el recyclerview
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Juan Jesus");
            add("Jose");
            add("Roberto");
            add("Susana");
            add("Elena");
        }};
    }

    //Método para añadir nombre desde el option menú
    private void addName(int position) {
        names.add(position, "New name " + (++count));

        //Se le informa al adaptador de la introducción de datos nuevos
        mAdapter.notifyItemInserted(position);

        mLayoutManager.scrollToPosition(position);
    }

    //Método para borrar nombre desde el option menú
    private void deleteName(int position){
        names.remove(position);

        //Se le informa al adaptador de la eliminación de uno de los datos
        mAdapter.notifyItemRemoved(position);
    }
}
