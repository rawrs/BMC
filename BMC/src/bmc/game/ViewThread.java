package bmc.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ViewThread extends Thread{
	private Panel mPanel;
    private SurfaceHolder mHolder;
    private long mStartTime;
    private long mElapsed;
    private boolean mRun = false;
    
 
    public ViewThread(Panel panel) {
        mPanel = panel;
        mHolder = mPanel.getHolder();
    }
 
    public void setRunning(boolean run) {
        mRun = run;
    }
 
    @Override
    public void run() {
    	Canvas canvas = null;
        mStartTime = System.currentTimeMillis();
        while (mRun) {
            canvas = mHolder.lockCanvas();
            if (canvas != null) {
                mPanel.animate(mElapsed);
                mPanel.doDraw(mElapsed, canvas);
                mElapsed = System.currentTimeMillis() - mStartTime;
                mHolder.unlockCanvasAndPost(canvas);
            }
            mStartTime = System.currentTimeMillis();
        }
    }
}
