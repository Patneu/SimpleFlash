package org.patneu.simpleflash;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * An Activity providing the user access to meta information about the
 * application. <br/><br/>
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
public class AboutActivity extends ActionBarActivity
{
    /**
     * A {@link ScrollView} that inherits the whole layout of the Activity to
     * make it scrollable. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected ScrollView aboutScrollView;
    /**
     * A {@link TextView} that provides a link to the {@link
     * #appDescriptionLabel}. <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView appDescriptionLink;
    /**
     * A {@link TextView} that provides a link to the {@link #appFeaturesLabel}.
     * <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView appFeaturesLink;
    /**
     * A {@link TextView} that provides a link to the {@link
     * #appPermissionsLabel}. <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView appPermissionsLink;
    /**
     * A {@link TextView} that provides a link to the {@link #sourceCodeLabel}.
     * <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView sourceCodeLink;
    /**
     * A {@link TextView} that provides a link to the {@link #appLicenseLabel}.
     * <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView appLicenseLink;
    /**
     * A {@link TextView} that provides a link to the {@link
     * #liabilityDisclaimerLabel}. <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView liabilityDisclaimerLink;
    /**
     * A {@link TextView} that provides a link to the {@link
     * #additionalLicensesLabel}. <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView additionalLicensesLink;
    /**
     * A {@link TextView} that provides a link to the {@link
     * #thirdPartySoftwareLabel}. <br/><br/>
     * 
     * @see #onLinkClicked(android.view.View)
     * 
     * @since Version 1.0
     */
    protected TextView thirdPartySoftwareLink;
    /**
     * A {@link TextView} that provides a description of the application for the
     * user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView appDescriptionLabel;
    /**
     * A {@link TextView} that provides information about the application's
     * features for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView appFeaturesLabel;
    /**
     * A {@link TextView} that provides information about the application's
     * permissions for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView appPermissionsLabel;
    /**
     * A {@link TextView} that provides information about the application's
     * source code for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView sourceCodeLabel;
    /**
     * A {@link TextView} that provides information about the application's
     * license for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView appLicenseLabel;
    /**
     * A {@link TextView} that provides the application's liability disclaimer
     * for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView liabilityDisclaimerLabel;
    /**
     * A {@link TextView} that provides information about the application's
     * additional licenses for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView additionalLicensesLabel;
    /**
     * A {@link TextView} that provides information about third party software
     * used by the application for the user. <br/><br/>
     * 
     * @since Version 1.0
     */
    protected TextView thirdPartySoftwareLabel;
    
    /**
     * A {@link MenuItem} used to {@link ActionBarActivity#finish() finish} the
     * Activity (as well as all of its parent Activities). <br/><br/>
     * 
     * @since Version 1.0
     */
    protected MenuItem exitItem;
    
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        aboutScrollView = (ScrollView) findViewById(R.id.about_scrollview);
        appDescriptionLink = (TextView) findViewById(R.id.app_description_link);
        appFeaturesLink = (TextView) findViewById(R.id.app_features_link);
        appPermissionsLink = (TextView) findViewById(R.id.app_permissions_link);
        sourceCodeLink = (TextView) findViewById(R.id.source_code_link);
        appLicenseLink = (TextView) findViewById(R.id.app_license_link);
        liabilityDisclaimerLink = (TextView) findViewById(R.id.liability_disclaimer_link);
        additionalLicensesLink = (TextView) findViewById(R.id.additional_licenses_link);
        thirdPartySoftwareLink = (TextView) findViewById(R.id.third_party_software_link);
        appDescriptionLabel = (TextView) findViewById(R.id.app_description_label);
        appFeaturesLabel = (TextView) findViewById(R.id.app_features_label);
        appPermissionsLabel = (TextView) findViewById(R.id.app_permissions_label);
        sourceCodeLabel = (TextView) findViewById(R.id.source_code_label);
        appLicenseLabel = (TextView) findViewById(R.id.app_license_label);
        liabilityDisclaimerLabel = (TextView) findViewById(R.id.liability_disclaimer_label);
        additionalLicensesLabel = (TextView) findViewById(R.id.additional_licenses_label);
        thirdPartySoftwareLabel = (TextView) findViewById(R.id.third_party_software_label);
        
        exitItem = (MenuItem) findViewById(R.id.exit_item);
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
     * Handles clicks that are performed on the various 'xxxLink' {@link
     * TextView TextViews} of this Activity. <br/><br/>
     * 
     * If one of the 'xxxLink' TextViews is clicked, this method tells the
     * {@link #aboutScrollView} to scroll to the beginning of the corresponding
     * 'xxxLabel' TextView. Therewith, this method provides the functionality
     * of a table of contents for the user.
     * 
     * @param view the View that was clicked by the user
     * 
     * @since Version 1.0
     */
    public void onLinkClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.app_description_link:
                aboutScrollView.smoothScrollTo(0, (int) appDescriptionLabel.getY());
                break;
            case R.id.app_features_link:
                aboutScrollView.smoothScrollTo(0, (int) appFeaturesLabel.getY());
                break;
            case R.id.app_permissions_link:
                aboutScrollView.smoothScrollTo(0, (int) appPermissionsLabel.getY());
                break;
            case R.id.source_code_link:
                aboutScrollView.smoothScrollTo(0, (int) sourceCodeLabel.getY());
                break;
            case R.id.app_license_link:
                aboutScrollView.smoothScrollTo(0, (int) appLicenseLabel.getY());
                break;
            case R.id.liability_disclaimer_link:
                aboutScrollView.smoothScrollTo(0, (int) liabilityDisclaimerLabel.getY());
                break;
            case R.id.additional_licenses_link:
                aboutScrollView.smoothScrollTo(0, (int) additionalLicensesLabel.getY());
                break;
            case R.id.third_party_software_link:
                aboutScrollView.smoothScrollTo(0, (int) thirdPartySoftwareLabel.getY());
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