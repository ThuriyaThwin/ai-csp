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

val allQueensResultsCounts = listOf(
        1 to 1,
        2 to 0,
        3 to 0,
        4 to 2,
        5 to 10,
        6 to 4,
        7 to 40,
        8 to 92,
        9 to 352,
        10 to 724,
        11 to 2680
//            12 to 14200,
//            13 to 73712
)
