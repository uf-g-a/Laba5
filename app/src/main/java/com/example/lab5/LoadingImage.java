package com.example.lab5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

public class LoadingImage extends AsyncTask<Void, Void, Void> {

    AdapterCats adapterCats;
    Cats catInfo;

    public LoadingImage(AdapterCats adapterCats) {
        this.adapterCats = adapterCats;
        catInfo = Cats.getInstance();
    }

    protected Void doInBackground(Void... result) {
        for (int i = catInfo.getImageURLs().size() - 5; i < catInfo.getImageURLs().size(); i++) {
            Log.d("TESTER", i + "");
            String urldisplay = catInfo.getImageURLs().get(i);
            Bitmap mIcon;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
                catInfo.getImage().add(mIcon);
            } catch (Exception e) {
                Log.e("download", e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        adapterCats.notifyDataSetChanged();
    }
}
