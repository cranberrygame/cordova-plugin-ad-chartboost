
module.exports = {
	_loadedFullScreenAd: false,
	_loadedMoreAppsAd: false,
	_loadedRewardedVideoAd: false,
	_isShowingFullScreenAd: false,
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
					if (result == "onFullScreenAdPreloaded") {
						if (self.onFullScreenAdPreloaded)
							self.onFullScreenAdPreloaded();
					}
					else if (result == "onFullScreenAdLoaded") {
						self._loadedFullScreenAd = true;

						if (self.onFullScreenAdLoaded)
							self.onFullScreenAdLoaded();
					}
					else if (result == "onFullScreenAdShown") {
						self._loadedFullScreenAd = false;						
						self._isShowingFullScreenAd = true;
					
						if (self.onFullScreenAdShown)
							self.onFullScreenAdShown();
					}
					else if (result == "onFullScreenAdHidden") {
						self._isShowingFullScreenAd = false;
					
						 if (self.onFullScreenAdHidden)
							self.onFullScreenAdHidden();
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
				}
				else {
					//if (result["event"] == "onXXX") {
					//	//result["message"]
					//	if (self.onXXX)
					//		self.onXXX(result);
					//}
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
	preloadFullScreenAd: function(location) {
        cordova.exec(
			null,
            null,
            'ChartboostPlugin',
            'preloadFullScreenAd',
            [location]
        ); 
    },
    showFullScreenAd: function(location) {
		cordova.exec(
 			null,
            null,
            'ChartboostPlugin',
            'showFullScreenAd',
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
	loadedFullScreenAd: function() {
		return this._loadedFullScreenAd;
	},
	loadedMoreAppsAd: function() {
		return this._loadedMoreAppsAd;
	},
	loadedRewardedVideoAd: function() {
		return this._loadedRewardedVideoAd;
	},	
	isShowingFullScreenAd: function() {
		return this._isShowingFullScreenAd;
	},
	isShowingMoreAppsAd: function() {
		return this._isShowingMoreAppsAd;
	},
	isShowingRewardedVideoAd: function() {
		return this._isShowingRewardedVideoAd;
	},	
	onFullScreenAdPreloaded: null,
	onFullScreenAdLoaded: null,
	onFullScreenAdShown: null,
	onFullScreenAdHidden: null,
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
