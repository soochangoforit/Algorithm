# https://www.acmicpc.net/problem/11047

import sys

N , K = map(int, sys.stdin.readline().split())

coins = [ int(sys.stdin.readline()) for _ in range(N)]

# delete coins that are smaller than K
coins = [ coin for coin in coins if coin <= K]

count = 0

for coin in reversed(coins):
    count += K // coin
    K %= coin

print(count)