internal class LatinSquareProblemIntegrationTest : CspAbstractTest()  {

    override val problemInitializer: (Int) -> Problem = { LatinSquareProblem(it) }

    override val firstResults = listOf(
            1 to "[[1]]",
            2 to "[[1, 2], [2, 1]]",
            3 to "[[1, 2, 3], [2, 3, 1], [3, 1, 2]]",
            4 to "[[1, 2, 3, 4], [2, 1, 4, 3], [3, 4, 1, 2], [4, 3, 2, 1]]",
            5 to "[[1, 2, 3, 4, 5], [2, 1, 4, 5, 3], [3, 4, 5, 1, 2], [4, 5, 2, 3, 1], [5, 3, 1, 2, 4]]"
    )

    override val allResultsCounts = listOf(
            1 to 1,
            2 to 2,
            3 to 12,
            4 to 576
//            5 to 161_280
//            6 to 812_851_200
    )
}
