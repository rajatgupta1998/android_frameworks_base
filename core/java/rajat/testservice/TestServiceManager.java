package rajat.testservice;

import android.os.IBinder;
import android.util.Slog;
import rajat.testservice.ITestService;
import rajat.testservice.TestService;
public class TestServiceManager {
    private static final String TAG = "TestServiceManager";
    private static TestServiceManager mTestServiceManager;
    private final ITestService mITestService;

    TestServiceManager (ITestService myService) {
        Slog.d(TAG, "Manager constructor!");
        if (myService == null) {
            throw new IllegalArgumentException("myService null");
        }
        mITestService = myService;
    }

    public static synchronized TestServiceManager getTestServiceManager() {
        Slog.d(TAG, "Manager getter!");
        if (mTestServiceManager == null) {
            IBinder binder = android.os.ServiceManager.getService("TestService");
            if (binder != null) {
                ITestService manager = ITestService.Stub.asInterface(binder);
                mTestServiceManager = new TestServiceManager(manager);
            } else {
                Slog.e(TAG, "Service binder is null!");
            }
        }
        return mTestServiceManager;
    }

    public void setData(int arg) {
        Slog.d(TAG, "Manager API called!");
        try {
            mITestService.setData(arg);
        } catch (Exception e) {
            Slog.d(TAG, "Failed to call Service method from Manager");
            e.printStackTrace();
        }
    }
}