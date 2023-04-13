package com.younglords.snakegame.ui

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import com.younglords.snakegame.beans.Directions
import kotlin.math.abs


@Stable
fun Modifier.square() = this.layout { measurable, constraints ->
    val size = minOf(constraints.maxWidth, constraints.maxHeight)
    layout(size, size) {
        val placeable = measurable.measure(
            constraints
                .copy(
                    minWidth = size,
                    minHeight = size,
                    maxWidth = size,
                    maxHeight = size
                )
        )
        placeable.placeRelative(0, 0)
    }

}

@Stable
fun Modifier.detectDirectionalMove(
    validMoveDelta: Float = 50f,
    updateDirection: (Directions) -> Unit,
) = this.pointerInput(Unit) {

    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        val start = down.position
        val up = waitForUpOrCancellation()
        up?.position?.let { end ->
            val dx = end.x - start.x
            val dy = end.y - start.y
            if (abs(dx) > validMoveDelta && abs(dx) > abs(dy)){
                updateDirection(
                    if (dx > 0) Directions.RIGHT else Directions.LEFT
                )
            }else if (abs(dy) > validMoveDelta){
                updateDirection(
                    if (dy > 0) Directions.DOWN else Directions.UP
                )
            }
        }
    }

}
