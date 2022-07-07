num = int(input())

# 소수 1 과 자기 자신으로만 나눌수 있는 수

for i in range(2,num+1):
    if num % i == 0:
        while num % i == 0: # 한번 나눠지기 시작하면, 계속 그 값으로 최대한 나눈다.
            print(i)
            num = num / i
