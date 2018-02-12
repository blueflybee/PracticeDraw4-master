package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice10MatrixSkewView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;
  Point point1 = new Point(200, 200);
  Point point2 = new Point(600, 200);

  public Practice10MatrixSkewView(Context context) {
    super(context);
  }

  public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    Matrix matrix = new Matrix();

    int trans1X = point1.x + bitmap.getWidth() / 2;
    int trans1Y = point1.y + bitmap.getHeight() / 2;
    matrix.setSkew(0, 0.5f);
    matrix.preTranslate(-trans1X, -trans1Y);
    matrix.postTranslate(trans1X, trans1Y);

    canvas.save();
    canvas.concat(matrix);
    canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
    canvas.restore();

    int trans2X = point2.x + bitmap.getWidth() / 2;
    int trans2Y = point2.y + bitmap.getHeight() / 2;
    matrix.reset();
    matrix.setSkew(-0.5f, 0);
    matrix.preTranslate(-trans2X, -trans2Y);
    matrix.postTranslate(trans2X, trans2Y);

    canvas.save();
    canvas.concat(matrix);
    canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
    canvas.restore();


  }
}
