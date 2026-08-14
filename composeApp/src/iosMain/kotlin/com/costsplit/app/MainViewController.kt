package com.costsplit.app

import androidx.compose.ui.window.ComposeUIViewController
import com.costsplit.app.di.initKoin

fun MainViewController(): platform.UIKit.UIViewController {
    initKoin("https://coast-split.darkube.ir")
    return ComposeUIViewController { App() }
}
