package com.joybar.library.permission.interf;

/**
 * Created by joybar on 15/04/2018.
 */

public interface IPermission {

    //同意权限
    void permissionGranted();

    //拒绝权限并且选中不再提示
    void permissionDenied(int requestCode, String[] permissions);

    //取消权限
    void permissionCanceled(int requestCode);
}
