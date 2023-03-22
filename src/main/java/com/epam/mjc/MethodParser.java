package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> methodTokens = StringSplitter.splitByDelimiters(signatureString, List.of("(",")"));
        List<MethodSignature.Argument> methodArgs = List.of();

        if(methodTokens.size() > 1) {
            methodArgs = parseMethodArguments(methodTokens.get(1));
        }

        String[] methodAttributes = methodTokens.get(0).split(" ");
        String accessModifier, returnType, methodName;
        if(methodAttributes.length > 2){
            accessModifier = methodAttributes[0];
            returnType = methodAttributes[1];
            methodName = methodAttributes[2];
        } else {
            accessModifier = null;
            returnType = methodAttributes[0];
            methodName = methodAttributes[1];
        }
        MethodSignature ret = new MethodSignature(methodName, methodArgs);
        ret.setAccessModifier(accessModifier);
        ret.setReturnType(returnType);
        return ret;
    }

    private static MethodSignature.Argument toArgument(String s){
        String[] argParsed = s.split(" ");
        return new MethodSignature.Argument(argParsed[0],argParsed[1]);
    }

    private static List<MethodSignature.Argument> parseMethodArguments(String s){
        return StringSplitter.splitByDelimiters(s, List.of(","))
                .stream()
                .map(String::trim)
                .map(MethodParser::toArgument )
                .collect(Collectors.toList());
    }
}
