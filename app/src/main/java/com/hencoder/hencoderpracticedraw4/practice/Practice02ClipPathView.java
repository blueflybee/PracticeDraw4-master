package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice02ClipPathView extends View {
  Paint paint = new Paint();
  Bitmap bitmap;
  Point point1 = new Point(200, 200);
  Point point2 = new Point(600, 200);

  public Practice02ClipPathView(Context context) {
    super(context);
  }

  public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice02ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
  }

//canvas.clipPath(path, Region.Op.DIFFERENCE);
//  我们把之前的区域设为A，裁剪的区域为B，则以上枚举值的意义如下：
//  Region.Op DIFFERENCE
//  显示A-B
//  Region.Op INTERSECT
//  显示 A与B的交集
//  Region.Op REPLACE
//  只显示B
//  Region.Op REVERSE_DIFFERENCE
//  显示B-A
//  Region.Op UNION
//  显示A与B的并集
//  Region.Op XOR
//  显示 A与B的并集-A与B的交集

//  结论：
//
//  XOR:异或运算，相同为0(false)，不用为1(true),也就是两个区域重叠的地方为0，不同的地方为1，剪切过后的画布的区域就是两个区域不重叠的部分。
//
//  INTERSECT：交集运算，剪切过后的画布区域就是两个区域的交集区域。
//
//  DIFFERENCE:差集运算，剪切过后的画布的区域就是A-B
//
//  REVERSE_DIFFERENCE:差集运算，REVERSE为反向，逆向的意思，不难猜出剪切过后的画布区域是B-A的区域
//
//  UNION:并集运算，剪切过后的画布的区域为A+B的区域
//  REPLACE:也就是剪切过后的结果只保留B区域，这也就是REPLACE代替或者替换的含义：当前还没被剪切的区域A被即将要剪切的区域B来代替。

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

//    canvas.drawColor(Color.RED);

    int right1 = point1.x + bitmap.getWidth();
    int bottom1 = point1.y + bitmap.getHeight();
    Path path1 = new Path();
    path1.addCircle(right1 - 100, bottom1 - 100, 150, Path.Direction.CW);

    int right2 = point2.x + bitmap.getWidth();
    int bottom2 = point2.y + bitmap.getHeight();
    Path path2 = new Path();
    path2.addCircle(right2 - 100, bottom2 - 100, 150, Path.Direction.CW);

    canvas.save();
    canvas.clipPath(path1, Region.Op.INTERSECT);
    canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
    canvas.restore();

    canvas.save();
    canvas.clipPath(path2, Region.Op.DIFFERENCE);
    canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
    canvas.restore();


  }
}
