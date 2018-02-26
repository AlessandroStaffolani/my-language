package evaluator;

import javafx.scene.control.TreeItem;

import java.util.List;

public class TreeBuilder {

    public static <T> void preorder(TreeItem<T> root, StringBuilder sb, String separator) {
        if (root != null) {
            sb.append(root.getValue());
            List<TreeItem<T>> children = root.getChildren();
            if (children != null) {
                for (TreeItem<T> child : children) {
                    sb.append(separator);
                    preorder(child, sb, separator);
                }
            }
        }
    }

    public static <T> void postorder(TreeItem<T> root, StringBuilder sb, String separator) {
        if (root != null) {
            List<TreeItem<T>> children = root.getChildren();
            if (children != null) {
                for (TreeItem<T> child : children) {
                    postorder(child, sb, separator);
                    sb.append(separator);
                }
            }
            sb.append(root.getValue());
        }
    }

    public static <T> void inorder(TreeItem<T> root, StringBuilder sb, String separator) {
        if (root != null) {
            List<TreeItem<T>> children = root.getChildren();
            if (children != null && children.size() > 2) {
                throw new IllegalArgumentException("Not binary");
            }
            if (children != null && children.size() > 0) {
                inorder(children.get(0), sb, separator);
            }
            sb.append(root.getValue());
            sb.append(separator);
            if (children != null && children.size() > 1) {
                inorder(children.get(1), sb, separator);
            }
        }
    }
}
