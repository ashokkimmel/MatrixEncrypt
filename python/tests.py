from encrypt import string_to_fitted_list, fitted_list_to_matrix, decrypt, encrypt
def tests():
    expected = [97, 32]
    result = string_to_fitted_list("a", 2)
    if expected != result:
        print("error string_to_fitted_list not working. Expected: " + str(expected) + " got " + str(result))

    expected = [97, 65]
    result = string_to_fitted_list("aA", 2)
    if expected != result:
        print("error string_to_fitted_list not working. Expected: " + str(expected) + " got " + str(result))

    expected = [97, 65, 97, 32]
    result = string_to_fitted_list("aAa", 2)
    if expected != result:
        print("error string_to_fitted_list not working. Expected: " + str(expected) + " got " + str(result))

    expected = [[10, 10]]
    result = fitted_list_to_matrix([10, 10], 2)
    if expected != result:
        print("error fitted_list_to_matrix not working. Expected: " + str(expected) + " got " + str(result))

    expected = []
    result = fitted_list_to_matrix([10, 10], 3)
    if expected != result:
        print("error fitted_list_to_matrix not working. Expected: " + str(expected) + " got " + str(result))

tests()
print(decrypt(encrypt("I love pater!", formatter("2384")), formatter("2384")))