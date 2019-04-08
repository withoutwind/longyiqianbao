package com.example.android.xiaolv;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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

public class ZhuceActivity extends BaseActivity {

    @BindView(R.id.poneword_retrieve)
    EditText ponewordRetrieve;
    @BindView(R.id.yanzhengma)
    EditText yanzhengma;
    @BindView(R.id.huoquyanzhengma)
    SuperButton huoquyanzhengma;
    @BindView(R.id.passwordok)
    EditText passwordok;
    @BindView(R.id.zhuceok)
    SuperButton zhuceok;
    private CountDownTimer countDownTimer;
    private NoBackgLodDialog noBackgLodDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.closeback, R.id.huoquyanzhengma, R.id.zhuceok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.closeback:
                finish();
                break;
            case R.id.huoquyanzhengma:
                if (TextUtils.isEmpty(ponewordRetrieve.getText().toString())) {
                    showToast("请填写手机号");
                    return;
                }
                intyanzhengma();
                break;
            case R.id.zhuceok:
                if (TextUtils.isEmpty(ponewordRetrieve.getText().toString())) {
                    showToast("请填写手机号");
                    return;
                }
                if (TextUtils.isEmpty(yanzhengma.getText().toString())) {
                    showToast("请填写验证码");
                    return;
                }
                if (TextUtils.isEmpty(passwordok.getText().toString())) {
                    showToast("请填写密码");
                    return;
                }
                intzhuceok();
                break;
        }
    }

    private void intyanzhengma() {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", ponewordRetrieve.getText().toString());
        final JSONObject jsonObject = new JSONObject(params);
        OkHttpUtils.post(CachUrl.baseurl+"customer/getCode")
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("code") == 200) {
                                countDownTimer = new CountDownTimer(60000, 1000) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        huoquyanzhengma.setText("剩" + millisUntilFinished / 1000 + "秒");
                                        huoquyanzhengma.setClickable(false);
                                        huoquyanzhengma.setsSolidColor("#D8D8D8");
                                    }

                                    @Override
                                    public void onFinish() {
                                        huoquyanzhengma.setText("重新发送");
                                        huoquyanzhengma.setClickable(true);
                                        huoquyanzhengma.setsSolidColor("#ff257fc7");
                                    }
                                };
                                countDownTimer.start();
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
                    }
                });

    }

    private void intzhuceok() {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", ponewordRetrieve.getText().toString());
        params.put("code", yanzhengma.getText().toString());
        params.put("passwd", passwordok.getText().toString());
        final JSONObject jsonObject = new JSONObject(params);
        OkHttpUtils.post(CachUrl.baseurl+"customer/enroll")
                .upJson(jsonObject.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        if (noBackgLodDialog == null) {
                            noBackgLodDialog = new NoBackgLodDialog(ZhuceActivity.this, false);
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
                                Gson gson = new Gson();
                                UserBean userBean = gson.fromJson(jsonObject.getString("data"), UserBean.class);
                                UserManageBean.userBean = userBean;
                                PreferencesTool.saveLoginInfo(ZhuceActivity.this, ponewordRetrieve.getText().toString(), passwordok.getText().toString());
                                UserManageBean.setIsLogin(true);
                                setResult(10);
                                goBack();
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
}
