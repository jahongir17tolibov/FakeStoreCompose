package com.jt17.fakestorecompose.core.ui.components

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.jt17.fakestorecompose.R

fun primaryFont(font: String = "Syne"): FontFamily {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val mainFont = GoogleFont(name = font)

    return FontFamily(
        Font(googleFont = mainFont, fontProvider = provider)
    )

}