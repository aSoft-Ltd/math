package math.generator

fun generateAllImmutableBinaryOperation(
    params: List<NumberType>, defs: List<SpatialDef>
): List<SourceFile> = mutableListOf<SourceFile>().apply {
    addAll(generateImmutableBinaryOperations(params, defs, "plus", "+"))
    addAll(generateImmutableBinaryOperations(params, defs, "minus", "-"))
    addAll(generateImmutableBinaryOperations(params, defs, "times", "*"))
    addAll(generateImmutableBinaryOperations(params, defs, "div", "/"))
}