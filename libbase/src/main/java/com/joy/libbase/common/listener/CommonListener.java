package com.joy.libbase.common.listener;


public abstract class CommonListener<T> {

   public abstract   void onSuccess(T data);

   public  void onFail(String message){

   }

   public  void onFail(int errorCode,String message){

   }

}
