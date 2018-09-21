from collections import deque
# 以下用一组散列表表示图
graph = {}
graph["you"] = ["alice","bob","claire"]
graph["bob"] = ["anuj","peggy"]
graph["alice"] = ["peggy"]
graph["claire"] = ["thom","jonny"]
graph["anuj"] = []
graph["peggy"] = []
graph["thom"] = []
graph["jonny"] = []

def breadth_first_search(name):
    # 创建一个队列
    search_queue = deque()
    # 将图的第一层加入到搜索队列
    search_queue += graph["you"]
    # 只要队列不空
    while search_queue:
        # 就取出其中第一个人
        person = search_queue.popleft()
        if (person_is_seller(person)):
            print(person + " is a mango seller!")
            return True
        else:
            # 不是要找的对象，则将这个人的朋友都加入到搜索队列
            search_queue += graph[person]
    return  False

def person_is_seller(name):
    return name[-1] == "m"

breadth_first_search("you")


