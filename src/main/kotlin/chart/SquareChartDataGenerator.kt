package chart

import problem.LatinSquareProblem
import problem.Problem

object SquareChartDataGenerator : ChartDataGenerator() {
    override val problemInitializer: (n: Int) -> Problem = { LatinSquareProblem(it) }
}
