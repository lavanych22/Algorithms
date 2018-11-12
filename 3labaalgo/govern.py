from collections import defaultdict

VISITED = 1
RESOLVED = 2
def dfs(graph):
    unvisited = set(graph.keys())
    status = {}
    order_set = set()

    while unvisited:
        for vertex in dfs_iteration(graph, status, unvisited, unvisited.pop()):
            if vertex in order_set:
                continue
            order_set.add(vertex)

            yield vertex

def dfs_iteration(graph, status, unvisited, start_v):
    stack = [start_v]
    while stack:
        vertex = stack.pop()
        if vertex in unvisited:
            unvisited.remove(vertex)

        if status.get(vertex) == RESOLVED:
            continue

        neighbors = [edge for edge in graph[vertex] if edge not in status]
        if neighbors:
            status[vertex] = VISITED
            stack.append(vertex)
            stack.extend(neighbors)
        else:
            status[vertex] = RESOLVED
            yield vertex

def get_topological_sort(graph):
    return dfs(graph)

def read_graph(fl):
    graph = defaultdict(set)
    for line in fl.readlines():
        print(line)
        _from, to = line.rstrip().split()
        graph[_from].add(to)
    return graph

def main():
    with open('govern.in') as fl:
        graph = read_graph(fl)

    path = get_topological_sort(graph)

    result = '\n'.join(path) + '\n'

    print(result)

if __name__ == '__main__':
    main()
