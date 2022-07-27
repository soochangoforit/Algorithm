# https://www.acmicpc.net/problem/2839

kg = int(input())

cnt = 0

# 우선 5로 가장 먼저 가져가는게 최소갯수를 가져갈 수 있다.
while kg >= 0: 
    target = (kg // 5) * 5 # 18 -> 15

    if target == kg: # 만약 5보다 작은 수가 kg옮으로써 target이 0이 되고, kg도 3배수에 의해서 계속 줄어들어 0이 되었을 경우 -> 해당 if문을 탄다.
        cnt += (kg // 5) # 5보다 작은 수가 오더라도 몫은 0이기 때문에 cnt에 영향을 주지 X
        print(cnt)
        break

    kg -= 3
    cnt += 1

else:
    print(-1)
