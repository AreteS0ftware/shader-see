package it.aretesoftware.shadersee.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.aretesoftware.shadersee.utils.ShaderVariablePrecision;
import it.aretesoftware.shadersee.utils.ShaderVariableQualifier;
import it.aretesoftware.shadersee.utils.ShaderVariableType;

public class DialogSource extends VisDialog {

    public DialogSource(String title, String shaderSource) {
        super(title);
        setCenterOnAdd(true);
        setResizable(true);
        addCloseButton();

        VisLabel label = new VisLabel(applyMarkup(shaderSource));
        label.getStyle().font.getData().markupEnabled = true;

        setSize(Math.min(600, label.getWidth() + 60), Math.min(600, label.getHeight() + 60));

        Table contentTable = getContentTable();
        VisScrollPane scrollPane = new VisScrollPane(label);
        contentTable.add(scrollPane);

    }

    private String applyMarkup(String shaderSource) {
        shaderSource = markupPreprocessor(shaderSource);
        shaderSource = markupPrecision(shaderSource);
        shaderSource = markupQualifier(shaderSource);
        shaderSource = markupKeywords(shaderSource);
        shaderSource = markupTypes(shaderSource);
        shaderSource = markupFunctions(shaderSource);
        shaderSource = markupNumbers(shaderSource); //TODO: missing bool (true/false) values!!!
        shaderSource = markupComments(shaderSource);
        return shaderSource;
    }

    private String markupPreprocessor(String shaderSource) {
        final String TAN = "[BROWN]";
        shaderSource = markup(shaderSource, "#ifdef", TAN);
        shaderSource = markup(shaderSource, "#else", TAN);
        shaderSource = markup(shaderSource, "#endif", TAN);
        shaderSource = markup(shaderSource, "#define", TAN);
        shaderSource = markup(shaderSource, "#line", TAN);
        shaderSource = markup(shaderSource, "#version", TAN);
        shaderSource = markup(shaderSource, "#extension", TAN);
        return shaderSource;
    }

    private String markupPrecision(String shaderSource) {
        final String FIREBRICK = "[FIREBRICK]";
        for (ShaderVariablePrecision precision : ShaderVariablePrecision.values()) {
            shaderSource = markup(shaderSource, precision.name(), FIREBRICK);
        }
        shaderSource = markup(shaderSource, "precision", FIREBRICK);
        return shaderSource;
    }

    private String markupQualifier(String shaderSource) {
        final String FIREBRICK = "[FIREBRICK]";
        for (ShaderVariableQualifier qualifier : ShaderVariableQualifier.values()) {
            shaderSource = markup(shaderSource, qualifier.name(), FIREBRICK);
        }
        return shaderSource;
    }

    private String markupKeywords(String shaderSource) {
        final String FIREBRICK = "[FIREBRICK]";
        shaderSource = shaderSource.replaceAll("if *\\(", FIREBRICK + "if [WHITE]\\(");
        shaderSource = shaderSource.replaceAll("else *\\{", FIREBRICK + "else [WHITE]\\{");
        shaderSource = shaderSource.replaceAll("for *\\(", FIREBRICK + "for [WHITE]\\(");
        shaderSource = shaderSource.replaceAll("do *\\{", FIREBRICK + "do [WHITE]\\{");
        shaderSource = shaderSource.replaceAll("while *\\(", FIREBRICK + "while [WHITE]\\(");
        shaderSource = shaderSource.replaceAll("const +", FIREBRICK + "const [WHITE]");
        shaderSource = shaderSource.replaceAll("return +", FIREBRICK + "return [WHITE]");
        return shaderSource;
    }

    private String markupTypes(String shaderSource) {
        final String FOREST = "[FOREST]";
        for (String type : ShaderVariableType.getTypesAsStrings()) {
            shaderSource = markup(shaderSource, type, FOREST);
        }
        return shaderSource;
    }

    private String markupFunctions(String shaderSource) {
        Array<String> functions = new Array<>(100);
        // Trigonometry
        functions.add("acos");
        functions.add("acosh");
        functions.add("asin");
        functions.add("asinh");
        functions.add("atan");
        functions.add("atanh");
        functions.add("cos");
        functions.add("cosh");
        functions.add("degrees");
        functions.add("radians");
        functions.add("sin");
        functions.add("sinh");
        functions.add("tan");
        functions.add("tanh");
        // Mathematics
        functions.add("abs");
        functions.add("ceil");
        functions.add("clamp");
        functions.add("dFdx");
        functions.add("dFdy");
        functions.add("exp");
        functions.add("exp2");
        functions.add("floor");
        functions.add("fma");
        functions.add("fract");
        functions.add("fwidth");
        functions.add("inversesqrt");
        functions.add("isinf");
        functions.add("isnan");
        functions.add("log");
        functions.add("log2");
        functions.add("max");
        functions.add("min");
        functions.add("mod");
        functions.add("modf");
        functions.add("noise");
        functions.add("pow");
        functions.add("round");
        functions.add("roundEven");
        functions.add("sign");
        functions.add("smoothstep");
        functions.add("sqrt");
        functions.add("step");
        functions.add("trunc");
        // Floating-Point
        functions.add("floatBitsToInt");
        functions.add("frexp");
        functions.add("intBitsToFloat");
        functions.add("ldexp");
        functions.add("packDouble2x32");
        functions.add("packHalf2x16");
        functions.add("packUnorm");
        functions.add("unpackDouble2x32");
        functions.add("unpackHalf2x16");
        functions.add("unpackUnorm");
        // Built-In Variables
        // TODO: add
        // Vector
        functions.add("cross");
        functions.add("distance");
        functions.add("dot");
        functions.add("equal");
        functions.add("faceforward");
        functions.add("length");
        functions.add("normalize");
        functions.add("notEqual");
        functions.add("reflect");
        functions.add("refract");
        // Component Comparison
        functions.add("all");
        functions.add("any");
        functions.add("greaterThan");
        functions.add("greaterThanEqual");
        functions.add("lessThan");
        functions.add("lessThanEqual");
        functions.add("not");
        // Geometry Shader
        // TODO: add
        // Texture Sampling
        functions.add("interpolateAtCentroid");
        functions.add("interpolateAtOffset");
        functions.add("interpolateAtSample");
        functions.add("texelFetch");
        functions.add("texelFetchOffset");
        functions.add("texture");
        functions.add("texture2D");
        functions.add("textureGather");
        functions.add("textureGatherOffset");
        functions.add("textureGatherOffsets");
        functions.add("textureGrad");
        functions.add("textureGradOffset");
        functions.add("textureLod");
        functions.add("textureLodOffset");
        functions.add("textureOffset");
        functions.add("textureProj");
        functions.add("textureProjGrad");
        functions.add("textureProjGradOffset");
        functions.add("textureProjLod");
        functions.add("textureProjLodOffset");
        functions.add("textureProjOffset");
        functions.add("textureQueryLevels");
        functions.add("textureQueryLod");
        functions.add("textureSamples");
        functions.add("textureSize");
        // Matrix
        functions.add("determinant");
        functions.add("groupMemoryBarrier");
        functions.add("inverse");
        functions.add("matrixCompMult");
        functions.add("outerProduct");
        functions.add("transpose");
        // Integer
        functions.add("bitCount");
        functions.add("bitfieldExtract");
        functions.add("bitfieldInsert");
        functions.add("bitfieldReverse");
        functions.add("findLSB");
        functions.add("findMSB");
        functions.add("uaddCarry");
        functions.add("umulExtended");
        functions.add("usubBorrow");
        // Image
        functions.add("imageAtomicAdd");
        functions.add("imageAtomicAnd");
        functions.add("imageAtomicCompSwap");
        functions.add("imageAtomicExchange");
        functions.add("imageAtomicMax");
        functions.add("imageAtomicMin");
        functions.add("imageAtomicOr");
        functions.add("imageAtomicXor");
        functions.add("imageLoad");
        functions.add("imageSamples");
        functions.add("imageSize");
        functions.add("imageStore");
        // Atomic
        functions.add("atomicAdd");
        functions.add("atomicAnd");
        functions.add("atomicCompSwap");
        functions.add("atomicCounter");
        functions.add("atomicCounterDecrement");
        functions.add("atomicCounterIncrement");
        functions.add("atomicExchange");
        functions.add("atomicMax");
        functions.add("atomicMin");
        functions.add("atomicOr");
        functions.add("atomicXor");
        // Memory Barrier
        functions.add("barrier");
        functions.add("groupMemoryBarrier");
        functions.add("memoryBarrier");
        functions.add("memoryBarrierAtomicCounter");
        functions.add("memoryBarrierBuffer");
        functions.add("memoryBarrierImage");
        functions.add("memoryBarrierShared");

        final String ROYAL = "[ROYAL]";
        for (String function : functions) {
            shaderSource = shaderSource.replaceAll(function + " *\\(", ROYAL + function + "[WHITE]\\(");
        }
        return shaderSource;
    }

    private String markupNumbers(String shaderSource) {
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
        Matcher matcher = pattern.matcher(shaderSource);
        int indexOffset = 0;
        while (matcher.find()) {
            String value = matcher.group();
            int valueIndex = matcher.start() + indexOffset;

            String before = shaderSource.substring(0, valueIndex);
            boolean variableName = false;
            for (int charIndex = before.length() - 3; charIndex < before.length(); charIndex++) {
                char character = before.charAt(charIndex);
                if (Character.isAlphabetic(character)) {
                    variableName = true;
                    break;
                }
            }
            if (variableName) {
                continue;
            }

            String middle = "[PURPLE]" + value + "[WHITE]";
            String after = shaderSource.substring(valueIndex + value.length());

            StringBuilder builder = new StringBuilder();
            builder.append(before);
            builder.append(middle);
            builder.append(after);
            shaderSource = builder.toString();

            indexOffset += (middle.length() - value.length());
        }
        return shaderSource;
    }

    private String markupComments(String shaderSource) {
        Pattern pattern = Pattern.compile("(\\/\\/ *.*\\n+)");
        Matcher matcher = pattern.matcher(shaderSource);
        while (matcher.find()) {
            shaderSource = markup(shaderSource, matcher.group(), "[ROYAL]");
        }
        return shaderSource;
    }

    private String markup(String shaderSource, String value, String color) {
        return shaderSource.replaceAll(value, color + value + "[WHITE]");
    }
}
