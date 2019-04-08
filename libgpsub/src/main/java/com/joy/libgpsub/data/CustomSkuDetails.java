package com.joy.libgpsub.data;

import android.support.annotation.Keep;

/**
 * Created by joybar on 2019/2/12.
 */

@Keep
public class CustomSkuDetails {

	/**
	 * productId : product_subscribe_id_01
	 * type : subs
	 * price : US$0.99
	 * price_amount_micros : 990000
	 * price_currency_code : USD
	 * subscriptionPeriod : P1M
	 * title : product_subscribe_01 (TestGooglePay)
	 * description : product_subscribe_01
	 */

	private String productId;
	private String type;
	private String price;
	private double price_amount_micros;
	private String price_currency_code;
	private String subscriptionPeriod;
	private String freeTrialPeriod;
	private String title;
	private String description;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public double getPrice_amount_micros() {
		return price_amount_micros;
	}

	public void setPrice_amount_micros(double price_amount_micros) {
		this.price_amount_micros = price_amount_micros;
	}

	public String getPrice_currency_code() {
		return price_currency_code;
	}

	public void setPrice_currency_code(String price_currency_code) {
		this.price_currency_code = price_currency_code;
	}

	public String getSubscriptionPeriod() {
		return subscriptionPeriod;
	}

	public void setSubscriptionPeriod(String subscriptionPeriod) {
		this.subscriptionPeriod = subscriptionPeriod;
	}

	public String getFreeTrialPeriod() {
		return freeTrialPeriod;
	}

	public void setFreeTrialPeriod(String freeTrialPeriod) {
		this.freeTrialPeriod = freeTrialPeriod;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CustomSkuDetails{" + "productId='" + productId + '\'' + ", type='" + type + '\'' + ", price='" + price + '\'' + ", " +
				"price_amount_micros=" + price_amount_micros + ", price_currency_code='" + price_currency_code + '\'' + ", subscriptionPeriod='" +
				subscriptionPeriod + '\'' + ", freeTrialPeriod='" + freeTrialPeriod + '\'' + ", title='" + title + '\'' + ", description='" +
				description + '\'' + '}';
	}
}


