package com.jt17.fakestorecompose.core.ui.components

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.jt17.fakestorecompose.R


fun primaryFont(): FontFamily {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontSaira = GoogleFont(name = "Syne")

    val primaryFont = FontFamily(
        Font(googleFont = fontSaira, fontProvider = provider)
    )

    return primaryFont

}