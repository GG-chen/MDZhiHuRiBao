package com.example.shinelon.mymdapp.ui.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.TuringParams;
import com.example.shinelon.mymdapp.adapter.TuringAdapter;
import com.example.shinelon.mymdapp.modle.bean.TuringBean;
import com.example.shinelon.mymdapp.presenter.TuringPresenter;
import com.example.shinelon.mymdapp.ui.activity.HomeActivity;
import com.example.shinelon.mymdapp.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/23.
 */
public class TuringFragment extends BaseFragment implements TuringFrg, View.OnClickListener {
    private TuringPresenter mPresenter;
    @InjectView(R.id.turing_main)
    public ListView mListView;
    @InjectView(R.id.send_msg)
    public ImageView mSend;
    @InjectView(R.id.edit_context)
    public EditText mEditText;

    private TuringAdapter mAdapter;
    private List<TuringBean> mList = new ArrayList<>();
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mAdapter.notifyDataSetChanged();
        }
    };
    private static boolean isFirst = true;

    @Override
    protected void initFragment() {
        initView();
        if (isFirst) {
            initData();
            isFirst = false;
        }
    }

    private void initData() {

        TuringBean bean = new TuringBean();
        bean.setText("你好呀  :)  ");
        bean.setType(TuringParams.TYPE_LEFT);
        bean.setCode(TuringParams.TulingCode.TEXT);
        mList.add(bean);
    }


    private void initView() {
        mPresenter = new TuringPresenter(mContext);
        mPresenter.attachView(this);
        mAdapter = new TuringAdapter(mList, mContext);
        mListView.setAdapter(mAdapter);
        mSend.setOnClickListener(this);
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyUtils.hideKeyboard((HomeActivity) mContext);
                return false;
            }
        });
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.turing_frag;
    }

    @Override
    public void loadData(TuringBean bean) {
        if (bean != null && bean.getCode() != TuringParams.TulingCode.ERROR_DATA_FORMAT
                && bean.getCode() != TuringParams.TulingCode.ERROR_TEXT_NULL
                && bean.getCode() != TuringParams.TulingCode.ERRORKEY_LEN
                && bean.getCode() != TuringParams.TulingCode.ERROR_SERVER_UPDATE
                && bean.getCode() != TuringParams.TulingCode.ERROR_KEY_ERROR
                && bean.getCode() != TuringParams.TulingCode.ERROR_OUT_OF_TIMES
                && bean.getCode() != TuringParams.TulingCode.ERROR_NOT_SUPPORT
                ) {
            mList.add(bean);
            mHandler.sendEmptyMessage(0);
        } else {
            TuringBean errorBean = new TuringBean();
            errorBean.setText("抱歉，我生病了，不能陪小主聊天了...");
            errorBean.setType(TuringParams.TYPE_LEFT);
            mList.add(errorBean);
            mHandler.sendEmptyMessage(0);

        }
        mListView.setSelection(mListView.getCount());

    }

    @Override
    public void onClick(View v) {
        MyUtils.hideKeyboard((HomeActivity) mContext);
        switch (v.getId()) {
            case R.id.send_msg:
                String text = String.valueOf(mEditText.getText());
                text.trim();
                if (text != null && !"".equals(text)) {
                    TuringBean bean = new TuringBean();
                    bean.setText(text);
                    bean.setType(TuringParams.TYPE_RIGHT);
                    mList.add(bean);
                    mHandler.sendEmptyMessage(0);
                    mPresenter.loadData(text);
                } else {
                    Toast.makeText(mContext, "不能为空！！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        mEditText.setText("");
        mListView.setSelection(mListView.getCount());
    }
}
