package algorithm

import chooser.MiddleChooser
import problem.Problem
import problem.QueensProblem
import problem.middleValueBoard

internal class QueensProblemMiddleValueIT : CountAllAbstractTest {

    override val problemInitializer: (n: Int) -> Problem =
        { QueensProblem(it, MiddleChooser(it), middleValueBoard(it)) }

    override val allResultsCounts: List<Pair<Int, Int>> = allQueensResultsCounts
}