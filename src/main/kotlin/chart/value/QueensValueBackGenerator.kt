package chart.value

import algorithm.BacktrackingExecutor
import algorithm.CspExecutor
import chart.HeuristicsChartDataGenerator
import problem.Problem
import problem.QueensProblem
import problem.middleValueBoard

object QueensValueBackGenerator : HeuristicsChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, board = middleValueBoard(it)) }
    override val executorInitializer: (Problem) -> CspExecutor = { BacktrackingExecutor(it) }
}
