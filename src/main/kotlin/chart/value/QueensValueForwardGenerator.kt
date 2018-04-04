package chart.value

import algorithm.CspExecutor
import algorithm.ForwardCheckingExecutor
import chart.HeuristicsChartDataGenerator
import problem.Problem
import problem.QueensProblem
import problem.middleValueBoard

object QueensValueForwardGenerator : HeuristicsChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, board = middleValueBoard(it)) }
    override val executorInitializer: (Problem) -> CspExecutor = { ForwardCheckingExecutor(it) }
}
