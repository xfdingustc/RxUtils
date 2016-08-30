package com.xfdingustc.rxutils.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xfdingustc.rxutils.library.RxBus;
import com.xfdingustc.rxutils.library.SimpleSubscribe;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private Button mBtnRxBusTest;

    private Subscription mRxSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnRxBusTest = (Button)findViewById(R.id.btn_rx_bus);
        mBtnRxBusTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getDefault().post(new TestRxBusEvent());
            }
        });

        mRxSubscription = RxBus.getDefault().toObserverable(TestRxBusEvent.class)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SimpleSubscribe<TestRxBusEvent>() {
                @Override
                public void onNext(TestRxBusEvent testRxBusEvent) {
                    Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                }
            });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mRxSubscription.isUnsubscribed()) {
            mRxSubscription.unsubscribe();
        }
    }
}
