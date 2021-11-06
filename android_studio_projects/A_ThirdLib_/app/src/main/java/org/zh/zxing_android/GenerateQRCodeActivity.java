package org.zh.zxing_android;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.zh.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class GenerateQRCodeActivity extends Activity
{
	final int BLACK = 0xFF000000;
	final int WHITE = 0xFFFFFFFF;

	private String contentString ="http://www.baidu.com";
	private ImageView qrImageView;
	private int size=350;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxing);

		qrImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
		Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
		generateQRCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				try
				{
					Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
					hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
					BitMatrix matrix = new MultiFormatWriter().encode(contentString, BarcodeFormat.QR_CODE, size, size,hints);
					int width = matrix.getWidth();
					int height = matrix.getHeight();
					int[] pixels = new int[width * height];
					for (int y = 0; y < height; y++)
					{
						for (int x = 0; x < width; x++)
						{
							if (matrix.get(x, y))
								pixels[y * width + x] = BLACK;
							else
								pixels[y * width + x] = WHITE;
						}
					}
					//android 没有 java.awt.image.BufferedImage 要使用  android.graphics.Bitmap
					Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
					bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
					//(int[] pixels, int offset, int stride, int x, int y, int width, int height)
					qrImageView.setImageBitmap(bitmap);


					//---写手机上
					if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
					{
						File filename= new File(Environment.getExternalStorageDirectory(), "qrCode.png");
						try {
							FileOutputStream outStream=new FileOutputStream(filename);
							//FileOutputStream outStream = GenerateQRCodeActivity.this.openFileOutput(filename, Context.MODE_PRIVATE);//MODE_APPEND,MODE_WORLD_READABLE,MODE_WORLD_WRITEABLE
							bitmap.compress(CompressFormat.PNG, 80, outStream);
							outStream.flush();
							outStream.close();
						} catch (Exception e) {
							Toast.makeText(GenerateQRCodeActivity.this, "错误发生,原因为:"+e.getMessage(), Toast.LENGTH_LONG).show();
							return ;
						}
						Toast.makeText(GenerateQRCodeActivity.this, "二维码图片已保存到:"+filename.getAbsolutePath(), Toast.LENGTH_LONG).show();
					}
				} catch (WriterException e) {
					e.printStackTrace();
				}
			}
		});
	}
}