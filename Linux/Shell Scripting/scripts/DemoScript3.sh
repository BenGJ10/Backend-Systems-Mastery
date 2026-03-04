#!/bin/bash

echo What is your age?
read age

if [ $age -ge 18 ]
then
    echo You are an adult.
elif [ $age -ge 13 ]
then
    echo You are a teenager.
else
    echo You are a child.
fi