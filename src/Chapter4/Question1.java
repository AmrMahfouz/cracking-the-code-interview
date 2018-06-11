package Chapter4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 */
class Question1 {

    public boolean search(Graph g, Node start, Node end) {
        if (start == end) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        for (Node n : g.nodes) {
            n.state = State.UNVISITED;
        }

        start.state = State.VISITING;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null) {
                for (Node n : node.children) {
                    if (n.state == State.UNVISITED) {
                        if (end == n) {
                            return true;
                        } else {
                            n.state = State.VISITING;
                            queue.add(n);
                        }
                    }
                }
                node.state = State.VISITED;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        List<Node> nodes = Node.createListOfNodes(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        nodes.get(0).addChild(nodes.get(1));
        nodes.get(1).addChildren(Arrays.asList(nodes.get(2), nodes.get(3), nodes.get(4)));
        nodes.get(2).addChild(nodes.get(8));
        nodes.get(7).addChild(nodes.get(8));
        nodes.get(8).addChild(nodes.get(3));

        Graph graph = new Graph(nodes);

        Question1 question1 = new Question1();
        System.out.println(question1.search(graph, nodes.get(0), nodes.get(2)));
        System.out.println(question1.search(graph, nodes.get(0), nodes.get(8)));
        System.out.println(question1.search(graph, nodes.get(0), nodes.get(7)));
        System.out.println(question1.search(graph, nodes.get(0), nodes.get(6)));
    }

}
