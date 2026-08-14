package com.costsplit.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.costsplit.app.di.initKoin

fun main() {
    initKoin("https://coast-split.darkube.ir")
    application {
        Window(onCloseRequest = ::exitApplication, title = "دُنگی") { App() }
    }
}
