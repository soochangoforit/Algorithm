count = int(input())

yes = 0
no = 0

for i in range(count):
    
    answer = input()
    if answer == "1" :
        yes += 1
    else:
        no += 1

print("Junhee is cute!" if yes > no else "Junhee is not cute!")
