#!/usr/bin/env python3

import argparse
from math import ceil, log
import time

def generate(n):
    if not isinstance(n, int) or n < 1:
        raise ValueError('The generate method accepts a positive integer')
    upper_bound = ceil(n*log(n) + n*log(log(n)))
    arr = sieve(upper_bound)
    primes = [index for index, value in enumerate(arr) if value is True]
    return primes[0:n]

def sieve(size):
    if not isinstance(size, int) or size < 2:
        raise ValueError('The sieve method accepts a positive integer greater than one')
    arr = [True] * size
    arr[0] = False
    arr[1] = False
    for i in range(2, size):
        if arr[i] is True:
            j = 2
            while i * j < size:
                arr[i * j] = False
                j += 1
    return arr

def main():
    parser = argparse.ArgumentParser(prog='primegen.py', description='Generate primes')
    parser.add_argument('count', type=float)
    parser.add_argument('-o', '--outputfile', type=str)
    parser.add_argument('-t', '--time', action='store_true')
    args = parser.parse_args()
    try:
        n = int(args.count)
        assert n > 0
    except:
        print('Unable to parse the command-line argument as a positive integer')
        return
    if args.time:
        st = time.time()
        primes = generate(n)
        te = time.time() - st
    else:
        primes = generate(n)
    if args.outputfile:
        with open(args.outputfile, 'w') as file:
            file.write(str(primes))
    else:
        print(primes)
    if args.time:
        print('Generated %d primes in %s seconds' %(n, te))

if __name__ == '__main__':
    main()
