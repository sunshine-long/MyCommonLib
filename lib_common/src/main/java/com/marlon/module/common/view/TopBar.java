package com.marlon.module.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marlon.module.common.base.BaseActivity;
import com.marlon.utils.R;


/**
 * @author Marlon
 * @desc
 * @date 2019/1/3
 */
public class TopBar extends ConstraintLayout implements View.OnClickListener {
    TextView mTopBarTitleTv;
    Button mTopBarBackBt;
    Button mTopBarDetailBt;
    private View rootView;
    String title, leftText, rightText;
    int titleColor, leftTextColor, rightTextColor;
    float titleTextSize, leftTextSize, rightTextSize;
    Drawable leftBackground, rightBackground;
    private Context mContext;

    View.OnClickListener onClickListener;

    public void setDetailOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);
        initTypedArray(context, attrs);
        init();
    }

    private void initView(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_top_bar, this);
        this.mTopBarTitleTv = (TextView) rootView.findViewById(R.id.top_bar_title_tv);
        this.mTopBarBackBt = (Button) rootView.findViewById(R.id.top_bar_back_bt);
        mTopBarBackBt.setOnClickListener(this);
        this.mTopBarDetailBt = (Button) rootView.findViewById(R.id.top_bar_detail_bt);
        mTopBarDetailBt.setOnClickListener(this);

    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        title = typedArray.getString(R.styleable.TopBar_title);
        titleColor = typedArray.getColor(R.styleable.TopBar_titleColor, 0);
        titleTextSize = typedArray.getDimension(R.styleable.TopBar_titleSize, 18);
        leftText = typedArray.getString(R.styleable.TopBar_leftText);
        leftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftTextSize = typedArray.getDimension(R.styleable.TopBar_leftTextSize, 16);
        leftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        rightText = typedArray.getString(R.styleable.TopBar_rightText);
        rightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightTextSize = typedArray.getDimension(R.styleable.TopBar_rightTextSize, 16);
        rightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        typedArray.recycle();
    }

    private void init() {
        if (null != title) {
            mTopBarTitleTv.setText(title);
        }
        mTopBarTitleTv.setTextSize(titleTextSize);
        if (titleColor != 0) {
            mTopBarTitleTv.setTextColor(titleColor);
        }
        if (leftText != null) {
            mTopBarBackBt.setText(leftText);
        }
        if (leftTextColor != 0) {
            mTopBarBackBt.setTextColor(leftTextColor);
        }
        mTopBarBackBt.setTextSize(leftTextSize);
        if (leftBackground != null) {
            mTopBarBackBt.setBackgroundDrawable(leftBackground);
        }
        if (rightText != null) {
            mTopBarDetailBt.setText(rightText);
        }
        if (rightTextColor != 0) {
            mTopBarDetailBt.setTextColor(rightTextColor);
        }
        mTopBarDetailBt.setTextSize(rightTextSize);
        if (rightBackground != null) {
            mTopBarDetailBt.setBackgroundDrawable(rightBackground);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.top_bar_back_bt) {
            ((BaseActivity) mContext).onBackPressed();
        } else if (i == R.id.top_bar_detail_bt) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

    }

}
