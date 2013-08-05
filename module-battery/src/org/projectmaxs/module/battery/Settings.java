/*
    This file is part of Project MAXS.

    MAXS and its modules is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MAXS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with MAXS.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.projectmaxs.module.battery;

import org.projectmaxs.shared.util.Log;
import org.projectmaxs.shared.util.Log.LogSettings;

import android.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Settings implements OnSharedPreferenceChangeListener {
	// App settings
	private final String DEBUG_LOG;
	private final String BATTERY_PLUG_STATUS = "BATTERY_PLUG_STATUS";
	private final String BATTERY_PERCENTAGE = "BATTERY_PERCENTAGE";

	private static Settings sSettings;

	public static Settings getInstance(Context context) {
		if (sSettings == null) {
			sSettings = new Settings(context);
		}
		return sSettings;
	}

	private SharedPreferences mSharedPreferences;
	private LogSettings mLogSettings;

	private Settings(Context context) {
		// this.mSharedPreferences =
		// context.getSharedPreferences(Constants.MAIN_PACKAGE,
		// Context.MODE_PRIVATE);
		this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

		DEBUG_LOG = context.getString(R.string.pref_app_debug_log_key);

		mSharedPreferences.registerOnSharedPreferenceChangeListener(this);

		mLogSettings = new Log.LogSettings() {
			@Override
			public boolean debugLog() {
				return isDebugLogEnabled();
			}
		};
	}

	public boolean isDebugLogEnabled() {
		return mSharedPreferences.getBoolean(DEBUG_LOG, false);
	}

	public Log.LogSettings getLogSettings() {
		return mLogSettings;
	}

	public SharedPreferences getSharedPreferences() {
		return mSharedPreferences;
	}

	public void setBatteryPlugStatus(int status) {
		mSharedPreferences.edit().putInt(BATTERY_PLUG_STATUS, status).commit();
	}

	public int getBatteryPlugStatus() {
		return mSharedPreferences.getInt(BATTERY_PLUG_STATUS, -1);
	}

	public void setBatteryPercentage(float percentage) {
		mSharedPreferences.edit().putFloat(BATTERY_PERCENTAGE, percentage);
	}

	public float getBatteryPercentage() {
		return mSharedPreferences.getFloat(BATTERY_PERCENTAGE, -1);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

	}

}
