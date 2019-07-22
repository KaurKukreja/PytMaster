package com.pictureyourtravel.pyt.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;

import java.util.List;

import androidx.core.content.FileProvider;

public class GenericFileProvider extends FileProvider {

    private static final String AUTHORITY = "sk.momosi.fuelup.file.provider";
    private static final String EXTERNAL_PATH = "external_files";

    /**
     * Grants URI permissions to all applications which can respond to given intent
     *
     * @param ctx
     * @param intent
     * @param uri
     * @param permissionFlags
     */
    public static void grantUriPermission(Activity ctx, Intent intent, Uri uri, int permissionFlags) {
        List<ResolveInfo> resolvedIntentActivities = ctx.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;

            ctx.grantUriPermission(packageName, uri, permissionFlags);
        }
    }

    public static String uriToPath(Uri uri) {
//        URI looks like this content://sk.momosi.fuelup.file.provider/external_files/Pictures/FuelUp/JPEF....
        if (!AUTHORITY.equals(uri.getAuthority())) {
            throw new IllegalArgumentException("This URI is not provided by " + GenericFileProvider.class.getSimpleName());
        }

        String path = uri.getEncodedPath();
        path = path.replace("/"+ EXTERNAL_PATH, Environment.getExternalStorageDirectory().getAbsolutePath());
        return path;
    }
}