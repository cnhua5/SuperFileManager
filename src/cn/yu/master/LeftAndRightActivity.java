package cn.yu.master;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;
import cn.yu.master.activities.ClockListFragment;
import cn.yu.master.activities.FragmentKeyListener;
import cn.yu.master.activities.SuperImageFragment;
import cn.yu.master.utils.DirectoryOperate;
import cn.yu.master.viewers.FileViewerFragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class LeftAndRightActivity extends BaseActivity {

	private static final String TAG = "LeftAndRightActivity";

	private SlidingMenu mSlidingMenu;
	private FragmentManager mFragmentManager;
	private Fragment mCurrFragment;
	private FileViewerFragment mFileViewerFragment;

	public LeftAndRightActivity() {
		super(R.string.left_and_right);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSlidingMenu = getSlidingMenu();
		mSlidingMenu.setMode(SlidingMenu.LEFT);
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mFragmentManager = getSupportFragmentManager();
		setContentView(R.layout.content_frame);

		replace(R.id.content_frame, new ClockListFragment());
		// mSlidingMenu.setSecondaryMenu(R.layout.menu_frame_two);
		// mSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		// replace(R.id.menu_frame_two, new SampleListFragment(new String[] {
		// "00!!!!", "00!!!!" }));
		overridePendingTransition(R.anim.activity_enter, R.anim.activity_quit);
		mHandler.sendEmptyMessage(MSG_FULL_SCREEN);
		mHandler.sendEmptyMessageDelayed(MSG_QUIT_FULL_SCREEN, 2000);
	}

	@Override
	public void switchPageViewContent(int position) {
		switch (position) {
		case 0:
			replace(R.id.content_frame, new ClockListFragment());
			mSlidingMenu.setStatic(false);
			break;
		case 1:
			replace(R.id.content_frame, new SuperImageFragment());
			mSlidingMenu.setStatic(false);
			break;
		case 2:
			mFileViewerFragment = new FileViewerFragment();
			setFragmentKeyListener(mFileViewerFragment);
			Bundle bundle = new Bundle();
			bundle.putString("file_dir", DirectoryOperate.INTERNAL_SDCARD);
			mFileViewerFragment.setArguments(bundle);
			replace(R.id.content_frame, mFileViewerFragment);
			mSlidingMenu.setStatic(false);
			break;
		default:
			break;
		}
	}

	private void replace(int rId, Fragment frag) {
		mCurrFragment = frag;
		mFragmentManager.beginTransaction().replace(rId, frag).commit();
	}

	private static final int MSG_QUIT = 0;
	private static final int MSG_QUIT_CANCLE = 1;
	private static final int MSG_FULL_SCREEN = 2;
	private static final int MSG_QUIT_FULL_SCREEN = 3;
	private long firstPressMill = 0;

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_QUIT:
				if (firstPressMill == 0) {
					Toast.makeText(getApplicationContext(), "再按一次退出！", 1000)
							.show();
					firstPressMill = System.currentTimeMillis();
					return;
				}
				long delay = System.currentTimeMillis() - firstPressMill;
				if (delay > 100 && delay < 2000) {
					firstPressMill = 0;
					finish();
				}
				break;
			case MSG_QUIT_CANCLE:
				firstPressMill = 0;
				break;
			case MSG_FULL_SCREEN:
				setFullScreen();
				break;
			case MSG_QUIT_FULL_SCREEN:
				cancelFullScreen();
				break;
			default:
				break;
			}
		};
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (null != getFragmentKeyListener()) {
			if (mCurrFragment instanceof FileViewerFragment) {
				if (mFileViewerFragment.onFragmentKeyDown(keyCode)) {
					return false;
				}
			}
		}
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Log.e(TAG, "on  Activity  Key Back>>>>>>>>>>>>");
			if (mSlidingMenu.isMenuShowing()) {
				return false;
			}
			mHandler.sendEmptyMessage(MSG_QUIT);
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MSG_QUIT_CANCLE), 2000);
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	};

	private void setFullScreen() {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	private void cancelFullScreen() {
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
