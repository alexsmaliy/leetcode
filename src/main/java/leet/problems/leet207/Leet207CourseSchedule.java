package leet.problems.leet207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    PROBLEM STATEMENT: There are a total of n courses you have to take,
    labeled from 0 to `n - 1`. Some courses may have prerequisites,
    for example to take course 0 you have to first take course 1, which
    is expressed as a pair: [0,1]. Given the total number of courses
    and a list of prerequisite pairs, is it possible for you to finish
    all courses? You may assume that there are no duplicate edges in
    the input prerequisites.
 */
public class Leet207CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Silly preliminaries for ege cases.
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // We will represent the graph as a mapping from the index of each
        // vertex (from 0 to `numCourses - 1`) to a set of its children.
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Separately, we also keep track of the number of inbound
        // edges to each vertex.
        Map<Integer, Integer> inboundVertexDegree = new HashMap<>();

        // We initalize inbound edge count to 0 for each vertex,
        // and each edge list to an empty list.
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
            inboundVertexDegree.put(i, 0);
        }

        // We construct the edge list and degree counts from input.
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            graph.get(course).add(prereq);
            inboundVertexDegree.put(
                prereq,
                inboundVertexDegree.get(prereq) + 1);
        }

        // We find all vertices with no inbound edges. These
        // wil be the starting points of our cycle searches.
        List<Integer> roots = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inboundVertexDegree.get(i) == 0) {
                roots.add(i);
            }
        }

        // Short-circuit: if every vertex has a parent, there is
        // no entry point into the graph.
        if (roots.isEmpty()) return false;

        // The Tarjan algorithm tracks visited nodes for DFS. Here,
        // we separately have a global list of visited nodes to detect
        // connected components that do not have an entry point.
        boolean[] globalVisitTracker = new boolean[numCourses];

        // Try to find cycles starting at each of the entry points
        // into the graph. Because the condition is to find any
        // cycles at all, we short-circuit as son as we find one.
        for (int root : roots) {
            if (!tarjanCycleFind(root, graph, numCourses, globalVisitTracker)) {
                return false;
            }
        }

        // If we failed to find a cycle starting from an entry point,
        // there exists a possibility of a cycle that had no entry
        // point at all. We check the global array for unvisited nodes.
        for (int i = 0; i < numCourses; i++) {
            if (!globalVisitTracker[i]) return false;
        }

        // An exhaustive search failed to find a cycle.
        return true;
    }

    // The gist of Tarjan seems to be DFS with backoff. As we pursue each
    // path, we keep track of the path of vertices we took to reach the
    // current tip of the path. When a path reaches a dead end, we unmark
    // visited nodes until we have finished backtracking to the most recent
    // fork we haven't finished exploring yet.
    private static boolean tarjanCycleFind(int root,
                                           Map<Integer, List<Integer>> g,
                                           int numVertices,
                                           boolean[] globalVisitTracker) {
        // We track visited vertices as in normal DFS, except
        // we unmark them when we backtrack.
        boolean[] visited = new boolean[numVertices];
        // The global tracker is used by the caller and is irrelevant here.
        globalVisitTracker[root] = true;
        // The FIFO stack keeps track of the next vertex to visit.
        // We keep track of the parent-child edge so that we can backtrack
        // visited nodes up to the parent. The items in the queue are
        // 2-tuples of [node, parent].
        Deque<int[]> visitNext = new ArrayDeque<>();
        // We need a FIFO stack of nodes visited in the current path
        // to facilitate backtracking.
        Deque<Integer> pathStack = new ArrayDeque<>();
        // Initialize the stack of vertices to visit with the children
        // of the root node.
        List<Integer> rootChildren = g.get(root);
        for (int i = 0; i < rootChildren.size(); i++) {
            visitNext.add(new int[] {rootChildren.get(i), root});
        }
        // DFS as usual.
        while (!visitNext.isEmpty()) {
            // Pop next vertex to visit.
            int[] edge = visitNext.poll();
            int vertex = edge[0];
            int predecessor = edge[1];
            // We need to unmark visitor nodes if we have had to
            // backtrack to visit the current vertex (namely, the
            // parent of this vertex is not at the top of the stack
            // of visited vertices. We pop the stack until it is.
            while (!pathStack.isEmpty() && pathStack.peek() != predecessor) {
                int pop = pathStack.removeFirst();
                visited[pop] = false;
            }
            // If the path has curved back to a previously visited node,
            // we have found a cycle.
            if (visited[vertex]) return false;
            // Otherwise, mark the node as visited and add it to the stack.
            visited[vertex] = true;
            pathStack.addFirst(vertex);
            globalVisitTracker[vertex] = true;
            // Add the edges between this node and its children to the
            // collection of vertices to visit.
            List<Integer> children = g.get(vertex);
            for (int i = 0; i < children.size(); i++) {
                visitNext.addFirst(new int[] {children.get(i), vertex});
            }
        }
        return true;
    }
}
