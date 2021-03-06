package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Point point1 = new Point(200, 200);
  Point point2 = new Point(600, 200);

  private int mDegree = 0;

  public Practice12CameraRotateFixedView(Context context) {
    super(context);
  }

  public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

//    canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
//    canvas.drawBitmap(bitmap, point2.x, point2.y, paint);

    int trans1X = point1.x + bitmap.getWidth() / 2;
    int trans1Y = point1.y + bitmap.getHeight() / 2;

    Camera camera = new Camera();
    canvas.save();

    canvas.translate(trans1X, trans1Y);
    camera.save();
    camera.rotateX(mDegree);
    camera.applyToCanvas(canvas);
    camera.restore();
    canvas.translate(-trans1X, -trans1Y);

    canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
    canvas.restore();

    int trans2X = point2.x + bitmap.getWidth() / 2;
    int trans2Y = point2.y + bitmap.getHeight() / 2;

    canvas.save();

    canvas.translate(trans2X, trans2Y);
    camera.save();
    camera.rotateY(mDegree);
    camera.applyToCanvas(canvas);
    camera.restore();
    canvas.translate(-trans2X, -trans2Y);

    canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
    canvas.restore();

    mDegree ++;
    if (mDegree >= 360) {
      mDegree = 0;
    }

    postInvalidateDelayed(20);
  }
}
