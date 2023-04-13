package com.younglords.snakegame.beans

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import java.util.LinkedList

enum class Directions {
    RIGHT, UP, DOWN, LEFT
}

@Stable
data class PointAxis(
    val x: Int,
    val y: Int
    ) {
    override fun toString(): String {
        return "Point(x=$x,y=$y)"
    }

    fun asOffset(blockSize: Size) = Offset(x.times(blockSize.width), y.times(blockSize.height))
}

@Stable
data class Snake(
    val body : LinkedList<PointAxis>,
    val bodySize:Float,
    val direction: Directions
){

    val head : PointAxis
    get() = body.first

    private val headPlace
    get() = head

    fun nextPos() = when(direction){
        Directions.RIGHT -> PointAxis(headPlace.x.plus(1), headPlace.y)
        Directions.LEFT -> PointAxis(headPlace.x.minus(1), headPlace.y)
        Directions.UP -> PointAxis(headPlace.x, headPlace.y.minus(1))
        Directions.DOWN -> PointAxis(headPlace.x, headPlace.y.plus(1))
    }

    fun sizeGrow(pos:PointAxis) = this.apply {
        body.addFirst(pos)
    }

    fun moveDirection(pos:PointAxis) = this.copy(
        body = this.body.apply {
            body.removeLast()
            body.addFirst(pos)
        }
    )

    fun changeDirection(newDirection : Directions) =
        if (direction.ordinal.plus(newDirection.ordinal) != 3){
            this.copy(direction = newDirection)
        }else this

}