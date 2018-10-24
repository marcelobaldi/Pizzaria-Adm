package com.example.trycatch01.recebendopizza;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pedido> pedidos;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    RecyclerView recyclerView;
    AdapterPedidos adapterPedidos;
    ArrayList<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        listaPedidos = new ArrayList<Pedido>(  );
        DatabaseReference referenciaPedidos = database.getReference().child( "Pedidos" );

        listaPedidos.add( new Pedido( "Teste1","ok" ) );
        adapterPedidos = new AdapterPedidos( listaPedidos,this);
        adapterPedidos.pedidos = listaPedidos;

        recyclerView = (RecyclerView) findViewById( R.id.recyclerView );


        //Criando o manager do recycleView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapterPedidos);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String id = dataSnapshot.getKey();
                    Log.e( "IDteste",id );
                    String status = dataSnapshot.child( "Status" ).getValue().toString();

                    Pedido pedidonovo = new Pedido(id,status);

                    listaPedidos.add( pedidonovo );
                    Log.e( "Tamanho",String.valueOf(listaPedidos.size()));
                    adapterPedidos.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        referenciaPedidos.addChildEventListener( childEventListener );

    }
}
