package kodluyoruz.com.androidsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

public class SensorActivitiy extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_activitiy);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        initView();
    }

    private void initView() {

//        SENSOR_DELAY_NORMAL - sensör hareketlerini 215-230 ms duyarlılıkla kontrol eder.
//
//        SENSOR_DELAY_FASTEST sensör hareketlerini 15-20 ms duyarlılıkla kontrol eder.
//                             (aşırı duyarlı uygulamalar için. çok fazla işlemci gücü harcar, şarjı çabuk bitirir)
//        SENSOR_DELAY_GAME Oyun uygulamaları için kullanılır. Yüksek duyarlılık sağlar.
//                          sensör hareketlerini 35-40 ms duyarlılıkla kontrol eder.
//
//        SENSOR_DELAY_UI sensör hareketlerini 85-90 ms duyarlılıkla kontrol eder.
//                               (normal uygulamalar için en ideali)

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        counter++;
        Log.e("Adım Sayınız : ", String.valueOf(counter));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        if (accuracy == SensorManager.SENSOR_STATUS_ACCURACY_HIGH) {

            counter++;
            Log.e("Adım Sayınız : ", String.valueOf(counter));
        }

//        sensor Eylemi tetikleyen sensör nesnesi.
//        accuracy Sensör’ün eylem tetiklendiği andaki doğruluğu.
//        values Algılanan yeni değerleri içeren bir float dizisi.
//        timestamp Sensör eyleminin gerçekleştiği zaman.

//        SensorManager.SENSOR_STATUS_ACCURACY_LOW Sensör’ün düşük doğrlukla bildirim yaptığını ve kalibre edilmeye ihtiyaç duyduğunu gösterir.
//        SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM Sensör verisinin ortalama doğrulukta olduğunu ve kalibre edilerek iyileştirilebiliceğini gösterir.
//        SensorManager.SENSOR_STATUS_ACCURACY_HIGH Sensör’ün mümkün olan en iyi doğrulukta olduğunu gösterir.
//        SensorManager.SENSOR_STATUS_ACCURACY_UNRELIABLE Sensör verirsinin güvenilir olmadığını yani kalibrasyon gerektiğini yada okumaların mümkün olmadığını gösterir.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this);
    }
}
