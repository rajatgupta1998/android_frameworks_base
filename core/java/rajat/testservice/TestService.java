package rajat.testservice;

import android.content.Context;
import android.util.Slog;

import rajat.testservice.ITestService;

public class TestService extends ITestService.Stub {
    private static final String TAG = "TestService";
    private Context mContext;

    public TestService(Context context) {
        super();
        Slog.d(TAG, "Service Constructor!");
        mContext = context;
    }

    public void setData(int val) {
        Slog.d(TAG, "Service API called! setData: " + val);
    }
}