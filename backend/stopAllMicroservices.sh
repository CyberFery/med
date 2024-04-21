#!/bin/bash

# List of microservices directories
microservices=("eureka-server" "authentication-service" "gateway" "medical-records-service" "modifications-archive-service" "ramq-service")

# Iterate over each microservice directory and stop the service
for microservice in "${microservices[@]}"; do
    echo "Stopping $microservice..."
    cd "$microservice"

    # Check if the PID file exists
    pid_file="service_pids.txt"
    if [ -f "$pid_file" ]; then
        # Read the PID from the file and kill the process
        while read -r pid; do
            echo "Killing PID $pid"
            kill "$pid"
        done < "$pid_file"
        # Remove the PID file after stopping the service
        rm "$pid_file"
    else
        echo "PID file not found for $microservice"
    fi

    cd ..
done
echo "All microservices stopped."
