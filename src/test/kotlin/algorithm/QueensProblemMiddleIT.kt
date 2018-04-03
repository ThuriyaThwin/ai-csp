package algorithm

import chooser.MiddleChooser
import problem.Problem
import problem.QueensProblem

internal class QueensProblemMiddleIT : CountAllAbstractTest {

    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, MiddleChooser(it)) }

    override val allResultsCounts: List<Pair<Int, Int>> = allQueensResultsCounts
}