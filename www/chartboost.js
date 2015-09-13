
module.exports = {
	_loadedInterstitialAd: false,
	_loadedMoreAppsAd: false,
	_loadedRewardedVideoAd: false,
	_isShowingInterstitialAd: false,
	_isShowingMoreAppsAd: false,
	_isShowingRewardedVideoAd: false,
	//
	setLicenseKey: function(email, licenseKey) {
		var self = this;	
        cordova.exec(
            null,
            null,
            'ChartboostPlugin',
            'setLicenseKey',			
            [email, licenseKey]
        ); 
    },
	setUp: function(appId, appSignature) {
		var self = this;	
        cordova.exec(
			function (result) {
				console.log('setUp succeeded.');
				
				if (typeof result == "string") {
/*				
					if (result == "onInterstitialAdPreloaded") {
					
//cranberrygame start; deprecated					
						if (self.onFullScreenAdPreloaded)
							self.onFullScreenAdPreloaded();
//cranberrygame end							
						if (self.onInterstitialAdPreloaded)
							self.onInterstitialAdPreloaded();
					}
					else if (result == "onInterstitialAdLoaded") {
						self._loadedInterstitialAd = true;

//cranberrygame start; deprecated						
						if (self.onFullScreenAdLoaded)
							self.onFullScreenAdLoaded();
//cranberrygame end						
						if (self.onInterstitialAdLoaded)
							self.onInterstitialAdLoaded();
					}
					else if (result == "onInterstitialAdShown") {
						self._loadedInterstitialAd = false;						
						self._isShowingInterstitialAd = true;
					
//cranberrygame start; deprecated					
						if (self.onFullScreenAdShown)
							self.onFullScreenAdShown();
//cranberrygame end						
						if (self.onInterstitialAdShown)
							self.onInterstitialAdShown();
					}
					else if (result == "onInterstitialAdHidden") {
						self._isShowingInterstitialAd = false;
					
//cranberrygame start; deprecated					
						 if (self.onFullScreenAdHidden)
							self.onFullScreenAdHidden();
//cranberrygame end							
						 if (self.onInterstitialAdHidden)
							self.onInterstitialAdHidden();
					}
					//
					else if (result == "onMoreAppsAdPreloaded") {
						if (self.onMoreAppsAdPreloaded)
							self.onMoreAppsAdPreloaded();
					}
					else if (result == "onMoreAppsAdLoaded") {
						self._loadedMoreAppsAd = true;
						
						if (self.onMoreAppsAdLoaded)
							self.onMoreAppsAdLoaded();
					}
					else if (result == "onMoreAppsAdShown") {
						self._loadedMoreAppsAd = false;
						self._isShowingMoreAppsAd = true;
					
						if (self.onMoreAppsAdShown)
							self.onMoreAppsAdShown();
					}
					else if (result == "onMoreAppsAdHidden") {
						self._isShowingMoreAppsAd = false;
					
						 if (self.onMoreAppsAdHidden)
							self.onMoreAppsAdHidden();
					}
					//
					else if (result == "onRewardedVideoAdPreloaded") {
						if (self.onRewardedVideoAdPreloaded)
							self.onRewardedVideoAdPreloaded();
					}
					else if (result == "onRewardedVideoAdLoaded") {
						self._loadedRewardedVideoAd = true;

						if (self.onRewardedVideoAdLoaded)
							self.onRewardedVideoAdLoaded();
					}
					else if (result == "onRewardedVideoAdShown") {
						self._loadedRewardedVideoAd = false;
						self._isShowingRewardedVideoAd = true;
					
						if (self.onRewardedVideoAdShown)
							self.onRewardedVideoAdShown();
					}
					else if (result == "onRewardedVideoAdHidden") {
						self._isShowingRewardedVideoAd = false;
					
						 if (self.onRewardedVideoAdHidden)
							self.onRewardedVideoAdHidden();
					}
					else if (result == "onRewardedVideoAdCompleted") {
						if (self.onRewardedVideoAdCompleted)
							self.onRewardedVideoAdCompleted();
					}
*/					
				}
				else {
					//var event = result["event"];
					//var location = result["message"];				
					//if (event == "onXXX") {
					//	if (self.onXXX)
					//		self.onXXX(location);
					//}
					
					var event = result["event"];
					var location = result["message"];					
					if (event == "onInterstitialAdPreloaded") {
					
//cranberrygame start; deprecated					
						if (self.onFullScreenAdPreloaded)
							self.onFullScreenAdPreloaded(location);
//cranberrygame end							
						if (self.onInterstitialAdPreloaded)
							self.onInterstitialAdPreloaded(location);
					}
					else if (event == "onInterstitialAdLoaded") {
						self._loadedInterstitialAd = true;

//cranberrygame start; deprecated						
						if (self.onFullScreenAdLoaded)
							self.onFullScreenAdLoaded(location);
//cranberrygame end						
						if (self.onInterstitialAdLoaded)
							self.onInterstitialAdLoaded(location);
					}
					else if (event == "onInterstitialAdShown") {
						self._loadedInterstitialAd = false;						
						self._isShowingInterstitialAd = true;
					
//cranberrygame start; deprecated					
						if (self.onFullScreenAdShown)
							self.onFullScreenAdShown(location);
//cranberrygame end						
						if (self.onInterstitialAdShown)
							self.onInterstitialAdShown(location);
					}
					else if (event == "onInterstitialAdHidden") {
						self._isShowingInterstitialAd = false;
					
//cranberrygame start; deprecated					
						 if (self.onFullScreenAdHidden)
							self.onFullScreenAdHidden(location);
//cranberrygame end							
						 if (self.onInterstitialAdHidden)
							self.onInterstitialAdHidden(location);
					}
					//
					else if (event == "onMoreAppsAdPreloaded") {
						if (self.onMoreAppsAdPreloaded)
							self.onMoreAppsAdPreloaded(location);
					}
					else if (event == "onMoreAppsAdLoaded") {
						self._loadedMoreAppsAd = true;
						
						if (self.onMoreAppsAdLoaded)
							self.onMoreAppsAdLoaded(location);
					}
					else if (event == "onMoreAppsAdShown") {
						self._loadedMoreAppsAd = false;
						self._isShowingMoreAppsAd = true;
					
						if (self.onMoreAppsAdShown)
							self.onMoreAppsAdShown(location);
					}
					else if (event == "onMoreAppsAdHidden") {
						self._isShowingMoreAppsAd = false;
					
						 if (self.onMoreAppsAdHidden)
							self.onMoreAppsAdHidden(location);
					}
					//
					else if (event == "onRewardedVideoAdPreloaded") {
						if (self.onRewardedVideoAdPreloaded)
							self.onRewardedVideoAdPreloaded(location);
					}
					else if (event == "onRewardedVideoAdLoaded") {
						self._loadedRewardedVideoAd = true;

						if (self.onRewardedVideoAdLoaded)
							self.onRewardedVideoAdLoaded(location);
					}
					else if (event == "onRewardedVideoAdShown") {
						self._loadedRewardedVideoAd = false;
						self._isShowingRewardedVideoAd = true;
					
						if (self.onRewardedVideoAdShown)
							self.onRewardedVideoAdShown(location);
					}
					else if (event == "onRewardedVideoAdHidden") {
						self._isShowingRewardedVideoAd = false;
					
						 if (self.onRewardedVideoAdHidden)
							self.onRewardedVideoAdHidden(location);
					}
					else if (event == "onRewardedVideoAdCompleted") {
						if (self.onRewardedVideoAdCompleted)
							self.onRewardedVideoAdCompleted(location);
					}					
				}
			},
			function (error) {
				console.log('setUp failed.');
			},
            'ChartboostPlugin',
            'setUp',			
			[appId, appSignature]
        ); 
    },
//cranberrygame start; deprecated	
	preloadFullScreenAd: function(location) {
        cordova.exec(
			null,
            null,
            'ChartboostPlugin',
            'preloadInterstitialAd',
            [location]
        ); 
    },
    showFullScreenAd: function(location) {
		cordova.exec(
 			null,
            null,
            'ChartboostPlugin',
            'showInterstitialAd',
            [location]
        ); 
    },
//cranberrygame end
	preloadInterstitialAd: function(location) {
        cordova.exec(
			null,
            null,
            'ChartboostPlugin',
            'preloadInterstitialAd',
            [location]
        ); 
    },
    showInterstitialAd: function(location) {
		cordova.exec(
 			null,
            null,
            'ChartboostPlugin',
            'showInterstitialAd',
            [location]
        ); 
    },	
	preloadMoreAppsAd: function(location) {
        cordova.exec(
 			null,
            null,
            'ChartboostPlugin',
            'preloadMoreAppsAd',
            [location]
        ); 
    },
    showMoreAppsAd: function(location) {
		cordova.exec(
 			null,
            null,
            'ChartboostPlugin',
            'showMoreAppsAd',
            [location]
        ); 
    },
	preloadRewardedVideoAd: function(location) {
        cordova.exec(
			null,
            null,
            'ChartboostPlugin',
            'preloadRewardedVideoAd',
            [location]
        ); 
    },
    showRewardedVideoAd: function(location) {
		cordova.exec(
			null,
            null,
            'ChartboostPlugin',
            'showRewardedVideoAd',
            [location]
        ); 
    },
//cranberrygame start; deprecated	
	loadedFullScreenAd: function() {
		return this._loadedInterstitialAd;
	},
//cranberrygame end
	loadedInterstitialAd: function() {
		return this._loadedInterstitialAd;
	},
	loadedMoreAppsAd: function() {
		return this._loadedMoreAppsAd;
	},
	loadedRewardedVideoAd: function() {
		return this._loadedRewardedVideoAd;
	},
//cranberrygame start; deprecated
	isShowingFullScreenAd: function() {
		return this._isShowingInterstitialAd;
	},
//cranberrygame end	
	isShowingInterstitialAd: function() {
		return this._isShowingInterstitialAd;
	},
	isShowingMoreAppsAd: function() {
		return this._isShowingMoreAppsAd;
	},
	isShowingRewardedVideoAd: function() {
		return this._isShowingRewardedVideoAd;
	},	
//cranberrygame start; deprecated	
	onFullScreenAdPreloaded: null,
	onFullScreenAdLoaded: null,
	onFullScreenAdShown: null,
	onFullScreenAdHidden: null,
//cranberrygame end
	onInterstitialAdPreloaded: null,
	onInterstitialAdLoaded: null,
	onInterstitialAdShown: null,
	onInterstitialAdHidden: null,
	//
	onMoreAppsAdPreloaded: null,
	onMoreAppsAdLoaded: null,
	onMoreAppsAdShown: null,
	onMoreAppsAdHidden: null,
	//
	onRewardedVideoAdPreloaded: null,
	onRewardedVideoAdLoaded: null,
	onRewardedVideoAdShown: null,
	onRewardedVideoAdHidden: null,
	onRewardedVideoAdCompleted: null
};
