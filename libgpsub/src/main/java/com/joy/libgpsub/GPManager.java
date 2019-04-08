package com.joy.libgpsub;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.joy.libgpsub.test.log.LLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by joybar on 26/01/2019.
 */

public class GPManager {
	private static final String TAG = "BillingManager";
	final WeakReference<Activity> mActivityWeakReference;

	private static BillingClient mBillingClient;
	private boolean mIsServiceConnected;

	final GpClientInitListener gpClientInitListener;
	final GpServiceConnectListener gpServiceConnectListener;
	GpPurchaseListener gpPurchaseListener;
	String billingType;

	/**
	 * 初始化
	 *
	 * @param mActivity
	 * @param gpClientInitListener
	 * @param gpServiceConnectListener
	 */
	public GPManager(Activity mActivity, GpClientInitListener gpClientInitListener, GpServiceConnectListener gpServiceConnectListener) {
		this.mActivityWeakReference = new WeakReference<>(mActivity);
		this.gpClientInitListener = gpClientInitListener;
		this.gpServiceConnectListener = gpServiceConnectListener;
		mBillingClient = BillingClient.newBuilder(mActivity).setListener(purchasesUpdatedListener).build();
		startServiceConnection(null);

	}


	/**
	 * 开始连接google服务并判断当前Google支付是否可用
	 *
	 * @param executeOnSuccess
	 */
	private void startServiceConnection(final Runnable executeOnSuccess) {
		mBillingClient.startConnection(new BillingClientStateListener() {
			@Override
			public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
				LLog.d(TAG, "Setup finished. Response code: " + billingResponseCode);
				if (billingResponseCode == BillingClient.BillingResponse.OK) {
					//说明可用，否则说明连接不上google
					mIsServiceConnected = true;
					if (executeOnSuccess != null) {
						executeOnSuccess.run();
					}
					if (gpServiceConnectListener != null) {
						gpServiceConnectListener.onServiceConnectSuccess();
					}

				} else {
					if (gpServiceConnectListener != null) {
						gpServiceConnectListener.onServiceConnectFail();
					}
				}
			}

			@Override
			public void onBillingServiceDisconnected() {
				//断开连接，当收到此回调时可以重新连接google否则会出现服务无法使用问题
				mIsServiceConnected = false;
				if (gpServiceConnectListener != null) {
					gpServiceConnectListener.onServiceConnectFail();
				}
			}
		});
	}

	/**
	 * 异步查询商品列表
	 *
	 * @param type            "inapp" "subs";
	 * @param skuList         skuList.add("product_subscribe_id_01");
	 * @param gpQueryListener
	 */
	public void querySkuDetailsAsync(final String type, final List<String> skuList, final GpQueryListener gpQueryListener) {

		// Creating a runnable from the request to use it inside our connection retry policy below
		Runnable queryRequest = new Runnable() {
			@Override
			public void run() {
				// Query the purchase async
				SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
				params.setSkusList(skuList).setType(type);
				mBillingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
					@Override
					public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {

						if (responseCode == BillingClient.BillingResponse.OK) {
							gpQueryListener.onQuerySuccess(type, skuDetailsList);
						} else {
							gpQueryListener.onQueryFail(responseCode);
						}
					}
				});
			}
		};
		executeServiceRequest(queryRequest);
	}

	/**
	 * @param type                   参数类型：SUBS 或者 INAPP
	 * @param gpQueryHistoryListener
	 */
	public void queryHistorySkuDetailsAsync(final String type, final GpQueryHistoryListener gpQueryHistoryListener) {
		Runnable queryToExecute = new Runnable() {
			@Override
			public void run() {
				if (BillingClient.SkuType.SUBS.equals(type)) {
					if (!areSubscriptionsSupported()) {
						LLog.d(TAG, "Skipped subscription purchases query since they are not " + "supported");
						if (null != gpQueryHistoryListener) {
							gpQueryHistoryListener.onQueryFail(0);
						}
						return;
					}
				}
				Purchase.PurchasesResult result = mBillingClient.queryPurchases(type);
				if (result != null && result.getResponseCode() == BillingClient.BillingResponse.OK) {
					if (null != gpQueryHistoryListener) {
						gpQueryHistoryListener.onQuerySuccess(type, result.getPurchasesList());
					}
				} else {
					if (null != gpQueryHistoryListener) {
						gpQueryHistoryListener.onQueryFail(result.getResponseCode());
					}
				}
			}
		};

		executeServiceRequest(queryToExecute);

	}


	/**
	 * 发起内购或者订阅
	 *
	 * @param activity
	 * @param type
	 * @param skuId
	 * @param purchaseListener
	 */
	public void purchase(final Activity activity, final String type, final String skuId, final GpPurchaseListener purchaseListener) {

		Runnable purchaseFlowRequest = new Runnable() {
			@Override
			public void run() {
				ArrayList list = new ArrayList(0);
				list.add("setOldSkus list");
				gpPurchaseListener = purchaseListener;
				billingType = type;
				BillingFlowParams purchaseParams = BillingFlowParams.newBuilder().setSku(skuId).setType(billingType).build();
				mBillingClient.launchBillingFlow(activity, purchaseParams);
			}
		};

		executeServiceRequest(purchaseFlowRequest);

	}

	/**
	 * 消耗商品
	 *
	 * @param purchase
	 * @param gpConsumeListener
	 */
	public void consumeAsync(final Purchase purchase, final GpConsumeListener gpConsumeListener) {

		// Creating a runnable from the request to use it inside our connection retry policy below
		if (null != purchase && !TextUtils.isEmpty(purchase.getPurchaseToken())) {
			Runnable consumeRequest = new Runnable() {
				@Override
				public void run() {
					// Consume the purchase async
					mBillingClient.consumeAsync(purchase.getPurchaseToken(), new ConsumeResponseListener() {

						@Override
						public void onConsumeResponse(int responseCode, String purchaseToken) {

							if (responseCode == BillingClient.BillingResponse.OK) {
								if (gpConsumeListener != null) {
									gpConsumeListener.onConsumeSuccess(purchaseToken);
								}
							} else {
								if (gpConsumeListener != null) {
									gpConsumeListener.onConsumeFail(responseCode);
								}
							}
						}
					});
				}
			};

			executeServiceRequest(consumeRequest);
		} else {
			if (gpConsumeListener != null) {
				gpConsumeListener.onConsumeFail(0);
			}
		}


	}

	private boolean areSubscriptionsSupported() {
		int responseCode = mBillingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);
		if (responseCode != BillingClient.BillingResponse.OK) {
			Log.w(TAG, "areSubscriptionsSupported() got an error response: " + responseCode);
		}
		return responseCode == BillingClient.BillingResponse.OK;
	}

	/**
	 * 异步执行任务
	 *
	 * @param runnable
	 */
	private void executeServiceRequest(Runnable runnable) {
		if (mIsServiceConnected) {
			runnable.run();
		} else {
			// If billing service was disconnected, we try to reconnect 1 time.
			// (feel free to introduce your retry policy here).
			startServiceConnection(runnable);
		}
	}


	public static class Builder {

		WeakReference<Activity> mActivityWeakReference;

		GpClientInitListener gpClientInitListener;
		GpServiceConnectListener gpServiceConnectListener;


		public Builder setGpClientInitListener(GpClientInitListener gpClientInitListener) {
			this.gpClientInitListener = gpClientInitListener;
			return this;
		}

		public Builder setGpServiceConnectListener(GpServiceConnectListener gpServiceConnectListener) {
			this.gpServiceConnectListener = gpServiceConnectListener;
			return this;
		}


		public Builder(Activity mActivity) {
			this.mActivityWeakReference = new WeakReference<>(mActivity);
		}

		public GPManager build() {
			if (null != mActivityWeakReference && null != mActivityWeakReference.get()) {
				return new GPManager(mActivityWeakReference.get(), gpClientInitListener, gpServiceConnectListener);
			}
			return null;
		}

	}

	PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
		@Override
		public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
			LLog.d(TAG, "onPurchasesUpdated,responseCode=" + responseCode);
			if (null != gpPurchaseListener) {
				if (responseCode == BillingClient.BillingResponse.OK) {
					gpPurchaseListener.onPurchaseSuccess(billingType, purchases);
				} else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
					gpPurchaseListener.onPurchaseFail(" user cancelled the purchase flow - " + "skipping", responseCode);
				} else {
					gpPurchaseListener.onPurchaseFail("got unknown resultCode = " + responseCode, responseCode);
				}
			} else {
				LLog.d(TAG, "onPurchasesUpdated, but purchasesUpdatedListener is null ");
			}

		}
	};
}

