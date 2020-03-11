import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DijkstraShortPath {

	//节点信息.
	private Node[] nodes;

	private int nodeSize;

	private Map<String, Integer> nodeIndexMap = new HashMap<>();

	private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

	private List<String> elements;

	public DijkstraShortPath(List<String> elements){
		this.nodeSize = elements.size();
		this.elements = elements;
		nodes = new Node[this.nodeSize];

		String element;
		for (int i = 0; i < this.nodeSize; i++) {
			element = elements.get(i);
			nodes[i] = new Node(element);
			nodeIndexMap.put(element, i);
		}
	}

	public List<String> getShortPath(String startElement, String endElement){
		int start = nodeIndexMap.get(startElement);
		int end = nodeIndexMap.get(endElement);

		queue.add(start);

		Node node = nodes[start];
		node.pathWeight = 0;
		node.isCheck = true;
		
		checkShortPath(end);

		List<String> path = getResultPath(end);
		return path;
	}

	private List<String> getResultPath(int end){
		Node node = nodes[end];
		Stack<String> revPath = new Stack<>();
		revPath.push(this.elements.get(end));
		while(node.preIndex != -1){
			//注意，这个必须在修改node之前加入path.
			revPath.push(this.elements.get(node.preIndex));
			node = nodes[node.preIndex];
		}

		List<String> result = new ArrayList<>();
		while(!revPath.isEmpty()){
			result.add(revPath.pop());
		}
		return result;
	}

	private void checkShortPath(int end){
		int pathWeight;
		int weight;
		
		Node node = null;
		Node endNode = null;
		int edgeEnd;

		while(!queue.isEmpty()){
			int index = queue.poll();
			node = nodes[index];
			pathWeight = node.pathWeight;
			// 遍历当前节点的所有边
			for (Edge edge : node.edges) {
				edgeEnd = edge.end;
				endNode = nodes[edgeEnd];
				weight = pathWeight + edge.weight;

				if (!endNode.isCheck || endNode.pathWeight > weight) {
					if (edgeEnd != end) {
						//到达终点的话，不添加到队列中.
						queue.add(edgeEnd);
					}
					endNode.pathWeight = weight;
					endNode.isCheck = true;
					endNode.preIndex = edge.start;
				}
			}
		}
	}

	private static class Node {
		//节点名称
		public String nodeName;
		//是否被检测过
		public boolean isCheck;
		//直连边列表.
		public Set<Edge> edges = new HashSet<>();
		//路径值，默认设置为-1
		public int pathWeight = -1;

		public int preIndex = -1;

		public Node(String nodeName){
			this.nodeName = nodeName;
		}
	}

	private static class Edge {
		//权重值
		public int weight;

		//起始位置
		public int start;

		//结束位置
		public int end;

		public Edge(int weight, int start, int end){
			this.weight = weight;
			this.start = start;
			this.end = end;
		}
	}


	// 双向添加边
	public void insertEdge(int start, int end, int weight){
		addNodeEdge(weight, start, end);
		addNodeEdge(weight, end, start);
	}

	private void addNodeEdge (int weight, int start, int end){
		Node node = nodes[start];
		Edge edge = new Edge(weight, start, end);
		node.edges.add(edge);
	}


	public static void main(String[] args) {
		List<String> nodes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
		DijkstraShortPath graph = new DijkstraShortPath(nodes);
		graph.insertEdge(0, 1, 5);
		graph.insertEdge(0, 2, 7);
		graph.insertEdge(0, 6, 5);
		graph.insertEdge(1, 6, 3);
		graph.insertEdge(1, 3, 9);
		graph.insertEdge(2, 4, 8);
		graph.insertEdge(3, 5, 4);
		graph.insertEdge(4, 5, 5);
		graph.insertEdge(4, 6, 4);
		graph.insertEdge(5, 6, 6);
//        graph.insertEdge(6, 2, 1);

		List<String> path = graph.getShortPath("D", "C");
		System.out.println(path);

		/**
		 * 输出：
		 * [D, F, E, C]
		 */
	}
}