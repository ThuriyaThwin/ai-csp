import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal interface CountAllAbstractTest {

    val problemInitializer: (n: Int) -> Problem
    val allResultsCounts: List<Pair<Int, Int>>

    @TestFactory
    fun `countAll() via backtracking`() = testCountAll { BacktrackingExecutor(it) }

    @TestFactory
    fun `countAll() via forward checking`() = testCountAll { ForwardCheckingExecutor(it) }

    private fun testCountAll(executorInitializer: (Problem) -> CspExecutor) =
            allResultsCounts.map { (n, expected) ->
                DynamicTest.dynamicTest("for n=$n gives $expected") {
                    val problem = problemInitializer(n)
                    val executor = executorInitializer(problem)
                    val result = executor.countAll()
                    assertThat(result).isEqualTo(expected)
                }
            }
}
