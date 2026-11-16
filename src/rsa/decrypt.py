#!/usr/bin/env python3

import argparse
import base64
import keyparser
from settings import default_key_path
import util

def decrypt(ciphertext, key):
    bytearr = base64.b64decode(ciphertext)
    plaintext = ''
    keylen = len(key)
    start = 0
    end = 0
    i = 0
    while start < len(bytearr):
        (n, e, d) = key[i % keylen]
        size = util.size(n)
        end = start + size
        block = bytearr[start:end]
        cipher = int.from_bytes(block, byteorder='big')
        codepoint = util.power_mod_n(cipher, d, n)
        plaintext += chr(codepoint)
        i += 1
        start += size
    return plaintext

def main():
    argparser = argparse.ArgumentParser(prog='decrypt.py', description='Decrypt a message')
    group = argparser.add_mutually_exclusive_group(required=True)
    group.add_argument('message', type=str, nargs='?')
    group.add_argument('-i', '--inputfile', type=str)
    argparser.add_argument('-k', '--keyfile', type=str, default=default_key_path)
    argparser.add_argument('-o', '--outputfile', type=str)
    args = argparser.parse_args()
    if args.inputfile:
        with open(args.inputfile, 'rb') as file:
            ciphertext = file.read()
    else:
        ciphertext = args.message
    key = keyparser.parse(args.keyfile)
    plaintext = decrypt(ciphertext, key)
    if args.outputfile:
        with open(args.outputfile, 'w') as file:
            file.write(plaintext)
    else:
        print(plaintext, end='')

if __name__ == '__main__':
    main()
