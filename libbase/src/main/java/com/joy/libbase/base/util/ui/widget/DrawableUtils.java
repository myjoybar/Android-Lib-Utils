package com.joy.libbase.base.util.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;


/**
 * Created by joybar on 1/11/16.
 */

public class DrawableUtils {


	/**
	 * 创建普通Shape，无边框
	 *
	 * @param context
	 * @param shape
	 * @param fillColor
	 * @param roundRadius
	 * @return
	 */
	public static GradientDrawable createShape(Context context, int shape, String fillColor, float[] roundRadius) {

		GradientDrawable gd = new GradientDrawable();
		if (!TextUtils.isEmpty(fillColor)) {
			gd.setColor(Color.parseColor(fillColor));
		}
		gd.setCornerRadii(convertRadiusToPx(context, roundRadius));
		gd.setShape(shape);
		return gd;
	}

	/**
	 * 创建普通Shape有边框
	 *
	 * @param context
	 * @param shape       GradientDrawable.RECTANGLE
	 * @param fillColor
	 * @param strokeWidth
	 * @param strokeColor
	 * @param roundRadius
	 * @return
	 */
	public static GradientDrawable createShape(Context context, int shape, String fillColor, int strokeWidth, String strokeColor,
											   float[] roundRadius) {

		GradientDrawable gd = new GradientDrawable();
		if (!TextUtils.isEmpty(fillColor)) {
			gd.setColor(Color.parseColor(fillColor));
		}
		if (!TextUtils.isEmpty(strokeColor)) {
			gd.setStroke((int) convertDipToPx(context, strokeWidth), Color.parseColor(strokeColor));
		}
		gd.setCornerRadii(convertRadiusToPx(context, roundRadius));
		gd.setShape(shape);
		return gd;
	}

	/**
	 * 创建渐变Shape, 无边框
	 *
	 * @param context
	 * @param shape
	 * @param gradientColors
	 * @param gradientType
	 * @param orientation    GradientDrawable.Orientation.TOP_BOTTOM
	 * @param roundRadius
	 * @return
	 */
	public static GradientDrawable createGradientShape(Context context, int shape, int[] gradientColors, int gradientType,
													   GradientDrawable.Orientation orientation, float[] roundRadius) {

		GradientDrawable gd = new GradientDrawable(orientation, gradientColors);
		gd.setGradientType(gradientType);//GradientDrawable.LINEAR_GRADIENT
		gd.setCornerRadii(convertRadiusToPx(context, roundRadius));
		gd.setShape(shape);// GradientDrawable.RECTANGLE
		return gd;
	}


	/**
	 * 创建渐变Shape,有边框
	 *
	 * @param context
	 * @param shape          GradientDrawable.RECTANGLE
	 * @param gradientColors
	 * @param gradientType
	 * @param orientation    GradientDrawable.Orientation.TOP_BOTTOM
	 * @param strokeWidth
	 * @param strokeColor
	 * @param roundRadius
	 * @return
	 */
	public static GradientDrawable createGradientShape(Context context, int shape, int[] gradientColors, int gradientType,
													   GradientDrawable.Orientation orientation, int strokeWidth, String strokeColor,
													   float[] roundRadius) {

		GradientDrawable gd = new GradientDrawable(orientation, gradientColors);
		gd.setGradientType(gradientType);//GradientDrawable.LINEAR_GRADIENT
		if (!TextUtils.isEmpty(strokeColor)) {
			gd.setStroke((int) convertDipToPx(context, strokeWidth), Color.parseColor(strokeColor));
		}
		gd.setCornerRadii(convertRadiusToPx(context, roundRadius));
		gd.setShape(shape);
		return gd;
	}


	/**
	 * 创建GradientDrawable的基本方法
	 *
	 * @param context
	 * @param fillColor
	 * @param gradientColors int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };
	 * @param gradientType   GradientDrawable.LINEAR_GRADIENT
	 * @param orientation    GradientDrawable.Orientation.TOP_BOTTOM
	 * @param Shape          GradientDrawable.RECTANGLE
	 * @param strokeWidth
	 * @param strokeColor
	 * @param roundRadius
	 * @return
	 */
	private static GradientDrawable createGradientDrawable(Context context, int Shape, String fillColor, int gradientColors[], int gradientType,
														   GradientDrawable.Orientation orientation, float strokeWidth, String strokeColor,
														   float[] roundRadius
														  ) {

		GradientDrawable gd = null;
		if (null == gradientColors) {
			gd = new GradientDrawable();// 创建drawable
		} else {
			//int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };//分别为开始颜色，中间夜色，结束颜色
			// orientation 起始位置
			gd = new GradientDrawable(orientation, gradientColors);
			gd.setGradientType(gradientType);//GradientDrawable.LINEAR_GRADIENT
		}
		if (!TextUtils.isEmpty(strokeColor)) {
			gd.setStroke((int) convertDipToPx(context, strokeWidth), Color.parseColor(strokeColor));
		}
		if (!TextUtils.isEmpty(fillColor)) {
			gd.setColor(Color.parseColor(fillColor));// 设置颜色
		}
		gd.setShape(Shape);// GradientDrawable.RECTANGLE
//		float rLeftTopPx = convertDipToPx(context, rLeftTop);
//		float rRightTopPx = convertDipToPx(context, rRightTop);
//		float rRightBottomPx = convertDipToPx(context, rRightBottom);
//		float rLeftBottomPx = convertDipToPx(context, rLeftBottom);
//		float[] r = {rLeftTopPx, rLeftTopPx, rRightTopPx, rRightTopPx, rRightBottomPx, rRightBottomPx, rLeftBottomPx, rLeftBottomPx};
		gd.setCornerRadii(convertRadiusToPx(context, roundRadius));
		//gd.setCornerRadius(rLeftTopPx);
		return gd;
	}


	/**
	 * 根据颜色创建selector Drawable,无渐变，无边框
	 *
	 * @param context
	 * @param colorNormal
	 * @param colorPressed
	 * @param radius
	 * @return
	 */
	public static StateListDrawable createSelectorDrawable(Context context, int Shape, String colorNormal, String colorPressed, float[] radius) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = colorNormal == null ? null : createGradientDrawable(context, Shape, colorNormal, null, 0, null, 0, null, radius);
		Drawable pressed = colorPressed == null ? null : createGradientDrawable(context, Shape, colorPressed, null, 0, null, 0, null, radius);

//		sd.addState(new int[] { android.R.attr.state_enabled,
//				android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed,
//				android.R.attr.state_enabled }, pressed);
//		sd.addState(new int[] { android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
//		sd.addState(new int[] { android.R.attr.state_enabled }, normal);
//		sd.addState(new int[] {}, normal);

		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);// 按下状态的背景
		sd.addState(new int[]{}, normal); //常规状态的背景
		return sd;
	}

	/**
	 * 根据颜色创建selector Drawable,有渐变，无边框
	 *
	 * @param context
	 * @param Shape
	 * @param gradientColorsNormal
	 * @param gradientColorsPressed
	 * @param gradientType
	 * @param orientation
	 * @return
	 */
	public static StateListDrawable createSelectorDrawable(Context context, int Shape, int gradientColorsNormal[], int gradientColorsPressed[],
														   int gradientType, GradientDrawable.Orientation orientation, float[] radius) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = createGradientDrawable(context, GradientDrawable.RECTANGLE, "", gradientColorsNormal, gradientType, orientation, 0, "",
				radius);
		Drawable pressed = createGradientDrawable(context, GradientDrawable.RECTANGLE, "", gradientColorsPressed, gradientType, orientation, 0, "",
				radius);

//		sd.addState(new int[] { android.R.attr.state_enabled,
//				android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed,
//				android.R.attr.state_enabled }, pressed);
//		sd.addState(new int[] { android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
//		sd.addState(new int[] { android.R.attr.state_enabled }, normal);
//		sd.addState(new int[] {}, normal);

		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);// 按下状态的背景
		sd.addState(new int[]{}, normal); //常规状态的背景
		return sd;
	}

	/**
	 * 根据颜色创建selector Drawable,无渐变，有边框
	 *
	 * @param context
	 * @param Shape
	 * @param colorNormal
	 * @param colorPressed
	 * @param strokeWidth
	 * @param strokeColor
	 * @param radius
	 * @return
	 */
	public static StateListDrawable createSelectorDrawable(Context context, int Shape, String colorNormal, String colorPressed, float strokeWidth,
														   String strokeColor, float[] radius) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = createGradientDrawable(context, Shape, colorNormal, null, 0, null, strokeWidth, strokeColor, radius);
		Drawable pressed = createGradientDrawable(context, Shape, colorPressed, null, 0, null, strokeWidth, strokeColor, radius);

//		sd.addState(new int[] { android.R.attr.state_enabled,
//				android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed,
//				android.R.attr.state_enabled }, pressed);
//		sd.addState(new int[] { android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
//		sd.addState(new int[] { android.R.attr.state_enabled }, normal);
//		sd.addState(new int[] {}, normal);

		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);// 按下状态的背景
		sd.addState(new int[]{}, normal); //常规状态的背景
		return sd;
	}


	/**
	 * 根据颜色创建selector Drawable,有渐变，有边框
	 *
	 * @param context
	 * @param Shape
	 * @param gradientColorsNormal
	 * @param gradientColorsPressed
	 * @param gradientType
	 * @param orientation
	 * @param strokeWidth
	 * @param strokeColor
	 * @param radius
	 * @return
	 */
	public static StateListDrawable createSelectorDrawable(Context context, int Shape, int gradientColorsNormal[], int gradientColorsPressed[],
														   int gradientType, GradientDrawable.Orientation orientation, int strokeWidth,
														   String strokeColor, float[] radius) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = createGradientDrawable(context, Shape, "", gradientColorsNormal, gradientType, orientation, strokeWidth, strokeColor,
				radius);
		Drawable pressed = createGradientDrawable(context, Shape, "", gradientColorsPressed, gradientType, orientation, strokeWidth, strokeColor,
				radius);

//		sd.addState(new int[] { android.R.attr.state_enabled,
//				android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed,
//				android.R.attr.state_enabled }, pressed);
//		sd.addState(new int[] { android.R.attr.state_focused }, pressed);
//		sd.addState(new int[] { android.R.attr.state_pressed }, pressed);
//		sd.addState(new int[] { android.R.attr.state_enabled }, normal);
//		sd.addState(new int[] {}, normal);

		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);// 按下状态的背景
		sd.addState(new int[]{}, normal); //常规状态的背景
		return sd;
	}


	/**
	 * @param context
	 * @param idNormal
	 * @param idPressed
	 * @param r
	 * @return
	 */
	public static StateListDrawable createStateRoundedCornerDrawable(Context context, int idNormal, int idPressed, float r) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = idNormal == 0 ? null : getRoundedCornerBitmapDrawable(context, idNormal, r);
		Drawable pressed = idPressed == 0 ? null : getRoundedCornerBitmapDrawable(context, idPressed, r);
		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		// 所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
		sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
		sd.addState(new int[]{android.R.attr.state_enabled}, normal);
		sd.addState(new int[]{}, normal);
		return sd;

	}

	private StateListDrawable addStateDrawable(Context context, int idNormal, int idPressed, int idFocused) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
		Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
		Drawable focus = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
		//注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		//所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
		sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
		sd.addState(new int[]{android.R.attr.state_focused}, focus);
		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
		sd.addState(new int[]{android.R.attr.state_enabled}, normal);
		sd.addState(new int[]{}, normal);
		return sd;
	}


	/**
	 * 画一个圆角图
	 *
	 * @param idDrawable
	 * @param roundPx
	 * @return
	 */
	public static BitmapDrawable getRoundedCornerBitmapDrawable(Context context, int idDrawable, float roundPx) {

		Drawable drawable = context.getResources().getDrawable(idDrawable);
		BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
		Bitmap bitmap = bitmapDrawable.getBitmap();
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, convertDipToPx(context, roundPx), convertDipToPx(context, roundPx), paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return new BitmapDrawable(output);
	}


	// 创建Bitmap
	public static Bitmap createBitmap(int width, int height) {
		int STRIDE = width + 14;// must be >=WIDTH
		int[] colors = new int[STRIDE * height];
		for (int y = 0; y < height; y++) {// use of x,y is legible then the use
			// of i,j
			for (int x = 0; x < width; x++) {
				int r = x * 255 / (width - 1);
				int g = y * 255 / (height - 1);
				int b = 255 - Math.min(r, g);
				int a = Math.max(r, g);
				colors[y * STRIDE + x] = (a << 24) | (r << 16) | (g << 8) | (b);// the
				// shift
				// operation
				// generates
				// the
				// color
				// ARGB
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(colors, 0, STRIDE, width, height, Bitmap.Config.ARGB_8888);
		return bitmap;

	}

	// // 创建全白bitmap
	// public static Bitmap createWhiteBitmap(int pich, int picw) {
	// int[] pix = new int[picw * picw];
	//
	// for (int y = 0; y < pich; y++)
	// for (int x = 0; x < picw; x++) {
	// int index = y * picw + x;
	// int r = ((pix[index] >> 16) & 0xff) | 0xff;
	// int g = ((pix[index] >> 8) & 0xff) | 0xff;
	// int b = (pix[index] & 0xff) | 0xff;
	// pix[index] = 0xff000000 | (r << 16) | (g << 8) | b;
	//
	// }
	// bm1.setPixels(pix, 0, picw, 0, 0, picw, pich);
	// BitmapDrawable bmp11 = new BitmapDrawable(bm1);
	// }

	// 改变bitmap的颜色
	public static final Bitmap createRGBImage(Bitmap bitmap, int color) {
		int bitmap_w = bitmap.getWidth();
		int bitmap_h = bitmap.getHeight();
		int[] arrayColor = new int[bitmap_w * bitmap_h];
		int count = 0;
		for (int i = 0; i < bitmap_h; i++) {
			for (int j = 0; j < bitmap_w; j++) {
				int color1 = bitmap.getPixel(j, i);
				// 这里也可以取出 R G B 可以扩展一下 做更多的处理，
				// 暂时我只是要处理除了透明的颜色，改变其他的颜色
				if (color1 != 0) {
				} else {
					color1 = color;
				}
				arrayColor[count] = color;
				count++;
			}
		}
		bitmap = Bitmap.createBitmap(arrayColor, bitmap_w, bitmap_h, Bitmap.Config.ARGB_4444);
		return bitmap;
	}

	/**
	 * 画一个圆角图
	 *
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}


	// 生成圆角图片
	public static Bitmap GetRoundedCornerBitmap(Bitmap bitmap) {
		try {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
			final float roundPx = 14;
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(Color.BLACK);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
			final Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			canvas.drawBitmap(bitmap, src, rect, paint);
			return output;
		} catch (Exception e) {
			return bitmap;
		}
	}

	// 1、Drawable → Bitmap
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public static float convertDipToPx(Context context, float dip) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
	}

	private static float[] convertRadiusToPx(Context context, float[] radius) {
		radius[0] = convertDipToPx(context, radius[0]);
		radius[1] = convertDipToPx(context, radius[1]);
		radius[2] = convertDipToPx(context, radius[2]);
		radius[3] = convertDipToPx(context, radius[3]);
		return radius;
	}

}
