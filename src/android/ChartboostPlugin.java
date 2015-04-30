package com.cranberrygame.cordova.plugin.ad.chartboost;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;
//
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Libraries.CBLogging.Level;
//md5
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//Util
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Surface;
//
import java.util.*;

class Util {

	//ex) Util.alert(cordova.getActivity(),"message");
	public static void alert(Activity activity, String message) {
		AlertDialog ad = new AlertDialog.Builder(activity).create();  
		ad.setCancelable(false); // This blocks the 'BACK' button  
		ad.setMessage(message);  
		ad.setButton("OK", new DialogInterface.OnClickListener() {  
			@Override  
			public void onClick(DialogInterface dialog, int which) {  
				dialog.dismiss();                      
			}  
		});  
		ad.show(); 		
	}
	
	//https://gitshell.com/lvxudong/A530_packages_app_Camera/blob/master/src/com/android/camera/Util.java
	public static int getDisplayRotation(Activity activity) {
	    int rotation = activity.getWindowManager().getDefaultDisplay()
	            .getRotation();
	    switch (rotation) {
	        case Surface.ROTATION_0: return 0;
	        case Surface.ROTATION_90: return 90;
	        case Surface.ROTATION_180: return 180;
	        case Surface.ROTATION_270: return 270;
	    }
	    return 0;
	}

	public static final String md5(final String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }
}

public class ChartboostPlugin extends CordovaPlugin{
	private static final String LOG_TAG = "ChartboostPlugin";
	private CallbackContext callbackContextKeepCallback;
	//
	protected String email;
	protected String licenseKey;
	protected String TEST_APP_ID = "55404fc104b01602ff113e68";
	protected String TEST_APP_SIGNATURE = "ce82ad49841edff7891ae44c3e7a502d522fdadd";
	//
	protected String appId;
	protected String appSignature;
	//
	protected boolean fullScreenAdPreload;
	protected boolean moreAppsAdPreload;
	protected boolean rewardedVideoAdPreload;
	
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
    }
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		if (action.equals("setLicenseKey")) {
			setLicenseKey(action, args, callbackContext);

			return true;
		}	
		else if (action.equals("setUp")) {
			setUp(action, args, callbackContext);

			return true;
		}			
		else if (action.equals("preloadFullScreenAd")) {
			preloadFullScreenAd(action, args, callbackContext);
			
			return true;
		}
		else if (action.equals("showFullScreenAd")) {
			showFullScreenAd(action, args, callbackContext);
						
			return true;
		}
		else if (action.equals("preloadMoreAppsAd")) {
			preloadMoreAppsAd(action, args, callbackContext);
			
			return true;
		}
		else if (action.equals("showMoreAppsAd")) {
			showMoreAppsAd(action, args, callbackContext);
						
			return true;
		}
		else if (action.equals("preloadRewardedVideoAd")) {
			preloadRewardedVideoAd(action, args, callbackContext);
			
			return true;
		}
		else if (action.equals("showRewardedVideoAd")) {
			showRewardedVideoAd(action, args, callbackContext);
						
			return true;
		}
		
		return false; // Returning false results in a "MethodNotFound" error.
	}

	private void setLicenseKey(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String email = args.getString(0);
		final String licenseKey = args.getString(1);				
		Log.d(LOG_TAG, String.format("%s", email));			
		Log.d(LOG_TAG, String.format("%s", licenseKey));
		
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				_setLicenseKey(email, licenseKey);
			}
		});
	}
	
	private void setUp(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		//Activity activity=cordova.getActivity();
		//webView
		//args.length()
		//args.getString(0)
		//args.getString(1)
		//args.getInt(0)
		//args.getInt(1)
		//args.getBoolean(0)
		//args.getBoolean(1)
		//JSONObject json = args.optJSONObject(0);
		//json.optString("adUnit")
		//json.optString("adUnitFullScreen")
		//JSONObject inJson = json.optJSONObject("inJson");
		//final String adUnit = args.getString(0);
		//final String adUnitFullScreen = args.getString(1);				
		//final boolean isOverlap = args.getBoolean(2);				
		//final boolean isTest = args.getBoolean(3);				
		//Log.d(LOG_TAG, String.format("%s", adUnit));			
		//Log.d(LOG_TAG, String.format("%s", adUnitFullScreen));
		//Log.d(LOG_TAG, String.format("%b", isOverlap));
		//Log.d(LOG_TAG, String.format("%b", isTest));		
		final String appId = args.getString(0);
		final String appSignature = args.getString(1);
		Log.d(LOG_TAG, String.format("%s", appId));			
		Log.d(LOG_TAG, String.format("%s", appSignature));
		
		callbackContextKeepCallback = callbackContext;
			
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				_setUp(appId, appSignature);
			}
		});
	}
	
	private void preloadFullScreenAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));
		
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_preloadFullScreenAd(location);
			}
		});
	}

	private void showFullScreenAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_showFullScreenAd(location);
			}
		});
	}

	private void preloadMoreAppsAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_preloadMoreAppsAd(location);
			}
		});
	}

	private void showMoreAppsAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_showMoreAppsAd(location);
			}
		});
	}

	private void preloadRewardedVideoAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_preloadRewardedVideoAd(location);
			}
		});
	}

	private void showRewardedVideoAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_showRewardedVideoAd(location);
			}
		});
	}
	
	public void _setLicenseKey(String email, String licenseKey) {
		this.email = email;
		this.licenseKey = licenseKey;
	}
	
	private void _setUp(String appId, String appSignature) {
		//
		String str1 = Util.md5("com.cranberrygame.cordova.plugin.: " + email);
		String str2 = Util.md5("com.cranberrygame.cordova.plugin.ad.chartboost: " + email);
		if(licenseKey != null && (licenseKey.equalsIgnoreCase(str1) || licenseKey.equalsIgnoreCase(str2))) {
			Log.d(LOG_TAG, String.format("%s", "valid licenseKey"));
			Chartboost.startWithAppId(cordova.getActivity(), appId , appSignature);
		}
		else {
			Log.d(LOG_TAG, String.format("%s", "invalid licenseKey"));
			if (new Random().nextInt(100) <= 1) {//0~99					
				Chartboost.startWithAppId(cordova.getActivity(), TEST_APP_ID , TEST_APP_SIGNATURE);
			}
			else {
				Chartboost.startWithAppId(cordova.getActivity(), appId , appSignature);
			}
		}
		//
		Chartboost.setLoggingLevel(Level.ALL);		
		Chartboost.onCreate(cordova.getActivity());
		Chartboost.onStart(cordova.getActivity());
		Chartboost.setDelegate(new MyChartboostDelegate());
	}

	private void _preloadFullScreenAd(String location) {
		fullScreenAdPreload = true;
		
		Chartboost.cacheInterstitial(location);	
	}

	private void _showFullScreenAd(String location) {
		fullScreenAdPreload = false;		

		Chartboost.showInterstitial(location);	
	}

	private void _preloadMoreAppsAd(String location) {
		moreAppsAdPreload = true;
		
		Chartboost.cacheMoreApps(location);	
	}

	private void _showMoreAppsAd(String location) {
		moreAppsAdPreload = false;
		
		Chartboost.showMoreApps(location);	
	}

	private void _preloadRewardedVideoAd(String location) {
		rewardedVideoAdPreload = true;
		
		Chartboost.cacheRewardedVideo(location);	
	}

	private void _showRewardedVideoAd(String location) {
		rewardedVideoAdPreload = false;
		
		Chartboost.showRewardedVideo(location);	
	}
	
	@Override
	public void onPause(boolean multitasking) {
		super.onPause(multitasking);
		Chartboost.onPause(cordova.getActivity());
	}
	
	@Override
	public void onResume(boolean multitasking) {
		super.onResume(multitasking);
		Chartboost.onResume(cordova.getActivity());
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Chartboost.onDestroy(cordova.getActivity());
	}
	
	class MyChartboostDelegate extends ChartboostDelegate{
	
		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.d(LOG_TAG, "shouldRequestInterstitial: "+ (location != null ? location : "null"));		
			return true;
		}
	
		@Override
		public void didCacheInterstitial(String location) {
			Log.i(LOG_TAG, "didCacheInterstitial: "+ (location != null ? location : "null"));
						
    		if (fullScreenAdPreload) {
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onFullScreenAdPreloaded");
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onFullScreenAdLoaded");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}
	
		@Override
		public void didFailToLoadInterstitial(String location, CBImpressionError error) {
			Log.d(LOG_TAG, "didFailToLoadInterstitial: "+ (location != null ? location : "null")+ ", " + error.name());
		}

		@Override
		public boolean shouldDisplayInterstitial(String location) {
			Log.d(LOG_TAG, "shouldDisplayInterstitial: "+ (location != null ? location : "null"));
			
			return true;
		}
		
		@Override
		public void didDisplayInterstitial(String location) {
			Log.d(LOG_TAG, "didDisplayInterstitial: "+ (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onFullScreenAdShown");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}
	
		@Override
		public void didFailToRecordClick(String uri, CBClickError error) {
			Log.d(LOG_TAG, "didFailToRecordClick: " + (uri != null ? uri : "null") + ", error: " + error.name());
		}		
		
		@Override
		public void didClickInterstitial(String location) {
			Log.d(LOG_TAG, "didClickInterstitial: "+ (location != null ? location : "null"));
		}
		
		@Override
		public void didCloseInterstitial(String location) {
			Log.d(LOG_TAG, "didCloseInterstitial: "+ (location != null ? location : "null"));
		}

		@Override
		public void didDismissInterstitial(String location) {
			Log.d(LOG_TAG, "didDismissInterstitial: "+ (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onFullScreenAdHidden");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);			
		}
		
		//------------------------
		@Override
		public boolean shouldRequestMoreApps(String location) {
			Log.d(LOG_TAG, "shouldRequestMoreApps: " +  (location != null ? location : "null"));
			return true;
		}

		@Override
		public void didCacheMoreApps(String location) {
			Log.d(LOG_TAG, "didCacheMoreApps: " +  (location != null ? location : "null"));
			
    		if (moreAppsAdPreload) {
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdPreloaded");
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdLoaded");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);			
		}
		
		@Override
		public void didFailToLoadMoreApps(String location, CBImpressionError error) {
			Log.d(LOG_TAG, "didFailToLoadMoreApps: " +  (location != null ? location : "null")+ " Error: "+ error.name());
		}
				
		@Override
		public boolean shouldDisplayMoreApps(String location) {
			Log.d(LOG_TAG, "shouldDisplayMoreApps: " +  (location != null ? location : "null"));
			
			return true;
		}
	
		@Override
		public void didDisplayMoreApps(String location) {
			Log.d(LOG_TAG, "didDisplayMoreApps: " +  (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdShown");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);			
		}
			
		@Override
		public void didClickMoreApps(String location) {
			Log.d(LOG_TAG, "didClickMoreApps: "+  (location != null ? location : "null"));
		}
		
		@Override
		public void didCloseMoreApps(String location) {
			Log.d(LOG_TAG, "didCloseMoreApps: "+  (location != null ? location : "null"));
		}

		@Override
		public void didDismissMoreApps(String location) {
			Log.d(LOG_TAG, "didDismissMoreApps: "+  (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdHidden");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}
		
		//------------------------		
		@Override
		public void didCacheRewardedVideo(String location) {
			Log.d(LOG_TAG, "didCacheRewardedVideo: "+  (location != null ? location : "null"));
					
    		if (rewardedVideoAdPreload) {
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdPreloaded");
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdLoaded");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}
		
		@Override
		public void didFailToLoadRewardedVideo(String location,	CBImpressionError error) {
			Log.d(LOG_TAG, "didFailToLoadRewardedVideo: " + (location != null ? location : "null")+ ", "+ error.name());			
		}
		
		@Override
		public boolean shouldDisplayRewardedVideo(String location) {
			Log.d(LOG_TAG, "shouldDisplayRewardedVideo: " + (location != null ? location : "null"));
			
			return true;
		}
	
		@Override
		public void didDisplayRewardedVideo(String location) {
			Log.d(LOG_TAG, "didDisplayRewardedVideo: " + (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdShown");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}
				
		@Override
		public void didClickRewardedVideo(String location) {
			Log.d(LOG_TAG, "didClickRewardedVideo: " + (location != null ? location : "null"));
		}
		
		@Override
		public void didCloseRewardedVideo(String location) {
			Log.d(LOG_TAG, "didCloseRewardedVideo: " + (location != null ? location : "null"));
		}		

		@Override
		public void didDismissRewardedVideo(String location) {
			Log.d(LOG_TAG, "didDismissRewardedVideo: " + (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdHidden");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}

		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.d(LOG_TAG, "didCompleteRewardedVideo: " + (location != null ? location : "null") + ", "+reward);
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdCompleted");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}

		//----------------------
		@Override
		public void willDisplayVideo(String location) {
			Log.d(LOG_TAG, "willDisplayVideo: " + (location != null ? location : "null"));
		}			
	};	
}
