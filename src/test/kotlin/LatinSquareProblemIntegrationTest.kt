internal class LatinSquareProblemIntegrationTest : CspAbstractTest()  {

    override val problemInitializer: (Int) -> Problem = { LatinSquareProblem(it) }

    override val firstResults = listOf(
            1 to "[1]",
            2 to null,
            3 to null,
            4 to "[2, 4, 1, 3]",
            5 to "[1, 3, 5, 2, 4]"
    )

    override val allResultsCounts = listOf(
            1 to 1,
            2 to 2,
            3 to 12,
            4 to 576,
            5 to 161_280,
            6 to 812_851_200
    )
}
