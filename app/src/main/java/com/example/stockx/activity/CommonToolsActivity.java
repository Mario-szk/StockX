package com.example.stockx.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.StockXUitls;
import com.example.stockx.R;
import com.google.android.material.textfield.TextInputEditText;

public class CommonToolsActivity extends AppCompatActivity {

    //成本价计算
    private TextInputEditText edCurrentCostPrice;
    private TextInputEditText edCurrentAmount;
    private TextInputEditText edAddOpenPrice;
    private TextInputEditText edAddAmount;
    private TextView tvCostPriceTip;
    private TextView tvCostPriceUpdate;

    //百分比计算
    private TextInputEditText edPer2PricePrice;
    private TextInputEditText edPer2PricePer;
    private TextView tvPer2PriceTip;

    //价格转百分比
    private TextInputEditText edPrice2PerFirstPrice;
    private TextInputEditText edPrice2PerSecondPrice;
    private TextView tvPrice2PerTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tools);
        setTitle("常用工具");
        findViews();
        initView();
    }

    private void findViews() {
        edCurrentCostPrice = (TextInputEditText) findViewById(R.id.ed_current_cost_price);
        edCurrentAmount = (TextInputEditText) findViewById(R.id.ed_current_amount);
        edAddOpenPrice = (TextInputEditText) findViewById(R.id.ed_add_open_price);
        edAddAmount = (TextInputEditText) findViewById(R.id.ed_add_amount);
        tvCostPriceTip = (TextView) findViewById(R.id.tv_cost_price_tip);
        tvCostPriceUpdate = (TextView) findViewById(R.id.tv_cost_price_update);
        edPer2PricePrice = (TextInputEditText) findViewById(R.id.ed_per_2_price_price);
        edPer2PricePer = (TextInputEditText) findViewById(R.id.ed_per_2_price_per);
        tvPer2PriceTip = (TextView) findViewById(R.id.tv_per_2_price_tip);
        edPrice2PerFirstPrice = (TextInputEditText) findViewById(R.id.ed_price_2_per_first_price);
        edPrice2PerSecondPrice = (TextInputEditText) findViewById(R.id.ed_price_2_per_second_price);
        tvPrice2PerTip = (TextView) findViewById(R.id.tv_price_2_per_tip);
    }

    private void initView() {
        edCurrentCostPrice.addTextChangedListener(costPriceTextWatcher);
        edCurrentAmount.addTextChangedListener(costPriceTextWatcher);
        edAddOpenPrice.addTextChangedListener(costPriceTextWatcher);
        edAddAmount.addTextChangedListener(costPriceTextWatcher);
        tvCostPriceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((tvCostPriceTip.getTag(R.id.tag_amount) == null) || (tvCostPriceTip.getTag(R.id.tag_cost_price) == null)) {
                    return;
                }
                double costPrice = (double) tvCostPriceTip.getTag(R.id.tag_cost_price);
                double amount = (double) tvCostPriceTip.getTag(R.id.tag_amount);

                edCurrentCostPrice.setText(StockXUitls.twoDeic(costPrice));
                edCurrentAmount.setText(StockXUitls.twoDeic(amount));
                edAddOpenPrice.setText("");
                edAddAmount.setText("");
                edAddOpenPrice.requestFocus();
            }
        });

        edPer2PricePrice.addTextChangedListener(per2PriceTextWatcher);
        edPer2PricePer.addTextChangedListener(per2PriceTextWatcher);

        edPrice2PerFirstPrice.addTextChangedListener(price2PerTextWatcher);
        edPrice2PerSecondPrice.addTextChangedListener(price2PerTextWatcher);
    }

    TextWatcher costPriceTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String curCostPriceStr = edCurrentCostPrice.getText().toString();
            String curAmountStr = edCurrentAmount.getText().toString();
            String curAddOpenPriceStr = edAddOpenPrice.getText().toString();
            String curAddAmountStr = edAddAmount.getText().toString();

            if (TextUtils.isEmpty(curCostPriceStr) || TextUtils.isEmpty(curAmountStr) || TextUtils.isEmpty(curAddOpenPriceStr) || TextUtils.isEmpty(curAddAmountStr)) {
                tvCostPriceTip.setVisibility(View.INVISIBLE);
                tvCostPriceUpdate.setVisibility(View.INVISIBLE);
                return;
            } else {
                tvCostPriceTip.setVisibility(View.VISIBLE);
                tvCostPriceUpdate.setVisibility(View.VISIBLE);
            }

            double curCostPrice = Double.parseDouble(curCostPriceStr);
            double curAmount = Double.parseDouble(curAmountStr);
            double curAddOpenPrice = Double.parseDouble(curAddOpenPriceStr);
            double curAddAmount = Double.parseDouble(curAddAmountStr);

            double result = (curCostPrice * curAmount + curAddOpenPrice * curAddAmount) / (curAmount + curAddAmount);
            tvCostPriceTip.setTag(R.id.tag_cost_price, result);
            tvCostPriceTip.setTag(R.id.tag_amount, curAmount + curAddAmount);
            tvCostPriceTip.setText("加仓后的成本价是: " + StockXUitls.twoDeic(result));
        }
    };

    TextWatcher per2PriceTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String curCostPricePerStr = edPer2PricePrice.getText().toString();
            String perStr = edPer2PricePer.getText().toString();
            if (TextUtils.isEmpty(curCostPricePerStr) || TextUtils.isEmpty(perStr)) {
                tvPer2PriceTip.setVisibility(View.INVISIBLE);
                return;
            } else {
                tvPer2PriceTip.setVisibility(View.VISIBLE);
            }

            double curCostPricePer = Double.parseDouble(curCostPricePerStr);
            double per = Double.parseDouble(perStr);
            double price = curCostPricePer;
            if (per > 0) {
                price = curCostPricePer + curCostPricePer * per / 100;
            } else {
                price = curCostPricePer - curCostPricePer * per / 100;
            }
            tvPer2PriceTip.setText("现成本价" + ((per > 0) ? "以上" : "以下") + "个点的价格是: " + StockXUitls.twoDeic(price));
        }
    };

    TextWatcher price2PerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String firstPriceStr = edPrice2PerFirstPrice.getText().toString();
            String secondPriceStr = edPrice2PerSecondPrice.getText().toString();
            if (TextUtils.isEmpty(firstPriceStr) || TextUtils.isEmpty(secondPriceStr)) {
                tvPrice2PerTip.setVisibility(View.INVISIBLE);
                return;
            } else {
                tvPrice2PerTip.setVisibility(View.VISIBLE);
            }

            double firstPrice = Double.parseDouble(firstPriceStr);
            double secondPrice = Double.parseDouble(secondPriceStr);

            double percent = (secondPrice - firstPrice) / firstPrice;
            tvPrice2PerTip.setText("两个价格之间相差: " + StockXUitls.twoDeic(percent * 100) + "%");
        }
    };
}