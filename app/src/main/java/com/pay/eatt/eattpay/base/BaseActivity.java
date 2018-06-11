package com.pay.eatt.eattpay.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.pay.eatt.eattpay.R;
import com.pay.eatt.eattpay.http.ServiceApi;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bill on 2017/8/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_title)
    RelativeLayout rl_title;
    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.rl_main)
    RelativeLayout rl_main;
    @BindView(R.id.tv_prompt)
    TextView tv_prompt;
    private Unbinder unbinder;
    protected ProgressDialog progressDialog;
    public ServiceApi serviceApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_view);
        serviceApi = new ServiceApi(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    /**
     * 添加布局
     */
    public void BaseSetContentView(int layoutID) {
        FrameLayout fl_content = (FrameLayout) findViewById(R.id.fl_content);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutID, null);
        fl_content.addView(view);
        unbinder = ButterKnife.bind(this);
        back();
        initView();
        initDate();
    }


    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDate();


    /**
     * 返回键监听
     */
    public void setBackOnClickListener(View.OnClickListener listener) {
        iv_back.setOnClickListener(listener);
    }

    /**
     * 返回键
     */
    public void back() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 隐藏返回键
     */
    public void hideBack() {
        iv_back.setVisibility(View.GONE);
    }

    public void setTitle(int stringid) {
        if (tv_title != null) {
            tv_title.setText(getString(stringid));
        }
    }

    public void setTitle(int stringid, int color) {
        if (tv_title != null) {
            tv_title.setText(getString(stringid));
            tv_title.setTextColor(color);
        }
    }

    public void setBackgroundColor(int color) {
        if (rl_main != null) {
            rl_main.setBackgroundColor(color);
        }
    }

    public void setTitle(String string) {
        if (tv_title != null) {
            tv_title.setText(string);
        }
    }

    public void setTitle(String string, int color) {
        if (tv_title != null) {
            tv_title.setText(string);
            tv_title.setTextColor(color);
        }
    }

    public void setTitleBackgroundColor(int color) {
        if (rl_title != null) {
            rl_title.setBackgroundColor(color);
        }
    }

    public void setTitleBackground(int resid) {
        if (rl_title != null) {
            rl_title.setBackgroundResource(resid);
        }
    }

    public void setNoTitle(boolean b) {
        if (rl_title != null && rl_title.getVisibility() == View.VISIBLE) {
            if (b) {
                rl_title.setVisibility(View.GONE);
            } else {
                rl_title.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setHasTitle() {
        if (rl_title != null && rl_title.getVisibility() == View.GONE) {
            rl_title.setVisibility(View.VISIBLE);
        }
    }

    public void setPromptText(String text) {
        if (tv_prompt != null) {
            tv_prompt.setText(text);
        }
    }

    public void setPromptText(String text, int color) {
        if (tv_prompt != null) {
            tv_prompt.setText(text);
            tv_prompt.setTextColor(color);
        }
    }

    public void setPromptText(int stringid) {
        if (tv_prompt != null) {
            tv_prompt.setText(getString(stringid));
        }
    }

    public void setPromptClickListener(View.OnClickListener clickListener) {
        if (tv_prompt != null) {
            tv_prompt.setOnClickListener(clickListener);
        }
    }

    public void isPromptVisibility(boolean visibility) {
        if (tv_prompt != null) {
            if (visibility) {
                tv_prompt.setVisibility(View.VISIBLE);
            } else {
                tv_prompt.setVisibility(View.GONE);
            }
        }
    }

    public void changeColor(TextView textView, int start, int end, int style) {
        try {
            SpannableString styledText = new SpannableString(textView.getText());
            TextAppearanceSpan appearanceSpan = new TextAppearanceSpan(BaseActivity.this, style);
            styledText.setSpan(appearanceSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(styledText, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName()); //统计页面
        MobclickAgent.onResume(this);          //统计时长
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
        MobclickAgent.onPause(this);
    }

    public ProgressDialog showProgressDialog(final String msg) {
        if (progressDialog == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(BaseActivity.this);
                    progressDialog.setMessage(msg);
                    progressDialog.show();
                    progressDialog.setOnDismissListener(null);
                }
            });

        } else {
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
        return progressDialog;
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setOnDismissListener(null);
            progressDialog.dismiss();
        }

    }
}
