//package com.example.test_01.code;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.ActivityManager;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.PendingIntent;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.bluetooth.BluetoothManager;
//import android.content.BroadcastReceiver;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.media.AudioManager;
//import android.media.SoundPool;
//import android.media.ToneGenerator;
//import android.net.Uri;
//import android.nfc.NfcAdapter;
//import android.nfc.Tag;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.os.Parcelable;
//import android.provider.Settings;
//import androidx.annotation.NonNull;
//import androidx.localbroadcastmanager.content.LocalBroadcastManager;
//import androidx.core.graphics.drawable.RoundedBitmapDrawable;
//import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
//import android.text.Editable;
//import android.text.Spannable;
//import android.text.SpannableStringBuilder;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.text.style.AbsoluteSizeSpan;
//import android.text.style.ForegroundColorSpan;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
//import com.michael.easydialog.EasyDialog;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.assist.FailReason;
//import com.nostra13.universalimageloader.core.assist.ImageSize;
//import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
//import com.welltizen.exman.BaseActivity;
//import com.welltizen.exman.BluetoothReaderHelper;
//import com.welltizen.exman.BoomCare.FeverBaseActivity;
//import com.welltizen.exman.BuildConfig;
//import com.welltizen.exman.ExmanApplication;
//import com.welltizen.exman.IntroAct;
//import com.welltizen.exman.NFCBluetoothAdapter;
//import com.welltizen.exman.R;
//import com.welltizen.exman.StaticObj;
//import com.welltizen.exman.gcm.MyFirebaseMessagingService;
//import com.welltizen.exman.log.LogManager;
//import com.welltizen.exman.main.MainAct;
//import com.welltizen.exman.main.SyncDate;
//import com.welltizen.exman.member.CustContactData;
//import com.welltizen.exman.main.NoticePickerDialog;
//import com.welltizen.exman.main.OptionNewAct;
//import com.welltizen.exman.member.MemberInfoAct;
//import com.welltizen.exman.member.PushRemoveAct;
//import com.welltizen.exman.member.PushRemoveUserData;
//import com.welltizen.exman.member.StudentPointData;
//import com.welltizen.exman.network.HttpClientRequest;
//import com.welltizen.exman.permission.WellPermission;
//import com.welltizen.exman.util.BackPressCloseHandler;
//import com.welltizen.exman.util.DBHelper;
//import com.welltizen.exman.util.FileDownLoad;
//import com.welltizen.exman.util.MyProgressDialog;
//import com.welltizen.exman.util.NfcService;
//import com.welltizen.exman.util.Util;
//import com.welltizen.exman.util.WellDialog;
//import com.welltizen.message.data.MessageData;
//import com.welltizen.message.util.MessageCommon;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static com.welltizen.exman.util.CommonUtilities.SENDER_ID;
//
//public class AttendSelfNewAct extends BaseActivity implements OnClickListener, BackPressCloseHandler.FinishListener {
//
//    private static final String TAG = "AttendSelfNewAct";
//    private static final String DUMMY_DATE = "9000";
//
//    private final int INPUT_KEYBOARD = 0;
//    private final int INPUT_RFID = 1;
//
//    MyProgressDialog mProgressDialog = null;
//
//    private AttendSelfAdapter mAdapter;
//    private ListView mListView;
//    private ImageView mResultFound, newVersionNoti;
//    private TextView mListEmptyView;
//    private TextView mKepadTextView;
//    private Button btnClose, btnKeypad1, btnKeypad2, btnKeypad3, btnKeypad4, btnKeypad5, btnKeypad6, btnKeypad7, btnKeypad8, btnKeypad9, btnKeypad0, btnKeypadReset, btnKeypadDel;
//    private Button btnLock, btnOption, btnBoard, btnDeviceSearch, btnDeviceState, btnBluetoothState, btnNfcState, btnAttendSelfLabel, btnAttendSelfSync, btnUserInfo;
//    private Button nfcExpBtn, bleExpBtn, bluetoothRefresh;
//    private EditText edRfid;
//    private LinearLayout loCardTag;
//    private TextView textTagResult;
//    private View imgTagResult, imgTagResultBirthday, bleExpView, nfcExpView, nMonitorAttend;
//
//    //CAMERA
////    private AttendCameraSurface mAttendSelfCamera;
//
//    static final int nTextChange = 5656;
//    private int nPos = 0;
//
//    private ArrayList<AttendData> attendList = new ArrayList<>();
//    private ArrayList<AttendRowData> attendRowList = new ArrayList<>();
//    private AttendRowData rowSelectData = new AttendRowData();
//    private String sAttendHomeStatus = "";
//
//    private ArrayList<AttendResultData> attendResultList = new ArrayList<AttendResultData>();
//
//    private ArrayList<PushRemoveUserData> removeUserList = new ArrayList<PushRemoveUserData>();
//    private ArrayList<StudentPointData> mStudentPointData = new ArrayList<>();
//
//    private long lSmsSplitAlertTime;
//
//    private String mCurDate = "";
//    private String mCurTime = "";
//    private String mSearchTime = "";
//    private String mSaveDay = "";
//    private String mInputNumber = "";
//    private String mCardNumber = "";
//    private final int INPUT_DIGIT_MAX = 4;
//    public static final int FEVER_RESULT = 999;
//
//    DBHelper dbHelper;
//    SQLiteDatabase db;
//    private boolean bKeyPress = false;
//    private boolean bFirstSearch = false;
//
//    private LogManager logManager;
//    private ToneGenerator mDTMFToneGenerator = null;
//    private SoundPool mSoundPool = null;
//    private int mSendingSucceed, mNoResultSound, selPos = 0;
//    private Runnable mRunnableInputReset, mRunnableShowResultImage;
//    private int mSoundAttending, mSoundLeaving, mSoundBirthday, mCurrentSound;
//
//    Dialog dialog;
//    View mmsView;
//
//    // NFC
//    private Tag mNfcTag;
//    private NfcAdapter adptNfc;
//    private PendingIntent ittPend;
//    private static final String CHARS = "0123456789ABCDEF";
//    private int inputMode = INPUT_RFID;
//    private boolean didTag = false;
//
//    // Bundle
//    private String bundleId = "", bundleName = "";
//
//    private int nRetryCnt = 0;
//    private String mSaveAtdDate = "";
//    private String mSaveItem = "";
//    private String mSaveUserId = "";
//    private String mSaveTime = "";
//    private String mItem = "";
//    private String sAttendReason = "0";
//    private String mKeyValue = "";
//    private String onKeyValue = ""; // 실제로 입력된 키값
//
//    private boolean isRfidTaging = false;   // RFID 인식 체크
//    private boolean bReg = false;    // 카드 등록 여부
//    private boolean bMakeAttend = false;    // 출석데이터 생성 여부
//
//    // 출석 & 귀가 메시지
//    private String sMsg = "";
//
//    private String sInputNum = "";
//    private boolean bInputCheck = false;
//    private boolean bRfidCard = false;
//    private String adminImgUrl = "";
//
//    private BroadcastReceiver mReceiver;
//
//    /********** Bluetooth 관련 데이터***********/
//    private BluetoothAdapter.LeScanCallback mLeScanCallback;
//    private NFCBluetoothAdapter mNFCBluetoothAdapter;
//    private BluetoothAdapter mBluetoothAdapter; // 블루투스 리더기
//    private static final int REQUEST_ENABLE_BT = 1;
//    private static final long SCAN_PERIOD = 10000;   /* Stops scanning after 10 seconds. */
//    private AlertDialog mBlutoothDialog;
//    private boolean mScanning;
////    public BluetoothAdapter btAdapter;
////    public ExBluetooth blueService;
////    public BluetoothDevice deviceR;
//
//    // 요청 정수
//    private final int TAG_DELAY_ADPOP = 11;     // 카드 태깅 지연 정수(애드팝)
//    private final int NEW_NOTI_CALL = 1001;   // 신규버전 알림 handler message
//
//    // 고정 값
//    private int DELAY_SECOND = 3000;    // 카드 태깅 시 화면전환 시간
//
//    private String mDeviceName = "";
//    private String mDeviceAddress = "";
//
//    private String sAttendType = "";
//
//    private String sWarnningRetVal = "";
//    private String sWarnningMsg = "";
//    private String sDeviceYn = "N";
//
//    private boolean blueToothDisconn = false;
//    private boolean bForeGround = true;
//    private boolean bAttendCheck = false;
//    private String sFailMsg = "";
//    private int mDelaySec = 60;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//        StaticObj.sAppId = "31";
//        logManager = new LogManager(this);
//        logManager.write("V", "31", "", "", "", "C");
//
//        Log.d("onCreate", "StaticObj.sKeyBoard[" + StaticObj.matchSd().sKeyBoard + "]");
//
//        setContentView(R.layout.rn_attend_self_new_act);
//
//        mRunnableInputReset = new Runnable() {
//            @Override
//            public void run() {
//                imgTagResult.setBackgroundDrawable(null);
//                resetAttendeeData(true);
//            }
//        };
//
//        mRunnableShowResultImage = new Runnable() {
//            @Override
//            public void run() {
//                mListEmptyView.setVisibility(View.VISIBLE);
//                mResultFound.setVisibility(View.GONE);
//            }
//        };
//
//        sDeviceYn = (StaticObj.matchSd().sDeviceType.equals("B") || StaticObj.matchSd().sDeviceType.equals("S")) ? "Y" : "N";
//
//        setLayout();
//        setEvent();
//        viewGetTime();
//        mHandler.sendEmptyMessageDelayed(100, 1000);
//
//        try {
//            mDTMFToneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF, ToneGenerator.MAX_VOLUME);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logManager.write("V", "31", "", "", android.os.Build.MODEL, "E");
//        }
//
//        mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//
//        if (StaticObj.matchSd().bOptionAttendAutoLock && StaticObj.bScreenLock == false && StaticObj.bScreenFirstLock == false) {
//            SharedPreferences pref = getSharedPreferences(StaticObj.matchSd().sGroupCd, Context.MODE_PRIVATE);
//            String sPassword = pref.getString("attend_lock_password", "");
//            Log.d("AttendSelfNewAct", "sPassword[" + sPassword + "]");
//            if (!TextUtils.isEmpty(sPassword)) {
//                //btnLock.setSelected(true);
//                StaticObj.bScreenLock = true;
//                StaticObj.bScreenFirstLock = true;
//            }
//        }
//
//        if (StaticObj.bScreenLock) {
//            btnLock.setSelected(true);
//        } else {
//            btnLock.setSelected(false);
//        }
//
//        if(!Build.MODEL.equals("TCC8920_EVM"))
//        {
//            if(!BuildConfig.IS_EXMAN) {
//                GCMSetting();
//            }
//
//            if(!Build.MODEL.equals("muPAD7")) {
//                // Bluetooth 상태 리시버
//                IntentFilter bfilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
//                registerReceiver(bluetoothReceiver, bfilter);
//
//                // NFC 상태 리시버
//                IntentFilter nfilter = new IntentFilter(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED);
//                registerReceiver(nfcReceiver, nfilter);
//
//                initNfc();
//            }
//        }
//
//        if (Build.VERSION.SDK_INT < 18) {
//            btnDeviceSearch.setVisibility(View.GONE);
//            btnDeviceState.setVisibility(View.GONE);
//        } else {
//            mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
//                @Override
//                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
//                    runOnUiThread(new Runnable() {
//
//                        public boolean contains(Object object) {
//                            if (object != null && mNFCBluetoothAdapter != null) {
//                                for (int i = 0; i < mNFCBluetoothAdapter.getCount(); i++) {
//                                    if (object.equals(mNFCBluetoothAdapter.getItem(i))) {
//                                        return true;
//                                    }
//                                }
//                            } else {
//                                if (mNFCBluetoothAdapter != null) {
//                                    for (int i = 0; i < mNFCBluetoothAdapter.getCount(); i++) {
//                                        if (mNFCBluetoothAdapter.getItem(i) == null) {
//                                            return true;
//                                        }
//                                    }
//                                }
//                            }
//                            return false;
//                        }
//
//                        @Override
//                        public void run() {
//                            if (!contains(device) && mNFCBluetoothAdapter != null
//                                    //&& !TextUtils.isEmpty(device.getName())
//                            ) {
//                                logManager.write("B", "118", "", "mNFCBluetoothAdapter", device.getAddress(), device.getName());
////                                Log.e("kooj", Util.nvl(device.getAddress())+", "+Util.nvl(device.getName()));
//                                mNFCBluetoothAdapter.addDevice(device);
//                                mNFCBluetoothAdapter.notifyDataSetChanged();
//
//                                if(Util.nvl(device.getName()).contains("ACR1255U")){
//                                    SharedPreferences pref = getSharedPreferences("deviceInfo", Activity.MODE_PRIVATE);
//                                    SharedPreferences.Editor edit = pref.edit();
//                                    edit.putString("nfc_device_address", device.getAddress());
//                                    edit.putString("nfc_device_name", device.getName());
//                                    edit.commit();
//                                }
//                            }
//                        }
//                    });
//                }
//            };
//
//            // 자동 연결일 경우 저장 되어 있는 정보를 가지고 자동 연결을 시도 합니다.
//            if(StaticObj.matchSd().bOptionBluetoothAutoConnect){
//                SharedPreferences pref = getSharedPreferences("deviceInfo", Activity.MODE_PRIVATE);
//                mDeviceAddress = pref.getString("nfc_device_address", "");
//                mDeviceName = pref.getString("nfc_device_name", "");
//
//                if (!Util.nvl(mDeviceAddress).equals("") && !Util.nvl(mDeviceName).equals("")) {
//                    // bluetooth 사용 할 수 있는 상태 인지 아닌지 확인 이 필요 하다.
//                    Log.d(TAG, "onCreate - mDeviceAddress : " + mDeviceAddress + ", mDevicename : " + mDeviceName);
//                    if (initBluetooth()) {
//                        // Bluetooth 사용 가능
//                        setNFCBluetoothStart();
//                    }
//                }
//            }
//        }
//
//        if(!BuildConfig.IS_EXMAN){
//            btnAttendSelfLabel.setText("출결");
//            btnAttendSelfSync.setVisibility(View.VISIBLE);
//            btnOption.setVisibility(View.VISIBLE);
//        }else{
//            btnOption.setVisibility(View.GONE);
//        }
//
//        loadData();
//
//        setAutoModeBroadcast();
//
//        AttendDaySocCon(0);
//
//        // url 다운로드 바로가기
//        SharedPreferences pref = getSharedPreferences("deviceInfo", Context.MODE_PRIVATE);
//        boolean isInstall = pref.getBoolean("isInstalled", false);
//        if(!BuildConfig.IS_DEPRECATED_SMS && !isInstall){
//            Util.addDownloadIcon(AttendSelfNewAct.this);
//        }
//
//        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
//            @Override
//            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                soundPool.play(sampleId, 1f, 1f, 0, 0, 1f);
//            }
//        });
//    }
//
//    private void GCMSetting(){
//
//		StaticObj.checkNotNull(SENDER_ID, "SENDER_ID", getApplicationContext());
//
//        if (StaticObj.mPrtSd.bOptionUpdateExist) {
//            MobileOptionSendCon();
//        } else {
//            String sToday = Util.dtCurrent("yyyy-MM-dd");
//            if (!StaticObj.mPrtSd.sPushUserDeleteDate.equals(sToday) && StaticObj.mPrtSd.sGrade.equals("A"))
//            {
//                PushRemoveUserSocCon(StaticObj.mPrtSd.sGroupCd, StaticObj.mPrtSd.sAdminId, sToday);
//            }
//            if(BuildConfig.IS_EXMAN){
//                checkPopupNotice();
//            }
//        }
//
//        if (checkPlayServices()) {
//            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                @Override
//                public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                    if (!task.isSuccessful()) {
//                        Log.w(TAG, "getInstanceId failed", task.getException());
//                        LogManager logManager = new LogManager(getApplicationContext());
//                        logManager.write("D", "getInstanceId failed[" + task.getException().getMessage() + "]");
//                        return;
//                    }
//
//                    // Get new Instance ID token
//                    String token = task.getResult().getToken();
//                    if (!TextUtils.isEmpty(token) && (!StaticObj.mPrtSd.sServerTokenId.equals(token) || StaticObj.mPrtSd.bTokenUpdate)) {
//                        MyFirebaseMessagingService.sendRegistrationToServer(getApplicationContext(), token);
//                    }
//                }
//            });
//        }
//    }
//
//    private void saveDeleteDate() {
//
//        closeProgressDialog();
//
//        String sToday = Util.dtCurrent("yyyy-MM-dd");
//
//        StaticObj.mPrtSd.sPushUserDeleteDate = sToday;
//        SharedPreferences pref = getSharedPreferences(StaticObj.mPrtSd.sGroupCd, 0);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putString("push_user_delete_date", StaticObj.mPrtSd.sPushUserDeleteDate);
//        edit.commit();
//    }
//
//    private void checkPopupNotice() {
//
//        Log.d("checkPopupNotice", "checkPopupNotice bLoginMobileNotice(" + StaticObj.bLoginMobileNotice + ")");
//        if (StaticObj.bLoginMobileNotice) {
//            // 로그인시 한번만 뛰우기
//            return;
//        }
//
//        String sCurDate = Util.dtCurrent("yyyyMMdd");
//
//        SharedPreferences pref = getSharedPreferences("deviceInfo", 0);
//        String sClickDate = pref.getString("mobile_notice_click", "20160926");
//
//        if (!sCurDate.equals(sClickDate)) {
//
//            lSmsSplitAlertTime = pref.getLong("sms_split_alert_time", 0);
//
//            long startTime = System.currentTimeMillis();
//            long endTime = lSmsSplitAlertTime;
//
//            //Log.v("test", "endTime - startTime = " + (endTime - startTime));
//            if (endTime - startTime <= 0) {
//                MobileNoticeSoc();
//            }
//        }
//    }
//
//    private void popupNotice(String sContentUrl, String sViewType, boolean bPopupNotice) {
//
//        int nViewType = 30;
//        if (!sViewType.equals("0")) {
//            try {
//                nViewType = Integer.parseInt(sViewType);
//            } catch (Exception e){
//                nViewType = 1;
//            }
//        }
//
//        StaticObj.bLoginMobileNotice = true;
//
//        final int nViewDate = nViewType;
//
//        if (bPopupNotice) {
//            NoticePickerDialog dialog = new NoticePickerDialog(AttendSelfNewAct.this, sContentUrl, new NoticePickerDialog.DialogDismissListener() {
//                @Override
//                public void dismiss() {//sViewType일간 다시보지 않기
//                    Calendar cal = Calendar.getInstance();
//                    cal.add(Calendar.DATE, nViewDate);
//                    lSmsSplitAlertTime = cal.getTimeInMillis();
//                    SharedPreferences pref = getSharedPreferences("deviceInfo", 0);
//                    SharedPreferences.Editor edit = pref.edit();
//                    edit.putLong("sms_split_alert_time", lSmsSplitAlertTime);
//                    edit.putString("mobile_notice_click", Util.dtCurrent("yyyyMMdd"));
//                    edit.commit();
//                }
//            }, true);
//            dialog.show();
//        } else {
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DATE, nViewDate);
//            lSmsSplitAlertTime = cal.getTimeInMillis();
//            SharedPreferences pref = getSharedPreferences("deviceInfo", 0);
//            SharedPreferences.Editor edit = pref.edit();
//            edit.putLong("sms_split_alert_time", lSmsSplitAlertTime);
//            edit.putString("mobile_notice_click", Util.dtCurrent("yyyyMMdd"));
//            edit.commit();
//        }
//    }
//
//    private boolean checkPlayServices() {
//
//        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
//
//        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(getApplicationContext());
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (googleApiAvailability.isUserResolvableError(resultCode)) {
//                googleApiAvailability.getErrorDialog(this, resultCode, 9000).show();
//            } else {
//                Log.i(TAG, "This device is not supported.");
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }
//
//    private void CustPointSocCon(String sGroupCd, String sAuthCode) {
//
//        StaticObj.sAppId = "0202";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_S_CUST_POINT");
//        map.put("group_cd", sGroupCd);
//        map.put("cust_id", sAuthCode);
////        map.put("cust_nm", mStudentData.get(nSelectIndex).getsName());
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void MobileNoticeSoc() {
//
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        StaticObj.sAppId = "";
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("sp", "dbo.MEXM_S_USERPOPUP");
//        map.put("group_cd", StaticObj.mPrtSd.sGroupCd);
//        map.put("admin_id", StaticObj.mPrtSd.sAdminId);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    public void initValue() {
//        nRetryCnt = 0;
//        mSaveAtdDate = "";
//        mSaveItem = "";
//        mSaveUserId = "";
//        mSaveTime = "";
//        sAttendType = "";
//    }
//
//    public void setValue(String sAtdDate, String sItem, String sUserId, String sTime) {
//        mSaveAtdDate = sAtdDate;
//        mSaveItem = sItem;
//        mSaveUserId = sUserId;
//        mSaveTime = sTime;
//    }
//
//    private void setAttendSound(){
//        File filePath = null;
//        if (StaticObj.matchSd().nSelectAttendingSound == 1) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_ATTENDING_01);
//        } else if (StaticObj.matchSd().nSelectAttendingSound == 2) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_ATTENDING_02);
//        }
//        if (filePath != null) {
//            if (filePath.exists()) {
//                mSoundAttending = mSoundPool.load(filePath.getPath(), 1);
//            } else {
//                if(!filePath.isDirectory()) filePath.getParentFile().mkdirs();
//                FileDownLoad task = new FileDownLoad(getApplicationContext(), StaticObj.soundAddress + filePath.getName(), "W") {
//                    @Override
//                    protected void onPostExecute(Integer nResult) {
//                        super.onPostExecute(nResult);
//                        if(nResult == 0){
//                            mSoundAttending = mSoundPool.load(StaticObj.SOUND_FILE_DIR + "/" + getsFileUrl(), 1);
//                        }
//                    }
//                };
//                task.execute();
//            }
//        }
//    }
//
//    private void setLeavingSound(){
//        File filePath = null;
//        if (StaticObj.matchSd().nSelectLeavingSound == 1) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_LEAVING_01);
//        } else if (StaticObj.matchSd().nSelectLeavingSound == 2) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_LEAVING_02);
//        } else if (StaticObj.matchSd().nSelectLeavingSound == 3) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_LEAVING_03);
//        }
//        if (filePath != null) {
//            if (filePath.exists()) {
//                mSoundLeaving = mSoundPool.load(filePath.getPath(), 1);
//            } else {
//                if(!filePath.isDirectory()) filePath.getParentFile().mkdirs();
//                FileDownLoad task = new FileDownLoad(getApplicationContext(), StaticObj.soundAddress + filePath.getName(), "W") {
//                    @Override
//                    protected void onPostExecute(Integer nResult) {
//                        super.onPostExecute(nResult);
//                        if(nResult == 0){
//                            mSoundLeaving = mSoundPool.load(StaticObj.SOUND_FILE_DIR + "/" + getsFileUrl(), 1);
//                        }
//                    }
//                };
//                task.execute();
//            }
//        }
//    }
//
//    private void setBirthdaySound(){
//        File filePath = null;
//        if (StaticObj.matchSd().nSelectBirthdaySound == 1) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_BIRTHDAY_01);
//        } else if (StaticObj.matchSd().nSelectBirthdaySound == 2) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_BIRTHDAY_02);
//        } else if (StaticObj.matchSd().nSelectBirthdaySound == 3) {
//            filePath = new File(StaticObj.SOUND_FILE_DIR + "/" + StaticObj.SOUND_FILE_NAME_BIRTHDAY_03);
//        }
//        if (filePath != null) {
//            if (filePath.exists()) {
//                mSoundBirthday = mSoundPool.load(filePath.getPath(), 1);
//            } else {
//                if(!filePath.isDirectory()) filePath.getParentFile().mkdirs();
//                FileDownLoad task = new FileDownLoad(getApplicationContext(), StaticObj.soundAddress + filePath.getName(), "W") {
//                    @Override
//                    protected void onPostExecute(Integer nResult) {
//                        super.onPostExecute(nResult);
//                        if(nResult == 0){
//                            mSoundBirthday = mSoundPool.load(StaticObj.SOUND_FILE_DIR + "/" + getsFileUrl(), 1);
//                        }
//                    }
//                };
//                task.execute();
//            }
//        }
//    }
//
//    private void initNfc() {
//        adptNfc = NfcAdapter.getDefaultAdapter(this);
//        Intent intent = new Intent(getApplicationContext(), getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        ittPend = PendingIntent.getActivity(this, 0, intent, 0);
//        if (adptNfc != null && !adptNfc.isEnabled()) {
////          createDlgNfcOn();
////          Toast.makeText(getApplicationContext(), "NFC로 출석하기 위해선 NFC 설정에서 읽기 쓰기 기능을 켜 주셔야 합니다.", 1).show();
//        }
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        Log.d("onNewIntent", "in didTag[" + didTag + "]");
//        if(!Build.MODEL.equals("TCC8920_EVM")){
//            if(!BuildConfig.IS_EXMAN && StaticObj.matchSd().sChildKeyYn.equals("Y")){
//                Util.toastMessage(AttendSelfNewAct.this, "전자출결장비로 태깅하시기 바랍니다.");
//                return;
//            }
//
//            mListEmptyView.setVisibility(View.GONE);
//            mNfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            if(mNfcTag == null){
//                return;
//            }
//            String cardNo = hexToString(mNfcTag.getId());
//            Log.d(TAG, "onNewIntent cardNo[" + cardNo + "]");
//            sAttendType = "N";
//            inputMode = INPUT_RFID;
//            mCardNumber = cardNo;
//            SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_CUST, new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//
//                    if (isFinishing()) {
//                        return;
//                    }
//
//                    loadData();
//                    AttendDaySyncSocCon();
//                }
//            });
//            mNfcTag = null;
//        }else{
//            mListEmptyView.setVisibility(View.GONE);
//
//            Parcelable parcelable = null;
//            try {
//                parcelable = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            }catch (Exception e){
//                Toast.makeText(getApplicationContext(), "NFC 태깅 오류", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//                return;
//            }
//
//            if(parcelable == null){
//                return;
//            }
//
//            String cardNo = null;
//
//            if (parcelable instanceof Tag) {
//                Tag nfcTag = (Tag) parcelable;
//                cardNo = Util.nvl(hexToString(nfcTag.getId()));
//            } else if (parcelable instanceof com.duali.nfc.tag.Tag) {
//                com.duali.nfc.tag.Tag dualiNfcTag = (com.duali.nfc.tag.Tag) parcelable;
//                cardNo = Util.nvl(hexToString(dualiNfcTag.GetCardUid()));
//
//                if (cardNo.length() > 8) {
//                    cardNo = cardNo.substring(cardNo.length() - 8, cardNo.length());
//                }
//            } else {
//                throw new ClassCastException();
//            }
//
//            Log.d(TAG, "onNewIntent cardNo[" + cardNo + "]");
//
//            try {
//                if (cardNo.length() > 4) {
//                    String lastValue = cardNo.substring(cardNo.length() - 4, cardNo.length());
//                    if (Util.nvl(lastValue).equals(DUMMY_DATE)) {
//                        cardNo = cardNo.substring(0, cardNo.length() - 4);
//                    }
//                }
//                sAttendType = "N";
//                inputMode = INPUT_RFID;
//                mCardNumber = cardNo;
//                SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_CUST, new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//
//                        if (isFinishing()) {
//                            return;
//                        }
//
//                        loadData();
//                        AttendDaySyncSocCon();
//                    }
//                });
//            } catch (Exception e) {
//            }
//        }
//    }
//
//    public static String hexToString(byte[] data) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < data.length; ++i) {
//            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(CHARS.charAt(data[i] & 0x0F));
//        }
//        return sb.toString();
//    }
//
//    public static String hexToString(byte[] data, int nLength) {
//        StringBuilder sb = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//        for (int i = 0; i < nLength; ++i) {
//            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F)).append(CHARS.charAt(data[i] & 0x0F));
//            //sb2.append(new String(data[i], ))
//        }
//        return sb.toString();
//    }
//
//    private void setLayout() {
//
//        edRfid = (EditText)this.findViewById(R.id.edRfid);
//        // 2020.08.03 sws 외부에서 한글키패드를 마지막으로 사용시 한글키패드가 기본으로 되어서 영문으로 강제적으로 적용
//        edRfid.setPrivateImeOptions("defaultInputmode=english;symbol=false");
//
//        mKepadTextView = (TextView) findViewById(R.id.attend_self_keypad_textview);
//        btnClose = (Button) findViewById(R.id.attend_self_btn_close);
//        btnKeypad1 = (Button) findViewById(R.id.attend_self_keypad_btn_1);
//        btnKeypad2 = (Button) findViewById(R.id.attend_self_keypad_btn_2);
//        btnKeypad3 = (Button) findViewById(R.id.attend_self_keypad_btn_3);
//        btnKeypad4 = (Button) findViewById(R.id.attend_self_keypad_btn_4);
//        btnKeypad5 = (Button) findViewById(R.id.attend_self_keypad_btn_5);
//        btnKeypad6 = (Button) findViewById(R.id.attend_self_keypad_btn_6);
//        btnKeypad7 = (Button) findViewById(R.id.attend_self_keypad_btn_7);
//        btnKeypad8 = (Button) findViewById(R.id.attend_self_keypad_btn_8);
//        btnKeypad9 = (Button) findViewById(R.id.attend_self_keypad_btn_9);
//        btnKeypad0 = (Button) findViewById(R.id.attend_self_keypad_btn_0);
//        btnKeypadReset = (Button) findViewById(R.id.attend_self_keypad_btn_reset);
//        btnKeypadDel = (Button) findViewById(R.id.attend_self_keypad_btn_del);
//        mResultFound = (ImageView) findViewById(R.id.attend_self_result_found_or_not);
//        newVersionNoti = (ImageView) findViewById(R.id.new_version_noti);
//        loCardTag = (LinearLayout) this.findViewById(R.id.loCardTag);
//        textTagResult = (TextView)this.findViewById(R.id.textTagResult);
//        imgTagResult = this.findViewById(R.id.imgTagResult);
//        imgTagResultBirthday = this.findViewById(R.id.imgTagResultBirthday);
//        mListEmptyView = (TextView) findViewById(R.id.attend_self_list_emptyview);
//        nMonitorAttend = (View) findViewById(R.id.monitor_attend);
//
//        mListView = (ListView) findViewById(R.id.attend_self_listview);
//        mListView.setEmptyView(mListEmptyView);
//        mAdapter = new AttendSelfAdapter(getApplicationContext());
//        mListView.setAdapter(mAdapter);
//        //btnKeyboard = (Button)this.findViewById(R.id.btnKeyboard);
//        //btnRfid = (Button)this.findViewById(R.id.btnRfid);
//        btnLock = (Button)this.findViewById(R.id.attend_self_btn_lock);
//        btnOption = (Button)this.findViewById(R.id.attend_self_btn_option);
//        btnBoard = (Button)this.findViewById(R.id.attend_self_btn_board);
//
//        btnDeviceSearch = (Button) this.findViewById(R.id.attend_self_device_search);
//        btnDeviceState = (Button) this.findViewById(R.id.attend_self_device_state);
//        btnBluetoothState = (Button) this.findViewById(R.id.attend_bluetooth_state);
//        btnNfcState = (Button) this.findViewById(R.id.attend_nfc_state);
//        btnAttendSelfLabel = (Button) this.findViewById(R.id.attend_self_label);
//        btnAttendSelfSync = (Button) this.findViewById(R.id.attend_self_sync);
//        btnUserInfo = (Button) this.findViewById(R.id.user_info_btn);
//        bluetoothRefresh = (Button) this.findViewById(R.id.bluetooth_refresh);
//
//        nfcExpBtn = (Button) findViewById(R.id.nfc_exp_btn);
//        bleExpBtn = (Button) findViewById(R.id.ble_exp_btn);
//
//        nfcExpView = (View) findViewById(R.id.nfc_exp_view);
//        bleExpView = (View) findViewById(R.id.ble_exp_view);
//
//        btnDeviceSearch.setVisibility(View.VISIBLE);
//        btnDeviceState.setVisibility(View.VISIBLE);
//
//        if(StaticObj.matchSd().sGroupBCd.equals("KI00005")){
//            nMonitorAttend.setVisibility(View.GONE);
//        }
//
//        if(Build.MODEL.equals("TCC8920_EVM")){
//            btnBluetoothState.setVisibility(View.GONE);
//            bleExpBtn.setVisibility(View.GONE);
//            bleExpView.setVisibility(View.GONE);
//        }
//
//        if(Build.MODEL.equals("muPAD7")) {
//            btnDeviceSearch.setVisibility(View.INVISIBLE);
//        }
//
//        if(Build.MODEL.equals("muPADT7")){
//            btnNfcState.setVisibility(View.GONE);
//            nfcExpBtn.setVisibility(View.GONE);
//            nfcExpView.setVisibility(View.GONE);
//        }
//
//        String sAppVersion = BuildConfig.IS_EXMAN ? StaticObj.sAppVersion : StaticObj.sAppVersion_d;
//        if(Integer.parseInt(sAppVersion) < Integer.parseInt(StaticObj.sVerCodeLate)){
//            mHandler.sendEmptyMessage(NEW_NOTI_CALL);
//        }else{
//            newVersionNoti.setVisibility(View.GONE);
//        }
//
//        SharedPreferences pref = getSharedPreferences("deviceInfo", 0);
//        mDeviceName = pref.getString("nfc_device_name", "");
//        mDeviceAddress = pref.getString("nfc_device_address", "");
//
//        if(mDeviceName.equals("") || mDeviceAddress.equals("")){
//            bluetoothRefresh.setVisibility(View.GONE);
//        }
//
//        bleExpView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                View view = View.inflate(getApplicationContext(), R.layout.rn_option_new_tip_pop, null);
//                TextView tvTipText = (TextView) view.findViewById(R.id.option_tip_txt);
//                tvTipText.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticObj.getnOptionFontSize());
//                tvTipText.setText("스마트폰 블루투스 전원 'ON/OFF' 상태표시 아이콘입니다.\n전원 'ON' 상태에서 블루투스 장비 연결이 가능합니다.");
//
//                new EasyDialog(AttendSelfNewAct.this)
//                        .setLayout(view)
//                        .setBackgroundColor(Color.WHITE)
//                        .setLocationByAttachedView(btnBluetoothState)
//                        .setAnimationAlphaShow(300, 0.3f, 1.0f)
//                        .setAnimationAlphaDismiss(150, 1.0f, 0.0f)
//                        .setGravity(EasyDialog.GRAVITY_BOTTOM)
//                        .setTouchOutsideDismiss(true)
//                        .setMatchParent(false)
//                        .setMarginLeftAndRight(24, 24)
//                        .setOutsideColor(Color.parseColor("#55000000"))
//                        .show();
//            }
//        });
//
//        nfcExpView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                View view = View.inflate(getApplicationContext(), R.layout.rn_option_new_tip_pop, null);
//                TextView tvTipText = (TextView) view.findViewById(R.id.option_tip_txt);
//                tvTipText.setTextSize(TypedValue.COMPLEX_UNIT_PX, StaticObj.getnOptionFontSize());
//                tvTipText.setText("스마트폰 NFC 전원 'ON/OFF' 상태표시 아이콘입니다.\n전원 'ON' 상태에서 NFC 태그 기능을 사용할 수 있습니다.");
//
//                new EasyDialog(AttendSelfNewAct.this)
//                        .setLayout(view)
//                        .setBackgroundColor(Color.WHITE)
//                        .setLocationByAttachedView(btnNfcState)
//                        .setAnimationAlphaShow(300, 0.3f, 1.0f)
//                        .setAnimationAlphaDismiss(150, 1.0f, 0.0f)
//                        .setGravity(EasyDialog.GRAVITY_BOTTOM)
//                        .setTouchOutsideDismiss(true)
//                        .setMatchParent(false)
//                        .setMarginLeftAndRight(24, 24)
//                        .setOutsideColor(Color.parseColor("#55000000"))
//                        .show();
//
//            }
//        });
//
//        nMonitorAttend.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences pref = getSharedPreferences("deviceInfo",0);
//                SharedPreferences.Editor edit = pref.edit();
//                edit.putString("screen_mode", "M");
//                edit.commit();
//
//                startActivity(new Intent(AttendSelfNewAct.this, AttendMonitorActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//                finish();
//            }
//        });
//
////        mAttendSelfCamera = (AttendCameraSurface) findViewById(R.id.attend_self_camera);
//
//    }
//
//    private void setEvent() {
//
//        btnAttendSelfSync.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logManager.write("B", "0502", "", "", "", "");
//                // 시간 리플래쉬
//                SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId);
//            }
//        });
//
//        btnOption.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logManager.write("B", "0113", "", "", "", "");
//                if (StaticObj.bScreenLock == false) {
//                    Intent intent = new Intent(getApplicationContext(), OptionNewAct.class);
//                    intent.putExtra("BackAct", "AttendSelfNewAct");
////              intent = new Intent(MainAct.this, OptionCardRegAct.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                }
//            }
//        });
//
//        btnClose.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (BuildConfig.IS_EXMAN) {
////                    logManager.write("B", "3101", "", "", "", "");
//                    if (StaticObj.bScreenLock == false) {
//                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                    }
//                }else{
////                    logManager.write("B", "3107", "", "", "", "");
//                    if (StaticObj.bScreenLock == false) {
//                        BackPressCloseHandler.getInstance().onBackPressed(AttendSelfNewAct.this);
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                    }
//                }
//            }
//        });
//
//        edRfid.addTextChangedListener(new TextWatcher(){
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                isRfidTaging = true;
//                boolean bLengthCheck = false, bDeviceException = false, bKoreanValue = false; // 한음절이상의 한글이 포함된 경우 (04뮬8ㄸ404)
//
////                Log.e("kooj", "edRfid : "+s.toString());
//
//                if(s.length() < 9 && s.toString().contains("\n")){
//                    bLengthCheck = true;
//                    bKoreanValue = true;
//                }
//
//                if (s.length() == 9 && s.toString().contains("\n") || s.length() == 11 && s.toString().contains("\n")) {
//                    bLengthCheck = true;
//                }
//
//                if (android.os.Build.MODEL.equals("LEGEND-GT") && s.length() == 10 && s.toString().contains("\n")) {
//                    bDeviceException = true;
//                    bLengthCheck = true;
//                }
//
////                logManager.write("B", "31", "", "onTextChanged", s.toString(), "read");
//
//                if (bLengthCheck && bKeyPress == false) {
//                    isRfidTaging = false;
//                    String cardNo = edRfid.getText().toString().replace("\n", "").replace(" ", "");
//                    Log.e("kooj", "koreanToEnglish_edRfid : " + cardNo);
//                    Log.e("kooj", "onKeyValue : " + onKeyValue);
//                    if(!Util.isEnglishNum(cardNo)){
//                        cardNo = onKeyValue;
//                    }
////                    cardNo = Util.koreanToEnglish(cardNo).toUpperCase();
//                    edRfid.setText("");
//                    onKeyValue = "";
//
//                    Log.d(TAG, "cardNo = (" + cardNo + ")");
//                    //Toast.makeText(getApplicationContext(), cardNo, Toast.LENGTH_LONG).show();
//
////                    logManager.write("B", "31", "", "RFID", cardNo, "read check");
//
//                    if (cardNo.length() == 9 && bDeviceException) {
//                        cardNo = "0" + cardNo;
//                    }
//
//                    if (didTag) {
//                        Toast.makeText(getApplicationContext(), "출석중 입니다.", Toast.LENGTH_LONG).show();
//                        return;
//                    }
//
//                    if (cardNo.length() < 5) {
//                        Toast.makeText(getApplicationContext(), "카드 ID를 읽지 못했습니다. 다시 등록해 주세요", Toast.LENGTH_LONG).show();
//                    } else {
//                        sAttendType = cardNo.length() == 8 ? "N" : "R";
//                        inputMode = INPUT_RFID;
//                        mCardNumber = cardNo;
//                        SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_CUST, new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//
//                                if (isFinishing()) {
//                                    return;
//                                }
//
//                                loadData();
//                                AttendDaySyncSocCon();
//                            }
//                        });
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//
//
//        View.OnClickListener btnKeypadOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mInputNumber.length() < INPUT_DIGIT_MAX) {
//                    switch (v.getId()) {
//                        case R.id.attend_self_keypad_btn_1:
//                            mInputNumber += "1";
//                            break;
//                        case R.id.attend_self_keypad_btn_2:
//                            selPos = 0;
//                            mInputNumber += "2";
//                            break;
//                        case R.id.attend_self_keypad_btn_3:
//                            selPos = 0;
//                            mInputNumber += "3";
//                            break;
//                        case R.id.attend_self_keypad_btn_4:
//                            selPos = 0;
//                            mInputNumber += "4";
//                            break;
//                        case R.id.attend_self_keypad_btn_5:
//                            selPos = 0;
//                            mInputNumber += "5";
//                            break;
//                        case R.id.attend_self_keypad_btn_6:
//                            selPos = 0;
//                            mInputNumber += "6";
//                            break;
//                        case R.id.attend_self_keypad_btn_7:
//                            selPos = 0;
//                            mInputNumber += "7";
//                            break;
//                        case R.id.attend_self_keypad_btn_8:
//                            selPos = 0;
//                            mInputNumber += "8";
//                            break;
//                        case R.id.attend_self_keypad_btn_9:
//                            selPos = 0;
//                            mInputNumber += "9";
//                            break;
//                        case R.id.attend_self_keypad_btn_0:
//                            selPos = 0;
//                            mInputNumber += "0";
//                            break;
//                    }
//
////                    if(StaticObj.matchSd().sGroupCd.equals("KI00004961") || StaticObj.matchSd().sGroupCd.equals("KI00000123")){
////                        logManager.write("M", "3111", "", "btnKeypadOnClickListener", mInputNumber, "");
////                    }
//
//                    displayDigitText();
//                }
//
//                if (mInputNumber.length() == INPUT_DIGIT_MAX) {
//                    sAttendType = "M";
//                    inputMode = INPUT_KEYBOARD;
//                    SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_CUST, new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//
//                            if (isFinishing()) {
//                                return;
//                            }
//
//                            loadData();
//                            AttendDaySyncSocCon();
//                        }
//                    });
//                }
//            }
//        };
//
//        btnKeypad1.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad2.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad3.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad4.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad5.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad6.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad7.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad8.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad9.setOnClickListener(btnKeypadOnClickListener);
//        btnKeypad0.setOnClickListener(btnKeypadOnClickListener);
//
//        btnKeypadReset.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logManager.write("B", "3103", "", "", "", "");
//                resetAttendeeData(true);
//            }
//        });
//
//        btnKeypadDel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mInputNumber.length() > 0) {
////                    logManager.write("B", "3104", "", "", "", "");
//
//                    resetAttendeeData(false);
//                    mInputNumber = mInputNumber.substring(0,mInputNumber.length() - 1);
//                    displayDigitText();
//                }
//            }
//        });
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> view, View arg1, final int position, long arg3) {
//
//                if (bKeyPress || Util.getTopProcessActivityName(AttendSelfNewAct.this).equals("FeverBaseActivity")) {   // 중복 데이터처리 방지
//                    return;
//                }
//
//                bundleName = attendRowList.get(position).getsName();
//
//                nPos = position;
//
//                if (makeAttendData(nPos)) {
//                    if(StaticObj.matchSd().bTemperature){
//                        checkFever(nPos);
//                    }else{
//                        sendAttendData();
//                    }
//                }
//            }
//        });
//
//        btnDeviceSearch.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Build.VERSION.SDK_INT >= 18){
//                    if (initBluetooth()) {
//                        // Bluetooth 사용 가능
//                        scanLeDevice(true);
//                    }
//                }
//            }
//        });
//
//        bluetoothRefresh.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(initBluetooth()){
//                    try {
//                        if(!mDeviceName.equals("") && !mDeviceAddress.equals("")){
//                            BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).disconnectReader();
//                            BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).connectReader(mDeviceName, mDeviceAddress);
//                        }
//                    } catch (Exception e){
//                        Util.toastMessage(AttendSelfNewAct.this, "연결된 장비가 없습니다.");
//                    }
//                }else{
//                    Util.toastMessage(AttendSelfNewAct.this, "블루투스 연결에 실패했습니다.");
//                }
//            }
//        });
//
//        btnBoard.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (StaticObj.bScreenLock == false) {
////                    logManager.write("B", "3106", "", "", "", "");
//
//                    Intent intent;
//                    intent = new Intent(getApplicationContext(), AttendBoardAct.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//                    finish();
//                } else {
//                    Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                }
//            }
//        });
//
//        btnLock.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                logManager.write("B", "0514", "", "", "", "");
//
//                SharedPreferences pref = getSharedPreferences(StaticObj.matchSd().sGroupCd, Context.MODE_PRIVATE);
//                if (StaticObj.bDebug) {
//                    Log.d("test", "attend_password =>" + pref.getString("attend_lock_password", ""));
//                }
//
//                String sPassword = pref.getString("attend_lock_password", "");
//
//                if (btnLock.isSelected()) {
//                    new AttendLockDialog(AttendSelfNewAct.this, sPassword, new AttendLockDialog.AttendLockDialogDismissListener() {
//                        @Override
//                        public void dismiss(boolean bUnLock) {
//                            if (bUnLock) {
//                                btnLock.setSelected(false);
//                                StaticObj.bScreenLock = false;
//                            } else {
//                                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } else {
//                    if (TextUtils.isEmpty(sPassword)) {
//                        new AttendLockPwSettingDialog(AttendSelfNewAct.this, new AttendLockPwSettingDialog.AttendLockDialogDismissListener() {
//                            @Override
//                            public void dismiss(String sPassword) {
//                                SharedPreferences pref = getSharedPreferences(StaticObj.matchSd().sGroupCd, Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = pref.edit();
//                                editor.putString("attend_lock_password", sPassword);
//                                editor.commit();
//
//                                btnLock.setSelected(true);
//                                StaticObj.bScreenLock = true;
//
//                                MobileOptionSendCon();
//                            }
//                        });
//                    } else {
//                        btnLock.setSelected(true);
//                        StaticObj.bScreenLock = true;
//                    }
//                }
//            }
//        });
//
//        btnUserInfo.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                logManager.write("B", "0111", "", "", "", "");
//
//                Intent intent = new Intent(getApplicationContext(), MemberInfoAct.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    private void PushRemoveUserSocCon(String sGroupCd, String sAdminId, String sToday) {
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        StaticObj.sAppId = "01";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_S_PUSH_DELETE_USER_V4");
//        map.put("group_cd", sGroupCd);
//        map.put("admin_id", sAdminId);
//        map.put("today", sToday);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void MobileOptionSendCon() {
//
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_M_MOBILE_OPTION_V16");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", StaticObj.matchSd().sAdminId);
//        map.put("options", StaticObj.getMobileOption(getApplicationContext(), StaticObj.matchSd()));
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void CustFeverSendCon(String custId, String temper, String feverYn, String deviceYn) {
//
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_I_CUST_FEVER_CHECK_V2");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("cust_cd", custId);
//        map.put("check_date", Util.getTodayDate());
//        map.put("check_time", Util.dtCurrent("HH:mm"));
//        map.put("check_temp", temper);
//        map.put("check_symptom", "N");
//        map.put("check_fever", feverYn);
//        map.put("check_device", deviceYn);  // 발열측정 방법 (체온계/수기)
//        map.put("reg_user", StaticObj.matchSd().sAdminId);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private boolean makeAttendData(final int nIndex) {
//        bMakeAttend = false;
//        if(!StaticObj.matchSd().bTemperature){
//            bKeyPress = true;
//        }
//        mItem = "";
//        sAttendHomeStatus = "";
//
//        String sAuthCode = "";
//
//        if (nIndex != -1) {
//            sAuthCode = attendRowList.get(nIndex).getsAuthCode();
//            Log.d("makeAttendData", "sAuthCode[" + sAuthCode + "], nIndex(" + nIndex + ")");
//        } else {
//            alertNoti("데이터를 찾을 수 없습니다.\n관리자에게 문의하세요.[1]");
//            didTag = false;
//            bKeyPress = false;
//            return bMakeAttend;
//        }
//
//        Log.d("makeAttendData", "inputMode(" + inputMode + ")");
//
//        String sNoAttendText = "";
//        rowSelectData = null;
//        for (int i=0; i<attendRowList.size(); i++) {
//            if (attendRowList.get(i).getsAuthCode().equals(sAuthCode)) {
//
//                AttendRowData data = attendRowList.get(i);
//
//                if (data.getsAttendStatus().equals("2")) {
//                    sNoAttendText = "\n\n이미 결석처리 되었습니다.";
//                } else {
//                    //1:공용 (출석,귀가), 3:귀가
//                    if (StaticObj.matchSd().nOptionAttendLeavingInput == 1 || StaticObj.matchSd().nOptionAttendLeavingInput == 3) {
//                        if (data.getsHomeStatus().equals("3")) {
//                            sNoAttendText = "\n\n이미 귀가체크 하였습니다.";
//                        } else {
//                            if (data.getsAttendStatus().equals("1") || data.getsAttendStatus().equals("5")) {
//                                String sAttendTime = data.getsAttendTime();
//                                sAttendTime = sAttendTime.replace("-", "");
//                                sAttendTime = sAttendTime.replace(":", "");
//                                sAttendTime = sAttendTime.replace(" ", "");
//
//                                String sHomeTime = Util.dtCurrent("yyyy-MM-dd HH:mm:ss");
//                                sHomeTime = sHomeTime.replace("-", "");
//                                sHomeTime = sHomeTime.replace(":", "");
//                                sHomeTime = sHomeTime.replace(" ", "");
//
//                                String strGap;
//                                if (StaticObj.matchSd().sHomeTerm.equals("")) {
//                                    strGap = "20";
//                                } else {
//                                    strGap = StaticObj.matchSd().sHomeTerm;
//                                }
//                                if (Util.dateCompareTime2(sAttendTime, sHomeTime, strGap) == 1) {
//                                    sNoAttendText = "\n\n출석 저장 후 " + strGap + "분 후 귀가 저장을 할 수 있습니다.";
//                                }
//                            }
//                        }
//                    } else if (StaticObj.matchSd().nOptionAttendLeavingInput == 2) {
//                        if (data.getsAttendStatus().equals("1") || data.getsAttendStatus().equals("5")) {
//                            sNoAttendText = "\n\n이미 출석체크 하였습니다.";
//                        }
//                    }
//                }
//
//                rowSelectData = data;
//            }
//        }
//
//        if (rowSelectData != null) {
//            if (!TextUtils.isEmpty(sNoAttendText)) {
//                double textsize = mListEmptyView.getTextSize()*1.3;
//                SpannableStringBuilder sps = new SpannableStringBuilder(rowSelectData.getsName() + sNoAttendText);
//                sps.setSpan(new AbsoluteSizeSpan((int)textsize), 0, rowSelectData.getsName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                mListEmptyView.setText(sps);
//                resetAttendeeData(true);
//                playNoResultSound();
//                return bMakeAttend;
//            } else {
//                if (rowSelectData.getsAttendStatus().equals("") || rowSelectData.getsAttendStatus().equals("0")) {
//                    if (StaticObj.matchSd().nOptionAttendLeavingInput == 1 || StaticObj.matchSd().nOptionAttendLeavingInput == 2) {
//                        sAttendHomeStatus = "1";
//                    } else if (StaticObj.matchSd().nOptionAttendLeavingInput == 3) {
//                        sAttendHomeStatus = "3";
//                    }
//                } else if (rowSelectData.getsAttendStatus().equals("1") || rowSelectData.getsAttendStatus().equals("5")) {
//                    if (StaticObj.matchSd().nOptionAttendLeavingInput == 1 || StaticObj.matchSd().nOptionAttendLeavingInput == 3) {
//                        sAttendHomeStatus = "3";
//                    }
//                }
//
//                logManager.write("B", "3110", "", rowSelectData.getsAuthCode(), sAttendHomeStatus, "");
//                mItem += rowSelectData.getsAuthCode() + "/" + sAttendHomeStatus + "/" + sAttendReason + "|";
//                bMakeAttend = true;
//            }
//        } else {
//            alertNoti("데이터를 찾을 수 없습니다.\n관리자에게 문의하세요.[2]");
//            bundleId = "";
//            bundleName = "";
//            didTag = false;
//            bKeyPress = false;
//        }
//
//        return bMakeAttend;
//    }
//
//    private void checkFever(int nIndex){
//        AttendData attendData = new AttendData();
//
//        for (int i = 0; i < attendList.size(); i++) {
//            if(attendList.get(i).getsAuthCode().equals(attendRowList.get(nIndex).getsAuthCode())){
//                attendData = attendList.get(i);
//                break;
//            }
//        }
//
//        if(!attendData.getsAttendStatus().equals("")){
//            sendAttendData();
//            return;
//        }
//
//        Intent intent = new Intent(AttendSelfNewAct.this, FeverBaseActivity.class);
//        intent.putExtra("attend_data", attendData);
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivityForResult(intent, FEVER_RESULT);
//    }
//
//    private void sendAttendData() {
//        String sToday = mCurDate.replace(".", "-");
//
//        AttendDataSendSocCon(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, sToday, mItem);
//    }
//
//    private void resetAttendeeData(boolean bDeleteInputNumber) {
//
//        bKeyPress = false;
//        //attendList.clear();
//        attendRowList.clear();
//        mAdapter.setItem(attendRowList);
//        mAdapter.notifyDataSetChanged();
//        if (bDeleteInputNumber) {
//            mInputNumber = "";
//        }
//        displayDigitText();
//        if(!Build.MODEL.equals("TCC8920_EVM")){
//            mHandler.sendEmptyMessageDelayed(nTextChange, DELAY_SECOND);
//        }else{
//            mHandler.sendEmptyMessageDelayed(TAG_DELAY_ADPOP, DELAY_SECOND);
//        }
//    }
//
//    private void displayDigitText() {
//        String buf = "";
//
//        if (mInputNumber == null) {
//            return;
//        }
//
//        if (mInputNumber.length() == 0) {
//            buf = "";
//        } else if (mInputNumber.length() == 1) {
//            buf = mInputNumber;
//        } else if (mInputNumber.length() == 2) {
//            buf = mInputNumber.substring(0, 1) + "  "+ mInputNumber.substring(1, 2);
//        } else if (mInputNumber.length() == 3) {
//            buf = mInputNumber.substring(0, 1) + "  "+ mInputNumber.substring(1, 2) + "  "+ mInputNumber.substring(2, 3);
//        } else if (mInputNumber.length() == 4) {
//            buf = mInputNumber.substring(0, 1) + "  "+ mInputNumber.substring(1, 2) + "  "+ mInputNumber.substring(2, 3) + "  "
//                    + mInputNumber.substring(3, 4);
//        }
//
//        mKepadTextView.setText(buf);
//    }
//
//    private void loadData() {
//
////        logManager.write("B", "3108", "", "mInputNumber", mInputNumber, "");
//
//        dbHelper = DBHelper.getInstance(this);
//        String sToday = mCurDate.replace(".", "-");
//        Cursor cursor;
//        if (StaticObj.matchSd().sClassFlag.equals("H") && !StaticObj.matchSd().sPartCode.equals("")) {
//            cursor = dbHelper.select_attendee_child(StaticObj.matchSd().sGroupCd, sToday, mInputNumber, StaticObj.matchSd().sPartCode);
//        } else {
//            cursor = dbHelper.select_attendee(StaticObj.matchSd().sGroupCd, sToday, "CARD");
//        }
//
//        if (cursor != null) {
//            Log.d("findAttendee", "cursor not null");
//
//            attendList.clear();
//
//            while(!cursor.isAfterLast()) {
//
//                AttendData data = new AttendData();
//                data.setsAuthCode(cursor.getString(cursor.getColumnIndex("auth_code")));
//                //Log.d("tag", "id : " + cursor.getString(cursor.getColumnIndex("auth_code")));
//                data.setsName(cursor.getString(cursor.getColumnIndex("name")));
//                data.setsSex((cursor.getString(cursor.getColumnIndex("sex")).equals("1")) ? "남" : "여");
//                data.setsBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
//                data.setsAge(cursor.getString(cursor.getColumnIndex("age")));
//                data.setsDepart(Util.nvl(cursor.getString(cursor.getColumnIndex("part_nm"))).equals("") ? "소속없음" : cursor.getString(cursor.getColumnIndex("part_nm")));
//                data.setsTime(Util.getStringTime(cursor.getString(cursor.getColumnIndex("time_cd"))));
//                data.setsTranYn((cursor.getString(cursor.getColumnIndex("tran_yn")) == null) ? "N" : cursor.getString(cursor.getColumnIndex("tran_yn")));
//                data.setbGcmUse((cursor.getString(cursor.getColumnIndex("gcm_use")).trim().equals("1")) ? true : false);
//                data.setsPPhone((cursor.getString(cursor.getColumnIndex("pphone")) == null) ? "" : cursor.getString(cursor.getColumnIndex("pphone")));
//                data.setsProfileUrl((cursor.getString(cursor.getColumnIndex("image_url")) == null) ? "" : cursor.getString(cursor.getColumnIndex("image_url")));
//                data.setsPModel((cursor.getString(cursor.getColumnIndex("pmodel")) == null) ? "" : cursor.getString(cursor.getColumnIndex("pmodel")));
//
//                data.setsAttendStatus((cursor.getString(cursor.getColumnIndex("attend_status")) == null) ? "" : cursor.getString(cursor.getColumnIndex("attend_status")));
//                data.setsAttendSmsYn((cursor.getString(cursor.getColumnIndex("attend_sms_yn")) == null) ? "N" : cursor.getString(cursor.getColumnIndex("attend_sms_yn")));
//                data.setsAttendTime((cursor.getString(cursor.getColumnIndex("attend_reg_date")) == null) ? "" : cursor.getString(cursor.getColumnIndex("attend_reg_date")));
//
//                data.setsHomeStatus((cursor.getString(cursor.getColumnIndex("home_status")) == null) ? "" : cursor.getString(cursor.getColumnIndex("home_status")));
//                data.setsHomeSmsYn((cursor.getString(cursor.getColumnIndex("home_sms_yn")) == null) ? "N" : cursor.getString(cursor.getColumnIndex("home_sms_yn")));
//                data.setsHomeTime((cursor.getString(cursor.getColumnIndex("home_reg_date")) == null) ? "" : cursor.getString(cursor.getColumnIndex("home_reg_date")));
//
//                data.setsLunar(cursor.getString(cursor.getColumnIndex("birthday_lunar")));
//                data.setsSelfCode(cursor.getString(cursor.getColumnIndex("self_code")));
//                data.setsCardNo(cursor.getString(cursor.getColumnIndex("card_no")));
//
//                ArrayList<CustContactData> custContactDataArr = new ArrayList<>();
//                dbHelper.select_cust_contact(StaticObj.matchSd().sGroupCd, data.getsAuthCode(), custContactDataArr);
//                data.setCustContactDataArr(custContactDataArr);
//
//                attendList.add(data);
//
//                cursor.moveToNext();
//            }
//
//        } else {
//            Log.d("findAttendee", "cursor null");
//        }
//
//        // 생일 추가
//        String sSolar = "";
//        for (int i=0; i<attendList.size(); i++)
//        {
//            sSolar = dbHelper.select_attend_solar_birthday(attendList.get(i).getsBirthday(), attendList.get(i).getsLunar());
//            //Log.d("findAttendee", "name[" + attendList.get(i).getsName() + "], sSolar[" + sSolar + "], sToday[" + sToday + "]");
//            if (!sSolar.equals("") && sSolar.equals(sToday)) {
//                attendList.get(i).setbBirthday(true);
//            }
//        }
//
//        Log.d("loadData", "attendList.size(" + attendList.size() + ")");
//    }
//
//    private void findData(String sKeyValue) {
//
//        String sToday = mCurDate.replace(".", "-");
//        attendRowList.clear();
//
//        Log.d("findData", "inputMode(" + inputMode + ")");
//        Log.d("findData", "sKeyValue(" + sKeyValue + ")");
//
//        if (sKeyValue.equals("")) {
//            bKeyPress = false;
//            return;
//        }
//
//        boolean bEquals = false;
//        bRfidCard = false;
//        for (int i=0; i<attendList.size(); i++)
//        {
//            bEquals = false;
//            if (inputMode == INPUT_KEYBOARD) {
//                Log.d("findData", "name[" + attendList.get(i).getsName() + "], selfCode[" + attendList.get(i).getsSelfCode()+"]");
//                if (attendList.get(i).getsSelfCode().equals(sKeyValue)) {
//                    bEquals = true;
//                }
//            } else if (inputMode == INPUT_RFID){
//                if (sKeyValue.length() == 4) {
//                    Log.d("findData", "name[" + attendList.get(i).getsName() + "], selfCode[" + attendList.get(i).getsSelfCode()+"]");
//                    if (attendList.get(i).getsSelfCode().equals(sKeyValue)) {
//                        bEquals = true;
//                    }
//                } else {
//                    Log.d("findData", "name[" + attendList.get(i).getsName() + "], carndNo[" + attendList.get(i).getsCardNo()+"]");
//                    if (attendList.get(i).getsCardNo().equals(sKeyValue)) {
//                        bEquals = true;
//                        bRfidCard = true;
//                        logManager.write("B", "3115", "", "mCardNumber", "["+sKeyValue+"],["+attendList.get(i).getsAuthCode()+"]", "");
//                    }
//                }
//            }
//
//            if (bEquals) {
//
//                AttendRowData rowData = new AttendRowData();
//                rowData.setsAuthCode(attendList.get(i).getsAuthCode());
//                rowData.setsName(attendList.get(i).getsName());
//                rowData.setsAtdDate(sToday);
//                rowData.setsSex(attendList.get(i).getsSex());
//                rowData.setbGcmUse(attendList.get(i).getbGcmUse());
//                rowData.setsProfileUrl(attendList.get(i).getsProfileUrl());
//
//                rowData.setsAttendStatus(attendList.get(i).getsAttendStatus());
//                rowData.setsAttendSmsYn(attendList.get(i).getsAttendSmsYn());
//                rowData.setsAttendTime(attendList.get(i).getsAttendTime());
//
//                rowData.setsHomeStatus(attendList.get(i).getsHomeStatus());
//                rowData.setsHomeSmsYn(attendList.get(i).getsHomeSmsYn());
//                rowData.setsHomeTime(attendList.get(i).getsHomeTime());
//
//                rowData.setbBirthday(attendList.get(i).getbBirthday());
//                rowData.setsBirthday(attendList.get(i).getsBirthday());
//                rowData.setsLunar(attendList.get(i).getsLunar());
//                rowData.setCustContactDataArr(attendList.get(i).getCustContactDataArr());
//
//                attendRowList.add(rowData);
//            }
//        }
//
//        Log.d("findData", "attendRowList size(" + attendRowList.size() + ")");
//
//
//        bKeyPress = false;
//
//        if (attendRowList.size() > 0) {
//            logManager.write("I", "3109", "", "found", attendRowList.size() + "", sKeyValue);
//
//            mAdapter.setItem(attendRowList);
//            mAdapter.notifyDataSetChanged();
//
//            //인원이 1명일때 바로출석 처리작업
//            if (StaticObj.matchSd().nOptionAttendLeavingInputCodeType == 2 && attendRowList.size() == 1) {
//
//                //회원번호 결과가 한명인 사람을 출석혹은 귀가체크 할 때 누가 출석했는지 하단에 회원이름이 출력
//                bundleName = attendRowList.get(0).getsName();
//
////                if(StaticObj.matchSd().sChildKeyYn.equals("Y") && inputMode == INPUT_KEYBOARD){
////                    AttendReasonConn();
////                }else{
////                    if (makeAttendData("", nPos)) {
////                        sendAttendData();
////                    }
////                }
//
//                if (makeAttendData(0)) {
//                    if(StaticObj.matchSd().bTemperature){
//                        checkFever(0);
//                    }else{
//                        sendAttendData();
//                    }
//                }
//            }
//        } else {
//            logManager.write("I", "3113", "", "cust not found", "", sKeyValue);
//            if(inputMode == INPUT_RFID && (sAttendType == "N" || sAttendType == "B" || sAttendType == "R")){
//                mKeyValue = sKeyValue;
//                AttendAdminCheck(sKeyValue, mKeyValue.length() == 4 ? "N" : "C");
//            }else{
//                if (inputMode == INPUT_KEYBOARD) {
//                    showResultNotFound();
//                } else if (inputMode == INPUT_RFID){
//                    if (mKeyValue.length() == 4) {
//                        showResultNotFound();
//                    } else {
//                        sMsg = "등록되어 있지 않은 카드입니다.";
//                        playNoResultSound();
//                        bReg = false;
//                        showTagResult(bReg, sMsg, "cust");
//                    }
//                }
//            }
//        }
//    }
//
//    private void AttendDaySocCon(int nType) {
//
//        if (nType == 1) {
//        } else {
//            if (mProgressDialog == null) {
//                try {
//                    mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//                } catch(Exception e){mProgressDialog = null;}
//            }
//        }
//
//        String sToday = mCurDate.replace(".", "-");
//        // mSearchTime = sToday + " " + mCurTime.replace(" ", "");
//        mSearchTime = sToday + " 00:00:00";
//
//        Log.d("AttendDaySocCon", "mSearchTime[" + mSearchTime + "]");
//
//        StaticObj.sAppId = "31";
//        StaticObj.matchSd().sDBTime = "";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_S_ATTEND_DAY_DATA_V10");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("today", sToday);
//        // map.put("reg_date", StaticObj.matchSd().sLastConnAttend);
//        map.put("reg_date", mSearchTime);
//        map.put("reg_user", StaticObj.matchSd().sAdminId);
//        map.put("search_user", "");
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void AttendDaySyncSocCon() {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
//        String sToday = mCurDate.replace(".", "-");
//        // mSearchTime = sToday + " " + mCurTime.replace(" ", "");
//        mSearchTime = sToday + " 00:00:00";
//
//        Log.d("AttendDaySocCon", "mSearchTime[" + mSearchTime + "]");
//
//        StaticObj.sAppId = "31";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "ATTEND_DAY_DATA_SYNC");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("today", sToday);
//        // map.put("reg_date", StaticObj.matchSd().sLastConnAttend);
//        map.put("reg_date", mSearchTime);
//        map.put("reg_user", StaticObj.matchSd().sAdminId);
//        map.put("search_user", "");
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void AttendDataSendSocCon(String sGroupCd, String sAdminId, String sAtdDate,
//                                      String sItem) {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
////        logManager.write("D", "MEXM_M_ATTEND_INFO_V16");
////        logManager.write("D", "atd_date[" + sAtdDate + "]");
////        logManager.write("D", "items[" + sItem + "]");
////        logManager.write("D", "user_id[" + sAdminId + "]");
//
//        String sTime = (mCurTime.replace(" ", "")).substring(0, 5);
//
//        setValue(sAtdDate, sItem, sAdminId, sTime);
//
//        StaticObj.sAppId = "31";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_M_ATTEND_INFO_V16");
//        map.put("group_cd", sGroupCd);
//        map.put("admin_id", sAdminId);
//        map.put("atd_date", sAtdDate);
//        map.put("atd_time", sTime);
//        map.put("items", sItem);
//        map.put("attend_type", sAttendType);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void AttendDataSendRetrySocCon() {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
////        logManager.write("D", "MEXM_M_ATTEND_INFO_V16");
////        logManager.write("D", "atd_date[" + mSaveAtdDate + "]");
////        logManager.write("D", "items[" + mSaveItem + "]");
////        logManager.write("D", "user_id[" + mSaveUserId + "]");
////        logManager.write("D", "time[" + mSaveTime + "]");
//
//        StaticObj.sAppId = "31";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_M_ATTEND_INFO_V16");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", mSaveUserId);
//        map.put("atd_date", mSaveAtdDate);
//        map.put("atd_time", mSaveTime);
//        map.put("items", mSaveItem);
//        map.put("attend_type", sAttendType);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    public void sendLogout()
//    {
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        StaticObj.sAppId = "01";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_I_MOBILE_LOGOUT_V2");
//        map.put("group_cd", StaticObj.mPrtSd.sGroupCd);
//        map.put("reg_user", StaticObj.mPrtSd.sAdminId);
//        map.put("phone", StaticObj.sPhoneNum);
//        map.put("model", android.os.Build.MODEL);
//        map.put("version", BuildConfig.IS_EXMAN ? StaticObj.sAppVersion : StaticObj.sAppVersion_d);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void alertNoti(String sMsg) {
//
//        new WellDialog(AttendSelfNewAct.this, "", sMsg, getString(android.R.string.ok),
//                new WellDialog.DialogOkListener() {
//                    @Override
//                    public void onOk() {
//                    }
//                },
//                false).show();
//    }
//
//    private void alertNotiFailCheck(String sMsg) {
//
//        new WellDialog(AttendSelfNewAct.this, "", sMsg, getString(android.R.string.ok),
//                new WellDialog.DialogOkListener() {
//                    @Override
//                    public void onOk() {
//                        if (bForeGround) {
//                            mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                            bKeyPress = false;
//                        }
//                    }
//                },
//                false).show();
//    }
//
//    private void viewWarnningMsg() {
//
//        if (sWarnningRetVal.equals("01")) {
//            sWarnningRetVal = "";
//            mHandler.sendEmptyMessageDelayed(3333, 2000);
//        }
//        else if (sWarnningRetVal.equals("02")) {
//            sWarnningRetVal = "";
//            mHandler.sendEmptyMessageDelayed(4444, 2000);
//        }
//        else if (sWarnningRetVal.equals("03")) {
//            sWarnningRetVal = "";
//            mHandler.sendEmptyMessageDelayed(2222, 2000);
//        }
//
//    }
//
//    public Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            if (isFinishing()) {
//                return;
//            }
//
//            switch (msg.what) {
//                case nTextChange:
//                    mHandler.removeMessages(nTextChange);
//
//                    if (inputMode == INPUT_KEYBOARD) {
//                        mListEmptyView.setText(getString(R.string.attend_self_list_empty_label));
//                    } else {
//                        if (StaticObj.matchSd().sKeyBoard.equals("Y")) {
//                            mListEmptyView.setText(getString(R.string.attend_self_list_empty_keyboard_label));
//                        } else {
//                            mListEmptyView.setText(getString(R.string.attend_self_list_empty_card_label));
//                        }
//
//                    }
////                    mAdapter.notifyDataSetChanged();
//                    break;
//                case TAG_DELAY_ADPOP:
//                    mHandler.removeMessages(TAG_DELAY_ADPOP);
//                    if(!Util.isServiceRunning(getApplicationContext(), NfcService.class)){
//                        StaticObj.startNfcService(getApplicationContext());
//                    }
//                    mHandler.sendEmptyMessage(nTextChange);
//                    break;
//                case 100:
//                    viewGetTime();
//                    mHandler.sendEmptyMessageDelayed(100, 1000);
//                    break;
////                case 101:
////                    int nPos = msg.arg1;
////                    if (makeAttendData("", nPos)) {
////                        sendAttendData();
////                    }
////                    break;
//                case 1111:
//                    Log.d("mHandler", "timeCheck[" + Util.dtCurrent("HH:mm:ss") + "]");
//                    if (StaticObj.matchSd().bFailNoti) {
//                        mHandler.removeMessages(1111);
//                        if (bKeyPress) {
//                            mHandler.sendEmptyMessageDelayed(1111, 1000 * 6);
//                        } else {
//                            if (mInputNumber.length() == 0) {
//                                failMessageCheckSocCon();
//                            } else {
//                                bKeyPress = false;
//                                mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                            }
//                        }
//                    }
//                    break;
//                case HttpClientRequest.RETURN_ATTEND_SAVE_SOC:
//                {
//                    Object[] objs = (Object[]) msg.obj;
//                    String szTmp[] = ((String) objs[0]).split("[|]");
//                    if (szTmp.length >= 1) {
//                        sWarnningRetVal = szTmp[0];
//                    }
//                    if (szTmp.length >= 2) {
//                        sWarnningMsg = szTmp[1];
//                    }
//
//                    if (sWarnningRetVal.equals("03")) {
//                        closeProgressDialog();
//                        didTag = false;
//                        bKeyPress = false;
//                        mHandler.postDelayed(mRunnableInputReset, 1000);
//                        viewWarnningMsg();
//                        return;
//                    } else if (sWarnningRetVal.equals("04")) {
//                        closeProgressDialog();
//                        didTag = false;
//                        bKeyPress = false;
//                        mHandler.postDelayed(mRunnableInputReset, 1000);
//                        Toast.makeText(getApplicationContext(), sWarnningMsg, Toast.LENGTH_SHORT).show();
//                        return;
//                    } else if (sWarnningRetVal.equals("05")) {
//                        closeProgressDialog();
//                        didTag = false;
//                        bKeyPress = false;
//                        mHandler.postDelayed(mRunnableInputReset, 1000);
//                        new WellDialog(AttendSelfNewAct.this, "", sWarnningMsg, "확인",
//                                new WellDialog.DialogOkListener() {
//                                    @Override
//                                    public void onOk() {
//                                    }
//                                }, false)
//                                .show();
//                        return;
//                    }
//
//                    attendResultList.clear();
//                    attendResultList = (ArrayList<AttendResultData>) objs[1];
//
//                    if (rowSelectData != null) {
//
//                        for (int i=0; i<attendResultList.size(); i++) {
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), cust_cd[" + attendResultList.get(i).getsCustCd() + "]");
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), phoneNum[" + attendResultList.get(i).getsPhoneNum() + "]");
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), gcmIdLen(" + attendResultList.get(i).getnGcmLength() + ")");
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), sector[" + attendResultList.get(i).getsSector() + "]");
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), attend[" + attendResultList.get(i).getsAttend() + "]");
//                            Log.d("RETURN_ATTEND_SAVE_SOC", "i(" + i + "), sms_rcv_yn[" + attendResultList.get(i).getsSmsRcvYn() + "]");
//
//
//                            if (attendResultList.get(i).getsCustCd().equals(rowSelectData.getsAuthCode())) {
//
//                                if (attendResultList.get(i).getsAttend().equals(sAttendHomeStatus)) {
//
//                                    for (int k = 0; k < rowSelectData.getCustContactDataArr().size(); k++) {
//                                        if (attendResultList.get(i).getsSector().equals(rowSelectData.getCustContactDataArr().get(k).getsSector())) {
//
//                                            if (attendResultList.get(i).getnGcmLength() < 80) {
//                                                // 문자 발송 해야함
//                                            } else {
//
//                                                if (!rowSelectData.getCustContactDataArr().get(k).getsGcmUse().equals("1")) {
//                                                    dbHelper = DBHelper.getInstance(getApplicationContext());
//                                                    if (attendResultList.get(i).getsSector().equals("CPN1")) {// cust_t에 업데이트 하기
//                                                        rowSelectData.setbGcmUse(true);
//                                                        rowSelectData.setsPModel(attendResultList.get(i).getsPhoneModel());
//                                                        dbHelper.updateCustGcmData(StaticObj.matchSd().sGroupCd, attendResultList.get(i).getsCustCd(), "1", attendResultList.get(i).getsPhoneModel());
//                                                        for (int l=0; l<attendList.size(); l++) {
//                                                            if (attendList.get(l).getsAuthCode().equals(rowSelectData.getsAuthCode())) {
//                                                                attendList.get(l).setsPModel(attendResultList.get(i).getsPhoneModel());
//                                                                attendList.get(l).setbGcmUse(rowSelectData.getbGcmUse());
//                                                                break;
//                                                            }
//                                                        }
//                                                    }
//                                                    rowSelectData.getCustContactDataArr().get(k).setsGcmUse("1");
//                                                    rowSelectData.getCustContactDataArr().get(k).setsPModel(attendResultList.get(i).getsPhoneModel());
//                                                    dbHelper.updateCustContactGcmData(StaticObj.matchSd().sGroupCd, attendResultList.get(i).getsCustCd(), "1", attendResultList.get(i).getsPhoneModel(), attendResultList.get(i).getsSector());
//                                                }
//                                            }
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    initValue();
//
//                    Log.d("RETURN_ATTEND_SAVE_SOC", "bOptionAttendSms("
//                            + StaticObj.matchSd().bOptionAttendSMS + "), bOptionLeavingSMS("
//                            + StaticObj.matchSd().bOptionLeavingSMS + "), isGcmUser("
//                            + isGcmUser() + ")");
//
//                    logManager.write("D", "bOptionAttendSms("
//                            + StaticObj.matchSd().bOptionAttendSMS + "), bOptionLeavingSMS("
//                            + StaticObj.matchSd().bOptionLeavingSMS + "), isGcmUser("
//                            + isGcmUser() + ")");
//
//                    boolean bSmsNoneCheck = false;
//
//                    if (StaticObj.matchSd().bOptionAttendSMS || StaticObj.matchSd().bOptionLeavingSMS) {
//
//                        bSmsNoneCheck = true;
//
//                        if (StaticObj.bCertiPhone) {
//                            sendAttendSms();
//                        } else {	// 공기계 일경우
//                            if(StaticObj.matchSd().sSmsSendType.equals("P") || StaticObj.matchSd().sSmsSendType.equals("S")) {
//                                sendByPass();
//                            } else {
//                                bSmsNoneCheck = false;
//                            }
//                        }
//                    }
//
//                    if (!bSmsNoneCheck) {
//                        closeProgressDialog();
//
//                        if( bundleName != null && bundleName.length() >0) {
//                            //playSendingSucceedSound();
//                            playSound();
//
//                            if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                                sMsg = bundleName + "의 출석이 완료됐습니다.";
//                                bReg = true;
//                                showTagResult(bReg, sMsg, "cust");
//                            } else if (sAttendHomeStatus.equals("3")) {
//                                sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                                bReg = true;
//                                showTagResult(bReg, sMsg, "cust");
//                            }
//                            bundleName = "";
//
//                        } else {
//                            showSendingSucceed();
//                        }
//
//                        didTag = false;
//                        bKeyPress = false;
//
//                        viewWarnningMsg();
//                    }
//
//                    nPos = 0;
//                }
//                break;
//                case HttpClientRequest.RETURN_ATTEND_SAVE_ERR:
//                    nRetryCnt++;
//                    if (nRetryCnt < 3)
//                    {
//                        AttendDataSendRetrySocCon();
//                    }
//                    else
//                    {
//                        closeProgressDialog();
//
//                        Toast.makeText(getApplicationContext(), "저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
//                        didTag = false;
//                        bKeyPress = false;
//                    }
//                    break;
//                case HttpClientRequest.RETURN_SMS_BY_PASS_SOC:
//                    adminPushSendSocCon();
//                    break;
//                case HttpClientRequest.RETURN_ADMIN_BYPASS_PUSH_SOC:
//                {
//                    closeProgressDialog();
//
//                    if (StaticObj.resultList.size() == 0) { // 에러 report IndexOutOfBoundException 발생
//                        playNoResultSound();
//
//                        bundleName = "";
//                        didTag = false;
//                        bKeyPress = false;
//
//                        return;
//                    }
//
//                    if (StaticObj.resultList.get(0).getbSendResult() == false) {
//
//                        if (StaticObj.bCertiPhone) {
//                            playNoResultSound();
//                        } else {	// 공기계
//                            if( bundleName != null && bundleName.length() >0) {
//                                //playSendingSucceedSound();
//                                playSound();
//                                if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                                    sMsg = bundleName + "의 출석이 완료됐습니다.";
//                                    bReg = true;
//                                    showTagResult(bReg, sMsg, "cust");
//                                } else if (sAttendHomeStatus.equals("3")) {
//                                    sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                                    bReg = true;
//                                    showTagResult(bReg, sMsg, "cust");
//                                }
//                                bundleName = "";
//                            } else {
//                                showSendingSucceed();
//                            }
//                        }
//                        Toast.makeText(getApplicationContext(), "전달 되었습니다.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        if( bundleName != null && bundleName.length() >0) {
//                            //playSendingSucceedSound();
//                            playSound();
//                            if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                                sMsg = bundleName + "의 출석이 완료됐습니다.";
//                                bReg = true;
//                                showTagResult(bReg, sMsg, "cust");
//                            } else if (sAttendHomeStatus.equals("3")) {
//                                sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                                bReg = true;
//                                showTagResult(bReg, sMsg, "cust");
//                            }
//                            bundleName = "";
//                        } else {
//                            showSendingSucceed();
//                        }
//                    }
//                    didTag = false;
//                    bKeyPress = false;
//
//                    viewWarnningMsg();
//                }
//                break;
//
//                case HttpClientRequest.RETURN_SMS_BY_PASS_ERR:
//                case HttpClientRequest.RETURN_ADMIN_BYPASS_PUSH_ERR:
//                {
//                    closeProgressDialog();
//
//                    Toast.makeText(getApplicationContext(), "전달 되었습니다.[E]",	Toast.LENGTH_SHORT).show();
//                    didTag = false;
//                    bKeyPress = false;
//                    viewWarnningMsg();
//                }
//                break;
//                case HttpClientRequest.RETURN_MESSAGE_SEND_SOC:
//                case HttpClientRequest.RETURN_MESSAGE_SEND_ERR:
//                    break;
//                case HttpClientRequest.RETURN_SMS_RESULT_SOC:
//                case HttpClientRequest.RETURN_SMS_RESULT_ERR:
//                    break;
//
//                case HttpClientRequest.RETURN_ATTEND_DAY_SOC:
//                {
//                    closeProgressDialog();
//                    ArrayList<AttendRowData> attendRefreshList = (ArrayList<AttendRowData>) msg.obj;
//
//                    dbHelper = DBHelper.getInstance(getApplicationContext());
//                    dbHelper.delete_attend_all(StaticObj.matchSd().sGroupCd);
//                    dbHelper.insert_attend_v2(StaticObj.matchSd().sGroupCd, attendRefreshList);
//                    loadData();
//
//                    if (bAttendCheck) {
//                        bAttendCheck = false;
//                        closeProgressDialog();
//                        if (bForeGround) {
//                            if (sFailMsg.length() > 5) {
//                                alertNotiFailCheck(sFailMsg);
//                            } else {
//                                mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                                bKeyPress = false;
//                            }
//                        } else {
//                            bKeyPress = false;
//                        }
//                        return;
//                    }
//
//                    bKeyPress = false;
//
//                    if (!bFirstSearch) {
//                        bFirstSearch = true;
//                        if (!StaticObj.matchSd().bWarningAttendSms && (!StaticObj.matchSd().bOptionAttendSMS && !StaticObj.matchSd().bOptionLeavingSMS)) {
//                            mHandler.sendEmptyMessageDelayed(7777, 1000);
//                        }
//                    }
//
//                    if (StaticObj.matchSd().bFailNoti) {
//                        mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                    }
//                }
//                break;
//                case HttpClientRequest.RETURN_ATTEND_DAY_ERR:
//
//                    if (bAttendCheck) {
//                        bAttendCheck = false;
//                        closeProgressDialog();
//                        if (bForeGround) {
//                            if (sFailMsg.length() > 5) {
//                                alertNotiFailCheck(sFailMsg);
//                            } else {
//                                mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                                bKeyPress = false;
//                            }
//                        } else {
//                            bKeyPress = false;
//                        }
//                        return;
//                    }
//
//                    closeProgressDialog();
//                    bKeyPress = false;
//
//                    if (!bFirstSearch) {
//                        bFirstSearch = true;
//                        if (!StaticObj.matchSd().bWarningAttendSms && (!StaticObj.matchSd().bOptionAttendSMS && !StaticObj.matchSd().bOptionLeavingSMS)) {
//                            mHandler.sendEmptyMessageDelayed(7777, 1000);
//                        }
//                    }
//                    if (StaticObj.matchSd().bFailNoti) {
//                        mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                    }
////                    timeCheck();
//                    break;
//                case HttpClientRequest.RETURN_ATTEND_DAY_SYNC_SOC:
//                {
//                    closeProgressDialog();
//                    ArrayList<AttendRowData> attendRefreshList = (ArrayList<AttendRowData>) msg.obj;
//
//                    dbHelper = DBHelper.getInstance(getApplicationContext());
//                    dbHelper.delete_attend_all(StaticObj.matchSd().sGroupCd);
//                    dbHelper.insert_attend_self(StaticObj.matchSd().sGroupCd, attendRefreshList);
//                    loadData();
//
//                    if (inputMode == INPUT_KEYBOARD) {
//                        findData(mInputNumber);
//                    } else if (inputMode == INPUT_RFID){
//                        findData(mCardNumber);
//                    }
//                    if (bInputCheck) {
//                        if (sInputNum.equals("8")) {
//                            if (selPos > 0) {
//                                selPos--;
//                                mAdapter.setSelPos(selPos);
//                                mAdapter.notifyDataSetChanged();
//                            }
//                        } else if (sInputNum.equals("2")) {
//                            if (selPos < attendList.size() - 1) {
//                                selPos++;
//                                mAdapter.setSelPos(selPos);
//                                mAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//                        bInputCheck = false;
//                        sInputNum = "";
//                        bKeyPress = false;
//
//                        mListView.setSelection(selPos);
//                    }
//                }
//                break;
//                case HttpClientRequest.RETURN_ATTEND_DAY_SYNC_ERR:
//                    closeProgressDialog();
//                    bKeyPress = false;
//                    bInputCheck = false;
//                    sInputNum = "";
//                    if (inputMode == INPUT_KEYBOARD) {
//                        findData(mInputNumber);
//                    } else if (inputMode == INPUT_RFID){
//                        findData(mCardNumber);
//                    }
//                    break;
//                case HttpClientRequest.RETURN_MOBILE_OPTION_UPDATE_SOC:
//                case HttpClientRequest.RETURN_MOBILE_OPTION_UPDATE_ERR:
//                    closeProgressDialog();
//                    bKeyPress = false;
//                    break;
//                case HttpClientRequest.RETURN_FAIL_MESSAGE_SOC: {
//                    String szTmp[] = ((String)msg.obj).split("[|]");
//                    String szRetCount = "", szRetMsg = "", szDelaySec = "";
//                    if (szTmp.length >= 1) {
//                        szRetCount = szTmp[0];
//                    }
//                    if (szTmp.length >= 2) {
//                        szRetMsg = szTmp[1];
//                    }
//                    if (szTmp.length >= 3) {
//                        szDelaySec = szTmp[2];
//                        try {
//                            mDelaySec = Integer.parseInt(szDelaySec);
//                        } catch(Exception e) {
//                            mDelaySec = 60;
//                        }
//                    } else {
//                        mDelaySec = 60;
//                    }
//                    if (szRetCount.equals("0")) {
//                        bAttendCheck = false;
//
//                        closeProgressDialog();
//                        String sMsg = szRetMsg;
//                        if (bForeGround) {
//                            if (sMsg.length() > 5) {
//                                alertNotiFailCheck(sMsg);
//                            } else {
//                                mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                                bKeyPress = false;
//                            }
//                        } else {
//                            bKeyPress = false;
//                        }
//
//                    } else {
//                        bAttendCheck = true;
//                        sFailMsg = szRetMsg;
//
//                        AttendDaySocCon(1);
//                    }
//                }
//                break;
//                case HttpClientRequest.RETURN_FAIL_MESSAGE_ERR:
//                    String szTmp[] = ((String)msg.obj).split("[|]");
//                    String szRetCount = "", szRetMsg = "", szDelaySec = "";
//                    if (szTmp.length >= 1) {
//                        szRetCount = szTmp[0];
//                    }
//                    if (szTmp.length >= 2) {
//                        szRetMsg = szTmp[1];
//                    }
//                    if (szTmp.length >= 3) {
//                        szDelaySec = szTmp[2];
//                        try {
//                            mDelaySec = Integer.parseInt(szDelaySec);
//                        } catch(Exception e) {
//                            mDelaySec = 60;
//                        }
//                    } else {
//                        mDelaySec = 60;
//                    }
//                    if (szRetCount.equals("0")) {
//                        bAttendCheck = false;
//
//                        closeProgressDialog();
//                        bKeyPress = false;
//                        if (bForeGround) {
//                            mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//                        }
//
//                    } else {
//                        bAttendCheck = false;
//
//                        AttendDaySocCon(1);
//                    }
//                    break;
//                case HttpClientRequest.RETURN_MOBILE_LOGOUT_SOC:
//                case HttpClientRequest.RETURN_MOBILE_LOGOUT_ERR:
//
//                    closeProgressDialog();
//
//                    appFinish();
//                    break;
//                case HttpClientRequest.RETURN_SYNC_DATE_SOC:
//                    Log.d("handleMessage", "SyncSocket.RETURN_SYNC_DATE_SOC");
//                    //closeProgressDialog();
////                    SyncDate syncServerDate = (SyncDate)msg.obj;
////                    testSyncDate(syncServerDate);
//                    //getGroupData();
//                    loadData();
//                    closeProgressDialog();
//                    Toast.makeText(getApplicationContext(), "동기화 되었습니다.", Toast.LENGTH_SHORT).show();
//                    break;
//                case HttpClientRequest.RETURN_PUSH_DELETE_USER_SOC:
//                    saveDeleteDate();
//
//                    removeUserList = (ArrayList<PushRemoveUserData>) msg.obj;
//
////                    logManager.write("P", "0119", "", "", "", "N]PushRemoveAct");
//
//                    Intent intent;
//                    intent = new Intent(getApplicationContext(), PushRemoveAct.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                    intent.putParcelableArrayListExtra("user_data", removeUserList);
//                    startActivity(intent);
//                    break;
//                case HttpClientRequest.RETURN_PUSH_DELETE_USER_ERR:
//                    saveDeleteDate();
//                    break;
//                case HttpClientRequest.RETURN_MOBILE_NOTICE_SOC:
//                    closeProgressDialog();
//
//                    String sContentUrl = StaticObj.serviceDomain + "/Api/View/NoticePopup.aspx?groupcd=" + StaticObj.mPrtSd.sGroupCd + "&userid=" + StaticObj.mPrtSd.sAdminId + "&srvflag=EA";
//                    //String sContentUrl = "http://202.158.144.167:13000/Api/View/NoticePopup.aspx?groupcd=" + StaticObj.sGroupCd + "&userid=" + StaticObj.sAdminId + "&srvflag=EA";
//                    String sViewType = "1";
//
//                    if (Util.StringToInt((String) msg.obj) > 0) {
//                        popupNotice(sContentUrl, sViewType, true);
//                    } else {
//                        popupNotice(sContentUrl, sViewType, false);
//                    }
//                    break;
//                case HttpClientRequest.RETURN_MOBILE_NOTICE_ERR:
//                    closeProgressDialog();
//                    break;
//                case HttpClientRequest.RETURN_CUST_POINT_SOC:
//                    if(bReg == false){
//                        textTagResult.setText(sMsg);
//                        break;
//                    }
//                    mStudentPointData.clear();
//                    mStudentPointData = (ArrayList<StudentPointData>) msg.obj;
//
//                    String sCustPoint = "0";
//                    if(mStudentPointData.size() != 0){
//                        if(!mStudentPointData.get(0).getsPoint().equals("")){
//                            sCustPoint = mStudentPointData.get(0).getsPoint();
//                        }
//                    }
//
//                    Configuration config = getResources().getConfiguration();
//                    if (mStudentPointData.size() != 0) {
//                        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
//                            textTagResult.setText(sMsg + "(" + sCustPoint + "포인트)");
//                        }else{
//                            textTagResult.setText(sMsg + "\n(" + sCustPoint + "포인트)");
//                        }
//                    } else {
//                        textTagResult.setText(sMsg);
//                    }
//                    break;
////                case HttpClientRequest.RETURN_ATTEND_REASON_SOC:
////                    sAttendReason = "0";
////                    closeProgressDialog();
////
////                    final ArrayList<String> reasonCd = new ArrayList<>();
////                    final ArrayList<String> reasonMsg = new ArrayList<>();
////
////                    ArrayList<HashMap<String, String>> dataMaps = (ArrayList<HashMap<String, String>>)msg.obj;
////
////                    for (int i = 0; i < dataMaps.size(); i++) {
////                        reasonCd.add(String.valueOf(i+1));    // reasonCd 값이 '1' 부터 시작 >> 사유 (1,2,3,4)
////                        reasonMsg.add(dataMaps.get(i).get(String.valueOf(i+1)));
////                    }
////
////                    new AlertDialog.Builder(AttendSelfNewAct.this)
////                            .setTitle("출석&귀가 사유선택")
////                            .setItems(reasonMsg.toArray(new String[reasonMsg.size()]), new DialogInterface.OnClickListener() {
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
////
////                                    sAttendReason = reasonCd.get(which);
////
////                                    if (makeAttendData(nPos)) {
////                                        if(StaticObj.matchSd().bTemperature){
////                                            checkFever(nPos);
////                                        }else{
////                                            sendAttendData();
////                                        }
////                                    }
////
////                                    Util.toastMessage(AttendSelfNewAct.this, reasonMsg.get(which));
////                                }
////                            }).show();
////
////                    break;
//                case HttpClientRequest.RETURN_ATTEND_REASON_ERR:
//                    Util.toastMessage(AttendSelfNewAct.this, "출결사유 조회에 실패했습니다.");
//                    break;
//                case HttpClientRequest.RETURN_ADMIN_ATTEND_CHECK_SOC:
//                    closeProgressDialog();
//
//                    String sTmp[] = ((String) msg.obj).split("[|]");
//                    String sRetVal = "";
//                    String sRetMsg = "";
//                    String sImgUrl = "";
//                    adminImgUrl = "";
//
//                    if (sTmp.length >= 1) {
//                        sRetVal = sTmp[0];
//                    }
//                    if (sTmp.length >= 2) {
//                        sRetMsg = sTmp[1];
//                    }
//                    if (sTmp.length >= 3) {
//                        sImgUrl = sTmp[2];
//                        adminImgUrl = sImgUrl;
//                    }
//
//                    if(!sRetVal.equals("03")){
//                        if(sRetVal.equals("00")){
//                            if (StaticObj.matchSd().bOptionSoundAttending) {
//                                playAttendingSound();
//                            } else {
//                                playSendingSucceedSound();
//                            }
//                        }else if (sRetVal.equals("01")){
//                            if (StaticObj.matchSd().bOptionSoundLeaving) {
//                                playLeavingSound();
//                            } else {
//                                playSendingSucceedSound();
//                            }
//                        }else{
//                            playNoResultSound();
//                        }
//
//                        showTagResult(true, sRetMsg, "admin");
//                    }else{
//                        if (mKeyValue.length() == 4) {
//                            showResultNotFound();
//                        } else {
//                            sMsg = "등록되어 있지 않은 카드입니다.";
//                            playNoResultSound();
//                            bReg = false;
//                            showTagResult(bReg, sMsg, "cust");
//                        }
//                    }
//
//                    break;
//
//                case HttpClientRequest.RETURN_CUST_FEVER_SOC:
//                    closeProgressDialog();
//                    sendAttendData();
//                    break;
//
//                case HttpClientRequest.RETURN_CUST_FEVER_ERR:
//                    closeProgressDialog();
//                    String retMsg = ((String)msg.obj);
//                    Util.toastMessage(AttendSelfNewAct.this, retMsg);
//                    break;
//
//                case NEW_NOTI_CALL:
//                    newVersionNoti.setVisibility(newVersionNoti.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
//                    mHandler.sendEmptyMessageDelayed(NEW_NOTI_CALL, 1000);
//                    break;
//                case 2222:
//                    if (sWarnningMsg.length() > 1) {
//                        new WellDialog(AttendSelfNewAct.this, "", sWarnningMsg, getString(android.R.string.ok),
//                                new WellDialog.DialogOkListener() {
//                                    @Override
//                                    public void onOk() {
//                                        AttendDaySocCon(0);
//                                    }
//                                },
//                                false).show();
//                    }
//                    break;
//                case 3333:
//                    if (sWarnningMsg.length() > 1) {
//                        Toast.makeText(getApplicationContext(), sWarnningMsg, Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                case 4444:
//                    if (sWarnningMsg.length() > 1) {
//                        alertNoti(sWarnningMsg);
//                    }
//                    break;
//                case 5555:  // Server 시간과 Local 시간 비교 (1시간 차이가 나면 경고 팝업)
//                    timeDifferentWarnning();
//                    break;
//                case 7777:
//                    viewWarning();
//                    break;
//                case 8888:
//                    String sMsg = "[메인메뉴] > [동기화] 후에 사용 해주세요.";
//                    Toast.makeText(getApplicationContext(), sMsg, Toast.LENGTH_LONG).show();
//                case 9999:
//                    String sMsg2 = "[회원관리] > [원생그룹] 에서 원생을 추가 해주세요.";
//                    Toast.makeText(getApplicationContext(), sMsg2, Toast.LENGTH_LONG).show();
//                    break;
//                case -1:
//                    Log.d("RETURN_ATTEND -1", "inin");
//                    closeProgressDialog();
//                    bKeyPress = false;
//                    break;
//                default:
//                    Log.d("RETURN_ATTEND default", "inin");
//                    closeProgressDialog();
//                    bKeyPress = false;
//                    break;
//            }
//        }
//    };
//
//    /*private void refreshAttendList(ArrayList<AttendRowData> list) {
//
//        for (int k=0; k<list.size(); k++)
//        {
//            for (int i=0; i<attendList.size(); i++)
//            {
//
//                if (attendList.get(i).getsAuthCode().equals(list.get(k).getsAuthCode())) {
//                    attendList.get(i).setsTranYn(list.get(k).getsTranYn());
//
//                    if (list.get(k).getsStatus().equals("1") || list.get(k).getsStatus().equals("2")) {
//                        attendList.get(i).setsAttendStatus(list.get(k).getsStatus());
//                        attendList.get(i).setsAttendSmsYn(list.get(k).getsSmsYn());
//                        attendList.get(i).setsAttendTime(list.get(k).getsAttendTime());
//                        attendList.get(i).setsAttendTranYn(list.get(k).getsAttendTranYn());
//                    } else if (list.get(k).getsStatus().equals("3")) {
//                        attendList.get(i).setsHomeStatus(list.get(k).getsHomeStatus());
//                        attendList.get(i).setsHomeSmsYn(list.get(k).getsHomeSmsYn());
//                        attendList.get(i).setsHomeTime(list.get(k).getsHomeTime());
//                        attendList.get(i).setsHomeTranYn(list.get(k).getsHomeTranYn());
//                    }
//
//                    if ((attendList.get(i).getsAttendStatus().equals("1") || attendList.get(i).getsAttendStatus().equals("5"))
//                            && attendList.get(i).getsAttendTranYn().equals("Y")
//                            && !attendList.get(i).getsHomeStatus().equals("3")) {
//                        attendList.get(i).setsAttend(attendList.get(i).getsAttendStatus());
//                    }
//                    if (attendList.get(i).getsAttendStatus().equals("2")){
//                        attendList.get(i).setsAttend(attendList.get(i).getsAttendStatus());
//                    }
//                    if (attendList.get(i).getsHomeStatus().equals("3") && attendList.get(i).getsHomeTranYn().equals("Y")) {
//                        attendList.get(i).setsAttend(attendList.get(i).getsHomeStatus());
//                    }
//                    break;
//                }
//            }
//        }
//    }*/
//
//    private void closeProgressDialog() {
//        if (mProgressDialog != null) {
//            try {
//                mProgressDialog.dismiss();
//            } catch (Exception e) {
//            }
//            mProgressDialog = null;
//        }
//    }
//
//    private boolean isGcmUser() {
//
//        if (attendList.size() == 0) {
//            return false;
//        }
//        for (int i = 0; i < attendList.size(); i++) {
//            if (!attendList.get(i).getbGcmUse()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void viewWarning() {
//
//        new WellDialog(AttendSelfNewAct.this, "알림!!", "출석 SMS 발송 OFF 설정 상태입니다.\n 상태 변경은 [옵션] > [출석메시지 설정]에서 변경 가능합니다.",
//                "다시보지않기", getString(android.R.string.ok),
//                new WellDialog.DialogOkListener() {
//                    @Override
//                    public void onOk() {
//                        StaticObj.matchSd().bWarningAttendSms = true;
//                        SharedPreferences pref = getSharedPreferences(StaticObj.matchSd().sGroupCd, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor edit = pref.edit();
//                        edit.putBoolean("warning_atd_sms", StaticObj.matchSd().bWarningAttendSms);
//                        edit.commit();
//                    }
//                },
//                new WellDialog.DialogCancelListener() {
//                    @Override
//                    public void onCancel() {
//                    }
//                }, false).show();
//    }
//
////    private void timeCheck() {
////
////        String sNowTime = Util.dtCurrent("yyyyMMddHHmmss");
////
////        if (!StaticObj.matchSd().sDBTime.equals("") && StaticObj.matchSd().sDBTime.length() > 13) {
////            String sServerTime = StaticObj.matchSd().sDBTime;
////            sServerTime = sServerTime.replace("-", "");
////            sServerTime = sServerTime.replace(":", "");
////            sServerTime = sServerTime.replace(" ", "");
////
////            if (Util.dateCompareTime2(sNowTime, sServerTime, "20") == 1) {
////
////            } else {
////                mHandler.sendEmptyMessageDelayed(5555, 1000);
////            }
////        }
////    }
//
//    private void timeDifferentWarnning() {
//
//        new WellDialog(AttendSelfNewAct.this, "알림!!", "현재 핸드폰 시간이 한국 표준시와 30분 이상 차이가 납니다. 핸드폰 시간을 변경해주세요.",
//                "시간변경",
//                new WellDialog.DialogOkListener() {
//                    @Override
//                    public void onOk() {
//                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
//                        startActivity(intent);
//                        finish();
//                        startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
//                    }
//                }, false).show();
//    }
//
//    private void sendByPass() {
//
//        StaticObj.resultList.clear();
//
//        try {
//            if (rowSelectData == null || TextUtils.isEmpty(rowSelectData.getsAuthCode()) || rowSelectData.getsAuthCode().equals("")) {
//                closeProgressDialog();
//                didTag = false;
//                bKeyPress = false;
//                viewWarnningMsg();
//                return;
//            }
//        } catch(Exception e) {
//            closeProgressDialog();
//            didTag = false;
//            bKeyPress = false;
//            viewWarnningMsg();
//            return;
//        }
//
//        for (int i = 0; i < attendResultList.size(); i++) {
//
//            if (attendResultList.get(i).getnGcmLength() < 60) {
//                if (attendResultList.get(i).getsCustCd().equals(rowSelectData.getsAuthCode())) {
//
//                    String szMsg = attendResultList.get(i).getsMsg();
//
//                    AttendSmsResultData data = new AttendSmsResultData();
//                    data.setsAuthCode(attendResultList.get(i).getsCustCd());
//                    data.setsSector(attendResultList.get(i).getsSector());
//                    data.setsSendPhone(attendResultList.get(i).getsPhoneNum());
//                    data.setsName(rowSelectData.getsName());
//                    data.setsSendMsg(szMsg);
//                    data.setsSendSubject(szMsg.substring(0, szMsg.length() >= 10 ? 10 : szMsg.length()));
//                    data.setbSendResult(false);
//                    if (BuildConfig.IS_DEPRECATED_SMS || StaticObj.mPrtSd.bMMSOnly || getMsgLength(szMsg) > 80) {
//                        data.setsSection1("MMS");
//                    } else {
//                        data.setsSection1("SMS");
//                    }
//                    data.setsSendGroup("");
//
//                    if (rowSelectData.getsAttendSmsYn().equals("N")) {
//
//                        data.setsSection2("ATT");
//                        data.setsAttendType("1");
//                        if (data.getsSendPhone().equals("01000000000") || data.getsSendPhone().equals("00000000000") || data.getsSendPhone().equals("0100000000")) {
//                        } else {
//                            if (StaticObj.matchSd().bSmsCheck) {
//                                if (StaticObj.matchSd().bOptionAttendSMS && !StaticObj.matchSd().bOptionAttendPush) {
//                                    StaticObj.resultList.add(data);
//                                } else if (StaticObj.matchSd().bOptionAttendSMS && StaticObj.matchSd().bOptionAttendPush) {
//                                    if (!attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                        StaticObj.resultList.add(data);
//                                    }
//                                }
//                            } else {
//                                if (StaticObj.matchSd().bOptionAttendSMS && !attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                    StaticObj.resultList.add(data);
//                                }
//                            }
//                        }
//                    } else if (rowSelectData.getsHomeSmsYn().equals("N")) {
//
//                        data.setsSection2("HOM");
//                        data.setsAttendType("3");
//                        if (data.getsSendPhone().equals("01000000000") || data.getsSendPhone().equals("00000000000") || data.getsSendPhone().equals("0100000000")) {
//                        } else {
//                            if (StaticObj.matchSd().bSmsCheck) {
//                                if (StaticObj.matchSd().bOptionLeavingSMS && !StaticObj.matchSd().bOptionLeavingPush) {
//                                    StaticObj.resultList.add(data);
//                                } else if (StaticObj.matchSd().bOptionLeavingSMS && StaticObj.matchSd().bOptionLeavingPush) {
//                                    if (!attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                        StaticObj.resultList.add(data);
//                                    }
//                                }
//                            } else {
//                                if (StaticObj.matchSd().bOptionLeavingSMS && !attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                    StaticObj.resultList.add(data);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (StaticObj.resultList.size() == 0) {
//            closeProgressDialog();
//
//            if( bundleName != null && bundleName.length() >0) {
//                //playSendingSucceedSound();
//                playSound();
//                if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                    sMsg = bundleName + "의 출석이 완료됐습니다.";
//                    bReg = true;
//                    showTagResult(bReg, sMsg, "cust");
//                } else if (sAttendHomeStatus.equals("3")) {
//                    sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                    bReg = true;
//                    showTagResult(bReg, sMsg, "cust");
//                }
//                bundleName = "";
//            } else {
//                showSendingSucceed();
//            }
//            didTag = false;
//            bKeyPress = false;
//
//            viewWarnningMsg();
//            return;
//        }
//
//
//        messageSendByPassSocCon();
//
//    }
//
//    private void sendAttendSms() {
//
//        StaticObj.resultList.clear();
//
//        for (int i = 0; i < attendResultList.size(); i++) {
//
//            if (attendResultList.get(i).getnGcmLength() < 60) {
//                if (attendResultList.get(i).getsCustCd().equals(rowSelectData.getsAuthCode())) {
//
//                    String szMsg = attendResultList.get(i).getsMsg();
//
//                    AttendSmsResultData data = new AttendSmsResultData();
//                    data.setsAuthCode(attendResultList.get(i).getsCustCd());
//                    data.setsSector(attendResultList.get(i).getsSector());
//                    data.setsSendPhone(attendResultList.get(i).getsPhoneNum());
//                    data.setsName(rowSelectData.getsName());
//                    data.setsSendMsg(szMsg);
//                    data.setbSendResult(false);
//                    data.setsSendSubject(szMsg.substring(0, szMsg.length() >= 10 ? 10 : szMsg.length()));
//                    if (BuildConfig.IS_DEPRECATED_SMS || StaticObj.mPrtSd.bMMSOnly || getMsgLength(szMsg) > 80) {
//                        data.setsSection1("MMS");
//                    } else {
//                        data.setsSection1("SMS");
//                    }
//                    data.setsSendGroup("");
//
//                    if (rowSelectData.getsAttendSmsYn().equals("N")) {
//
//                        data.setsSection2("ATT");
//                        data.setsAttendType("1");
//                        if (data.getsSendPhone().equals("01000000000") || data.getsSendPhone().equals("00000000000") || data.getsSendPhone().equals("0100000000")) {
//                        } else {
//                            if (StaticObj.matchSd().bSmsCheck) {
//                                if (StaticObj.matchSd().bOptionAttendSMS && !StaticObj.matchSd().bOptionAttendPush) {
//                                    StaticObj.resultList.add(data);
//                                } else if (StaticObj.matchSd().bOptionAttendSMS && StaticObj.matchSd().bOptionAttendPush) {
//                                    if (!attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                        StaticObj.resultList.add(data);
//                                    }
//                                }
//                            } else {
//                                if (StaticObj.matchSd().bOptionAttendSMS && !attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                    StaticObj.resultList.add(data);
//                                }
//                            }
//                        }
//
//                        Log.d("sendAttendSms", "1111 authcode[" + data.getsAuthCode() + "], szMsg[" + szMsg + "]");
//                    } else if (rowSelectData.getsHomeSmsYn().equals("N")) {
//
//                        data.setsSection2("HOM");
//                        data.setsAttendType("3");
//                        if (data.getsSendPhone().equals("01000000000") || data.getsSendPhone().equals("00000000000") || data.getsSendPhone().equals("0100000000")) {
//                        } else {
//                            if (StaticObj.matchSd().bSmsCheck) {
//                                if (StaticObj.matchSd().bOptionLeavingSMS	&& !StaticObj.matchSd().bOptionLeavingPush) {
//                                    StaticObj.resultList.add(data);
//                                } else if (StaticObj.matchSd().bOptionLeavingSMS && StaticObj.matchSd().bOptionLeavingPush) {
//                                    if (!attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                        StaticObj.resultList.add(data);
//                                    }
//                                }
//                            } else {
//                                if (StaticObj.matchSd().bOptionLeavingSMS	&& !attendResultList.get(i).getsPhoneGcmId().equals("1")) {
//                                    StaticObj.resultList.add(data);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (StaticObj.resultList.size() == 0) {
//            closeProgressDialog();
//
//            if( bundleName != null && bundleName.length() >0) {
//                //playSendingSucceedSound();
//                playSound();
//                if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                    sMsg = bundleName + "의 출석이 완료됐습니다.";
//                    bReg = true;
//                    showTagResult(bReg, sMsg, "cust");
//                } else if (sAttendHomeStatus.equals("3")) {
//                    sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                    bReg = true;
//                    showTagResult(bReg, sMsg, "cust");
//                }
//                bundleName = "";
//            } else {
//                showSendingSucceed();
//            }
//            didTag = false;
//            bKeyPress = false;
//
//            viewWarnningMsg();
//            return;
//        }
//
//        StaticObj.nSendCount = 0;		//전송 갯수
//        StaticObj.nSendFailCount = 0;	//전송 실패 갯수
//
//        try {
//            ExmanApplication myApp = (ExmanApplication) getApplication();
//
//            for (int i = 0; i < StaticObj.resultList.size(); i++) {
//                MessageData message = new MessageData();
//                if (StaticObj.matchSd().bTestModeYn) {
//                    message.setPhoneNum(StaticObj.matchSd().sTestModePhoneNum);
//                } else {
//                    message.setPhoneNum(StaticObj.resultList.get(i).getsSendPhone());
//                }
//                String sSubject = "출석체크";
//                try {
//                    sSubject = StaticObj.resultList.get(i).getsSendMsg().substring(0, 14);
//                } catch (Exception e) {
//                    sSubject = "출석체크";
//                }
//                message.setSubject(sSubject.replace("'", "\""));
//                message.setMessage(StaticObj.resultList.get(i).getsSendMsg().replace("'", "\""));
//                message.setUISend("Y");  //Y: UI단에서 메시지 요청, N: 백단(서비스)에서 메시지 요청
//                message.setSid(StaticObj.resultList.get(i).getsAuthCode());
//                message.setSector(StaticObj.resultList.get(i).getsSector());
//
//                if (!message.getPhoneNum().equals("") && message.getPhoneNum().length()>=10 && !message.getMessage().equals("")) {
//                    myApp.setMessageData(message);
//                }
//            }
//
//            myApp.sendMessageStart();
//
//        } catch (Exception e) {}
//
//
//    }
//
//    // 전송 결과 호면
//    private void resultMmsKlinker() {
//
//        if (dialog == null) {
//            AlertDialog.Builder build = new AlertDialog.Builder(
//                    AttendSelfNewAct.this);
//            String infService = Context.LAYOUT_INFLATER_SERVICE;
//
//            LayoutInflater lif = (LayoutInflater) this.getSystemService(infService);
//            mmsView = lif.inflate(R.layout.rn_mms_sending, null);
//
//            TextView txtInfo = (TextView) mmsView.findViewById(R.id.TXT_MMS_INFO);
//            TextView txtStatus = (TextView) mmsView.findViewById(R.id.TXT_MMS_STATUS);
//            txtInfo.setText(StaticObj.nSendCount + "/" + StaticObj.resultList.size());
//            txtStatus.setText("성공("	+ (StaticObj.nSendCount - StaticObj.nSendFailCount)
//                    + "), 실패(" + StaticObj.nSendFailCount + ")");
//            Log.d("count", StaticObj.nSendCount + "/" + StaticObj.resultList.size());
//            build.setView(mmsView);
//
//            dialog = build.create();
//            dialog.setCancelable(false);
//            dialog.show();
//        } else {
//
//            if (mmsView != null) {
//                LinearLayout lineBox = (LinearLayout) mmsView.findViewById(R.id.LINE_MMS_BOX);
//                int nWidth = lineBox.getMeasuredWidth();
//                int nPer = nWidth / StaticObj.resultList.size();
//
//                TextView txtIng = (TextView) mmsView.findViewById(R.id.TXT_MMS_ING);
//                TextView txtInfo = (TextView) mmsView.findViewById(R.id.TXT_MMS_INFO);
//                TextView txtStatus = (TextView) mmsView.findViewById(R.id.TXT_MMS_STATUS);
//                txtInfo.setText(StaticObj.nSendCount + "/" + StaticObj.resultList.size());
//                txtStatus.setText("성공("	+ (StaticObj.nSendCount - StaticObj.nSendFailCount)
//                        + "), 실패(" + StaticObj.nSendFailCount + ")");
//                Log.d("count", StaticObj.nSendCount + "/"+ StaticObj.resultList.size());
//                txtIng.setWidth(nPer * StaticObj.nSendCount);
//            }
//        }
//
//    }
//
//    private int getMsgLength(String sMsg) {
//
//        int nByteLenth = 0;
//
//        for (int index = 0; index < sMsg.length(); index++) {
//            if (isKorean(sMsg.substring(index, index + 1))) {
//                nByteLenth += 2;
//            } else {
//                nByteLenth += 1;
//            }
//        }
//
//        return nByteLenth;
//    }
//
//    private boolean isKorean(String data) {
//
//        Pattern p = Pattern.compile("[가-힣ㄱ-ㅣ]");
//        Matcher m = p.matcher(data);
//        if (m.matches()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public String getsMessage80(String sMessage) {
//        String sMsg = "";
//
//        byte[] sbMsg = sMessage.getBytes();
//
//        try {
//            sMsg = new String(sbMsg, 0, 80);
//        } catch (Exception e) {
//            if (sMessage.length() >= 40) {
//                sMsg = sMessage.substring(0, 40);
//            } else {
//                sMsg = sMessage.substring(0, sMessage.length());
//            }
//        }
//
//        return sMsg;
//    }
//
//    private void messageSendResultSocCon(AttendSmsResultData data) {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("sp", "dbo.MEXM_I_SMS_SEND_V9");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", StaticObj.matchSd().sAdminId);
//        map.put("resultData", data);
//        map.put("system", "AN");
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void failMessageCheckSocCon() {
//
//        bKeyPress = true;
//        bAttendCheck = false;
//        sFailMsg = "";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_S_MESSAGE_FAIL_LIST_V3");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", StaticObj.matchSd().sAdminId);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void messageSendByPassSocCon() {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "MEXM_I_SMS_BYPASS_V4");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", StaticObj.matchSd().sAdminId);
//        map.put("send_type", "");
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void adminPushSendSocCon() {
//
//        if (mProgressDialog == null) {
//            try {
//                mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//            } catch(Exception e){mProgressDialog = null;}
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_I_PUSH_ADMIN_SEND");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("sector", "MS");
//        map.put("reg_user", StaticObj.matchSd().sAdminId);
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void SyncDateSoc(String sGroupCd, String sAdminId) {
//        SyncDateSoc(sGroupCd, sAdminId, SyncDate.LIST_ALL, mHandler);
//    }
//
//    private void SyncDateSoc(String sGroupCd, String sAdminId, int nSyncType, Handler handler) {
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "", true, false, null);
//        }
//
//        StaticObj.sAppId = "01";
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_S_GROUP_SYNC_TIME_V4");
//        map.put("group_cd", sGroupCd);
//        map.put("admin_id", sAdminId);
//        map.put("sync_type", String.valueOf(nSyncType));
//        HttpClientRequest task = new HttpClientRequest(handler, getApplicationContext());
//        task.execute(map);
//    }
//
//    private void AttendAdminCheck(String sCardNo, String sSector) {
//
//        if (mProgressDialog == null) {
//            mProgressDialog = MyProgressDialog.show(this, "", "",	true, false, null);
//        }
//
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("sp", "dbo.MEXM_M_ADMIN_ATTEND_CHECK");
//        map.put("group_cd", StaticObj.matchSd().sGroupCd);
//        map.put("admin_id", StaticObj.matchSd().sAdminId);
//        map.put("atd_date", mCurDate.replace(".", "-"));
//        map.put("atd_time", (mCurTime.replace(" ", "")).substring(0, 5));
//        map.put("sector", sSector);
//        map.put("data", sCardNo);
//
//        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
//        task.execute(map);
//    }
//
////    private void AttendReasonConn() {
////
////        if (mProgressDialog == null) {
////            mProgressDialog = MyProgressDialog.show(this, "", "",	true, false, null);
////        }
////
////        HashMap<String, String> map = new HashMap<String, String>();
////        map.put("sp", "dbo.KIDSLOVE_S_ATTEND_REASON");
////        map.put("group_cd", StaticObj.matchSd().sGroupCd);
////        map.put("admin_id", StaticObj.matchSd().sAdminId);
////
////        HttpClientRequest task = new HttpClientRequest(mHandler, getApplicationContext());
////        task.execute(map);
////    }
//
//    private void viewGetTime() {
//
//        mCurDate = Util.dtCurrent("yyyy.MM.dd");
//        mCurTime = Util.dtCurrent("HH : mm : ss");
//
//        if (mSaveDay.equals("")) {
//            mSaveDay = mCurDate;
//        }
//
//        if (!mSaveDay.equals(mCurDate)) {
//            mSaveDay = mCurDate;
//            loadData();
//            AttendDaySocCon(0);
//        }
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if(isRfidTaging){
//            switch (keyCode){
//                case KeyEvent.KEYCODE_A:
//                    onKeyValue += "A";
//                    break;
//                case KeyEvent.KEYCODE_B:
//                    onKeyValue += "B";
//                    break;
//                case KeyEvent.KEYCODE_C:
//                    onKeyValue += "C";
//                    break;
//                case KeyEvent.KEYCODE_D:
//                    onKeyValue += "D";
//                    break;
//                case KeyEvent.KEYCODE_E:
//                    onKeyValue += "E";
//                    break;
//                case KeyEvent.KEYCODE_F:
//                    onKeyValue += "F";
//                    break;
//                case KeyEvent.KEYCODE_0:
//                    onKeyValue += "0";
//                    break;
//                case KeyEvent.KEYCODE_1:
//                    onKeyValue += "1";
//                    break;
//                case KeyEvent.KEYCODE_2:
//                    onKeyValue += "2";
//                    break;
//                case KeyEvent.KEYCODE_3:
//                    onKeyValue += "3";
//                    break;
//                case KeyEvent.KEYCODE_4:
//                    onKeyValue += "4";
//                    break;
//                case KeyEvent.KEYCODE_5:
//                    onKeyValue += "5";
//                    break;
//                case KeyEvent.KEYCODE_6:
//                    onKeyValue += "6";
//                    break;
//                case KeyEvent.KEYCODE_7:
//                    onKeyValue += "7";
//                    break;
//                case KeyEvent.KEYCODE_8:
//                    onKeyValue += "8";
//                    break;
//                case KeyEvent.KEYCODE_9:
//                    onKeyValue += "9";
//                    break;
//            }
//        }
//
//        if (StaticObj.matchSd().sKeyBoard.equals("Y"))
//        {
//            if( keyCode == KeyEvent.KEYCODE_BACK)
//            {
//                if(BuildConfig.IS_EXMAN){
//                    if (StaticObj.bScreenLock == false) {
//                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                        return false;
//                    }
//                }else{
//                    if (StaticObj.bScreenLock == false) {
//                        BackPressCloseHandler.getInstance().onBackPressed(AttendSelfNewAct.this);
//                        return false;
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                        return false;
//                    }
//                }
//            }
//            if((inputMode == INPUT_KEYBOARD || inputMode == INPUT_RFID) && bRfidCard == false) {
//
//                edRfid.setText("");
//                edRfid.setVisibility(View.VISIBLE);
//                edRfid.requestFocus();
//
//                if (keyCode == KeyEvent.KEYCODE_DEL) {
//                    if (mInputNumber.length() > 0) {
//                        resetAttendeeData(false);
//                        mInputNumber = mInputNumber.substring(0, mInputNumber.length() - 1);
//                        displayDigitText();
//                        generateDTMFTone(24);
//                    }
//                } else if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER ) {
//
//                    if (attendRowList.size() > 0) {
//                        if (attendRowList.size() == 1) {
//                            //attend(0);
//                            if (makeAttendData(0)) {
//                                sAttendType = "K";
//                                if(StaticObj.matchSd().bTemperature){
//                                    checkFever(nPos);
//                                }else{
//                                    sendAttendData();
//                                }
//                            }
//                        } else {
//                            if (attendRowList.size() > selPos) {
//                                if (makeAttendData(selPos)) {
//                                    sAttendType = "K";
//                                    if(StaticObj.matchSd().bTemperature){
//                                        checkFever(nPos);
//                                    }else{
//                                        sendAttendData();
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else if ( keyCode == KeyEvent.KEYCODE_NUMPAD_SUBTRACT && mInputNumber.length() == INPUT_DIGIT_MAX) {
//                    if (selPos > 0) {
//                        selPos--;
//                        mAdapter.setSelPos(selPos);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                    mListView.setSelection(selPos);
//                } else {
//                    final Pattern KEYCODE_PATTERN = Pattern.compile("^[0-9]*$");
//                    String key = (char) event.getUnicodeChar() + "";
//                    //Toast.makeText(getApplicationContext(), "key[" + key + "], edRfid[" + edRfid.getText() + "]", Toast.LENGTH_SHORT).show();
//                    if( key.equals("+")) {
//                        if (selPos < attendRowList.size() - 1 && mInputNumber.length() == INPUT_DIGIT_MAX) {
//                            selPos++;
//                            mAdapter.setSelPos(selPos);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        mListView.setSelection(selPos);
//                    }
//                    Matcher matcher = KEYCODE_PATTERN.matcher(key);
//                    if (matcher.matches()) {
//                        inputNum(key);
//                    }
//                }
//            }
//
//            return super.onKeyDown(keyCode, event);
//        }
//        else
//        {
//            if(inputMode == INPUT_KEYBOARD || inputMode == INPUT_RFID) {
//                if( keyCode == KeyEvent.KEYCODE_DEL) {
//                    stopDTMFTone();
//                } else if( keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER ) {
//                } else {
//                    final Pattern KEYCODE_PATTERN = Pattern.compile("^[0-9]*$");
//                    String key = (char) event.getUnicodeChar() + "";
//                    // Toast.makeText(getApplicationContext(), "key : " + keyCode + " / " + key, 0).show();
//                    Matcher matcher = KEYCODE_PATTERN.matcher(key);
//                    if (matcher.matches()) {
//                        stopDTMFTone();
//                    }
//                }
//            }
//
//            return false;
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (StaticObj.matchSd().sKeyBoard.equals("Y"))
//        {
//            if((inputMode == INPUT_KEYBOARD || inputMode == INPUT_RFID) && bRfidCard == false) {
//                //Toast.makeText(getApplicationContext(), "key down00", Toast.LENGTH_SHORT).show();
//                edRfid.setText("");
//                edRfid.setVisibility(View.GONE);
//
//                if( keyCode == KeyEvent.KEYCODE_DEL) {
//                    stopDTMFTone();
//                } else if( keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER ) {
//                } else {
//                    final Pattern KEYCODE_PATTERN = Pattern.compile("^[0-9]*$");
//                    String key = (char) event.getUnicodeChar() + "";
//                    // Toast.makeText(getApplicationContext(), "key : " + keyCode + " / " + key, 0).show();
//                    Matcher matcher = KEYCODE_PATTERN.matcher(key);
//                    if (matcher.matches()) {
//                        stopDTMFTone();
//                    }
//                }
//            }
//
//            return false;
//        }
//        else
//        {
//            if( keyCode == KeyEvent.KEYCODE_BACK)
//            {
//                if(BuildConfig.IS_EXMAN){
//                    if (StaticObj.bScreenLock == false) {
//                        Intent intent = new Intent(getApplicationContext(), MainAct.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                        return false;
//                    }
//                }else{
//                    if (StaticObj.bScreenLock == false) {
//                        BackPressCloseHandler.getInstance().onBackPressed(AttendSelfNewAct.this);
//                        return false;
//                    } else {
//                        Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
//                        return false;
//                    }
//                }
//            }
//            if(inputMode == INPUT_KEYBOARD || inputMode == INPUT_RFID) {
//                if (keyCode == KeyEvent.KEYCODE_DEL) {
//                    if (mInputNumber.length() > 0) {
//                        resetAttendeeData(false);
//                        mInputNumber = mInputNumber.substring(0, mInputNumber.length() - 1);
//                        displayDigitText();
//                        generateDTMFTone(24);
//                    }
//                } else if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER ) {
//                    if (attendList.size() > 0) {
//                        if (attendList.size() == 1) {
//                            if (makeAttendData(0)) {
//                                sAttendType = "K";
//                                if(StaticObj.matchSd().bTemperature){
//                                    checkFever(nPos);
//                                }else{
//                                    sendAttendData();
//                                }
//                            }
//                        } else {
//                            if (attendList.size() > selPos) {
//                                sAttendType = "K";
//                                if(StaticObj.matchSd().bTemperature){
//                                    checkFever(nPos);
//                                }else{
//                                    sendAttendData();
//                                }
//                            }
//                        }
//                    }
//                } else if ( keyCode == KeyEvent.KEYCODE_NUMPAD_SUBTRACT && mInputNumber.length() == INPUT_DIGIT_MAX) {
//                    if (selPos > 0) {
//                        selPos--;
//                        mAdapter.setSelPos(selPos);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                    mListView.setSelection(selPos);
//                } else {
//                    final Pattern KEYCODE_PATTERN = Pattern.compile("^[0-9]*$");
//                    String key = (char) event.getUnicodeChar() + "";
//                    if( key.equals("+")) {
//                        if (selPos < attendList.size() - 1 && mInputNumber.length() == INPUT_DIGIT_MAX) {
//                            selPos++;
//                            mAdapter.setSelPos(selPos);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        mListView.setSelection(selPos);
//                    }
//                    Matcher matcher = KEYCODE_PATTERN.matcher(key);
//                    if (matcher.matches()) {
//                        inputNum(key);
//                    }
//                }
//            }
//
//            return super.onKeyDown(keyCode, event);
//        }
//    }
//
//    private void inputNum(String num) {
//        if (mInputNumber.length() < INPUT_DIGIT_MAX) {
//            selPos = 0;
//            generateDTMFTone(Integer.parseInt(num));
//            mInputNumber += num;
//            displayDigitText();
//        }
//
//        if (mInputNumber.length() == INPUT_DIGIT_MAX) {
//            bInputCheck = true;
//            sInputNum = num;
//            inputMode = INPUT_KEYBOARD;
//            AttendDaySyncSocCon();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        logManager.write("V", "34", "", "", "", "D");
//
//        stopAutoModeBroadcast();
//
//        if(!Build.MODEL.equals("muPAD7")
//                && !Build.MODEL.equals("TCC8920_EVM")) {
//            if (Build.VERSION.SDK_INT >= 18) {
//                BluetoothReaderHelper.getInstance(getApplicationContext()).destory();
//                logManager.write("B", "3105", "", TAG, "onDestroy", "BluetoothReaderHelper.destory()");
//            }
//        }
//
//        if (StaticObj.matchSd().bFailNoti) {
//            mHandler.removeMessages(1111);
//        }
//
//        if(!Build.MODEL.equals("TCC8920_EVM")){
//            unregisterReceiver(bluetoothReceiver);
//            unregisterReceiver(nfcReceiver);
//        }
//
//        if(mSoundPool != null){
//            mSoundPool.release();
//        }
//
//        if(mDTMFToneGenerator != null){
//            mDTMFToneGenerator.release();
//        }
//
//        closeProgressDialog();
//    }
//
//    @Override
//    protected void onResume() {
//        overridePendingTransition(0, 0);
//        super.onResume();
//        logManager.write("V", "31", "", "", "", "R");
//
//        if (!WellPermission.checkAllPermission(getApplicationContext(), true) && StaticObj.matchSd().bTemperature) {
//            WellPermission.requestPermission(AttendSelfNewAct.this, false, false, true);
//            return;
//        }
//
//        if(Build.MODEL.equals("TCC8920_EVM")){
//            btnNfcState.setBackgroundResource(R.drawable.nfc_on);
//            nfcExpBtn.setVisibility(View.GONE);
//            nfcExpView.setVisibility(View.GONE);
//            mHandler.sendEmptyMessageDelayed(TAG_DELAY_ADPOP, DELAY_SECOND);
//        }else{
//            if (adptNfc == null)
//                adptNfc = NfcAdapter.getDefaultAdapter(this);
//
//            if (adptNfc != null) {
//                adptNfc.enableForegroundDispatch(this, ittPend, null, null);
//            }
//
//            edRfid.requestFocus();
//            setModeRfid();
//
//            if(Build.MODEL.equals("muPAD7")) {
//                btnBluetoothState.setBackgroundResource(R.drawable.bluetooth_off);
//                btnNfcState.setBackgroundResource(R.drawable.nfc_off);
//                nfcExpBtn.setVisibility(View.VISIBLE);
//                nfcExpView.setVisibility(View.VISIBLE);
//                bleExpBtn.setVisibility(View.VISIBLE);
//                bleExpView.setVisibility(View.VISIBLE);
//            }
//            else {
//                BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
//
//                //현재 Bluetooth가 켜져 있는지, 혹은 켜는 중인지 확인 한다.
//                if(bAdapter != null){
//                    try {
//                        if(bAdapter.getState() == BluetoothAdapter.STATE_TURNING_ON ||
//                                bAdapter.getState() == BluetoothAdapter.STATE_ON) {
//                            btnBluetoothState.setBackgroundResource(R.drawable.bluetooth_on);
//                            bleExpBtn.setVisibility(View.GONE);
//                            bleExpView.setVisibility(View.GONE);
//                        }else{
//                            btnBluetoothState.setBackgroundResource(R.drawable.bluetooth_off);
//                            bleExpBtn.setVisibility(View.VISIBLE);
//                            bleExpView.setVisibility(View.VISIBLE);
//                        }
//                    }catch (Exception e){}
//                }
//
//                NfcAdapter nAdapter = NfcAdapter.getDefaultAdapter(this);
//
//                //현재 NFC가 켜져 있는지, 혹은 켜는 중인지 확인 한다.
//                if(nAdapter != null){
//                    if(nAdapter.isEnabled()) {
//                        btnNfcState.setBackgroundResource(R.drawable.nfc_on);
//                        nfcExpBtn.setVisibility(View.GONE);
//                        nfcExpView.setVisibility(View.GONE);
//                    }else{
//                        btnNfcState.setBackgroundResource(R.drawable.nfc_off);
//                        nfcExpBtn.setVisibility(View.VISIBLE);
//                        nfcExpView.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//
//        }
//
//        if(sDeviceYn.equals("Y")){
//            btnBluetoothState.setVisibility(View.GONE);
//            btnNfcState.setVisibility(View.GONE);
//            nfcExpBtn.setVisibility(View.GONE);
//            bleExpBtn.setVisibility(View.GONE);
//            nfcExpView.setVisibility(View.GONE);
//            bleExpView.setVisibility(View.GONE);
//        }
//
//        btnOnTouch(this, R.id.attend_self_keypad_btn_1);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_2);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_3);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_4);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_5);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_6);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_7);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_8);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_9);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_0);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_reset);
//        btnOnTouch(this, R.id.attend_self_keypad_btn_del);
//
//        mListEmptyView.setCompoundDrawables(null, null, null, null);
//
//        bForeGround = true;
//        if (StaticObj.matchSd().bFailNoti) {
//            mHandler.removeMessages(1111);
//            if (bForeGround) {
//                mHandler.sendEmptyMessageDelayed(1111, 1000 * mDelaySec);
//            }
//        }
//
//        if (!StaticObj.matchSd().sSyncDate.equals(Util.dtCurrent("yyyy-MM-dd"))) {
//            Intent intent = new Intent(getApplicationContext(), IntroAct.class);
//            startActivity(intent);
//            finish();
//        }else{
//            if (mInputNumber.length() == INPUT_DIGIT_MAX) {
//                sAttendType = "M";
//                inputMode = INPUT_KEYBOARD;
//
//                SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_ALL, new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//
//                        if (isFinishing()) {
//                            return;
//                        }
//
//                        loadData();
//                        AttendDaySocCon(1);
//                    }
//                });
//            }
//        }
//
////        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN){
////            if(nAdapter.isEnabled() || nAdapter.isNdefPushEnabled()) {
////                btnNfcState.setBackgroundResource(R.drawable.nfc_on);
////            }else{
////                btnNfcState.setBackgroundResource(R.drawable.nfc_off);
////            }
////        }else{
////            if(nAdapter.isEnabled()) {
////                btnNfcState.setBackgroundResource(R.drawable.nfc_on);
////            }else{
////                btnNfcState.setBackgroundResource(R.drawable.nfc_off);
////            }
////        }
//    }
//
////    @Override
////    protected void onUserLeaveHint() {
////        super.onUserLeaveHint();
////        if (StaticObj.bScreenLock) {
////            Util.toastMessage(getApplicationContext(), "화면이 잠겨있습니다.");
////            startActivity(new Intent(getApplicationContext(), AttendSelfNewAct.class)
////                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
////            finish();
////        }
////    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if(Build.MODEL.equals("TCC8920_EVM")){
//            if(Util.isServiceRunning(getApplicationContext(), NfcService.class)){
//                StaticObj.stopNfcService(getApplicationContext());
//            }
//        }else{
//            scanLeDevice(false);
//        }
//
//        bForeGround = false;
//        mHandler.removeMessages(1111);
//    }
//
//    private void generateDTMFTone(int num) {
//        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        int currentVolumn = audio.getStreamVolume(AudioManager.STREAM_DTMF);
//        Log.e("kooj", "generateDTMFTone : "+num+", "+currentVolumn);
//        int tone = ToneGenerator.TONE_DTMF_0 + num;
//        try {
//            if (mDTMFToneGenerator != null) {
//                mDTMFToneGenerator.stopTone();
//                mDTMFToneGenerator.startTone(tone, 2000);
//            }
//        } catch(Exception e) {}
//    }
//
//    private void stopDTMFTone() {
//        Log.e("kooj", "stopDTMFTone");
//        try {
//            if (mDTMFToneGenerator != null) {
//                mDTMFToneGenerator.stopTone();
//            }
//        } catch(Exception e) {}
//    }
//
//    private void playAttendingSound() {
//        try {
//            if (mSoundPool != null && mSoundAttending != 0) {
//                Log.e("kooj", "AttendingSound");
//                mSoundPool.play(mSoundAttending, 1f, 1f, 0, 0, 1f);
//            }else{
//                if(mSoundPool != null){
//                    setAttendSound();
//                    mCurrentSound = mSoundAttending;
//                }
//            }
//        } catch(Exception e) {}
//    }
//
//    private void playLeavingSound() {
//        try {
//            if (mSoundPool != null && mSoundLeaving != 0) {
//                Log.e("kooj", "LeavingSound");
//                mSoundPool.play(mSoundLeaving, 1f, 1f, 0, 0, 1f);
//            }else{
//                if(mSoundPool != null){
//                    setLeavingSound();
//                    mCurrentSound = mSoundLeaving;
//                }
//            }
//        } catch(Exception e) {}
//    }
//
//    private void playBirthdaySound() {
//        try {
//            if (mSoundPool != null && mSoundBirthday != 0) {
//                Log.e("kooj", "BirthdaySound");
//                mSoundPool.play(mSoundBirthday, 1f, 1f, 0, 0, 1f);
//            }else{
//                if(mSoundPool != null){
//                    setBirthdaySound();
//                    mCurrentSound = mSoundBirthday;
//                }
//            }
//        } catch(Exception e) {}
//    }
//
//    private void playSendingSucceedSound() {
//        try {
//            if (mSoundPool != null && mSendingSucceed != 0) {
//                Log.e("kooj", "SucceedSound");
//                mSoundPool.play(mSendingSucceed, 1f, 1f, 0, 0, 1f);
//                //mSoundPool.play(mSoundAttending, 1f, 1f, 0, 0, 1f);
//            }else{
//                if (mSoundPool != null) {
//                    mSendingSucceed = mSoundPool.load(getApplicationContext(), R.raw.alarm_attend, 1);
//                    mCurrentSound = mSendingSucceed;
//                }
//            }
//        } catch(Exception e) {}
//    }
//
//    private void playNoResultSound() {
//        try {
//            if (mSoundPool != null && mNoResultSound != 0) {
//                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//                int currentVolumn = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
//                Log.e("kooj", "NoResultSound_volumn : "+currentVolumn+", mNoResultSound : "+mNoResultSound);
//                mSoundPool.play(mNoResultSound, 1f, 1f, 0, 0, 1f);
//            }else{
//                if (mSoundPool != null) {
//                    mNoResultSound = mSoundPool.load(getApplicationContext(), R.raw.glassping, 1);
//                    mCurrentSound = mNoResultSound;
//                }
//            }
//        } catch(Exception e) {}
//    }
//
//    private void showSendingSucceed() {
//        resetAttendeeData(true);
//        mAdapter.setItem(attendRowList);
//        mAdapter.notifyDataSetChanged();
//
//        bRfidCard = false;
//
//        playSound();
//
//        try {
//            if (rowSelectData == null || TextUtils.isEmpty(rowSelectData.getsAuthCode()) || rowSelectData.getsAuthCode().equals("")) {
//                mResultFound.setImageResource(R.drawable.rn_attend_self_sending_succeed);
//            } else {
//                if(rowSelectData.getbBirthday()) {
//                    imgTagResultBirthday.setVisibility(View.VISIBLE);
//                } else {
//                    imgTagResultBirthday.setVisibility(View.GONE);
//                }
//
//                if (rowSelectData.getsSex().equals("여")) {
//                    mResultFound.setImageResource(R.drawable.rn_img10_3);
//                } else {
//                    mResultFound.setImageResource(R.drawable.rn_img10_4);
//                }
//            }
//        } catch(Exception e) {
//        }
//        mResultFound.setVisibility(View.VISIBLE);
//        mListEmptyView.setVisibility(View.GONE);
//        mHandler.postDelayed(mRunnableShowResultImage, 1000);
//        mHandler.postDelayed(mRunnableInputReset, 1000);
//    }
//
//    private void playSound() {
//
//        try {
//            if (rowSelectData == null || TextUtils.isEmpty(rowSelectData.getsAuthCode()) || rowSelectData.getsAuthCode().equals("")) {
//                return;
//            }
//        } catch(Exception e) {
//            return;
//        }
//
//        try {
//
//            if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                if (StaticObj.matchSd().bOptionSoundBirthday) {
//                    if (rowSelectData.getbBirthday()) {
//                        playBirthdaySound();
//                    } else {
//                        if (StaticObj.matchSd().bOptionSoundAttending) {
//                            playAttendingSound();
//                        } else {
//                            playSendingSucceedSound();
//                        }
//                    }
//                } else {
//                    if (StaticObj.matchSd().bOptionSoundAttending) {
//                        playAttendingSound();
//                    } else {
//                        playSendingSucceedSound();
//                    }
//                }
//
//            } else if (sAttendHomeStatus.equals("3")) {
//                if (StaticObj.matchSd().nOptionAttendLeavingInput == 3) {
//                    if (StaticObj.matchSd().bOptionSoundBirthday) {
//                        if (rowSelectData.getbBirthday()) {
//                            playBirthdaySound();
//                        } else {
//                            if (StaticObj.matchSd().bOptionSoundLeaving) {
//                                playLeavingSound();
//                            } else {
//                                playSendingSucceedSound();
//                            }
//                        }
//                    } else {
//                        if (StaticObj.matchSd().bOptionSoundLeaving) {
//                            playLeavingSound();
//                        } else {
//                            playSendingSucceedSound();
//                        }
//                    }
//                } else {
//                    if (StaticObj.matchSd().bOptionSoundLeaving) {
//                        playLeavingSound();
//                    } else {
//                        playSendingSucceedSound();
//                    }
//                }
//            } else {
//                playSendingSucceedSound();
//            }
//        } catch(Exception e){}
//    }
//
//    public void btnOnTouch(Context mContext, final int nXml) {
//        final Button btnV = (Button) ((Activity) mContext).findViewById(nXml);
//        btnV.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (nXml) {
//                    case R.id.attend_self_keypad_btn_1:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(1);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_2:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(2);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_3:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(3);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_4:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(4);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_5:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(5);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_6:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(6);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_7:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(7);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_8:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(8);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_9:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(9);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_0:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(0);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_reset:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            try {
//                                if (mDTMFToneGenerator != null) {
//                                    mDTMFToneGenerator.stopTone();
//                                    mDTMFToneGenerator.startTone(ToneGenerator.TONE_PROP_ACK, 300);
//                                }
//                            } catch (Exception e) {
//                            }
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                        }
//                        break;
//                    case R.id.attend_self_keypad_btn_del:
//                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12ov);
//                            generateDTMFTone(24);
//                        } else if (event.getAction() == MotionEvent.ACTION_UP
//                                || event.getAction() == MotionEvent.ACTION_CANCEL) {
//                            btnV.setBackgroundResource(R.drawable.rn_img12);
//                            stopDTMFTone();
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnDummy:
//                break;
//            case R.id.attend_self_keypad_btn_1:
//                if (mInputNumber.length() < INPUT_DIGIT_MAX) {
//                    selPos = 0;
//                    mInputNumber += "1";
//                    displayDigitText();
//                }
//
//                if (mInputNumber.length() == INPUT_DIGIT_MAX) {
//                    inputMode = INPUT_KEYBOARD;
//                    AttendDaySyncSocCon();
//                }
//                break;
//        }
//    }
//
//    private void showResultNotFound() {
//        bRfidCard = false;
//        playNoResultSound();
//        mResultFound.setImageResource(R.drawable.rn_attend_self_not_found);
//        mResultFound.setVisibility(View.VISIBLE);
//        mListEmptyView.setVisibility(View.GONE);
//        mHandler.postDelayed(mRunnableShowResultImage, 1000);
//        mHandler.postDelayed(mRunnableInputReset, 1000);
//    }
//
//    private void showTagResult( boolean result, String msg, String sector) {
//        mListView.setVisibility(View.GONE);
//        mListEmptyView.setVisibility(View.GONE);
//
//        if(attendRowList.size() != 0){
//            CustPointSocCon(StaticObj.matchSd().sGroupCd, rowSelectData.getsAuthCode());
//        }else{
//            textTagResult.setText(msg);
//        }
//
//        if(rowSelectData.getbBirthday()) {
//            imgTagResultBirthday.setVisibility(View.VISIBLE);
//        } else {
//            imgTagResultBirthday.setVisibility(View.GONE);
//        }
//
//        Drawable d = null;
//        if (result) {
//            if(sector.equals("cust")){
//                if (StaticObj.matchSd().bOptionAttendProfile && !rowSelectData.getsProfileUrl().equals("") && rowSelectData.getsProfileUrl().length() > 2) {
//                    setUrlImage(rowSelectData.getsProfileUrl(), rowSelectData.getsSex());
//                } else {
//                    if (rowSelectData.getsSex().equals("여")) {
//                        d = getResources().getDrawable(R.drawable.rn_img10_3);
//                    } else {
//                        d = getResources().getDrawable(R.drawable.rn_img10_4);
//                    }
//
//                    loCardTag.setVisibility(View.VISIBLE);
//                    imgTagResult.setBackgroundDrawable(d);
//
//                    Handler h = new Handler();
//                    Runnable r = new Runnable() {
//                        @Override
//                        public void run() {
//                            mListEmptyView.setVisibility(View.VISIBLE);
//                            loCardTag.setVisibility(View.GONE);
//                        }
//                    };
//                    h.postDelayed(r, DELAY_SECOND);
//                    mHandler.postDelayed(mRunnableInputReset, DELAY_SECOND);
//                }
//            }else{
//                if(!adminImgUrl.equals("")){
//                    setUrlImage(adminImgUrl, "남");
//                }else{
//                    d = getResources().getDrawable(R.drawable.rn_img10_4);
//
//                    loCardTag.setVisibility(View.VISIBLE);
//                    imgTagResult.setBackgroundDrawable(d);
//
//                    Handler h = new Handler();
//                    Runnable r = new Runnable() {
//                        @Override
//                        public void run() {
//                            mListEmptyView.setVisibility(View.VISIBLE);
//                            loCardTag.setVisibility(View.GONE);
//                        }
//                    };
//                    h.postDelayed(r, DELAY_SECOND);
//                    mHandler.postDelayed(mRunnableInputReset, DELAY_SECOND);
//                }
//            }
//        } else {
//            loCardTag.setVisibility(View.VISIBLE);
//            d = getResources().getDrawable(R.drawable.rn_attend_self_not_found);
//            imgTagResult.setBackgroundDrawable(d);
//            Handler h = new Handler();
//            Runnable r = new Runnable() {
//                @Override
//                public void run() {
//                    mListEmptyView.setVisibility(View.VISIBLE);
//                    loCardTag.setVisibility(View.GONE);
//                }
//            };
//            h.postDelayed(r, DELAY_SECOND);
//            mHandler.postDelayed(mRunnableInputReset, DELAY_SECOND);
//        }
//
//    }
//
//    private void setUrlImage(final String imageUrl, final String sex){
//        DisplayImageOptions builder = new DisplayImageOptions.Builder()
//                //.showImageForEmptyUri(R.drawable.rn_attend_self_sending_succeed)
//                //.showImageOnFail(R.drawable.rn_attend_self_sending_succeed)
//                //.showImageOnLoading(R.drawable.rn_attend_self_sending_succeed)
//                .cacheInMemory(false)
//                .cacheOnDisk(true)
//                //.displayer(new CircleBitmapDisplayer())
//                .bitmapConfig(Bitmap.Config.ARGB_8888).build();
//
//        ImageLoader.getInstance().loadImage(
//                StaticObj.serviceDomain + imageUrl,
//                new ImageSize(Util.pxTodp(getApplicationContext(), 312), Util.pxTodp(getApplicationContext(), 312)),
//                builder,
//                new ImageLoadingListener() {
//                    @Override
//                    public void onLoadingStarted(String imageUri, View view) {
//
//                    }
//
//                    @Override
//                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                        Drawable d = null;
//                        if (sex.equals("여")) {
//                            d = getResources().getDrawable(R.drawable.rn_img10_3);
//                        } else {
//                            d = getResources().getDrawable(R.drawable.rn_img10_4);
//                        }
//                        display(d);
//                    }
//
//                    @Override
//                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//
//                        // Create the RoundedBitmapDrawable.
//                        RoundedBitmapDrawable roundDrawable = RoundedBitmapDrawableFactory.create(getResources(), loadedImage);
//                        roundDrawable.setCircular(true);
//                        roundDrawable.setAntiAlias(true);
//                        display(roundDrawable);
//                    }
//
//                    @Override
//                    public void onLoadingCancelled(String imageUri, View view) {
//                        Drawable d = null;
//                        if (sex.equals("여")) {
//                            d = getResources().getDrawable(R.drawable.rn_img10_3);
//                        } else {
//                            d = getResources().getDrawable(R.drawable.rn_img10_4);
//                        }
//                        display(d);
//                    }
//
//                    public void display(Drawable d) {
//                        loCardTag.setVisibility(View.VISIBLE);
//                        imgTagResult.setBackgroundDrawable(d);
//
//                        Handler h = new Handler();
//                        Runnable r = new Runnable() {
//                            @Override
//                            public void run() {
//                                //mListView.setVisibility(View.VISIBLE);
//                                mListEmptyView.setVisibility(View.VISIBLE);
//                                loCardTag.setVisibility(View.GONE);
//                            }
//                        };
//                        h.postDelayed(r, DELAY_SECOND);
//                        mHandler.postDelayed(mRunnableInputReset, DELAY_SECOND);
//                    }
//                });
//    }
//
//    private void setModeRfid() {
//        boolean bModeChange = false;
//        if (inputMode != INPUT_RFID) {
//            resetAttendeeData(true);
//
//            inputMode = INPUT_RFID;
//            bModeChange = true;
//        }
//
//        edRfid.setVisibility(View.VISIBLE);
//        edRfid.setText("");
//        if (bModeChange) {
//            if (inputMode == INPUT_KEYBOARD) {
//                mListEmptyView.setText(getString(R.string.attend_self_list_empty_label));
//            } else {
//                if (StaticObj.matchSd().sKeyBoard.equals("Y")) {
//                    mListEmptyView.setText(getString(R.string.attend_self_list_empty_keyboard_label));
//                } else {
//                    mListEmptyView.setText(getString(R.string.attend_self_list_empty_card_label));
//                }
//            }
//        }
//        edRfid.requestFocus();
//    }
//
//
//    ////////////////////////////////////////////자동모드 전용 START///////////////////////////////////////////
//    private void setAutoModeBroadcast(){
//
//        try{
//            mReceiver = new BroadcastReceiver() {
//
//                @Override
//                public void onReceive(Context context, Intent intent) {
//
//                    if (StaticObj.resultList.size() == 0) return; //액티비티에서 BroadCastReciever를 등록시킨것과 SmsSendNew에서 BroadCastReciever를 등록시킨것과 구분하기 위한 코드
//
//                    if(intent.getAction().equals(MessageCommon.AUTO_SMS_SEND_RESULT_BR)) {
//
//                        SMSSendResult((MessageData) intent.getSerializableExtra("message"));
//
//                    }else if(intent.getAction().equals(MessageCommon.AUTO_MMS_SEND_RESULT_BR)) {
//
//                        MMSSendResult((MessageData) intent.getSerializableExtra("message"));
//
//                    }else if(intent.getAction().equals(MessageCommon.AUTO_MESSAGE_ERROR_BR)) {
//
//                        MessageError(intent.getStringArrayListExtra("log"));
//
//                    }else if(intent.getAction().equals(MessageCommon.AUTO_MESSAGE_SEND_FINISH_BR)) {
//
//                        MessageSendFinish(intent.getStringArrayListExtra("log"));
//                    }
//
//                }
//            };
//
//            IntentFilter intentFilter = new IntentFilter();
//            intentFilter.addAction(MessageCommon.AUTO_SMS_SEND_RESULT_BR);
//            intentFilter.addAction(MessageCommon.AUTO_MMS_SEND_RESULT_BR);
//            intentFilter.addAction(MessageCommon.AUTO_MESSAGE_ERROR_BR);
//            intentFilter.addAction(MessageCommon.AUTO_MESSAGE_SEND_FINISH_BR);
//
//            if(mReceiver != null){
//                LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, intentFilter);
//            }
//
//        }catch(Exception e){
//        }
//
//    }
//
//
//    private void stopAutoModeBroadcast(){
//
//        try{
//            if(mReceiver != null){
//                LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
//            }
//        }catch(Exception e){
//        }
//
//    }
//
//
//    private void SMSSendResult(MessageData message){
//
//        if(message.getLogArr() != null && !message.getLogArr().isEmpty()){ //로그출력
//            LogManager logManager = new LogManager(getApplicationContext());
//            ArrayList<String> logArr = message.getLogArr();
//            logManager.write("A", logArr);
//            message.logClear();
//        }
//
//        for (int i=0; i<StaticObj.resultList.size(); i++) {
//
//            if (StaticObj.resultList.get(i).getsAuthCode().equals(message.getSid()) && StaticObj.resultList.get(i).getsSector().equals(message.getSector())) {
//
//                Log.d("myLog", "auth_code[" + message.getSid() + "]");
//                Log.d("myLog", "getPhoneNum[" + message.getPhoneNum() + "]");
//
//                if(message.getResultCode() == MessageCommon.SMS_SEND_SUCCESS){
//                    StaticObj.nSendCount++;
//                    StaticObj.resultList.get(i).setbSendResult(true);
//                    Log.d("myLog","BR SMS 성공 "+message.getId());
//
//                }else{
//                    StaticObj.nSendFailCount++;
//                    StaticObj.resultList.get(i).setbSendResult(false);
//
//                    Log.d("myLog","BR SMS 실패 "+message.getId()+","+message.getSendFailReason()); Log.d("myLog","------------");
//                }
//                Log.d("myLog", "getSendResult(" + StaticObj.resultList.get(i).getbSendResult() + ")");
//                Log.d("myLog","------------");
//
//                messageSendResultSocCon(StaticObj.resultList.get(i));
//
//                break;
//            }
//        }
//        Log.d("myLog","-BR-message.getUISend()->"+message.getUISend()+","+message.getRegDate()+","+message.getRegTime());
//    }
//
//
//
//    private void MMSSendResult(MessageData message){
//
//        if(message.getLogArr() != null && !message.getLogArr().isEmpty()){ //로그출력
//            LogManager logManager = new LogManager(getApplicationContext());
//            ArrayList<String> logArr = message.getLogArr();
//            logManager.write("A", logArr);
//            message.logClear();
//        }
//
//        for (int i=0; i<StaticObj.resultList.size(); i++) {
//            if (StaticObj.resultList.get(i).getsAuthCode().equals(message.getSid()) && StaticObj.resultList.get(i).getsSector().equals(message.getSector())) {
//
//                Log.d("myLog", "auth_code[" + message.getSid() + "]");
//                Log.d("myLog", "getPhoneNum[" + message.getPhoneNum() + "]");
//
//                if(message.getResultCode() == MessageCommon.MMS_SEND_SUCCESS){
//                    StaticObj.nSendCount++;
//                    StaticObj.resultList.get(i).setbSendResult(true);
//
//                    Log.d("myLog","BR MMS 성공 "+message.getId());
//                }else{
//                    StaticObj.nSendFailCount++;
//                    StaticObj.resultList.get(i).setbSendResult(false);
//
//                    Log.d("myLog","BR MMS 실패 "+message.getId()+","+message.getSendFailReason()); Log.d("myLog","------------");
//                }
//                Log.d("myLog", "getSendResult(" + StaticObj.resultList.get(i).getbSendResult() + ")");
//                Log.d("myLog","------------");
//
//                messageSendResultSocCon(StaticObj.resultList.get(i));
//
//                break;
//            }
//        }
//
//        Log.d("myLog", "-BR-message.getUISend()->" + message.getUISend() + "," + message.getRegDate() + "," + message.getRegTime());
//
//    }
//
//    private void MessageError(ArrayList<String> logArr){
//
//        if(logArr != null && !logArr.isEmpty()){ //로그출력
//            LogManager logManager = new LogManager(getApplicationContext());
//            logManager.write("A", logArr);
//        }
//
//        //alertShow("메시지 목록 조회에 실패했습니다.");
//    }
//
//    private void MessageSendFinish(ArrayList<String> logArr){
//
//        if(logArr != null && !logArr.isEmpty()){ //로그출력
//            LogManager logManager = new LogManager(getApplicationContext());
//            logManager.write("A", logArr);
//        }
//
//        closeProgressDialog();
//
//        if (StaticObj.resultList.size() == 0) {
//            logManager.write("I", StaticObj.sAppId, "", "", "MessageSendFinish", "size=0");
//        } else {
//
//            if (StaticObj.resultList.get(0).getbSendResult() == false) {
//
//                if (StaticObj.bCertiPhone) {
//                    playNoResultSound();
//                } else {    // 공기계
//                    if (bundleName != null && bundleName.length() > 0) {
//                        //playSendingSucceedSound();
//                        playSound();
//                        if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                            sMsg = bundleName + "의 출석이 완료됐습니다.";
//                            bReg = true;
//                            showTagResult(bReg, sMsg, "cust");
//                        } else if (sAttendHomeStatus.equals("3")) {
//                            sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                            bReg = true;
//                            showTagResult(bReg, sMsg, "cust");
//                        }
//                        bundleName = "";
//                    } else {
//                        showSendingSucceed();
//                    }
//                }
//                Toast.makeText(getApplicationContext(), "전달 되었습니다.", Toast.LENGTH_SHORT).show();
//            } else {
//                if (bundleName != null && bundleName.length() > 0) {
//                    playSound();
//                    if ((sAttendHomeStatus.equals("1") || sAttendHomeStatus.equals("5"))) {
//                        sMsg = bundleName + "의 출석이 완료됐습니다.";
//                        bReg = true;
//                        showTagResult(bReg, sMsg, "cust");
//                    } else if (sAttendHomeStatus.equals("3")) {
//                        sMsg = bundleName + "의 귀가가 완료됐습니다.";
//                        bReg = true;
//                        showTagResult(bReg, sMsg, "cust");
//                    }
//                    bundleName = "";
//                } else {
//                    showSendingSucceed();
//                }
//            }
//        }
//        didTag = false;
//        bKeyPress = false;
//
//        viewWarnningMsg();
//
//    }
//
//    ////////////////////////////////////////////자동모드 전용 END///////////////////////////////////////////
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        Log.d("D", "(onActivityResult)");
//
//        if (data != null) {
//            if(requestCode == FEVER_RESULT){
//                String custId = data.getExtras().getString("cust_id");
//                String temper = String.valueOf(data.getExtras().getFloat("temperature"));
//                String feverYn = data.getExtras().getString("fever_yn");
//                String deviceYn = data.getExtras().getString("device_yn");
//                CustFeverSendCon(custId, temper, feverYn, deviceYn);
//                bKeyPress = true;
//            }
//        } else {
//            // OS setting 화면에서 BT 설정 후 돌아 왔을때.
//            if (requestCode == REQUEST_ENABLE_BT) {
//                if (resultCode == Activity.RESULT_OK && Build.VERSION.SDK_INT >= 18) {
////                    initBluetooth();
//                    if (initBluetooth()) {
//
//                        // Bluetooth 사용 가능
//                        if (StaticObj.matchSd().bOptionBluetoothAutoConnect) {
//                            SharedPreferences pref = getSharedPreferences("deviceInfo", 0);
//                            mDeviceAddress = pref.getString("nfc_device_address", "");
//                            mDeviceName = pref.getString("nfc_device_name", "");
//
//                            if (!Util.nvl(mDeviceAddress).equals("") && !Util.nvl(mDeviceName).equals("")) {
//                                // bluetooth 사용 할 수 있는 상태 인지 아닌지 확인 이 필요 하다.
//                                Log.d(TAG, "onActivityResult - mDeviceAddress : " + mDeviceAddress + ", mDevicename : " + mDeviceName);
//                                if (initBluetooth()) {
//                                    // Bluetooth 사용 가능
//                                    setNFCBluetoothStart();
//                                }
//                            }
//                        } else {
//                            scanLeDevice(true);
//                        }
//                    }
//                } else if (resultCode == Activity.RESULT_CANCELED) {
//                }
//            }else if(requestCode == FEVER_RESULT){
//                bKeyPress = true;
//                nPos = 0;
//                resetAttendeeData(true);
//            }
//        }
//    }
//
//    public void appFinish() {
//        finish();
//    }
//
//    @Override
//    public void backFinish() {
//        if (Util.getDataEnable(getApplicationContext(), false)) {
//            sendLogout();
//        }
//        else
//        {
//            appFinish();
//        }
//    }
//
//    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            final String action = intent.getAction();
//
//            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
//                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);
//                switch(state) {
//                    case BluetoothAdapter.STATE_OFF:
//                        btnBluetoothState.setBackgroundResource(R.drawable.bluetooth_off);
//                        bleExpBtn.setVisibility(View.VISIBLE);
//                        bleExpView.setVisibility(View.VISIBLE);
//                        blueToothDisconn = true;
//
//                        if(StaticObj.matchSd().bOptionBluetoothAutoConnect){
//                            String msg = "장비 연결중..";
//                            try{
//                                SpannableStringBuilder sps = new SpannableStringBuilder(msg);
//                                sps.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 3, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                btnDeviceSearch.setText(sps);
//                            }catch (Exception e){
//                                btnDeviceSearch.setText(msg);
//                            }
//
//                            btnDeviceState.setBackgroundResource(R.drawable.rn_img08off);
//                        }
//
//                        logManager.write("B", "3106", "", String.valueOf(action), String.valueOf(BluetoothAdapter.STATE_OFF), String.valueOf(state));
//                        break;
////                    case BluetoothAdapter.STATE_TURNING_OFF:
////                        break;
//                    case BluetoothAdapter.STATE_ON:
//                        btnBluetoothState.setBackgroundResource(R.drawable.bluetooth_on);
//                        bleExpBtn.setVisibility(View.GONE);
//                        bleExpView.setVisibility(View.GONE);
//
//                        if(StaticObj.matchSd().bOptionBluetoothAutoConnect){
//                            if(blueToothDisconn && !Util.nvl(mDeviceName).equals("") && !Util.nvl(mDeviceAddress).equals("")){
//                                BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).disconnectReader();
//                                BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).connectReader(mDeviceName, mDeviceAddress);
//                                blueToothDisconn = false;
//                            }
//                        }
//
//                        break;
////                    case BluetoothAdapter.STATE_TURNING_ON:
////                        break;
//                }
//
//                if(sDeviceYn.equals("Y")){
//                    nfcExpBtn.setVisibility(View.GONE);
//                    nfcExpView.setVisibility(View.GONE);
//                    bleExpBtn.setVisibility(View.GONE);
//                    bleExpView.setVisibility(View.GONE);
//                }
//            }
//        }
//    };
//
//    private final BroadcastReceiver nfcReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            final String action = intent.getAction();
//
//            NfcAdapter nAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
//
//            if(action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)){
//                final int state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE, NfcAdapter.STATE_OFF);
//
////                Bundle bundle = intent.getExtras();
////                Set<String> keys = bundle.keySet();
//                if(nAdapter != null){
//                    if(nAdapter.isEnabled()){
//                        btnNfcState.setBackgroundResource(R.drawable.nfc_on);
//                        nfcExpBtn.setVisibility(View.GONE);
//                        nfcExpView.setVisibility(View.GONE);
//                    }else{
//                        btnNfcState.setBackgroundResource(R.drawable.nfc_off);
//                        nfcExpBtn.setVisibility(View.VISIBLE);
//                        nfcExpView.setVisibility(View.VISIBLE);
//                    }
//                }
//
//                if(Build.MODEL.equals("TCC8920_EVM")){
//                    btnNfcState.setBackgroundResource(R.drawable.nfc_on);
//                    nfcExpBtn.setVisibility(View.GONE);
//                    nfcExpView.setVisibility(View.GONE);
//                }
//
//                if(sDeviceYn.equals("Y")){
//                    nfcExpBtn.setVisibility(View.GONE);
//                    nfcExpView.setVisibility(View.GONE);
//                    bleExpBtn.setVisibility(View.GONE);
//                    bleExpView.setVisibility(View.GONE);
//                }
////                switch (state){
////                    case NfcAdapter.STATE_OFF:
////                        btnNfcState.setBackgroundResource(R.drawable.nfc_off);
////                        break;
//////                    case NfcAdapter.STATE_TURNING_OFF:
//////                        break;
////                    case NfcAdapter.STATE_ON:
////                        btnNfcState.setBackgroundResource(R.drawable.nfc_on);
////                        break;
//////                    case NfcAdapter.STATE_TURNING_ON:
//////                        break;
////                }
//            }
//        }
//    };
//
//    // 블루투스 NFC 리더기
//    @SuppressLint("NewApi")
//    private boolean initBluetooth() {
//        Log.d(TAG, "initBluetooth()");
//        /*
//         * Use this check to determine whether BLE is supported on the device.
//         * Then you can selectively disable BLE-related features.
//         */
//        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//            Toast.makeText(getApplicationContext(), "BLE를 지원하지 않는 단말입니다.", Toast.LENGTH_SHORT).show();
//            setResult(500, null);
//            finish();
//        }
//
//        /*
//         * Initializes a Bluetooth adapter. For API level 18 and above, get a
//         * reference to BluetoothAdapter through BluetoothManager.
//         */
//        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        mBluetoothAdapter = bluetoothManager.getAdapter();
//
//        /* Checks if Bluetooth is supported on the device. */
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(getApplicationContext(), "Bluetooth를 지원하지 않는 단말입니다.", Toast.LENGTH_SHORT).show();
//            setResult(500, null);
//            finish();
//            return false;
//        }
//
//        /*
//         * Ensures Bluetooth is enabled on the device. If Bluetooth is not
//         * currently enabled, fire an intent to display a dialog asking the user
//         * to grant permission to enable it.
//         */
//        if (!mBluetoothAdapter.isEnabled()) {
//            if (!mBluetoothAdapter.isEnabled()) {
//                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    ////////////////////////////////////////////NFC 블루투스 검색 START////////////////////////////////////////////
//    /* Device scan callback. */
//
//    @SuppressLint("NewApi")
//    private synchronized void scanLeDevice(final boolean enable) {
//
//        if (enable) {
//            /* Stops scanning after a pre-defined scan period. */
//            if (!WellPermission.checkAllPermission(getApplicationContext(), true)) {
//                WellPermission.requestPermission(this, false, false, true);
//                return;
//            }
//
//            showBluetoothDialog();
//
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (mScanning) {
//                        mScanning = false;
//                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                        Log.e("kooj", "stopLeScan_1");
//                    }
//                }
//            }, SCAN_PERIOD);
//
//            mScanning = true;
//            mBluetoothAdapter.startLeScan(mLeScanCallback);
//            Log.e("kooj", "startLeScan_1");
//        } else {
//            if (mScanning) {
//                mScanning = false;
//                mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                Log.e("kooj", "stopLeScan_2");
//            } else {
//                if(mBluetoothAdapter != null){
//                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                    Log.e("kooj", "stopLeScan_3");
//                }
//            }
//        }
//    }
//
//    private void showBluetoothDialog() {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.alert_title_layout, null);
//
//        TextView title_text = (TextView) view.findViewById(R.id.title_text);
//        View close_text = view.findViewById(R.id.close_text);
//        title_text.setText("블루투스 검색");
//        close_text.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                scanLeDevice(false);
//                mBlutoothDialog.dismiss();
//            }
//        });
//
//        mNFCBluetoothAdapter = new NFCBluetoothAdapter(AttendSelfNewAct.this);
//        ListView listView = new ListView(AttendSelfNewAct.this);
//        listView.setAdapter(mNFCBluetoothAdapter);
//        listView.setBackgroundColor(Color.WHITE);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                final BluetoothDevice device = mNFCBluetoothAdapter.getDevice(position);
//                if (device == null) {
//                    return;
//                }
//                scanLeDevice(false);
//
//                mDeviceName = device.getName();
//                mDeviceAddress = device.getAddress();
//
////                Log.e("kooj", "mDeviceName : "+mDeviceName+", mDeviceAddress : "+mDeviceAddress);
//
//                setNFCBluetoothStart();
//
//                mBlutoothDialog.dismiss();
//            }
//        });
//
//        mBlutoothDialog = new AlertDialog.Builder(AttendSelfNewAct.this).setCustomTitle(view).setView(listView)
//        .setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                scanLeDevice(false);
//                Log.e("kooj", "scan_onCancel");
//            }
//        }).create();
//        mBlutoothDialog.show();
//
//        //팝업창의 기본 라인색상을 변경한다.
//        try {
//            int divierId = mBlutoothDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
//            View divider = mBlutoothDialog.findViewById(divierId);
//            divider.setBackgroundColor(Color.parseColor("#279BD8"));
//
//        } catch (Exception e) {
//        }
//
//    }
//    ////////////////////////////////////////////NFC 블루투스 검색 END////////////////////////////////////////////
//
//    @SuppressLint("NewApi")
//    private void setNFCBluetoothStart() {
//
//        /* Connect the reader. */
//        BluetoothReaderHelper.getInstance(getApplicationContext(), new BluetoothReaderHelper.BluetoothReaderCallBack() {
//
//            @Override
//            public void connecting() {
//                Log.v(TAG, "connecting");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String msg = "장비 연결중..";
//                        try{
//                            SpannableStringBuilder sps = new SpannableStringBuilder(msg);
//                            sps.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 3, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                            btnDeviceSearch.setText(sps);
//                        }catch (Exception e){
//                            btnDeviceSearch.setText(msg);
//                        }
//                        btnDeviceState.setBackgroundResource(R.drawable.rn_img08off);
//                        logManager.write("B", "3107", "", "setNFCBluetoothStart", "connecting", "");
//                    }
//                });
//            }
//
//            @Override
//            public void reconnecting() {
//                Log.v(TAG, "reconnecting");
//                BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).disconnectReader();
//                BluetoothReaderHelper.getInstance(AttendSelfNewAct.this).connectReader(mDeviceName, mDeviceAddress);
//            }
//
//            @Override
//            public void connected() {
//                Log.v(TAG, "connected");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String msg = "장비 연결됨";
//                        try{
//                            SpannableStringBuilder sps = new SpannableStringBuilder(msg);
//                            sps.setSpan(new ForegroundColorSpan(Color.parseColor("#48a3d2")), 3, msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                            btnDeviceSearch.setText(sps);
//                        }catch (Exception e){
//                            btnDeviceSearch.setText(msg);
//                        }
//                        btnDeviceState.setBackgroundResource(R.drawable.rn_img08on);
//                        bluetoothRefresh.setVisibility(View.VISIBLE);
//                    }
//                });
//            }
//
//            @Override
//            public void disconnecting() {
//                Log.v(TAG, "disconnecting");
//                btnDeviceState.setBackgroundResource(R.drawable.rn_img08off);
//            }
//
//            @Override
//            public void disconnected() {
//                Log.v(TAG, "disconnected");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnDeviceState.setBackgroundResource(R.drawable.rn_img08off);
//                    }
//                });
//                logManager.write("B", "3108", "", "setNFCBluetoothStart", "disconnected", "");
//            }
//
//            @Override
//            public void findData(final String value) {  // call back 넘겨받은 태그정보로 매칭처리
//
//                if(Util.getTopProcessActivityName(AttendSelfNewAct.this).equals("FeverBaseActivity")){   // 중복 데이터처리 방지
//                    return;
//                }
//
//                try {
//                    Log.v(TAG, "override findData = " + value + " > " + String.valueOf(Long.parseLong(value, 16)));
//                }catch (NumberFormatException e){
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        sAttendType = "B";
//                        inputMode = INPUT_RFID;
//                        mCardNumber = value;
//
////                        if(StaticObj.matchSd().sGroupCd.equals("KI00004961") || StaticObj.matchSd().sGroupCd.equals("KI00000123")){
////                            logManager.write("B", "3112", "", "BluetoothReaderHelper_findData", "INPUT_RFID", mCardNumber);
////                        }
//
//                        SyncDateSoc(StaticObj.matchSd().sGroupCd, StaticObj.matchSd().sAdminId, SyncDate.LIST_CUST, new Handler() {
//                            @Override
//                            public void handleMessage(Message msg) {
//
//                                if (isFinishing()) {
//                                    return;
//                                }
//
//                                loadData();
//                                AttendDaySyncSocCon();
//                            }
//                        });
//                    }
//                });
//            }
//        }).connectReader(mDeviceName, mDeviceAddress);
//    }
//}