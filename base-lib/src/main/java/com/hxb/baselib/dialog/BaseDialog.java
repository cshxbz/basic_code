package com.hxb.baselib.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.hxb.baselib.R;

public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.base_dialog);
    }

}
