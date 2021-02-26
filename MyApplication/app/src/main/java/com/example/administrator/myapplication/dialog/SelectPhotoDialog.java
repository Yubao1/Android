package com.example.administrator.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.content.Context;
import android.view.View;

import com.example.administrator.R;
import com.example.administrator.myapplication.tool.PixelsTools;


/**
 * 选择图片
 *
 */
public class SelectPhotoDialog extends BaseCustomDialog {

    private SelectPhotoListener selectPhotoListener;

    public void setSelectPhotoListener(SelectPhotoListener selectPhotoListener) {
        this.selectPhotoListener = selectPhotoListener;
    }

    public SelectPhotoDialog(Context context) {
        super(context);
    }

    @Override
    public int requestLayoutId() {
        return R.layout.dialog_select_photo;
    }

    @Override
    public void initDialogView(View view) {
        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_select_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        dismiss();
        switch (v.getId()) {
            case R.id.tv_take_photo:

                if (selectPhotoListener != null) {
                    selectPhotoListener.takePhoto();
                }
                break;
            case R.id.tv_select_photo:
                if (selectPhotoListener != null) {
                    selectPhotoListener.selectPhoto();
                }
                break;
            case R.id.tv_cancel:

                break;
            default:
                break;
        }
    }

    public interface SelectPhotoListener {
        void takePhoto();

        void selectPhoto();
    }

    @Override
    public int requestDialogWidth() {
        return PixelsTools.getWidthPixels(getActivity());
    }

    @Override
    public MyCustomDialog.DialogGravity requestDialogGravity() {
        return MyCustomDialog.DialogGravity.CENTER_BOTTOM;
    }
}
