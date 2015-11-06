//Copyright (c) 2014 Sang Ki Kwon (Cranberrygame)
//Email: cranberrygame@yahoo.com
//Homepage: http://cranberrygame.github.io
//License: MIT (http://opensource.org/licenses/MIT)
#import <Cordova/CDV.h>
#import <Chartboost/Chartboost.h>

@interface ChartboostPlugin : CDVPlugin <ChartboostDelegate>

@property NSString *callbackIdKeepCallback;
//
@property NSString *email;
@property NSString *licenseKey_;
@property BOOL validLicenseKey;
//
@property NSString *appId;
@property NSString *appSignature;
//
@property BOOL interstitialAdPreload;
@property BOOL moreAppsAdPreload;
@property BOOL rewardedVideoAdPreload;

- (void) setLicenseKey: (CDVInvokedUrlCommand*)command;
- (void) setUp:(CDVInvokedUrlCommand*)command;
- (void) preloadFullScreenAd:(CDVInvokedUrlCommand*)command;
- (void) showFullScreenAd:(CDVInvokedUrlCommand*)command;
- (void) preloadMoreAppsAd:(CDVInvokedUrlCommand*)command;
- (void) showMoreAppsAd:(CDVInvokedUrlCommand*)command;
- (void) preloadRewardedVideoAd:(CDVInvokedUrlCommand*)command;
- (void) showRewardedVideoAd:(CDVInvokedUrlCommand*)command;

@end
