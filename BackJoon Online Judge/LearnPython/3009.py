# x와 y값을 입력받아 각각 리스트에 저장해준 다음 요소의 개수를 세어서 개수가 하나인 것을 출력해준다.
x_=[]
y_=[]


for i in range(3):
    x,y = map(int, input().split())
    x_.append(x)
    y_.append(y)

for i in range(3):
    if x_.count(x_[i]) == 1:
        x = x_[i]
    if y_.count(y_[i]) == 1:
        y = y_[i]

print(x,y)
