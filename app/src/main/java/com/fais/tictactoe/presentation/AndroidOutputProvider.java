package com.fais.tictactoe.presentation;

import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.fais.tictactoe.R;
import com.fais.tictactoe.interfaces.OutputProvider;

/**
 * This class manages output for android
 * Created by paweldylag on 21/10/15.
 */
public class AndroidOutputProvider implements OutputProvider{

    /**
     * Board size in DP for screen
     */
    private static final int BOARD_SIZE_DP = 80;

    /**
     * Board size in DP for screen
     */
    private static final int BOARD_CELL_PADDING = 8;

    /**
     * Board view object
     */
    private GridView boardView;

    /**
     * Current activity context
     */
    private Context context;

    /**
     * Board size
     */
    private int boardSize;

    /**
     * Board view adapter
     */
    private BoardAdapter boardAdapter;


    public AndroidOutputProvider(GridView boardView, int boardSize, Context context) {
        this.boardView = boardView;
        this.context = context;
        this.boardSize = boardSize;
        this.boardAdapter = new BoardAdapter(boardSize, context);
        boardView.setAdapter(boardAdapter);
    }

    @Override
    public void drawOnBoard(int x, int y, int resource) {
        if ( x >= boardSize || x < 0) {
            throw new IllegalArgumentException(" x value must be between (0, boardSize).");
        }
        if ( y >= boardSize || y < 0) {
            throw new IllegalArgumentException(" y value must be between (0, boardSize).");
        }
        if (boardView != null && boardAdapter != null) {
            boardAdapter.drawOnBoard(x, y, resource);
            boardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void displayMessage(String message) {
    }

    @Override
    public void displayDialog(String title, String message) {

    }

    private void initBoard() {
    }

    private class BoardAdapter extends BaseAdapter {

        private int boardSize;
        private int boardThumnnails[];
        private Context context;

        public BoardAdapter(int boardSize, Context context) {
            this.boardSize = boardSize;
            this.context = context;
            this.initBoardThumbnails(boardSize);
        }

        @Override
        public int getCount() {
            return boardThumnnails.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(BOARD_SIZE_DP, BOARD_SIZE_DP));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(BOARD_CELL_PADDING, BOARD_CELL_PADDING, BOARD_CELL_PADDING, BOARD_CELL_PADDING);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setTag(position);
            imageView.setImageResource(boardThumnnails[position]);
            return imageView;
        }

        private void initBoardThumbnails(int boardSize) {
            boardThumnnails = new int[boardSize * boardSize];
            for (int i = 0; i < boardSize * boardSize ; i++) {
                // init board with blank fields
                boardThumnnails[i] = R.drawable.board_blank_thumbnail;
            }
        }

        public void drawOnBoard(int x, int y, int resource) {
            boardThumnnails[convert2DIndexTo1D(x,y)] = resource;
        }
    }

    protected int convert2DIndexTo1D(int x, int y) {
        return x * boardSize + y;
    }

    protected Point convert1DIndexTo2D(int index) {
        Point point = new Point();
        int x = (int) Math.floor(((double) index / boardSize));
        int y = index % boardSize;
        point.set(x, y);
        return point;
    }
}
