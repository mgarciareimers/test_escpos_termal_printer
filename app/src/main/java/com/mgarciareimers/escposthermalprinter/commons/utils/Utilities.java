package com.mgarciareimers.escposthermalprinter.commons.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import com.mgarciareimers.escposthermalprinter.commons.constants.Fields;

import java.util.List;

public class Utilities {
    /**
     * @description Method that checks the permissions.
     */
    public static boolean checkPermissions(Context context, String requestedPermission) {
        return ContextCompat.checkSelfPermission(context, requestedPermission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @description Method that transforms a list of strings to an array of strings.
     */
    public static String [] transformListToArrayStrings(List<String> stringList) {
        String [] stringArray = new String [stringList.size()];

        if (stringList == null || stringList.isEmpty()) {
            return null;
        }

        for (int i = 0; i < stringList.size(); i++) {
            stringArray[i] = stringList.get(i);
        }

        return stringArray;
    }

    /**
     * @description Method that opens the permissions screen.
     */
    public static void openPermissionsScreen(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts(Fields.packageText, context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
