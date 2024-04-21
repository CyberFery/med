#!/bin/bash

# List of microservices directories
microservices=("eureka-server" "authentication-service" "gateway" "medical-records-service" "modifications-archive-service" "ramq-service")

# Create or overwrite the PID file
pid_file="service_pids.txt"
> "$pid_file" # This line ensures the file is created or overwritten

# Iterate over each microservice directory and run it
for microservice in "${microservices[@]}"; do
    echo "Starting $microservice..."
    cd "$microservice"
    mvn spring-boot:run &
    pid=$! # Get the PID of the last background command
    echo "PID of $microservice is $pid"
    echo "$pid" >> "$pid_file" # Write the PID to the file
    cd ..
done
echo "All microservices started."
