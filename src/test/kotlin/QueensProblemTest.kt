import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class QueensProblemTest {

    @Test
    fun updateDomains() {
        val problem = QueensProblem(1)
        val newProblem = problem.updateDomains(0, 1)
        val newDomain = newProblem.domainOfVariable(0)
        val expectedDomain = listOf(1)
        assertThat(newDomain).isEqualTo(expectedDomain)
    }
}