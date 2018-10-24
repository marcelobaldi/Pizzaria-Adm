package com.example.trycatch01.recebendopizza;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.PedidosViewHolder> {
    ArrayList<Pedido> pedidos;
    Context context;

    public AdapterPedidos(ArrayList<Pedido> pedidos, Context context){
        this.pedidos = pedidos;
        this.context = context;
    }

    @Override
    public PedidosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter_pedidos, parent, false);

        PedidosViewHolder viewHolder = new PedidosViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PedidosViewHolder holder, int position) {
        final Pedido pedido = pedidos.get(position);
        String pedidoKey = pedido.id;

        holder.txPedido.setText( pedidoKey.substring(0,5 ) );

        holder.btConfirmar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Pedidos","Confirmado");
                pedido.aceitar();
            }
        } );
    }

    @Override
    public int getItemCount() {
        Log.e("TamanhoTEste",String.valueOf(pedidos.size()));
        return pedidos.size();
    }

    class PedidosViewHolder extends RecyclerView.ViewHolder{

        TextView txPedido;
        Button btConfirmar;

        public PedidosViewHolder(View itemView) {
            super( itemView );
            txPedido = (TextView) itemView.findViewById( R.id.TXNome);
            btConfirmar = (Button) itemView.findViewById( R.id.btConfirmar);
        }


    }
}
