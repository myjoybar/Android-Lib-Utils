package com.joy.libgpsub;

import com.android.billingclient.api.SkuDetails;

import java.util.List;

/**
 * Created by joybar on 26/01/2019.
 */

public interface GpQueryListener {
     void onQuerySuccess(String type, List<SkuDetails> list);

     void onQueryFail(int errorCode);

}
