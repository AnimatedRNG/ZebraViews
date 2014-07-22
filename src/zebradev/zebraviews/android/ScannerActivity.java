package zebradev.zebraviews.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.CaptureActivity;

public class ScannerActivity extends CaptureActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scanner);
	}
	
	@Override
	public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
		Toast.makeText(this.getApplicationContext(),
				"Scanned code " + rawResult.getText() + " product type " + 
				rawResult.getBarcodeFormat().name(),
				Toast.LENGTH_LONG).show();
		Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(BeepManager.VIBRATE_DURATION);
		if (rawResult.getBarcodeFormat() == BarcodeFormat.QR_CODE)
		{
			Intent browserQR = new Intent(Intent.ACTION_VIEW, Uri.parse(rawResult.getText()));
			startActivity(browserQR);
		}
	}
}
