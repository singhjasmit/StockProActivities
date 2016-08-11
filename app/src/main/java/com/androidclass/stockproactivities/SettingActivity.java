package com.androidclass.stockproactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = SettingActivity.class.getSimpleName();
    Button btnSave;
    CheckBox cbSync, cbShow;
    boolean showMore = true;
    boolean syncEnabled = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:

                Intent output = new Intent();
                showMore = cbShow.isChecked();
                syncEnabled = cbSync.isChecked();

                output.putExtra(Constants.SYNC_ON, syncEnabled);
                output.putExtra(Constants.SHOWMORE, showMore);

                Log.d(TAG, " sending  showmore = " + showMore);

                setResult(RESULT_OK, output);

                finish();
                break;
            default:
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        cbSync = (CheckBox) findViewById(R.id.checkbox_sync);
        cbShow = (CheckBox) findViewById(R.id.checkbox_showmore);


        Bundle b = getIntent().getExtras();

        if (b != null) {

            showMore = b.getBoolean(Constants.SHOWMORE, true);
            syncEnabled = b.getBoolean(Constants.SYNC_ON, true);

            Log.d(TAG, " got from parent activity  showmore = " + showMore);

        }

        showCheckBoxes();


    }

    private void showCheckBoxes() {
        cbShow.setChecked(showMore);
        cbSync.setChecked(syncEnabled);
    }


}
