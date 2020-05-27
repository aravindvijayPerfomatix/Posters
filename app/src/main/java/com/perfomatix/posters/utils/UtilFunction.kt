package com.perfomatix.posters.utils

import android.view.View
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


fun View.setRxOnClickListener(function: () -> Unit): Disposable {
    return RxView.clicks(this)
        .throttleFirst(500L, TimeUnit.MILLISECONDS)
        .subscribe {
            function()
        }
}

fun EditText.setRxOnEditTextChangeAfter(function: (String) -> Unit): Disposable {
    return RxTextView.afterTextChangeEvents(this)
        .throttleLast(500L, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .skip(1)
        .subscribe {
            it.editable()?.let { editable ->
                function(editable.toString())
            }
        }
}



