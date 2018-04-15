package com.joybar.library.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 15/04/2018.
 */

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    public PermissionManager() {

    }

    public static PermissionManager getInstance() {
        return PermissionManagerHolder.INSTANCE;
    }

    private static class PermissionManagerHolder {
        private static PermissionManager INSTANCE = new PermissionManager();
    }


    /**
     * 检查权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public boolean checkPermissionAllGranted(Activity context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public List<String> getDeniedPermissions(Activity activity, String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager
                    .PERMISSION_GRANTED || ActivityCompat.shouldShowRequestPermissionRationale
                    (activity, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    public void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (checkPermissionAllGranted(activity, permissions)) {
           // permissionSuccess(requestCode);
        } else {
            List<String> needPermissions = getDeniedPermissions(activity, permissions);
            ActivityCompat.requestPermissions(activity, needPermissions.toArray(new
                    String[needPermissions.size()]), requestCode);
        }
    }

    public boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
     注：如果用户在过去拒绝了权限请求，并在权限请求系统对话框中选择了 Don’t ask again 选项，
     此方法将返回 false。如果设备规范禁止应用具有该权限，此方法也会返回 false。
     * @param activity
     * @param permissions
     * @return
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }
}
