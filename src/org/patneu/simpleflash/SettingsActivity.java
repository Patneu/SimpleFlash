package org.patneu.simpleflash;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * An Activity providing the user access to the settings that are advanced or
 * too unimportant to be shown by the {@link SimpleFlash} main class. <br/><br/>
 * 
 * @author Copyright 2014 Patrick Neumann. <br/><br/>
 * 
 * <b>License:</b> Licensed under the Apache License, Version 2.0 (the
 *                 "License"); you may use this file only in compliance with the
 *                 License (or one of the other licenses mentioned under
 *                 "Additional licenses"). You may obtain a copy of the License
 *                 at http://opensource.org/licenses/Apache-2.0 . <br/><br/>
 * 
 * <b>Liability disclaimer:</b> In no event unless required by applicable law or
 *                              agreed to in writing will any copyright holder,
 *                              or any other party who modifies and/or conveys
 *                              this file as permitted by the specified
 *                              license(s), be liable to you for any damages,
 *                              including any general, special, incidental or
 *                              consequential damages arising out of the use or
 *                              inability to use this file (including but not
 *                              limited to loss of data or data being rendered
 *                              inaccurate or losses sustained by you or third
 *                              parties or a failure of this file to operate
 *                              with any other programs), even if such holder or
 *                              other party has been advised of the possibility
 *                              of such damages. There is no warranty for this
 *                              file, to the extend permitted by applicable law.
 *                              Except when otherwise stated in writing the
 *                              copyright holders and/or other parties provide
 *                              this file "as is" without warranty of any kind,
 *                              either expressed or implied, including, but not
 *                              limited to, the implied warranties of liability
 *                              for merchantability or fitness for a particular
 *                              purpose. The entire risk as to the quality and
 *                              performance of this file is with you. Should
 *                              this file prove defective, you assume the cost
 *                              of all necessary servicing, repair or
 *                              correction. If the liability disclaimer provided
 *                              cannot be given local legal effect according to
 *                              its terms, reviewing courts shall apply local
 *                              law that most closely approximates an absolute
 *                              waiver of all civil liability in connection with
 *                              this file, unless a warranty or assumption of
 *                              liability accompanies a copy of this file in
 *                              return for a fee. If there are differences
 *                              between this liability disclaimer and a
 *                              liability disclaimer provided by any license(s)
 *                              under whose terms the use of this file is
 *                              permitted, this version shall apply. If there
 *                              are differences or obscurities between this
 *                              liability disclaimer and any version of it
 *                              translated for convenience, this english version
 *                              shall apply. <br/><br/>
 * 
 * <b>Additional licenses:</b> In addition to the Apache License mentioned under
 *                             "License" you may also use this file in
 *                             compliance with one of the additional licenses
 *                             mentioned in the "licenses.txt" file of this
 *                             application. A copy of the corresponding license
 *                             conditions is also included and/or linked to in
 *                             this file. You are free to use the one of those
 *                             licenses that suits your needs best. <br/><br/>
 * 
 * <b>Third party software:</b> This file uses third party software of the
 *                              Android Open Source Project - namely the android
 *                              Software Development Kit ("SDK") and android v4
 *                              and v7-appcompat Support Librarys. This software
 *                              is licensed under the Apache License, Version
 *                              2.0; to obtain a copy of this license see the
 *                              "License" section or the "licenses.txt" file of
 *                              this application.
 * 
 * @since Version 1.0
 */
public class SettingsActivity extends ActionBarActivity
{
    /**
     * A checkbox used to set wether the volume keys should be used to turn the
     * light on and off. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setVolumeKeysUsed(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox volumekeysUsedCheckbox;
    /**
     * A checkbox used to set wether the screen should remain on when it is
     * locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setScreenOn(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox screenOnCheckbox;
    /**
     * A checkbox used to set wether the {@link #exitItem} should be locked when
     * the screen is locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setLockExit(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox lockExitCheckbox;
    /**
     * A checkbox used to set wether the back key should be locked when the
     * screen is locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setLockBack(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox lockBackCheckbox;
    /**
     * A checkbox used to set wether the menu key should be locked when the
     * screen is locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setLockMenu(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox lockMenuCheckbox;
    /**
     * A checkbox used to set wether the volume keys should be locked when the
     * screen is locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setLockVolume(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox lockVolumeCheckbox;
    /**
     * A checkbox used to set wether the status bar should be hidden when the
     * screen is locked. <br/><br/>
     * 
     * @see SimpleFlash.SettingsManager#setHideStatusbar(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox hideStatusbarCheckbox;
    /**
     * A checkbox used to set wether a {@link CameraOnNotification#getInstance(
     * android.content.Context, android.content.Intent, int, int)
     * CameraOnNotification} should be shown when the light is turned on. <br/>
     * <br/>
     * 
     * @see SimpleFlash.SettingsManager#setShowNotification(boolean)
     * 
     * @since Version 1.0
     */
    protected CheckBox showNotificationCheckbox;
    /**
     * A radio button used to set wether to resume an Activity sending a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification}, when the
     * Notification is clicked. <br/><br/>
     * 
     * @see #notificationFinishClickRadio
     * @see #notificationNothingClickRadio
     * @see SimpleFlash.SettingsManager#setNotificationClick(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationResumeClickRadio;
    /**
     * A radio button used to set wether to finish an Activity sending a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification}, when the
     * Notification is clicked. <br/><br/>
     * 
     * @see #notificationResumeClickRadio
     * @see #notificationNothingClickRadio
     * @see SimpleFlash.SettingsManager#setNotificationClick(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationFinishClickRadio;
    /**
     * A radio button used to set wether to do nothing, when a sent {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} is clicked. <br/>
     * <br/>
     * 
     * @see #notificationResumeClickRadio
     * @see #notificationFinishClickRadio
     * @see SimpleFlash.SettingsManager#setNotificationClick(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationNothingClickRadio;
    /**
     * A radio button used to set wether to resume an Activity sending a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification}, when the
     * Notification is cancelled. <br/><br/>
     * 
     * @see #notificationFinishCancelRadio
     * @see #notificationNothingCancelRadio
     * @see SimpleFlash.SettingsManager#setNotificationCancel(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationResumeCancelRadio;
    /**
     * A radio button used to set wether to finish an Activity sending a {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification}, when the
     * Notification is cancelled. <br/><br/>
     * 
     * @see #notificationResumeCancelRadio
     * @see #notificationNothingCancelRadio
     * @see SimpleFlash.SettingsManager#setNotificationCancel(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationFinishCancelRadio;
    /**
     * A radio button used to set wether to do nothing, when a sent {@link
     * CameraOnNotification#getInstance(android.content.Context,
     * android.content.Intent, int, int) CameraOnNotification} is cancelled.
     * <br/><br/>
     * 
     * @see #notificationResumeCancelRadio
     * @see #notificationFinishCancelRadio
     * @see SimpleFlash.SettingsManager#setNotificationCancel(int)
     * 
     * @since Version 1.0
     */
    protected RadioButton notificationNothingCancelRadio;
    
    /**
     * A {@link MenuItem} used to {@link ActionBarActivity#finish() finish} the
     * Activity (as well as all of its parent Activities). <br/><br/>
     * 
     * @since Version 1.0
     */
    protected MenuItem exitItem;
    
    /**
     * Inner class that handles the saving and loading of the user preferences.
     * <br/><br/>
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
            prefs.apply();
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
            prefs.apply();
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
            prefs.apply();
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
            prefs.apply();
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
            prefs.apply();
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
            prefs.apply();
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
         * Loads the advanced preferences of the application. <br/><br/>
         * 
         * This method loads only the preferences the user can change in this
         * Activity, but not the preferences the user can change in the
         * {@link SimpleFlash} core class. It doesn't return the values but
         * changes the state of their corresponding {@link View Views} directly.
         * 
         * @since Version 1.0
         */
        protected void loadPrefs()
        {
            volumekeysUsedCheckbox.setChecked(loadBooleanPref(R.string.volumekeys_used_pref, true));
            screenOnCheckbox.setChecked(loadBooleanPref(R.string.screen_on_pref, true));
            lockExitCheckbox.setChecked(loadBooleanPref(R.string.lock_exit_pref, false));
            lockBackCheckbox.setChecked(loadBooleanPref(R.string.lock_back_pref, false));
            lockMenuCheckbox.setChecked(loadBooleanPref(R.string.lock_menu_pref, false));
            lockVolumeCheckbox.setChecked(loadBooleanPref(R.string.lock_volume_pref, false));
            hideStatusbarCheckbox.setChecked(loadBooleanPref(R.string.hide_statusbar_pref, false));
            showNotificationCheckbox.setChecked(loadBooleanPref(R.string.show_notification_pref, true));
            switch(loadIntPref(R.string.notification_click_pref, CameraOnNotification.NOTIFICATION_RESUME))
            {
                case CameraOnNotification.NOTIFICATION_RESUME:
                    notificationResumeClickRadio.setChecked(true);
                    break;
                case CameraOnNotification.NOTIFICATION_FINISH:
                    notificationFinishClickRadio.setChecked(true);
                    break;
                case CameraOnNotification.NOTIFICATION_NOTHING:
                    notificationNothingClickRadio.setChecked(true);
            }
            switch(loadIntPref(R.string.notification_cancel_pref, CameraOnNotification.NOTIFICATION_FINISH))
            {
                case CameraOnNotification.NOTIFICATION_RESUME:
                    notificationResumeCancelRadio.setChecked(true);
                    break;
                case CameraOnNotification.NOTIFICATION_FINISH:
                    notificationFinishCancelRadio.setChecked(true);
                    break;
                case CameraOnNotification.NOTIFICATION_NOTHING:
                    notificationNothingCancelRadio.setChecked(true);
            }
        }
    }
    /**
     * An instance of the inner {@link PrefsManager} class. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected PrefsManager pm = new PrefsManager();
    
    /**
     * Performs the start up operations of this activity. <br/><br/>
     * 
     * Initializes the activity's ui elements.
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
        setContentView(R.layout.settings_layout);
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        volumekeysUsedCheckbox = (CheckBox) findViewById(R.id.volumekeys_used_checkbox);
        screenOnCheckbox = (CheckBox) findViewById(R.id.screen_on_checkbox);
        lockExitCheckbox = (CheckBox) findViewById(R.id.lock_exit_checkbox);
        lockBackCheckbox = (CheckBox) findViewById(R.id.lock_back_checkbox);
        lockMenuCheckbox = (CheckBox) findViewById(R.id.lock_menu_checkbox);
        lockVolumeCheckbox = (CheckBox) findViewById(R.id.lock_volume_checkbox);
        hideStatusbarCheckbox = (CheckBox) findViewById(R.id.hide_statusbar_checkbox);
	showNotificationCheckbox = (CheckBox) findViewById(R.id.show_notification_checkbox);
        notificationResumeClickRadio = (RadioButton) findViewById(R.id.notification_click_resume_radio);
        notificationFinishClickRadio = (RadioButton) findViewById(R.id.notification_click_finish_radio);
        notificationNothingClickRadio = (RadioButton) findViewById(R.id.notification_click_nothing_radio);
        notificationResumeCancelRadio = (RadioButton) findViewById(R.id.notification_cancel_resume_radio);
        notificationFinishCancelRadio = (RadioButton) findViewById(R.id.notification_cancel_finish_radio);
        notificationNothingCancelRadio = (RadioButton) findViewById(R.id.notification_cancel_nothing_radio);
        
        exitItem = (MenuItem) findViewById(R.id.exit_item);
    }
    
    /**
     * Performs the resume operations of this Activity. <br/><br/>
     * 
     * {@link PrefsManager#loadPrefs() Loads the preferences} and sets the
     * {@link #lockMenuCheckbox} {@link View#GONE GONE} and disabled if the SDK
     * version is lower than Eclair MR1 (because before this SDK version there
     * is no separate action bar to contain the lockItem of the {@link
     * SimpleFlash} Activity, that's why it is located in the normal options
     * menu so that providing the possibility to lock the menu key would be a
     * bad idea).
     * 
     * @see ActionBarActivity#onResume()
     * 
     * @since Version 1.0
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ECLAIR_MR1)
        {
            lockMenuCheckbox.setVisibility(View.GONE);
            lockMenuCheckbox.setEnabled(false);
        }
        
        pm.loadPrefs();
    }
    
    /**
     * {@link MenuInflater#inflate(int, android.view.Menu) Inflates} the options
     * menu for this Activity. <br/><br/>
     * 
     * Inflates the 'exit_actionbar_menu' resource.
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
        getMenuInflater().inflate(R.menu.exit_actionbar_menu, menu);
        
        return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * Handles clicks that are performed on the {@link CheckBox CheckBoxes} and
     * {@link RadioButton RadioButtons} of this Activity. <br/><br/>
     * 
     * Changes the corresponding preferences when the CheckBoxes and
     * RadioButtons are clicked.
     * 
     * @param view the View that was clicked by the user
     * 
     * @since Version 1.0
     */
    public void onButtonClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.volumekeys_used_checkbox:
                pm.saveBooleanPref(R.string.volumekeys_used_pref, volumekeysUsedCheckbox.isChecked());
		break;
            case R.id.screen_on_checkbox:
                pm.saveBooleanPref(R.string.screen_on_pref, screenOnCheckbox.isChecked());
                break;
            case R.id.lock_exit_checkbox:
                pm.saveBooleanPref(R.string.lock_exit_pref, lockExitCheckbox.isChecked());
		break;
            case R.id.lock_back_checkbox:
                pm.saveBooleanPref(R.string.lock_back_pref, lockBackCheckbox.isChecked());
		break;
            case R.id.lock_menu_checkbox:
                pm.saveBooleanPref(R.string.lock_menu_pref, lockMenuCheckbox.isChecked());
		break;
            case R.id.lock_volume_checkbox:
                pm.saveBooleanPref(R.string.lock_volume_pref, lockVolumeCheckbox.isChecked());
		break;
            case R.id.hide_statusbar_checkbox:
                pm.saveBooleanPref(R.string.hide_statusbar_pref, hideStatusbarCheckbox.isChecked());
		break;
            case R.id.show_notification_checkbox:
                pm.saveBooleanPref(R.string.show_notification_pref, showNotificationCheckbox.isChecked());
		break;
            case R.id.notification_click_resume_radio:
                pm.saveIntPref(R.string.notification_click_pref, CameraOnNotification.NOTIFICATION_RESUME);
                break;
            case R.id.notification_click_finish_radio:
                pm.saveIntPref(R.string.notification_click_pref, CameraOnNotification.NOTIFICATION_FINISH);
                break;
            case R.id.notification_click_nothing_radio:
                pm.saveIntPref(R.string.notification_click_pref, CameraOnNotification.NOTIFICATION_NOTHING);
                break;
            case R.id.notification_cancel_resume_radio:
                pm.saveIntPref(R.string.notification_cancel_pref, CameraOnNotification.NOTIFICATION_RESUME);
                break;
            case R.id.notification_cancel_finish_radio:
                pm.saveIntPref(R.string.notification_cancel_pref, CameraOnNotification.NOTIFICATION_FINISH);
                break;
            case R.id.notification_cancel_nothing_radio:
                pm.saveIntPref(R.string.notification_cancel_pref, CameraOnNotification.NOTIFICATION_NOTHING);
        }
    }
    
    /**
     * Handles the selection of items in the options menu. <br/><br/>
     * 
     * {@link ActionBarActivity#finish() Finishes} the Activity and {@link
     * ActionBarActivity#setResult(int) sets} the {@link
     * SimpleFlash#RESULT_FINISHED 'RESULT_FINISHED'} result (to finish all
     * parent Activities as well) if the {@link #exitItem} was selected.
     * 
     * @param item the {@link MenuItem} selected by the user
     * 
     * @return true if the selected item was handled in any way, the return
     *         value of the corresponding method of the super class otherwise
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
            case R.id.exit_item:
                setResult(SimpleFlash.RESULT_FINISHED);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}