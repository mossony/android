package com.example.camerademo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.util.SparseArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextureView previewView;
    HandlerThread mHandlerThread;
    Handler mCameraHandler;
    Size mPreviewSize;
    Size mCaptureSize;

    CameraManager cameraManager;
    String mCameraID;
    CameraDevice mCameraDevice;
    CaptureRequest.Builder mCaptureRequestBuilder;
    CaptureRequest mCaptureRequest;
    CameraCaptureSession mSession;
    ImageReader mImageReader;
    private static final SparseArray ORIENTATION = new SparseArray();
    static {
        ORIENTATION.append(Surface.ROTATION_0, 90);
        ORIENTATION.append(Surface.ROTATION_90, 0);
        ORIENTATION.append(Surface.ROTATION_180, 270);
        ORIENTATION.append(Surface.ROTATION_270, 180);
//        ORIENTATION.append(Surface.ROTATION_0, 0);
//        ORIENTATION.append(Surface.ROTATION_90, 90);
//        ORIENTATION.append(Surface.ROTATION_180, 180);
//        ORIENTATION.append(Surface.ROTATION_270, 270);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.texture_view);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startCameraThread();

        if (!previewView.isAvailable()) {
            previewView.setSurfaceTextureListener(textureListener);
        }
        else if(previewView.isAvailable()){
            try {
                startPreview();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
            try {
                setUpCamera(width, height);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            try {
                turnOnCamera();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

        }
    };

    private void startCameraThread() {
        mHandlerThread = new HandlerThread("CameraThread");
        mHandlerThread.start();
        mCameraHandler = new Handler(mHandlerThread.getLooper());
    }

    private void setUpCamera(int width, int height) throws CameraAccessException {
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        for (String cameraID : cameraManager.getCameraIdList()) {
            CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraID);
            Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
            if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                continue;
            }
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (map != null) {
                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class), width, height);
                mCaptureSize = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)), new Comparator<Size>() {
                    @Override
                    public int compare(Size o1, Size o2) {
                        return Long.signum(o1.getWidth() * o1.getHeight() - o2.getWidth() * o2.getHeight());
                    }
                });
            }
            setUpImageReader();
            mCameraID = cameraID;
            break;
        }
    }

    private void setUpImageReader(){
        mImageReader = ImageReader.newInstance(mCaptureSize.getWidth(), mCaptureSize.getHeight(), ImageFormat.JPEG, 2);
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                mCameraHandler.post(new ImageSaver(reader.acquireNextImage()));
            }
        }, mCameraHandler);
    }


    private Size getOptimalSize(Size[] sizeMap, int width, int height) {
        List<Size> sizeList = new ArrayList<Size>();

        for (Size option : sizeMap) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    sizeList.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width) {
                    sizeList.add(option);
                }
            }
        }

        if (sizeList.size() > 1) {
            return Collections.min(sizeList, new Comparator<Size>() {
                @Override
                public int compare(Size o1, Size o2) {
                    return Long.signum(o1.getWidth() * o1.getHeight() - o2.getWidth() * o2.getHeight());
                }
            });
        }


        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turnOnCamera() throws CameraAccessException {

        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        int i = 0;
        for (String permission: permissions){
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(permissions, i++);
                return;
            }
        }
        cameraManager.openCamera(mCameraID, mStateCallback, mCameraHandler);
    }

    CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            try {
                startPreview();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            mCameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    };

    private void startPreview() throws CameraAccessException {

        SurfaceTexture mSurfaceTexture = previewView.getSurfaceTexture();
        mSurfaceTexture.setDefaultBufferSize(mCaptureSize.getWidth(), mCaptureSize.getHeight());
        @SuppressLint("Recycle") Surface previewSurface = new Surface(mSurfaceTexture);

        mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        mCaptureRequestBuilder.addTarget(previewSurface);

        mCameraDevice.createCaptureSession(Arrays.asList(previewSurface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {
                 mCaptureRequest = mCaptureRequestBuilder.build();
                 mSession = session;
                try {
                    mSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {

            }
        }, mCameraHandler);
    }

    public void capture(View view) throws CameraAccessException {
        CaptureRequest.Builder mCameraBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
        mCameraBuilder.addTarget(mImageReader.getSurface());

        // Obtain the direction of the camera
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        mCameraBuilder.set(CaptureRequest.JPEG_ORIENTATION, (int)ORIENTATION.get(rotation));

        CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
            @Override
            public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                Toast.makeText(getApplicationContext(), "Photo taken", Toast.LENGTH_SHORT).show();
                try {
                    unLockFocus();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
                super.onCaptureCompleted(session, request, result);
            }
        };

        mSession.stopRepeating();
        mSession.capture(mCameraBuilder.build(), mCaptureCallback, mCameraHandler);


    }

    private void unLockFocus() throws CameraAccessException {
        mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
        mSession.setRepeatingRequest(mCaptureRequestBuilder.build(), null, mCameraHandler);
    }

    private class ImageSaver implements Runnable{

        Image mImage;

        public ImageSaver(Image image){
            mImage = image;
        }

        @Override
        public void run() {
            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

            String path = Environment.getExternalStorageDirectory() + "/DCIM/CameraV2/";
            File mImageFile = new File(path);
            if (!mImageFile.exists()){
                mImageFile.mkdir();
            }

            @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = path + "IMG_" + timeStamp + ".jpg";
            try {
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}