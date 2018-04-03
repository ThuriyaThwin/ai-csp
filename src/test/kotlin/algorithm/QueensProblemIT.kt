package algorithm

import problem.Problem
import problem.QueensProblem

internal class QueensProblemIntegrationTest : CspAbstractTest() {

    override val problemInitializer: (Int) -> Problem = { QueensProblem(it) }

    override val firstResults = listOf(
        1 to "[1]",
        2 to null,
        3 to null,
        4 to "[2, 4, 1, 3]",
        5 to "[1, 3, 5, 2, 4]"
    )

    override val allResultsCounts = allQueensResultsCounts
}
