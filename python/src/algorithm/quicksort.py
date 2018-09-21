def quicksort(array):
    # 基线条件：为空或只包含一个元素的数组是“有序”的
    if len(array) < 2:
        return array
    else:
        # 递归条件
        pivot = array[0]
        # 所有小于等于基准值的元素组成的集合
        less = [i for i in array[1:] if i <= pivot]
        # 所有大于等于基准值的元素组成的集合
        greater = [i for i in array[1:] if i > pivot]
        return quicksort(less) + [pivot] + quicksort(greater)

print(quicksort([15,2,5,3,18,6,7]))
