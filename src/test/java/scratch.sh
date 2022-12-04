
echo 10 | http :8080/create
cat sample | http :8080/add/0
http :8080/detail/0
##----------- find repeated Numbers starting from [1] ----------
echo [4,3,4,4,6,2] | http :8080/repeated-numbers
