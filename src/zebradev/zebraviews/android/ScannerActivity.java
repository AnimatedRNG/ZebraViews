package zebradev.zebraviews.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	public void onStart() {
		super.onStart();
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
		else
		{
			Intent launchActivity = new Intent(this, ResultsActivity.class);
			launchActivity.putExtra("product_code", rawResult.getText());
			launchActivity.putExtra("product_type", rawResult.getBarcodeFormat().name());
			startActivity(launchActivity);
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
	    menuInflater.inflate(R.menu.zebra_capture, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		int itemId = item.getItemId();
		
		switch (itemId) {
		case R.id.menu_settings: /*Open up settings activity*/ break;
		case R.id.menu_help: /*Open up help activity*/ break;
		}
		
		return true;
	}
}
