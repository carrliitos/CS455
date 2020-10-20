'''This program finds Euler's Totient function'''

def phi(n):
	result = n
	p = 2
	while(p * p <= n):
		# Check if prime factor
		if(n % p == 0):
			# update n
			while(n % p == 0):
				n = n // p
			# result
			result = result * (1.0 - (1.0 / (float)(p)))
		p += 1

	if(n > 1):
		result = result * (1.0 - (1.0 / (float)(n)))

	return (int)(result)

def main():
	val = input("To calculate phi(n), enter n: ")
	val = int(val)
	print("phi(", val, ") = ", phi(val))

if __name__ == '__main__':
	main()