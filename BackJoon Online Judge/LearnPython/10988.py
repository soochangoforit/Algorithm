text = list(input())

a = []
flag = 1

for i in (text):
    a.append(i)

for i in range(len(text)):
    if text[i] == a[len(a)-i-1]:
        continue
    else:
        flag = 0
        break

print("1" if flag == 1 else "0")
    
    
    


