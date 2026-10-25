package com.shilapi.xcertplay.orchestration

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * The car's own home screen, for xPlay's "Car home" control and for CarPlay's car icon.
 *
 * On the X管家 head unit the car's home is X管家's 互联 (link) page, not the Android home screen: the
 * HOME intent lands on the car's personal-centre page there. X管家's exported LaunchRouter is its
 * launcher entry and forwards [XBUTLER_EXTRA_TAB] to ConsoleActivity, which switches to the tab
 * named in it. Anywhere without X管家, or when the launch fails, the Android home screen stays the
 * fallback, so neither entry point breaks on a head unit that does not ship X管家.
 */
object CarHomeLauncher {
    /** X管家, the head unit's own launcher and hub. */
    const val XBUTLER_PACKAGE = "com.xbutler"

    /** The extra X管家's LaunchRouter forwards to ConsoleActivity. */
    const val XBUTLER_EXTRA_TAB = "com.xbutler.intent.extra.TAB"

    /** ConsoleActivity's 互联 tab, which lists xPlay and the other phone-projection entries. */
    const val XBUTLER_TAB_LINK = "LINK"

    private const val TAG = "xPlay-CarHome"

    /**
     * Opens the car's home screen: X管家's 互联 page when it is installed, else the Android home
     * screen. Never throws; returns whether a screen was launched.
     */
    fun open(context: Context, log: (String) -> Unit = {}): Boolean {
        if (openXbutlerLinkPage(context, log)) return true
        return runCatching {
            context.startActivity(
                Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            )
            true
        }.onFailure { report(log, "car home screen could not open: ${it.javaClass.simpleName}") }
            .getOrDefault(false)
    }

    /** Opens X管家 on its 互联 page; false when it is not installed or refuses the launch. */
    fun openXbutlerLinkPage(context: Context, log: (String) -> Unit = {}): Boolean {
        val launch = runCatching { context.packageManager.getLaunchIntentForPackage(XBUTLER_PACKAGE) }
            .getOrNull() ?: return false
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        launch.putExtra(XBUTLER_EXTRA_TAB, XBUTLER_TAB_LINK)
        return runCatching {
            context.startActivity(launch)
            true
        }.onFailure { report(log, "X管家 互联 page could not open: ${it.javaClass.simpleName}") }
            .getOrDefault(false)
    }

    private fun report(log: (String) -> Unit, message: String) {
        Log.w(TAG, message)
        log(message)
    }
}
