package com.costsplit.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.costsplit.app.di.initKoin

fun main() {
    initKoin("http://localhost:8080/")
    application {
        Window(onCloseRequest = ::exitApplication, title = "Cost Split") { App() }
    }
}

