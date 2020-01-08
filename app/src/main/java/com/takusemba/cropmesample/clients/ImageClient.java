package com.takusemba.cropmesample.clients;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by takusemba on 2017/09/10.
 */

public class ImageClient {

    private static final String KEY_BITMAP_STRING = "key_bitmap_string";

    final private SharedPreferences prefs;

    public ImageClient(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public void saveBitmap(String key , Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String bitmapStr = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, bitmapStr);
        editor.apply();
    }

    public Bitmap getBitmap(String key) {
        String bitmapStr = prefs.getString(key, "");
        Bitmap bitmap = null;
        if (!bitmapStr.isEmpty()) {
            byte[] bytes = Base64.decode(bitmapStr, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length).copy(Bitmap.Config.ARGB_8888, true);
        }
        return bitmap;
    }
}
