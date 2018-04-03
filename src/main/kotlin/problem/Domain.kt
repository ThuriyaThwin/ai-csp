package problem

data class Domain(private val list: List<Int>) {

    constructor(size: Int) : this(List(size) { it + 1 })

    fun isEmpty() = list.isEmpty()

    operator fun minus(value: Int) = Domain(list - value)

    operator fun iterator(): Iterator<Int> {
        return list.iterator()
    }
}
