package com.joy.libbase.base.util.ui.drawable;

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

import com.joy.libbase.base.util.common.screem.ScreenUtils;


/**
 * Created by joybar on 1/11/16.
 */

public class DrawbleUtils {
	// GradientDrawable
	public static GradientDrawable setGradientDrawable(Context context, String color, int rLeftTop, int rRightTop, int rRightBottom,
													   int rLeftBottom) {
		// 但是如果想设置Gradient的渐变色该咋办呢？
		// 方法是改变GradientDrawable的创建方法：
		// int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd
		// };//分别为开始颜色，中间夜色，结束颜色
		// GradientDrawable gd = new
		// GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);

		GradientDrawable gd = new GradientDrawable();// 创建drawable
		//gd.setStroke(20, (Color.parseColor("#CCCCCC")));
		gd.setColor((Color.parseColor(color)));// 设置颜色
		gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
		// gd.setShape(GradientDrawable.RECTANGLE);// 矩形
		gd.setShape(GradientDrawable.RECTANGLE);// 矩形
		float[] r = {ScreenUtils.convertDipToPx(context, rLeftTop), ScreenUtils.convertDipToPx(context, rLeftTop),
				ScreenUtils.convertDipToPx(context, rRightTop), ScreenUtils.convertDipToPx(context, rRightTop), ScreenUtils.convertDipToPx(context,
				rRightBottom), ScreenUtils.convertDipToPx(context, rRightBottom), ScreenUtils.convertDipToPx(context, rLeftBottom),
				ScreenUtils.convertDipToPx(context, rLeftBottom)};
		gd.setCornerRadii(r);
		//  gd.setCornerRadius();
		return gd;
	}

	public static StateListDrawable setStateDrawable(Context context, String colorNormal, String colorPressed, String colorFocused, int r) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = colorNormal == null ? null : setGradientDrawable(context, colorNormal, r, r, r, r);
		Drawable pressed = colorPressed == null ? null : setGradientDrawable(context, colorPressed, r, r, r, r);
		Drawable focus = colorFocused == null ? null : setGradientDrawable(context, colorFocused, r, r, r, r);
		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		// 所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
		sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
		sd.addState(new int[]{android.R.attr.state_focused}, focus);
		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
		sd.addState(new int[]{android.R.attr.state_enabled}, normal);
		sd.addState(new int[]{}, normal);
		return sd;

	}

	public static StateListDrawable setStateDrawable(Context context, int idNormal, int idPressed) {
		StateListDrawable sd = new StateListDrawable();
		Drawable normal = idNormal == 0 ? null : context.getResources().getDrawable(idNormal);
		Drawable pressed = idPressed == 0 ? null : context.getResources().getDrawable(idPressed);
		// 注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
		// 所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
		sd.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
		sd.addState(new int[]{android.R.attr.state_pressed}, pressed);
		sd.addState(new int[]{android.R.attr.state_enabled}, normal);
		sd.addState(new int[]{}, normal);
		return sd;

	}


	public static StateListDrawable setStateDrawableRoundedCorner(Context context, int idNormal, int idPressed, int r) {
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


	/**
	 * 画一个圆角图
	 *
	 * @param idDrawable
	 * @param roundPx
	 * @return
	 */
	public static BitmapDrawable getRoundedCornerBitmapDrawable(Context context, int idDrawable, int roundPx) {

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
		canvas.drawRoundRect(rectF, ScreenUtils.convertDipToPx(context, roundPx), ScreenUtils.convertDipToPx(context, roundPx), paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return new BitmapDrawable(output);
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

}
