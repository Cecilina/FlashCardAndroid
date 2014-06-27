package com.myfalshcard.flashcardgameOptionMenu;

import java.util.Random;

import com.myfalshcard.flashcardgameOptionMenu.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


//    private static enum FlashCard{
//    	FIRSTCARD(1),SECONDCARD(2),THIRDCARD(2);
//        private int value;
//        private FlashCard(int value){
//        	this.value=value;
//        }
//    };
    private static int winCount;
	private static int loseCount;
	private static int Count;
	private static int surfaceTotal=0;
	private static int resultSum;
	private static String st;
    private static Random randomNumber = new Random();
    private static Button btn1;
	private static Button btn2;
	private static Button btn3;
	private static Button btn4;
    private static TextView resultText;
	private static TextView resultText2;
	private static TextView resultText3;
	private static int operator;
	static int i = 0;
    private static Boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
      winCount = savedInstanceState.getInt("score");
      Count = savedInstanceState.getInt("level"); 
      st = savedInstanceState.getString("result");
        }else
        {
        	winCount =0;
        	Count =0;
        	
        }
       setContentView(R.layout.activity_main);
       
        Context context = getApplicationContext();
       
         resultText = (TextView) findViewById(R.id.textview1);
        
        
         resultText2 = (TextView) findViewById(R.id.textView2);
        
         resultText3 = (TextView) findViewById(R.id.textView3);
         
        

    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.main, menu);
		return true;
    	
    }
    
    
    public boolean onOptionsItemSelected(MenuItem menuItem){
    	operator = menuItem.getItemId();
    	 btn1 = (Button) findViewById(R.id.Button1);
         btn2 = (Button) findViewById(R.id.Button2);
         btn3 = (Button) findViewById(R.id.Button3);
         btn4 = (Button) findViewById(R.id.button4);
         final LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout1); 
         surfaceTotal = assignRandom(resultText2,resultText3);

     	
         buttonRandom(btn1,btn2,btn3,surfaceTotal);
         
         btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 
				clickEventHappen(v);  
				
				
			}
		});
         
         btn2.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 
 				clickEventHappen(v);  
 				
 				
 			}
 		});
         
         btn3.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 	
 				clickEventHappen(v);  
 				
 				
 			}
 		});
         
         btn4.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  	
  				onClickListener(v);  
  				
  				
  			}
  		});
   

    	
    	return true;
		
    	
    }
   
    @SuppressLint("ResourceAsColor")
	public static void clickEventHappen(final View v){

     resultSum=onClickListener(v);
     i++;
    if(resultSum==surfaceTotal){
    	if(i==1){
  		   winCount++;
  		  
  	   }
 	   Count++;
 	  v.setBackgroundColor(Color.GREEN);   
 	  Animation animation = new TranslateAnimation(0,0,0,1000);
    	 animation.setDuration(2000);
    	resultText2.startAnimation(animation);
 	   resultText3.startAnimation(animation); 
 	 final Handler handler = new Handler();
	  handler.postDelayed(new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			btn1.setBackgroundColor(0xFFffc000);
		 	  btn2.setBackgroundColor(0xFFffc000);
		 	  btn3.setBackgroundColor(0xFFffc000);
		 	 surfaceTotal = assignRandom(resultText2,resultText3);
		      buttonRandom(btn1,btn2,btn3,surfaceTotal);
		      i = 0;
		}
		  
	   }, 1000);
	  
 	   
    }else if(resultSum != surfaceTotal){
 	 //  loseCount++;
 	  v.setBackgroundColor(Color.RED); 
    }

  st = new String(winCount+" out of "+Count+" are right."); 
   resultText.setText(st);

	
    }

    public static int assignRandom(TextView text1, TextView text2){
    	int text1Integer = randomNumber.nextInt(9);
        text1.setText(Integer.toString(text1Integer));
        int text2Integer = randomNumber.nextInt(9);
        text2.setText(Integer.toString(text2Integer));
        
        switch(operator){
        case R.id.addition:
        	return (text1Integer+text2Integer);
        case R.id.subtract:
        	return (text1Integer-text2Integer);
        case R.id.multiplication:
        	return (text1Integer*text2Integer);
        case R.id.division:
        	text2Integer = tranformZerotoNon0(text2Integer);
        	text2.setText(Integer.toString(text2Integer));
        	return (text1Integer/text2Integer);
        case R.id.mode:
        	text2Integer =tranformZerotoNon0(text2Integer);
        	text2.setText(Integer.toString(text2Integer));
        	return (text1Integer%text2Integer);
        	default:
        		return (text1Integer+text2Integer);	
        }
      
    }
    
    private static int tranformZerotoNon0(int text2Integer) {
  		while(text2Integer == 0){
  			text2Integer = randomNumber.nextInt(9);
  		}
  		return text2Integer;
  	}
    
    public static void buttonRandom(Button btn1, Button btn2, Button btn3, int sum){
    	int randNum = randomNumber.nextInt(2);
    	int rand1 = randomNumber.nextInt(18);
    	int rand2 = randomNumber.nextInt(18);
    	switch(randNum){
    	case 0:
    		btn1.setText(Integer.toString(sum));
    		btn2.setText(Integer.toString(rand1));
    		btn3.setText(Integer.toString(rand2));
    		break;
    	case 1:
    		btn2.setText(Integer.toString(sum));
    		btn1.setText(Integer.toString(rand1));
    		btn3.setText(Integer.toString(rand2));
    		break;
    	case 2:
    		btn3.setText(Integer.toString(sum));
    		btn2.setText(Integer.toString(rand1));
    		btn1.setText(Integer.toString(rand2));
    		break;
    	default:
    			btn1.setText(Integer.toString(sum));
        		btn2.setText(Integer.toString(rand1));
        		btn3.setText(Integer.toString(rand2));
        		break;
    	}
    }
    
    public static int onClickListener(View v){
    	int result = Integer.MAX_VALUE;
    	switch (v.getId()){
    	case R.id.Button1:
    	  result=Integer.parseInt( btn1.getText().toString());
    	  checked = true;
          btn1.setSelected(false);
    	  break;
    	case R.id.Button2:
    		result = Integer.parseInt(btn2.getText().toString());
    		checked = true;
	           btn2.setSelected(false);
    		break;
    	case R.id.Button3:
    		result = Integer.parseInt(btn3.getText().toString());
    		checked = true;
	           btn3.setSelected(false);
    		break;
    	case R.id.button4:
    		winCount=0;
    		Count =0;
    		loseCount =0;
//    		btn1.setText("");
//    		btn2.setText("");
//    		btn3.setText("");
//    		resultText2.setText("");
//    		resultText3.setText("");
    		resultText.setText(R.string.result);
    	}
    	
    	return result;
    }
 
public void onSaveInstanceState(Bundle savedInstanceState){
	savedInstanceState.putInt("score",winCount);
	savedInstanceState.putInt("level",Count);
	savedInstanceState.putString("result", st);
	super.onSaveInstanceState(savedInstanceState);
}

public void onRestoreInstanceState(Bundle savedInstanceState){
	super.onRestoreInstanceState(savedInstanceState);
	 winCount = savedInstanceState.getInt("score");
	 Count = savedInstanceState.getInt("level");
	 st = savedInstanceState.getString("result");
	 
}


}
