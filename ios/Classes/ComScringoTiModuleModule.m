/**
 * Your Copyright Here
 *
 * Appcelerator Titanium is Copyright (c) 2009-2010 by Appcelerator, Inc.
 * and licensed under the Apache Public License (version 2)
 */
#import "ComScringoTiModuleModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import "TiApp.h"

//#import <Scringo/Scringo.h>
//#import <Scringo/ScringoChatRoomsViewController.h>
#import "Scringo.h"
#import "ScringoChatRoomsViewController.h"

@implementation ComScringoTiModuleModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"341f20e1-9bd6-4156-875d-1a96b632c903";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"com.scringo.ti.module";
}

#pragma mark Lifecycle

-(void)startup
{
	// this method is called when the module is first loaded
	// you *must* call the superclass
	[super startup];
	
	NSLog(@"[INFO] %@ loaded",self);
}

-(void)shutdown:(id)sender
{
	// this method is called when the module is being unloaded
	// typically this is during shutdown. make sure you don't do too
	// much processing here or the app will be quit forceably
	
	// you *must* call the superclass
	[super shutdown:sender];
}

-(void)resumed:(id)sender {
    NSString *urlStr = [[TiApp app].launchOptions objectForKey:@"url"];
    NSString *sourceApp = [[TiApp app].launchOptions objectForKey:@"source"];
    if (urlStr != nil && [urlStr length] > 0) {
        NSURL *url = [NSURL URLWithString:urlStr];
        [Scringo handleOpenURL:url];
    }
}

#pragma mark Cleanup 

-(void)dealloc
{
	// release any resources that have been retained by the module
	[super dealloc];
}

#pragma mark Internal Memory Management

-(void)didReceiveMemoryWarning:(NSNotification*)notification
{
	// optionally release any resources that can be dynamically
	// reloaded once memory is available - such as caches
	[super didReceiveMemoryWarning:notification];
}

#pragma mark Listener Notifications

-(void)_listenerAdded:(NSString *)type count:(int)count
{
	if (count == 1 && [type isEqualToString:@"my_event"])
	{
		// the first (of potentially many) listener is being added 
		// for event named 'my_event'
	}
}

-(void)_listenerRemoved:(NSString *)type count:(int)count
{
	if (count == 0 && [type isEqualToString:@"my_event"])
	{
		// the last listener called for event named 'my_event' has
		// been removed, we can optionally clean up any resources
		// since no body is listening at this point for that event
	}
}

#pragma Public APIs

-(void)initWithAppId:(id)args {
    ENSURE_UI_THREAD_1_ARG(args);
    NSString *appId = [TiUtils stringValue:[args objectAtIndex:0]];
    KrollCallback *doneCallback = [args objectAtIndex:1];
    ENSURE_TYPE(doneCallback, KrollCallback);
    if (appId != nil && [appId length] > 0) {
        [Scringo initWithAppId:appId completion:^{
            NSLog(@"[INFO] Scringo initialized with App Id '%@'", appId);
            [self _fireEventToListener:@"onComplete" withObject:nil listener:doneCallback thisObject:nil];
        }];
    }
}

-(void)setTestMode:(id)arg {
    ENSURE_SINGLE_ARG(arg, NSNumber);
    BOOL testMode = [arg boolValue];
    [Scringo setTestMode:testMode];
}

-(void)addSidebar:(id)arg {
    ENSURE_UI_THREAD_1_ARG(arg);
    ENSURE_SINGLE_ARG(arg, NSNumber);
    BOOL toLeft = [TiUtils boolValue:arg def:YES];
    [Scringo addSidebar:[TiApp app].window toLeft:toLeft];
}

-(void)openSidebar:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openSidebar];
}

-(void)closeSidebar:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo closeSidebar];
}

-(void)enableSwipeToOpenSidebar:(id)arg {
    [Scringo enableSwipeToOpenSidebar];
}

-(void)disableSwipeToOpenSidebar:(id)arg {
    [Scringo disableSwipeToOpenSidebar];
}

-(id)isSwipeOpenSidebar:(id)arg {
    BOOL result = [Scringo isSwipeOpenSidebar];
    return NUMBOOL(result);
}

-(id)isSidebarOpen:(id)arg {
    BOOL result = [Scringo isSidebarOpen];
    return NUMBOOL(result);
}

-(void)preloadActivity:(id)arg {
    ENSURE_SINGLE_ARG(arg, KrollCallback);
    KrollCallback *callBack = arg;
    [Scringo preloadActivityWithCompletion:^{
        [self _fireEventToListener:@"onComplete" withObject:nil listener:callBack thisObject:nil];
    }];
}

-(void)preloadChatRooms:(id)arg {
    ENSURE_SINGLE_ARG(arg, KrollCallback);
    KrollCallback *callBack = arg;
    [Scringo preloadChatRoomsWithCompletion:^{
        [self _fireEventToListener:@"onComplete" withObject:nil listener:callBack thisObject:nil];
    }];
}

-(void)preloadInbox:(id)arg {
    ENSURE_SINGLE_ARG(arg, KrollCallback);
    KrollCallback *callBack = arg;
    [Scringo preloadInboxWithCompletion:^{
        [self _fireEventToListener:@"onComplete" withObject:nil listener:callBack thisObject:nil];
    }];
}

-(void)preloadQuizzes:(id)arg {
    ENSURE_SINGLE_ARG(arg, KrollCallback);
    KrollCallback *callBack = arg;
    [Scringo preloadQuizzesWithCompletion:^{
        [self _fireEventToListener:@"onComplete" withObject:nil listener:callBack thisObject:nil];
    }];
}

-(void)openMenu:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openMenuWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openQuiz:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openQuizWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openChatRooms:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openChatRoomsWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openInbox:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openInboxWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openActivity:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openActivityWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openFeedback:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openFeedbackWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openFindInviteFriends:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openFindInviteFriendsWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openLeaderboard:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openLeaderboardWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openLogin:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openLoginWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openSignup:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openSignupWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openMyProfile:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openMyProfileWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openRadar:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openRadarWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openTalkToUs:(id)arg {
    ENSURE_UI_THREAD_0_ARGS;
    [Scringo openTalkToUsWithNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openChatRoomWithTopicId:(id)arg {
    ENSURE_UI_THREAD_1_ARG(arg);
    ENSURE_SINGLE_ARG(arg, NSNumber);
    int topicId = [arg intValue];
    if (topicId < 0) {
        NSLog(@"[ERROR] Scringo:openChatRoomWithTopicId: Got illegal Chat Room Topic Id (%d)", topicId);
        return;
    }
    
    [Scringo openChatRoomWithTopicId:topicId withNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openChatWithUser:(id)arg {
    ENSURE_UI_THREAD_1_ARG(arg);
    ENSURE_SINGLE_ARG(arg, NSString);
    NSString *userId = arg;
    if (userId == nil || [userId length] == 0) {
        NSLog(@"[ERROR] Scringo:openChatWithUser: Got an empty Scringo User Id");
        return;
    }
    
    [Scringo openChatWithUser:userId withNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)openProfileForUser:(id)arg {
    ENSURE_UI_THREAD_1_ARG(arg);
    ENSURE_SINGLE_ARG(arg, NSString);
    NSString *userId = arg;
    if (userId == nil || [userId length] == 0) {
        NSLog(@"[ERROR] Scringo:openProfileForUser: Got an empty Scringo User Id");
        return;
    }
    
    [Scringo openProfileForUser:userId withNavigationController:[TiApp app].controller.navigationController withScringoNavControllerEnabled:YES];
}

-(void)postToFeed:(id)arg {
    ENSURE_SINGLE_ARG(arg, NSString);
    NSString *message = arg;
    if (message == nil || [message length] == 0) {
        NSLog(@"[ERROR] Scringo:postToFeed: Got an empty Message");
        return;
    }
    
    [Scringo postToFeed:message];
}

-(void)postToFeedWithLink:(id)args {
    NSString *message = [args objectAtIndex:0];
    NSString *link = [args objectAtIndex:1];
    if (message == nil || [message length] == 0 || link == nil || [link length] == 0) {
        NSLog(@"[ERROR] Scringo:postToFeedWithLink: Got an empty Message or Link");
        return;
    }
    
    [Scringo postToFeed:message withLink:link];
}

-(void)postToFeedWithImage:(id)args {
    NSString *message = [args objectAtIndex:0];
    NSString *imageUrl = [args objectAtIndex:1];
    if (message == nil || [message length] == 0 || imageUrl == nil || [imageUrl length] == 0) {
        NSLog(@"[ERROR] Scringo:postToFeedWithImage: Got an empty Message or Image URL");
        return;
    }
    
    NSString *description = nil;
    if ([args count] > 2) {
        description = [args objectAtIndex:2];
    }
    NSString *subDesc = nil;
    if ([args count] > 3) {
        subDesc = [args objectAtIndex:3];
    }
    NSString *actionVerb = nil;
    if ([args count] > 4) {
        actionVerb = [args objectAtIndex:4];
    }
    NSString *actionId = nil;
    if ([args count] > 5) {
        actionId = [args objectAtIndex:5];
    }
    
    [Scringo postToFeed:message withImage:imageUrl withDescription:description withSubDesc:subDesc withActionVerb:actionVerb withActionId:actionId];
}

// Model
-(id)getFeatureBadgeNumber:(id)arg {
    ENSURE_SINGLE_ARG(arg, NSNumber);
    int featureId = [arg intValue];
    if (featureId == SCRINGO_FEATURE_UNKNOWN) {
        NSLog(@"[ERROR] Scringo:getFeatureBadgeNumber: Got illegal feature Id (%d)", featureId);
        return;
    }
    
    int badgeNumber = [Scringo getFeatureBadgeNumber:featureId];
    return NUMINT(badgeNumber);
}

-(id)getChatRoomTopics:(id)arg {
    NSArray *chatRooms = [Scringo getChatRoomTopics];
    return chatRooms;
}

-(id)setUserScore:(id)args {
    long score = [[TiUtils numberFromObject:[args objectAtIndex:0]] longValue];
    NSString *level = [TiUtils stringValue:[args objectAtIndex:1]];
    if (level == nil) {
        level = @"";
    }
    [Scringo setUserScore:score withLevel:level];
}

@end
