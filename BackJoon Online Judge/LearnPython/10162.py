# https://www.acmicpc.net/problem/10162

time = int(input())

h=300
m=60
s=10


h_ = time // h

m_ = (time % h) // m

s_ = ((time % h) % m) // s

if(((time % h) % m) % s) == 0:
    print(f"{h_} {m_} {s_}")
else:
    print("-1")
    


        

    
    