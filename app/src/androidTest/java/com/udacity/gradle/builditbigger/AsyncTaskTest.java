package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Matteo on 30/06/2015.
 */
public class AsyncTaskTest extends AndroidTestCase {
    public void test() {
        String result = null;
        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            result = endpointsAsyncTask.execute(getContext()).get().second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}
