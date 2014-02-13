package org.patneu.simpleflash;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * A helper class used to provide instances of the standard {@link Notification}
 * that this application sends to the user. <br/><br/>
 * 
 * It also provides some static values related to the behavior of this
 * Notification.
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
public class CameraOnNotification
{
    /**
     * The id to {@link SimpleFlash.NotifyManager#sendNotification(int,
     * android.app.Notification) send} a CameraOnNotification with. <br/><br/>
     * 
     * @since Version 1.0
     */
    public static final int CAMERA_ON_ID = 1;
    
    /**
     * A value indicating to resume its sending Activity when a
     * CameraOnNotification is clicked and/or cancelled. <br/><br/>
     * 
     * Primarily used as the 'click' or 'cancel' parameter of the {@link
     * #getInstance(android.content.Context, android.content.Intent, int, int)}
     * method.
     * 
     * @see #NOTIFICATION_FINISH
     * @see #NOTIFICATION_NOTHING
     * 
     * @since Version 1.0
     */
    public static final int NOTIFICATION_RESUME = 0;
    /**
     * A value indicating to finish its sending Activity when a
     * CameraOnNotification is clicked and/or cancelled. <br/><br/>
     * 
     * Primarily used as the 'click' or 'cancel' parameter of the {@link
     * #getInstance(android.content.Context, android.content.Intent, int, int)}
     * method.
     * 
     * @see #NOTIFICATION_RESUME
     * @see #NOTIFICATION_NOTHING
     * 
     * @since Version 1.0
     */
    public static final int NOTIFICATION_FINISH = 1;
    /**
     * A value indicating to do nothing when a CameraOnNotification is clicked
     * or cancelled. <br/><br/>
     * 
     * Primarily used as the 'click' or 'cancel' parameter of the {@link
     * #getInstance(android.content.Context, android.content.Intent, int, int)}
     * method.
     * 
     * @see #NOTIFICATION_RESUME
     * @see #NOTIFICATION_FINISH
     * 
     * @since Version 1.0
     */
    public static final int NOTIFICATION_NOTHING = 2;
    
    /**
     * A value to use as a parameter for {@link
     * Intent#setAction(java.lang.String), indicating the receiving Activity to
     * finish. <br/><br/>
     * 
     * @since Version 1.0
     */
    public static final String ACTION_FINISH = "android.intent.action.FINISH";
    
    private CameraOnNotification()
    {
    }
    
    /**
     * Returns an instance of this application's standard {@link Notification},
     * referred to as the 'CameraOnNotification'. <br/><br/>
     * 
     * This method gets a Notification to tell the user that the camera's
     * flashlight is still turned on, since unintentionally leaving it on may
     * damage the user's device. <br/><br/>
     * 
     * <b>Note:</b> Although this method is named 'getInstance', it doesn't
     *              actually use any constructor of this class (which wouldn't
     *              have sense anyway, since the class doesn't extend the
     *              Notification class. Instead, it internally builds a new
     *              Notification using the {@link NotificationCompat.Builder}
     *              class.
     * 
     * @param context the {@link Context} of the Activity that wants to {@link
     *                SimpleFlash.NotifyManager#sendNotification(int,
     *                android.app.Notification) send} this Notification
     * @param intent should be the {@link Intent} that originally started the
     *               sending Activity, as provided by {@link
     *               Activity#getIntent()}
     * @param click defines what to do if the user clicks this Notification,
     *              should be one of the static 'NOTIFICATION_XXX' values
     * @param cancel defines what to do if the user cancels this Notification,
     *               should be one of the static 'NOTIFICATION_XXX' values
     * 
     * @return an instance of this application's standard Notification with the
     *         specified behavior
     * 
     * @since Version 1.0
     */
    public static Notification getInstance(Context context, Intent intent, int click, int cancel)
    {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(R.drawable.ic_stat_notify);
        nBuilder.setContentTitle(context.getString(R.string.app_name));
        nBuilder.setContentText(context.getString(R.string.camera_on_notification_text));
        nBuilder.setTicker(context.getString(R.string.camera_on_notification_text));
        nBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        
        PendingIntent contentPending = null;
        PendingIntent deletePending = null;
        if(click != NOTIFICATION_NOTHING)
        {
            if(click == NOTIFICATION_RESUME)
            {
                contentPending = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            if(click == NOTIFICATION_FINISH)
            {
                Intent finishIntent = intent.cloneFilter();
                finishIntent.setAction(ACTION_FINISH);
                contentPending = PendingIntent.getActivity(context, 0, finishIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            nBuilder.setContentIntent(contentPending);
        }
        if(cancel != NOTIFICATION_NOTHING)
        {
            if(cancel == NOTIFICATION_RESUME)
            {
                deletePending = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            if(cancel == NOTIFICATION_FINISH)
            {
                Intent finishIntent = intent.cloneFilter();
                finishIntent.setAction(ACTION_FINISH);
                deletePending = PendingIntent.getActivity(context, 0, finishIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            }
            nBuilder.setDeleteIntent(deletePending);
        }
        
        return nBuilder.build();
    }
}