package com.chartboost.sdk.sample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.InPlay.CBInPlay;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.CBAnalytics;

public class SampleActivity extends Activity {
	
	private static final String TAG = "Chartboost";

    // Sample counter variables to increments PIA Level Track Info values
    private static int level_no = 1;
    private static int eventLabel = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Chartboost.startWithAppId(this, getResources().getString(R.string.appId), getResources().getString(R.string.appSignature));
		Chartboost.setLoggingLevel(Level.ALL);
		Chartboost.setDelegate(delegate);
		Chartboost.onCreate(this);
		setContentView(R.layout.main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Chartboost.onStart(this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Chartboost.onStop(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Chartboost.onPause(this);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Chartboost.onResume(this);
	}

	@Override
	public void onBackPressed() {
	     // If an interstitial is on screen, close it. Otherwise continue as normal.
        if (Chartboost.onBackPressed())
            return;
        else
            super.onBackPressed();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Chartboost.onDestroy(this);
	}

	public void onLoadButtonClick(View view) {
		String toastStr = "Loading Interstitial";
		if (Chartboost.hasInterstitial(CBLocation.LOCATION_LEADERBOARD))
			toastStr = "Loading Interstitial From Cache";
		Log.i(TAG, toastStr);
		Chartboost.showInterstitial(CBLocation.LOCATION_LEADERBOARD);
	}

	public void onMoreButtonClick(View view) {
		String toastStr = "Loading More Apps";
		if (Chartboost.hasMoreApps(CBLocation.LOCATION_GAME_SCREEN))
			toastStr = "Loading More Apps From Cache";
		Log.i(TAG, toastStr);
		Chartboost.showMoreApps(CBLocation.LOCATION_GAME_SCREEN);
	}

	public void onPreloadClick(View v) {
		Log.i(TAG, "Preloading Interstitial Ad");
		Chartboost.cacheInterstitial(CBLocation.LOCATION_LEADERBOARD);
	}

	public void onPreloadMoreAppsClick(View v) {
		Log.i(TAG, "Preloading More apps Ad");
		Chartboost.cacheMoreApps(CBLocation.LOCATION_GAME_SCREEN);
	}

	public void onLoadVideoClick(View view) {
		String toastStr = "Loading Rewarded Interstitial";
		if (Chartboost.hasRewardedVideo(CBLocation.LOCATION_ACHIEVEMENTS))
			toastStr = "Loading Rewarded Interstitial From Cache";
		Log.i(TAG, toastStr);
		Chartboost.showRewardedVideo(CBLocation.LOCATION_ACHIEVEMENTS); 
	}

	public void onPreloadVideoClick(View v) {
		Log.i(TAG, "Preloading Rewarded Interstitial Ad");
		Chartboost.cacheRewardedVideo(CBLocation.LOCATION_ACHIEVEMENTS);
	}

    public void sendLevelInfo(View v) {
        Log.i(TAG, "Sending Main Level Information");
        String event_label = Integer.toString(eventLabel++);
        int level = level_no++;
        CBAnalytics.trackLevelInfo(event_label, CBAnalytics.CBLevelType.HIGHEST_LEVEL_REACHED, level, event_label);       
    }

    public void sendSubLevelInfo(View v) {
        Log.i(TAG, "Sending Sub Level Information");
        String event_label = Integer.toString(eventLabel++);
        int level = level_no++;
        int sublevel = level+1;
        CBAnalytics.trackLevelInfo(event_label, CBAnalytics.CBLevelType.HIGHEST_LEVEL_REACHED,level, sublevel, event_label);
    }
    
	private ChartboostDelegate delegate = new ChartboostDelegate() {

		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.i(TAG, "SHOULD REQUEST INTERSTITIAL '"+ (location != null ? location : "null"));		
			return true;
		}
	
		@Override
		public boolean shouldDisplayInterstitial(String location) {
			Log.i(TAG, "SHOULD DISPLAY INTERSTITIAL '"+ (location != null ? location : "null"));
			return true;
		}
	
		@Override
		public void didCacheInterstitial(String location) {
			Log.i(TAG, "DID CACHE INTERSTITIAL '"+ (location != null ? location : "null"));
		}
	
		@Override
		public void didFailToLoadInterstitial(String location, CBImpressionError error) {
			Log.i(TAG, "DID FAIL TO LOAD INTERSTITIAL '"+ (location != null ? location : "null")+ " Error: " + error.name());
			Toast.makeText(getApplicationContext(), "INTERSTITIAL '"+location+"' REQUEST FAILED - " + error.name(), Toast.LENGTH_SHORT).show();
		}
	
		@Override
		public void didDismissInterstitial(String location) {
			Log.i(TAG, "DID DISMISS INTERSTITIAL: "+ (location != null ? location : "null"));
		}
	
		@Override
		public void didCloseInterstitial(String location) {
			Log.i(TAG, "DID CLOSE INTERSTITIAL: "+ (location != null ? location : "null"));
		}
	
		@Override
		public void didClickInterstitial(String location) {
			Log.i(TAG, "DID CLICK INTERSTITIAL: "+ (location != null ? location : "null"));
		}
	
		@Override
		public void didDisplayInterstitial(String location) {
			Log.i(TAG, "DID DISPLAY INTERSTITIAL: " +  (location != null ? location : "null"));
		}
	
		@Override
		public boolean shouldRequestMoreApps(String location) {
			Log.i(TAG, "SHOULD REQUEST MORE APPS: " +  (location != null ? location : "null"));
			return true;
		}
	
		@Override
		public boolean shouldDisplayMoreApps(String location) {
			Log.i(TAG, "SHOULD DISPLAY MORE APPS: " +  (location != null ? location : "null"));
			return true;
		}
	
		@Override
		public void didFailToLoadMoreApps(String location, CBImpressionError error) {
			Log.i(TAG, "DID FAIL TO LOAD MOREAPPS " +  (location != null ? location : "null")+ " Error: "+ error.name());
			Toast.makeText(getApplicationContext(), "MORE APPS REQUEST FAILED - " + error.name(), Toast.LENGTH_SHORT).show();
		}
	
		@Override
		public void didCacheMoreApps(String location) {
			Log.i(TAG, "DID CACHE MORE APPS: " +  (location != null ? location : "null"));
		}
	
		@Override
		public void didDismissMoreApps(String location) {
			Log.i(TAG, "DID DISMISS MORE APPS " +  (location != null ? location : "null"));
		}
	
		@Override
		public void didCloseMoreApps(String location) {
			Log.i(TAG, "DID CLOSE MORE APPS: "+  (location != null ? location : "null"));
		}
	
		@Override
		public void didClickMoreApps(String location) {
			Log.i(TAG, "DID CLICK MORE APPS: "+  (location != null ? location : "null"));
		}
	
		@Override
		public void didDisplayMoreApps(String location) {
			Log.i(TAG, "DID DISPLAY MORE APPS: " +  (location != null ? location : "null"));
		}
	
		@Override
		public void didFailToRecordClick(String uri, CBClickError error) {
			Log.i(TAG, "DID FAILED TO RECORD CLICK " + (uri != null ? uri : "null") + ", error: " + error.name());
			Toast.makeText(getApplicationContext(), "FAILED TO RECORD CLICK " + (uri != null ? uri : "null") + ", error: " + error.name(), Toast.LENGTH_SHORT).show();
		}
	
		@Override
		public boolean shouldDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format("SHOULD DISPLAY REWARDED VIDEO: '%s'",  (location != null ? location : "null")));
			return true;
		}
	
		@Override
		public void didCacheRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CACHE REWARDED VIDEO: '%s'",  (location != null ? location : "null")));
		}
	
		@Override
		public void didFailToLoadRewardedVideo(String location,
				CBImpressionError error) {
			Log.i(TAG, String.format("DID FAIL TO LOAD REWARDED VIDEO: '%s', Error:  %s",  (location != null ? location : "null"), error.name()));
			Toast.makeText(getApplicationContext(), String.format("DID FAIL TO LOAD REWARDED VIDEO '%s' because %s", location, error.name()), Toast.LENGTH_SHORT).show();
		}
	
		@Override
		public void didDismissRewardedVideo(String location) {
			Log.i(TAG, String.format("DID DISMISS REWARDED VIDEO '%s'",  (location != null ? location : "null")));
		}
	
		@Override
		public void didCloseRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLOSE REWARDED VIDEO '%s'",  (location != null ? location : "null")));
		}
	
		@Override
		public void didClickRewardedVideo(String location) {
			Log.i(TAG, String.format("DID CLICK REWARDED VIDEO '%s'",  (location != null ? location : "null")));
		}
	
		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.i(TAG, String.format("DID COMPLETE REWARDED VIDEO '%s' FOR REWARD %d",  (location != null ? location : "null"), reward));
		}
		
		@Override
		public void didDisplayRewardedVideo(String location) {
			Log.i(TAG, String.format("DID DISPLAY REWARDED VIDEO '%s' FOR REWARD", location));
		}
		
		@Override
		public void willDisplayVideo(String location) {
			Log.i(TAG, String.format("WILL DISPLAY VIDEO '%s", location));
		}
		
	};
	
	@SuppressLint("ClickableViewAccessibility")
	public void onInPlayButtonClick(View view) {
		String toastStr = "Loading InPlay";
		Log.i(TAG, toastStr);
		Toast.makeText(this, toastStr,
				Toast.LENGTH_SHORT).show();
		
		final CBInPlay inPlay  = CBInPlay.getInPlay(CBLocation.LOCATION_GAMEOVER);
		
		if(inPlay != null) {
			ImageView myview = (ImageView) findViewById(R.id.inplayView);
			myview.setImageBitmap(inPlay.getAppIcon());
			inPlay.show();
			myview.setOnTouchListener(new View.OnTouchListener() {
			
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					inPlay.click();
					return false;
				}
			});
		}
	}
	
	public void onPreloadInPlayButtonClick(View view) {
		String toastStr = "Preloading InPlay";
		Log.i(TAG, toastStr);
		CBInPlay.cacheInPlay(CBLocation.LOCATION_GAMEOVER);
	}
}