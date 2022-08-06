package math.generator

fun generateAllMutableBinaryOperation(
    params: List<NumberType>, defs: List<SpatialDef>
): List<SourceFile> = mutableListOf<SourceFile>().apply {
    addAll(generateMutableBinaryOperations(params, defs, "plus", "+"))
    addAll(generateMutableBinaryOperations(params, defs, "minus", "-"))
    addAll(generateMutableBinaryOperations(params, defs, "times", "*"))
    addAll(generateMutableBinaryOperations(params, defs, "div", "/"))
}