package com.example.android.xiaolv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_message, R.id.gerenxinxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_message:
                finish();
                break;
            case R.id.gerenxinxi:
                Intent intent=new Intent(MessageActivity.this,MymessageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
