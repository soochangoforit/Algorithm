# https://www.acmicpc.net/problem/2775

case = int(input())

for _ in range(case):
    floor = int(input())
    num = int(input())

    f0 = [ x for x in range(1,num+1)] # 0번째 층의 호수별 인원
    # f0 = [1,3,3] -> f가 0일때

    for f in range(1,floor+1):
        for n in range(1,num):
            f0[n] += f0[n-1]
    
    print(f0[-1])


