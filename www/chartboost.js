
module.exports = {

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
					else if (result == "onFullScreenAdShown") {
						if (self.onFullScreenAdShown)
							self.onFullScreenAdShown();
					}
					else if (result == "onFullScreenAdHidden") {
						 if (self.onFullScreenAdHidden)
							self.onFullScreenAdHidden();
					}
					//-----------
					else if (result == "onMoreAppsAdPreloaded") {
						if (self.onMoreAppsAdPreloaded)
							self.onMoreAppsAdPreloaded();
					}
					else if (result == "ondMoreAppsAdShown") {
						if (self.onMoreAppsAdShown)
							self.ondMoreAppsAdShown();
					}
					else if (result == "ondMoreAppsAdHidden") {
						 if (self.onMoreAppsAdHidden)
							self.onMoreAppsAdHidden();
					}
					//-----------
					else if (result == "onRewardedVideoAdPreloaded") {
						if (self.onRewardedVideoAdPreloaded)
							self.onRewardedVideoAdPreloaded();
					}
					else if (result == "onRewardedVideoAdShown") {
						if (self.onRewardedVideoAdShown)
							self.onRewardedVideoAdShown();
					}
					else if (result == "onRewardedVideoAdHidden") {
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
	onFullScreenAdPreloaded: null,
	onFullScreenAdShown: null,
	onFullScreenAdHidden: null,	
	onMoreAppsAdPreloaded: null,
	onMoreAppsAdShown: null,
	onMoreAppsAdHidden: null,	
	onRewardedVideoAdPreloaded: null,
	onRewardedVideoAdShown: null,
	onRewardedVideoAdHidden: null,
	onRewardedVideoAdCompleted: null
};
