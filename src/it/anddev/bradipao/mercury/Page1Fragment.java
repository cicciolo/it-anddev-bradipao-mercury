package it.anddev.bradipao.mercury;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Page1Fragment extends Fragment {

   Button btnWrite;
   public String ptext="..PAGE 1..";
   
   // activity listener interface
   private OnPageListener pageListener;
   public interface OnPageListener {
      public void onPage1(String s);
   }
   
   // onAttach : set activity listener
   @Override
   public void onAttach(Activity activity) {
      super.onAttach(activity);
      // if implemented by activity, set listener
      if(activity instanceof OnPageListener) {
         pageListener = (OnPageListener) activity;
      }
      // else create local listener (code never executed in this example)
      else pageListener = new OnPageListener() {
         @Override
         public void onPage1(String s) {
            Log.d("PAG1","Button event from page 1 : "+s);
         }
      };
   }
   
   // onCreateView : 
   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
      
      // fragment not when container null
      if (container == null) {
         return null;
      }
      // inflate view from layout
      View view = (LinearLayout)inflater.inflate(R.layout.page1,container,false);
      // get button reference
      btnWrite = (Button) view.findViewById(R.id.btWrite);
      // set button listener to trigger activity listener
      btnWrite.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            pageListener.onPage1("Page 1 to");
         }
      });
      // update text
      TextView tv = (TextView) view.findViewById(R.id.tvText1);
      tv.setText(ptext);
      
      return view;
   }
   
   // set text helper function
   public void setText(String item) {
      TextView view = (TextView) getView().findViewById(R.id.tvText1);
      view.setText(item);
   } 
   
}