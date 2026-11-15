#!/usr/bin/env python3

import argparse
import math
import primegen
import random
import time
import util

def generate(keylength, lowerbound, upperbound, verbose=False):
    # First we'll do some input validation
    if keylength < 1:
        raise ValueError('The key length must be a positive integer')
    if lowerbound < 0:
        raise ThresholdError('The lower bound must be a nonnegative integer')
    if lowerbound > upperbound:
        raise ThresholdError('The lower bound must be less than or equal to the upper bound')

    # This is the key that we'll return
    # It holds a list of (n, e, d) tuples
    key = []

    # Get a list of prime numbers up to and including the upper bound
    primes = primegen.generate_up_to(upperbound)

    # Filter out prime numbers that are less than the lower bound
    primes = [p for p in primes if p > lowerbound]

    # A counter for keeping track of the number of (n, e, d) tuples we calculate
    # The public key is a list of (n, e) tuples and the private key is a list of (n, d) tuples
    counter = 0

    # Create a key of the desired length
    while counter < keylength:
        # First, we'll choose two prime numbers, p and q
        p = random.choice(primes)
        q = random.choice(primes)

        # We want p and q to be distinct... if they're not, repeat the loop
        if p == q:
            continue

        # n is a semiprime, which means it's the product of two prime numbers
        # n is the product of p and q
        n = p * q

        # If verbose mode is enabled, print the values of n, p, q
        if verbose:
            print('n = %d, p = %d, q = %d' %(n, p, q))

        # This is a helper variable, and it has two possible values
        # The first possible value is phi = ϕ(n) = (p-1)*(q-1)
        # The second possible value is phi = lcm(p-1,q-1)
        # I named it phi after Euler's totient function ϕ(n)
        phi = (p-1) * (q-1)

        # If verbose mode is enabled, print the value of phi
        if verbose:
            print('phi = %d' %phi)

        # This part is a little confusing, but I'll try to make it clear
        # d should be a large, random integer relatively prime to phi
        # First we'll create a list of candidate d-values
        # Then we'll choose d from our list of candidate d-values
        d_candidates = []
        d_counter = 0
        max_iterations = 1000
        startvalue = phi + 1
        while len(d_candidates) < 10:
            if d_counter > max_iterations:
                raise RuntimeError('Potential infinite loop when calculating the d value')
            value = startvalue + d_counter
            if math.gcd(value, phi) == 1:
                d_candidates.append(value)
            d_counter += 1

        # Here we randomly choose a value from our list of candidates to be our d-value
        d = random.choice(d_candidates)

        # If verbose mode is enabled, print the value of d
        if verbose:
            print('d = %d' %d)

        # Now we calculate the value of e
        # e is the multiplicative inverse of d modulo phi
        for e in range(2, phi):
            if (d * e) % phi == 1:
                break

        # If verbose mode is enabled, print the value of e
        if verbose:
            print('e = %d' %d)

        # Before we add (n, e, d) to our key, make sure it works
        if validate(n, e, d):
            if verbose:
                print('Adding key (n=%d, e=%d, d=%d)' %(n, e, d))
            key.append((n, e, d))
            counter += 1

        # If it doesn't work, print an error message to stdout
        elif verbose:
            print(f'The key n={n}, e={e}, d={d} is not valid')

    # After the while loop has finished, return the key
    return key

def validate(n, e, d):
    message = 'hello world! today is sunday november 15 2026'
    codepoints = list(map(lambda x: ord(x), message))

    encrypted = []
    for i in range(len(codepoints)):
        encrypted.append(util.power_mod_n(codepoints[i], e, n))

    decrypted = []
    for i in range(len(codepoints)):
        decrypted.append(util.power_mod_n(encrypted[i], d, n))

    return codepoints == decrypted

def main():
    parser = argparse.ArgumentParser(prog='keygen.py', description='Generate a RSA key')
    parser.add_argument('-kl', '--keylength', type=int, default=3)
    parser.add_argument('-lb', '--lowerbound', type=float, default=1e3)
    parser.add_argument('-ub', '--upperbound', type=float, default=1e4)
    parser.add_argument('-o', '--outputfile', type=str, default='key.txt')
    parser.add_argument('-v', '--verbose', action='store_true')
    args = parser.parse_args()
    keylength = args.keylength
    lowerbound = int(args.lowerbound)
    upperbound = int(args.upperbound)
    outputfile = args.outputfile
    verbose = args.verbose
    start_time = time.time()
    try:
        key = generate(keylength, lowerbound, upperbound, verbose)
        time_elapsed = time.time() - start_time
        with open(outputfile, 'w') as file:
            for (n, e, d) in key:
                file.write("n=%d e=%d d=%d\n" %(n, e, d))
        print(f'Generated {outputfile} in {time_elapsed} seconds')
    except Exception as err:
        print(err)

if __name__ == '__main__':
    main()
