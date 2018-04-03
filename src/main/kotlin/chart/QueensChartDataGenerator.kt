package chart

import problem.Problem
import problem.QueensProblem

object QueensChartDataGenerator : ChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it) }
}
