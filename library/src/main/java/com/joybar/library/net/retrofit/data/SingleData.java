package com.joybar.library.net.retrofit.data;

/**
 * Created by joybar on 5/24/16.
 */
public class SingleData<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
