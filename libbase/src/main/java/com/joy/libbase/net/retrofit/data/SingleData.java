package com.joy.libbase.net.retrofit.data;

import android.support.annotation.Keep;

/**
 * Created by joybar on 5/24/16.
 */
@Keep
public class SingleData<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
