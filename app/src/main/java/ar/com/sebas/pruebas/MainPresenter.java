package ar.com.sebas.pruebas;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sebas on 21/10/2017.
 */

public class MainPresenter {
    private Activity context;
    private MainListener mainListener;

    public MainPresenter(Activity context, MainListener mainListener) {
        this.context = context;
        this.mainListener = mainListener;
    }

    /***
     * It's a asyncronous call, when this finish call the listener
     */
    public void AsyncCall()
    {
        final int seconds = 5;//new Random().nextInt(10);
        Log.d("Prueba Thread", "Start thread:" + String.valueOf(seconds) + "\"");
        new Thread(new Runnable() {
            int auxSeconds = seconds;
            public void run() {
                try {
                    do {
                        Log.d("Prueba Thread", "auxSeconds:" + String.valueOf(auxSeconds) + "\"");
                        Log.d("Prueba Thread", "mainListener != null: " + String.valueOf(mainListener != null));
                        if(mainListener != null) {
                            context.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainListener.onProgess(String.valueOf(auxSeconds));
                                    if(auxSeconds == 1)
                                    {
                                        Intent intent = new Intent(context, MainActivity.class);
                                        intent.putExtra("value", context.isFinishing()
                                                ? "Cerrado"
                                                : "Abierto");
                                        context.startActivity(intent);
                                    }
                                }
                            });
                        }
                        Thread.sleep(1000);
                        auxSeconds--;
                    } while (auxSeconds > 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
