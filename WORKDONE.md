LOGIN AND SIGN UP
login and sign up using firebase Authentication with phone number. 
directly open main screen if user is already logged in.
ask for sign in if user is signed up in past.

CUSTOMER PART
used recycler view and query in firebase to display only that salon which has same city as customer.
choosing services and slot by clicking on any one of salon.
disable that slot which are closed by barber or booked by any other customer.
by clicking on book now option, it will save detail in customer's history in firebase to retrieve it later.
log out option.

BOOKED HISTORY OF CUSTOMER
used recycler view without any query to firebase database which shows all future ands booked appointments with date, time, Salon name , address and phone number.


BARBER PART
display today's appointmenmts which customer has booked with his phone number, name and service which he wants with the respective time.
display tomorrow's appointments which customer has booked with phone number, name and service and giving facility to barber to cancel that appointment
and he can also close any slot which he wants.
log out option.

PART RELATED WITH TIME
if barber has opened app today, then it maked next date's data in firebase , with the default value availabe.
if barber has not opened app today, then database will not be created for next date. but if customes opens barber's id then the database 
for barber will be created for the next date with status unavailable.
