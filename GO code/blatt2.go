package main

import (
	"fmt"
)

func main() {

	var a = "Hallo"
	var b = "world"
	saySentence(a, b)
	fmt.Println(greater(5, 4))

	var hannah Student
	hannah.matrikelnr = 1
	hannah.name = "hannr"

	var dan Student
	dan.matrikelnr = 2
	dan.name = "Daniel"

	var liste = [2]Student{hannah, dan}
	for idx, val := range liste {
		fmt.Printf("%v\t%v\n", idx, val)
	}

	myslice1 := []int{}
	fmt.Println(len(myslice1))
	fmt.Println(cap(myslice1))
	fmt.Println(myslice1)

	myslice2 := []string{"Go", "Slices", "Are", "Powerful"}
	fmt.Println(len(myslice2))
	fmt.Println(cap(myslice2))
	fmt.Println(myslice2)

	myslice2 = append(myslice2, "hello")
	fmt.Println(len(myslice2))
	fmt.Println(cap(myslice2))
	fmt.Println(myslice2)

}

func saySentence(a string, b string) {
	fmt.Println(a, b)
}

func greater(a int, b int) (result int) {

	if a >= b {
		result = a
	} else {
		result = b
	}
	return
}

type Student struct {
	name       string
	matrikelnr int
}
