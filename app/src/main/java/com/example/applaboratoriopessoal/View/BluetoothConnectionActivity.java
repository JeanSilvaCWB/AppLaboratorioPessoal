package com.example.applaboratoriopessoal.View;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.Model.Dados;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

public class BluetoothConnectionActivity {
    private BluetoothListener bluetoothListener;
    private static final String TAG = "BluetoothConnection";
    private static final String appName = "LABORATORIO PESSOAL";
    private static final UUID MY_UUID_INSECURE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final BluetoothAdapter mBluetoothAdapter;
    Context mContext;
    private AcceptThread mInsecureAcceptThread;
    private ConnectThread mConnectThread;
    private BluetoothDevice mDevice;
    private UUID deviceUUID;
    ProgressDialog mProgressDialog;
    private ConnectedThread mConnectedThread;
    private String incomingMessage;

    public BluetoothConnectionActivity(Context mContext, BluetoothListener bluetoothListener) {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mContext = mContext;
        this.bluetoothListener = bluetoothListener;
        start();
    }

    /*Esta thread executa enquanto ouve as conexões recebidas. Funciona como um
    server-side client. É executada até o momento que uma conexão é aceita (ou cancelada).*/
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mServerSocket   ;

        public AcceptThread(){
            BluetoothServerSocket tmp = null;

            try{
                tmp = mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(appName, MY_UUID_INSECURE);
                Log.d(TAG, "AcceptThread: Setting up Server using " + MY_UUID_INSECURE);
            } catch (IOException e){
                Log.e(TAG, "AcceptThread: IOException " + e.getMessage());
            }
            mServerSocket = tmp;
        }

        public void run(){
            Log.d(TAG, "Run: AcceptThread running.");
            BluetoothSocket socket = null;

            try {
                Log.d(TAG, "Run: RFCOM server socket start...");
                socket = mServerSocket.accept();
                Log.d(TAG, "Run: RFCOM server socket accepted connection.");
            } catch (IOException e){
                Log.e(TAG, "AcceptThread: IOException " + e.getMessage());
            }

            if(socket != null){
                connected(socket, mDevice);
            }
            Log.i(TAG,"END mAcceptThread.");
        }

        public void cancel(){
            Log.d(TAG, "Cancel: cancelling AcceptThread.");
            try{
                mServerSocket.close();
            } catch (IOException e){
                Log.e(TAG, "Cancel: close of AcceptThread ServerSocket failed " + e.getMessage());
            }
        }
    }

    /*Esta thread executa enquanto tenta realizar uma conexão com o dispositivo. A thread é toda
    executada, independentemente se a conexão é estabelecida ou não.*/
    private class ConnectThread extends Thread {
        private BluetoothSocket mSocket;

        public ConnectThread(BluetoothDevice device, UUID uuid){
            Log.d(TAG, "ConnectThread: started.");
            mDevice = device;
            deviceUUID = uuid;
        }

        public void run(){
            BluetoothSocket tmp = null;
            Log.i(TAG, "RUN mConnectThread.");

            try{
                Log.d(TAG, "ConnectThread: trying to create InsecureRfcommSocket using UUID: " + MY_UUID_INSECURE);
                tmp = mDevice.createInsecureRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e){
                Log.e(TAG, "ConnectThread: could not create InsecureRfcommSocket " + e.getMessage());
            }

            mSocket = tmp;

            mBluetoothAdapter.cancelDiscovery();

            try{
                mSocket.connect();
                Log.d(TAG, "Run: ConnectThread connected.");
            } catch (IOException e){
                try{
                    mSocket.close();
                    Log.d(TAG, "Run: closed socket");
                } catch (IOException e1){
                    Log.e(TAG, "mConnectThread: run: unable to close connection in socket: " + e1.getMessage());
                }
                Log.d(TAG, "Run: ConnectThread: could not connect to UUID: " + MY_UUID_INSECURE);
            }

            connected(mSocket, mDevice);
        }

        public void cancel(){
            try{
                Log.d(TAG, "Cancel: closing client socket");
                mSocket.close();
            } catch (IOException e){
                Log.e(TAG, "Cancel: close() of mSocket in ConnectThread failed " + e.getMessage());
            }
        }
    }

    /*Inicia a transmissão e recebimento. Especificamente inicia a AcceptThread
    para que comece o modo de escuta (server). Chamada pela Activity onResume().*/
    public synchronized void start(){
        Log.d(TAG, "Start");

        if(mConnectThread != null){
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if(mInsecureAcceptThread == null){
            mInsecureAcceptThread = new AcceptThread();
            mInsecureAcceptThread.start();
        }
    }

    /*AcceptThread é iniciada e fica esperando por uma conexão.
    Então, ConnectThread é iniciada e tenta realizar uma conexão com outros dispositivos AcceptThread.*/
    public void startClient(BluetoothDevice device, UUID uuid){
        Log.d(TAG, "startClient: started");

        mProgressDialog = ProgressDialog.show(mContext, "Connecting Bluetooth", "Please Wait...", true);
        mConnectThread = new ConnectThread(device, uuid);
        mConnectThread.start();
    }

    public class ConnectedThread extends Thread{
        private final BluetoothSocket mSocket;
        private final InputStream mInputStream;
        private final OutputStream mOutputStream;

        public ConnectedThread(BluetoothSocket socket){
            Log.d(TAG, "ConnectThread: starting...");

            mSocket = socket;
            InputStream tmpInputStream = null;
            OutputStream tmpOutputStream = null;

            try {
                mProgressDialog.dismiss();
            } catch (NullPointerException e){
                e.printStackTrace();
            }

            try{
                tmpInputStream = mSocket.getInputStream();
                tmpOutputStream = mSocket.getOutputStream();
            } catch (IOException e){
                e.printStackTrace();
            }

            mInputStream = tmpInputStream;
            mOutputStream = tmpOutputStream;
        }

        public void run(){
            byte[] buffer = new byte[1024];
            int bytes;  //bytes returned from read()

            while (true){
                try{
                    bytes = mInputStream.read(buffer);
                    incomingMessage = new String(buffer, 0, bytes);
                    bluetoothListener.setTextOnTextView(incomingMessage);
                    Log.d(TAG, "InputStream " + incomingMessage);
                } catch (IOException e) {
                    Log.e(TAG, "read: error reading InputStream " + e.getMessage());
                    break;
                }
            }
        }

        public void write(byte[] bytes){
            String text = new String(bytes, Charset.defaultCharset());
            Log.d(TAG, "write: writing to OutputStream " + text);
            try{
                mOutputStream.write(bytes);
            } catch (IOException e){
                Log.e(TAG, "write: error writing to OutputStream " + e.getMessage());
            }
        }
        public void cancel(){
            try{
                mSocket.close();
            } catch (IOException e){
            }
        }
    }

    private void connected(BluetoothSocket mSocket, BluetoothDevice mDevice) {
        Log.d(TAG, "connected: starting...");

        mConnectedThread = new ConnectedThread(mSocket);
        mConnectedThread.start();
    }

    public void write(byte[] out){
        ConnectedThread r;

        Log.d(TAG, "write: write called");
        mConnectedThread.write(out);
    }
}
