package signature;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import util.SignatureUtil;
import util.StringUtil;
import util.ToastUtil;
import you.with.com.signature.R;

/**
 * @author Klein
 * @version 1.1.5
 * @date 2018/12/6 11:22
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private EditText mEditContent;
    private RadioGroup mRg;
    private TextView mTvSign01;
    private TextView mTvSign02;
    private LinearLayout mLayoutSign01;
    private LinearLayout mLayoutSign02;
    private ImmersionBar mImmersionBar = null;
    private String mSign = "";
    private String mSignSymbol = "";
    private String mSignType = SignatureUtil.MD5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true).keyboardEnable(true).statusBarDarkFont(true, 0.2f).barColor(R.color.theme_color).init();
        mEditContent = findViewById(R.id.edit_pack_name);
        mRg = findViewById(R.id.rg);
        mLayoutSign01 = findViewById(R.id.layout_sign_01);
        mLayoutSign02 = findViewById(R.id.layout_sign_02);
        mTvSign01 = findViewById(R.id.tv_sign_01);
        mTvSign02 = findViewById(R.id.tv_sign_02);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mTvSign01.setText("");
                mTvSign02.setText("");
                mLayoutSign01.setVisibility(View.GONE);
                mLayoutSign02.setVisibility(View.GONE);
                switch (checkedId) {
                    case R.id.rb_01:
                        mSignType = SignatureUtil.MD5;
                        break;
                    case R.id.rb_02:
                        mSignType = SignatureUtil.SHA1;
                        break;
                    case R.id.rb_03:
                        mSignType = SignatureUtil.SHA256;
                        break;
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:
                String mPackAgeName = mEditContent.getText().toString().trim();
                if (mPackAgeName == null || mPackAgeName.equals("")) {
                    mLayoutSign01.setVisibility(View.GONE);
                    mLayoutSign02.setVisibility(View.GONE);
                    ToastUtil.show(mContext, mEditContent.getHint().toString().trim());
                    return;
                }
                try {
                    //如果你的keystore里面就只有一个签名信息，那么可以这样获取
                    mSign = SignatureUtil.getSignatureStr(mContext, mPackAgeName, mSignType);
                    mSignSymbol = SignatureUtil.getSignatureStrSymbol(mContext, mPackAgeName, mSignType);
                    if (StringUtil.isEmpty(mSign)) {
                        mLayoutSign01.setVisibility(View.GONE);
                        mLayoutSign02.setVisibility(View.GONE);
                        ToastUtil.show(mContext, "请检查包名是否有误");
                        return;
                    } else {
                        mTvSign01.setText(mSign + "");
                        mTvSign02.setText(mSignSymbol + "");
                        mLayoutSign01.setVisibility(View.VISIBLE);
                        mLayoutSign02.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    ToastUtil.show(mContext, "请检查包名是否有误");
                    mLayoutSign01.setVisibility(View.GONE);
                    mLayoutSign02.setVisibility(View.GONE);
                }
                break;
            //选择签名
            case R.id.btn_select:
                Intent intent = new Intent(MainActivity.this, PackageListActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_copy_sign_01:
                if (StringUtil.isNotEmpty(mSign)) {
                    ClipboardManager clip = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    clip.setText(mSign);
                    ToastUtil.show(mContext, "签名已复制到粘贴板");
                }
                break;
            case R.id.btn_copy_sign_02:
                if (StringUtil.isNotEmpty(mSignSymbol)) {
                    ClipboardManager clip = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    clip.setText(mSignSymbol);
                    ToastUtil.show(mContext, "签名已复制到粘贴板");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            String packageName = data.getStringExtra("packageName");
            mEditContent.setText(packageName);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }
}
