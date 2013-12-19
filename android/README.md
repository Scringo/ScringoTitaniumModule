# scringo Module

## Description

Now you can create and manage your mobile community with Scringo for Appcelerator Titanium platform. For more information go to <http://www.scringo.com>.
The Scringo version used is 2.5.3.

Note: Currently, there is no Facebook integration.

Note: Currently, Scringo's In-App Billing is disabled.

## Requirements

Add this to the <android /> node in tiapp.xml:

	<android xmlns:android="http://schemas.android.com/apk/res/android">
		<manifest package="com.scringo.sampleTest">
			<uses-permission android:name="com.android.vending.BILLING"/>
			<uses-permission android:name="android.permission.VIBRATE"/>
			<uses-permission android:name="android.permission.INTERNET"/>
			<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
			<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
			<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
			<uses-permission android:name="com.google.android.c2dm.permission.RECEIVE"/>
			<uses-permission android:name="android.permission.WAKE_LOCK"/>
			<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
			<application android:allowBackup="true">
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.radar.ScringoRadarActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.feed.ScringoFeedActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoProfileActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoFollowersActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.inbox.ScringoInboxActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.inbox.ScringoChatActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.invite.ScringoInviteActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.twitter.ScringoTwitterActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.invite.ScringoFindFriendsActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.feedback.ScringoFeedbackActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.inbox.ScringoSendToActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoProfileBridge"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoProfileEditBio"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.feedback.ScringoFeedbackRootActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.inbox.ScringoSystemMessageActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.invite.ScringoFindFriendsRootActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.invite.ScringoSuggestionsActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.apps.ScringoAppDiscoveryActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.chatroom.ScringoChatroomActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.chatroom.ScringoTopicActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoSignUpRootActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoSignUpActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoLoginRootActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoLoginActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoSignUpSettingsActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoForgotPasswordActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoChangePasswordActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.quiz.ScringoQuizzesActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.quiz.ScringoQuizActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.quiz.ScringoQuizIntroActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.quiz.ScringoQuizStoreActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.menu.ScringoMenuActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.profile.ScringoMyProfileActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.scores.ScringoScoresActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.quiz.ScringoSuggestQuestionActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.keyboard.ScringoStickerStoreActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.keyboard.ScringoStickerPackActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.keyboard.ScringoPhotoActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.scringo.features.ScringoWebViewActivity"/>
				<activity android:configChanges="orientation|keyboardHidden" android:name="com.facebook.LoginActivity"/>
				<service android:name="com.scringo.service.ScringoService"/>
				<meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/replace_this_facebook_app_id"/>
			</application>
		</manifest>
	</android>

## Accessing the scringo Module

To access this module from JavaScript, you would do the following:

	var scringo = require("com.scringo.ti.module");

The scringo variable is a reference to the Module object.	

## Reference

### scringo.initWithAppId

Initialize Scringo with the assigned Scringo App Id.

#### Arguments

Takes two arguments:

* appId[string]: Your App Id. You can get this from http://www.scringo.com
* completionBlk[function]: A function that is called when Scringo initialization is done.

#### Example

	scringo.initWithAppId("YOUR SCRINGO APP ID", function() {
			Ti.API.info("Scringo done initializing");
		}
	);

## Usage
```js
// This is a single context application with multiple windows in a stack
(function() {

    var win = Ti.UI.createWindow({
        backgroundColor : 'white',
    });

    var label = Ti.UI.createLabel();
    win.add(label);
    label.text = "Initializing ...";

    //Create a container for our buttons
    var vwTop = Ti.UI.createView({
        height : 50,
        layout : "vertical",
        height : Ti.UI.FILL,
        top : 20
    });
    win.add(vwTop);
    var scringo = require('com.scringo.ti.module');
    Ti.API.info("module is => " + scringo);

    var InboxBadge = Ti.UI.createButton({
        title : "Inbox Badge",
        width : 200,
        height : 40
    });
    vwTop.add(InboxBadge);

    InboxBadge.addEventListener('click', function() {
        Ti.API.info("Getting Inbox Badge");
        label.text = "Inbox Badge is " + scringo.getFeatureBadgeNumber(1);
    });

    var openSidebar = Ti.UI.createButton({
        title : "Open Sidebar",
        width : 200,
        height : 40
    });
    vwTop.add(openSidebar);

    openSidebar.addEventListener('click', function() {
        Ti.API.info("Opening Sidebar");
        scringo.addSidebar(true);
        scringo.openSidebar();
        label.text = "Scringo Sidebar opened";
    });

    var openMenu = Ti.UI.createButton({
        title : "Open Menu",
        width : 200,
        height : 40
    });
    vwTop.add(openMenu);

    openMenu.addEventListener('click', function() {
        Ti.API.info("Opening Menu");
        scringo.openMenu();
        label.text = "Scringo Menu Opened";
    });

    var postToFeed = Ti.UI.createButton({
        title : "Post",
        width : 200,
        height : 40
    });
    vwTop.add(postToFeed);

    postToFeed.addEventListener('click', function() {
        Ti.API.info("Posting to Feed with Image");
        scringo.postToFeed("Test Message From Appcelerator !!");
        label.text = "Feed Posted";
    });

    scringo.initWithAppId("TEST_APP", function() {
            Ti.API.info("Scringo done initializing");
            label.text = "Scringo Initialized";
        }
    );

    win.open();
})();
```

## Author

Guy Federovsky (support@scringo.com) 

## License

Copyright 2013 Scringo. Please see the LICENSE file included in the distribution for further details.
