# 创建一张列表，包含要覆盖的州
# 传入一个数组，把它转化为集合
states_needed = set(["mt","wa","or","id","nv","ut","ca","az"])

# 创建字典表示可供选择的广播台清单
stations = {}
stations["kone"] = set(["id","nv","ut"])
stations["ktwo"] = set(["wa","id","mt"])
stations["kthree"] = set(["or","nv","ca"])
stations["kfour"] = set(["nv","ut"])
stations["kfive"] = set(["ca","az"])

# 最终选择的广播台
final_stations = set()

while states_needed:
    best_station = None
    states_coverd = set()
    for station, states_for_station in stations.items():
        # 计算需要覆盖的州和某个电视台能覆盖的州的交集
        covered = states_needed & states_for_station
        if (len(covered) > len(states_coverd)):
            best_station = station
            states_coverd = covered
    final_stations.add(best_station)
    states_needed -= states_coverd

print(final_stations)