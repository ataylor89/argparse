#!/usr/bin/env python3

import argparse
import base64
import keyparser
from settings import default_key_path
import util

def encrypt(plaintext, key):
    bytearr = bytearray()
    codepoints = list(map(lambda x: ord(x), plaintext))
    keylen = len(key)
    for i in range(len(codepoints)):
        (n, e, d) = key[i % keylen]
        size = util.size(n)
        cipher = util.power_mod_n(codepoints[i], e, n)
        bytearr += cipher.to_bytes(size, byteorder='big')
    ciphertext = base64.b64encode(bytearr).decode('utf-8')
    return ciphertext

def main():
    argparser = argparse.ArgumentParser(prog='encrypt.py', description='Encrypt a message')
    group = argparser.add_mutually_exclusive_group(required=True)
    group.add_argument('message', type=str, nargs='?')
    group.add_argument('-i', '--inputfile', type=str)
    argparser.add_argument('-k', '--keyfile', type=str, default=default_key_path)
    argparser.add_argument('-o', '--outputfile', type=str)
    args = argparser.parse_args()
    if args.inputfile:
        with open(args.inputfile, 'r') as file:
            plaintext = file.read()
    else:
        plaintext = args.message
    key = keyparser.parse(args.keyfile)
    ciphertext = encrypt(plaintext, key)
    if args.outputfile:
        with open(args.outputfile, 'w') as file:
            file.write(ciphertext)
    else:
        print(ciphertext, end='')

if __name__ == '__main__':
    main()
