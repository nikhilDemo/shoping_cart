package com.shopingcartdemo.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shopingcartdemo.R;
import com.shopingcartdemo.views.fragments.CartFragment;
import com.shopingcartdemo.views.fragments.ProductsFragment;
import com.shopingcartdemo.views.dummy.DummyContent;

public class HomeScreenActivity extends AppCompatActivity implements CartFragment.OnListFragmentInteractionListener, ProductsFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;
    /**
     * Cart Fragment reference to reuse same instance while replacing.
     */
    private CartFragment cartFragment;
    /**
     * Products Fragment reference to reuse same instance while replacing.
     */
    private ProductsFragment productsFragment;
    /**
     * Bottome Navigation view to hold the Product & cart buttons.
     */
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initialize();
        initViews();
        setListeners();
    }

    /**
     * Initializes the objects required throughout in this screen.
     */
    private void initialize() {
        cartFragment = CartFragment.newInstance(2);
        productsFragment = ProductsFragment.newInstance(3);
    }

    /**
     * Initializes all views used in this activity.
     */
    private void initViews() {
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

    private void setListeners() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * On Navigation Item selected listener to perform the switching fragments on the respective click events.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_products:
                    mTextMessage.setText(R.string.title_products);
                    replaceFragment(productsFragment);
                    return true;
                case R.id.navigation_carts:
                    mTextMessage.setText(R.string.title_carts);
                    replaceFragment(cartFragment);
                    return true;
            }
            return false;
        }

    };

    /**
     * Replaces the fragment to the fragment stack.
     *
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container, fragment);
            //fragmentTransaction.addToBackStack(null);
            //fragmentTransaction.commitAllowingStateLoss();j
            fragmentTransaction.commit();
        } else {
            Log.e("Replace Fragment", "Fragment is null!");
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, "Got Callback", Toast.LENGTH_SHORT).show();
    }
}
