package com.example.notweather;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * https://proandroiddev.com/testing-the-un-testable-and-beyond-with-android-architecture-components-part-1-testing-room-4d97dec0f451
 */
public class LiveDataTestUtil {
    public static <T> T getValue(LiveData<T> liveData) {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer =
                new Observer<T>() {
                    @Override
                    public void onChanged(@Nullable T o) {
                        data[0] = o;
                        latch.countDown();
                        liveData.removeObserver(this);
                    }
                };
        liveData.observeForever(observer);
        try {
            latch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return (T) data[0];
        }

        return (T) data[0];
    }
}
