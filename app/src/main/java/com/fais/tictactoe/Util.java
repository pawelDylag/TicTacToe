package com.fais.tictactoe;

import android.graphics.Point;

/**
 * Created by paweldylag on 24/10/15.
 */
public class Util {

    public static int convert2DIndexTo1D(int x, int y, int size) {
        return y * size + x;
    }

    public static Point convert1DIndexTo2D(int index, int size) {
        Point point = new Point();
        int x = index % size;
        int y = (int) Math.floor(((double) index / size));
        point.set(x, y);
        return point;
    }
}
