package com.iisysgroup.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.iisysgroup.newlandtestapp.R;
import com.iisysgroup.view.SweetView;


public class LoadingDialog extends Dialog {

	private static LoadingDialog loadingDialog =null;
	private boolean canDismiss = false;
	private boolean wasDismiss = false;
	private SweetView sweetView;
	private  long time =10000;
	private Runnable dismissRunable=new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (loadingDialog!=null){
				dismissNow();
			}
		}
	};



	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);//
		FrameLayout layout = (FrameLayout) v.findViewById(R.id.dialog_view);// ���ز���
		sweetView=(SweetView) v.findViewById(R.id.sweet);
		setCancelable(false);// 
		setContentView(layout, new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));//
		mhandler.postDelayed(dismissRunable, time);
		
	}

	public static LoadingDialog getInstens (Context context){
		if (loadingDialog==null){
			loadingDialog = new LoadingDialog(context,R.style.loading_dialog );
		}
		return  loadingDialog;
	}

	@Override
	public void show() {
		super.show();
		
		// TODO Auto-generated method stub
		if(sweetView!=null){
			sweetView.show();
		}
		canDismiss=false;
		wasDismiss=false;
		mhandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				canDismiss = true;
				if (wasDismiss) {
					dismiss();
				}
			}
		}, 800);
		
		mhandler.postDelayed(dismissRunable, time);
		
		super.show();
	}
	
	

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		System.out.println("can dismiss");
		wasDismiss = true;
		if (canDismiss) {
			super.dismiss();
		}
		mhandler.removeCallbacks(dismissRunable);
	}

	public void dismissNow(){
		if (mhandler!=null){
		   mhandler.removeCallbacks(dismissRunable);
		 }
//		super.dismiss();
	}

	public void onDestoryed() {
        if (loadingDialog!=null){
			loadingDialog.dismissNow();
			loadingDialog =null;
		}
	}

	private Handler mhandler = new Handler(getContext().getMainLooper());

	public void setContentText(String text) {
		// TODO Auto-generated method stub
		((TextView)findViewById(R.id.content)).setText(text);
		
	}

}
