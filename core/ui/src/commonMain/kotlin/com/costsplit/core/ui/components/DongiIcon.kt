package com.costsplit.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class DongiIcon {
    Home,
    Groups,
    Activity,
    Settings,
    Add,
    Receipt,
    Settle,
    Chevron,
}

@Composable
fun DongiGlyph(
    icon: DongiIcon,
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = 22.dp,
) {
    Canvas(modifier = modifier.size(size)) {
        val stroke = size.toPx() * 0.075f
        val line = Stroke(width = stroke, cap = StrokeCap.Round)
        val w = this.size.width
        val h = this.size.height

        when (icon) {
            DongiIcon.Home -> {
                val roof = Path().apply {
                    moveTo(w * .16f, h * .46f)
                    lineTo(w * .5f, h * .18f)
                    lineTo(w * .84f, h * .46f)
                }
                drawPath(roof, color, style = line)
                drawRoundRect(
                    color = color,
                    topLeft = Offset(w * .23f, h * .42f),
                    size = Size(w * .54f, h * .42f),
                    cornerRadius = CornerRadius(w * .08f),
                    style = line,
                )
            }

            DongiIcon.Groups -> {
                drawCircle(color, w * .14f, Offset(w * .5f, h * .34f), style = line)
                drawCircle(color, w * .105f, Offset(w * .25f, h * .44f), style = line)
                drawCircle(color, w * .105f, Offset(w * .75f, h * .44f), style = line)
                drawArc(color, 205f, 130f, false, Offset(w * .27f, h * .49f), Size(w * .46f, h * .36f), style = line)
            }

            DongiIcon.Activity -> {
                drawLine(color, Offset(w * .22f, h * .3f), Offset(w * .78f, h * .3f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .22f, h * .5f), Offset(w * .62f, h * .5f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .22f, h * .7f), Offset(w * .72f, h * .7f), stroke, StrokeCap.Round)
                drawCircle(color, stroke * .7f, Offset(w * .82f, h * .7f))
            }

            DongiIcon.Settings -> {
                drawCircle(color, w * .3f, Offset(w * .5f, h * .5f), style = line)
                drawCircle(color, w * .09f, Offset(w * .5f, h * .5f), style = line)
                repeat(4) { index ->
                    val vertical = index % 2 == 0
                    val start = if (vertical) Offset(w * .5f, h * .08f) else Offset(w * .08f, h * .5f)
                    val end = if (vertical) Offset(w * .5f, h * .2f) else Offset(w * .2f, h * .5f)
                    val flip = index >= 2
                    drawLine(
                        color,
                        if (flip) Offset(w - start.x, h - start.y) else start,
                        if (flip) Offset(w - end.x, h - end.y) else end,
                        stroke,
                        StrokeCap.Round,
                    )
                }
            }

            DongiIcon.Add -> {
                drawLine(color, Offset(w * .5f, h * .22f), Offset(w * .5f, h * .78f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .22f, h * .5f), Offset(w * .78f, h * .5f), stroke, StrokeCap.Round)
            }

            DongiIcon.Receipt -> {
                drawRoundRect(color, Offset(w * .24f, h * .12f), Size(w * .52f, h * .76f), CornerRadius(w * .06f), style = line)
                drawLine(color, Offset(w * .35f, h * .38f), Offset(w * .65f, h * .38f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .35f, h * .58f), Offset(w * .58f, h * .58f), stroke, StrokeCap.Round)
            }

            DongiIcon.Settle -> {
                drawCircle(color, w * .31f, Offset(w * .5f, h * .5f), style = line)
                drawLine(color, Offset(w * .35f, h * .5f), Offset(w * .65f, h * .5f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .58f, h * .4f), Offset(w * .68f, h * .5f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .58f, h * .6f), Offset(w * .68f, h * .5f), stroke, StrokeCap.Round)
            }

            DongiIcon.Chevron -> {
                drawLine(color, Offset(w * .62f, h * .25f), Offset(w * .38f, h * .5f), stroke, StrokeCap.Round)
                drawLine(color, Offset(w * .38f, h * .5f), Offset(w * .62f, h * .75f), stroke, StrokeCap.Round)
            }
        }
    }
}
