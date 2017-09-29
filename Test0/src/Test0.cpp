//============================================================================
// Name        : Test0.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

int main() {
	__int8 a = 12;
	__int8 b = 23;

	cout << &a << endl << &bwq;
	return 0;
}

//primes p and q
// z = pq
//o = (p-1)(q-1)
//n | gcd(n,o)=1
//z,n are made public
//0<s<o | nsmodo=1
//s is secret used to decrypt messages
//to send integer 0<=a<=(z-1) to holder of public key z,n, the sender computers c=a^nmodz and sends c
//to decrypt, the recipient computes c^smodz
