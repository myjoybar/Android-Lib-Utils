package com.joy.libgpsub;

/**
 * Created by joybar on 26/01/2019.
 */

public interface GpConsumeListener {
     void onConsumeSuccess(String purchaseToken);

     void onConsumeFail(int errorCode);

}
