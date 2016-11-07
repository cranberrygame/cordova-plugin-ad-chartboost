Cordova Chartboost plugin
====================
# Overview #
Show chartboost interstitial (static interstitial, video interstial), more apps, rewarded video ad

[android, ios] [cordova cli] [xdk] [cocoon] [phonegap build service]

Requires chartboost account https://www.chartboost.com

Android SDK 6.1.0 (Nov. 19, 2015)<br>
iOS SDK 6.4.0 (Mar. 14, 2016)<br>

I can't see any ads in my game - create a new publishing campaign in the Chartboost dashboard (takes 20 minutes to take effect)
https://answers.chartboost.com/hc/en-us/articles/201121969-I-can-t-see-any-ads-in-my-game

This is open source cordova plugin.

You can see Cordova Plugins in one page: http://cranberrygame.github.io?referrer=github

# Change log #
```c
1.0.46
	Added Is showing full screen ad, Is showing more apps ad, Is showing rewarded video ad conditions.
1.0.47
	Added Loaded full screen ad, Loaded more apps ad, Loaded rewarded video ad conditions.
	Updated SDK
		Android SDK Version 5.3.0 (May 7, 2015)
		iOS SDK Version 5.3.0 (May 7, 2015)
1.0.51
	Downgrade SDK to fix ios armv7s build error
		iOS SDK Version 5.1.5 (Mar. 17, 2015)
1.0.74
	Fixed android interstitial ad issue.
	Changed "full screen ad" to "interstitial ad"
1.0.77
    Updated SDK (Android SDK 6.0.2, iOS SDK 6.0.1)
1.0.78
    Updated SDK (Android SDK 6.1.0, iOS SDK 6.1.1)
1.0.81
    Updated SDK (iOS SDK 6.4.0)
1.0.83
    Updated SDK (Android SDK 6.6.1, iOS SDK 6.5.2)
```
# Install plugin #

## Cordova cli ##
https://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface - npm install -g cordova@6.0.0
```c
cordova plugin add cordova-plugin-ad-chartboost
(when build error, use github url: cordova plugin add cordova plugin add https://github.com/cranberrygame/cordova-plugin-ad-chartboost)
```

## Xdk ##
https://software.intel.com/en-us/intel-xdk - Download XDK - XDK PORJECTS - [specific project] - CORDOVA HYBRID MOBILE APP SETTINGS - Plugin Management - Add Plugins to this Project - Third Party Plugins -
```c
Plugin Source: Cordova plugin registry
Plugin ID: cordova-plugin-ad-chartboost
```

## Cocoon ##
https://cocoon.io - Create project - [specific project] - Setting - Plugins - Custom - Git Url: https://github.com/cranberrygame/cordova-plugin-ad-chartboost.git - INSTALL - Save<br>

## Phonegap build service (config.xml) ##
https://build.phonegap.com/ - Apps - [specific project] - Update code - Zip file including config.xml
```c
<gap:plugin name="cordova-plugin-ad-chartboost" source="npm" />
```

## Construct2 ##
Download construct2 plugin<br>
https://dl.dropboxusercontent.com/u/186681453/pluginsforcordova/index.html<br>
How to install c2 native plugins in xdk, cocoon and cordova cli<br>
https://plus.google.com/102658703990850475314/posts/XS5jjEApJYV

# Server setting #
```c
```

<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/app_id1.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/app_id2.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign1.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign2.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign3.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign4.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign5.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign6.png"><br>
<img src="https://raw.githubusercontent.com/cranberrygame/cordova-plugin-ad-chartboost/master/doc/publishing_campaign7.png"><br>

Test mode setting:<br>
https://www.chartboost.com - Login - DASHBOARD - [specific app] - APP SETTINGS - Basic Settings - Test Mode: select Disabled or Enabled 

# API #
```javascript
var appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
var appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE";
/*
var appId;
var appSignature;
//android
if (navigator.userAgent.match(/Android/i)) {
	appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
	appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE";
}
//ios
else if (navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i)) {
	appId = "REPLACE_THIS_WITH_YOUR_APP_ID";
	appSignature = "REPLACE_THIS_WITH_YOUR_APP_SIGNATURE";
}
*/

document.addEventListener("deviceready", function(){
	//if no license key, 2% ad traffic share for dev support.
	//you can get paid license key: https://cranberrygame.github.io/request_cordova_ad_plugin_paid_license_key
	//window.chartboost.setLicenseKey("yourEmailId@yourEmaildDamin.com", "yourLicenseKey");

	window.chartboost.setUp(appId, appSignature);
	
	//
	window.chartboost.onInterstitialAdPreloaded = function(location) {
		alert('onInterstitialAdPreloaded: ' + location);
	};
	window.chartboost.onInterstitialAdLoaded = function(location) {
		alert('onInterstitialAdLoaded: ' + location);
	};
	window.chartboost.onInterstitialAdShown = function(location) {
		alert('onInterstitialAdShown: ' + location);
	};
	window.chartboost.onInterstitialAdHidden = function(location) {
		alert('onInterstitialAdHidden: ' + location);
	};
	//
	window.chartboost.onMoreAppsAdPreloaded = function(location) {
		alert('onMoreAppsAdPreloaded: ' + location);
	};
	window.chartboost.onMoreAppsAdLoaded = function(location) {
		alert('onMoreAppsAdLoaded: ' + location);
	};
	window.chartboost.onMoreAppsAdShown = function(location) {
		alert('onMoreAppsAdShown: ' + location);
	};
	window.chartboost.onMoreAppsAdHidden = function(location) {
		alert('onMoreAppsAdHidden: ' + location);
	};
	//
	window.chartboost.onRewardedVideoAdPreloaded = function(location) {
		alert('onRewardedVideoAdPreloaded: ' + location);
	};
	window.chartboost.onRewardedVideoAdLoaded = function(location) {
		alert('onRewardedVideoAdLoaded: ' + location);
	};
	window.chartboost.onRewardedVideoAdShown = function(location) {
		alert('onRewardedVideoAdShown: ' + location);
	};
	window.chartboost.onRewardedVideoAdHidden = function(location) {
		alert('onRewardedVideoAdHidden: ' + location);
	};	
	window.chartboost.onRewardedVideoAdCompleted = function(location) {
		alert('onRewardedVideoAdCompleted: ' + location);
	};
}, false);

/*
location parameter:
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
window.chartboost.preloadInterstitialAd('Default');//option, download ad previously for fast show
window.chartboost.showInterstitialAd('Default');

window.chartboost.preloadMoreAppsAd('Default');//option, download ad previously for fast show
window.chartboost.showMoreAppsAd('Default');

window.chartboost.preloadRewardedVideoAd('Default');//option, download ad previously for fast show
window.chartboost.showRewardedVideoAd('Default');

alert(window.chartboost.loadedInterstitialAd());//boolean: true or false
alert(window.chartboost.loadedMoreAppsAd());//boolean: true or false
alert(window.chartboost.loadedRewardedVideoAd());//boolean: true or false

alert(window.chartboost.isShowingInterstitialAd());//boolean: true or false
alert(window.chartboost.isShowingMoreAppsAd());//boolean: true or false
alert(window.chartboost.isShowingRewardedVideoAd());//boolean: true or false
```
# Examples #
<a href="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/example/basic/index.html">example/basic/index.html</a><br>

# Test #

<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/fullscreen_ad.png">

[![](http://img.youtube.com/vi/EQJLRbSKmPU/0.jpg)](https://www.youtube.com/watch?v=EQJLRbSKmPU&feature=youtu.be "Youtube")

You can also run following test apk.
https://dl.dropboxusercontent.com/u/186681453/pluginsforcordova/chartboost/apk.html

# Useful links #

Cordova Plugins<br>
http://cranberrygame.github.io?referrer=github

# Credits #
