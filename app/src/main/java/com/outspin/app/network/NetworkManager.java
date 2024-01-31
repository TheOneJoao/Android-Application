package com.outspin.app.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.outspin.app.data.Macros;
import com.outspin.app.models.Moment;
import com.outspin.app.ui.moment.MomentAdapter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

public class NetworkManager {

    public static boolean isServerReachable(int timeoutMS) {
        boolean connected = false;
        Socket socket;
        try {
            socket = new Socket();
            SocketAddress socketAddress =
                    new InetSocketAddress(Macros.CONST_INTERNET_SERVER_IPV4, Macros.CONST_INTERNET_TCP_PORT);
            socket.connect(socketAddress, timeoutMS);

            if (socket.isConnected()) {
                connected = true;
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
        return connected;
    }

    // Returns connection type. 0: none; 1: mobile data; 2: wifi; 3: vpn;
    @IntRange(from = 0, to = 3)
    public static int getConnectionType(@NonNull Context context) {
        int result = 0;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                        result = 2;
                    else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                        result = 1;
                    else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
                        result = 3;
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                        result = 2;
                    else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                        result = 1;
                    else if (activeNetwork.getType() == ConnectivityManager.TYPE_VPN)
                        result = 3;
                }
            }
        }
        return result;
    }

    public static class BindMoment extends AsyncTask<Void, Void, Boolean> {
        Context context;
        MomentAdapter.MomentViewHolder momentViewHolder;

        public BindMoment(Context context, MomentAdapter.MomentViewHolder momentViewHolder) {
            this.context = context;
            this.momentViewHolder = momentViewHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //Returns connection type. 0: none; 1: mobile data; 2: wifi; 3: vpn; 4: server not reachable
        @Override
        protected Boolean doInBackground(Void... voids) {
            int type = getConnectionType(this.context);
            return type != 0 && isServerReachable(Macros.CONST_INTERNET_SOCKET_TIMEOUT);
        }

        @Override
        protected void onPostExecute(Boolean isConnected) {
            super.onPostExecute(isConnected);

            if(isConnected)
                new MomentLoader(momentViewHolder).execute();
            else
                Toast.makeText(this.context, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    /*
        Moment Loader
     */
    public static class MomentLoader extends AsyncTask<VideoView, String, Moment> {
        MomentAdapter.MomentViewHolder momentViewHolder;

        public MomentLoader(MomentAdapter.MomentViewHolder momentViewHolder) {
            this.momentViewHolder = momentViewHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Moment doInBackground(VideoView... videoView) {
            //download e tal e poe no viewholder
            return new Moment("video3", "Urban Beach",
                    "https://turingnotes.com/wp-content/uploads/2022/02/momentvideo9.mp4");
        }

        @Override
        protected void onPostExecute(Moment momentPackage) {
            super.onPostExecute(momentPackage);

            if(this.momentViewHolder != null)
                this.momentViewHolder.bindMomentData(momentPackage);
            else Log.d("OH NO!", "WHATTT");
        }
    }
}

/*

private boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager conManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    wifiAvailable = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    mobileAvailable = true;
        }
        return wifiAvailable || mobileAvailable;
    }

 */

/*
        void downloadFile(){

            try {
                URL url = new URL("https://turingnotes.com/wp-content/uploads/2022/02/momentvideo9.mp4");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);

                //connect
                urlConnection.connect();

                //set the path where we want to save the file
                String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();

                //create a new file, to save the downloaded file
                File file = new File(SDCardRoot,"mydownloadmovie.mp4");

                FileOutputStream fileOutput = new FileOutputStream(file);

                //Stream used for reading the data from the internet
                InputStream inputStream = urlConnection.getInputStream();

                //this is the total size of the file which we are downloading
                totalSize = urlConnection.getContentLength();


                //create a buffer...
                byte[] buffer = new byte[1024];
                int bufferLength = 0;

                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }
                //close the output stream when complete //
                fileOutput.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e) {

            }
        } */