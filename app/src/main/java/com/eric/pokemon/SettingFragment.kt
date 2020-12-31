package com.eric.pokemon

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

/**
 * Created by eric on 20-12-28
 */
class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pokemon_setting, rootKey)
    }
}