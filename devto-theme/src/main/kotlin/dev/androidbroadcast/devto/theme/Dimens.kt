package dev.androidbroadcast.devto.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

open class Dimens {

    val screenPaddings = ContentPaddings(16.dp, 16.dp)
    val contentPaddings = ContentPaddings(8.dp, 8.dp)
    val paddings = Paddings()

    class ContentPaddings(
        val start: Dp,
        val end: Dp,
        val top: Dp,
        val bottom: Dp,
    ) {

        constructor(horizontal: Dp, vertical: Dp) : this(
            start = horizontal,
            end = horizontal,
            top = vertical,
            bottom = vertical,
        )
    }

    class Paddings {
        val default = 8.dp
        val small = 4.dp
        val large = 16.dp
    }

    companion object : Dimens()
}

fun Modifier.screenContentPaddings(
    start: Dp = Dimens.contentPaddings.start,
    end: Dp = Dimens.contentPaddings.end,
    top: Dp = Dimens.contentPaddings.top,
    bottom: Dp = Dimens.contentPaddings.bottom,
): Modifier {
    return padding(start, top, end, bottom)
}

fun Modifier.contentPaddings(
    start: Dp = Dimens.contentPaddings.start,
    end: Dp = Dimens.contentPaddings.end,
    top: Dp = Dimens.contentPaddings.top,
    bottom: Dp = Dimens.contentPaddings.bottom,
): Modifier {
    return padding(start, top, end, bottom)
}
