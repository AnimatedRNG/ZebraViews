package zebradev.zebraviews.android;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.minlog.Log.Logger;

public class ZebraAndroidLogger extends Logger {
	
	@Override
	public synchronized void log(int level, String category, String message, Throwable ex)
	{
		switch (level) {
		case Log.LEVEL_ERROR: android.util.Log.e(category, message); break;
		case Log.LEVEL_WARN: android.util.Log.w(category, message); break;
		case Log.LEVEL_INFO: android.util.Log.i(category, message); break;
		case Log.LEVEL_DEBUG: android.util.Log.d(category, message); break;
		case Log.LEVEL_TRACE: android.util.Log.v(category, message); break;
		}
		
		StringBuilder builder = new StringBuilder();
		if (ex != null)
		{
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
			builder.append("\n" + errors.toString());
		}
		
		android.util.Log.e("Stack trace", builder.toString());
	}
}
