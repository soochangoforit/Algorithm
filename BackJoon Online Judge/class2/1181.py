# https://www.acmicpc.net/problem/1181

# import sys

# case = int(sys.stdin.readline())

# text = []

# for i in range(case):
#     text.append(sys.stdin.readline().strip()) # sys.stdin.readline()은 '\n\'을 포함

# text = list(set(text)) # 중복 제거

# text.sort()
# text.sort(key=len)

# # print text
# for i in text:
#     print(i)

N = int(input())
M= [input() for _ in range(N)]
M = list(set(M))


M.sort(key = lambda x : (len(x) , x))
print(*M, sep="\n")














# import sys

# # readline 
# case = int(sys.stdin.readline())

# text = []

# for i in range(case):
#     a = input()
#     if(a in text):
#         continue
#     text.append(a)



# len_text = len(text)

# for j in range(len_text):
#     for k in range(len_text): # 0 ,1 , 2
#         temp=text[k] # 문자열 
#         if(k == len(text) - 1): # 맨마지막은 종료
#             break
#         if(len(temp) > len(text[k+1])): # 길이가 길면 뒤로 이동
#             text[k] = text[k+1]
#             text[k+1]= temp
#         # if lengths are same, compare alphabetically 
#         elif(len(temp) == len(text[k+1])):
#             if(temp > text[k+1]):
#                 text[k] = text[k+1]
#                 text[k+1]= temp
#         else:
#             continue
    

# print(text)
