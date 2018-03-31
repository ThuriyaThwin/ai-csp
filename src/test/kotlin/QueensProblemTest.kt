import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class QueensProblemTest {

    @Test
    fun updateDomains() {
        val problem = QueensProblem(1)
        val newProblem = problem.updateDomains(0, 1)
        val newDomains = newProblem.domains
        val expectedDomains = listOf(listOf(1))
        assertThat(newDomains).isEqualTo(expectedDomains)
    }
}