package com.androidclass.stockproactivities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidclass.stockproactivities.service.Stock;
import com.androidclass.stockproactivities.service.StockService;

public class StockDetailActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = StockDetailActivity.class.getSimpleName();
    StockService ss;
    StockHolding sh;
    LinearLayout llLess, llMore;
    Button btnLess, btnMore, btnList;
    TextView symbol, name, price, change, high, low, volume, info;
    boolean showMore = true;
    boolean currentState = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_more:
                currentState = false;
                showMoreButton();
                break;
            case R.id.btn_less:
                currentState = true;
                showMoreButton();
                break;
            case R.id.btn_list:
                finish();
                break;
            default:
                break;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        if (savedInstanceState != null) {

            currentState = savedInstanceState.getBoolean(Constants.CURRENT_STATE, true);
            Log.d(TAG, "savedinstance is not null: " + currentState);

        }


        llLess = (LinearLayout) findViewById(R.id.row_less);
        llMore = (LinearLayout) findViewById(R.id.row_more);

        btnList = (Button) findViewById(R.id.btn_list);
        btnMore = (Button) findViewById(R.id.btn_more);
        btnLess = (Button) findViewById(R.id.btn_less);

        btnList.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        btnLess.setOnClickListener(this);


        symbol = (TextView) findViewById(R.id.symbol);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        change = (TextView) findViewById(R.id.change);
        high = (TextView) findViewById(R.id.high);
        low = (TextView) findViewById(R.id.low);
        volume = (TextView) findViewById(R.id.vol);
        info = (TextView) findViewById(R.id.info);


        Bundle b = getIntent().getExtras();

        if (b != null) {
            sh = b.getParcelable(Constants.STOCK);
            showMore = b.getBoolean(Constants.SHOWMORE, true);
        }
        if (sh == null) {
            hideDisplay();
        } else {

            ss = new StockService();
            Stock st = ss.getStock(sh.getSymbol());

            if (st == null) {
                hideDisplay();
            } else {
                displayStock(st);
                showMoreButton();
            }

        }

    }

    private void hideDisplay() {
        Toast.makeText(getBaseContext(), "No Stock Information to Detail", Toast.LENGTH_SHORT).show();
        llLess.setVisibility(View.GONE);
        llMore.setVisibility(View.GONE);

        btnMore.setVisibility(View.GONE);
        btnLess.setVisibility(View.GONE);


    }

    private void displayStock(Stock st) {

        symbol.setText(st.getSymbol());
        name.setText(st.getName());

        Resources res = getResources();


        String textPrice = String.format(res.getString(R.string.price), "" + Util.round(st.getPrice(), 2));
        price.setText(textPrice);

        String textChange = String.format(res.getString(R.string.change), "" + st.getChange());
        change.setText(textChange);
        if (st.getChange() >= 0)
            change.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorGreen));
        else change.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));

        info.setText(st.getInfo());

        String textHigh = String.format(res.getString(R.string.high), "" + Util.round(st.getHigh(), 2));
        high.setText(textHigh);

        String textLow = String.format(res.getString(R.string.low), "" + Util.round(st.getLow(), 2));
        high.setText(textLow);

        String textVolume = String.format(res.getString(R.string.vol), st.getVolume());
        volume.setText(textVolume);


    }

    private void showMoreButton() {
        if (currentState) {
            llLess.setVisibility(View.GONE);
            btnLess.setVisibility(View.GONE);
            llMore.setVisibility(View.VISIBLE);
            btnMore.setVisibility(View.VISIBLE);
        } else {
            llLess.setVisibility(View.VISIBLE);
            btnLess.setVisibility(View.VISIBLE);
            llMore.setVisibility(View.GONE);
            btnMore.setVisibility(View.GONE);

        }
        if (!showMore) {
            btnMore.setVisibility(View.GONE);
            btnLess.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.CURRENT_STATE, currentState);
        Log.d(TAG, "saving state: " + currentState);

    }


}
