Cordova Chartboost plugin
====================
# Overview #
Show chartboost full screen (static interstitial, video interstial), more apps, rewarded video ad

[android, ios] [cordova cli] [xdk]

Requires revmob account https://www.revmobmobileadnetwork.com

Android SDK Version 5.3.0 (May 7, 2015)
iOS SDK Version 5.3.0 (May 7, 2015)

I can't see any ads in my game - create a new publishing campaign in the Chartboost dashboard (takes 20 minutes to take effect)
https://answers.chartboost.com/hc/en-us/articles/201121969-I-can-t-see-any-ads-in-my-game

This is open source cordova plugin.

You can see Plugins For Cordova in one page: http://cranberrygame.github.io?referrer=github

# Change log #
```c
1.0.46
	Added Is showing full screen ad, Is showing more apps ad, Is showing rewarded video ad conditions.
1.0.47
	Added Loaded full screen ad, Loaded more apps ad, Loaded rewarded video ad conditions.
	Updated SDK
		Android SDK Version 5.3.0 (May 7, 2015)
		iOS SDK Version 5.3.0 (May 7, 2015)
```
# Install plugin #

## Cordova cli ##
https://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface - npm install -g cordova@4.1.2
```c
cordova plugin add com.cranberrygame.cordova.plugin.ad.chartboost
```

## Xdk ##
https://software.intel.com/en-us/intel-xdk - Download XDK - XDK PORJECTS - [specific project] - CORDOVA 3.X HYBRID MOBILE APP SETTINGS - PLUGINS - Third Party Plugins - Add a Third Party Plugin - Get Plugin from the Web -
```c
Name: revmob
Plugin ID: com.cranberrygame.cordova.plugin.ad.chartboost
[v] Plugin is located in the Apache Cordova Plugins Registry
```

## Cocoon ##
https://cocoon.io - Create project - [specific project] - Setting - Plugins - Custom - Git Url: https://github.com/cranberrygame/cordova-plugin-ad-chartboost.git - INSTALL - Save<br>

## Phonegap build service (config.xml) ##
https://build.phonegap.com/ - Apps - [specific project] - Update code - Zip file including config.xml
```c
<gap:plugin name="com.cranberrygame.cordova.plugin.ad.chartboost" source="plugins.cordova.io" />
```

## Construct2 ##
Download construct2 plugin: http://www.paywithapost.de/pay?id=4ef3f2be-26e8-4a04-b826-6680db13a8c8
<br>
Now all the native plugins are installed automatically: https://plus.google.com/102658703990850475314/posts/XS5jjEApJYV
# Server setting #
```c
```

<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/app_id.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign1.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign2.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign3.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign4.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign5.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign6.png"><br>
<img src="https://github.com/cranberrygame/cordova-plugin-ad-chartboost/blob/master/doc/publishing_campaign7.png"><br>

Test mode setting: 
https://www.chartboost.com - Login - DASHBOARD - [specific app] - App Settings - Test Mode: select Disabled or Enabled 

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
	//you can get free license key from https://play.google.com/store/apps/details?id=com.cranberrygame.pluginsforcordova
	//window.chartboost.setLicenseKey("yourEmailId@yourEmaildDamin.com", "yourFreeLicenseKey");

	window.chartboost.setUp(appId, appSignature);
	
	//
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
	//
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
	//
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
	window.chartboost.onRewardedVideoAdCompleted = function() {
		alert('onRewardedVideoAdCompleted');
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
window.chartboost.preloadFullScreenAd('Default');//option, download ad previously for fast show
window.chartboost.showFullScreenAd('Default');

window.chartboost.preloadMoreAppsAd('Default');//option, download ad previously for fast show
window.chartboost.showMoreAppsAd('Default');

window.chartboost.preloadRewardedVideoAd('Default');//option, download ad previously for fast show
window.chartboost.showRewardedVideoAd('Default');

alert(window.chartboost.loadedFullScreenAd());//boolean: true or false
alert(window.chartboost.loadedMoreAppsAd());//boolean: true or false
alert(window.chartboost.loadedRewardedVideoAd());//boolean: true or false

alert(window.chartboost.isShowingFullScreenAd());//boolean: true or false
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

Plugins For Cordova<br>
http://cranberrygame.github.io?referrer=github

# Credits #
