package com.cranberrygame.cordova.plugin.ad;

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

public class ChartboostPlugin extends CordovaPlugin{
	private static final String LOG_TAG = "Chartboost";
	//
	private CallbackContext callbackContextKeepCallback;

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
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException{
		
		if (action.equals("setUp")) {
			final String appId = args.getString(0);
			final String appSignature = args.getString(1);

			Log.i(LOG_TAG, "setUp");
			Log.i(LOG_TAG, String.format("%s", appId));
			Log.i(LOG_TAG, String.format("%s", appSignature));
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					_setUp(appId , appSignature);
				}
			});
			
			return true;
		}
		//----------
		else if(action.equals("preloadFullScreenAd")){
			final String location = args.getString(0);
			
			Log.i(LOG_TAG, "preloadFullScreenAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.cacheInterstitial(location);
				}
			});
		}
		else if(action.equals("showFullScreenAd")){
			final String location = args.getString(0);
			
			Log.i(LOG_TAG, "showFullScreenAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.showInterstitial(location);
				}
			});
		}
		//----------
		else if(action.equals("preloadMoreAppsAd")){
			final String location = args.getString(0);

			Log.i(LOG_TAG, "preloadMoreAppsAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.cacheMoreApps(location);
				}
			});
		}
		else if(action.equals("showMoreAppsAd")){
			final String location = args.getString(0);
			
			Log.i(LOG_TAG, "showMoreAppsAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.showMoreApps(location);
				}
			});
		}
		//----------
		else if(action.equals("preloadRewardedVideoAd")){
			final String location = args.getString(0);

			Log.i(LOG_TAG, "preloadRewardedVideoAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.cacheRewardedVideo(location);
				}
			});
		}
		else if(action.equals("showRewardedVideoAd")){
			final String location = args.getString(0);
			
			Log.i(LOG_TAG, "showRewardedVideoAd");
			
			cordova.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Chartboost.showRewardedVideo(location);
				}
			});
		}
		
		return false;
	}
	
	private void _setUp(String appId , String appSignature) {
		Chartboost.startWithAppId(cordova.getActivity(), appId , appSignature);
		Chartboost.onCreate(cordova.getActivity());
		Chartboost.onStart(cordova.getActivity());
		Chartboost.setDelegate(new MyChartboostDelegate());
	}
		
	class MyChartboostDelegate extends ChartboostDelegate{
	
		@Override
		public boolean shouldRequestInterstitial(String location) {
			Log.d(LOG_TAG, "shouldRequestInterstitial: "+ (location != null ? location : "null"));		
			return true;
		}
	
		@Override
		public void didCacheInterstitial(String location) {
			Log.i(LOG_TAG, "onFullScreenAdPreloaded: "+ (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onFullScreenAdPreloaded");
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
			Log.d(LOG_TAG, "onFullScreenAdShown: "+ (location != null ? location : "null"));
			
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
			Log.d(LOG_TAG, "onFullScreenAdHidden: "+ (location != null ? location : "null"));
			
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
			Log.d(LOG_TAG, "onMoreAppsAdPreloaded: " +  (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onMoreAppsAdPreloaded");
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
			Log.d(LOG_TAG, "onMoreAppsAdShown: " +  (location != null ? location : "null"));
			
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
			Log.d(LOG_TAG, "onMoreAppsAdHidden: "+  (location != null ? location : "null"));
			
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
			Log.d(LOG_TAG, "onRewardedVideoAdPreloaded: "+  (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdPreloaded");
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
			Log.d(LOG_TAG, "onRewardedVideoAdShown: " + (location != null ? location : "null"));
			
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
			Log.d(LOG_TAG, "onFullScreenAdHidden: " + (location != null ? location : "null"));
			
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onRewardedVideoAdHidden");
			pr.setKeepCallback(true);
			callbackContextKeepCallback.sendPluginResult(pr);
			//PluginResult pr = new PluginResult(PluginResult.Status.ERROR);
			//pr.setKeepCallback(true);
			//callbackContextKeepCallback.sendPluginResult(pr);				
		}

		@Override
		public void didCompleteRewardedVideo(String location, int reward) {
			Log.d(LOG_TAG, "onRewardedVideoAdCompleted: " + (location != null ? location : "null") + ", "+reward);
			
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
