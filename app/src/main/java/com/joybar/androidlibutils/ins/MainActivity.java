package com.joybar.androidlibutils.ins;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.joy.libbase.base.util.common.device.DeviceUtils;
import com.joy.libbase.io.mmkv.MMKVManager;
import com.joy.libbase.json.GsonUtil;
import com.joy.libok.OkHttpManager;
import com.joy.libok.OkHttpManager2;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.response.responsehandler.GsonResponseHandler;
import com.joy.libok.test.log.LLog;
import com.joybar.androidlibutils.R;
import com.joybar.androidlibutils.data.HuoYingData;
import com.joybar.androidlibutils.ins.data.InsBaseResponseData;
import com.joybar.androidlibutils.ins.data.InsRequestCallBack;
import com.joybar.androidlibutils.ins.data.feed.FeedResponseData;
import com.joybar.androidlibutils.ins.data.feed.GetFeedRequest;
import com.joybar.androidlibutils.ins.data.follower.FollowersResponseData;
import com.joybar.androidlibutils.ins.data.follower.GetFollowersRequest;
import com.joybar.androidlibutils.ins.data.following.FollowingResponseData;
import com.joybar.androidlibutils.ins.data.following.GetFollowingRequest;
import com.joybar.androidlibutils.ins.data.followingcancel.FollowingCancelPayload;
import com.joybar.androidlibutils.ins.data.followingcancel.FollowingCancelRequest;
import com.joybar.androidlibutils.ins.data.followingcancel.FollowingCancelResponseData;
import com.joybar.androidlibutils.ins.data.followingcreate.FollowingCreatePayload;
import com.joybar.androidlibutils.ins.data.followingcreate.FollowingCreateRequest;
import com.joybar.androidlibutils.ins.data.followingcreate.FollowingCreateResponseData;
import com.joybar.androidlibutils.ins.data.header.GetHeaderRequest;
import com.joybar.androidlibutils.ins.data.login.LoginPayload;
import com.joybar.androidlibutils.ins.data.login.LoginRequest;
import com.joybar.androidlibutils.ins.data.login.LoginResponseData;
import com.joybar.androidlibutils.ins.data.mediacomments.GetMediaCommentsRequest;
import com.joybar.androidlibutils.ins.data.mediacomments.MediaCommentResponseData;
import com.joybar.androidlibutils.ins.data.medialike.MediaLikePayload;
import com.joybar.androidlibutils.ins.data.medialike.MedialLikeRequest;
import com.joybar.androidlibutils.ins.data.medialikers.GetMediaLikersRequest;
import com.joybar.androidlibutils.ins.data.medialikers.MediaLikersResponseData;
import com.joybar.androidlibutils.ins.data.mediaunlike.MediaUnLikePayload;
import com.joybar.androidlibutils.ins.data.mediaunlike.MedialUnLikeRequest;
import com.joybar.androidlibutils.ins.data.userinfo.UserInfoRequest;
import com.joybar.androidlibutils.ins.sign.IgSignatureUtils;

import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "MainActivity";
	private Context mContext;
	FeedResponseData mFeedResponseDataAll = new FeedResponseData();
	FeedResponseData mFeedResponseDataLiked = new FeedResponseData();
	FollowersResponseData mFollowersResponseData = new FollowersResponseData();
	FollowingResponseData mFollowingResponseData = new FollowingResponseData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ins);
		mContext = this;
		initInsFiledManger();
		MMKVManager.getInstance().init(this);
		initListener();
		initOkManager();

	}

	private void initInsFiledManger() {
		InsCommonFieldsManager.getInstance().init(this);
	}

	private void initListener() {
		findViewById(R.id.btn_getcsftoken).setOnClickListener(this);
		findViewById(R.id.btn_login).setOnClickListener(this);
		findViewById(R.id.btn_getfeed).setOnClickListener(this);
		findViewById(R.id.btn_getfeedlike).setOnClickListener(this);
		findViewById(R.id.btn_followers).setOnClickListener(this);
		findViewById(R.id.btn_following).setOnClickListener(this);
		findViewById(R.id.btn_media_likers).setOnClickListener(this);
		findViewById(R.id.btn_media_comments).setOnClickListener(this);
		findViewById(R.id.btn_getUserInfo).setOnClickListener(this);
		findViewById(R.id.btn_following_create).setOnClickListener(this);
		findViewById(R.id.btn_following_cancel).setOnClickListener(this);
		findViewById(R.id.btn_media_like).setOnClickListener(this);
		findViewById(R.id.btn_media_unlike).setOnClickListener(this);


	}

	private void initOkManager() {
		OKConfigData okConfigData = new OKConfigData();
		ClearableCookieJar cookieJar =
				new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mContext));
		okConfigData.setCookiesJar(cookieJar);
		OkHttpManager.getInstance().init(okConfigData);

		OkHttpManager2.getInstance().init(okConfigData);


	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_getcsftoken:
				//getCsftoken();
				getCsftoken22();
				//test2("joy.dingtone", "123456inst");
				break;

			case R.id.btn_login:

				//login("joy.dingtone", "123456inst"); //10171477409
				//login("baily.teesy", "bj12345678");
				//login("wwwaitinglwt", "669845lwt");  //pkId = 322961280
				//login("pidan_baby", "destiny411"); // pkId = 6500982440
				login22("pidan_baby", "destiny411"); // pkId = 6500982440
				break;

			case R.id.btn_getfeed:
				//getFeed(true);
				getFeed22(true,"");
				break;

			case R.id.btn_getfeedlike:
				getFeedLiked(true);
				break;

			case R.id.btn_followers:
				//getFollowers(true);
				getFollowers22(true,"");
				break;
			case R.id.btn_following:
				//getFollowing(true);
				getFollowing222(true,"");
				break;

			case R.id.btn_media_likers:
				getMediaLikers();
				//getMediaLikers("1912206998860865471_322961280");
				//getMediaLikers("1809442075797084161_6500982440");
				//getMediaLikers("1815244845150013945_6500982440");


				break;

			case R.id.btn_media_comments:
				getMediaComments();
				break;

			case R.id.btn_getUserInfo:
				getUserInfo("6500982440");
				break;


			case R.id.btn_following_create:
				following_create("10171477409");
				break;



			case R.id.btn_following_cancel:
				following_cancel("10171477409");
				break;


			case R.id.btn_media_like:
				mediaLike("1986863750145515832_10171477409");
				break;


			case R.id.btn_media_unlike:
				mediaUnLike("1986863750145515832_10171477409");
				break;

			default:
				break;
		}
	}


	private void test() {
		String url = "https://www.391k.com/api/xapi.ashx/info.json";
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("key", "bd_hyrzjjfb4modhj");
		paramsMap.put("size", "10");
		paramsMap.put("page", "1");
		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.execute(new GsonResponseHandler<HuoYingData>() {
					@Override
					public void onSuccess(int statusCode, HuoYingData huoYingData) {
						LLog.d(TAG, huoYingData.toString());
					}

					@Override
					public void onFailure(int errorCode, String errorMsg) {

					}
				});
	}
//
//	Host: i.instagram.com
//	Connection: keep-alive
//	X-IG-Connection-Type: mobile(UMTS)
//	X-IG-Capabilities: 3ToAAA==
//	Accept-Language: en-US
//	Cookie: csrftoken=g79dofABCDEFGII3LI7YdHei1234567; mid=WFI52QABAAGrbKL-ABCDEFGHIJK
//	User-Agent: Instagram 10.3.0 Android (18/4.3; 320dpi; 720x1280; Xiaomi; HM 1SW; armani; qcom; en_US)
//	Accept-Encoding: gzip, deflate, sdch


//"Connection" : "close",
//		"Accept" : "*/*",
//		"Cookie2" : "$Version=1",
//		"Accept-Language" : "en-US",
//		"User-Agent" : "Instagram 10.21.0 Android (23/6.0.1; 640dpi; 1440x2560; samsung; SM-G935F; hero2lte; samsungexynos8890; en_US)"


	private void getCsftoken() {
		String url = IGConfig.API_V1 + IGConfig.ACTION_GET_HEADER;


		OkHttpManager.getInstance()
				.get(url)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<InsBaseResponseData>() {

					@Override
					public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
						//LLog.d(TAG, huoYingData.toString());
					}

					@Override
					public void onFailure(int errorCode, String errorMsg) {

					}

					@Override
					public void onGetHeaders(Headers headers) {
						super.onGetHeaders(headers);
						List<String> cookies = headers.values("Set-Cookie");
						int length = 0;
						if (cookies != null && (length = cookies.size()) != 0) {
							for (int i = 0; i < length; i++) {
								String session = cookies.get(i);

								char semicolon = ';';
								if (session.contains(IGConfig.CSRFTOKEN)) {
									int startIndex = session.indexOf(IGConfig.CSRFTOKEN) + IGConfig.CSRFTOKEN.length() + 1;
									int endIndex = 0;
									char[] chars = session.toCharArray();
									for (int j = startIndex; j < chars.length; j++) {
										if (chars[j] == semicolon) {
											endIndex = j;
											break;
										}
									}
									String result = session.substring(startIndex, endIndex);
									IGMKVManager.saveCsrftoken(result);
									Log.d(TAG, "result= " + result);
								}

							}

						}


					}
				});



	}

	private void getCsftoken22() {
		final GetHeaderRequest getHeaderRequest = new GetHeaderRequest();
		getHeaderRequest.execute(new InsRequestCallBack() {
			@Override
			public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
				getHeaderRequest.getCsrfCookie();
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {

			}
		});



	}






//
//	  ->addPost('phone_id', $this->phone_id)
//			->addPost('_csrftoken', $this->client->getToken())
//			->addPost('username', $this->username)
//			->addPost('adid', $this->advertising_id)
//			->addPost('guid', $this->uuid)
//			->addPost('device_id', $this->device_id)
//			->addPost('password', $this->password)
//			->addPost('login_attempt_count', 0)



	private void test2(String userName, String pwd){

		String url = IGConfig.API_V1 + IGConfig.ACTION_LOGIN;


		String csrftoken = IGMKVManager.getCsrftoken();
		Log.d(TAG, "csrftoken = " + csrftoken);
		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("_csrftoken", csrftoken);
		paramsMap.put("username", userName);
		paramsMap.put("guid", IGUtils.generateUuid(false));
		paramsMap.put("password", pwd);
		paramsMap.put("device_id", DeviceUtils.getAndroidId(mContext));
		//paramsMap.put("phone_id", DeviceUtils.getAndroidId(mContext));
		paramsMap.put("login_attempt_count", "0");

		OkHttpManager2.getInstance().requestPost(url, paramsMap, IGConfig.getHeadersPHp3(), new InsGsonResponseHandler<LoginResponseData>() {
			@Override
			public void onSuccess(int statusCode, LoginResponseData response) {

			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				super.onFailure(errorCode, errorMsg);
			}
		});

//
//		OkHttpManager.getInstance()
//				.post(url)
//				//.addBodyContent(IgSignatureUtils.buildBodySignContent22(paramsMap))
//				.addParams(paramsMap)
//				.addHeaders(IGConfig.getHeadersPHp3())
//				.execute(new InsGsonResponseHandler<LoginResponseData>() {
//					@Override
//					public void onSuccess(int statusCode,LoginResponseData insBaseData) {
//						LLog.d(TAG, insBaseData.toString());
//						LoginResponseData.LoggedInUserBean loggedInUserBean = null;
//						if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null) {
//							String pkId = loggedInUserBean.getPk() + "";
//							if (!TextUtils.isEmpty(pkId)) {
//								IGMKVManager.savePKID(pkId);
//							}
//							Log.d(TAG, "pkId = " + pkId);
//
//						}
//					}
//
//					@Override
//					public void onFailure(int errorCode, String errorMsg) {
//						super.onFailure(errorCode, errorMsg);
//						LLog.d(TAG, String.format("errorCode= %s , errorMsg = %s", errorCode,errorMsg));
//
//					}
//				});
	}
	private void login(String userName, String pwd) {
		String url = IGConfig.API_V1 + IGConfig.ACTION_LOGIN;

		String csrftoken = IGMKVManager.getCsrftoken();
		LoginPayload loginPayload = new LoginPayload();
		loginPayload.set_csrftoken(csrftoken);
		loginPayload.setUsername(userName);
		loginPayload.setPassword(pwd);
		loginPayload.setGuid(IGUtils.generateUuid(true));
		loginPayload.setDevice_id(IGUtils.generateDeviceId(userName,pwd));
		loginPayload.setPhone_id(IGUtils.generateUuid(true));
		loginPayload.setLogin_attempt_account(0);

		String payload = GsonUtil.parseBeanToStr(loginPayload);

		payload = IgSignatureUtils.generateSignature(payload);
		//payload = IgSignatureUtils.buildBodySignContent(loginPayload.getPayLoad());

//		OkHttpManager2.getInstance().requestPost(url, paramsMap, IGConfig.getHeadersPHp3(), new InsGsonResponseHandler<LoginResponseData>() {
//			@Override
//			public void onSuccess(int statusCode, LoginResponseData response) {
//
//			}
//
//			@Override
//			public void onFailure(int errorCode, String errorMsg) {
//				super.onFailure(errorCode, errorMsg);
//			}
//		});

		OkHttpManager.getInstance()
				.post(url)
				//.addBodyContent(IgSignatureUtils.buildBodySignContent22(paramsMap))
				.addBodyContent(payload)
				//.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<LoginResponseData>() {
					@Override
					public void onSuccess(int statusCode,LoginResponseData insBaseData) {
						LLog.d(TAG, insBaseData.toString());
						LoginResponseData.LoggedInUserBean loggedInUserBean = null;
						if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null) {
							String pkId = loggedInUserBean.getPk() + "";
							if (!TextUtils.isEmpty(pkId)) {
								IGMKVManager.savePKID(pkId);
							}
							Log.d(TAG, "pkId = " + pkId);
						}
					}

					@Override
					public void onFailure(int errorCode, String errorMsg) {
						super.onFailure(errorCode, errorMsg);
						LLog.d(TAG, String.format("errorCode= %s , errorMsg = %s", errorCode,errorMsg));

					}
				});

	}



	private void login22(String userName, String pwd) {

		String csrftoken = IGMKVManager.getCsrftoken();
		LoginPayload loginPayload = new LoginPayload();
		loginPayload.set_csrftoken(csrftoken);
		loginPayload.setUsername(userName);
		loginPayload.setPassword(pwd);
		loginPayload.setGuid(IGUtils.generateUuid(true));
		loginPayload.setDevice_id(IGUtils.generateDeviceId(userName,pwd));
		loginPayload.setPhone_id(IGUtils.generateUuid(true));
		loginPayload.setLogin_attempt_account(0);

		//InsBasePostRequest<LoginPayload,LoginResponseData> loginRequest =
		//		new InsBasePostRequest<LoginPayload,LoginResponseData>(IGConfig.ACTION_LOGIN, loginPayload);

		LoginRequest loginRequest = new LoginRequest(loginPayload);

		loginRequest.execute(new InsRequestCallBack<LoginResponseData>() {
			@Override
			public void onSuccess(int statusCode, LoginResponseData insBaseData) {
				LLog.d(TAG, insBaseData.toString());
				LoginResponseData.LoggedInUserBean loggedInUserBean = null;
				if (insBaseData != null && (loggedInUserBean = insBaseData.getLogged_in_user()) != null) {
					String pkId = loggedInUserBean.getPk() + "";
					if (!TextUtils.isEmpty(pkId)) {
						IGMKVManager.savePKID(pkId);
					}
					Log.d(TAG, "pkId = " + pkId);
				}
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				LLog.d(TAG, String.format("errorCode= %s , errorMsg = %s", errorCode, errorMsg));
			}
		});



	}







	private void getFeed(boolean isFirstPage) {

		String userId = IGMKVManager.getPKID();
		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_FEED, userId);

		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("rank_token", String.format("%s_%s", userId, DeviceUtils.getAndroidId(mContext)));//rank_token本地拼接的userid_deviceID （
		paramsMap.put("ranked_content", "true");
		if(isFirstPage){
			mFeedResponseDataAll.getItems().clear();
		}else{
			paramsMap.put("max_id", mFeedResponseDataAll.getNext_max_id());
		}



		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<FeedResponseData>() {
					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getFeed onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, FeedResponseData response) {
						Log.d(TAG, "getFeed onSuccess " );

						mFeedResponseDataAll.setAuto_load_more_enabled(response.isAuto_load_more_enabled());
						mFeedResponseDataAll.setNum_results(response.getNum_results()+response.getNum_results());
						mFeedResponseDataAll.setNext_max_id(response.getNext_max_id());
						mFeedResponseDataAll.setMore_available(response.isMore_available());
						mFeedResponseDataAll.getItems().addAll(response.getItems());

						if(response.isMore_available()){
							getFeed(false);
						}else{
							Log.d(TAG, String.format("getFeed request end and the size = %s ",mFeedResponseDataAll.getItems().size()) );
						}


					}
				});





	}

	private void getFeed22(boolean isFirstPage,String nextMaxId) {

		if (isFirstPage) {
			mFeedResponseDataAll.getItems().clear();
		}
		String userId = IGMKVManager.getPKID();
		GetFeedRequest getFeedRequest = new GetFeedRequest(isFirstPage,userId,nextMaxId);
		getFeedRequest.execute(new InsRequestCallBack<FeedResponseData>() {
			@Override
			public void onSuccess(int statusCode, FeedResponseData response) {

				mFeedResponseDataAll.setAuto_load_more_enabled(response.isAuto_load_more_enabled());
				mFeedResponseDataAll.setNum_results(response.getNum_results()+response.getNum_results());
				mFeedResponseDataAll.setNext_max_id(response.getNext_max_id());
				mFeedResponseDataAll.setMore_available(response.isMore_available());
				mFeedResponseDataAll.getItems().addAll(response.getItems());

				if(response.isMore_available()){
					getFeed22(false,mFeedResponseDataAll.getNext_max_id());
				}else{
					Log.d(TAG, String.format("getFeed request end and the size = %s ",mFeedResponseDataAll.getItems().size()) );
				}
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getFeed onFailure = " + errorMsg);
			}
		});


	}




	/**
	 * 获取点赞的post
	 * @param isFirstPage
	 */
	private void getFeedLiked(boolean isFirstPage) {

		String url = IGConfig.API_V1 + IGConfig.ACTION_GET_FEED_LIKED;

		String userId = IGMKVManager.getPKID();


		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("uid", userId);


		if(isFirstPage){
			mFeedResponseDataLiked.getItems().clear();
		}else{
			paramsMap.put("max_id", mFeedResponseDataLiked.getNext_max_id());
		}





		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<FeedResponseData>() {
					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getFeedLiked onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, FeedResponseData response) {
						Log.d(TAG, "getFeedLiked onSuccess " );

						mFeedResponseDataLiked.setAuto_load_more_enabled(response.isAuto_load_more_enabled());
						mFeedResponseDataLiked.setNum_results(response.getNum_results()+response.getNum_results());
						mFeedResponseDataLiked.setNext_max_id(response.getNext_max_id());
						mFeedResponseDataLiked.setMore_available(response.isMore_available());
						mFeedResponseDataLiked.getItems().addAll(response.getItems());

						if(response.isMore_available()){
							getFeedLiked(false);
						}else{
							Log.d(TAG, String.format("getFeedLiked request end and the size = %s ",mFeedResponseDataLiked.getItems().size()) );
						}

					}
				});





	}


	/**
	 * 获取followers
	 * @param isFirstPage
	 */
	private void getFollowers(boolean isFirstPage) {

		String userId = IGMKVManager.getPKID();
		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_FOLLOWERS, userId);



		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("rank_token", String.format("%s_%s", userId, DeviceUtils.getAndroidId(mContext)));//rank_token本地拼接的userid_deviceID （
		paramsMap.put("ranked_content", "true");

		if(isFirstPage){
			mFollowersResponseData.getUsers().clear();
		}else{
			paramsMap.put("max_id", mFollowersResponseData.getNext_max_id());
		}




		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<FollowersResponseData>() {

					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getFollowers onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, FollowersResponseData response) {
						Log.d(TAG, "getFollowers onSuccess " );
						mFollowersResponseData.setBig_list(response.isBig_list());
						mFollowersResponseData.setNext_max_id(response.getNext_max_id());
						mFollowersResponseData.setPage_size(mFollowersResponseData.getPage_size()+response.getPage_size());
						mFollowersResponseData.getUsers().addAll(response.getUsers());

						if(!TextUtils.isEmpty(response.getNext_max_id())){
							getFollowers(false);
						}else{
							Log.d(TAG, String.format("getFollowers request end and the size = %s ",mFollowersResponseData.getUsers().size()) );
						}



					}


				});



	}





	private void getFollowers22(boolean isFirstPage,String nextMaxId) {

		if(isFirstPage){
			mFollowersResponseData.getUsers().clear();
		}
		final String userId = IGMKVManager.getPKID();
		GetFollowersRequest getFollowersRequest = new GetFollowersRequest(isFirstPage,userId,nextMaxId);
		getFollowersRequest.execute(new InsRequestCallBack<FollowersResponseData>() {
			@Override
			public void onSuccess(int statusCode, FollowersResponseData response) {
				Log.d(TAG, "getFollowers onSuccess " );
				mFollowersResponseData.setBig_list(response.isBig_list());
				mFollowersResponseData.setNext_max_id(response.getNext_max_id());
				mFollowersResponseData.setPage_size(mFollowersResponseData.getPage_size()+response.getPage_size());
				mFollowersResponseData.getUsers().addAll(response.getUsers());

				if(!TextUtils.isEmpty(response.getNext_max_id())){
					getFollowers22(false,mFollowersResponseData.getNext_max_id());
				}else{
					Log.d(TAG, String.format("getFollowers request end and the size = %s ",mFollowersResponseData.getUsers().size()) );
				}

			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getFollowers onFailure = " + errorMsg);
			}
		});




	}









	private void getFollowing(boolean isFirstPage) {

		String userId = IGMKVManager.getPKID();
		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_FOLLOWING, userId);



		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("rank_token", String.format("%s_%s", userId, DeviceUtils.getAndroidId(mContext)));//rank_token本地拼接的userid_deviceID （
		paramsMap.put("ranked_content", "true");

		if(isFirstPage){
			mFollowingResponseData.getUsers().clear();
		}else{
			paramsMap.put("max_id", mFollowingResponseData.getNext_max_id());
		}




		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<FollowingResponseData>() {
					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getFollowers onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, FollowingResponseData response) {
						Log.d(TAG, "getFollowers onSuccess " );
						mFollowingResponseData.setBig_list(response.isBig_list());
						mFollowingResponseData.setNext_max_id(response.getNext_max_id());
						mFollowingResponseData.setPage_size(mFollowingResponseData.getPage_size()+response.getPage_size());
						mFollowingResponseData.getUsers().addAll(response.getUsers());

						if(!TextUtils.isEmpty(response.getNext_max_id())){
							getFollowing(false);
						}else{
							Log.d(TAG, String.format("getFollowers request end and the size = %s ",mFollowingResponseData.getUsers().size()) );
						}
					}


				});




	}




	private void getFollowing222(boolean isFirstPage,String nextMaxId) {

		if(isFirstPage){
			mFollowingResponseData.getUsers().clear();
		}


		String userId = IGMKVManager.getPKID();

		GetFollowingRequest request =new GetFollowingRequest(isFirstPage,userId,nextMaxId);
		request.execute(new InsRequestCallBack<FollowingResponseData>() {
			@Override
			public void onSuccess(int statusCode, FollowingResponseData response) {
				Log.d(TAG, "getFollowers onSuccess " );
				mFollowingResponseData.setBig_list(response.isBig_list());
				mFollowingResponseData.setNext_max_id(response.getNext_max_id());
				mFollowingResponseData.setPage_size(mFollowingResponseData.getPage_size()+response.getPage_size());
				mFollowingResponseData.getUsers().addAll(response.getUsers());

				if(!TextUtils.isEmpty(response.getNext_max_id())){
					getFollowing222(false,mFollowingResponseData.getNext_max_id());
				}else{
					Log.d(TAG, String.format("getFollowers request end and the size = %s ",mFollowingResponseData.getUsers().size()) );
				}
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getFollowers onFailure = " + errorMsg);
			}
		});





	}










	private void getMediaLikers() {
		List<FeedResponseData.ItemsBean> itemsBeanList = mFeedResponseDataAll.getItems();
		int size = itemsBeanList.size();
		getLikersCount = 0;
		for (int i = 0; i < size; i++) {
			Log.d(TAG, "ItemsBean---------- " + i);
			FeedResponseData.ItemsBean itemsBean = itemsBeanList.get(i);
			//getMediaLikers(itemsBean);
			getMediaLikers222(itemsBean);
		}
	}

	private int getLikersCount = 0;
	private void getMediaLikers(final FeedResponseData.ItemsBean itemsBean) {

		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_MEDIA_LIKERS, itemsBean.getId());
		HashMap<String, String> paramsMap = new HashMap<>();


		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<MediaLikersResponseData>() {
					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getMediaLikers onFailure = " + errorMsg);

					}
					@Override
					public void onSuccess(int statusCode, MediaLikersResponseData response) {

						itemsBean.getLikers().clear();
						itemsBean.getLikers().addAll(response.getUsers());
						getLikersCount++;
						Log.d(TAG, "getMediaLikers onSuccess, "+getLikersCount );
					}


				});




	}



	private void getMediaLikers222(final FeedResponseData.ItemsBean itemsBean) {

		GetMediaLikersRequest getMediaLikersRequest = new GetMediaLikersRequest(itemsBean.getId());
		getMediaLikersRequest.execute(new InsRequestCallBack<MediaLikersResponseData>() {
			@Override
			public void onSuccess(int statusCode, MediaLikersResponseData response) {
				itemsBean.getLikers().clear();
				itemsBean.getLikers().addAll(response.getUsers());
				getLikersCount++;
				Log.d(TAG, "getMediaLikers onSuccess, "+getLikersCount );
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getMediaLikers onFailure = " + errorMsg);
			}
		});




	}




	private void getMediaLikers(String mediaId) {

		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_MEDIA_LIKERS, mediaId);
		HashMap<String, String> paramsMap = new HashMap<>();


		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<MediaLikersResponseData>() {

					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getMediaLikers onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, MediaLikersResponseData response) {
						Log.d(TAG, "getMediaLikers onSuccess " );


					}


				});


	}








	private void getMediaComments() {
		List<FeedResponseData.ItemsBean> itemsBeanList = mFeedResponseDataAll.getItems();
		int size = itemsBeanList.size();
		MediaComments = 0;
		for (int i = 0; i < size; i++) {
			Log.d(TAG, "ItemsBean---------- " + i);
			FeedResponseData.ItemsBean itemsBean = itemsBeanList.get(i);
			//getMediaComments(itemsBean);
			getMediaComments222(itemsBean);
		}
	}

	private int MediaComments = 0;
	private void getMediaComments(final FeedResponseData.ItemsBean itemsBean) {

		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_MEDIA_COMMENTS, itemsBean.getId());
		HashMap<String, String> paramsMap = new HashMap<>();


		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<MediaCommentResponseData>() {
					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getMediaComments onFailure = " + errorMsg);

					}
					@Override
					public void onSuccess(int statusCode, MediaCommentResponseData response) {

						itemsBean.getComments().clear();
						itemsBean.getComments().addAll(response.getComments());
						MediaComments++;
						Log.d(TAG, "getMediaLikers onSuccess, "+MediaComments );
					}


				});


	}



	private void getMediaComments222(final FeedResponseData.ItemsBean itemsBean) {

		GetMediaCommentsRequest commentsRequest = new GetMediaCommentsRequest(itemsBean.getId());
		commentsRequest.execute(new InsRequestCallBack<MediaCommentResponseData>() {
			@Override
			public void onSuccess(int statusCode, MediaCommentResponseData response) {

				itemsBean.getComments().clear();
				itemsBean.getComments().addAll(response.getComments());
				MediaComments++;
				Log.d(TAG, "getMediaLikers onSuccess, "+MediaComments );
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getMediaLikers onFailure = " + errorMsg);
			}
		});

	}





	private void getMediaComments(String mediaId) {

		String url = IGConfig.API_V1 + String.format(IGConfig.ACTION_GET_MEDIA_COMMENTS, mediaId);
		HashMap<String, String> paramsMap = new HashMap<>();


		OkHttpManager.getInstance()
				.get(url)
				.addParams(paramsMap)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<MediaCommentResponseData>() {

					@Override
					public void onFailure(int errorCode, String errorMsg) {
						Log.d(TAG, "getMediaLikers onFailure = " + errorMsg);

					}

					@Override
					public void onSuccess(int statusCode, MediaCommentResponseData response) {
						Log.d(TAG, "getMediaLikers onSuccess " );


					}


				});


	}



	private void getUserInfo(String userId) {
		UserInfoRequest userInfoRequest = new UserInfoRequest(userId);
		userInfoRequest.execute(new InsRequestCallBack<InsBaseResponseData>() {
			@Override
			public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
				Log.d(TAG, "getUserInfo onSuccess " );
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getUserInfo onFailure = " + errorMsg);
			}
		});
	}



	private void  following_create(String userId) {
		String csrftoken = IGMKVManager.getCsrftoken();
		String pkid = IGMKVManager.getPKID();

		FollowingCreatePayload followingCreatePayload = new FollowingCreatePayload();

		followingCreatePayload.set_csrftoken(csrftoken);
		followingCreatePayload.set_uid(pkid);
		followingCreatePayload.set_uuid(IGUtils.generateUuid(true));
		followingCreatePayload.setUser_id(userId);
		followingCreatePayload.setRadio_type("wifi-none");



		FollowingCreateRequest followingCreateRequest = new FollowingCreateRequest(userId,followingCreatePayload);
		followingCreateRequest.execute(new InsRequestCallBack<FollowingCreateResponseData>() {
			@Override
			public void onSuccess(int statusCode, FollowingCreateResponseData insBaseData) {
				if(insBaseData.getFriendship_status().isFollowing()){
					Log.d(TAG, "关注 onSuccess " );
				}else{
					Log.d(TAG, "关注 失败 " );
				}


			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getUserInfo onFailure = " + errorMsg);
			}
		});
	}



	private void  following_cancel(String userId) {

		String csrftoken = IGMKVManager.getCsrftoken();
		String pkid = IGMKVManager.getPKID();

		FollowingCancelPayload followingCreatePayload = new FollowingCancelPayload();

		followingCreatePayload.set_csrftoken(csrftoken);
		followingCreatePayload.set_uid(pkid);
		followingCreatePayload.set_uuid(IGUtils.generateUuid(true));
		followingCreatePayload.setUser_id(userId);
		followingCreatePayload.setRadio_type("wifi-none");



		FollowingCancelRequest followingCreateRequest = new FollowingCancelRequest(userId,followingCreatePayload);
		followingCreateRequest.execute(new InsRequestCallBack<FollowingCancelResponseData>() {
			@Override
			public void onSuccess(int statusCode, FollowingCancelResponseData insBaseData) {
				if(insBaseData.getFriendship_status().isFollowing()){
					Log.d(TAG, "取消关注 失败 " );
				}else{
					Log.d(TAG, "取消关注 成功" );
				}
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "getUserInfo onFailure = " + errorMsg);
			}
		});
	}



	private void  mediaLike(String mediaID) {

		String csrftoken = IGMKVManager.getCsrftoken();
		String pkid = IGMKVManager.getPKID();

		MediaLikePayload mediaLikePayload = new MediaLikePayload();

		mediaLikePayload.set_csrftoken(csrftoken);
		mediaLikePayload.set_uid(pkid);
		mediaLikePayload.set_uuid(IGUtils.generateUuid(true));
		mediaLikePayload.setRadio_type("wifi-none");
		mediaLikePayload.setMedia_id(mediaID);



		MedialLikeRequest medialLikeRequest = new MedialLikeRequest(mediaID,mediaLikePayload);
		medialLikeRequest.execute(new InsRequestCallBack<InsBaseResponseData>() {
			@Override
			public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
				Log.d(TAG, "mediaLike onSuccess = " );
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "mediaLike onFailure = " + errorMsg);
			}
		});
	}

	private void  mediaUnLike(String mediaID) {

		String csrftoken = IGMKVManager.getCsrftoken();
		String pkid = IGMKVManager.getPKID();

		MediaUnLikePayload mediaUnLikePayload = new MediaUnLikePayload();

		mediaUnLikePayload.set_csrftoken(csrftoken);
		mediaUnLikePayload.set_uid(pkid);
		mediaUnLikePayload.set_uuid(IGUtils.generateUuid(true));
		mediaUnLikePayload.setRadio_type("wifi-none");
		mediaUnLikePayload.setMedia_id(mediaID);



		MedialUnLikeRequest medialUnLikeRequest = new MedialUnLikeRequest(mediaID,mediaUnLikePayload);
		medialUnLikeRequest.execute(new InsRequestCallBack<InsBaseResponseData>() {
			@Override
			public void onSuccess(int statusCode, InsBaseResponseData insBaseData) {
				Log.d(TAG, "mediaUnLike onSuccess  " );
			}

			@Override
			public void onFailure(int errorCode, String errorMsg) {
				Log.d(TAG, "mediaUnLike onFailure = " + errorMsg);
			}
		});
	}




}
