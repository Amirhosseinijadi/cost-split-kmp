package com.costsplit.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.costsplit.app.di.initKoin
import com.costsplit.core.ui.strings.DongiString
import com.costsplit.core.ui.strings.getDongiString
import kotlinx.coroutines.runBlocking

fun main() {
    initKoin("https://coast-split.darkube.ir")
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = runBlocking { getDongiString(DongiString.AppName) },
        ) { App() }
    }
}
