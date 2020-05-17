学习笔记

# DFS 代码模板
递归写法
```java
visited = set() 

def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 

	visited.add(node) 

	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

非递归写法
```java
def DFS(self, tree): 

	if tree.root is None: 
		return [] 

	visited, stack = [], [tree.root]

	while stack: 
		node = stack.pop() 
		visited.add(node)

		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 

	# other processing work 
	...
```

# BFS 代码模板
```java
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 

	while queue: 
		node = queue.pop() 
		visited.add(node)

		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)

	# other processing work 
```

# 二分查找

使用二分查找算法的前提
    1. 目标函数单调性（单调递增或递减）
    2. 存在上下界(bounded)
    3. 能够通过索引访问（index accessible）


代码模板：
```java
int left = 0, right = len(array)-1;

while(left <= right) {
    int mid = (left + right) /2;   
    if (array[mid] == target){
        //find the target
        return mid or target;
     } else if ( array[mid] < target){
        left = mid + 1;
     }  else  {
        right = mid - 1;
     }   
    
}
```

牛顿迭代法
    // todo
    
做题两个准则： 
    1. 五毒神掌（做五遍）
    2. 四步做题（审题（clarrification）, 所有的解法都思考一遍并求出相应的时间复杂度和空间复杂度得出最优解，
       写代码，测试样例）
       
# 被劈断的有序数组，使用0(log(n))的算法找到被劈断的位置

```java
public int searchUnSordPos(int[] nums) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) /2;
            if( mid>0 && nums[mid -1] > nums[mid]) {
                result = mid;
                break;
            }
            if( nums[left] <= nums[mid] ) {
                left = mid ;
            } else {
                right = mid ;
            }
        }
        return result;
    }
```
