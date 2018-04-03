package algorithm

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import problem.Problem

internal interface FindFirstAbstractTest {

    val firstResults: List<Pair<Int, String?>>
    val problemInitializer: (n: Int) -> Problem

    @TestFactory
    fun `findFirst() via backtracking`() = testFindFirst { BacktrackingExecutor(it) }

    @TestFactory
    fun `findFirst() via forward checking`() = testFindFirst { ForwardCheckingExecutor(it) }

    private fun testFindFirst(executorInitializer: (Problem) -> CspExecutor) =
            firstResults.map { (n, expected) ->
                DynamicTest.dynamicTest("for n=$n gives $expected") {
                    val problem = problemInitializer(n)
                    val executor = executorInitializer(problem)
                    val result = executor.findFirst()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
