package instant.bazinga;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class bazinga extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bazinga);

		final ImageView bazingaButton = (ImageView) findViewById(R.id.up);
		final MediaPlayer mp = MediaPlayer.create(bazinga.this, R.raw.bazinga);
		bazingaButton.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					// set the image to 'button clicked'
					bazingaButton.setImageResource(R.drawable.down);
					mp.start();
					mp.setOnCompletionListener(new OnCompletionListener() {
						public void onCompletion(MediaPlayer arg0) {
							
						}
					});
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					// set the image to 'button released'
					bazingaButton.setImageResource(R.drawable.up);
				}
				return true;
			}
		});
	}
}