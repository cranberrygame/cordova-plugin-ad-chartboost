# Overview #
show chartboost full screen (static interstitial, video interstial), more apps, rewarded video ad

[android, ios] [crosswalk] [cordova cli]

requires revmob account https://www.revmobmobileadnetwork.com

Chartboost android SDK 5.2.0 (Apr. 6, 2015)
Chartboost ios SDK 5.2.1 (Apr. 13, 2015)

I can't see any ads in my game - create a new publishing campaign in the Chartboost dashboard (takes 20 minutes to take effect)
https://answers.chartboost.com/hc/en-us/articles/201121969-I-can-t-see-any-ads-in-my-game

this is open source cordova plugin.

you can get free license from following app.
https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.admob/blob/master/doc/cordovapluginfreelicense/CordovaApp-debug.apk
put window.chartboost.setLicenseKey("youremail@yourdoamin.com", "yourlicensekey"); function call before setUp function call.
if you do not call this function, this plugin will share 2% traffic with plugin developer for supporting plugin development.

# Change log #
```c
```
# Install plugin #

## Cordova cli ##
```c
cordova plugin add com.cranberrygame.cordova.plugin.ad.chartboost
```

## Crosswalk ##
```c
XDK PORJECTS - your_xdk_project - CORDOVA 3.X HYBRID MOBILE APP SETTINGS - PLUGINS AND PERMISSIONS - Third Party Plugins - Add a Third Party Plugin - Get Plugin from the Web -

Name: revmob
Plugin ID: com.cranberrygame.cordova.plugin.ad.chartboost
[v] Plugin is located in the Apache Cordova Plugins Registry
```

## Phonegap build service (config.xml) ##
```c
<gap:plugin name="com.cranberrygame.cordova.plugin.ad.chartboost" source="plugins.cordova.io" />
```

## Construct2 ##

Download construct2 chartboost plugin and example
https://plus.google.com/communities/117978754675005605917 

# Server setting #
```c
```

<img src="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/app_id.png"><br>
<img src="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/publishing_campaign1.png"><br>
<img src="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/publishing_campaign2.png"><br>
<img src="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/publishing_campaign3.png">

test mode setting: 
https://www.chartboost.com - Login - DASHBOARD - [specific app] - App Settings - Test Mode: select Disabled or Enabled 

# API #
```javascript
var appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
var appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE_ID";

/*
var appId;
var appSignature;
//android
if (navigator.userAgent.match(/Android/i)) {
	appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
	appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE_ID";
}
//ios
else if (navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i)) {
	appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
	appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE_ID";
}
*/

document.addEventListener("deviceready", function(){
	window.chartboost.setUp(mediaId, isOverlap);
	
	//full screen ad callback
	window.chartboost.onFullScreenAdPreloaded = function() {
		alert('onFullScreenAdPreloaded');
	};
	window.chartboost.onFullScreenAdLoaded = function() {
		alert('onFullScreenAdLoaded');
	};
	window.chartboost.onFullScreenAdShown = function() {
		alert('onFullScreenAdShown');
	};
	window.chartboost.onFullScreenAdHidden = function() {
		alert('onFullScreenAdHidden');
	};
	//more apps ad callback
	window.chartboost.onMoreAppsAdPreloaded = function() {
		alert('onMoreAppsAdPreloaded');
	};
	window.chartboost.onMoreAppsAdLoaded = function() {
		alert('onMoreAppsAdLoaded');
	};
	window.chartboost.onMoreAppsAdShown = function() {
		alert('onMoreAppsAdShown');
	};
	window.chartboost.onMoreAppsAdHidden = function() {
		alert('onMoreAppsAdHidden');
	};
	//rewarded video ad callback
	window.chartboost.onRewardedVideoAdPreloaded = function() {
		alert('onRewardedVideoAdPreloaded');
	};
	window.chartboost.onRewardedVideoAdLoaded = function() {
		alert('onRewardedVideoAdLoaded');
	};
	window.chartboost.onRewardedVideoAdShown = function() {
		alert('onRewardedVideoAdShown');
	};
	window.chartboost.onRewardedVideoAdHidden = function() {
		alert('onRewardedVideoAdHidden');
	};	
}, false);

/*
location parameter

'Default' - Supports legacy applications that only have one "Default" location
'Startup' - Initial startup of game.
'Home Screen' - Home screen the player first sees.
'Main Menu' - Menu that provides game options.
'Game Screen' - Game screen where all the magic happens.
'Achievements' - Screen with list of achievements in the game.
'Quests' - Quest, missions or goals screen describing things for a player to do.
'Pause' - Pause screen.
'Level Start' - Start of the level.
'Level Complete' - Completion of the level
'Turn Complete' - Finishing a turn in a game.
'IAP Store' - The store where the player pays real money for currency or items.
'Item Store' - The store where a player buys virtual goods.
'Game Over' - The game over screen after a player is finished playing.
'Leaderboard' - List of leaders in the game.
'Settings' - Screen where player can change settings such as sound.
'Quit' - Screen displayed right before the player exits a game.		
*/	

//static interstitial, video interstial
window.chartboost.preloadFullScreenAd('Default');
window.chartboost.showFullScreenAd('Default');

//more apps
window.chartboost.preloadMoreAppsAd('Default');
window.chartboost.showMoreAppsAd('Default');

//rewarded video
window.chartboost.preloadRewardedVideoAd('Default');
window.chartboost.showRewardedVideoAd('Default');
```
# Examples #
<a href="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/example/basic/index.html">example/basic/index.html</a><br>

# Test #

<img src="https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/fullscreen_ad.png">

Youtube
[![](http://img.youtube.com/vi/EQJLRbSKmPU/0.jpg)](https://www.youtube.com/watch?v=EQJLRbSKmPU&feature=youtu.be "Youtube")

CordovaApp-debug.apk
https://github.com/cranberrygame/com.cranberrygame.cordova.plugin.ad.chartboost/blob/master/doc/CordovaApp-debug.apk

# Useful links #

<<<<<<< HEAD
Cordova monetisation-related plugins G+ community<br>
https://plus.google.com/communities/116032133386884708413<br>
<br>
Construct2 monetisation-related plugins by cordova G+ community<br>
=======
Cordova monetisation related plugins G+ community<br>
https://plus.google.com/communities/116032133386884708413<br>
<br>
Construct2 monetisation related plugins by cordova G+ community<br>
>>>>>>> origin/master
https://plus.google.com/communities/117978754675005605917<br>

# Credits #
