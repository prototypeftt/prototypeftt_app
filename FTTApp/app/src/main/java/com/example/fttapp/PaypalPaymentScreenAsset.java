package com.example.fttapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fttapp.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PaypalPaymentScreenAsset extends AppCompatActivity {
    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Config.PAYPAL_CLIENT_ID);

    Button btnPayNow;
    TextView paymentAmount;

    String amount = "";

    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_type_screen);

        Intent receiveIntent = getIntent();
        amount = receiveIntent.getStringExtra("amount");

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        btnPayNow = findViewById(R.id.PaymentButton);
        paymentAmount = findViewById(R.id.amountToPay);
        paymentAmount.setText(amount + "€");

        btnPayNow.setOnClickListener(view -> processPayment(amount));
    }

    private void processPayment(String amount) {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(amount), "EUR", "Payment", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null){
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, PaymentDetails.class).putExtra("PaymentDetails", paymentDetails).putExtra("PaymentAmount", amount));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
