package com.example.shinelon.mymdapp.ui.fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.shinelon.mymdapp.R;
import com.example.shinelon.mymdapp.ui.view.MyClickSpan;

import butterknife.InjectView;

/**
 * Created by Shinelon on 2017/2/23.
 */
public class AboutFragment extends BaseFragment {
    public static final String MY_GITHUB = "https://github.com/GG-chen/MDZhiHuRiBao";
    @InjectView(R.id.about_context)
    public TextView mAbout;
    @Override
    protected void initFragment() {
        SpannableString spannableString = new SpannableString(MY_GITHUB);
        MyClickSpan myClickSpan = new MyClickSpan(MY_GITHUB, mContext);
        spannableString.setSpan(myClickSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mAbout.append("\n");
        mAbout.append(spannableString);
        mAbout.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected int getCurrentLayoutId() {
        return R.layout.about_frag;
    }
}
