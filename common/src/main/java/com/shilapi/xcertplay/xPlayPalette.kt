package com.shilapi.xcertplay

import android.graphics.Color

/**
 * Semantic colors for xPlay-owned chrome. Media canvases and cluster-specific palettes stay separate.
 *
 * The two palettes are X管家's own: a light blue day theme and a deep blue night theme rather than
 * a pure black one, so xPlay reads as part of the head unit's UI. Light or dark, [systemBar] mirrors
 * [background] and [systemBarIconsAreDark] tells the system bars which icon set to draw.
 */
internal data class xPlayPalette(
    val background: Int,
    val surface: Int,
    val button: Int,
    val outline: Int,
    val railSelected: Int,
    val railSelectedOutline: Int,
    val primaryText: Int,
    val secondaryText: Int,
    val accent: Int,
    val onAccent: Int,
    val accentTrack: Int,
    val trackOff: Int,
    val warning: Int,
    val success: Int,
    val danger: Int,
    val ripple: Int,
    val focusRing: Int,
    val systemBar: Int,
    val systemBarIconsAreDark: Boolean,
    val overlayBackground: Int,
    val overlayPrimaryText: Int,
    val overlaySecondaryText: Int,
    val overlayAccent: Int,
    val overlayOnAccent: Int,
    val overlayAccentTrack: Int,
    val overlayTrackOff: Int,
    val overlayDanger: Int,
) {
    companion object {
        fun of(night: Boolean): xPlayPalette = if (night) DARK else LIGHT

        /** X管家's night theme: deep blue surfaces (#2E2F56..#282B42, cards #3A3A5A), light text. */
        val DARK = xPlayPalette(
            background = Color.rgb(43, 45, 76),
            surface = Color.rgb(58, 58, 90),
            button = Color.rgb(66, 68, 107),
            outline = Color.rgb(79, 81, 128),
            railSelected = Color.rgb(60, 70, 119),
            railSelectedOutline = Color.rgb(93, 107, 166),
            primaryText = Color.rgb(255, 255, 255),
            secondaryText = Color.rgb(185, 190, 218),
            accent = Color.rgb(143, 184, 248),
            onAccent = Color.rgb(27, 29, 51),
            accentTrack = Color.rgb(127, 163, 236),
            trackOff = Color.rgb(74, 76, 114),
            warning = Color.rgb(255, 196, 128),
            success = Color.rgb(76, 195, 138),
            danger = Color.rgb(224, 108, 108),
            ripple = 0x338FB8F8,
            focusRing = Color.rgb(143, 184, 248),
            systemBar = Color.rgb(43, 45, 76),
            systemBarIconsAreDark = false,
            overlayBackground = Color.rgb(32, 34, 58),
            overlayPrimaryText = Color.rgb(255, 255, 255),
            overlaySecondaryText = Color.rgb(185, 190, 218),
            overlayAccent = Color.rgb(143, 184, 248),
            overlayOnAccent = Color.rgb(27, 29, 51),
            overlayAccentTrack = Color.rgb(127, 163, 236),
            overlayTrackOff = Color.rgb(74, 76, 114),
            overlayDanger = Color.rgb(224, 108, 108),
        )

        /** X管家's day theme: the #DBE6FD..#BDD6FF blue canvas, cards #EAF1FF, navy text. */
        val LIGHT = xPlayPalette(
            background = Color.rgb(219, 230, 253),
            surface = Color.rgb(234, 241, 255),
            button = Color.rgb(203, 220, 246),
            outline = Color.rgb(165, 185, 216),
            railSelected = Color.rgb(199, 219, 251),
            railSelectedOutline = Color.rgb(134, 161, 201),
            primaryText = Color.rgb(31, 42, 68),
            secondaryText = Color.rgb(85, 98, 122),
            accent = Color.rgb(31, 95, 207),
            onAccent = Color.WHITE,
            accentTrack = Color.rgb(31, 95, 207),
            trackOff = Color.rgb(188, 203, 230),
            warning = Color.rgb(138, 82, 0),
            success = Color.rgb(27, 107, 62),
            danger = Color.rgb(169, 50, 50),
            ripple = 0x331F5FCF,
            focusRing = Color.rgb(31, 95, 207),
            systemBar = Color.rgb(219, 230, 253),
            systemBarIconsAreDark = true,
            overlayBackground = Color.rgb(242, 247, 255),
            overlayPrimaryText = Color.rgb(31, 42, 68),
            overlaySecondaryText = Color.rgb(85, 98, 122),
            overlayAccent = Color.rgb(31, 95, 207),
            overlayOnAccent = Color.WHITE,
            overlayAccentTrack = Color.rgb(31, 95, 207),
            overlayTrackOff = Color.rgb(188, 203, 230),
            overlayDanger = Color.rgb(169, 50, 50),
        )
    }
}
