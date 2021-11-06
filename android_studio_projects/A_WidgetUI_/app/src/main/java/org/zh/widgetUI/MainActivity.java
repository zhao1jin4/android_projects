package org.zh.widgetUI;




import org.zh.widgetUI.AutoCompleteTextViewActivity;
import org.zh.widgetUI.CheckBoxActivity;
import org.zh.widgetUI.DatePickerActivity;
import org.zh.widgetUI.EditTextActivity;
import org.zh.widgetUI.GridViewActivity;
import org.zh.widgetUI.ImageButtonActivity;
import org.zh.widgetUI.ImageShowActivity;
import org.zh.widgetUI.ImageViewActivity;
import org.zh.widgetUI.MatrixActivity;
import org.zh.widgetUI.MyTextSwitcherActivity;
import org.zh.widgetUI.MyViewActivity;
import org.zh.widgetUI.MyZoomActivity;
import org.zh.widgetUI.ProgressBarActivity;
import org.zh.widgetUI.R;
import org.zh.widgetUI.RadioGroupActivity;
import org.zh.widgetUI.RatingBarActivity;
import org.zh.widgetUI.SeekBarActivity;
import org.zh.widgetUI.SpinnerActivity;
import org.zh.widgetUI.TabTagActivity;
import org.zh.widgetUI.TimePickerActivity;
import org.zh.widgetUI.ViewTextActivity;
import org.zh.widgetUI.fragememt.listFragment.FragmentLayout;
import org.zh.widgetUI.fragement.tab.FragmentTabs;
import org.zh.widgetUI.layout.ActivityMain;
import org.zh.widgetUI.listview.ListViewMain;
import org.zh.widgetUI.menu.MenuMain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		find_and_modify_button();
	}

	private void find_and_modify_button() {
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(button_listener);
		Button text_view_button = (Button) findViewById(R.id.text_view_button);
		text_view_button.setOnClickListener(text_view_button_listener);

		Button edit_view_button = (Button) findViewById(R.id.edit_view_button);
		edit_view_button.setOnClickListener(edit_view_button_listener);

		Button check_box_button = (Button) findViewById(R.id.check_box_button);
		check_box_button.setOnClickListener(check_box_button_listener);

		Button radio_group_button = (Button) findViewById(R.id.radio_group_button);
		radio_group_button.setOnClickListener(radio_group_button_listener);

		Button spinner_button = (Button) findViewById(R.id.spinner_button);
		spinner_button.setOnClickListener(spinner_button_listener);

		Button auto_complete_button = (Button) findViewById(R.id.auto_complete_button);
		auto_complete_button.setOnClickListener(auto_complete_button_listener);

		Button date_picker_button = (Button) findViewById(R.id.date_picker_button);
		date_picker_button.setOnClickListener(date_picker_button_listener);

		Button time_picker_button = (Button) findViewById(R.id.time_picker_button);
		time_picker_button.setOnClickListener(time_picker_button_listener);

		Button progress_bar_button = (Button) findViewById(R.id.progress_bar_button);
		progress_bar_button.setOnClickListener(progress_bar_button_listener);

		Button seek_bar_button = (Button) findViewById(R.id.seek_bar_button);
		seek_bar_button.setOnClickListener(seek_bar_button_listener);

		Button rating_bar_button = (Button) findViewById(R.id.rating_bar_button);
		rating_bar_button.setOnClickListener(rating_bar_button_listener);

		Button image_view_button = (Button) findViewById(R.id.image_view_button);
		image_view_button.setOnClickListener(image_view_button_listener);

		Button image_button_button = (Button) findViewById(R.id.image_button_button);
		image_button_button.setOnClickListener(image_button_button_listener);

		Button image_show_button = (Button) findViewById(R.id.image_show_button);
		image_show_button.setOnClickListener(image_show_button_listener);

		Button grid_view_button = (Button) findViewById(R.id.grid_view_button);
		grid_view_button.setOnClickListener(grid_view_button_listener);


		Button tab_tag_button = (Button) findViewById(R.id.tab_tag_button);
		tab_tag_button.setOnClickListener(tab_tag_button_listener);

		Button custom_view_button = (Button) findViewById(R.id.custom_view_button);
		custom_view_button.setOnClickListener(custom_view_button_listener);


		Button text_switcher_button = (Button) findViewById(R.id.text_switcher_button);
		text_switcher_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MyTextSwitcherActivity.class);
				startActivity(intent);
			}
		});


		Button zoom_control_button = (Button) findViewById(R.id.zoom_control_button);
		zoom_control_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MyZoomActivity.class);
				startActivity(intent);
			}
		});
		Button matirx_button = (Button) findViewById(R.id.matirx_button);
		matirx_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MatrixActivity.class);
				startActivity(intent);
			}
		});

		Button menu_button = (Button) findViewById(R.id.menu_button);
		menu_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MenuMain.class);
				startActivity(intent);
			}
		});

		Button dialog_button = (Button) findViewById(R.id.dialog_button);
		dialog_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DialogMain.class);
				startActivity(intent);
			}
		});

		Button layout_button = (Button) findViewById(R.id.layout_button);
		layout_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ActivityMain.class);
				startActivity(intent);
			}
		});

		Button listview_button = (Button) findViewById(R.id.listview_button);
		listview_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ListViewMain.class);
				startActivity(intent);
			}
		});


		Button hcrollViewButton = (Button) findViewById(R.id.hcrollViewButton);
		hcrollViewButton.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,  org.zh.widgetUI.hscroll.HScrollActivity.class);
				startActivity(intent);
			}
		});


		Button fragementTab_button = (Button) findViewById(R.id.fragementTab_button);
		fragementTab_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,  FragmentTabs.class);
				startActivity(intent);
			}
		});
		Button fragementLayout_button = (Button) findViewById(R.id.fragementLayout_button);
		fragementLayout_button.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,  FragmentLayout.class);
				startActivity(intent);
			}
		});


	}



	private Button.OnClickListener button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			setTitle("哎呦,button被点了一下");
		}
	};

	private Button.OnClickListener text_view_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ViewTextActivity.class);
			startActivity(intent);
		}
	};
	private Button.OnClickListener edit_view_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, EditTextActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener check_box_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, CheckBoxActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener radio_group_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, RadioGroupActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener spinner_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SpinnerActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener auto_complete_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, AutoCompleteTextViewActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener date_picker_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, DatePickerActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener time_picker_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, TimePickerActivity.class);
			startActivity(intent);
		}
	};


	private Button.OnClickListener progress_bar_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ProgressBarActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener seek_bar_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SeekBarActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener rating_bar_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, RatingBarActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener image_view_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ImageViewActivity.class);
			startActivity(intent);
		}
	};
	private Button.OnClickListener image_button_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ImageButtonActivity.class);
			startActivity(intent);
		}
	};
	private Button.OnClickListener image_show_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ImageShowActivity.class);
			startActivity(intent);
		}
	};

	private Button.OnClickListener grid_view_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, GridViewActivity.class);
			startActivity(intent);
		}
	};


	private Button.OnClickListener tab_tag_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, TabTagActivity.class);
			startActivity(intent);
		}
	};
	private Button.OnClickListener custom_view_button_listener = new Button.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, MyViewActivity.class);
			startActivity(intent);
		}
	};

}