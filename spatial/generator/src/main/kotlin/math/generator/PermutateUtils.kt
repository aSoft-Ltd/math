package math.generator

fun <E> List<E>.permutations(): List<Pair<E, E>> {
    val permutations = mutableListOf<Pair<E, E>>()
    for (ex in this) {
        for (ey in this) {
            permutations.add(ex to ey)
        }
    }
    return permutations
}
