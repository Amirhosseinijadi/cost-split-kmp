package com.costsplit.core.network.remote

fun String.apiUrl(path: String): String =
    trimEnd('/') + "/" + path.trimStart('/')
