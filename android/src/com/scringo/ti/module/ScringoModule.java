/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.scringo.ti.module;

import java.util.HashMap;

import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import android.app.Activity;
import android.util.Log;

import com.scringo.Scringo;
import com.scringo.ScringoEventHandler;

@Kroll.module(name="Scringo", id="com.scringo.ti.module")
public class ScringoModule extends KrollModule
{
	private class ScringoInfo {
		public Scringo scringo = null;
		boolean started = false;
		boolean wasSidebarAdded = false;
	}
	
	// Standard Debugging variables
	private static final String TAG = "ScringoModule";

//	private Scringo scringo = null;
	private HashMap<Activity, ScringoInfo> activityToScringoHash = new HashMap<Activity, ScringoInfo>();
//	private boolean wasSidebarAdded = false;
//	private boolean started = false;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;
	
	public ScringoModule()
	{
		super();
	}

	@Override
	public void onStart(Activity activity) {
		Log.i(TAG, "GFGF: onStart - called");
		Log.i(TAG, "GFGF: onStart - current Activity is: " + activity);
		super.onStart(activity);
		final ScringoInfo scringoInfo = activityToScringoHash.get(activity);
		if (scringoInfo == null) {
			return;
		}

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "GFGF: onStart - running on UI thread");
				if (scringoInfo.scringo != null && ! scringoInfo.started) {
					Log.i(TAG, "GFGF: onStart - Starting scringo");
					scringoInfo.scringo.onStart();
					scringoInfo.started = true;
				}
			}
		});
	}
	
	@Override
	public void onStop(Activity activity) {
		Log.i(TAG, "GFGF: onStop - called");
		Log.i(TAG, "GFGF: onStop - current Activity is: " + activity);
		super.onStop(activity);
		final ScringoInfo scringoInfo = activityToScringoHash.get(activity);
		if (scringoInfo == null) {
			return;
		}

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "GFGF: onStop - running on UI thread");
				if (scringoInfo.scringo != null && scringoInfo.started) {
					Log.i(TAG, "GFGF: onStop - Stopping scringo");
					scringoInfo.scringo.onStop();
					scringoInfo.started = false;
				}
			}
		});
	}

	@Kroll.method
	public void initWithAppId (String appId, final KrollFunction callBack) {
		Log.i(TAG, "GFGF: initWithAppId - called");
		Scringo.setAppId(appId);
		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		Log.i(TAG, "GFGF: initWithAppId - current Activity is: " + activity);
		final ScringoInfo scringoInfo = new ScringoInfo();
		scringoInfo.scringo = new Scringo(activity);
		activityToScringoHash.put(activity, scringoInfo);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "GFGF: initWithAppId - running on UI thread (scringo?" + scringoInfo.scringo + ")");
				scringoInfo.scringo.init(new ScringoEventHandler() {
					@Override
					public void onInitCompleted() {
						Log.i(TAG, "GFGF: initWithAppId - Initialization completed (scringo?" + scringoInfo.scringo + ")");
						if (! scringoInfo.started) {
							Log.i(TAG, "GFGF: initWithAppId - Starting scringo");
							scringoInfo.scringo.onStart();
							scringoInfo.started = true;
						}
						callBack.call(getKrollObject(), new HashMap<String,String>());
					}
				});
			}
		});
	}
	
	@Kroll.method
	public void setTestMode(boolean testMode) {
		Scringo.setDebugMode(testMode);
	}
	
	@Kroll.method
	public void setTwitterCredentials(String twitterAppToken, String twitterAppSecret) {
		Scringo.setTwitterCredentials(twitterAppToken, twitterAppSecret);
	}
	
	@Kroll.method
	public void setGoogleAppPublicKey(String publicKey) {
		Scringo.setGoogleAppPublicKey(publicKey);
	}

	@Kroll.method
	public void addSidebar (boolean toLeft) {
		Log.i(TAG, "GFGF: addSidebar - called with toLeft?" + toLeft);
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo == null || scringoInfo.wasSidebarAdded) {
			Log.i(TAG, "GFGF: addSidebar - scringo not initialized yet or sidebar already added");
			return;
		}

		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		Log.i(TAG, "GFGF: addSidebar - current Activity is: " + activity);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "GFGF: addSidebar - Sidebar is being added in UI thread");
				scringoInfo.scringo.addSidebar();
				if (scringoInfo.started) {
					Log.i(TAG, "GFGF: addSidebar - stopping Scringo (in order to start again later");
					scringoInfo.scringo.onStop();
				}
				scringoInfo.scringo.onStart();
				scringoInfo.started = true;
				scringoInfo.wasSidebarAdded = true;
			}
		});
	}
	
	@Kroll.method
	public void openSidebar () {
		Log.i(TAG, "GFGF: openSidebar - called");
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo == null || scringoInfo.scringo.isSidebarOpen()) {
			Log.i(TAG, "GFGF: openSidebar - scringo not initialized yet or sidebar already open");
			return;
		}

		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		Log.i(TAG, "GFGF: openSidebar - current Activity is: " + activity);
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "GFGF: openSidebar - Sidebar is being opened in UI thread");
				scringoInfo.scringo.openSidebar();
			}
		});
	}
	
	@Kroll.method
	public void closeSidebar () {
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo == null || ! scringoInfo.scringo.isSidebarOpen()) {
			return;
		}

		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				scringoInfo.scringo.closeSidebar();
			}
		});
	}

	@Kroll.method
	public void enableSwipeToOpenSidebar () {
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo != null) {
			scringoInfo.scringo.enableSwipeToOpenSidebar();
		}
	}
	
	@Kroll.method
	public void disableSwipeToOpenSidebar () {
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo != null) {
			scringoInfo.scringo.disableSwipeToOpenSidebar();
		}
	}
	
	@Kroll.method
	public boolean isSwipeOpenSidebar () {
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo == null) {
			return false;
		}
		
		return scringoInfo.scringo.isSwipeOpenSidebar();
	}
	
	@Kroll.method
	public boolean isSidebarOpen () {
		final ScringoInfo scringoInfo = activityToScringoHash.get(TiApplication.getAppCurrentActivity());
		if (scringoInfo == null) {
			return false;
		}
		
		return scringoInfo.scringo.isSidebarOpen();
	}
	
	@Kroll.method
	public void openMenu () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openMenu(activity);
			}
		});
	}
	
	@Kroll.method
	public void openQuiz () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openQuiz(activity);
			}
		});
	}
	
	@Kroll.method
	public void openInbox () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openInbox(activity);
			}
		});
	}
	
	@Kroll.method
	public void openChatRooms () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openChatRooms(activity);
			}
		});
	}
	
	@Kroll.method
	public void openRadar () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openRadar(activity);
			}
		});
	}
	
	@Kroll.method
	public void openFeedback () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openFeedback(activity);
			}
		});
	}
	
	@Kroll.method
	public void openLeaderboard () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openLeaderboard(activity);
			}
		});
	}
	
	@Kroll.method
	public void openActivity () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openActivityFeed(activity);
			}
		});
	}
	
	@Kroll.method
	public void openTalkToUs () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openTalkToUs(activity);
			}
		});
	}
	
	@Kroll.method
	public void openMyProfile () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openMyProfile(activity);
			}
		});
	}
	
	@Kroll.method
	public void openFindInviteFriends () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openFindFriends(activity);
			}
		});
	}
	
	@Kroll.method
	public void openLogin () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openLogin(activity);
			}
		});
	}
	
	@Kroll.method
	public void openSignup () {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openSignup(activity);
			}
		});
	}
	
	@Kroll.method
	public void openChatWithUser (final String otherUserId) {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openChat(activity, otherUserId);
			}
		});
	}
	
	@Kroll.method
	public void openProfileForUser (final String otherUserId) {
		TiApplication appContext = TiApplication.getInstance();
		final Activity activity = appContext.getCurrentActivity();
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Scringo.openProfile(activity, otherUserId);
			}
		});
	}
	
	@Kroll.method
	public void preloadInbox () {
		Scringo.preloadInbox();
	}
	
	@Kroll.method
	public void preloadActivity () {
		Scringo.preloadActivityFeed();
	}
	
	@Kroll.method
	public void preloadChatRooms () {
		Scringo.preloadChatRooms();
	}
	
	@Kroll.method
	public void setUserScore (int score, @Kroll.argument(optional=true) String level) {
		Scringo.setScore(score, level);
	}
	
	@Kroll.method
	public void postToFeed (String message) {
		Scringo.sendFeed(message, null, null, null, null, null);
	}
	
	@Kroll.method
	public void postToFeedWithLink (String message, String link) {
		Scringo.sendFeed(message, link, null, null, null, null);
	}
	
	@Kroll.method
	public void postToFeedWithImage (String message, String imageUrl, String description, String subDesc, @Kroll.argument(optional=true) String actionVerb, @Kroll.argument(optional=true) String actionId) {
		Scringo.sendFeed(message, imageUrl, description, subDesc, actionVerb, actionId);
	}
	
	@Kroll.method
	public int getFeatureBadgeNumber (int featureId) {
		int val = 0;
		switch (featureId) {
		case 0:
			val = Scringo.getBadgeValue(Scringo.ScringoFeature.ACTIVITY);
			break;
		case 1:
			val = Scringo.getBadgeValue(Scringo.ScringoFeature.INBOX);
			break;
		case 2:
			val = Scringo.getBadgeValue(Scringo.ScringoFeature.CHAT_ROOMS);
			break;
		case 3:
			val = Scringo.getBadgeValue(Scringo.ScringoFeature.FIND_FRIENDS);
			break;
		case 4:
			val = Scringo.getBadgeValue(Scringo.ScringoFeature.RADAR);
			break;
		}

		return val;
	}
}

