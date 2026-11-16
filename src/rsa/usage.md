# Example usage

Here are some notes on example usage.

    # First, we cd into the rsa directory
    % cd ~/Github/argparse/src/rsa

    # Second, we generate an RSA key, and we enable verbose mode
    % python keygen.py -v
    n = 24034511, p = 7151, q = 3361
    phi = 24024000
    d = 24024211
    e = 24024211
    Adding key (n=24034511, e=5692891, d=24024211)
    n = 22343911, p = 4231, q = 5281
    phi = 22334400
    d = 22334617
    e = 22334617
    Adding key (n=22343911, e=22128553, d=22334617)
    n = 38253169, p = 7717, q = 4957
    phi = 38240496
    d = 38240519
    e = 38240519
    Adding key (n=38253169, e=31589975, d=38240519)
    Generated key.txt in 2.59076189994812 seconds

    # Third, we inspect the outputfile, which is key.txt by default
    % cat key.txt
    n=24034511 e=5692891 d=24024211
    n=22343911 e=22128553 d=22334617
    n=38253169 e=31589975 d=38240519

    # Fourth, we use our keyfile to encrypt and decrypt a message
    % python encrypt.py "hello world today is sunday november 15 2026"
    AWOLmwB80+0CGNxLABqjKwBHnD0BMcliAKFdhQBHnD0CJPc6ABqjKwB0i8UBMcliACvQegBHnD0AAcqCAQ25lABYb0ABMcliANoexQE/TJwBMcliAKh4HABiKVwCJ22RAPoAgAA32DYAyk+NAK6YdQBfx4kAhYu7AOwrUAB80+0A1oqsATK/KwB80+0CJPc6AK6YdQD/zNwBNeEsAK6YdQDjJOYBQDkzAEq4nwCReiY=

    # This time, we write the output to file
    % python encrypt.py "hello world today is sunday november 15 2026" -o cipher.txt
    % cat cipher.txt
    AWOLmwB80+0CGNxLABqjKwBHnD0BMcliAKFdhQBHnD0CJPc6ABqjKwB0i8UBMcliACvQegBHnD0AAcqCAQ25lABYb0ABMcliANoexQE/TJwBMcliAKh4HABiKVwCJ22RAPoAgAA32DYAyk+NAK6YdQBfx4kAhYu7AOwrUAB80+0A1oqsATK/KwB80+0CJPc6AK6YdQD/zNwBNeEsAK6YdQDjJOYBQDkzAEq4nwCReiY=

    # Fifth, we decrypt the encrypted file
    % python decrypt.py -i cipher.txt
    hello world today is sunday november 15 2026

    # It works!
    # You might be wondering, why don't we specify the keyfile, when we encrypt/decrypt a message?
    # It's actually configured to use key.txt as the default keyfile
    # But if we want to we can specify the keyfile
    # Let's do that quickly
    % python keygen.py -o mykeyfile.txt -v
    n = 65850769, p = 7537, q = 8737
    phi = 65834496
    d = 65834677
    e = 65834677
    Adding key (n=65850769, e=727453, d=65834677)
    n = 43864193, p = 6997, q = 6269
    phi = 43850928
    d = 43851031
    e = 43851031
    Adding key (n=43864193, e=2554423, d=43851031)
    n = 9885251, p = 3343, q = 2957
    phi = 9878952
    d = 9879007
    e = 9879007
    Adding key (n=9885251, e=7543927, d=9879007)
    Generated mykeyfile.txt in 0.46289992332458496 seconds

    # Let's inspect the keyfile
    % cat mykeyfile.txt 
    n=65850769 e=727453 d=65834677
    n=43864193 e=2554423 d=43851031
    n=9885251 e=7543927 d=9879007

    # Let's use mykeyfile.txt to encrypt or decrypt a message
    % python encrypt.py "hello world this is a test message" -k mykeyfile.txt
    Attl1AIr334R4TEBDUPmAaO5Ni7ztgMi3CYBo7k2ccfnAQ1D5gCFTq8u87YAthj1ARU4vZP98QKAEtgAYOo8k/3xAoAS2ABg6jwFLgcBdheuAAJJf0kdXQKAEtgAAkl/LvO2A94HtQIr334dVeECgBLYAWLP7nqQJANXazU=

    # Let's write the output to mycipher.txt
    % python encrypt.py "hello world this is a test message" -k mykeyfile.txt -o mycipher.txt

    # Let's decrypt the encrypted file, using mykeyfile.txt
    % python decrypt.py -i mycipher.txt -k mykeyfile.txt
    hello world this is a test message

    # Once again, it works :)

I hope that these examples serve as a good demonstration.
