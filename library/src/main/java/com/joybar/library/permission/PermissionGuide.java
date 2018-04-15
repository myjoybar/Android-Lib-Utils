package com.joybar.library.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import java.util.List;

/**
 * Created by joybar on 15/04/2018.
 */

public class PermissionGuide {

    /**
     * 显示提示对话框
     */
    public static void showTipsDialog(final Activity activity, String[] permissionNames) {

        StringBuilder sb = new StringBuilder();
        sb.append(PermissionUtils.PermissionTip1);
        List<String> deniedPermissions = PermissionManager.getInstance().getDeniedPermissions
                (activity, permissionNames);
        String name = PermissionUtils.getInstance().getPermissionNames(deniedPermissions);
        sb.append(name);
        sb.append(PermissionUtils.PermissionTip2);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(sb.toString());
        builder.setPositiveButton(PermissionUtils.PermissionDialogPositiveButton, new
                DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings(activity);
            }
        });
        builder.setNegativeButton(PermissionUtils.PermissionDialogNegativeButton, null);
        builder.show();
    }

    /**
     * 启动当前应用设置页面
     */
    private static void startAppSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        activity.startActivity(intent);
    }

}
