package com.it520.activity.news.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.it520.activity.R;
import com.it520.activity.news.city.bean.WhetherInfo;

public class WhetherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        Intent intent = getIntent();
        WhetherInfo whether = (WhetherInfo) intent.getSerializableExtra("whether");
        Log.d("whe-act", whether.toString());

        WhetherInfo.DayInfo dayInfo0 = whether.getDayInfos().get(0);
        ((TextView)findViewById(R.id.city_whether_temp_tv)).setText(dayInfo0.getTemperature());
        ((TextView)findViewById(R.id.whether_date)).setText(dayInfo0.getDate());
        ((TextView)findViewById(R.id.whether_climate)).setText(dayInfo0.getClimate());
        ((TextView)findViewById(R.id.whether_wind)).setText(dayInfo0.getWind());
        ((TextView)findViewById(R.id.whether_pm2d5)).setText(whether.getPm2d5().getPm2_5());
        ((TextView)findViewById(R.id.whether_quality)).setText("良");

        WhetherInfo.DayInfo dayInfo1 = whether.getDayInfos().get(1);
        ((TextView)findViewById(R.id.wind_tv)).setText(dayInfo1.getWind());
        ((TextView)findViewById(R.id.climate_tv)).setText(dayInfo1.getClimate());
        ((TextView)findViewById(R.id.temp_tv)).setText(dayInfo1.getTemperature());
        ((TextView)findViewById(R.id.day_tv)).setText(dayInfo1.getWeek());

        WhetherInfo.DayInfo dayInfo2 = whether.getDayInfos().get(2);
        ((TextView)findViewById(R.id.wind_tv_1)).setText(dayInfo2.getWind());
        ((TextView)findViewById(R.id.climate_tv_1)).setText(dayInfo2.getClimate());
        ((TextView)findViewById(R.id.temp_tv_1)).setText(dayInfo2.getTemperature());
        ((TextView)findViewById(R.id.day_tv_1)).setText(dayInfo2.getWeek());

        WhetherInfo.DayInfo dayInfo3 = whether.getDayInfos().get(3);
        ((TextView)findViewById(R.id.wind_tv_2)).setText(dayInfo3.getWind());
        ((TextView)findViewById(R.id.climate_tv_2)).setText(dayInfo3.getClimate());
        ((TextView)findViewById(R.id.temp_tv_2)).setText(dayInfo3.getTemperature());
        ((TextView)findViewById(R.id.day_tv_2)).setText(dayInfo3.getWeek());

        ImageView shareIv = (ImageView) findViewById(R.id.whether_shape_iv);
        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 分享接口
                Toast.makeText(WhetherActivity.this, "share", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
