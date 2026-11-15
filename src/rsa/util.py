def power_mod_n(base, expo, n):
    res = 1
    base = base % n
    while expo > 0:
        if expo & 1:
            res = (res * base) % n
        base = (base * base) % n
        expo = expo // 2
    return res
