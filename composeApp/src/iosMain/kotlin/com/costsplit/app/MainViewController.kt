package com.costsplit.app

import androidx.compose.ui.window.ComposeUIViewController
import com.costsplit.app.di.initKoin

fun MainViewController(): platform.UIKit.UIViewController {
    initKoin("http://localhost:8080/")
    return ComposeUIViewController { App() }
}
