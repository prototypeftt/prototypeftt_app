package com.example.fttapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private BrokerAdapter mBrokerAdapter;
    private AssetAdapter mAssetAdapter;

    public void setConfigBroker(RecyclerView recyclerView, Context context, List<Broker> brokers, List<String> brokerKeys){
        mContext = context;
        mBrokerAdapter = new BrokerAdapter(brokers, brokerKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBrokerAdapter);
    }

    public void setConfigAsset(RecyclerView recyclerView, Context context, List<Asset> assets, List<String> assetKeys){
        mContext = context;
        mAssetAdapter = new AssetAdapter(assets, assetKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAssetAdapter);
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
            mName.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, BrokerScreen.class);
                intent.putExtra("broker", broker);
                mContext.startActivity(intent);
            });
        }
    }

    class AssetItemView extends RecyclerView.ViewHolder {
        private TextView mAssetName, mAssetPrediction, mAssetPrice;

        private String key;

        public AssetItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_assets_recycler, parent, false));

            mAssetName = (TextView) itemView.findViewById((R.id.assetName));
            mAssetPrediction = (TextView) itemView.findViewById((R.id.assetPrediction));
            mAssetPrice = (TextView) itemView.findViewById((R.id.assetPrice));
            parent.setOnClickListener(view -> Toast.makeText(mContext, "This would go", Toast.LENGTH_LONG).show());
        }

        public void bind(Asset asset, String key){
            mAssetName.setText(asset.getAssetName());
            mAssetPrediction.setText(asset.getAssetPrediction());
            mAssetPrice.setText(asset.getClosePrice());
            this.key = key;
            mAssetName.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, AssetScreen.class);
                intent.putExtra("asset", asset);
                mContext.startActivity(intent);
            });
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

    class AssetAdapter extends RecyclerView.Adapter<AssetItemView>{
        private List<Asset> assetList;
        private List<String> keysList;

        public AssetAdapter(List<Asset> assetList, List<String> keysList){
            this.assetList = assetList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public AssetItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AssetItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AssetItemView holder, int position) {
            holder.bind(assetList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return assetList.size();
        }
    }
}
