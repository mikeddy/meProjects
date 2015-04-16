package com.example.aademo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.aademo.util.PalLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 不弹软键盘的edittext
 */
public class NoSoftInputEditText extends EditText {

    private static final Method mShowSoftInputOnFocus =getMethod(EditText.class, "setShowSoftInputOnFocus", boolean.class);

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setCursorVisible(true);
        }
    };

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            setCursorVisible(true);
            return false;
        }
    };

    public static Method getMethod(Class<?> cls, String methodName, Class<?>... parametersType) {
        Class<?> sCls = cls.getSuperclass();
        while (sCls != Object.class) {
            try {
                return sCls.getDeclaredMethod(methodName, parametersType);
            } catch (NoSuchMethodException e) {
            }
            sCls = sCls.getSuperclass();
        }
        return null;
    }

    public static Object invokeMethod(Method method, Object receiver, Object... args) {
        try {
            return method.invoke(receiver, args);
        } catch (IllegalArgumentException e) {
            PalLog.printE("Safe invoke fail", "Invalid args");
        } catch (IllegalAccessException e) {
            PalLog.printE("Safe invoke fail", "Invalid access");
        } catch (InvocationTargetException e) {
            PalLog.printE("Safe invoke fail", "Invalid target");
        }

        return null;
    }

    public NoSoftInputEditText(Context context) {
        super(context);
        initialize();
    }

    public NoSoftInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public NoSoftInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        synchronized (this) {
            setInputType(getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
            setFocusableInTouchMode(true);
        }
        setOnClickListener(mOnClickListener);
        setOnLongClickListener(mOnLongClickListener);
        reflexSetShowSoftInputOnFocus(false);
        setSelection(getText().length());
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        hideKeyboard();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean ret = super.onTouchEvent(event);
        hideKeyboard();
        return ret;
    }

    private void hideKeyboard() {
        final InputMethodManager imm = ((InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (imm != null && imm.isActive(this)) {
            imm.hideSoftInputFromWindow(getApplicationWindowToken(), 0);
        }
    }

    private void reflexSetShowSoftInputOnFocus(boolean show) {
        if (mShowSoftInputOnFocus != null) {
            invokeMethod(mShowSoftInputOnFocus, this, show);
        } else {
            hideKeyboard();
        }
    }


}