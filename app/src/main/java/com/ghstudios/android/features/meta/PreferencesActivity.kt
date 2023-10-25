package com.ghstudios.android.features.meta

import android.os.Build
import android.os.Bundle
import android.preference.SwitchPreference
import android.preference.TwoStatePreference
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.support.v7.preference.ListPreference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.widget.SwitchCompat
import android.util.Log
import com.ghstudios.android.*
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.mhgendatabase.R
import kotlin.reflect.typeOf


/**
 * The activity hosting the PreferencesFragment, which does the real work.
 * This activity is top level so that you cannot go back to a previous fragment,
 * this allow language settings to be changed without restart.
 */
class PreferencesActivity : GenericActivity() {
    override fun getSelectedSection() = MenuSection.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setAsTopLevel()
    }

    override fun createFragment(): Fragment {
        return PreferencesFragment()
    }
}

class PreferencesFragment : PreferenceFragmentCompat() {
    private val defaultLabel get() = getString(R.string.preference_language_default)

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = AppSettings.SETTINGS_FILE_NAME

        setPreferencesFromResource(R.xml.preferences, rootKey)
        //initAppLanguages()
        initDataLanguages()
        //initBloatedMultiplier()
    }


    /**
     * Initialize app languages preference.
     * Currently unused, as its difficult to do and keeps changing every android version,
     * with potential bugs such as activity titles not changing.
     */
    private fun initAppLanguages() {
        val localePref = findPreference(AppSettings.PROP_APP_LOCALE) as ListPreference


        // not supported on older android versions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            localePref.isEnabled = false
            localePref.summary = getString(R.string.preference_app_language_description_disabled)
        } else {
            val languageCodes = listOf("") + appLanguages
            val languageNames = languageCodes.map { allLanguages.getOrElse(it) { defaultLabel } }

            localePref.entryValues = languageCodes.toTypedArray()
            localePref.entries = languageNames.toTypedArray()
            localePref.value = AppSettings.trueDataLocale // ensure a value is set
        }

    }

    private fun initDataLanguages() {
        val localePref = findPreference(AppSettings.PROP_DATA_LOCALE) as ListPreference

        val languageCodes = listOf("") + DataManager.get().getLanguages()
        val languageNames = languageCodes.map { allLanguages.getOrElse(it) { defaultLabel } }

        localePref.entryValues = languageCodes.toTypedArray()
        localePref.entries = languageNames.toTypedArray()
        localePref.value = AppSettings.trueDataLocale // ensure a value is set
    }


    private fun initBloatedMultiplier() {
        val bloatedPref = findPreference(AppSettings.PROP_BLOATED_ENABLED) as android.support.v14.preference.SwitchPreference
        //Log.d(bloatedPref.isChecked.toString(), "VAR")
        //Log.d(bloatedPref::class.java.typeName.toString(), "VAR")
        //Log.d(bloatedPref.get.toString(), "VAL")

        //Log.d(bloatedPref.toString(),"Works")
        //bloatedPref.isChecked = AppSettings.isBloatedEnabled
    }

}