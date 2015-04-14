#import <Cordova/CDV.h>

#import <Chartboost/Chartboost.h>
#import <Chartboost/CBNewsfeed.h>
#import <CommonCrypto/CommonDigest.h>
#import <AdSupport/AdSupport.h>

/*
#import <Chartboost/Chartboost.h>
#import <Chartboost/CBNewsfeed.h>
#import "AppDelegate.h"
#import <CommonCrypto/CommonDigest.h>
#import <AdSupport/AdSupport.h>
#import <Chartboost/Chartboost.h>
#import <UIKit/UIKit.h>
#import "ViewController.h"
#import <Chartboost/Chartboost.h>
#import <Chartboost/CBNewsfeed.h>
#import <Chartboost/CBAnalytics.h>
#import <StoreKit/StoreKit.h>
*/

@interface ChartboostPlugin : CDVPlugin <ChartboostDelegate, CBNewsfeedDelegate>{
	NSMutableArray* _queue;
}

@property NSString *callbackIdKeepCallback;

-(void) setUp:(CDVInvokedUrlCommand*)command;
-(void) preloadFullScreenAd:(CDVInvokedUrlCommand*)command;
-(void) showFullScreenAd:(CDVInvokedUrlCommand*)command;
-(void) preloadMoreAppsAd:(CDVInvokedUrlCommand*)command;
-(void) showMoreAppsAd:(CDVInvokedUrlCommand*)command;
-(void) preloadRewardedVideoAd:(CDVInvokedUrlCommand*)command;
-(void) showRewardedVideoAd:(CDVInvokedUrlCommand*)command;

@end

@implementation ChartboostPlugin

@synthesize callbackIdKeepCallback;

-(void) setUp:(CDVInvokedUrlCommand*)command {
	NSString* appId = [command.arguments objectAtIndex:0];
	NSString* appSignature = [command.arguments objectAtIndex:1];
	NSString* callbackId = command.callbackId;
	
    NSLog(@"%@", @"setUp");
    NSLog(@"%@", appId);
    NSLog(@"%@", appSignature);
	
	callbackIdKeepCallback = callbackId;

	[Chartboost startWithAppId:appId
				appSignature:appSignature
				delegate:self];	
}

//-----------
-(void) preloadFullScreenAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	
	NSLog(@"%@", @"preloadFullScreenAd");
	NSLog(@"%@", location);
	[Chartboost cacheInterstitial:location];
}

-(void) showFullScreenAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];

	NSLog(@"%@", @"showFullScreenAd");
	NSLog(@"%@", location);
    
	[Chartboost showInterstitial:location];
}

//-----------
-(void) preloadMoreAppsAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	
	NSLog(@"%@", @"preloadMoreAppsAd");
    NSLog(@"%@", location);
		
	[Chartboost cacheMoreApps:CBLocationHomeScreen];
}

-(void) showMoreAppsAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	
	NSLog(@"%@", @"showMoreAppsAd");
	NSLog(@"%@", location);
    
	[Chartboost showMoreApps:location];
}

//-----------
-(void) preloadRewardedVideoAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	
	NSLog(@"%@", @"preloadRewardedVideoAd");
	NSLog(@"%@", location);
    
    [Chartboost cacheRewardedVideo:location];	
}

-(void) showRewardedVideoAd:(CDVInvokedUrlCommand*)command {
	NSString* location = [command.arguments objectAtIndex:0];
	
	NSLog(@"%@", @"showRewardedVideoAd");
	NSLog(@"%@", location);
    
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

- (void)didCacheInterstitial:(NSString *)location {
	NSLog(@"%@", @"onFullScreenAdPreloaded");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onFullScreenAdPreloaded"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:command.callbackId];	
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
    NSLog(@"about to display interstitial at location %@", location);

    // For example:
    // if the user has left the main menu and is currently playing your game, return NO;
    
    // Otherwise return YES to display the interstitial
    return YES;
}

-(void) didDisplayInterstitial:(CBLocation)location{
	NSLog(@"%@", @"onFullScreenAdShown");

	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onFullScreenAdShown"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void)didFailToRecordClick:(CBLocation)location withError:(CBClickError)error {

}

- (void)didClickInterstitial:(CBLocation)location {

}

- (void)didCloseInterstitial:(CBLocation)location {
	NSLog(@"%@", @"onFullScreenAdHidden2");
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

- (void)didDismissInterstitial:(NSString *)location {
	NSLog(@"%@", @"onFullScreenAdHidden1");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onFullScreenAdHidden"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

//---------------------------

- (void)didCacheMoreApps:(CBLocation)location {
	NSLog(@"%@", @"onMoreAppsAdPreloaded");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdPreloaded"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void)didFailToLoadMoreApps:(CBLoadError)error {
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

- (BOOL)shouldDisplayMoreApps:(CBLocation)location {
    return YES;
}

- (void)didDisplayMoreApps:(CBLocation)location {
	NSLog(@"%@", @"onMoreAppsAdShown");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdShown"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void)didClickMoreApps:(CBLocation)location {
}

- (void)didCloseMoreApps:(CBLocation)location {
	NSLog(@"%@", @"onMoreAppsAdHidden2");
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

- (void)didDismissMoreApps:(NSString *)location {
	NSLog(@"%@", @"onMoreAppsAdHidden1");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onMoreAppsAdHidden"];
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

- (void)didPrefetchVideos {

}

- (void)didCacheRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"onRewardedVideoAdPreloaded");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdPreloaded"];
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

- (void)didFailToLoadRewardedVideo:(NSString *)location withError:(CBLoadError)error {
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

- (BOOL)shouldDisplayRewardedVideo:(CBLocation)location {
    return YES;
}

- (void)didDisplayRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"onRewardedVideoAdShown");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdShown"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

- (void)didClickRewardedVideo:(CBLocation)location {

}

- (void)didCloseRewardedVideo:(CBLocation)location {

}

- (void)didDismissRewardedVideo:(CBLocation)location {
	NSLog(@"%@", @"onRewardedVideoAdHidden1");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdHidden"];
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
- (void)didCompleteRewardedVideo:(CBLocation)location withReward:(int)reward {
    //NSLog(@"completed rewarded video view at location %@ with reward amount %d", location, reward);
	NSLog(@"%@", @"onRewardedVideoAdCompleted");
	
	CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"onRewardedVideoAdCompleted"];
	[pr setKeepCallbackAsBool:YES];
	[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
	//CDVPluginResult* pr = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
	//[pr setKeepCallbackAsBool:YES];
	//[self.commandDelegate sendPluginResult:pr callbackId:callbackIdKeepCallback];
}

//-----------------------------------------

#pragma mark - Video Delegate

- (void)willDisplayVideo:(CBLocation)location {

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
- (void)didCacheInPlay:(CBLocation)location {
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
- (void)didFailToLoadInPlay:(CBLocation)location
                  withError:(CBLoadError)error{
}
				  
//-----------------------------------------

#pragma mark - General Delegate

/*!
 @abstract
 Called after the App Store sheet is dismissed, when displaying the embedded app sheet.
 
 @discussion Implement to be notified of when the App Store sheet is dismissed.
 */
- (void)didCompleteAppStoreSheetFlow {
}

/*!
 @abstract
 Called if Chartboost SDK pauses click actions awaiting confirmation from the user.
 
 @discussion Use this method to display any gating you would like to prompt the user for input.
 Once confirmed call didPassAgeGate:(BOOL)pass to continue execution.
 */
- (void)didPauseClickForConfirmation {
}

@end