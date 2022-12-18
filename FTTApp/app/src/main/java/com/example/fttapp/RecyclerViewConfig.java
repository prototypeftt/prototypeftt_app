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
    private MyStockAdapter mStockAdapter;
    private MyCryptoAdapter myCryptoAdapter;
    private MessageAdapter mMessageAdapter;
    private ReviewAdapter mReviewAdapter;

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

    public void setConfigMyStock(RecyclerView recyclerView, Context context, List<Asset> stocks, List<String> stockKeys){
        mContext = context;
        mStockAdapter = new MyStockAdapter(stocks, stockKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStockAdapter);
    }

    public void setConfigMyCrypto(RecyclerView recyclerView, Context context, List<Asset> cryptos, List<String> cryptoKeys){
        mContext = context;
        myCryptoAdapter = new MyCryptoAdapter(cryptos, cryptoKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myCryptoAdapter);
    }

    public void setConfigMessage(RecyclerView recyclerView, Context context, List<Message> messages, List<String> messageKeys){
        mContext = context;
        mMessageAdapter = new MessageAdapter(messages, messageKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMessageAdapter);
    }

    public void setConfigReview(RecyclerView recyclerView, Context context, List<Review> reviews, List<String> reviewKeys){
        mContext = context;
        mReviewAdapter = new ReviewAdapter(reviews, reviewKeys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mReviewAdapter);
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

    class MyStockItemView extends RecyclerView.ViewHolder {
        private TextView mAssetName, mAssetQuantity, mAssetPrice;

        private String key;

        public MyStockItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_stock_recycler, parent, false));

            mAssetName = (TextView) itemView.findViewById((R.id.stockName));
            mAssetQuantity = (TextView) itemView.findViewById((R.id.stockQuantity));
            mAssetPrice = (TextView) itemView.findViewById((R.id.stockPrice));
        }

        public void bind(Asset asset, String key){
            mAssetName.setText(asset.getAssetName());
            mAssetQuantity.setText(asset.getQty());
            mAssetPrice.setText(asset.getClosePrice());
            this.key = key;
            mAssetName.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, AssetScreen.class);
                intent.putExtra("asset", asset);
                mContext.startActivity(intent);
            });
        }
    }

    class MyCryptoItemView extends RecyclerView.ViewHolder {
        private TextView mAssetName, mAssetQuantity, mAssetPrice;

        private String key;

        public MyCryptoItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_crypto_recycler, parent, false));

            mAssetName = (TextView) itemView.findViewById((R.id.cryptoName));
            mAssetQuantity = (TextView) itemView.findViewById((R.id.cryptoQuantity));
            mAssetPrice = (TextView) itemView.findViewById((R.id.cryptoPrice));
        }

        public void bind(Asset asset, String key){
            mAssetName.setText(asset.getAssetName());
            mAssetQuantity.setText(asset.getQty());
            mAssetPrice.setText(asset.getClosePrice());
            this.key = key;
            mAssetName.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, AssetScreen.class);
                intent.putExtra("asset", asset);
                mContext.startActivity(intent);
            });
        }
    }

    class MessageItemView extends RecyclerView.ViewHolder {
        private TextView mMessagerName, mMessagerText;

        private String key;

        public MessageItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_messages_recycler, parent, false));

            mMessagerName = (TextView) itemView.findViewById((R.id.messageSenderName));
            mMessagerText = (TextView) itemView.findViewById((R.id.messageSenderText));
        }

        public void bind(Message message, String key){
            mMessagerName.setText(message.getName());
            mMessagerText.setText(message.getMessage());
            this.key = key;
        }
    }

    class ReviewItemView extends RecyclerView.ViewHolder {
        private TextView mReviewMark, mReviewText;

        private String key;

        public ReviewItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.aux_reviews_recycler, parent, false));

            mReviewMark = (TextView) itemView.findViewById((R.id.marksGiven));
            mReviewText = (TextView) itemView.findViewById((R.id.reviewText));
        }

        public void bind(Review review, String key){
            mReviewMark.setText(review.getReviewMarks());
            mReviewText.setText(review.getReviewText());
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

    class MyStockAdapter extends RecyclerView.Adapter<MyStockItemView>{
        private List<Asset> stockList;
        private List<String> keysList;

        public MyStockAdapter(List<Asset> stockList, List<String> keysList){
            this.stockList = stockList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public MyStockItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyStockItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyStockItemView holder, int position) {
            holder.bind(stockList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return stockList.size();
        }
    }

    class MyCryptoAdapter extends RecyclerView.Adapter<MyCryptoItemView>{
        private List<Asset> cryptoList;
        private List<String> keysList;

        public MyCryptoAdapter(List<Asset> stockList, List<String> keysList){
            this.cryptoList = stockList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public MyCryptoItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyCryptoItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyCryptoItemView holder, int position) {
            holder.bind(cryptoList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return cryptoList.size();
        }
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageItemView>{
        private List<Message> messageList;
        private List<String> keysList;

        public MessageAdapter(List<Message> messageList, List<String> keysList){
            this.messageList = messageList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public MessageItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MessageItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MessageItemView holder, int position) {
            holder.bind(messageList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }
    }

    class ReviewAdapter extends RecyclerView.Adapter<ReviewItemView>{
        private List<Review> reviewList;
        private List<String> keysList;

        public ReviewAdapter(List<Review> reviewList, List<String> keysList){
            this.reviewList = reviewList;
            this.keysList = keysList;
        }

        @NonNull
        @Override
        public ReviewItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReviewItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReviewItemView holder, int position) {
            holder.bind(reviewList.get(position), keysList.get(position));
        }

        @Override
        public int getItemCount() {
            return reviewList.size();
        }
    }
}
