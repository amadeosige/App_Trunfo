package com.example.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.myapplication.data.model.Compra;
import com.example.myapplication.uteis.RecyclerViewOnClickListenerHack;
import com.example.myapplication.uteis.Util;

import java.util.List;

import br.android.com.lembretes.R;


public class LembreteAdapter extends RecyclerView.Adapter<LembreteAdapter.ViewHolder> {

    private static final String TAG = LembreteAdapter.class.getSimpleName();
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    private List<Compra> list;

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public LembreteAdapter(List<Compra> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textViewLembrete;
        public TextView textViewData;
        public TextView textViewId;
        public TextView textViewProdutosList;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewLembrete = (TextView) itemView.findViewById(R.id.textViewLembrete);
            textViewData = (TextView) itemView.findViewById(R.id.textViewData);
            textViewId = (TextView) itemView.findViewById(R.id.textViewId);
            textViewProdutosList = (TextView) itemView.findViewById(R.id.produtosList);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(view, getPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.lembrete_item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Compra item = list.get(position);
        holder.textViewLembrete.setText(item.getNome());
        holder.textViewData.setText(Util.format(item.getDataHora(), "dd/MM/yyyy"));
        holder.textViewId.setText(String.valueOf(item.getId()));
        holder.textViewProdutosList.setText(AjustarListaProdutos(item.getConteudo()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String AjustarListaProdutos(String conteudo){

        conteudo = conteudo.replace("null", "");
        conteudo = conteudo.replace("%", "\n");
        conteudo = conteudo.replace("/", " - R$");

      return conteudo;
    }
}