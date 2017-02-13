package com.limox.jesus.manageproductcontentprovider.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.limox.jesus.manageproductcontentprovider.R;

/**
 * Created by usuario on 2/11/16.
 */

public class AccountSettings_Activity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }
}
