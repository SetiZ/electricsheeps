package instant.bazinga;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.RemoteViews;

public class widget extends AppWidgetProvider {

	public static String ACTION_WIDGET = "widgetClick";
	private static MediaPlayer mp = null;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget);
		Intent active = new Intent(context, widget.class);
		active.setAction(ACTION_WIDGET);
		PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context,
				0, active, 0);
		remoteViews.setOnClickPendingIntent(R.id.icon, actionPendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		// v1.5 fix that doesn't call onDelete Action
		final String action = intent.getAction();
		if (mp == null) {
			mp = MediaPlayer.create(context, R.raw.bazinga);
		} else {
			if (!mp.isPlaying()) {
				mp = MediaPlayer.create(context, R.raw.bazinga);
			}
		}
		if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) {
			final int appWidgetId = intent.getExtras().getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
			if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
				this.onDeleted(context, new int[] { appWidgetId });
			}
		} else {
			// check, if our Action was called
			if (intent.getAction().equals(ACTION_WIDGET)) {
				// MediaPlayer mp = MediaPlayer.create(context,R.raw.bazinga);
				// if (mp != null) {
				// mp.stop();
				// mp.release();
				// }
				if (mp != null) {
					mp.start();
				}
				/*
				 * mp.setOnCompletionListener(new OnCompletionListener() {
				 * public void onCompletion(MediaPlayer arg0) { arg0.release();
				 * } });
				 */
			} else {
				// do nothing
			}
			super.onReceive(context, intent);
		}
	}
}