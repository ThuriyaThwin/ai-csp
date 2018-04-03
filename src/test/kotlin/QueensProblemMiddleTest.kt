internal class QueensProblemMiddleTest : CountAllAbstractTest {

    override val problemInitializer: (n: Int) -> Problem = { QueensProblem(it, MiddleChooser(it)) }

    override val allResultsCounts: List<Pair<Int, Int>> = allQueensResultsCounts
}