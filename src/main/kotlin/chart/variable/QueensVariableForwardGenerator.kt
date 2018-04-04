package chart.variable

import algorithm.CspExecutor
import algorithm.ForwardCheckingExecutor
import chart.HeuristicsChartDataGenerator
import chooser.MiddleChooser
import problem.Problem
import problem.QueensProblem

object QueensVariableForwardGenerator : HeuristicsChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, MiddleChooser(it)) }
    override val executorInitializer: (Problem) -> CspExecutor = { ForwardCheckingExecutor(it) }
}
