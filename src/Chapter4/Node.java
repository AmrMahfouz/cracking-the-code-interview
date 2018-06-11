package Chapter4;

import java.util.ArrayList;
import java.util.List;

class Node {

    int value;
    List<Node> children;
    State state;

    public Node(int value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public void addChildren(List<Node> nodes) {
        children.addAll(nodes);
    }

    public static List<Node> createListOfNodes(int[] values) {
        List<Node> nodes = new ArrayList<>();
        for (int value : values) {
            nodes.add(new Node(value));
        }
        return nodes;
    }

}
