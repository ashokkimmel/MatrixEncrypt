import math
import copy
from fractions import Fraction
def number_to_base_ascii(datatype):
    return chr(datatype + 32)

def base_ascii_to_number(datatype):
    return ord(datatype) - 32

def formatter(string):
    strlen = int(math.sqrt(len(string)))
    if strlen == 0:
        raise ValueError("Input a key")
    elif (strlen * strlen) == len(string):
        realkey = [[]]
        currentletter = 0
        currentpart = 0
        for i in string:
            realkey[currentpart].append(ord(i))
            currentletter += 1
            oldletter = currentletter
            currentletter = currentletter % strlen
            if oldletter != currentletter:
                realkey.append([])
                currentpart += 1
        realkey.pop()
        if discriminant(realkey) == 0:
            raise ValueError("Matrix is not invertible, the key must have not have a determinant of 0. (Change the key to something else)")
        return realkey
    else:
        raise ValueError("Matrix is not square, the key must have a square number of characters.")
def encrypt(string, key):
    charlst = string_to_fitted_list(string, len(key))
    arrayedlst = fitted_list_to_matrix(charlst, len(key))
    encodedlst = matrix_multiplication(arrayedlst, key)
    reformattedlst = matrix_to_fitted_list(encodedlst)
    fullyencryptedlst = list(map(number_to_list_of_ascii, reformattedlst))
    finalanswer = " ".join(fullyencryptedlst)
    try: 
        if charlst != string_to_fitted_list(decrypt(finalanswer, key), len(key)):
            raise ValueError("For some reason, decrypting that message fails, change something, I don't know what's wrong :( )")
        else:
            return finalanswer
    except:
        raise ValueError("For some reason, decrypting that message gives an error, change something, I don't know what's wrong :( )") 
def decrypt(strmsg, key):
    lst = strmsg.split(" ")
    listofnum = list(map(list_of_ascii_to_number, lst))
    arrayedlst = fitted_list_to_matrix(listofnum, len(key))
    decryptedlst = matrix_multiplication(arrayedlst, inverse(key))
    finallist = fractions_to_msg(decryptedlst)
    return finallist

def matrix_multiplication(arrayedlst, key):
    encodedlst = []
    for i in arrayedlst:
        list_to_append = []
        for j in range(len(i)):
            tkey = key[j]
            tsum = 0
            for k in range(len(i)):
                tsum += tkey[k] * i[k]
            list_to_append.append(tsum)
        encodedlst.append(list_to_append)
    return encodedlst


def fitted_list_to_matrix(charlst, keylen):
    currentletter = 0
    currentpart = 0
    arrayedlst = [[]]
    for i in charlst:
        arrayedlst[currentpart].append(int(i))
        currentletter += 1
        if currentletter != currentletter % keylen:
            currentletter = 0
            arrayedlst.append([])
            currentpart += 1
    if currentletter != 0:
        raise ValueError("Improper input, make sure you meant to decrypt. If you intentionally meant to decrypt, there is probably a typo. (Make sure there aren't spaces at the end)")
    arrayedlst.pop()
    return arrayedlst

def fractions_to_msg(lst):
    mystring = ""
    for i in lst:
        for j in i:
            if j.denominator != 1:
                raise ValueError("Matrix multiplication failed.")
            mystring += chr(j.numerator)
    return mystring

def rest(lst):
    return lst[1:]

def discriminantrecur(left, right):
    i = 0
    c = 1
    while right != []:
        if len(right[0]) == 1:
            return right[0][0]
        else:
            temp = c * right[0][0]
            i += temp * discriminantrecur([], list(map(rest, left + rest(right))))
            left.append(right[0])
            right = right[1:]
            c = -c
    return i

def discriminant(key):
    return discriminantrecur([], key)

def inverse(key):
    invertedkey = copy.deepcopy(key)
    disc = discriminant(key)
#    print(invertedkey)
    for i in range(len(key)):
        for j in range(len(key)):
            copiedlst = copy.deepcopy(key)
            for element in copiedlst:
                del element[j]
            del copiedlst[i]
            """print(invertedkey)
            print(key)
            print(i)
            print(j)"""
            invertedkey[j][i] = Fraction(((-1) ** (i + j)) * discriminant(copiedlst), disc)
    return invertedkey

def string_to_fitted_list(string, keylen):
    charlst = []
    for ch in string:
        charlst.append(ord(ch))
    dif = keylen - (((len(charlst)-1) % keylen)+1)
    for i in range(dif):
        charlst.append(ord(" "))
    return charlst

def matrix_to_fitted_list(lst):
    newlst = []
    for e in lst:
        for f in e:
            newlst.append(f)
    return newlst

def number_to_list_of_ascii(num):
    newstr = ""
    while num != 0:
        newstr += (number_to_base_ascii(num % 95))
        num = num // 95
    return newstr

def list_of_ascii_to_number(string):
    newnum = 0
    multiplier = 1
    while string != "":
        newnum += multiplier * base_ascii_to_number(string[0])
        string = string[1:]
        multiplier *= 95
    return newnum