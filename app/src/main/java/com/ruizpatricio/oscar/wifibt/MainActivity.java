package com.ruizpatricio.oscar.wifibt;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button wifi, bt;
    private TextView result;

    private WifiManager wifiManager;
    private BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        getViews();
        setListeners();
    }

    private void getViews() {
        wifi = (Button)findViewById(R.id.wifi_button);
        bt = (Button)findViewById(R.id.bt_button);

        result = (TextView)findViewById(R.id.result);
    }

    private void setListeners() {
        //enable/disable wifi
        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (wifiManager.getWifiState()) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        //enable wifi
                        wifiManager.setWifiEnabled(true);
                        result.setText(getString(R.string.wifi_enabled));
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        //disable wifi
                        wifiManager.setWifiEnabled(false);
                        result.setText(getString(R.string.wifi_disabled));
                        break;
                    default:
                        result.setText(getString(R.string.wifi_error));
                }
            }
        });

        //enable/disable bluetooth
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btAdapter.isEnabled()) {
                    //disable bluetooth
                    btAdapter.disable();
                    result.setText(getString(R.string.bt_disabled));
                } else if (!btAdapter.isEnabled()) {
                    //enable bluetooth
                    btAdapter.enable();
                    result.setText(getString(R.string.bt_enabled));
                }
            }
        });
    }
}
