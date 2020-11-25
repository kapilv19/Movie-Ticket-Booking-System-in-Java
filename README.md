# Movie-Ticket-Booking-System-in-Java

## Description
A small Java API class to reserve movie tickets. (Asked in Udaan On-Campus drive for developer role)
  
## Methods
  
### 1. Add Screen 
Adds a screen to the cinema hall, with the specified number of rows and columns. User can specify the seat numbers which are the aisle seats in that screen. 
Prints success if a new screen has been created, and failure if it already exists.

**Format:** add-screen <screen-name\> <no. of rows\> <seats-per-row\> <list-of-aisle-seats-separeted-by-space\>

_**Example:**_ add-screen Screen1 12 10 4 5 8 9

### 2. Reserve Seat
Reserves seats in a particular screen, with given row number and seat numbers.
Prints failure if not possible and success otherwise.

**Format:** reserve-seat <screen-name\> <row-number\> <space-separated-seats-to-be-reserved\>

_**Example:**_ reserve-seat Screen1 4 5 6 7

### 3. List un-reserved seats in a row 
Prints all the unreserved seat numbers for a particular row at a specified screen, prints none if no seats available or prints failure if screen doesn't exist.

**Format:** get-unreserved-seats <name-of-screen\> <row-number\>

_**Example:**_ get-unreserved-seats Screen2 13

### 4. Suggest available contiguous seats in a row 
Prints specified number of contiguous unreserved seats in a particular row, taking into account aisle seats, and one preferred seat number which should be necessarily included, prints 'none' if this is not possible in that row or prints failure if screen doesn't exist.

**Format:** suggest-contiguous-seats <screen\> <number-of-seats\> <row-number choice-of-seat\>

_**Example:**_ suggest-contiguous-seats Screen1 4 9 5
  
### Sample Input:

8  
add-screen Screen1 12 10 4 5 8 9  
add-screen Screen2 20 25 3 4 12 13 17 18  
reserve-seat Screen1 4 5 6 7  
reserve-seat Screen2 13 6 7 8 9 10  
reserve-seat Screen2 13 4 5 6  
get-unreserved-seats Screen2 13  
suggest-contiguous-seats Screen1 4 9 5  
suggest-contiguous-seats Screen2 4 13 4  
