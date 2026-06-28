plugins { base }

allprojects {
    configurations.configureEach {
        val isNativeKmpConfiguration = listOf("ios", "apple", "native").any {
            name.contains(it, ignoreCase = true)
        }
        if (isNativeKmpConfiguration) {
            resolutionStrategy.force(
                "androidx.lifecycle:lifecycle-common:2.9.1",
                "androidx.lifecycle:lifecycle-runtime:2.9.1",
                "androidx.lifecycle:lifecycle-viewmodel:2.9.1",
            )
        }
    }
}
