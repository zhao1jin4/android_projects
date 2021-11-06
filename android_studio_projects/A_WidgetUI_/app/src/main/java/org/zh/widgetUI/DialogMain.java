package org.zh.widgetUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.zh.widgetUI.R;


public class DialogMain extends Activity
{
	private static final int DIALOG_NO_MESSAGE = 1;
	private static final int DIALOG_MESSAGE = 2;
	private static final int DIALOG_PROGESS = 4;
	private static final int DIALOG_LAYOUT = 3;


	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case DIALOG_NO_MESSAGE:
				return buildDialogNoMessage(DialogMain.this);

			case DIALOG_MESSAGE:
				return buildDialogWithMessage(DialogMain.this);

			case DIALOG_LAYOUT:
				return buildDialogUseLayout(DialogMain.this);
			case DIALOG_PROGESS:
				return buildDialogUseProgess(DialogMain.this);

		}
		return null;
	}

	protected void onPrepareDialog(int id, Dialog dialog){
		if(id==DIALOG_NO_MESSAGE){
			setTitle("测试");
		}
	}

	int mStackLevel=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_alert);


		Button button_alertfragement = (Button) findViewById(R.id.button_alertfragement);
		button_alertfragement.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				DialogAlertFragmentMain newFragment = new DialogAlertFragmentMain();
				Bundle args = new Bundle();
				args.putInt("title", R.string.alert_dialog_two_buttons_title);
				newFragment.setArguments(args);
				newFragment.show(getFragmentManager(), "11111112222222dialog");
			}
		});

		Button button_fragement = (Button) findViewById(R.id.button_fragement);
		button_fragement.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				mStackLevel++;

				// DialogFragment.show() will take care of adding the fragment
				// in a transaction.  We also want to remove any currently showing
				// dialog, so make our own transaction and take care of that here.
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment prev = getFragmentManager().findFragmentByTag("My_Dialog");
				if (prev != null) {//现在这个(标识)对放框是否已经显示,如是删它
					ft.remove(prev);
				}
				ft.addToBackStack(null);

				//-------
				DialogFragmentMain newFragment = new DialogFragmentMain();
				Bundle args = new Bundle();
				args.putInt("num", mStackLevel);
				newFragment.setArguments(args);
				newFragment.show(ft, "My_Dialog");
			}
		});

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				showDialog(DIALOG_NO_MESSAGE);  //上面两个是解决方法
			}
		});

		Button buttons2 = (Button) findViewById(R.id.buttons2);
		buttons2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_MESSAGE);
			}
		});

		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_LAYOUT);
			}
		});

		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_PROGESS);
			}
		});
	}

	private Dialog buildDialogNoMessage(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_two_buttons_title);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("点击了对话框上的确定按钮");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("点击了对话框上的取消按钮");
					}
				});
		return builder.create();

	}
	private Dialog buildDialogWithMessage(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_two_buttons_msg);
		builder.setMessage(R.string.alert_dialog_two_buttons2_msg);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("点击了对话框上的确定按钮");
					}
				});
		builder.setNeutralButton(R.string.alert_dialog_something,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("点击了对话框上的进入详细按钮");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setTitle("点击了对话框上的取消按钮");
					}
				});
		return builder.create();
	}

	private Dialog buildDialogUseLayout(Context context) {
		LayoutInflater inflater = LayoutInflater.from(this);
		final View textEntryView = inflater.inflate(
				R.layout.dialog_text_entry, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.alert_dialog_icon);
		builder.setTitle(R.string.alert_dialog_text_entry);
		builder.setView(textEntryView);
		builder.setPositiveButton(R.string.alert_dialog_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("点击了对话框上的确定按钮");
					}
				});
		builder.setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("点击了对话框上的取消按钮");
					}
				});
		return builder.create();
	}


	private Dialog buildDialogUseProgess(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle("正在下载歌曲");
		dialog.setMessage("请稍候……");
		return  dialog;
	}
}


class DialogFragmentMain extends DialogFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		int mNum = getArguments().getInt("num");
		Log.i("DialogFragmentMain","(mNum-1)%6="+(mNum-1)%6);

		int style = DialogFragment.STYLE_NORMAL, theme = 0;
		switch ((mNum-1)%6) {
			case 1: style = DialogFragment.STYLE_NO_TITLE; break;
			case 2: style = DialogFragment.STYLE_NO_FRAME; break;//背景透明
			case 3: style = DialogFragment.STYLE_NO_INPUT; break;//背景透明,可以点后面的按钮
			case 4: style = DialogFragment.STYLE_NORMAL; break;//进入单独Activity,不是浮动的
			case 5: style = DialogFragment.STYLE_NORMAL; break;
			case 6: style = DialogFragment.STYLE_NO_TITLE; break;
			case 7: style = DialogFragment.STYLE_NO_FRAME; break;
			case 8: style = DialogFragment.STYLE_NORMAL; break;
		}
		switch ((mNum-1)%6) {
			case 4: theme = android.R.style.Theme_Holo; break;
			case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
			case 6: theme = android.R.style.Theme_Holo_Light; break;
			case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
			case 8: theme = android.R.style.Theme_Holo_Light; break;
		}
		setStyle(style, theme);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_text_entry, container, false);
		return v;
	}
}

class DialogAlertFragmentMain extends DialogFragment
{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int title = getArguments().getInt("title");
		return new AlertDialog.Builder(getActivity())
				.setIcon(R.drawable.alert_dialog_icon)
				.setTitle(title)
				.setMessage(R.string.alert_dialog_two_buttons2_msg)
				.setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								Log.i("DialogAlertFragmentMain", "Positive click!");
							}
						}
				)
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								Log.i("DialogAlertFragmentMain", "Negative  click!");
							}
						}
				)
				.create();
	}
}

