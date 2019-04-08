package com.joybar.androidlibutils.ins;

import android.util.Log;

import java.util.HashMap;
import java.util.Random;

public class IGConfig {

	//public static final String API_V1 = "https://i.instagram.com/api/v1/";
	public static final String API_V1 = "https://i.instagram.com/api/v1/";
	public static final String API_V2 = "https://i.instagram.com/api/v2/";


	//public static final String ACTION_GET_HEADER = "si/fetch_headers/?challenge_type=signup&guid=" + IGUtils.generateUuid(false);

	public static final String ACTION_GET_HEADER = "si/fetch_headers/?challenge_type=signup&guid=%s"; //获取token
	public static final String ACTION_LOGIN = "accounts/login/"; //登录
	public static final String ACTION_GET_FEED = "feed/user/%s/";//获取post
	public static final String ACTION_GET_FEED_LIKED = "feed/liked/";//获取喜欢的post
	public static final String ACTION_GET_FOLLOWERS = "friendships/%s/followers/";//获取followers
	public static final String ACTION_GET_FOLLOWING = "friendships/%s/following/";//获取following
	public static final String ACTION_GET_FOLLOWING_CANCEL = "friendships/destroy/%s/";//关注某人
	public static final String ACTION_GET_FOLLOWING_CREATE = "friendships/create/%s/";//取关某人
	public static final String ACTION_GET_MEDIA_LIKERS = "media/%s/likers/";//获取某条post的followers
	public static final String ACTION_GET_MEDIA_COMMENTS = "media/%s/comments/";//获取某条post的comment
	public static final String ACTION_GET_MEDIA_LIKE = "media/%s/like/";//like某条post
	public static final String ACTION_GET_MEDIA_UNLIKE = "media/%s/unlike/";//unlike某条post
	public static final String ACTION_GET_USER_INFO = "users/%s/info/";//根据userId获取用户信息

	//======constant====


	public static final String CSRFTOKEN = "csrftoken";


	public static final String API_KEY_VERSION = "4";

	public static final String DEVICE_MANUFACTURER = "samsung";

	public static final String DEVICE_MODEL = "SM-G935F";

	public static final String DEVICE_ANDROID_VERSION = "23";

	public static final String DEVICE_ANDROID_RELEASE = "6.0.1";

	public static final String DEVICE_EXPERIMENTS = "ig_android_progressive_jpeg,ig_creation_growth_holdout,ig_android_report_and_hide," +
			"ig_android_new_browser,ig_android_enable_share_to_whatsapp,ig_android_direct_drawing_in_quick_cam_universe," +
			"ig_android_huawei_app_badging,ig_android_universe_video_production,ig_android_asus_app_badging,ig_android_direct_plus_button," +
			"ig_android_ads_heatmap_overlay_universe,ig_android_http_stack_experiment_2016,ig_android_infinite_scrolling,ig_fbns_blocked," +
			"ig_android_white_out_universe,ig_android_full_people_card_in_user_list,ig_android_post_auto_retry_v7_21,ig_fbns_push," +
			"ig_android_feed_pill,ig_android_profile_link_iab,ig_explore_v3_us_holdout,ig_android_histogram_reporter,ig_android_anrwatchdog," +
			"ig_android_search_client_matching,ig_android_high_res_upload_2,ig_android_new_browser_pre_kitkat,ig_android_2fac," +
			"ig_android_grid_video_icon,ig_android_white_camera_universe,ig_android_disable_chroma_subsampling,ig_android_share_spinner," +
			"ig_android_explore_people_feed_icon,ig_explore_v3_android_universe,ig_android_media_favorites,ig_android_nux_holdout," +
			"ig_android_search_null_state,ig_android_react_native_notification_setting,ig_android_ads_indicator_change_universe," +
			"ig_android_video_loading_behavior,ig_android_black_camera_tab,liger_instagram_android_univ,ig_explore_v3_internal," +
			"ig_android_direct_emoji_picker,ig_android_prefetch_explore_delay_time,ig_android_business_insights_qe,ig_android_direct_media_size," +
			"ig_android_enable_client_share,ig_android_promoted_posts,ig_android_app_badging_holdout,ig_android_ads_cta_universe," +
			"ig_android_mini_inbox_2,ig_android_feed_reshare_button_nux,ig_android_boomerang_feed_attribution,ig_android_fbinvite_qe,ig_fbns_shared,"
			+ "ig_android_direct_full_width_media,ig_android_hscroll_profile_chaining,ig_android_feed_unit_footer,ig_android_media_tighten_space," + "ig_android_private_follow_request,ig_android_inline_gallery_backoff_hours_universe,ig_android_direct_thread_ui_rewrite," + "ig_android_rendering_controls,ig_android_ads_full_width_cta_universe,ig_video_max_duration_qe_preuniverse," + "ig_android_prefetch_explore_expire_time,ig_timestamp_public_test,ig_android_profile,ig_android_dv2_consistent_http_realtime_response," + "ig_android_enable_share_to_messenger,ig_explore_v3,ig_ranking_following,ig_android_pending_request_search_bar," + "ig_android_feed_ufi_redesign,ig_android_video_pause_logging_fix,ig_android_default_folder_to_camera,ig_android_video_stitching_7_23," + "ig_android_profanity_filter,ig_android_business_profile_qe,ig_android_search,ig_android_boomerang_entry," + "ig_android_inline_gallery_universe,ig_android_ads_overlay_design_universe,ig_android_options_app_invite," + "ig_android_view_count_decouple_likes_universe,ig_android_periodic_analytics_upload_v2,ig_android_feed_unit_hscroll_auto_advance," + "ig_peek_profile_photo_universe,ig_android_ads_holdout_universe,ig_android_prefetch_explore,ig_android_direct_bubble_icon," + "ig_video_use_sve_universe,ig_android_inline_gallery_no_backoff_on_launch_universe,ig_android_image_cache_multi_queue," + "ig_android_camera_nux,ig_android_immersive_viewer,ig_android_dense_feed_unit_cards,ig_android_sqlite_dev,ig_android_exoplayer," + "ig_android_add_to_last_post,ig_android_direct_public_threads,ig_android_prefetch_venue_in_composer,ig_android_bigger_share_button," + "ig_android_dv2_realtime_private_share,ig_android_non_square_first,ig_android_video_interleaved_v2,ig_android_follow_search_bar," + "ig_android_last_edits,ig_android_video_download_logging,ig_android_ads_loop_count_universe,ig_android_swipeable_filters_blacklist," + "ig_android_boomerang_layout_white_out_universe,ig_android_ads_carousel_multi_row_universe,ig_android_mentions_invite_v2," + "ig_android_direct_mention_qe,ig_android_following_follower_social_context";


	public static final String USER_AGENT = String.format("Instagram 10.26.0 Android (%s/%s; 640dpi; 1440x2560; %s; %s; hero2lte; samsungexynos8890;"
			+ " en_US)", DEVICE_ANDROID_VERSION, DEVICE_ANDROID_RELEASE, DEVICE_MANUFACTURER, DEVICE_MODEL);


	static String agent1 = "Mozilla/5.0 (Linux; Android 7.1.1; SM-J250F Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 " +
			"Chrome/64.0.3282.137 Mobile Safari/537.36";

	public static HashMap<String, String> getHeaders() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Connection", "close");
		map.put("Accept", "*/*");
		map.put("Cookie2", "$Version=1");
		map.put("Accept-Language", "en-US");
		map.put("X-IG-Capabilities", "3boBAA==");
		map.put("X-IG-Connection-Type", "WIFI");
		map.put("X-IG-Connection-Speed", "-1kbps");
		map.put("X-IG-App-ID", "567067343352427");
		map.put("User-Agent", USER_AGENT);
		return map;

	}


	public static HashMap<String, String> getHeaders1() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Connection", "close");
		map.put("Accept", "*/*");
		map.put("Cookie2", "$Version=1");
		map.put("Accept-Language", "en-US");
		map.put("X-IG-Capabilities", "3boBAA==");
		map.put("X-IG-Connection-Type", "WIFI");
		map.put("X-IG-Connection-Speed", "-1kbps");
		map.put("X-IG-App-ID", "567067343352427");
		map.put("User-Agent", agent1);
		return map;

	}

	public static HashMap<String, String> getHeaders2() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("accept-encoding", "gzip, deflate, br");
		map.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		map.put("cache-control", "no-cache");
		map.put("cookie", "mcd=3; mid=XDVhWAAEAAFDC6x7O-FAhguDRZtR; fbm_124024574287414=base_domain=.instagram.com; rur=FTW; " + "csrftoken" +
				"=pDMWVyBnpUtQrruvdkCuwTOmfeLTLY6J; shbid=1379; ds_user_id=322961280; sessionid=322961280%3AL0XJIGZWDBAKAg%3A3; " + "shbts" +
				"=1554109881.3543475; urlgen=\"{\\\"45.62.126.61\\\": 25820}:1hAuBU:00cQVjRz70O2OmxMrK9YpDTnmNI\"");
		map.put("pragma", "no-cache");
		map.put("upgrade-insecure-requests", "1");
		map.put("user-agent", agent1);
		return map;

	}


	public static HashMap<String, String> getHeadersPHp3() {
		HashMap<String, String> headsMap = new HashMap<String, String>();
		//headsMap.put("Connection","close");
		headsMap.put("Connection", "keep-alive");
		headsMap.put("Content-Type", "application/json");
		headsMap.put("X-IG-Connection-Type", "mobile(UMTS)");
		headsMap.put("X-IG-Capabilities", "3ToAAA==");
		headsMap.put("Accept-Language", "en-US");
		//headsMap.put("csrf_token", "d9mtZESkv3CsPAGsAJZQyCLEKZukzNqH");
		headsMap.put("User-Agent", "Instagram 10.3.0 Android (18/4.3; 320dpi; 720x1280; Xiaomi; HM 1SW; armani; qcom; en_US)");
		return headsMap;

	}

	public static HashMap<String, String> getHeadersPHp4() {
		HashMap<String, String> headsMap = new HashMap<String, String>();


		headsMap.put("Connection", "keep-alive");
		headsMap.put("Content-Type", "application/json");
		headsMap.put("X-FB-HTTP-Engine", "Liger");
		headsMap.put("Accept", "*/*");
		headsMap.put("Accept-Encoding", "gzip,deflate");
		headsMap.put("Accept-Language", "en-US");
		headsMap.put("User-Agent", getUserAgent());

		Log.d("AAAA", getUserAgent());


		headsMap.put("X-IG-App-ID", "567067343352427");
		headsMap.put("X-IG-Capabilities", "3brTvw==");
		headsMap.put("X-IG-Connection-Type", "WIFI");
		headsMap.put("X-IG-Connection-Speed", "2000.kbps");
		headsMap.put("X-IG-Bandwidth-Speed-KBPS", "-1.000");
		headsMap.put("X-IG-Bandwidth-TotalBytes-B", "0");
		headsMap.put("X-IG-Bandwidth-TotalTime-MS", "0");


		return headsMap;

	}


	//public static String IG_SIG_KEY = "19ce5f445dbfd9d29c59dc2a78c616a7fc090a8e018b9267bc4240a30244c53b";
	public static String SIG_KEY_VERSION = "4";

	public static String IG_SIG_KEY = "4f8732eb9ba7d1c8e8897a75d6474d4eb3f5279137431b2aafb71fafe2abe178";


	public static String USER_AGENT_FORMAT = "Instagram %s Android (%s/%s; %s; %s; %s; %s; %s; %s; %s; %s)";

	public static String IG_VERSION = "76.0.0.15.395";
	public static String VERSION_CODE = "138226743";
	public static String USER_AGENT_LOCALE = "en_US"; // "language_COUNTRY".

	public static String getUserAgent() {
		int length = RANDOM_DEVICES.length;
		int index = new Random().nextInt(length);
		String userAgentStr = RANDOM_DEVICES[index];
		userAgentStr = userAgentStr.replace("/", ";");
		String[] deviceInfos = userAgentStr.split(";");
		return String.format(USER_AGENT_FORMAT, IG_VERSION, deviceInfos[0], deviceInfos[1], deviceInfos[2], deviceInfos[3], deviceInfos[4],
				deviceInfos[5], deviceInfos[6], deviceInfos[7], USER_AGENT_LOCALE, VERSION_CODE);
	}

	public static String[] RANDOM_DEVICES = {

			/* OnePlus 3T. Released: November 2016.
			* https://www.amazon.com/OnePlus-A3010-64GB-Gunmetal-International/dp/B01N4H00V8
			* https://www.handsetdetection.com/properties/devices/OnePlus/A3010
			*/
			"24/7.0; 380dpi; 1080x1920; OnePlus; ONEPLUS A3010; OnePlus3T; qcom",

			/* LG G5. Released: April 2016.
			 * https://www.amazon.com/LG-Unlocked-Phone-Titan-Warranty/dp/B01DJE22C2
			 * https://www.handsetdetection.com/properties/devices/LG/RS988
			 */
			"23/6.0.1; 640dpi; 1440x2392; LGE/lge; RS988; h1; h1",

			/* Huawei Mate 9 Pro. Released: January 2017.
			 * https://www.amazon.com/Huawei-Dual-Sim-Titanium-Unlocked-International/dp/B01N9O1L6N
			 * https://www.handsetdetection.com/properties/devices/Huawei/LON-L29
			 */
			"24/7.0; 640dpi; 1440x2560; HUAWEI; LON-L29; HWLON; hi3660",

			/* ZTE Axon 7. Released: June 2016.
			 * https://www.frequencycheck.com/models/OMYDK/zte-axon-7-a2017u-dual-sim-lte-a-64gb
			 * https://www.handsetdetection.com/properties/devices/ZTE/A2017U
			 */
			"23/6.0.1; 640dpi; 1440x2560; ZTE; ZTE A2017U; ailsa_ii; qcom",

			/* Samsung Galaxy S7 Edge SM-G935F. Released: March 2016.
			 * https://www.amazon.com/Samsung-SM-G935F-Factory-Unlocked-Smartphone/dp/B01C5OIINO
			 * https://www.handsetdetection.com/properties/devices/Samsung/SM-G935F
			 */
			"23/6.0.1; 640dpi; 1440x2560; samsung; SM-G935F; hero2lte; samsungexynos8890",

			/* Samsung Galaxy S7 SM-G930F. Released: March 2016.
			 * https://www.amazon.com/Samsung-SM-G930F-Factory-Unlocked-Smartphone/dp/B01J6MS6BC
			 * https://www.handsetdetection.com/properties/devices/Samsung/SM-G930F
			 */
			"23/6.0.1; 640dpi; 1440x2560; samsung; SM-G930F; herolte; samsungexynos8890" };

}
