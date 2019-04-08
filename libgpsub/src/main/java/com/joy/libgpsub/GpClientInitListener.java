package com.joy.libgpsub;

/**
 * Created by joybar on 26/01/2019.
 */

public interface GpClientInitListener {
    void onInitSuccess();

    void onInitFail(String errorMsg);
}
