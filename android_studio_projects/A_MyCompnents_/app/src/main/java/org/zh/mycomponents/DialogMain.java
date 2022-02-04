package org.zh.mycomponents;

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

import org.zh.mycomponents.R;


public class DialogMain extends Activity {
    private static final int DIALOG_NO_MESSAGE = 1;
    private static final int DIALOG_MESSAGE = 2;
    private static final int DIALOG_PROGESS = 4;
    private static final int DIALOG_LAYOUT = 3;


    @Override
    protected Dialog onCreateDialog(int id) {//过时,提示使用的DialogFragment也过时,目前为了兼容老的
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

    protected void onPrepareDialog(int id, Dialog dialog) {
        if (id == DIALOG_NO_MESSAGE) {
            setTitle("测试");
        }
    }

    int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_alert);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
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
        ProgressDialog dialog = new ProgressDialog(context);//ProgressDialog过时，使用 ProgressBar 替代
        dialog.setTitle("正在下载歌曲");
        dialog.setMessage("请稍候……");
        return dialog;
    }
}
