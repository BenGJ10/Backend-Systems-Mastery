#!/bin/bash

echo "Enter a number:"
read num

if (( $num <= 0 )); 
then
  echo "Please enter a positive integer."
  exit 1
fi

for ((i = 1; i <= $num; i++)); 
do
  echo "Number: $i"
done

# The difference between using (( )) and [ ] in bash is that 
# (( )) is used for arithmetic expressions and allows for more complex calculations, 
# while [ ] is used for conditional expressions and is more limited in its capabilities.

echo Do you want to list users inside /etc/passwd? [y/n]
read answer

if [ "$answer" == "y" ]; then
  echo "Executing command..."
  
  i=1
  for username in `awk -F: '{print $1}' /etc/passwd | tail -n 10`; 
  do
      echo "Username $i: $username"
      i=$((i+1))
  done

else
  echo "Command execution skipped."
fi
  
# awk -F: '{print $1}' /etc/passwd: is used to extract the first field (username) from the /etc/passwd file, 
# which contains user account information. The `-F:` option tells `awk` to use a colon (:) as the field separator
