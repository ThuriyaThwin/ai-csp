package chart.variable

import algorithm.BacktrackingExecutor
import algorithm.CspExecutor
import chart.HeuristicsChartDataGenerator
import chooser.MiddleChooser
import problem.Problem
import problem.QueensProblem

object QueensVariableBackGenerator : HeuristicsChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, MiddleChooser(it)) }
    override val executorInitializer: (Problem) -> CspExecutor = { BacktrackingExecutor(it) }
}
