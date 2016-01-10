package com.example.olya.photos;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

public class YandexToken {
    public String token;

    void connect(String clientId, final Context context,
                 DialogInterface.OnDismissListener listener) {
        RelativeLayout popLayout = new RelativeLayout(context);
        WebView web = new WebView(context);
        web.getSettings().setJavaScriptEnabled(true);
        popLayout.addView(web, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        final Dialog popUp = new Dialog(context);
        popUp.setContentView(popLayout);

        web.loadUrl("https://oauth.yandex.ru/authorize?response_type=token" + "&client_id=" +
                clientId);
        popUp.show();
        WebViewClient wC = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("access_token=")) {
                    String fragment = (Uri.parse(url)).getEncodedFragment();
                    token = fragment.substring(
                            fragment.indexOf("access_token=") + "access_token=".length(),
                            fragment.indexOf("&"));
                    popUp.dismiss();
                }
            }
        };

        web.setWebViewClient(wC);
        popUp.setCancelable(true);
        popUp.setOnDismissListener(listener);
    }

    public String toString() {
        return token;
    }
}
