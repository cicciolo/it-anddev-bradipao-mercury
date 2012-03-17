package it.anddev.bradipao.mercury;
// derived from http://thepseudocoder.wordpress.com/2011/10/05/android-page-swiping-using-viewpager/

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MercuryActivity extends FragmentActivity implements Page1Fragment.OnPageListener {
   
   // list contains fragments to instantiate in the viewpager
   List<Fragment> fragments = new Vector<Fragment>();
   // page adapter between fragment list and view pager
   private PagerAdapter mPagerAdapter;
   // view pager
   private ViewPager mPager;
   // activity data
   public String p2text,p3text;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
     
      // creating fragments and adding to list
      fragments.add(Fragment.instantiate(this,Page1Fragment.class.getName()));
      fragments.add(Fragment.instantiate(this,Page2Fragment.class.getName()));
      fragments.add(Fragment.instantiate(this,Page3Fragment.class.getName()));
      
      // creating adapter and linking to view pager
      this.mPagerAdapter = new PagerAdapter(super.getSupportFragmentManager(),fragments);
      mPager = (ViewPager) super.findViewById(R.id.pager);
      mPager.setAdapter(this.mPagerAdapter);

      // upper bar button listener, allows direct page access
      Button button = (Button)findViewById(R.id.btnPag1);
      button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            mPager.setCurrentItem(0);   // go to first page
         }
      });
      button = (Button)findViewById(R.id.btnPag2);
      button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            mPager.setCurrentItem(1);   // go to second page
         }
      });
      button = (Button)findViewById(R.id.btnPag3);
      button.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            mPager.setCurrentItem(2);   // go to third page
         }
      });
   }
   
   // page 1 fragment listener implementation
   @Override
   public void onPage1(String s) {    
      
      // set activity data with received string
      p2text = new String(s+" 2");
      p3text = new String(s+" 3");

      // page 2 fragment update
      Page2Fragment f2 = (Page2Fragment) fragments.get(1);
      f2.ptext = p2text;
      // if page 2 view is already created, update
      View v2 = f2.getView();
      if (v2!=null) {
         f2.setText(p2text+" direct");
      }
      
      // page 3 fragment update
      Page3Fragment f3 = (Page3Fragment) fragments.get(2);
      f3.ptext = p3text;
      // if page 3 view is already created, update
      View v3 = f3.getView();
      if (v3!=null) {
         f3.setText(p3text+" direct");
      }
      
   }

}