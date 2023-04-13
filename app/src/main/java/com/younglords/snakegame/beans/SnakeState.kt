package com.younglords.snakegame.beans


import androidx.compose.ui.geometry.Size
import androidx.compose.runtime.Stable
import com.younglords.snakegame.ui.SnakeGameViewModel.Companion.COL_NUM
import com.younglords.snakegame.ui.SnakeGameViewModel.Companion.ROW_NUM

enum class GameState{
    WAITING, PLAYING, LOST
}


@Stable
class SnakeState(
    val snake: Snake,
    val size: Pair<Int, Int>,
    val blockSize: Size,
    val food: PointAxis,
    val gameState: GameState = GameState.WAITING,
    val difficulty: Float = 1f,
) {

    fun strikeWall(nextPos : PointAxis) =
        nextPos.x < 0 || nextPos.y < 0 || nextPos.x >= COL_NUM || nextPos.y >= ROW_NUM

    fun strikeSelf(nextPos: PointAxis) =
        snake.body.any { it == nextPos }

    fun getSleepTime() = (1000.0f / difficulty).toLong()

    fun getScore() = this.snake.body.size * 100

    fun copy(
        snake: Snake = this.snake,
        size: Pair<Int, Int> = this.size,
        blockSize: Size = this.blockSize,
        food: PointAxis = this.food,
        gameState: GameState = this.gameState,
        difficulty: Float = this.difficulty
    ) = SnakeState(snake, size, blockSize, food, gameState, difficulty = difficulty)
}

sealed class GameAction {
    data class MoveSnake(val direction: Directions) : GameAction()
    data class ChangeSize(val size: Pair<Int, Int>) : GameAction()
    object GameTick : GameAction()
    object StartGame : GameAction()
    object LoseGame : GameAction()
    object RestartGame : GameAction()
    object QuitGame : GameAction()

    override fun toString(): String {
        return when (this) {
            is MoveSnake -> "MoveSnake(direction=$direction)"
            is ChangeSize -> "ChangeSize(size=$size)"
            GameTick -> "GameTick"
            StartGame -> "StartGame"
            LoseGame -> "LoseGame"
            RestartGame -> "RestartGame"
            QuitGame -> "QuitGame"
        }
    }

}
