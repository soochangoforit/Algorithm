# https://www.acmicpc.net/problem/1541

num = input().split('-')

sum = 0

# - 이 나오기 전까지는 숫자 모두 더하기
for i in num[0].split('+'):
    sum += int(i)

# 첫 - 이후 부터는 각각의 - 사이에 있는 숫자들을 더해서 빼주기
for i in num[1:]:
    # + 기호가 없는 숫자들은 단독으로 반환된다.
    for j in i.split('+'):
        sum -= int(j)
    
print(sum)

