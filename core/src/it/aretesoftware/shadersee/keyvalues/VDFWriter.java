package it.aretesoftware.shadersee.keyvalues;

import java.util.Map;
import java.util.Set;

public class VDFWriter {

    public VDFWriter() {

    }

    public String write(VDFNode root) {
        return write(root, new StringBuilder(root.size()), 0);
    }

    private String write(VDFNode root, StringBuilder builder, int tabAmount) {
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < tabAmount; i++) {
            tab.append("\t");
        }

        Set<Map.Entry<String, Object[]>> entries = root.entrySet();
        for (Map.Entry<String, Object[]> entry : entries) {
            String key = entry.getKey();
            Object[] value = entry.getValue();

            builder.append(tab);
            builder.append("\"").append(key).append("\"");
            builder.append(" ");
            for (int i = 0; i < value.length; i++) {
                Object obj = value[i];
                if (!(obj instanceof VDFNode)) {
                    if (i > 0) {
                        builder.append(tab).append("\"").append(key).append("\"");
                        builder.append(" ");
                    }
                    builder.append("\"").append(obj).append("\"");
                    if (i < value.length - 1) {
                        builder.append("\n");
                    }
                }
                else {
                    VDFNode node = (VDFNode) obj;
                    if (i > 0) {
                        builder.append("\n").append(tab).append("\"").append(key).append("\"");
                        builder.append(" ");
                    }
                    builder.append("{");
                    if (!node.isEmpty()) {
                        builder.append("\n");
                        tabAmount++;
                    }
                    builder.append(write(node, new StringBuilder(), tabAmount));
                    if (!node.isEmpty()) {
                        builder.append(tab);
                        tabAmount--;
                    }
                    builder.append("}");
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

}