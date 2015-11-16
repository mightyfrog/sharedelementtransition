package org.mightyfrog.android.sample.sharedelementtransition;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Shigehiro Soejima
 */
public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        int color = getIntent().getIntExtra("backgroundColor", 0xff212121);
        findViewById(R.id.layout).setBackgroundColor(color);
    }
}
