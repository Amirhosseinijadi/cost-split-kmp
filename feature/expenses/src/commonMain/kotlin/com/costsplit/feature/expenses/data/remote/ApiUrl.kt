package com.costsplit.feature.expenses.data.remote

internal fun String.apiUrl(path: String): String =
    trimEnd('/') + "/" + path.trimStart('/')
