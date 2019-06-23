package com.example.whychat;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterHistorico extends BaseAdapter {
    private final List<Historico> cursos;
    private final Activity act;

    public AdapterHistorico(List<Historico> cursos, Activity act) {
        this.cursos = cursos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Object getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.item_lista, parent, false);

        Historico historico = cursos.get(position);

        TextView periodo = (TextView)
                view.findViewById(R.id.itemPeriodo);
        TextView pontos = (TextView)
                view.findViewById(R.id.itemPontos);
        TextView acertos = (TextView)
                view.findViewById(R.id.itemAcertos);

        acertos.setText("Quantidade Acertos: "+historico.getAcertos());
        pontos.setText("Pontuação: "+historico.getPontuacao());
        periodo.setText(historico.getPeriodo());

        return view;
    }
}
