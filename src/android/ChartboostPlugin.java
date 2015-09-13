//Copyright (c) 2014 Sang Ki Kwon (Cranberrygame)
//Email: cranberrygame@yahoo.com
//Homepage: http://cranberrygame.github.io
//License: MIT (http://opensource.org/licenses/MIT)
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
import java.util.*;//Random

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

public class ChartboostPlugin extends CordovaPlugin {
	private static final String LOG_TAG = "ChartboostPlugin";
	private CallbackContext callbackContextKeepCallback;
	//
	protected String email;
	protected String licenseKey;
	public boolean validLicenseKey;
	protected String TEST_APP_ID = "55404fc104b01602ff113e68";
	protected String TEST_APP_SIGNATURE = "ce82ad49841edff7891ae44c3e7a502d522fdadd";	
	//
	protected String appId;
	protected String appSignature;
	//
	protected boolean interstitialAdPreload;
	protected boolean moreAppsAdPreload;
	protected boolean rewardedVideoAdPreload;
	
    @Override
	public void pluginInitialize() {
		super.pluginInitialize();
		//
    }
	
	//@Override
	//public void onCreate(Bundle savedInstanceState) {//build error
	//	super.onCreate(savedInstanceState);
	//	//
	//}
	
	//@Override
	//public void onStart() {//build error
	//	super.onStart();
	//	//
	//}
	
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
	
	//@Override
	//public void onStop() {//build error
	//	super.onStop();
	//	//
	//}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Chartboost.onDestroy(cordova.getActivity());
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
		else if (action.equals("preloadInterstitialAd")) {
			preloadInterstitialAd(action, args, callbackContext);
			
			return true;
		}
		else if (action.equals("showInterstitialAd")) {
			showInterstitialAd(action, args, callbackContext);
						
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
		//json.optString("adUnitBanner")
		//json.optString("adUnitFullScreen")
		//JSONObject inJson = json.optJSONObject("inJson");
		//final String adUnitBanner = args.getString(0);
		//final String adUnitFullScreen = args.getString(1);				
		//final boolean isOverlap = args.getBoolean(2);				
		//final boolean isTest = args.getBoolean(3);
		//final String[] zoneIds = new String[args.getJSONArray(4).length()];
		//for (int i = 0; i < args.getJSONArray(4).length(); i++) {
		//	zoneIds[i] = args.getJSONArray(4).getString(i);
		//}			
		//Log.d(LOG_TAG, String.format("%s", adUnitBanner));			
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
	
	private void preloadInterstitialAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));
		
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_preloadInterstitialAd(location);
			}
		});
	}

	private void showInterstitialAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		final String location = args.getString(0);
		Log.d(LOG_TAG, String.format("%s", location));

		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_showInterstitialAd(location);
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
		
		//
		String str1 = Util.md5("cordova-plugin-: " + email);
		String str2 = Util.md5("cordova-plugin-ad-chartboost: " + email);
		String str3 = Util.md5("com.cranberrygame.cordova.plugin.: " + email);
		String str4 = Util.md5("com.cranberrygame.cordova.plugin.ad.chartboost: " + email);
		String str5 = Util.md5("com.cranberrygame.cordova.plugin.ad.video.chartboost: " + email);
		if(licenseKey != null && (licenseKey.equalsIgnoreCase(str1) || licenseKey.equalsIgnoreCase(str2) || licenseKey.equalsIgnoreCase(str3) || licenseKey.equalsIgnoreCase(str4) || licenseKey.equalsIgnoreCase(str5))) {
			this.validLicenseKey = true;
			//
			String[] excludedLicenseKeys = {"xxx"};
			for (int i = 0 ; i < excludedLicenseKeys.length ; i++) {
				if (excludedLicenseKeys[i].equals(licenseKey)) {
					this.validLicenseKey = false;
					break;
				}
			}			
			if (this.validLicenseKey)
				Log.d(LOG_TAG, String.format("%s", "valid licenseKey"));
			else
				Log.d(LOG_TAG, String.format("%s", "invalid licenseKey"));
		}
		else {
			Log.d(LOG_TAG, String.format("%s", "invalid licenseKey"));
			this.validLicenseKey = false;
		}
		//if (!this.validLicenseKey)
		//	Util.alert(cordova.getActivity(),"Cordova Chartboost: invalid email / license key. You can get free license key from https://play.google.com/store/apps/details?id=com.cranberrygame.pluginsforcordova");			
	}
	
	private void _setUp(String appId, String appSignature) {
		this.appId = appId;
		this.appSignature = appSignature;
		
		if (!validLicenseKey) {
			if (new Random().nextInt(100) <= 1) {//0~99					
				this.appId = TEST_APP_ID;
				this.appSignature = TEST_APP_SIGNATURE;
			}
		}
		
		Chartboost.startWithAppId(cordova.getActivity(), this.appId , this.appSignature);
		Chartboost.setLoggingLevel(Level.ALL);		
		Chartboost.onCreate(cordova.getActivity());
		Chartboost.onStart(cordova.getActivity());
		Chartboost.setDelegate(new MyChartboostDelegate());
	}

	private void _preloadInterstitialAd(String location) {
		interstitialAdPreload = true;
		
		Chartboost.cacheInterstitial(location);	
	}

	private void _showInterstitialAd(String location) {
		interstitialAdPreload = false;		

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
	
	class MyChartboostDelegate extends ChartboostDelegate {
	
		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.d(LOG_TAG, "shouldRequestInterstitial: "+ (location != null ? location : "null"));		
			return true;
		}
	
		@Override
		public void didCacheInterstitial(String location) {
			Log.i(LOG_TAG, "didCacheInterstitial: "+ (location != null ? location : "null"));
						
    		if (interstitialAdPreload) {
			
				JSONObject result = new JSONObject();
				try {
					result.put("event", "onInterstitialAdPreloaded");
					result.put("message", location);
				}
				catch(JSONException ex){
				}		
    			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onInterstitialAdPreloaded");
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onInterstitialAdLoaded");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onInterstitialAdLoaded");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onInterstitialAdShown");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onInterstitialAdShown");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onInterstitialAdHidden");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onInterstitialAdHidden");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
				JSONObject result = new JSONObject();
				try {
					result.put("event", "onMoreAppsAdPreloaded");
					result.put("message", location);
				}
				catch(JSONException ex){
				}			
    			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdPreloaded");
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onMoreAppsAdLoaded");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdLoaded");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onMoreAppsAdShown");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdShown");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onMoreAppsAdHidden");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdHidden");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
				JSONObject result = new JSONObject();
				try {
					result.put("event", "onRewardedVideoAdPreloaded");
					result.put("message", location);
				}
				catch(JSONException ex){
				}			
    			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdPreloaded");
    			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
    			pr.setKeepCallback(true);
    			callbackContextKeepCallback.sendPluginResult(pr);
    			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
    			//pr.setKeepCallback(true);
    			//callbackContextKeepCallback.sendPluginResult(pr);		
    		}
    		
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onRewardedVideoAdLoaded");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdLoaded");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onRewardedVideoAdShown");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdShown");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onRewardedVideoAdHidden");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdHidden");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}

		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.d(LOG_TAG, "didCompleteRewardedVideo: " + (location != null ? location : "null") + ", "+reward);
			
			JSONObject result = new JSONObject();
			try {
				result.put("event", "onRewardedVideoAdCompleted");
				result.put("message", location);
			}
			catch(JSONException ex){
			}			
			//PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdCompleted");
			PluginResult pr = new PluginResult(PluginResult.Status.OK, result);
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
