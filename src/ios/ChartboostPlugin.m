//Copyright (c) 2014 Sang Ki Kwon (Cranberrygame)
//Email: cranberrygame@yahoo.com
//Homepage: http://cranberrygame.github.io
//License: MIT (http://opensource.org/licenses/MIT)
#import "ChartboostPlugin.h"
#import <CommonCrypto/CommonDigest.h> //md5

@implementation ChartboostPlugin

@synthesize callbackIdKeepCallback;
//
@synthesize email;
@synthesize licenseKey_;
@synthesize validLicenseKey;
static NSString *TEST_APP_ID = @"55404ffdc909a62b5e90ed69";
static NSString *TEST_APP_SIGNATURE = @"37f4e779dc43837e7a6645002dffdeab0a97369b";
//
@synthesize appId;
@synthesize appSignature;
//
@synthesize interstitialAdPreload;
@synthesize moreAppsAdPreload;
@synthesize rewardedVideoAdPreload;

- (void) pluginInitialize {
    [super pluginInitialize];    
    //
}

- (void) setLicenseKey: (CDVInvokedUrlCommand*)command {
    NSString *email = [command.arguments objectAtIndex: 0];
    NSString *licenseKey = [command.arguments objectAtIndex: 1];
    NSLog(@"%@", email);
    NSLog(@"%@", licenseKey);
    
    [self.commandDelegate runInBackground:^{
        [self _setLicenseKey:email aLicenseKey:licenseKey];
    }];
}

- (void) setUp: (CDVInvokedUrlCommand*)command {
    //self.viewController
    //self.webView	
    //NSString *adUnitBanner = [command.arguments objectAtIndex: 0];
    //NSString *adUnitFullScreen = [command.arguments objectAtIndex: 1];
    //BOOL isOverlap = [[command.arguments objectAtIndex: 2] boolValue];
    //BOOL isTest = [[command.arguments objectAtIndex: 3] boolValue];
	//NSArray *zoneIds = [command.arguments objectAtIndex:4];	
    //NSLog(@"%@", adUnitBanner);
    //NSLog(@"%@", adUnitFullScreen);
    //NSLog(@"%d", isOverlap);
    //NSLog(@"%d", isTest);
	NSString* appId = [command.arguments objectAtIndex:0];
	NSString* appSignature = [command.arguments objectAtIndex:1];
	NSLog(@"%@", appId);
	NSLog(@"%@", appSignature);
	
    self.callbackIdKeepCallback = command.callbackId;
	
    //[self.commandDelegate runInBackground:^{
		[self _setUp:appId anAppSignature:appSignature];	
    //}];
}

- (void) preloadInterstitialAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);
	
    [self.commandDelegate runInBackground:^{
		[self _preloadInterstitialAd:location];
    }];		
}

- (void) showInterstitialAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);

    //[self.commandDelegate runInBackground:^{
		[self _showInterstitialAd:location];
    //}];
}

- (void) preloadMoreAppsAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);

    [self.commandDelegate runInBackground:^{
		[self _preloadMoreAppsAd:location];
    }];		
}

- (void) showMoreAppsAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);

    //[self.commandDelegate runInBackground:^{
		[self _showMoreAppsAd:location];
    //}];
}

- (void) preloadRewardedVideoAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);

   [self.commandDelegate runInBackground:^{
		[self _preloadRewardedVideoAd:location];
    }];
}

- (void) showRewardedVideoAd: (CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	NSLog(@"%@", location);

    //[self.commandDelegate runInBackground:^{
		[self _showRewardedVideoAd:location];
    //}];
}

- (void) _setLicenseKey:(NSString *)email aLicenseKey:(NSString *)licenseKey {
	self.email = email;
	self.licenseKey_ = licenseKey;
	
	//
	NSString *str1 = [self md5:[NSString stringWithFormat:@"cordova-plugin-: %@", email]];
	NSString *str2 = [self md5:[NSString stringWithFormat:@"cordova-plugin-ad-chartboost: %@", email]];
	NSString *str3 = [self md5:[NSString stringWithFormat:@"com.cranberrygame.cordova.plugin.: %@", email]];
	NSString *str4 = [self md5:[NSString stringWithFormat:@"com.cranberrygame.cordova.plugin.ad.chartboost: %@", email]];
	NSString *str5 = [self md5:[NSString stringWithFormat:@"com.cranberrygame.cordova.plugin.ad.video.chartboost: %@", email]];
	if(licenseKey_ != Nil && ([licenseKey_ isEqualToString:str1] || [licenseKey_ isEqualToString:str2] || [licenseKey_ isEqualToString:str3] || [licenseKey_ isEqualToString:str4] || [licenseKey_ isEqualToString:str5])){
		self.validLicenseKey = YES;
		NSArray *excludedLicenseKeys = [NSArray arrayWithObjects: @"xxx", nil];
		for (int i = 0 ; i < [excludedLicenseKeys count] ; i++) {
			if([[excludedLicenseKeys objectAtIndex:i] isEqualToString:licenseKey]) {
				self.validLicenseKey = NO;
				break;
			}
		}
	}
	else {
		self.validLicenseKey = NO;
	}
	if (self.validLicenseKey)
		NSLog(@"valid licenseKey");
	else {
		NSLog(@"invalid licenseKey");
		//UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Alert" message:@"Cordova Chartboost: invalid email / license key. You can get free license key from https://play.google.com/store/apps/details?id=com.cranberrygame.pluginsforcordova" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
		//[alert show];
	}
}

- (NSString*) md5:(NSString*) input {
    const char *cStr = [input UTF8String];
    unsigned char digest[16];
    CC_MD5( cStr, strlen(cStr), digest ); // This is the md5 call
    
    NSMutableString *output = [NSMutableString stringWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    
    for(int i = 0; i < CC_MD5_DIGEST_LENGTH; i++)
        [output appendFormat:@"%02x", digest[i]];
    
    return  output;
}

- (void) _setUp:(NSString *)appId anAppSignature:(NSString *)appSignature {
	self.appId = appId;
	self.appSignature = appSignature;

	if (!validLicenseKey) {
		if (arc4random() % 100 <= 1) {//0 ~ 99		
			self.appId = TEST_APP_ID;
			self.appSignature = TEST_APP_SIGNATURE;
		}
	}
	
	//
	[Chartboost startWithAppId:self.appId appSignature:self.appSignature delegate:self];
}

-(void) _preloadInterstitialAd:(NSString *)location {
	self.interstitialAdPreload = YES;	
	
	[Chartboost cacheInterstitial:location];
}

-(void) _showInterstitialAd:(NSString *)location {
	self.interstitialAdPreload = NO;	
	
	[Chartboost showInterstitial:location];
}

-(void) _preloadMoreAppsAd:(NSString *)location {
	self.moreAppsAdPreload = YES;	
	
	[Chartboost cacheMoreApps:location];
}

-(void) _showMoreAppsAd:(NSString *)location {
	self.moreAppsAdPreload = NO;	
	
	[Chartboost showMoreApps:location];
}

-(void) _preloadRewardedVideoAd:(NSString *)location {
	self.rewardedVideoAdPreload = YES;	
	
    [Chartboost cacheRewardedVideo:location];	
}

-(void) _showRewardedVideoAd:(NSString *)location {
	self.rewardedVideoAdPreload = NO;	
	
	[Chartboost showRewardedVideo:location];
}

//-----------------------------------------------------

//ChartboostDelegate

-(BOOL) shouldRequestInterstitialsInFirstSession {
	return NO;
}

// Called before requesting an interstitial via the Chartboost API server.
-(BOOL) shouldRequestInterstitial:(CBLocation)location{
	return YES; 
}

- (void) didCacheInterstitial:(NSString *)location {
	NSLog(@"%@", @"didCacheInterstitial");

	if(interstitialAdPreload) {
		NSDictionary* result = @{
			@"event":@"onInterstitialAdPreloaded",
			@"message":location
		};	
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onInterstitialAdPreloaded"];
		CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
		[pr setKeepCallbackAsBool:YES];
		[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
		//[pr setKeepCallbackAsBool:YES];
		//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];			
	}
	
	NSDictionary* result = @{
		@"event":@"onInterstitialAdLoaded",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onInterstitialAdLoaded"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void) didFailToLoadInterstitial:(NSString *)location withError:(CBLoadError)error {
    switch(error){
        case CBLoadErrorInternetUnavailable: {
            NSLog(@"Failed to load Interstitial, no Internet connection !");
        } break;
        case CBLoadErrorInternal: {
            NSLog(@"Failed to load Interstitial, internal error !");
        } break;
        case CBLoadErrorNetworkFailure: {
            NSLog(@"Failed to load Interstitial, network error !");
        } break;
        case CBLoadErrorWrongOrientation: {
            NSLog(@"Failed to load Interstitial, wrong orientation !");
        } break;
        case CBLoadErrorTooManyConnections: {
            NSLog(@"Failed to load Interstitial, too many connections !");
        } break;
        case CBLoadErrorFirstSessionInterstitialsDisabled: {
            NSLog(@"Failed to load Interstitial, first session !");
        } break;
        case CBLoadErrorNoAdFound : {
            NSLog(@"Failed to load Interstitial, no ad found !");
        } break;
        case CBLoadErrorSessionNotStarted : {
            NSLog(@"Failed to load Interstitial, session not started !");
        } break;
        case CBLoadErrorNoLocationFound : {
            NSLog(@"Failed to load Interstitial, missing location parameter !");
        } break;
        default: {
            NSLog(@"Failed to load Interstitial, unknown error !");
        }
    }
}

-(BOOL) shouldDisplayInterstitial:(NSString *)location {
    NSLog(@"shouldDisplayInterstitial %@", location);

    // For example:
    // if the user has left the main menu and is currently playing your game, return NO;
    
    // Otherwise return YES to display the interstitial
    return YES;
}

-(void) didDisplayInterstitial:(CBLocation)location{
	NSLog(@"%@", @"didDisplayInterstitial");

	NSDictionary* result = @{
		@"event":@"onInterstitialAdShown",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onInterstitialAdShown"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void) didFailToRecordClick:(CBLocation)location withError:(CBClickError)error {

}

- (void) didClickInterstitial:(CBLocation)location {

}

- (void) didCloseInterstitial:(CBLocation)location {
	NSLog(@"%@", @"didCloseInterstitial");
}

/*
 * didDismissInterstitial
 *
 * This is called when an interstitial is dismissed
 *
 * Is fired on:
 * - Interstitial click
 * - Interstitial close
 *
 */

- (void) didDismissInterstitial:(NSString *)location {
	NSLog(@"%@", @"didDismissInterstitial");
	
	NSDictionary* result = @{
		@"event":@"onInterstitialAdHidden",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onInterstitialAdHidden"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

//---------------------------

- (void) didCacheMoreApps:(CBLocation)location {
	NSLog(@"%@", @"didCacheMoreApps");
	
	if(moreAppsAdPreload) {
		NSDictionary* result = @{
			@"event":@"onMoreAppsAdPreloaded",
			@"message":location
		};		
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdPreloaded"];
		CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
		[pr setKeepCallbackAsBool:YES];
		[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
		//[pr setKeepCallbackAsBool:YES];
		//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];			
	}
	
	NSDictionary* result = @{
		@"event":@"onMoreAppsAdLoaded",
		@"message":location
	};		
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdLoaded"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void) didFailToLoadMoreApps:(CBLoadError)error {
    switch(error){
        case CBLoadErrorInternetUnavailable: {
            NSLog(@"Failed to load More Apps, no Internet connection !");
        } break;
        case CBLoadErrorInternal: {
            NSLog(@"Failed to load More Apps, internal error !");
        } break;
        case CBLoadErrorNetworkFailure: {
            NSLog(@"Failed to load More Apps, network error !");
        } break;
        case CBLoadErrorWrongOrientation: {
            NSLog(@"Failed to load More Apps, wrong orientation !");
        } break;
        case CBLoadErrorTooManyConnections: {
            NSLog(@"Failed to load More Apps, too many connections !");
        } break;
        case CBLoadErrorFirstSessionInterstitialsDisabled: {
            NSLog(@"Failed to load More Apps, first session !");
        } break;
        case CBLoadErrorNoAdFound: {
            NSLog(@"Failed to load More Apps, Apps not found !");
        } break;
        case CBLoadErrorSessionNotStarted : {
            NSLog(@"Failed to load More Apps, session not started !");
        } break;
        default: {
            NSLog(@"Failed to load More Apps, unknown error !");
        }
    }
}

- (BOOL) shouldDisplayMoreApps:(CBLocation)location {
    return YES;
}

- (void) didDisplayMoreApps:(CBLocation)location {
	NSLog(@"%@", @"didDisplayMoreApps");
	
	NSDictionary* result = @{
		@"event":@"onMoreAppsAdShown",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdShown"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void) didClickMoreApps:(CBLocation)location {
	NSLog(@"%@", @"didClickMoreApps");
}

- (void) didCloseMoreApps:(CBLocation)location {
	NSLog(@"%@", @"didCloseMoreApps");
}

/*
 * didDismissMoreApps
 *
 * This is called when the more apps page is dismissed
 *
 * Is fired on:
 * - More Apps click
 * - More Apps close
 *
 */

- (void) didDismissMoreApps:(NSString *)location {
	NSLog(@"%@", @"didDismissMoreApps");
	
	NSDictionary* result = @{
		@"event":@"onMoreAppsAdHidden",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdHidden"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

//---------------------------

/*!
 @abstract
 Called after videos have been successfully prefetched.
 
 @discussion Implement to be notified of when the prefetching process has finished successfully.
 */

- (void) didPrefetchVideos {
	NSLog(@"%@", @"didPrefetchVideos");
}

- (void) didCacheRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"didCacheRewardedVideo");

	if(moreAppsAdPreload) {
		NSDictionary* result = @{
			@"event":@"onRewardedVideoAdPreloaded",
			@"message":location
		};	
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdPreloaded"];
		CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
		[pr setKeepCallbackAsBool:YES];
		[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
		//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
		//[pr setKeepCallbackAsBool:YES];
		//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];			
	}
	
	NSDictionary* result = @{
		@"event":@"onRewardedVideoAdLoaded",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdLoaded"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

/*
 * didFailToLoadRewardedVideo
 *
 * This is called when a Rewarded Video has failed to load. The error enum specifies
 * the reason of the failure
 */

- (void) didFailToLoadRewardedVideo:(NSString *)location withError:(CBLoadError)error {
    switch(error){
        case CBLoadErrorInternetUnavailable: {
            NSLog(@"Failed to load Rewarded Video, no Internet connection !");
        } break;
        case CBLoadErrorInternal: {
            NSLog(@"Failed to load Rewarded Video, internal error !");
        } break;
        case CBLoadErrorNetworkFailure: {
            NSLog(@"Failed to load Rewarded Video, network error !");
        } break;
        case CBLoadErrorWrongOrientation: {
            NSLog(@"Failed to load Rewarded Video, wrong orientation !");
        } break;
        case CBLoadErrorTooManyConnections: {
            NSLog(@"Failed to load Rewarded Video, too many connections !");
        } break;
        case CBLoadErrorFirstSessionInterstitialsDisabled: {
            NSLog(@"Failed to load Rewarded Video, first session !");
        } break;
        case CBLoadErrorNoAdFound : {
            NSLog(@"Failed to load Rewarded Video, no ad found !");
        } break;
        case CBLoadErrorSessionNotStarted : {
            NSLog(@"Failed to load Rewarded Video, session not started !");
        } break;
        case CBLoadErrorNoLocationFound : {
            NSLog(@"Failed to load Rewarded Video, missing location parameter !");
        } break;
        default: {
            NSLog(@"Failed to load Rewarded Video, unknown error !");
        }
    }
}

- (BOOL) shouldDisplayRewardedVideo:(CBLocation)location {
    return YES;
}

- (void) didDisplayRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"didDisplayRewardedVideo");
	
	NSDictionary* result = @{
		@"event":@"onRewardedVideoAdShown",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdShown"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void) didClickRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"didClickRewardedVideo");
}

- (void) didCloseRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"didCloseRewardedVideo");
}

- (void) didDismissRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"didDismissRewardedVideo");
	
	NSDictionary* result = @{
		@"event":@"onRewardedVideoAdHidden",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdHidden"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

/*
 * didCompleteRewardedVideo
 *
 * This is called when a rewarded video has been viewed
 *
 * Is fired on:
 * - Rewarded video completed view
 *
 */
- (void) didCompleteRewardedVideo:(CBLocation)location withReward:(int)reward {
    //NSLog(@"completed rewarded video view at location %@ with reward amount %d", location, reward);
	NSLog(@"%@", @"didCompleteRewardedVideo");
	
	NSDictionary* result = @{
		@"event":@"onRewardedVideoAdCompleted",
		@"message":location
	};	
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdCompleted"];
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:result];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

//-----------------------------------------

#pragma mark - Video Delegate

- (void) willDisplayVideo:(CBLocation)location {
	NSLog(@"%@", @"willDisplayVideo");
}

//-----------------------------------------

#pragma mark - InPlay Delegate

/*!
 @abstract
 Called after an InPlay object has been loaded from the Chartboost API
 servers and cached locally.
 
 @param location The location for the Chartboost impression type.
 
 @discussion Implement to be notified of when an InPlay object has been loaded from the Chartboost API
 servers and cached locally for a given CBLocation.
 */
- (void) didCacheInPlay:(CBLocation)location {
	NSLog(@"%@", @"didCacheInPlay");
}

/*!
 @abstract
 Called after a InPlay has attempted to load from the Chartboost API
 servers but failed.
 
 @param location The location for the Chartboost impression type.
 
 @param error The reason for the error defined via a CBLoadError.
 
 @discussion Implement to be notified of when an InPlay has attempted to load from the Chartboost API
 servers but failed for a given CBLocation.
 */
- (void) didFailToLoadInPlay:(CBLocation)location withError:(CBLoadError)error{
	NSLog(@"%@", @"didFailToLoadInPlay");
}
				  
//-----------------------------------------

#pragma mark - General Delegate

/*!
 @abstract
 Called after the App Store sheet is dismissed, when displaying the embedded app sheet.
 
 @discussion Implement to be notified of when the App Store sheet is dismissed.
 */
- (void) didCompleteAppStoreSheetFlow {
	NSLog(@"%@", @"didCompleteAppStoreSheetFlow");
}

/*!
 @abstract
 Called if Chartboost SDK pauses click actions awaiting confirmation from the user.
 
 @discussion Use this method to display any gating you would like to prompt the user for input.
 Once confirmed call didPassAgeGate:(BOOL)pass to continue execution.
 */
- (void) didPauseClickForConfirmation {
	NSLog(@"%@", @"didPauseClickForConfirmation");
}

@end