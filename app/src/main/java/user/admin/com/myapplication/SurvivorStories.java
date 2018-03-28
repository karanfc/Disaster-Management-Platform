package user.admin.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SurvivorStories extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survivor_stories);

        webView = (WebView)findViewById(R.id.myWebView);
        webView.clearCache(true);
        webView.loadUrl("http://listverse.com/2015/05/18/10-amazing-survivors-of-unusual-natural-disasters/");
        webView.setHorizontalScrollBarEnabled(false);
    }
}
