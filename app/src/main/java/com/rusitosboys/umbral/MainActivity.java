package com.rusitosboys.umbral;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView wv;

    private class Bridge {
        @JavascriptInterface
        public void exit() { runOnUiThread(new Runnable() { public void run() { finish(); } }); }
    }

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= 28) {
            getWindow().getAttributes().layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        wv = new WebView(this);
        wv.setBackgroundColor(Color.BLACK);
        setContentView(wv);
        WebSettings s = wv.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        wv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        wv.setLongClickable(false);
        wv.setHapticFeedbackEnabled(false);
        wv.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) { return true; }
        });
        wv.addJavascriptInterface(new Bridge(), "UmbralNative");
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("file:///android_asset/index.html");
    }

    private void immersive() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override public void onWindowFocusChanged(boolean f) {
        super.onWindowFocusChanged(f);
        if (f) immersive();
    }

    @Override protected void onResume() { super.onResume(); wv.onResume(); immersive(); }

    @Override protected void onPause() {
        wv.evaluateJavascript("window.__pause&&window.__pause()", null);
        wv.onPause();
        super.onPause();
    }

    @Override public void onBackPressed() {
        wv.evaluateJavascript("window.__back&&window.__back()", null);
    }

    @Override protected void onDestroy() { wv.destroy(); super.onDestroy(); }
}
