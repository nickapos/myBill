#!/bin/bash
#usage scriptname pofile and the pofile is converted to properties file
filename=$1
extension="${filename##*.}"
barename="${filename%.*}"
python ./po2prop.py -t ./myBillUIBundle.properties  $1 >$barename".properties"
