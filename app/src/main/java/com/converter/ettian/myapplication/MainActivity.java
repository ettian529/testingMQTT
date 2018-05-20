package com.converter.ettian.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MainActivity extends AppCompatActivity {

    //static String MQTTHOST = "jdbc:mysql://ettiantest.tk/testbulletindb";
    static String MQTTHOST = "tcp://ettiantest.tk:1883";
    static String USERNAME = "temp";
    static String PASSWORD = "123";
    String topicStr= "";

    MqttAndroidClient client;
    String clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void RequestMqtt(){
        clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(MainActivity.this,"Connection Failed",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
