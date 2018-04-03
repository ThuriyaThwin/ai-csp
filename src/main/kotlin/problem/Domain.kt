package problem

data class Domain(
    private val list: List<Int>,
    private val iteratorInitializer: (list: List<Int>) -> Iterator<Int>
) {

    constructor(size: Int, iteratorInitializer: (list: List<Int>) -> Iterator<Int> = { it.iterator() }) : this(
        List(size) { it + 1 },
        iteratorInitializer
    )

    fun isEmpty() = list.isEmpty()

    operator fun minus(value: Int) = copy(list = list - value)

    operator fun iterator(): Iterator<Int> = iteratorInitializer(list)
}
