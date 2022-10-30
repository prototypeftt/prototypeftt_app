package com.example.fttapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private BrokerAdapter mBrokerAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Broker> brokers, List<String> keys){
        mContext = context;
        mBrokerAdapter = new BrokerAdapter(brokers, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBrokerAdapter);
    }

    class BrokerItemView extends RecyclerView.ViewHolder {
        private TextView mName;

        private String key;

        public BrokerItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_brokers_recycler, parent, false));

            mName = (TextView) itemView.findViewById((R.id.brokerName));

        }

        public void bind(Broker broker, String key){
            mName.setText(broker.getName());
            this.key = key;
        }
    }

    class BrokerAdapter extends RecyclerView.Adapter<BrokerItemView>{
        private List<Broker> brokerList;
        private List<String> keysList;

        public BrokerAdapter(List<Broker> brokerList, List<String> keysList){
            this.brokerList = brokerList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public BrokerItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BrokerItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BrokerItemView holder, int position) {
            holder.bind(brokerList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return brokerList.size();
        }
    }
}
