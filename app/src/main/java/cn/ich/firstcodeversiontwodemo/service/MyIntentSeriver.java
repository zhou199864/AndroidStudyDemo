package cn.ich.firstcodeversiontwodemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentSeriver extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentSeriver(String name) {
        super(name);
    }
    public MyIntentSeriver() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService","Thread id is "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}
