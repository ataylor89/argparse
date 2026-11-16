#!/usr/bin/env python3

import argparse
import math
import primegen
import random
from settings import default_key_length
from settings import default_key_path
from settings import default_lower_bound
from settings import default_upper_bound
from settings import number_of_choices_for_d
import time
import util

def generate(keylength, lowerbound, upperbound, verbose=False):
    # First we'll do some input validation
    if keylength < 1:
        raise ValueError('The key length must be a positive integer')
    if lowerbound < 0:
        raise ValueError('The lower bound must be a nonnegative integer')
    if lowerbound > upperbound:
        raise ValueError('The lower bound must be less than or equal to the upper bound')

    # This is the key that we'll return
    # It holds a list of (n, e, d) tuples
    key = []

    # Get a list of prime numbers up to and including the upper bound
    primes = primegen.generate_up_to(upperbound)

    # Filter out prime numbers that are less than the lower bound
    primes = [p for p in primes if p > lowerbound]

    # A variable for keeping track of the number of (n, e, d) tuples we discover
    # We can think of the full key as a list of (n, e, d) tuples
    # The public key is a list of (n, e) tuples
    # The private key is a list of (n, d) tuples
    progress = 0

    # Create a key of the desired length
    while progress < keylength:
        # First, we'll choose two prime numbers, p and q
        p = random.choice(primes)
        q = random.choice(primes)

        # We want p and q to be distinct... if they're not, repeat the loop
        if p == q:
            continue

        # If verbose mode is enabled, print the values of p and q
        if verbose:
            print(f'p = {p}, q = {q}')

        # n is a semiprime, which means it's the product of two prime numbers
        # n is the product of p and q
        n = p * q

        # If verbose mode is enabled, print the value of n
        if verbose:
            print(f'n = {n}')

        # This is a helper variable, and it has two possible values
        # The first possible value is phi = ϕ(n) = (p-1)*(q-1)
        # The second possible value is phi = lcm(p-1,q-1)
        # I named it phi after Euler's totient function ϕ(n)
        phi = (p-1) * (q-1)

        # If verbose mode is enabled, print the value of phi
        if verbose:
            print(f'\u03d5(n) = {phi}')

        # This part is a little confusing, but I'll try to make it clear
        # d should be a large, random integer relatively prime to phi
        # First we'll create a list of choices for d
        # Then we'll choose d from our list of choices
        choices = []
        counter = 0
        max_iterations = 1e4
        startvalue = n + 1
        while len(choices) < number_of_choices_for_d:
            if counter > max_iterations:
                raise RuntimeError('Potential infinite loop when calculating the d value')
            value = startvalue + counter
            if math.gcd(value, phi) == 1:
                choices.append(value)
            counter += 1

        # Here we randomly choose d from our list of choices
        d = random.choice(choices)

        # If verbose mode is enabled, print the value of d
        if verbose:
            print(f'd = {d}')

        # Now we calculate the value of e
        # e is the multiplicative inverse of d modulo phi
        for e in range(2, phi):
            if (d * e) % phi == 1:
                break

        # If verbose mode is enabled, print the value of e
        if verbose:
            print(f'e = {e}')

        # Before we add (n, e, d) to our key, make sure it works
        if validate(n, e, d):
            if verbose:
                print(f'Adding key (n={n}, e={e}, d={d})')
            key.append((n, e, d))
            progress += 1

        # If it doesn't work, print an error message to stdout
        elif verbose:
            print(f'The key (n={n}, e={e}, d={d}) is not valid')

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
    parser.add_argument('-kl', '--keylength', type=int, default=default_key_length)
    parser.add_argument('-lb', '--lowerbound', type=float, default=default_lower_bound)
    parser.add_argument('-ub', '--upperbound', type=float, default=default_upper_bound)
    parser.add_argument('-o', '--outputfile', type=str, default=default_key_path)
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
                file.write(f'n={n} e={e} d={d}\n')
        print(f'Generated {outputfile} in {time_elapsed} seconds')
    except Exception as err:
        print(err)

if __name__ == '__main__':
    main()
