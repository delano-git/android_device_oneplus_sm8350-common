package org.aosp.device.OnePlusSettings.ModeSwitch;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;

import org.aosp.device.OnePlusSettings.Utils.FileUtils;

public class EdgeTouchSwitch implements OnPreferenceChangeListener {

    private static final String FILE = "/proc/touchpanel/tpedge_limit_enable";

    public static String getFile() {
        if (FileUtils.isFileWritable(FILE)) {
            return FILE;
        }
        return null;
    }

    public static boolean isSupported() {
        return FileUtils.isFileWritable(getFile());
    }

    public static boolean isCurrentlyEnabled(Context context) {
        return FileUtils.getFileValueAsBoolean(getFile(), false);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        FileUtils.writeLine(getFile(), enabled ? "1" : "0");
        return true;
    }
}
