inline fun <T> List<T>.copy(mutatorBlock: MutableList<T>.() -> Unit): List<T> {
    return toMutableList().apply(mutatorBlock)
}