def KMPSearch(pat, txt):
    M = len(pat)
    N = len(txt)

    sptable = [0]*M

    # Preprocess the pattern
    findSpTable(pat, sptable)

    i = 0  # index for txt[]
    j = 0  # index for pat[]
    while i < N:
        # 문자열이 같은 경우 양쪽 인덱스를 모두 증가시킴
        if pat[j] == txt[i]:
            i += 1
            j += 1
        # Pattern을 찾지 못한 경우
        elif pat[j] != txt[i]:
            # j!=0인 경우는 짧은 sptable에 대해 재검사
            if j != 0:
                j = sptable[j-1]
            # j==0이면 일치하는 부분이 없으므로 인덱스 증가
            else:
                i += 1

        # Pattern을 찾은 경우
        if j == M:
            print("Found pattern at index " + str(i-j))
            # 이전 인덱스의 sptable값을 참조하여 계속 검색
            j = sptable[j-1]

def findSpTable(pat, spt):
    leng = 0  # length of the previous longest prefix suffix

    # 항상 spt[0]==0이므로 while문은 i==1부터 시작한다.
    i = 1
    while i < len(pat):
        # 이전 인덱스에서 같았다면 다음 인덱스만 비교하면 된다.
        if pat[i] == pat[leng]:
            leng += 1
            spt[i] = leng
            i += 1
        else:
            # 일치하지 않는 경우
            if leng != 0:
                # 이전 인덱스에서는 같았으므로 leng을 줄여서 다시 검사
                leng = spt[leng-1]
                # 다시 검사해야 하므로 i는 증가하지 않음
            else:
                # 이전 인덱스에서도 같지 않았다면 spt[i]는 0 이고 i는 1 증가
                spt[i] = 0
                i += 1


txt = 'ABXACBABXAB'
pat = 'ABXAB'
KMPSearch(pat, txt)