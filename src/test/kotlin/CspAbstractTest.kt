import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal abstract class CspAbstractTest {

    abstract val firstResults: List<Pair<Int, String?>>
    abstract val problemInitializer: (Int) -> Problem
    abstract val allResultsCounts: List<Pair<Int, Int>>

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
