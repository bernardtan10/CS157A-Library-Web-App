package model;

public class Checkout {
	String checkout_id;
	String checkout_date;
	String user_id;
	String asset_id;
	public String getCheckout_id() {
		return checkout_id;
	}
	public void setCheckout_id(String checkout_id) {
		this.checkout_id = checkout_id;
	}
	public String getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(String checkout_date) {
		this.checkout_date = checkout_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
}