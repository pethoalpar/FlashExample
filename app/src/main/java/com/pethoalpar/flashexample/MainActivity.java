package com.pethoalpar.flashexample;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean flashState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CameraManager cameraManager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
                    for(String id : cameraManager.getCameraIdList()){
                        if(cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)){
                            cameraManager.setTorchMode(id, !flashState);
                            flashState = !flashState;
                        }
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
