package org.patneu.simpleflash;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * The main class of this application that handles most of it's functionality
 * (or at least delegates it to it's inner classes). <br/><br/>
 * 
 * @author Copyright 2014 Patrick Neumann. <br/><br/>
 * 
 * <b>License:</b> Licensed under the Apache License, Version 2.0 (the
 *                 "License"); you may use this application - as well as this
 *                 file - only in compliance with the License (or one of the
 *                 other licenses mentioned under "Additional licenses"). You
 *                 may obtain a copy of the License at
 *                 http://opensource.org/licenses/Apache-2.0 . <br/><br/>
 * 
 * <b>Liability disclaimer:</b> In no event unless required by applicable law or
 *                              agreed to in writing will any copyright holder,
 *                              or any other party who modifies and/or conveys
 *                              this application - as well as this file - as
 *                              permitted by the specified license(s), be liable
 *                              to you for any damages, including any general,
 *                              special, incidental or consequential damages
 *                              arising out of the use or inability to use this
 *                              application - as well as this file - (including
 *                              but not limited to loss of data or data being
 *                              rendered inaccurate or losses sustained by you
 *                              or third parties or a failure of this
 *                              application - as well as this file - to operate
 *                              with any other programs), even if such holder or
 *                              other party has been advised of the possibility
 *                              of such damages. There is no warranty for this
 *                              application - as well as this file -, to the
 *                              extend permitted by applicable law. Except when
 *                              otherwise stated in writing the copyright
 *                              holders and/or other parties provide this
 *                              application - as well as this file - "as is"
 *                              without warranty of any kind, either expressed
 *                              or implied, including, but not limited to, the
 *                              implied warranties of liability for
 *                              merchantability or fitness for a particular
 *                              purpose. The entire risk as to the quality and
 *                              performance of this application - as well as
 *                              this file - is with you. Should this application
 *                              - as well as this file - prove defective, you
 *                              assume the cost of all necessary servicing,
 *                              repair or correction. If the liability
 *                              disclaimer provided cannot be given local legal
 *                              effect according to its terms, reviewing courts
 *                              shall apply local law that most closely
 *                              approximates an absolute waiver of all civil
 *                              liability in connection with this application -
 *                              as well as this file -, unless a warranty or
 *                              assumption of liability accompanies a copy of
 *                              this application - as well as this file - in
 *                              return for a fee. If there are differences
 *                              between this liability disclaimer and a
 *                              liability disclaimer provided by any license(s)
 *                              under which the use of this application - as
 *                              well as this file - is permitted, this version
 *                              shall apply. If there are differences or
 *                              obscurities between this liability disclaimer
 *                              and any version of it translated for
 *                              convenience, this english version shall apply.
 *                              <br/><br/>
 * 
 * <b>Additional licenses:</b> In addition to the Apache License mentioned under
 *                             "License" you may also use this application - as
 *                             well as this file - in compliance with one of the
 *                             additional licenses mentioned in the
 *                             "licenses.txt" file of this application. A copy
 *                             of the corresponding license conditions is also
 *                             included and/or linked to in this file. You are
 *                             free to use the one of those licenses that suits
 *                             your needs best. <br/><br/>
 * 
 * <b>Third party software:</b> This application - as well as this file - uses
 *                              third party software of the Android Open Source
 *                              Project - namely the android Software
 *                              Development Kit ("SDK") and android v4 and v7-
 *                              appcompat Support Librarys. This software is
 *                              licensed under the Apache License, Version 2.0;
 *                              to obtain a copy of this license see the
 *                              "License" section or the "licenses.txt" file of
 *                              this application.
 * 
 * @since Version 1.0
 */
public class SimpleFlash extends ActionBarActivity
{
    /**
     * An Activity result indicating the demand to {@link
     * ActionBarActivity#finish() finish} all Activities of the application.
     * <br/><br/>
     * 
     * @since Version 1.0
     */
    public static final int RESULT_FINISHED = 2;
    
    private boolean lightOn;
    
    /**
     * The main button of the Activity; used to turn the light on and off. <br/>
     * <br/>
     * 
     * @see #setLightOn(boolean)
     * @see FlashLightManager#setFlashLightOn(boolean)
     * 
     * @since Version 1.0
     */
    protected Button onOffButton;
    /**
     * A checkbox used to set wether the light should be turned on when the
     * application starts. <br/><br/>
     * 
     * @see SettingsManager#setOnStart(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox onStartCheckbox;
    /**
     * A checkbox used to set wether the light should stay turned on when the
     * Activity is paused or stopped. <br/><br/>
     * 
     * @see SettingsManager#setInBackground(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox inBackgroundCheckbox;
    
    /**
     * A {@link MenuItem} used to lock or unlock the screen. <br/><br/>
     * 
     * @see LockManager#setScreenLocked(boolean)
     * 
     * @since Version 1.0
     */
    protected MenuItem lockItem;
    /**
     * A {@link MenuItem} used to start the {@link SettingsActivity} <br/><br/>
     * 
     * @since Version 1.0
     */
    protected MenuItem settingsItem;
    /**
     * A {@link MenuItem} used to {@link ActionBarActivity#finish() finish} the
     * Activity. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected MenuItem exitItem;
    /**
     * A {@link MenuItem} used to start the {@link AboutActivity} <br/><br/>
     * 
     * @since Version 1.0
     */
    protected MenuItem aboutItem;
    
    /**
     * A reference to the Activity's context, that may be used by it's inner
     * classes. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected final Context context = (Context) this;
    
    /**
     * Inner class that handles the saving and loading of the user preferences.
     * <br/><br/>
     * 
     * <b>Note:</b> This class should not be used to set or get the settings
     *              during the normal workflow of the Activity. Use an instance
     *              of the {@link SettingsManager} class for this.
     * 
     * @since Version 1.0
     */
    protected class PrefsManager
    {
        /**
         * Implementation of the default constructor. <br/><br/>
         * 
         * @since Version 1.0
         */
        protected PrefsManager()
        {
        }
        
        /**
         * Saves a single preference that consists of a boolean value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to save
         * @param value the boolean value to save
         * 
         * @see #saveBooleanPref(int, boolean)
         * @see #loadBooleanPref(java.lang.String, boolean)
         * @see #loadBooleanPref(int, boolean)
         * 
         * @since Version 1.0
         */
        protected void saveBooleanPref(String prefPath, boolean value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putBoolean(prefPath, value);
            prefs.commit();
        }
        
        /**
         * Saves a single preference that consists of a boolean value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to save
         * @param value the boolean value to save
         * 
         * @see #saveBooleanPref(java.lang.String, boolean)
         * @see #loadBooleanPref(java.lang.String, boolean)
         * @see #loadBooleanPref(int, boolean)
         * 
         * @since Version 1.0
         */
        protected void saveBooleanPref(int prefPathId, boolean value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putBoolean(getString(prefPathId), value);
            prefs.commit();
        }
        
        /**
         * Loads a single preference that consists of a boolean value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadBooleanPref(int, boolean)
         * @see #saveBooleanPref(java.lang.String, boolean)
         * @see #saveBooleanPref(int, boolean)
         * 
         * @since Version 1.0
         */
        protected boolean loadBooleanPref(String prefPath, boolean defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getBoolean(prefPath, defaultValue);
        }
        
        /**
         * Loads a single preference that consists of a boolean value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadBooleanPref(java.lang.String, boolean)
         * @see #saveBooleanPref(java.lang.String, boolean)
         * @see #saveBooleanPref(int, boolean)
         * 
         * @since Version 1.0
         */
        protected boolean loadBooleanPref(int prefPathId, boolean defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getBoolean(getString(prefPathId), defaultValue);
        }
        
        /**
         * Saves a single preference that consists of an int value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to save
         * @param value the int value to save
         * 
         * @see #saveIntPref(int, int)
         * @see #loadIntPref(java.lang.String, int)
         * @see #loadIntPref(int, int)
         * 
         * @since Version 1.0
         */
        protected void saveIntPref(String prefPath, int value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putInt(prefPath, value);
            prefs.commit();
        }
        
        /**
         * Saves a single preference that consists of an int value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to save
         * @param value the int value to save
         * 
         * @see #saveIntPref(java.lang.String, int)
         * @see #loadIntPref(java.lang.String, int)
         * @see #loadIntPref(int, int)
         * 
         * @since Version 1.0
         */
        protected void saveIntPref(int prefPathId, int value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putInt(getString(prefPathId), value);
            prefs.commit();
        }
        
        /**
         * Loads a single preference that consists of an int value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadIntPref(int, int)
         * @see #saveIntPref(java.lang.String, int)
         * @see #saveIntPref(int, int)
         * 
         * @since Version 1.0
         */
        protected int loadIntPref(String prefPath, int defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getInt(prefPath, defaultValue);
        }
        
        /**
         * Loads a single preference that consists of an int value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadIntPref(java.lang.String, int)
         * @see #saveIntPref(java.lang.String, int)
         * @see #saveIntPref(int, int)
         * 
         * @since Version 1.0
         */
        protected int loadIntPref(int prefPathId, int defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getInt(getString(prefPathId), defaultValue);
        }
        
        /**
         * Saves a single preference that consists of a String value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to save
         * @param value the String value to save
         * 
         * @see #saveStringPref(int, java.lang.String)
         * @see #loadStringPref(java.lang.String, java.lang.String)
         * @see #loadStringPref(int, java.lang.String)
         * 
         * @since Version 1.0
         */
        protected void saveStringPref(String prefPath, String value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putString(prefPath, value);
            prefs.commit();
        }
        
        /**
         * Saves a single preference that consists of a String value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to save
         * @param value the String value to save
         * 
         * @see #saveStringPref(java.lang.String, java.lang.String)
         * @see #loadStringPref(java.lang.String, java.lang.String)
         * @see #loadStringPref(int, java.lang.String)
         * 
         * @since Version 1.0
         */
        protected void saveStringPref(int prefPathId, String value)
        {
            SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE).edit();
            prefs.putString(getString(prefPathId), value);
            prefs.commit();
        }
        
        /**
         * Loads a single preference that consists of a String value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPath the path to the preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadStringPref(int, java.lang.String)
         * @see #saveStringPref(java.lang.String, java.lang.String)
         * @see #saveStringPref(int, java.lang.String)
         * 
         * @since Version 1.0
         */
        protected String loadStringPref(String prefPath, String defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getString(prefPath, defaultValue);
        }
        
        /**
         * Loads a single preference that consists of a String value, using the
         * {@link SharedPreferences}. <br/><br/>
         * 
         * @param prefPathId the id of a String that represents the path to the
         *                   preference to load
         * @param defaultValue the default value to return if no value was
         *                     previously saved for this preference
         * 
         * @return the saved value of the specified preference if there is one,
         *         the 'defaultValue' otherwise
         * 
         * @see #loadStringPref(java.lang.String, java.lang.String)
         * @see #saveStringPref(java.lang.String, java.lang.String)
         * @see #saveStringPref(int, java.lang.String)
         * 
         * @since Version 1.0
         */
        protected String loadStringPref(int prefPathId, String defaultValue)
        {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_path), MODE_PRIVATE);
            return prefs.getString(getString(prefPathId), defaultValue);
        }
        
        /**
         * Loads the start up preferences of the application, primarily used
         * during {@link #onCreate(android.os.Bundle)}. <br/><br/>
         * 
         * This method only loads the preferences the user can change in this
         * particular Activity - namely {@link SettingsManager#isOnStart()
         * onStart} and {@link SettingsManager#isInBackground() inBackground}.
         * It doesn't return the values but changes their corresponding {@link
         * SettingsManager settings} and the 'checked' state of their
         * corresponding {@link CheckBox CheckBoxes} directly.
         * 
         * @see #loadAdvancedPrefs()
         * 
         * @since Version 1.0
         */
        protected void loadStartUpPrefs()
        {
            sm.setOnStart(loadBooleanPref(R.string.on_start_pref, true));
            onStartCheckbox.setChecked(sm.isOnStart());
            sm.setInBackground(loadBooleanPref(R.string.in_background_pref, false));
            inBackgroundCheckbox.setChecked(sm.isInBackground());
        }
        
        /**
         * Loads the advanced preferences of the application, primarily used
         * during {@link #onResume()}. <br/><br/>
         * 
         * This method loads the preferences the user can change in the separate
         * {@link SettingsActivity}. It doesn't return the values but changes
         * their corresponding {@link SettingsManager settings} directly. It is
         * not recommended to be (only) used during {@link
         * #onCreate(android.os.Bundle)}, since this would prevent the
         * preferences from immediately taking effect after changes are made by
         * the user.
         * 
         * @see #loadStartUpPrefs()
         * 
         * @since Version 1.0
         */
        protected void loadAdvancedPrefs()
        {
            sm.setVolumeKeysUsed(loadBooleanPref(R.string.volumekeys_used_pref, true));
            sm.setScreenOn(loadBooleanPref(R.string.screen_on_pref, true));
            sm.setLockExit(loadBooleanPref(R.string.lock_exit_pref, false));
            sm.setLockBack(loadBooleanPref(R.string.lock_back_pref, false));
            sm.setLockMenu(loadBooleanPref(R.string.lock_menu_pref, false));
            sm.setLockVolume(loadBooleanPref(R.string.lock_volume_pref, false));
            sm.setHideStatusbar(loadBooleanPref(R.string.hide_statusbar_pref, false));
            sm.setShowNotification(loadBooleanPref(R.string.show_notification_pref, true));
            sm.setNotificationClick(loadIntPref(R.string.notification_click_pref, CameraOnNotification.NOTIFICATION_RESUME));
            sm.setNotificationCancel(loadIntPref(R.string.notification_cancel_pref, CameraOnNotification.NOTIFICATION_FINISH));
        }
    }
    /**
     * An instance of the inner {@link PrefsManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected PrefsManager pm = new PrefsManager();
    /**
     * Inner class that handles the settings of the application. <br/><br/>
     * 
     * <b>Note:</b> This class is used to set and get the settings during the
     *              normal workflow of the Activity. To permanently save and
     *              load the user preferences use an instance of the {@link
     *              PrefsManager} class.
     * 
     * @since Version 1.0
     */
    protected class SettingsManager
    {
        private boolean onStart;
        private boolean inBackground;
        private boolean volumeKeysUsed;
        private boolean screenOn;
        private boolean lockExit;
        private boolean lockBack;
        private boolean lockMenu;
        private boolean lockVolume;
        private boolean hideStatusbar;
        private boolean showNotification;
        private int notificationClick;
        private int notificationCancel;
        
        /**
         * Implementation of the default constructor. <br/><br/>
         * 
         * @since Version 1.0
         */
        protected SettingsManager()
        {
        }
        
        /**
         * Sets wether to {@link #setLightOn(boolean) set the light} on when
         * the Activity is first created. <br/><br/>
         * 
         * @param start wether to set the light on or not
         * 
         * @see #isOnStart()
         * 
         * @since Version 1.0
         */
        protected void setOnStart(boolean start)
        {
            onStart = start;
        }

        /**
         * Returns wether the light should be {@link #setLightOn(boolean) set}
         * on when the Activity is first created. <br/><br/>
         * 
         * @return wether the light should be set on or not
         * 
         * @see #setOnStart(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isOnStart()
        {
            return onStart;
        }

        /**
         * Sets wether the light should remain {@link #isLightOn() on} when the
         * Activity is paused or stopped. <br/><br/>
         * 
         * @param background wether the light should remain on or not
         * 
         * @see #isInBackground()
         * 
         * @since Version 1.0
         */
        protected void setInBackground(boolean background)
        {
            inBackground = background;
        }

        /**
         * Returns wether the light should remain {@link #isLightOn() on} when
         * the Activity is paused or stopped. <br/><br/>
         * 
         * @return wether the light should remain on or not
         * 
         * @see #setInBackground(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isInBackground()
        {
            return inBackground;
        }
        
        /**
         * Sets wether the device's volume keys should be used to {@link
         * #setLightOn(boolean) set the light} on or off. <br/><br/>
         * 
         * @param used wether the device's volume keys should be used or not
         * 
         * @see #isVolumeKeysUsed()
         * 
         * @since Version 1.0
         */
        protected void setVolumeKeysUsed(boolean used)
        {
            volumeKeysUsed = used;
        }
        
        /**
         * Returns wether the device's volume keys should be used to {@link
         * #setLightOn(boolean) set the light} on or off. <br/><br/>
         * 
         * @return wether the device's volume keys should be used or not
         * 
         * @see #setVolumeKeysUsed(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isVolumeKeysUsed()
        {
            return volumeKeysUsed;
        }
        
        /**
         * Sets wether the device's screen should remain on when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the screen <b>should</b>
         *              remain on, but doesn't actually undertake any steps to
         *              do that. Please refer to {@link
         *              LockManager#keepScreenOn(boolean) keepScreenOn(boolean)}
         *              for doing this.
         * 
         * @param on wether the device's screen should remain on or not
         * 
         * @see #isScreenOn()
         * 
         * @since Version 1.0
         */
        protected void setScreenOn(boolean on)
        {
            screenOn = on;
        }
        
        /**
         * Returns wether the device's screen should remain on when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the screen <b>should</b>
         *              remain on, but not if any steps were actually undertaken
         *              to do that. It's the task of any other method using this
         *              setting to do the necessary checks.
         * 
         * @return wether the device's screen should remain on or not
         * 
         * @see #setScreenOn(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isScreenOn()
        {
            return screenOn;
        }
        
        /**
         * Sets wether the {@link #exitItem} should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the exitItem <b>should</b>
         *              be locked, but doesn't actually undertake any steps to
         *              do that. It's the task of any other method using this
         *              setting to do the necessary preparations.
         * 
         * @param lock wether the exitItem should be locked or not
         * 
         * @see #isExitLocked()
         * @see #onOptionsItemSelected(android.view.MenuItem)
         * 
         * @since Version 1.0
         */
        protected void setLockExit(boolean lock)
        {
            lockExit = lock;
        }
        
        /**
         * Returns wether the {@link #exitItem} should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the exitItem
         *              <b>should</b> be locked, but not if any steps were
         *              actually undertaken to do that. It's the task of any
         *              other method using this setting to do the necessary
         *              checks.
         * 
         * @return wether the exitItem should be locked or not
         * 
         * @see #setLockExit(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isExitLocked()
        {
            return lockExit;
        }
        
        /**
         * Sets wether the device's back key should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the back key <b>should</b>
         *              be locked, but doesn't actually undertake any steps to
         *              do that. It's the task of any other method using this
         *              setting to do the necessary preparations.
         * 
         * @param lock wether the back key should be locked or not
         * 
         * @see #isBackLocked()
         * @see #dispatchKeyEvent(android.view.KeyEvent)
         * 
         * @since Version 1.0
         */
        protected void setLockBack(boolean lock)
        {
            lockBack = lock;
        }
        
        /**
         * Returns wether the device's back key should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the back key
         *              <b>should</b> be locked, but not if any steps were
         *              actually undertaken to do that. It's the task of any
         *              other method using this setting to do the necessary
         *              checks.
         * 
         * @return wether the back key should be locked or not
         * 
         * @see #setLockBack(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isBackLocked()
        {
            return lockBack;
        }
        
        /**
         * Sets wether the device's menu key should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the menu key <b>should</b>
         *              be locked, but doesn't actually undertake any steps to
         *              do that. It's the task of any other method using this
         *              setting to do the necessary preparations.
         * 
         * @param lock wether the menu key should be locked or not
         * 
         * @see #isMenuLocked()
         * @see #dispatchKeyEvent(android.view.KeyEvent)
         * 
         * @since Version 1.0
         */
        protected void setLockMenu(boolean lock)
        {
            lockMenu = lock;
        }
        
        /**
         * Returns wether the device's menu key should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the menu key
         *              <b>should</b> be locked, but not if any steps were
         *              actually undertaken to do that. It's the task of any
         *              other method using this setting to do the necessary
         *              checks.
         * 
         * @return wether the menu key should be locked or not
         * 
         * @see #setLockMenu(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isMenuLocked()
        {
            return lockMenu;
        }
        
        /**
         * Sets wether the device's volume keys should be locked when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the volume keys
         *              <b>should</b> be locked, but doesn't actually undertake
         *              any steps to do that. It's the task of any other method
         *              using this setting to do the necessary preparations.
         * 
         * @param lock wether the volume keys should be locked or not
         * 
         * @see #isVolumeLocked()
         * @see #dispatchKeyEvent(android.view.KeyEvent)
         * 
         * @since Version 1.0
         */
        protected void setLockVolume(boolean lock)
        {
            lockVolume = lock;
        }
        
        /**
         * Returns wether the device's volume keys should be locked when the
         * {@link LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the volume keys
         *              <b>should</b> be locked, but not if any steps were
         *              actually undertaken to do that. It's the task of any
         *              other method using this setting to do the necessary
         *              checks.
         * 
         * @return wether the volume keys should be locked or not
         * 
         * @see #setLockVolume(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isVolumeLocked()
        {
            return lockVolume;
        }
        
        /**
         * Sets wether the status bar should be hidden when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only sets wether the status bar
         *              <b>should</b> be hidden, but doesn't actually undertake
         *              any steps to do that. Please refer to {@link
         *              LockManager#hideStatusbar(boolean)
         *              hideStatusbar(boolean)} for doing this.
         * 
         * @param hide wether the status bar should be hidden or not
         * 
         * @see #isStatusbarHidden()
         * 
         * @since Version 1.0
         */
        protected void setHideStatusbar(boolean hide)
        {
            hideStatusbar = hide;
        }
        
        /**
         * Returns wether the status bar should be hidden when the {@link
         * LockManager#isScreenLocked() screen is locked}. <br/><br/>
         * 
         * <b>Note:</b> This method only returns wether the status bar
         *              <b>should</b> be hidden, but not if any steps were
         *              actually undertaken to do that. It's the task of any
         *              other method using this setting to do the necessary
         *              checks.
         * 
         * @return wether the status bar should be hidden or not
         * 
         * @see #setHideStatusbar(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isStatusbarHidden()
        {
            return hideStatusbar;
        }
        
        /**
         * Sets wether a {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} should be
         * shown if the camera's flashlight turns on. <br/><br/>
         * 
         * <b>Note:</b> This method sets only wether a CameraOnNotification
         *              <b>should</b> be shown but doesn't actually show one.
         *              Please refer to {@link
         *              NotifyManager#sendNotification(int,
         *              android.app.Notification) sendNotification} for doing
         *              this.
         * 
         * @param show wether a CameraOnNotification should be shown or not
         * 
         * @see #isNotificationShown()
         * 
         * @since Version 1.0
         */
        protected void setShowNotification(boolean show)
        {
            showNotification = show;
        }
        
        /**
         * Returns wether a {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} should be
         * shown if the camera's flashlight turns on. <br/><br/>
         * 
         * <b>Note:</b> This method returns only wether a CameraOnNotification
         *              <b>should</b> be shown but doesn't actually show one.
         *              It's the task of any other method using this setting to
         *              do the necessary checks.
         * 
         * @return wether a CameraOnNotification should be shown or not
         * 
         * @see #setShowNotification(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isNotificationShown()
        {
            return showNotification;
        }
        
        /**
         * Sets the action that should be performed when a shown {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} is clicked.
         * <br/><br/>
         * 
         * The 'click' parameter should be one of the static 'NOTIFICATION_XXX'
         * values defined in the {@link CameraOnNotification} class.
         * 
         * @param click the action that should be performed
         * 
         * @see #getNotificationClick()
         * 
         * @since Version 1.0
         */
        protected void setNotificationClick(int click)
        {
            notificationClick = click;
        }
        
        /**
         * Returns the action that should be performed when a shown {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} is clicked.
         * <br/><br/>
         * 
         * The returned value should be one of the static 'NOTIFICATION_XXX'
         * values defined in the {@link CameraOnNotification} class.
         * 
         * @return the action that should be performed
         * 
         * @see #setNotificationClick(int)
         * 
         * @since Version 1.0
         */
        protected int getNotificationClick()
        {
            return notificationClick;
        }
        
        /**
         * Sets the action that should be performed when a shown {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} is cancelled.
         * <br/><br/>
         * 
         * The 'cancel' parameter should be one of the static 'NOTIFICATION_XXX'
         * values defined in the {@link CameraOnNotification} class.
         * 
         * @param cancel the action that should be performed
         * 
         * @see #getNotificationCancel()
         * 
         * @since Version 1.0
         */
        protected void setNotificationCancel(int cancel)
        {
            notificationCancel = cancel;
        }
        
        /**
         * Returns the action that should be performed when a shown {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} is cancelled.
         * <br/><br/>
         * 
         * The returned value should be one of the static 'NOTIFICATION_XXX'
         * values defined in the {@link CameraOnNotification} class.
         * 
         * @return the action that should be performed
         * 
         * @see #setNotificationCancel(int)
         * 
         * @since Version 1.0
         */
        protected int getNotificationCancel()
        {
            return notificationCancel;
        }
    }
    /**
     * An instance of the inner {@link SettingsManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected SettingsManager sm = new SettingsManager();
    /**
     * Inner class that handles all kinds of notifications shown to the user.
     * <br/><br/>
     * 
     * @since Version 1.0
     */
    protected class NotifyManager
    {
        /**
         * Implementation of the default constructor. <br/><br/>
         * 
         * @since Version 1.0
         */
        protected NotifyManager()
        {
        }
        
        /**
         * Sends a status bar {@link Notification} for the user. <br/><br/>
         * 
         * This method is primarily used to send a {@link
         * CameraOnNotification#getInstance(android.content.Context,
         * android.content.Intent, int, int) CameraOnNotification} with the
         * {@link CameraOnNotification#CAMERA_ON_ID CAMERA_ON_ID}. If you want
         * to send another Notification, you should make sure that it doesn't
         * use the same id, since this could result in unpredictable behavior.
         * 
         * @param id an id to identify the Notification to send
         * @param notification the Notification to send
         * 
         * @see #cancelNotification(int)
         * 
         * @since Version 1.0
         */
        protected void sendNotification(int id, Notification notification)
        {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(id, notification);
        }
        
        /**
         * Cancels a status bar {@link Notification}. <br/><br/>
         * 
         * @param id an id to identify the Notification to cancel
         * 
         * @see #sendNotification(int, android.app.Notification)
         * 
         * @since Version 1.0
         */
        protected void cancelNotification(int id)
        {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(id);
        }
        
        /**
         * Sends a {@link Toast} for the user. <br/><br/>
         * 
         * @param text the message to show
         * @param duration the duration to show the text, should be {@link
         *                 Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         * 
         * @see #sendToast(int, int)
         * 
         * @since Version 1.0
         */
        protected void sendToast(String text, int duration)
        {
            Toast.makeText(context, text, duration).show();
        }
        
        /**
         * Sends a {@link Toast} for the user. <br/><br/>
         * 
         * @param textId the id of a String resource to show as the message
         * @param duration the duration to show the text, should be {@link
         *                 Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
         * 
         * @see #sendToast(java.lang.String, int)
         * 
         * @since Version 1.0
         */
        protected void sendToast(int textId, int duration)
        {
            Toast.makeText(context, getString(textId), duration).show();
        }
    }
    /**
     * An instance of the inner {@link NotifyManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected NotifyManager nm = new NotifyManager();
    /**
     * Inner class that handles the camera's flashlight. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected class FlashLightManager
    {
        private boolean flashLightOpen;
        private boolean flashLightOn;
        
        private Camera flashLight;
        
        /**
         * Implementation of the default constructor. <br/><br/>
         * 
         * @since Version 1.0
         */
        protected FlashLightManager()
        {
        }
        
        /**
         * {@link Camera#open() Opens} an instance of the {@link Camera} class
         * and permits access to the device's camera. <br/><br/>
         * 
         * On devices with more than one camera this method looks for the first
         * camera id that's a 'back' camera.
         * 
         * @throws RuntimeException if no 'back' camera was found, usually
         *                          handled by {@link #setFlashLightOn(boolean)}
         * 
         * @see #releaseFlashLight()
         * @see #isFlashLightOpen()
         * 
         * @since Version 1.0
         */
        protected void openFlashLight()
        {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD)
            {
                flashLight = Camera.open();
                flashLightOpen = true;
            }
            else
            {
                for(int counter = 0; counter < Camera.getNumberOfCameras(); counter = counter + 1)
                {
                    Camera.CameraInfo info = new Camera.CameraInfo();
                    Camera.getCameraInfo(counter, info);
                    if(info.facing == Camera.CameraInfo.CAMERA_FACING_BACK)
                    {
                        flashLight = Camera.open(counter);
                        flashLightOpen = true;
                        return;
                    }
                }
                throw new RuntimeException("no back camera found");
            }
        }
        
        /**
         * {@link Camera#release() Releases} the current instance of the {@link
         * Camera} class. <br/><br/>
         * 
         * @see #openFlashLight()
         * @see #isFlashLightOpen()
         * 
         * @since Version 1.0
         */
        protected void releaseFlashLight()
        {
            flashLight.release();
            flashLight = null;
            flashLightOpen = false;
        }
        
        /**
         * Returns wether an instance of the {@link Camera} class is currently
         * {@link Camera#open() open}.
         * 
         * @return wether an instance of the Camera class is currently open
         * 
         * @see #openFlashLight()
         * @see #releaseFlashLight()
         * 
         * @since Version 1.0
         */
        protected boolean isFlashLightOpen()
        {
            return flashLightOpen;
        }
        
        /**
         * Turns the camera's flashlight on or off. <br/><br/>
         * 
         * <b>Note:</b> If the device's camera could not be accessed - exactly:
         *              if a {@link RuntimeException} was thrown - the
         *              flashlight will be turned off, the corresponding
         *              background for the {@link #onOffButton} will be set and
         *              a {@link Toast} will be {@link
         *              NotifyManager#sendToast(int, int) sent} to the user.
         * 
         * @param on wether to turn the flashlight on or off
         * 
         * @see #isFlashLightOn()
         * @see #setLightOn(boolean)
         * @see #isLightOn()
         * 
         * @since Version 1.0
         */
        protected void setFlashLightOn(boolean on)
        {
            try
            {
                if(on)
                {
                    if(!isFlashLightOpen())
                    {
                        openFlashLight();
                    }
                    Camera.Parameters params = flashLight.getParameters();
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    flashLight.setParameters(params);
                    flashLight.startPreview();
                }
                else
                {
                    if(isFlashLightOpen())
                    {
                        flashLight.stopPreview();
                        Camera.Parameters params = flashLight.getParameters();
                        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        flashLight.setParameters(params);
                        releaseFlashLight();
                    }
                }
                flashLightOn = on;
            }
            catch(RuntimeException re)
            {
                nm.sendToast(R.string.camera_failed_toast_text, Toast.LENGTH_LONG);
                flashLightOn = false;
                onOffButton.setBackgroundResource(R.drawable.on_off_button_off);
            }
        }
        
        /**
         * Returns wether the camera's flashlight is turned on
         * 
         * @return wether the flashlight is turned on
         * 
         * @see #setFlashLightOn(boolean)
         * @see #setLightOn(boolean)
         * @see #isLightOn()
         * 
         * @since Version 1.0
         */
        protected boolean isFlashLightOn()
        {
            return flashLightOn;
        }
    }
    /**
     * An instance of the inner {@link FlashLightManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected FlashLightManager fm = new FlashLightManager();
    /**
     * Inner class that handles most matters related to locking the screen.<br/>
     * <br/>
     * 
     * @since Version 1.0
     */
    protected class LockManager
    {
        private boolean screenLocked;
        
        /**
         * Implementation of the default constructor. <br/><br/>
         * 
         * @since Version 1.0
         */
        protected LockManager()
        {
        }
        
        /**
         * Sets wether to lock the screen. <br/><br/>
         * 
         * Locks the screen to prevent unintended user interaction. This method
         * {@link #disableViews(boolean) disables the Views}, {@link
         * #updateMenu() updates the options menu}, {@link
         * #hideStatusbar(boolean) hides the status bar} - depending on {@link
         * SettingsManager#isStatusbarHidden() isStatusbarHidden()} - and {@link
         * #keepScreenOn(boolean) keeps the screen on} - depending on {@link
         * SettingsManager#isScreenOn() isScreenOn()}.
         * 
         * @param locked wether to lock the screen
         * 
         * @see #isScreenLocked()
         * 
         * @since Version 1.0
         */
        protected void setScreenLocked(boolean locked)
        {
            screenLocked = locked;
            disableViews(locked);
            updateMenu();
            if(sm.isStatusbarHidden())
            {
                hideStatusbar(locked);
            }
            if(sm.isScreenOn())
            {
                keepScreenOn(locked);
            }
        }
        
        /**
         * Returns wether the screen is locked. <br/><br/>
         * 
         * @return wether the screen is locked
         * 
         * @see #setScreenLocked(boolean)
         * 
         * @since Version 1.0
         */
        protected boolean isScreenLocked()
        {
            return screenLocked;
        }
        
        /**
         * Sets wether to disable the {@link View Views} of this Activity. <br/>
         * <br/>
         * 
         * Calls the {@link View#setEnabled(boolean)} method on the Views of
         * this Activity with the corresponding values. Please remember that
         * this method sets wether to <b>disable</b> the Views but their
         * 'setEnabled' methods set wether to <b>enable</b> them.
         * 
         * @param disable wether to disable the buttons
         * 
         * @since Version 1.0
         */
        protected void disableViews(boolean disable)
        {
            onOffButton.setEnabled(!disable);
            onStartCheckbox.setEnabled(!disable);
            inBackgroundCheckbox.setEnabled(!disable);
        }
        
        /**
         * Notifies the Activity that it's options menu should be recreated at
         * the next opportunity. <br/><br/>
         * 
         * Internally calls the {@link
         * ActionBarActivity#supportInvalidateOptionsMenu()} method.
         * 
         * @see #onCreateOptionsMenu(android.view.Menu)
         * 
         * @since Version 1.0
         */
        protected void updateMenu()
        {
            supportInvalidateOptionsMenu();
        }
        
        /**
         * Sets wether to hide the status bar. <br/><br/>
         * 
         * @param hide wether to hide the status bar
         * 
         * @see SettingsManager#setHideStatusbar(boolean)
         * @see SettingsManager#isStatusbarHidden()
         * 
         * @since Version 1.0
         */
        protected void hideStatusbar(boolean hide)
        {
            if(hide)
            {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                }
            }
            else
            {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                }
            }
        }
        
        /**
         * Sets wether to keep the screen on. <br/><br/>
         * 
         * @param on wether to keep the screen on
         * 
         * @see SettingsManager#setScreenOn(boolean)
         * @see SettingsManager#isScreenOn()
         * 
         * @since Version 1.0
         */
        protected void keepScreenOn(boolean on)
        {
            if(on)
            {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
            else
            {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        }
    }
    /**
     * An instance of the inner {@link LockManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected LockManager lm = new LockManager();
    
    /**
     * Performs the start up operations of this activity. <br/><br/>
     * 
     * Initializes the activity's ui elements, {@link
     * PrefsManager#loadStartUpPrefs() loads the start up preferences} and
     * {@link #setLightOn(boolean) sets the light} on or off, based on {@link
     * SettingsManager#isOnStart() isOnStart()}.
     * 
     * @param savedInstanceState not actively used, only passed to the
     *                           corresponding method of the super class
     * 
     * @see ActionBarActivity#onCreate(android.os.Bundle)
     * 
     * @since Version 1.0
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        onOffButton = (Button) findViewById(R.id.on_off_button);
        onStartCheckbox = (CheckBox) findViewById(R.id.on_start_checkbox);
        inBackgroundCheckbox = (CheckBox) findViewById(R.id.in_background_checkbox);
        
        lockItem = (MenuItem) findViewById(R.id.lock_item);
        settingsItem = (MenuItem) findViewById(R.id.settings_item);
        exitItem = (MenuItem) findViewById(R.id.exit_item);
        aboutItem = (MenuItem) findViewById(R.id.about_item);
        
        pm.loadStartUpPrefs();
        
        setLightOn(sm.isOnStart());
    }
    
    /**
     * Performs the resume operations of this Activity. <br/><br/>
     * 
     * {@link PrefsManager#loadAdvancedPrefs() Loads the advanced preferences},
     * {@link LockManager#setScreenLocked(boolean) sets the screen unlocked},
     * {@link FlashLightManager#setFlashLightOn(boolean) turns the flashlight}
     * on or off - based on {@link isLightOn()} -, sets the corresponding
     * background resource for the {@link #onOffButton} and {@link
     * NotifyManager#sendNotification(int, android.app.Notification) sends} or
     * {@link NotifyManager#cancelNotification(int) cancels} a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} - based on {@link
     * FlashLightManager#isFlashLightOn() isFlashLightOn()} and {@link
     * SettingsManager#isNotificationShown() isNotificationShown()}.
     * 
     * @see ActionBarActivity#onResume()
     * 
     * @since Version 1.0
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        
        pm.loadAdvancedPrefs();
        lm.setScreenLocked(false);
        fm.setFlashLightOn(isLightOn());
        if(fm.isFlashLightOn())
        {
            onOffButton.setBackgroundResource(R.drawable.on_off_button_on);
            if(sm.isNotificationShown())
            {
                nm.sendNotification(CameraOnNotification.CAMERA_ON_ID, CameraOnNotification.getInstance(context, getIntent(), sm.getNotificationClick(), sm.getNotificationCancel()));
            }
        }
        else
        {
            onOffButton.setBackgroundResource(R.drawable.on_off_button_off);
            nm.cancelNotification(CameraOnNotification.CAMERA_ON_ID);
        }
    }
    
    /**
     * Performs the stop operations of this Activity. <br/><br/>
     * 
     * {@link FlashLightManager#setFlashLightOn(boolean) Turns the flashlight}
     * on or off - based on {@link isLightOn()} and {@link
     * SettingsManager#isInBackground() isInBackground()} - and {@link
     * NotifyManager#sendNotification(int, android.app.Notification) sends} or
     * {@link NotifyManager#cancelNotification(int) cancels} a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} - based on {@link
     * FlashLightManager#isFlashLightOn() isFlashLightOn()} and {@link
     * SettingsManager#isNotificationShown() isNotificationShown()}.
     * 
     * @see ActionBarActivity#onStop()
     * 
     * @since Version 1.0
     */
    @Override
    protected void onStop()
    {
        super.onStop();

        fm.setFlashLightOn(isLightOn() && sm.isInBackground());
        if(fm.isFlashLightOn())
        {
            if(sm.isNotificationShown())
            {
                nm.sendNotification(CameraOnNotification.CAMERA_ON_ID, CameraOnNotification.getInstance(context, getIntent(), sm.getNotificationClick(), sm.getNotificationCancel()));
            }
        }
        else
        {
            nm.cancelNotification(CameraOnNotification.CAMERA_ON_ID);
        }
    }
    
    /**
     * Performs the last clean up operations of this Activity. <br/><br/>
     * 
     * {@link FlashLightManager#setFlashLightOn(boolean) Turns the flashlight}
     * off and {@link NotifyManager#cancelNotification(int) cancels} an active
     * {@link CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification}.
     * 
     * @see ActionBarActivity#onDestroy()
     * 
     * @since Version 1.0
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        fm.setFlashLightOn(false);
        nm.cancelNotification(CameraOnNotification.CAMERA_ON_ID);
    }
    
    /**
     * Calls {@link ActionBarActivity#finish() finish} on this Activity on
     * 'resultCode' {@link #RESULT_FINISHED} from a started Activity. <br/><br/>
     * 
     * Other values for the 'resultCode' param are not handled. This method is
     * used to finish this Activity if the exit item in the options menu of a
     * started Activity is selected, so that the entire application is
     * finishing.
     * 
     * @param requestCode not actively used
     * @param resultCode the result of a started activity
     * @param data not actively used
     * 
     * @since Version 1.0
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_FINISHED)
        {
            finish();
        }
    }
    
    /**
     * Calls {@link ActionBarActivity#finish() finish} on this Activity on
     * action {@link CameraOnNotification#ACTION_FINISH ACTION_FINISH} of the
     * 'intent' param. <br/><br/>
     * 
     * The 'intent' param is expected to be the {@link Intent} of a {@link
     * PendingIntent} that was passed to a {@link Notification} before. This
     * method is used to (possibly) finish this Activity if the Notification was
     * clicked or cancelled by the user, depending on the users settings (see
     * {@link SettingsManager#getNotificationClick() getNotificationClick()},
     * {@link SettingsManager#getNotificationCancel() getNotificationCancel()}
     * and {@link CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int)} for details). <br/><br/>
     * 
     * <b>Note:</b> The 'intent' param is not set as the new Intent for this
     *              Activity as returned by {@link #getIntent()}.
     * 
     * @param intent the Intent of a PendingIntent passed to a notification
     *               before
     * 
     * @see ActionBarActivity#onNewIntent(android.content.Intent)
     * 
     * @since Version 1.0
     */
    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        
        if(intent.getAction().equals(CameraOnNotification.ACTION_FINISH))
        {
            finish();
        }
    }
    
    /**
     * {@link MenuInflater#inflate(int, android.view.Menu) Inflates} the options
     * menu for this Activity. <br/><br/>
     * 
     * Inflates either the 'main_actionbar_menu' or the 'lock_actionbar_menu'
     * resource, depending on {@link LockManager#isScreenLocked()
     * isScreenLocked()}.
     * 
     * @param menu not actively used, only passed to the corresponding method of
     *             the super class and to {@link MenuInflater#inflate(int,
     *             android.view.Menu) 
     * 
     * @return the return value of the corresponding method of the super class
     * 
     * @see ActionBarActivity#onCreateOptionsMenu(android.view.Menu)
     * 
     * @since Version 1.0
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(!lm.isScreenLocked())
        {
            getMenuInflater().inflate(R.menu.main_actionbar_menu, menu);
        }
        else
        {
            getMenuInflater().inflate(R.menu.lock_actionbar_menu, menu);
        }
        
        return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * Handles events from the volume keys, the back key and the menu key. <br/>
     * <br/>
     * 
     * This method intercepts the events to lock the keys - depending on {@link
     * SettingsManager#isVolumeLocked() isVolumeLocked()}, {@link
     * SettingsManager#isBackLocked() isBackLocked()} and {@link
     * SettingsManager#isMenuLocked() isMenuLocked()} - if the {@link
     * LockManager#isScreenLocked() screen is locked} and to {@link
     * FlashLightManager#setFlashLightOn(boolean) turn the flashlight} on and
     * off using the volume keys - depending on {@link
     * SettingsManager#isVolumeKeysUsed() isVolumeKeysUsed()}.<br/><br/>
     * 
     * It also sets the corresponding background resource for the {@link
     * #onOffButton} and {@link NotifyManager#sendNotification(int,
     * android.app.Notification) sends} or {@link
     * NotifyManager#cancelNotification(int) cancels} a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} - based on {@link
     * FlashLightManager#isFlashLightOn() isFlashLightOn()} and {@link
     * SettingsManager#isNotificationShown() isNotificationShown()}.
     * 
     * @param event the {@link KeyEvent} to handle
     * 
     * @return true if one of the keys specified above was handled in any way,
     *         the return value of the corresponding method of the super class
     *         otherwise
     * 
     * @see ActionBarActivity#dispatchKeyEvent(android.view.KeyEvent)
     * 
     * @since Version 1.0
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            if(event.getAction() == KeyEvent.ACTION_DOWN && sm.isVolumeKeysUsed() && !sm.isVolumeLocked())
            {
                setLightOn(!isLightOn());
                fm.setFlashLightOn(!fm.isFlashLightOn());
                if(fm.isFlashLightOn())
                {
                    onOffButton.setBackgroundResource(R.drawable.on_off_button_on);
                    if(sm.isNotificationShown())
                    {
                        nm.sendNotification(CameraOnNotification.CAMERA_ON_ID, CameraOnNotification.getInstance(context, getIntent(), sm.getNotificationClick(), sm.getNotificationCancel()));
                    }
                }
                else
                {
                    onOffButton.setBackgroundResource(R.drawable.on_off_button_off);
                    nm.cancelNotification(CameraOnNotification.CAMERA_ON_ID);
                }
            }
            if(sm.isVolumeKeysUsed() || (sm.isVolumeLocked() && lm.isScreenLocked()))
            {
                return true;
            }
        }
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            if(lm.isScreenLocked() && sm.isBackLocked())
            {
                return true;
            }
        }
        if(event.getKeyCode() == KeyEvent.KEYCODE_MENU)
        {
            if(lm.isScreenLocked() && sm.isMenuLocked())
            {
                return true;
            }
        }
        
        return super.dispatchKeyEvent(event);
    }
    
    /**
     * Handles clicks that are performed on the {@link #onOffButton}, the {@link
     * #onStartCheckbox} and the {@link #inBackgroundCheckbox}. <br/><br/>
     * 
     * Changes the corresponding settings and preferences when the
     * onStartCheckbox or the inBackgroundCheckbox is clicked. {@link
     * FlashLightManager#setFlashLightOn(boolean) Turns the flashlight} on or
     * off when the onOffButton is clicked.
     * 
     * It also sets the corresponding background resource for the {@link
     * #onOffButton} and {@link NotifyManager#sendNotification(int,
     * android.app.Notification) sends} or {@link
     * NotifyManager#cancelNotification(int) cancels} a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} - based on {@link
     * FlashLightManager#isFlashLightOn() isFlashLightOn()} and {@link
     * SettingsManager#isNotificationShown() isNotificationShown()}.
     * 
     * @param view the View that was clicked by the user
     * 
     * @since Version 1.0
     */
    public void onButtonClicked(View view)
    {
        if(view.equals(onOffButton))
        {
            setLightOn(!isLightOn());
            fm.setFlashLightOn(!fm.isFlashLightOn());
            if(fm.isFlashLightOn())
            {
                onOffButton.setBackgroundResource(R.drawable.on_off_button_on);
                if(sm.isNotificationShown())
                {
                    nm.sendNotification(CameraOnNotification.CAMERA_ON_ID, CameraOnNotification.getInstance(context, getIntent(), sm.getNotificationClick(), sm.getNotificationCancel()));
                }
            }
            else
            {
                onOffButton.setBackgroundResource(R.drawable.on_off_button_off);
                nm.cancelNotification(CameraOnNotification.CAMERA_ON_ID);
            }
        }
        if(view.equals(onStartCheckbox))
        {
            sm.setOnStart(onStartCheckbox.isChecked());
            pm.saveBooleanPref(R.string.on_start_pref, onStartCheckbox.isChecked());
        }
        if(view.equals(inBackgroundCheckbox))
        {
            sm.setInBackground(inBackgroundCheckbox.isChecked());
            pm.saveBooleanPref(R.string.in_background_pref, inBackgroundCheckbox.isChecked());
        }
    }
    
    /**
     * Handles the selection of items in the options menu. <br/><br/>
     * 
     * {@link LockManager#setScreenLocked(boolean) Locks or unlocks} the screen
     * if the {@link #lockItem} was selected and sends a corresponding {@link
     * Toast} message. Opens the corresponding Activity if the {@link
     * #settingsItem} or the {@link #aboutItem} was selected. Finishes the
     * Activity or does nothing if the {@link #exitItem} was selected -
     * depending on {@link LockManager#isScreenLocked() isScreenLocked()} and
     * {@link SettingsManager#isExitLocked() isExitLocked()}.
     * 
     * @param item the {@link MenuItem} selected by the user
     * 
     * @return true if the selected item was handled in any way and/or if the
     *         screen is locked, the return value of the corresponding method of
     *         the super class otherwise
     * 
     * @see ActionBarActivity#onOptionsItemSelected(android.view.MenuItem)
     * 
     * @since Version 1.0
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.lock_item:
                lm.setScreenLocked(!lm.isScreenLocked());
                if(lm.isScreenLocked())
                {
                    nm.sendToast(R.string.screen_locked_toast_text, Toast.LENGTH_LONG);
                }
                else
                {
                    nm.sendToast(R.string.screen_unlocked_toast_text, Toast.LENGTH_SHORT);
                }
                return true;
            case R.id.settings_item:
                startActivityForResult(new Intent(context, org.patneu.simpleflash.SettingsActivity.class), RESULT_FINISHED);
                return true;
            case R.id.exit_item:
                if(!lm.isScreenLocked() || !sm.isExitLocked())
                {
                    finish();
                }
                return true;
            case R.id.about_item:
                startActivityForResult(new Intent(context, org.patneu.simpleflash.AboutActivity.class), RESULT_FINISHED);
                return true;
            default:
                if(lm.isScreenLocked())
                {
                    return true;
                }
                else
                {
                    return super.onOptionsItemSelected(item);
                }
        }
    }
    
    /**
     * Sets whether the camera's flashlight should turn on. <br/><br/>
     * 
     * <b>Note:</b> This method sets only wether the light <b>should</b> turn
     *              on, but doesn't actually turn it on. Please refer to {@link
     *              FlashLightManager#setFlashLightOn(boolean)
     *              setFlashLightOn(boolean)} for doing this.
     * 
     * @param on wether the light should turn on
     * 
     * @see #isLightOn()
     * 
     * @since Version 1.0
     */
    protected void setLightOn(boolean on)
    {
        lightOn = on;
    }
    
    /**
     * Returns wether the camera's flashlight should be turned on. <br/><br/>
     * 
     * <b>Note:</b> This method returns only wether the light <b>should</b> be
     *              turned on, but not if it actually is turned on. Please refer
     *              to {@link FlashLightManager#isFlashLightOn()
     *              isFlashLightOn()} for doing this.
     * 
     * @return wether the camera's flashlight should be turned on
     * 
     * @see #setLightOn(boolean)
     * 
     * @since Version 1.0
     */
    protected boolean isLightOn()
    {
        return lightOn;
    }
}