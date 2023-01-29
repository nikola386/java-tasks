Imagine that you are one of the few lucky developers who has the opportunity to work for SpaceX Corporation. You love your job and therefore you do it very well, your talent and hard work were appreciated, and you were chosen to take part in the new project  of SpaceX Corporation called “FutureOnMars”. The mission is to build a new and better Mars exploration rover (Martian robot) that should be able to freely navigate and operate on Martian surface. The task of the Martian robot will be to collect dust and small stones for research from Martian surface, until his battery level is above 20%, then with the remaining battery power he will return to the station. You have the task to implement the software for the robot. It is crucial to develop fast and good algorithms for every single task in order to preserve as much energy as you can. It is easy to understand why the energy is so important resource in this specific scenario. If the software is good and fast the robot will be more efficient and will perform the task for longer period, which means more dust and stones for research. The robot can collect hundreds of samples in one charge. Now, you need to implement a collection that will store all samples. The collection should store elements without caring about the sequencing. Duplicate elements are allowed. The most important feature is to provide a quick way of getting one element (constant time).

Please create new class with name Bag that should have the following methods:
*	grab() - Returns the value of a random element in the bag.
*	grab(index) - Returns the element at the specified position in the bag.
*	add(item) - Inserts item in the bag. Returns true if a new element is added and false otherwise.
*	remove(index) - Removes the element at the specified position in this list.
*	remove(item) - Removes a single instance of item from this bag and returns true if it is present; otherwise returns false.
*    contains(item) - Returns true if this bag contains the specified element and false otherwise.
*	isEmpty() - Returns true if this collection contains no elements.
*	clear() - Removes all the elements from this bag. The bag will be empty after this method returns.
*	size() - Returns the number of elements in this bag.
*	iterator() - Returns an iterator over the elements in this bag.
*	toArray() - Returns an array containing all the elements in this bag.
*	toString() - Returns a string that displays the elements in the bag. The description is a comma separated list of elements enclosed in brackets.
