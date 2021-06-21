
# Buidl and Run

mvn clean install

$ mvn spring-boot:run

# Create redis servis with docker

docker run -d -p 6379:6379 --name my-redis redis


# use postman

#save

http://localhost:8080/employee/save

 {
  "id": "85",
  "name": "Sezayir",
  "department":"IT"
}


#Get all employee
http://localhost:8080/employee/all


#Delete
http://localhost:8080/employee/delete/{id}


# Connect with redis-cli
docker exec -it my-redis redis-cli


127.0.0.1:6379> keys *
1) "\xac\xed\x00\x05t\x00\bEMPLOYEE"
127.0.0.1:6379>
