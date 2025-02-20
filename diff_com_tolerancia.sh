#!/bin/bash
TOL="10^(-2)"

diff -q "$1" "$2" >/dev/null 2>/dev/null
if [ "$?" == "0" ]; then
    files_are_equal="true"
else
    diff -q -b "$1" "$2" >/dev/null 2>/dev/null
    if [ "$?" == "0" ]; then
        files_are_equal="true"
    else
        diff -q -b -B "$1" "$2" >/dev/null 2>/dev/null
        if [ "$?" == "0" ]; then
            files_are_equal="true"
        else
            diff -q -i -b -B "$1" "$2" >/dev/null 2>/dev/null
            if [ "$?" == "0" ]; then
                files_are_equal="true"
            else
                diff -q -b -B -w "$1" "$2" >/dev/null 2>/dev/null
                if [ "$?" == "0" ]; then
                    files_are_equal="true"
                else
                    diff -q -i -b -B -w "$1" "$2" >/dev/null 2>/dev/null
                    if [ "$?" == "0" ]; then
                        files_are_equal="true"
                    fi
                fi
            fi
        fi
    fi
fi

if [ "$files_are_equal" == "true" ]; then
    echo "Iguais"
    exit 1
else
    readarray -t arq1 < $1
    readarray -t arq2 < $2

    if [[ ${#arq1[@]} == ${#arq2[@]} ]]; then

        for (( i=0; i<${#arq1[@]}; i++ )); do

            readarray -d ";" -t tokens1 < <(printf '%s' "${arq1[i]}")
            readarray -d ";" -t tokens2  < <(printf '%s' "${arq2[i]}")

            if [[ ${#tokens1[@]} == ${#tokens2[@]} ]]; then

                for (( j=0; j<${#tokens1[@]}; j++ )); do

                    if [[ ${tokens1[j]} =~ [R][$][\ ][0-9]+([.,][0-9]+)? ]]; then
                        expressao=$( echo "${tokens1[j]:3} - ${tokens2[j]:3}" | sed 's/,/./g' )
                        result=$(echo "scale=4; $expressao " | bc  )
                        difference=$(echo "$result" | sed 's/-//' )
                        check_tolerance=$(echo "scale=2; $difference <= $TOL " | bc )
                        if [[ "$check_tolerance" == "0" ]]; then
                            echo "Diferentes"
                            exit 0
                        fi
                        
                    else
                        if [[ ${tokens1[j]} != ${tokens2[j]} ]]; then
                            echo "Diferentes"
                            exit 0
                        fi
                    fi

                done

            else 
                echo "Diferentes"
                exit 0
            fi

        done
    else
        echo "Diferentes"
        exit 0
    fi

    echo "Iguais"
    exit 1
    

fi  