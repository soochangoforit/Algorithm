# https://www.acmicpc.net/problem/10816
# https://chancoding.tistory.com/45

from sys import stdin

_ = stdin.readline()
N = map(int,stdin.readline().split())
_ = stdin.readline()
M = map(int,stdin.readline().split())

hashmap = {} # HashMap에는 N리스트가 가지고 있는 원소가 Key가 되고 , 개수가 value가 된다.

# HashMap의 사용은 전적으로 N을 모두 순회하면서 Count value를 남기고자 한다.
for n in N:
    if n in hashmap: # HaspMap에소 hasing에 의해 이미 원소를 가지고 있으면 해당 value 값을 1 증가시킨다.
        hashmap[n] += 1 
    else:            # N을 순회하면서 HashMap에 값이 없으면 처음으로 count하는 의미이므로 value에 1를 넣어준다.
        hashmap[n] = 1

# 출력할때는 M을 순회하면서 N에서 찾는게 아니라 HashMap에서 해당 Key가 있는지 확인한다.
print(' '.join(str(hashmap[m]) if m in hashmap else '0' for m in M)) 

# 이번 풀이의 가장 큰 특징은 비록 M이 아닌 N이 가지고 있는 모든 숫자에 대해서 count를 하지만, hash 자료구조의 특징을 활용해서 
# key로 작용하는 n에 대해서 , hashmap에 n이 있으면 해당 dic에서 value를 +1 해주고 없으면 1를 할당해준다.
# 1를 할당해주는 이유는 우리는 지금 M에 대해서가 아닌 , N에 대해서 숫자의 개수를 파악하기 위해서 이다.