import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LatinSquareProblemTest {

    @Test
    fun areConstrainsSatisfied() {
        val problem = LatinSquareProblem(2)
        problem.setVariable(0, 1)
        val result = problem.areConstrainsSatisfied(0, 1)
        assertThat(result).isEqualTo(true)
    }
}