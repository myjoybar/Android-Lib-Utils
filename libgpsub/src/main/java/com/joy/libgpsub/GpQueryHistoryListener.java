package com.joy.libgpsub;

import com.android.billingclient.api.Purchase;

import java.util.List;

/**
 * Created by joybar on 26/01/2019.
 */

public interface GpQueryHistoryListener {
     void onQuerySuccess(String type, List<Purchase> list);

     void onQueryFail(int errorCode);

}
