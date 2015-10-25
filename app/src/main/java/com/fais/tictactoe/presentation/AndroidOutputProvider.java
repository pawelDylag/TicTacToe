package com.fais.tictactoe.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.fais.tictactoe.R;
import com.fais.tictactoe.utilities.Util;
import com.fais.tictactoe.interfaces.OutputProvider;
import com.fais.tictactoe.presentation.widgets.BoardItemView;

import java.util.ArrayList;

/**
 * This class manages output for android
 * Created by paweldylag on 21/10/15.
 */
public class AndroidOutputProvider implements OutputProvider{

    /**
     * Board size in DP for screen
     */
    private static final int BOARD_CELL_SIZE_DP = 80;

    /**
     * Board size in DP for screen
     */
    private static final int BOARD_CELL_PADDING = 5;

    /**
     * Board view object
     */
    private GridView boardView;

    /**
     * Coordinator layout for displaying messages on screen
     */
    private CoordinatorLayout coordinatorLayout;

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

    public AndroidOutputProvider(GridView boardView, CoordinatorLayout coordinatorLayout, int boardSize, Context context) {
        this.boardView = boardView;
        this.context = context;
        this.boardSize = boardSize;
        this.coordinatorLayout = coordinatorLayout;
        this.boardAdapter = new BoardAdapter(boardSize, context);
        initBoardView();
    }

    private void initBoardView() {
        // set adapter for grid view
        boardView.setAdapter(boardAdapter);
        // set grid view params
        boardView.setNumColumns(boardSize);
        boardView.setColumnWidth(BOARD_CELL_SIZE_DP);
        // initial animation
        Animation cellAnimation = AnimationUtils.loadAnimation(context, R.anim.board_cell_appear);
        GridLayoutAnimationController animController = new GridLayoutAnimationController(cellAnimation);
        animController.setDirectionPriority(GridLayoutAnimationController.PRIORITY_NONE);
        animController.setColumnDelay(0.6f / boardSize);
        animController.setRowDelay(0.6f / boardSize);
        boardView.setLayoutAnimation(animController);
    }

    /**
     * Displays winer combination as a sweet animation!
     * @param winnerPoints
     */
    @Override
    public void displayWinnerCells (ArrayList<Point> winnerPoints) {
        if (winnerPoints == null) {
            throw new NullPointerException("Winner points list object must not be null");
        }
        if (winnerPoints.size() > boardSize * boardSize) {
            throw new IllegalArgumentException("Too many winner points.");
        }
        // run through all items
        for (int i = 0; i < boardSize * boardSize; i++) {
            // flag if current point is winning point
            boolean flag = false;
            // check all wining points
            for (int j = 0; j < winnerPoints.size(); j++) {
                if (winnerPoints.get(j) == null) {
                    throw new NullPointerException("Winner points in list must not be null");
                }
                int winningPointIndex = Util.convert2DIndexTo1D(winnerPoints.get(j).x, winnerPoints.get(j).y, boardSize);
                if (i == winningPointIndex) {
                    flag = true;
                }
            }
            if (!flag) {
                BoardItemView item = (BoardItemView) boardView.getChildAt(i);
                Animation cellAnimation = AnimationUtils.loadAnimation(context, R.anim.board_cell_downsize);
                item.setAnimation(cellAnimation);
            }
        }
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
            this.playSound(R.raw.draw_sound);
            boardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayDialog(String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(context).setMessage(message).setTitle(title).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    /**
     * Play sound from raw resources
     * @param soundResource
     */
    private void playSound(int soundResource) {
        MediaPlayer mp = MediaPlayer.create(context, soundResource);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // release reference to mediaPlayer object
                mp.release();
            }

        });
        mp.start();
    }

    /**
     * Adapter for GridView
     */
    private class BoardAdapter extends BaseAdapter {

        private int boardThumbnails[];
        private Context context;

        public BoardAdapter(int boardSize, Context context) {
            this.context = context;
            this.initBoardThumbnails(boardSize);
        }

        @Override
        public int getCount() {
            return boardThumbnails.length;
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
            BoardItemView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new BoardItemView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(BOARD_CELL_PADDING, BOARD_CELL_PADDING, BOARD_CELL_PADDING, BOARD_CELL_PADDING);
            } else {
                imageView = (BoardItemView) convertView;
            }
            imageView.setTag(position);
            imageView.setImageResource(boardThumbnails[position]);
            return imageView;
        }

        private void initBoardThumbnails(int boardSize) {
            boardThumbnails = new int[boardSize * boardSize];
            for (int i = 0; i < boardSize * boardSize ; i++) {
                // init board with blank fields
                boardThumbnails[i] = R.drawable.board_blank_thumbnail;
            }
        }

        public void drawOnBoard(int x, int y, int resource) {
            boardThumbnails[Util.convert2DIndexTo1D(x, y, boardSize)] = resource;
        }
    }


}
