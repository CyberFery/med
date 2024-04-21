#!/bin/bash

# Path to the Postman collection file
collection_file="postman/Doctor-Centralized-Medical-Records.postman_collection.json"


# Run Newman to execute the collection
newman run "$collection_file"

echo "Database has been seeded."