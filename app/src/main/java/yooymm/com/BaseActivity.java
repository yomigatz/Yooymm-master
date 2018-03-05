package yooymm.com;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yaomi on 2018/2/13.
 * Time ：10:05
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mUnbinder = ButterKnife.bind(this);
        //初始化
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //buttknife 解除绑定
        if (mUnbinder!=mUnbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    public abstract int setLayout();

//    public abstract void setUpActivityComponent(AppComponent appComponent);

    public abstract void init();
}
