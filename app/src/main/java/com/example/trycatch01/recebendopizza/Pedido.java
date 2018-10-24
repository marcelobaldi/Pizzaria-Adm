package com.example.trycatch01.recebendopizza;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pedido {
    String id;
    boolean aceito;
    String status;
    private static final String TAG = "Pedido";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenciaPedido;


    public Pedido(String id,String status) {
        Log.i(TAG,"Iniciando Carrinho");
        this.aceito = false;
        this.id = id;
        this.status = "Enviando Pedido";
    }


    public void aceitar() {
        boolean aceito = true;
        referenciaPedido = database.getReference().child("Pedidos").child( this.id );
        referenciaPedido.child("Status").setValue( "Aceito" );
    }
}

