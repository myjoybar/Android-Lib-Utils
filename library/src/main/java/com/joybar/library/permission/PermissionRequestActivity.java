package com.joybar.library.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.joybar.library.R;
import com.joybar.library.permission.interf.IPermission;

/**
 * Created by joybar on 15/04/2018.
 */

public class PermissionRequestActivity extends Activity {
    private static final String TAG = "PermissionManager";
    private static IPermission permissionListener;
    private String[] permissions;
    private static final String PERMISSIONS = "permissions";
    private static final String REQUEST_CODE = "request_code";
    private int requestCode;

    public static void permissionRequest(Context context, String[] permissions, int requestCode,
                                         IPermission iPermission) {
        permissionListener = iPermission;
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSIONS, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            permissions = bundle.getStringArray(PERMISSIONS);
            requestCode = bundle.getInt(REQUEST_CODE, 0);
        }
        if (permissions == null || permissions.length <= 0) {
            finish();
            return;
        }
        requestPermission();
    }

    /**
     * 申请权限
     */
    private void requestPermission() {
        if (PermissionManager.getInstance().checkPermissionAllGranted(this, permissions)) {
            permissionListener.permissionGranted();
            finish();
        } else {
            PermissionManager.getInstance().requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (this.requestCode == requestCode) {

            if (PermissionManager.getInstance().verifyPermissions(grantResults)) {
                //所有权限都同意
                if (permissionListener != null) {
                    permissionListener.permissionGranted();
                }
            } else {
                if (permissionListener != null) {
                    permissionListener.permissionDenied(requestCode, permissions);
                }
            }
        }
        finish();
    }
}
