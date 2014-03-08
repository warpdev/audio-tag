package com.warpdev.htmlaudio;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.google.ads.*;
import java.util.*;


public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
	EditText s;
    @Override
	
	private AdView adView;
	private Button crb;
	private String urlk;
	private static final String MY_AD_UNIT_ID="a15319f9689b5f8";
	// copy나 paste 를 하기 위한 EditText control
	private EditText mEditText;
// clipboardmanager에 copy 하기 위한 함수
	private void copy(String strClipText)
	{
		ClipboardManager clipManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipManager.setText(strClipText);
	}
// clipboard manager 에 저장되어 있는 text 를 가져와 EditText control에 paste 하는 함수
	private void paste()
	{
		ClipboardManager clipMgr = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		String strClip = "";
		if (clipMgr.hasText() == true) {
			strClip = clipMgr.getText().toString();
			if (strClip.length() > 0) {
				mEditText.setText(strClip);
			}
		}
		}
	public void setLocale(String charicter) {
		Locale locale = new Locale(charicter);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
	}
    public void onCreate(Bundle savedInstanceState)
	{
		Locale locale = getBaseContext().getResources().getConfiguration().locale;
		final String lancode = locale.getLanguage();
		if(lancode.equals("ko")){
			this.setContentView(R.layout.main);
		}else{
			this.setContentView(R.layout.mainus);
		}
        super.onCreate(savedInstanceState);
 
		
		adView =new AdView(this,AdSize.BANNER,MY_AD_UNIT_ID);
		LinearLayout layout=(LinearLayout)findViewById(R.id.mainLinearLayout);
		layout.addView(adView);
		s = (EditText) findViewById(R.id.urlbox);
		adView.loadAd(new AdRequest());
		crb=(Button) findViewById(R.id.create_button);
		crb.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					AlertDialog.Builder dlg=new AlertDialog.Builder(MainActivity.this);
					if(lancode.equals("ko")){
						dlg.setTitle("성공");
					dlg.setMessage("클립보드에 복사되었습니다."+"\n게시글 작성시 원하는곳에 붙여 넣으세요.");
						dlg.setPositiveButton("확인",null);
						}else{
							dlg.setTitle("Success");
							dlg.setMessage("Audio Tag copied to Clipboard."+"\nPaste where you want.");
							dlg.setPositiveButton("Okay",null);
						}
						
						dlg.show();
						urlk=s.getText().toString();
						copy("<audio autoplay controls><source src="+'"'+urlk+'"'+"/></audio>");
				}});
				
    }
	@Override public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}
}
