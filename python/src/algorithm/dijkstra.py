# 以下用散列表构造加权图
graph = {}

graph["start"] = {}
graph["start"]["a"] = 6
graph["start"]["b"] = 2

graph["a"] = {}
graph["a"]["fin"] = 1

graph["b"] = {}
graph["b"]["a"] = 3
graph["b"]["fin"] = 5

# 终点没有邻居
graph["fin"] = {}

# 以下用一个散列表存储每个节点的开销
# 节点的开销：从起点出发前往该节点需要多长时间

# 以下表示无穷大
infinity = float("inf")

costs = {}
costs["a"] = 6
costs["b"] = 2
costs["fin"] = infinity

# 以下用一个散列表存储父节点
parents = {}
parents["a"] = "start"
parents["b"] = "start"
parents["fin"] = None

# 以下用一个数组记录已处理过的节点，同一个节点不用多次处理
processed = []

def dijkstra(graph):
    # 在未处理的节点中找出开销最小的节点
    node = find_lowest_cost_node(costs)
    print(node)
    # 只要还有未处理的节点
    while node is not None:
        cost = costs[node]
        neighbors = graph[node]
        # 遍历当前节点的所有邻居
        for n in neighbors.keys():
            new_cost = cost + neighbors[n]
            # 如果经当前节点前往改邻居更近
            if costs[n] > new_cost:
                # 就更新该邻居的开销
                costs[n] = new_cost
                # 同时将邻居的父节点设置为当前节点
                parents[n] = node
        # 将当前节点标记为已处理节点
        processed.append(node)
        # 找出接下来要处理的节点并循环
        node = find_lowest_cost_node(costs)

def find_lowest_cost_node(costs):
    lowest_cost = float("inf")
    lowest_cost_node = None
    for node in costs:
        cost = costs[node]
        if (cost < lowest_cost and node not in processed):
            lowest_cost = cost
            lowest_cost_node = node
    return lowest_cost_node