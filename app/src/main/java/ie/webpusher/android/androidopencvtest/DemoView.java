package ie.webpusher.android.androidopencvtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class DemoView extends Activity implements CameraBridgeViewBase.CvCameraViewListener, View.OnTouchListener {

    private final String TAG = "DemoView";
    private CameraBridgeViewBase mOpenCvCameraView;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");

                    /* Now enable camera view to start receiving frames */
                    mOpenCvCameraView.setOnTouchListener(DemoView.this);
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Log.d(TAG, "Creating and setting view");
        mOpenCvCameraView = (CameraBridgeViewBase) new JavaCameraView(this, -1);
        setContentView(mOpenCvCameraView);
        mOpenCvCameraView.setCvCameraViewListener(this);

    }


    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "called onCreateOptionsMenu");
//        mItemHideNumbers = menu.add("Show/hide tile numbers");
//        mItemStartNewGame = menu.add("Start new game");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "Menu Item selected " + item);
//        if (item == mItemStartNewGame) {
//            /* We need to start new game */
//            mPuzzle15.prepareNewGame();
//        } else if (item == mItemHideNumbers) {
//            /* We need to enable or disable drawing of the tile numbers */
//            mPuzzle15.toggleTileNumbers();
//        }
        return true;
    }

    public void onCameraViewStarted(int width, int height) {
//        mGameWidth = width;
//        mGameHeight = height;
//        mPuzzle15.prepareGameSize(width, height);
    }

    public void onCameraViewStopped() {
    }

    public boolean onTouch(View view, MotionEvent event) {
        int xpos, ypos;

//        xpos = (view.getWidth() - mGameWidth) / 2;
//        xpos = (int)event.getX() - xpos;
//
//        ypos = (view.getHeight() - mGameHeight) / 2;
//        ypos = (int)event.getY() - ypos;
//
//        if (xpos >=0 && xpos <= mGameWidth && ypos >=0  && ypos <= mGameHeight) {
//            /* click is inside the picture. Deliver this event to processor */
//            mPuzzle15.deliverTouchEvent(xpos, ypos);
//        }

        return false;
    }

    public Mat onCameraFrame(Mat inputFrame) {
        return inputFrame;
    }
}
