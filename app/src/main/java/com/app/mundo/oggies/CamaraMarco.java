package com.app.mundo.oggies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.widget.Toast;

public class CamaraMarco extends Activity implements SurfaceHolder.Callback{

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean cameraview = false;
    LayoutInflater inflater = null;
    ImageView overlay;
    String marco;
    int resIDMarco;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFormat(PixelFormat.UNKNOWN);




        surfaceView = (SurfaceView)findViewById(R.id.cameraview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        inflater = LayoutInflater.from(getBaseContext());
        View view = inflater.inflate(R.layout.overlay, null);

        LayoutParams layoutParamsControl= new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addContentView(view, layoutParamsControl);


        //Carga el int del res que se quiere cargar y se asigna al imageView imageOverLay del layout overlay.xml
        overlay = (ImageView)findViewById(R.id.overlayImage);
        Context context = overlay.getContext();
        resIDMarco = Jugador.marcos.get(MarcosFotos.pos_marco);
        overlay.setImageResource(resIDMarco);

        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                camera.takePicture(cameraShutterCallback,
                        cameraPictureCallbackRaw,
                        cameraPictureCallbackJpeg);
            }
        });


    }

    ShutterCallback cameraShutterCallback = new ShutterCallback()
    {
        @Override
        public void onShutter()
        {
            // TODO Auto-generated method stub
        }
    };

    PictureCallback cameraPictureCallbackRaw = new PictureCallback()
    {
        @Override
        public void onPictureTaken(byte[] data, Camera camera)
        {
            // TODO Auto-generated method stub
        }
    };

    PictureCallback cameraPictureCallbackJpeg = new PictureCallback()
    {
        @Override
        public void onPictureTaken(byte[] data, Camera camera)
        {

            // TODO Auto-generated method stub
            Bitmap cameraBitmap = BitmapFactory.decodeByteArray
                    (data, 0, data.length);

            int angleToRotate = getRotationAngle(CamaraMarco.this, Camera.CameraInfo.CAMERA_FACING_FRONT);
            // Solve image inverting problem
            angleToRotate = angleToRotate + 180;
            Bitmap orignalImage = BitmapFactory.decodeByteArray(data, 0, data.length);
            //IMAGEN ROTADA
            Bitmap BitmapPhoto = rotate(orignalImage, angleToRotate);

            //IMAGEN SIN ESPEJO
            BitmapPhoto = flip(BitmapPhoto);


            //Toast.makeText(MainActivity.this, "PHOTO = height: "+BitmapPhoto.getHeight() +" width: "+BitmapPhoto.getWidth(), Toast.LENGTH_SHORT).show();

            //Bitmap de oggie
            //Carga el resID del marco y se carga en el bitmap

            Bitmap bitmapFondo  = BitmapFactory.decodeResource(getResources(), resIDMarco);


            int wid = bitmapFondo.getWidth();
            int hgt = bitmapFondo.getHeight();

            //Toast.makeText(MainActivity.this, "FONDO = height: "+hgt +" width: "+wid, Toast.LENGTH_SHORT).show();

            //  Toast.makeText(getApplicationContext(), wid+""+hgt, Toast.LENGTH_SHORT).show();
            Bitmap imagenFinal = Bitmap.createBitmap(wid, hgt, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(imagenFinal);


            Rect rectangle = new Rect(0,0,wid,hgt);

            //Dibujo en el canvas la foto

            canvas.drawBitmap(BitmapPhoto, null, rectangle, null);


            //Dibujo en el canvas la imagen de los oggies
            canvas.drawBitmap(bitmapFondo, null, rectangle, null);

            File storagePath = new File(Environment.
                    getExternalStorageDirectory() + "/Oggies/");
            storagePath.mkdirs();

            File myImage = new File(storagePath,
                    Long.toString(System.currentTimeMillis()) + ".jpg");

            try
            {
                FileOutputStream out = new FileOutputStream(myImage);
                imagenFinal.compress(Bitmap.CompressFormat.JPEG, 80, out);


                out.flush();
                out.close();
                addImageToGallery(myImage.getAbsolutePath(), CamaraMarco.this);
            }
            catch(FileNotFoundException e)
            {
                Log.d("In Saving File", e + "");
            }
            catch(IOException e)
            {
                Log.d("In Saving File", e + "");
            }

            camera.startPreview();



            imagenFinal.recycle();
            imagenFinal = null;

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + myImage.getAbsolutePath()), "image/*");
            startActivity(intent);

        }
    };

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, Room_view.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        if(cameraview){
            camera.stopPreview();
            cameraview = false;
        }

        if (camera != null){
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
                cameraview = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {

                    camera = Camera.open(camIdx);
                    camera.setDisplayOrientation(90);

                } catch (RuntimeException e) {
                    //Log.e(TAG, "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
        camera = null;
        cameraview = false;
    }

    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.postRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    Bitmap flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = d;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }

    /**
     * Get Rotation Angle
     *
     * @param mContext
     * @param cameraId
     *            probably front cam
     * @return angel to rotate
     */
    public static int getRotationAngle(Activity mContext, int cameraId) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = mContext.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }
}