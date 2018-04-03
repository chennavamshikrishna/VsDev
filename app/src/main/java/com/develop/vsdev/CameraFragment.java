package com.develop.vsdev;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.vsdev.Utils.ActivityUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraFragment extends Fragment implements SurfaceHolder.Callback,Camera.PictureCallback {

    static CameraFragment cameraFragment;

    SurfaceView cameraView;
    SurfaceHolder surfaceHolder;
    Camera camera;
    ImageView capture,image;
    FrameLayout fl;
    TextView ok,cancel;
    Bitmap bm;
    public CameraFragment() {
    }
    public static CameraFragment getInstance(){
        if(cameraFragment==null){
            cameraFragment=new CameraFragment();
        }
        return cameraFragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v=inflater.inflate(R.layout.fragment_camera,container,false);
        cameraView = (SurfaceView) v.findViewById(R.id.camera_preview);
        fl=v.findViewById(R.id.frame);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
        capture = v.findViewById(R.id.capture);
        image=v.findViewById(R.id.image);
        ok=v.findViewById(R.id.ok);
        cancel=v.findViewById(R.id.cancel);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, CameraFragment.this);
                fl.setVisibility(View.GONE);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl.setVisibility(View.VISIBLE);
                image.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(bm);
            }
        });

        return v;

    }


    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        camera.startPreview();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        try {
            camera.setPreviewDisplay(holder);
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            camera.setParameters(parameters);
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                parameters.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
            }
            camera.setParameters(parameters);
        } catch (IOException exception) {
            camera.release();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /**
     * Create a file Uri for saving an image or video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        Log.d("stotgae path", mediaStorageDir.toString());
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        Log.d("path is", mediaFile.toString());
        return mediaFile;
    }

    public void onPictureTaken(byte[] data, Camera camera) {


        if (data != null) {
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int screenHeight = getResources().getDisplayMetrics().heightPixels;
           bm = BitmapFactory.decodeByteArray(data, 0, (data != null) ? data.length : 0);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Notice that width and height are reversed
                Bitmap scaled = Bitmap.createScaledBitmap(bm, screenHeight, screenWidth, true);
                int w = scaled.getWidth();
                int h = scaled.getHeight();
                // Setting post rotate to 90
                Matrix mtx = new Matrix();
                mtx.postRotate(90);
                // Rotating Bitmap
                bm = Bitmap.createBitmap(scaled, 0, 0, w, h, mtx, true);
            } else {// LANDSCAPE MODE
                //No need to reverse width and height
                Bitmap scaled = Bitmap.createScaledBitmap(bm, screenWidth, screenHeight, true);
                bm = scaled;
            }
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bm);
            ok.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);




        }


    }
   public void loadData(Bitmap bm){
       File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
       if (pictureFile == null) {
           Log.d("error", "Error creating media file, check storage permissions: "
           );
           return;
       }
       try {
           FileOutputStream fos = new FileOutputStream(pictureFile);
           bm.compress(Bitmap.CompressFormat.JPEG,90,fos);
           //fos.write(data);
           fos.close();
           Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();
       } catch (FileNotFoundException e) {
           Log.d("error", "File not found: " + e.getMessage());
       } catch (IOException e) {
           Log.d("error", "Error accessing file: " + e.getMessage());
       }
       String stringuri=pictureFile.getAbsolutePath();
       AdditionalInfo info=AdditionalInfo.getInstance();
       Bundle bmp=new Bundle();
       bmp.putString("bitimg",stringuri);
       info.setArguments(bmp);
       if(getFragmentManager()!=null){
           ActivityUtils.replaceFragmentInActivity(getFragmentManager(),info,R.id.uploadframe);
       }
    }

}
