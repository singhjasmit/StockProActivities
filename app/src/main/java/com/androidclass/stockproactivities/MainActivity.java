package com.androidclass.stockproactivities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidclass.stockproactivities.service.Stock;
import com.androidclass.stockproactivities.service.StockService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static final int PORT_MGR = 300;
    Map<String, StockHolding> userStocks = new HashMap<String, StockHolding>();
    boolean showMore = true;
    boolean syncEnabled = true;
    StockService ss;
    EditText edSymbol, edQty;
    Button btnAdd;
    TextView txTotalValue;
    ImageView ivSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        edSymbol = (EditText) findViewById(R.id.txt_symbol);
        edQty = (EditText) findViewById(R.id.txt_qty);
        btnAdd = (Button) findViewById(R.id.add_button);
        txTotalValue = (TextView) findViewById(R.id.total);
        ivSetting = (ImageView) findViewById(R.id.setting);


        ivSetting.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             showSettings();

                                         }
                                     }
        );


        btnAdd.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String sym = edSymbol.getText().toString();
                                          String qtyStr = edQty.getText().toString();
                                          int qty = 1;


                                          if ((sym == null) || sym.isEmpty()) {
                                              Toast.makeText(getBaseContext(), "Stock Symbol is required", Toast.LENGTH_SHORT).show();
                                          } else {
                                              Stock st = ss.getStock(sym.toUpperCase());
                                              if (st != null) {

                                                  if ((qtyStr != null)) {
                                                      qty = Integer.parseInt(qtyStr);
                                                      if (qty < 1) qty = 1;
                                                  }

                                                  StockHolding sh = userStocks.get(sym.toUpperCase());
                                                  if (sh != null)
                                                      sh.setQty(sh.getQty() + qty);
                                                  else
                                                      sh = new StockHolding(sym.toUpperCase(), qty);
                                                  userStocks.put(sh.getSymbol(), sh);
                                                  updateRows();

                                              } else {
                                                  Toast.makeText(getBaseContext(), "Stock Symbol is not available", Toast.LENGTH_SHORT).show();

                                              }
                                          }

                                      }
                                  }

        );
        ss = new StockService();
        initializeUserPortfolio();
        updateRows();


    }


    private void initializeUserPortfolio() {
        userStocks.put("MSFT", new StockHolding("MSFT", 5));

    }

    private void updateRows() {

        LayoutInflater layoutInflator = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout insertPoint = (LinearLayout) findViewById(R.id.ll_content);
        insertPoint.removeAllViewsInLayout();
        Resources res = getResources();

        List views = new ArrayList();

        double totalValue = 0;

        for (Map.Entry<String, StockHolding> entry : userStocks.entrySet()) {

            StockHolding sh = entry.getValue();

            View view = layoutInflator.inflate(R.layout.stock_row, null);

            TextView txQty = (TextView) view.findViewById(R.id.row_qty);
            TextView txPrice = (TextView) view.findViewById(R.id.row_price);
            TextView txChange = (TextView) view.findViewById(R.id.row_change);
            TextView txName = (TextView) view.findViewById(R.id.row_name);
            TextView txValue = (TextView) view.findViewById(R.id.row_value);
            Button btnDel = (Button) view.findViewById(R.id.row_button);
            Stock st = ss.getStock(sh.symbol);


            String textQty = String.format(res.getString(R.string.qty), sh.qty, sh.symbol);
            txQty.setText(textQty);

            String textPrice = String.format(res.getString(R.string.price), "" + Util.round(st.getPrice(), 2));
            txPrice.setText(textPrice);

            String textChange = String.format(res.getString(R.string.change), "" + st.getChange());
            txChange.setText(textChange);
            if (st.getChange() >= 0)
                txChange.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorGreen));
            else
                txChange.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorAccent));

            txName.setText(st.getName());

            String textValue = String.format(res.getString(R.string.value), "" + Util.round(st.getPrice() * sh.qty, 2));
            txValue.setText(textValue);

            totalValue = totalValue + (st.getPrice() * sh.qty);

            btnDel.setTag(sh);

            btnDel.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              StockHolding sth = (StockHolding) view.getTag();
                                              if (userStocks.containsKey(sth.getSymbol())) {
                                                  userStocks.remove(sth.getSymbol());
                                                  Toast.makeText(getBaseContext(), "Deleting Stock", Toast.LENGTH_SHORT).show();
                                                  updateRows();
                                              } else {
                                                  Toast.makeText(getBaseContext(), "Stock Not Deleted", Toast.LENGTH_SHORT).show();
                                              }

                                          }
                                      }

            );

            view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            view.setTag(sh);

            view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            StockHolding sh = (StockHolding) view.getTag();
                                            showDetail(sh);

                                        }
                                    }
            );

            view.setFocusable(false);

            views.add(view);
        }


        for (int i = 0; i < views.size(); i++)
            insertPoint.addView((View) views.get(i));

        txTotalValue.setText(res.getString(R.string.total_value, "" + Util.round(totalValue, 2)));

    }


    private void showDetail(StockHolding sh) {

        Intent i = new Intent(this, StockDetailActivity.class);
        Bundle b = new Bundle();
        b.putParcelable(Constants.STOCK, sh);
        b.putBoolean(Constants.SHOWMORE, showMore);
        i.putExtras(b);
        startActivity(i);


    }

    private void showSettings() {

        Intent i = new Intent(this, SettingActivity.class);
        Bundle b = new Bundle();
        b.putBoolean(Constants.SHOWMORE, showMore);
        b.putBoolean(Constants.SYNC_ON, syncEnabled);

        i.putExtras(b);
        startActivityForResult(i, PORT_MGR);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PORT_MGR) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                showMore = data.getBooleanExtra(Constants.SHOWMORE, true);
                syncEnabled = data.getBooleanExtra(Constants.SYNC_ON, true);

            }
        }
    }

}





