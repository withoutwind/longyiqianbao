package com.example.android.xiaolv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.denglu)
    SuperButton denglu;
    @BindView(R.id.zhuce)
    SuperButton zhuce;
    private NoBackgLodDialog noBackgLodDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.close, R.id.denglu, R.id.zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.denglu:
                if (TextUtils.isEmpty(phone.getText().toString())){
                    showToast("请填写手机号");
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    showToast("请填写密码");
                    return;
                }
                intLogin();
                break;
            case R.id.zhuce:
                Intent intent=new Intent(this,ZhuceActivity.class);
                startActivityForResult(intent,10);
                break;
        }
    }

    private void intLogin() {
        HashMap<String, String> params = new HashMap<>();
        params.put("passwd",password.getText().toString());
        params.put("phone",phone.getText().toString());
        final JSONObject jsonObject = new JSONObject(params);
        OkHttpUtils.post(CachUrl.baseurl+"customer/login")
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (noBackgLodDialog == null) {
                            noBackgLodDialog = new NoBackgLodDialog(LoginActivity.this, false);
                        }
                        noBackgLodDialog.show();
                    }
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (noBackgLodDialog != null) {
                            noBackgLodDialog.dismis();
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("code") == 200) {
                                Gson gson=new Gson();
                                UserBean userBean=gson.fromJson(jsonObject.getString("data"),UserBean.class);
                                UserManageBean.userBean=userBean;
                                UserManageBean.setIsLogin(true);
                                PreferencesTool.saveLoginInfo(LoginActivity.this,phone.getText().toString(),password.getText().toString());
                                finish();
                            } else {
                                showToast(jsonObject.getString("msg"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        if (noBackgLodDialog != null) {
                            noBackgLodDialog.dismis();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==10){
          goBack();
        }
    }


}
