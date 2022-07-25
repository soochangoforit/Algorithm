# https://www.acmicpc.net/problem/1920

import sys

int(sys.stdin.readline().rstrip())
first_nums= sys.stdin.readline().rstrip().split(" ") #list
first_nums.sort()

second = int(sys.stdin.readline().rstrip())
second_nums= sys.stdin.readline().rstrip().split(" ") #list

def binary_search(first_nums, target):
    first , last = 0 , len(first_nums) - 1 # 인덱스

    while first <= last:
        mid = (first + last) // 2 # mid의 인덱스
        if first_nums[mid] == target:
            return mid # return으로 mid index를 반환한다.
        if first_nums[mid] > target:
            last = mid - 1
        else:
            first = mid +1
    
    return -1


for i in second_nums:
    result_index=binary_search(first_nums , i)
    if(result_index == -1):
        print(0)
    else:
        print(1)





