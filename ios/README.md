# scringo Module

## Description

Now you can create and manage your mobile community with Scringo for Appcelerator Titanium platform. For more information go to <http://www.scringo.com>.
The Scringo version used is 2.5.7.

## Requirements

Add the contents of ScringoNibs.zip to your app project Resources/iphone folder.

Note: This module cannot co-exist with Titanium Facebook module as it requires newer version of it. Hence, Facebook SDK should be downloaded (from https://developers.facebook.com/docs/ios/getting-started) and installed into the default location (~/Documents/FacebookSDK) for the module to function properly.

Note: Facebook integration requires Titanium 3.2 and above.

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
var win = Ti.UI.createWindow({
	backgroundColor:'white',
	exitOnClose: true
});
var label = Ti.UI.createLabel({bottom:50});
win.add(label);
label.text = "Initializing ...";

//Create a container for our buttons
var vwTop = Ti.UI.createView({
	width : 300,
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
    height : 60
});
vwTop.add(InboxBadge);

InboxBadge.addEventListener('click', function() {
    Ti.API.info("Getting Inbox Badge");
    label.text = "Inbox Badge is " + scringo.getFeatureBadgeNumber(1);
});

var openSidebar = Ti.UI.createButton({
    title : "Open Sidebar",
    width : 200,
    height : 60
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
    height : 60
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
    height : 60
});
vwTop.add(postToFeed);

postToFeed.addEventListener('click', function() {
    Ti.API.info("Posting to Feed with Image");
    scringo.postToFeed("Test Message From Appcelerator !!");
    label.text = "Feed Posted";
});

var newWindowButton = Ti.UI.createButton({
	title : "New Window",
	width : 200,
	height : 60
});
vwTop.add(newWindowButton);

newWindowButton.addEventListener('click', function() {
	Ti.API.info("Opening new Window");
	var win2 = Ti.UI.createWindow({
		backgroundColor : 'yellow'
	});
 
	win2.addEventListener('open', function() {
		test.initWithAppId("YOUR SCRINGO APP ID", function() {
			Ti.API.info("Scringo done initializing (2)");
			label.text = "Scringo Initialized (2)";
			test.addSidebar(true);
		});
	});

	win2.open();
});

win.addEventListener('open', function() {
	test.initWithAppId("YOUR SCRINGO APP ID", function() {
		Ti.API.info("Scringo done initializing");
		label.text = "Scringo Initialized";
		test.addSidebar(true);
	});
});

test.setTestMode(true);
var osname = Ti.Platform.osname;
Ti.API.info("OS Name: '" + osname + "'");
if (osname === 'android') {
	Ti.API.info("Setting Twitter credentials for android platform");
	test.setTwitterCredentials("YOUR TWITTER APP TOKEN", "YOUR TWITTER APP SECRET");
}

win.open();
```

## Author

Guy Federovsky (support@scringo.com) 

## License

Copyright 2013 Scringo. Please see the LICENSE file included in the distribution for further details.
