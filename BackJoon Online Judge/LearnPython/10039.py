total = 0

for i in range(5):
    tem = int(input())

    if tem < 40:
        tem = 40
    
    total += tem

print(total // 5)

# // 연산자는 나눈 값을 정수형으로 출력하기 위한 연산자이다.
# / 연산자를 사용하면 나누어 떨어져도 50.0 같이 실수형으로 나옴