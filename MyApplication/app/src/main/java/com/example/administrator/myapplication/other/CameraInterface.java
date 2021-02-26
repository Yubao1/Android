package com.example.administrator.myapplication.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.administrator.myapplication.tool.CameraParamUtils;
import com.example.administrator.myapplication.tool.ImageUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2020/3/22.
 */

public class CameraInterface {
    private static final String TAG = "CameraInterface";
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;
    private float mPreviewRate = -1f;
    private static CameraInterface mCameraInterface;
    private int mCameraFacing = -1;
    private CameraOpenCallback mCameraOpenCallback;
    private CameraCaptureCallback mCameraCaptureCallback;
    private SurfaceHolder mSurfaceHolder;

    public interface CameraOpenCallback {
        void cameraHasOpened(boolean hasOpened);
    }

    public interface CameraCaptureCallback {
        void onCameraCaptured(Bitmap bitmap);
    }

    private CameraInterface() {

    }

    public static synchronized CameraInterface getInstance() {
        if (mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    public CameraInterface setCameraOpenCallBack(CameraOpenCallback callBack) {
        mCameraOpenCallback = callBack;
        return mCameraInterface;
    }

    public CameraInterface setSurfaceHolder(SurfaceHolder mHolder) {
        mSurfaceHolder = mHolder;
        return mCameraInterface;
    }

    public CameraInterface setPreviewRate(float rate) {
        mPreviewRate = rate;
        return mCameraInterface;
    }

    public CameraInterface setCameraCaptureCallback(CameraCaptureCallback mCallback) {
        mCameraCaptureCallback = mCallback;
        return mCameraInterface;
    }

    public void doOpenCamera(int facing) {
        int id = -1;
        try {
            Camera.CameraInfo ci = new Camera.CameraInfo();
            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                Camera.getCameraInfo(i, ci);
                if (ci.facing == facing) {
                    id = ci.facing;
                    break;
                }
            }
            mCameraFacing = facing;
            if (id != -1) {
                mCamera = Camera.open(id);
                mCameraOpenCallback.cameraHasOpened(true);
            } else {
                mCameraOpenCallback.cameraHasOpened(false);
            }
        } catch (RuntimeException exception) {
            mCameraFacing = -1;
            mCameraOpenCallback.cameraHasOpened(false);
        }

    }

    public void switchCamera() {
        if (mCameraFacing != -1) {
            doStopCamera();
            doOpenCamera(mCameraFacing == Camera.CameraInfo.CAMERA_FACING_FRONT ? Camera.CameraInfo.CAMERA_FACING_BACK : Camera.CameraInfo.CAMERA_FACING_FRONT);
        }
    }

    public void doStartPreview() {
        Log.i(TAG, "doStartPreview...");
        if (isPreviewing) {
            mCamera.stopPreview();
            return;
        }
        if (Math.abs((-1) - mPreviewRate) < 0.00000001f) {
            return;
        }
        if (mCamera != null) {

            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);//设置拍照后存储的图片格式
            CameraParamUtils.getInstance().printSupportPictureSize(mParams);
            CameraParamUtils.getInstance().printSupportPreviewSize(mParams);
            //设置PreviewSize和PictureSize
            Camera.Size pictureSize = CameraParamUtils.getInstance().getPropPictureSize(
                    mParams.getSupportedPictureSizes(), mPreviewRate, 800);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Camera.Size previewSize = CameraParamUtils.getInstance().getPropPreviewSize(
                    mParams.getSupportedPreviewSizes(), mPreviewRate, 800);
            mParams.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);

            CameraParamUtils.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if (focusModes.contains("continuous-video")) {
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);

            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isPreviewing = true;
            mParams = mCamera.getParameters();
        }
    }

    /**
     * 停止预览，释放Camera
     */
    public void doStopCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mPreviewRate = -1f;
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 拍照
     */
    public void doTakePicture() {
        if (isPreviewing && (mCamera != null)) {
            try{
                mCamera.takePicture(null, null, mJpegPictureCallback);
            }catch (Exception e){
                if (mCameraCaptureCallback != null) {
                    mCameraCaptureCallback.onCameraCaptured(null);
                }
            }
        }
    }

    //快门按下的回调，在这里我们可以设置类似播放“咔嚓”声之类的操作。默认的就是咔嚓。
    Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {
        }
    };


    Camera.PictureCallback mJpegPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap b = null;
            if (null != data) {
                b = BitmapFactory.decodeByteArray(data, 0, data.length);//data是字节数据，将其解析成位图
//                if (data.length > 0) {
//                    mIDetectionResultInterface.onResult(true);
//                }
                camera.stopPreview();
                CameraInterface.getInstance().isPreviewing = false;
            }

            if (null != b) {
                Bitmap rotaBitmap;
                if (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    rotaBitmap = ImageUtil.getRotateBitmap(b, -90.0f);
                } else {
                    rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                }
                if (mCameraCaptureCallback != null) {
                    mCameraCaptureCallback.onCameraCaptured(rotaBitmap);
                }
            }
            //再次进入预览
            camera.startPreview();
            CameraInterface.getInstance().isPreviewing = true;

        }
    };

    public void turnFlashlight() {
        if (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            Log.d(TAG, "camera facing front");
            return;
        }
        if (mCamera == null) {
            Log.d(TAG, "camera null");
            return;
        }
        mParams = mCamera.getParameters();
        if (mParams == null) {
            Log.d(TAG, "camera params null");
            return;
        }

// 有些获取不到 supportedFlashModes 故暂时不用
//        List<String> supportedFlashModes = params.getSupportedFlashModes();
//        if (supportedFlashModes != null) {
//            Log.d(TAG, "flash mode null");
//            return;
//        }
//        if (supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH)) {
//            params.setFlashMode(params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH) ? Camera.Parameters.FLASH_MODE_OFF : Camera.Parameters.FLASH_MODE_TORCH);
//        } else if (supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_ON)) {
//            params.setFlashMode(params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_ON) ? Camera.Parameters.FLASH_MODE_OFF : Camera.Parameters.FLASH_MODE_ON);
//        }
        mParams.setFlashMode(mParams.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH) ? Camera.Parameters.FLASH_MODE_OFF : Camera.Parameters.FLASH_MODE_TORCH);

        if (mCamera != null) {
            mCamera.setParameters(mParams);
        }
    }
}
