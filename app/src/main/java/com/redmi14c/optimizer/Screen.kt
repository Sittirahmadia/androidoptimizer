package com.redmi14c.optimizer

sealed class Screen(val route: String, val title: String, val icon: String) {
    object Dashboard : Screen("dashboard", "Dashboard", "dashboard")
    object Gaming : Screen("gaming", "Gaming", "sports_esports")
    object Tweaks : Screen("tweaks", "Tweaks", "tune")
    object Advanced : Screen("advanced", "Advanced", "settings")
    object StringsDB : Screen("strings_db", "Strings DB", "storage")
    object Profile : Screen("profile", "Profile", "person")

    companion object {
        val items = listOf(Dashboard, Gaming, Tweaks, Advanced, StringsDB, Profile)
    }
}
