package com.iisysgroup.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iisysgroup.newlandtestapp.R;


public class CommonAlertDialog extends Dialog {
    /**
     * 取消按钮 *
     */
    private Button btnCancel;
    /**
     * 确定按钮 *
     */
    private Button btnConfirm;
    /**
     * 标题 *
     */
    private TextView txtTitle;
    /**
     * 内容 *
     */
    private TextView txtContent;
    private LinearLayout view;
    private View scrollView;
    /**
     * 按钮layout *
     */
    private View btnLayout;

    public CommonAlertDialog(Context context) {
        super(context, R.style.DialogStyle);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.width = context.getResources().getDimensionPixelSize(R.dimen.ctalert_width);
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

         /*
//         * 将对话框的大小按屏幕大小的百分比设置
//         */
//        WindowManager m = ((Activity)context).getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        params.height = (int) (d.getHeight() * 0.40); // 高度设置为屏幕的0.6
//        params.width = (int) (d.getWidth() * 0.80); // 宽度设置为屏幕的0.65
        getWindow().setAttributes(params);
        setContentView(View.inflate(context, R.layout.base_alert_dialog, null));
        txtTitle = (TextView) findViewById(R.id.alert_title);
        txtContent = (TextView) findViewById(R.id.alert_content);
        btnLayout = findViewById(R.id.alert_btn_layout);
        view = (LinearLayout) findViewById(R.id.alert_view);
        scrollView = findViewById(R.id.alert_scroll);
        initBtnCancel();
        initBtnConfirm();
    }

    /**
     * 默认取消按钮事件
     */
    private void initBtnCancel() {
        btnCancel = (Button) findViewById(R.id.alert_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 默认确定按钮事件
     */
    private void initBtnConfirm() {
        btnConfirm = (Button) findViewById(R.id.alert_btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    public void setView(View view) {
        this.view.addView(view);
        this.view.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }
    
    /**
     * 设置消息
     *
     * @param message
     */
    public void setMessage(String message) {
        txtContent.setText(message);
    }

    /**
     * 改变取消按钮标题
     *
     * @param title
     */
    public void setBtnCancelTitle(String title, final OnClickListener listener) {
        btnLayout.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        btnCancel.setText(title);
        if (listener != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onClick(v, CommonAlertDialog.this);
                }
            });
        }
    }

    /**
     * 改变确定按钮标题
     *
     * @param title
     */
    public void setBtnConfirmTitle(String title, final OnClickListener listener) {
        btnLayout.setVisibility(View.VISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
        btnConfirm.setText(title);
        if (listener != null) {
            btnConfirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onClick(v, CommonAlertDialog.this);
                }
            });
        }
    }

    public void setLayoutParams(int width, int Height) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.height = Height;
        getWindow().setAttributes(params);
    }

    public interface OnClickListener {
        public void onClick(View view, DialogInterface dialog);
    }

}
