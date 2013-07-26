package com.geryon.ocraa;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewHelper extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        
            return false;
        }
       
}